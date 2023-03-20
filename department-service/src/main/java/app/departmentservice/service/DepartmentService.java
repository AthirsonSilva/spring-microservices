package app.departmentservice.service;

import app.departmentservice.payload.DepartmentDTO;

public interface DepartmentService {
	DepartmentDTO save(DepartmentDTO deparmentDTO);

	DepartmentDTO findByCode(String code);
}
