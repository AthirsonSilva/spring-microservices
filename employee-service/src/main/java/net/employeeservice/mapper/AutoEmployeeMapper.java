package net.employeeservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import net.employeeservice.entity.EmployeeEntity;
import net.employeeservice.payload.EmployeeDTO;

@Mapper
public interface AutoEmployeeMapper {
	AutoEmployeeMapper INSTANCE = Mappers.getMapper(AutoEmployeeMapper.class);

	@Mapping(source = "id", target = "id")
	EmployeeDTO toEmployeeDTO(EmployeeEntity employee);

	@Mapping(source = "id", target = "id")
	EmployeeEntity toEmployeeEntity(EmployeeDTO employeeDTO);
}
