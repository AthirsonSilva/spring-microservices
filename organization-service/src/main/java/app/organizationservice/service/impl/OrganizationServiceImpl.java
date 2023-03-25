package app.organizationservice.service.impl;

import static app.organizationservice.mapper.OrganizationMapper.INSTANCE;

import org.springframework.stereotype.Service;

import app.organizationservice.entity.OrganizationEntity;
import app.organizationservice.exception.ResourceNotFoundException;
import app.organizationservice.payload.OrganizationDTO;
import app.organizationservice.repository.OrganizationRepository;
import app.organizationservice.service.OrganizationService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
	private final OrganizationRepository organizationRepository;

	@Override
	public OrganizationDTO findById(String id) {
		return organizationRepository.findById(id).map(org -> INSTANCE.toOrganizationDTO(org))
				.orElseThrow(() -> new ResourceNotFoundException("Organization", "id", id));
	}

	@Override
	public OrganizationDTO findByCode(String code) {
		OrganizationEntity organizationEntity = organizationRepository.findByOrganizationCode(code)
				.orElseThrow(() -> new ResourceNotFoundException("Organization", "code", code));

		return INSTANCE.toOrganizationDTO(organizationEntity);
	}

	@Override
	public OrganizationDTO save(OrganizationDTO organizationDTO) {
		OrganizationEntity organizationEntity = INSTANCE.toOrganizationEntity(organizationDTO);

		return INSTANCE.toOrganizationDTO(organizationRepository.save(organizationEntity));
	}

}
