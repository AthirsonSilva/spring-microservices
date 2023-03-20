package net.employeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.employeeservice.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

}
