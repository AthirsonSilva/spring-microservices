package app.departmentservice.service;

import java.util.Optional;

import app.departmentservice.payload.DepartmentDTO;

public interface DepartmentService {
	DepartmentDTO save(DepartmentDTO deparmentDTO);

	Optional<DepartmentDTO> findByCode(String code);
}
