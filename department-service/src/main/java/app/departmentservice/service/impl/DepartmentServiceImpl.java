package app.departmentservice.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import app.departmentservice.entity.DepartmentEntity;
import app.departmentservice.mapper.DepartmentMapper;
import app.departmentservice.payload.DepartmentDTO;
import app.departmentservice.repository.DepartmentRepository;
import app.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
	private DepartmentRepository departmentRepository;

	@Override
	public DepartmentDTO save(DepartmentDTO departmentDTO) {
		DepartmentEntity departmentEntity = DepartmentMapper.toDepartmentEntity(departmentDTO);

		DepartmentEntity savedDepartment = departmentRepository
				.save(departmentEntity);

		return DepartmentMapper.toDepartmentDTO(savedDepartment);
	}

	@Override
	public Optional<DepartmentDTO> findByCode(String code) {
		Optional<DepartmentEntity> departmentEntity = departmentRepository
				.findByDepartmentCode(code);

		return departmentEntity.map(DepartmentMapper::toDepartmentDTO);
	}
}
