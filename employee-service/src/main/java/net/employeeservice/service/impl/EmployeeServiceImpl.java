package net.employeeservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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

	@Override
	public APIResponseDTO findById(Long id) {
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
}
