package net.employeeservice.service.impl;

import java.util.List;

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
import net.employeeservice.repository.EmployeeRepository;
import net.employeeservice.service.APIClient;
import net.employeeservice.service.EmployeeService;

@Service
@Transactional
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	private final EmployeeRepository employeeRepository;
	private final WebClient webClient;
	private APIClient apiClient;

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
	@Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
	@Override
	public APIResponseDTO findById(Long id) {
		LOGGER.info("inside findById method of EmployeeServiceImpl class");

		EmployeeEntity employee = employeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("employee", "id", id));

		DepartmentDTO department = fetchEmployeeDepartmentFeign(employee.getDepartmentCode());

		APIResponseDTO apiResponse = new APIResponseDTO(EmployeeMapper.toEmployeeDTO(employee), department);

		return apiResponse;
	}

	@Override
	public List<EmployeeDTO> findByDepartment(String code) {
		List<EmployeeDTO> employees = employeeRepository.findByDepartmentCode(code)
				.stream().map(employee -> EmployeeMapper.toEmployeeDTO(employee))
				.toList();

		return employees;
	}

	private DepartmentDTO fetchEmployeeDepartment(String departmentCode) {
		return webClient
				.get()
				.uri("http://localhost:8080/api/v1/department/" + departmentCode)
				.retrieve()
				.bodyToMono(DepartmentDTO.class)
				.block();
	}

	private DepartmentDTO fetchEmployeeDepartmentFeign(String departmentCode) {
		return apiClient.getDepartment(departmentCode);
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

		APIResponseDTO apiResponse = new APIResponseDTO(EmployeeMapper.toEmployeeDTO(employee), department);

		return apiResponse;
	}
}
