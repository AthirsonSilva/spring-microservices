package app.organizationservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.organizationservice.entity.OrganizationEntity;

public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Long> {
	Optional<OrganizationEntity> findByOrganizationCode(String code);
}