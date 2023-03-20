package app.departmentservice.mapper;

import org.springframework.beans.BeanUtils;

import app.departmentservice.entity.DepartmentEntity;
import app.departmentservice.payload.DepartmentDTO;

public class DepartmentMapper {
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
}
