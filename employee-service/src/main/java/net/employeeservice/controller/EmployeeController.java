package net.employeeservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import net.employeeservice.payload.EmployeeDTO;
import net.employeeservice.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/employee")
@AllArgsConstructor
public class EmployeeController {
	private final EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<EmployeeDTO> save(@Validated @RequestBody EmployeeDTO employeeDTO) {
		return new ResponseEntity<>(employeeService.save(employeeDTO), HttpStatus.CREATED);
	}

	@GetMapping("{id}")
	public ResponseEntity<EmployeeDTO> get(@PathVariable Long id) {
		return new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);
	}
}
