package net.employeeservice.service.impl;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import net.employeeservice.entity.EmployeeEntity;
import net.employeeservice.mapper.EmployeeMapper;
import net.employeeservice.payload.EmployeeDTO;
import net.employeeservice.repository.EmployeeRepository;
import net.employeeservice.service.EmployeeService;

@Service
@Transactional
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	private final EmployeeRepository employeeRepository;

	@Override
	public EmployeeDTO save(EmployeeDTO employeeDTO) {
		EmployeeEntity employeeEntity = employeeRepository.save(EmployeeMapper.toEmployeeEntity(employeeDTO));

		return EmployeeMapper.toEmployeeDTO(employeeEntity);
	}

	@Override
	public EmployeeDTO findById(Long id) {
		EmployeeEntity employee = employeeRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Employee not found with id: " + id));

		return EmployeeMapper.toEmployeeDTO(employee);
	}

}
