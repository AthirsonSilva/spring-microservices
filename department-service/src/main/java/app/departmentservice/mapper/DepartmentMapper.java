package app.departmentservice.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;

import app.departmentservice.entity.DepartmentEntity;
import app.departmentservice.payload.DepartmentDTO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DepartmentMapper {
	private final ModelMapper mapper;

	public static DepartmentDTO toDepartmentDTO(DepartmentEntity department) {
		DepartmentDTO departmentDTO = new DepartmentDTO();
		BeanUtils.copyProperties(department, departmentDTO);

		return departmentDTO;
	}

	public static DepartmentEntity toDepartmentEntity(DepartmentDTO departmentDTO) {
		DepartmentEntity department = new DepartmentEntity();
		BeanUtils.copyProperties(departmentDTO, department);

		return department;
	}

	public DepartmentDTO toDepartmentDTOWithModelMapper(DepartmentEntity department) {
		return mapper.map(department, DepartmentDTO.class);
	}

	public DepartmentEntity toDepartmentEntityWithModelMapper(DepartmentDTO departmentDTO) {
		return mapper.map(departmentDTO, DepartmentEntity.class);
	}
}
