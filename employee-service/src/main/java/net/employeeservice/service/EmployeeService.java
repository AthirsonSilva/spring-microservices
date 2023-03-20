package net.employeeservice.service;

import net.employeeservice.payload.EmployeeDTO;

public interface EmployeeService {
	EmployeeDTO save(EmployeeDTO employee);

	EmployeeDTO findById(Long id);
}
