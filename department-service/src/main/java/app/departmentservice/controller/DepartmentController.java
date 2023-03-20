package app.departmentservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.departmentservice.payload.DepartmentDTO;
import app.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/department")
@AllArgsConstructor
public class DepartmentController {
	private DepartmentService departmentService;

	@PostMapping
	public ResponseEntity<DepartmentDTO> create(@RequestBody DepartmentDTO request) {
		DepartmentDTO savedDepartment = departmentService.save(request);

		return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
	}

	@GetMapping("{code}")
	public ResponseEntity<?> get(@PathVariable String code) {
		return new ResponseEntity<>(departmentService.findByCode(code), HttpStatus.OK);
	}
}
