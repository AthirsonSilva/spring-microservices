package app.departmentservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.departmentservice.entity.DepartmentEntity;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
	Optional<DepartmentEntity> findByDepartmentCode(String code);
}
