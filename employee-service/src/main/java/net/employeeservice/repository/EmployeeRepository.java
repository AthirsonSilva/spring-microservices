package net.employeeservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.employeeservice.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
	Optional<EmployeeEntity> findByEmail(String email);

	List<EmployeeEntity> findByDepartmentCode(String departmentCode);
}
