package net.employeeservice.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import net.employeeservice.entity.EmployeeEntity;
import net.employeeservice.exception.EmailAlreadyExistsException;
import net.employeeservice.exception.ResourceNotFoundException;
import net.employeeservice.mapper.EmployeeMapper;
import net.employeeservice.payload.APIResponseDTO;
import net.employeeservice.payload.DepartmentDTO;
import net.employeeservice.payload.EmployeeDTO;
import net.employeeservice.payload.OrganizationDTO;
import net.employeeservice.repository.EmployeeRepository;
import net.employeeservice.service.DepartmentFeignClient;
import net.employeeservice.service.EmployeeService;

@Service
@Transactional
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	private final EmployeeRepository employeeRepository;
	private final WebClient webClient;
	private DepartmentFeignClient apiClient;

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Override
	public EmployeeDTO save(EmployeeDTO employeeDTO) {
		if (employeeRepository.findByEmail(employeeDTO.getEmail()).isPresent()) {
			throw new EmailAlreadyExistsException(employeeDTO.getEmail());
		}

		if (fetchEmployeeDepartmentFeign(employeeDTO.getDepartmentCode()) == null)
			throw new ResourceNotFoundException("department", "code", employeeDTO.getDepartmentCode());

		EmployeeEntity employeeEntity = employeeRepository.save(EmployeeMapper.toEmployeeEntity(employeeDTO));

		return EmployeeMapper.toEmployeeDTO(employeeEntity);
	}

	@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
	@Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultOrganization")
	@Override
	public APIResponseDTO findById(Long id) {
		LOGGER.info("inside findById method of EmployeeServiceImpl class");

		EmployeeEntity employee = employeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("employee", "id", id));

		DepartmentDTO department = fetchEmployeeDepartmentFeign(employee.getDepartmentCode());
		OrganizationDTO organization = fetchEmployeeOrganization(employee.getOrganizationCode());

		APIResponseDTO apiResponse = new APIResponseDTO(EmployeeMapper.toEmployeeDTO(employee), department, organization);

		return apiResponse;
	}

	@Override
	public List<EmployeeDTO> findByDepartment(String code) {
		List<EmployeeDTO> employees = employeeRepository.findByDepartmentCode(code)
				.stream().map(employee -> EmployeeMapper.toEmployeeDTO(employee))
				.toList();

		return employees;
	}

	private OrganizationDTO fetchEmployeeOrganization(String organizationCode) {
		return webClient
				.get()
				.uri("http://localhost:8083/api/v1/organizations?code=" + organizationCode)
				.retrieve()
				.bodyToMono(OrganizationDTO.class)
				.block();
	}

	private DepartmentDTO fetchEmployeeDepartmentFeign(String departmentCode) {
		return apiClient.getDepartment(departmentCode);
	}

	private APIResponseDTO getDefaultOrganization(Long id, Exception e) {
		LOGGER.info("inside getDefaultOrganization method of EmployeeServiceImpl class");

		EmployeeEntity employee = employeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("employee", "id", id));

		DepartmentDTO department = fetchEmployeeDepartmentFeign(employee.getDepartmentCode());

		OrganizationDTO organization = new OrganizationDTO();
		organization.setId(String.valueOf(UUID.randomUUID()));
		organization.setOrganizationCode("O001");
		organization.setOrganizationName("Default Org");
		organization.setOrganizationDescription("Default Organization for research");
		organization.setCreationDate(LocalDateTime.now());

		APIResponseDTO apiResponse = new APIResponseDTO(EmployeeMapper.toEmployeeDTO(employee), department, organization);

		return apiResponse;
	}

	private APIResponseDTO getDefaultDepartment(Long id, Exception e) {
		LOGGER.info("inside getDefaultDepartment method of EmployeeServiceImpl class");

		EmployeeEntity employee = employeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("employee", "id", id));

		DepartmentDTO department = new DepartmentDTO();
		department.setId(0L);
		department.setDepartmentCode("D001");
		department.setDepartmentName("Default DP");
		department.setDepartmentDescription("Default Department for research");

		OrganizationDTO organization = fetchEmployeeOrganization(employee.getOrganizationCode());

		APIResponseDTO apiResponse = new APIResponseDTO(EmployeeMapper.toEmployeeDTO(employee), department, organization);

		return apiResponse;
	}
}
