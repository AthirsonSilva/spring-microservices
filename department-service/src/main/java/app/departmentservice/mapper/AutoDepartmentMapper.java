package app.departmentservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import app.departmentservice.entity.DepartmentEntity;
import app.departmentservice.payload.DepartmentDTO;

@Mapper
public interface AutoDepartmentMapper {
	AutoDepartmentMapper INSTANCE = Mappers.getMapper(AutoDepartmentMapper.class);

	@Mapping(source = "id", target = "id")
	DepartmentDTO toDepartmentDTO(DepartmentEntity department);

	@Mapping(source = "id", target = "id")
	DepartmentEntity toDepartmentEntity(DepartmentDTO departmentDTO);
}
