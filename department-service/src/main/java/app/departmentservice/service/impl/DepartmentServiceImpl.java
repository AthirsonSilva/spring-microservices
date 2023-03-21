package app.departmentservice.service.impl;

import org.springframework.stereotype.Service;

import app.departmentservice.entity.DepartmentEntity;
import app.departmentservice.exception.DepartmentCodeAlreadyExistsException;
import app.departmentservice.exception.ResourceNotFoundException;
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

		if (departmentRepository.findByDepartmentCode(departmentEntity.getDepartmentCode()).isPresent()) {
			throw new DepartmentCodeAlreadyExistsException(departmentEntity.getDepartmentCode());
		}

		DepartmentEntity savedDepartment = departmentRepository
				.save(departmentEntity);

		return DepartmentMapper.toDepartmentDTO(savedDepartment);
	}

	@Override
	public DepartmentDTO findByCode(String code) {
		DepartmentEntity departmentEntity = departmentRepository.findByDepartmentCode(code)
				.orElseThrow(() -> new ResourceNotFoundException("department", "code", code));

		return DepartmentMapper.toDepartmentDTO(departmentEntity);
	}
}
