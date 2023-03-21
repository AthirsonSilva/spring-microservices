package net.employeeservice.service;

import net.employeeservice.payload.APIResponseDTO;
import net.employeeservice.payload.EmployeeDTO;

public interface EmployeeService {
	EmployeeDTO save(EmployeeDTO employee);

	APIResponseDTO findById(Long id);
}
