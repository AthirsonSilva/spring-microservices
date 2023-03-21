package net.employeeservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import net.employeeservice.payload.DepartmentDTO;

@FeignClient(url = "http://localhost:8080", name = "department-service")
public interface APIClient {
	@GetMapping("/api/v1/department/{code}")
	public DepartmentDTO getDepartment(@PathVariable String code);
}
