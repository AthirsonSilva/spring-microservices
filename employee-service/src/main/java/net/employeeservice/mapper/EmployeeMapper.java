package net.employeeservice.mapper;

import org.springframework.beans.BeanUtils;

import net.employeeservice.entity.EmployeeEntity;
import net.employeeservice.payload.EmployeeDTO;

public class EmployeeMapper {
	public static EmployeeDTO toEmployeeDTO(EmployeeEntity employeeEntity) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		BeanUtils.copyProperties(employeeEntity, employeeDTO);

		return employeeDTO;
	}

	public static EmployeeEntity toEmployeeEntity(EmployeeDTO employeeDTO) {
		EmployeeEntity employeeEntity = new EmployeeEntity();
		BeanUtils.copyProperties(employeeDTO, employeeEntity);

		return employeeEntity;
	}
}
