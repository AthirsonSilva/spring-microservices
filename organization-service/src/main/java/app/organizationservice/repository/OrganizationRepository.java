package app.organizationservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import app.organizationservice.entity.OrganizationEntity;

public interface OrganizationRepository extends MongoRepository<OrganizationEntity, Long> {
	Optional<OrganizationEntity> findByOrganizationCode(String code);

	Optional<OrganizationEntity> findById(String id);
}