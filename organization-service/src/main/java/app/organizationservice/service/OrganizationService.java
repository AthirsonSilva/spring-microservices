package app.organizationservice.service;

import app.organizationservice.payload.OrganizationDTO;

public interface OrganizationService {
	OrganizationDTO save(OrganizationDTO organizationDTO);

	OrganizationDTO findById(Long id);

	OrganizationDTO findByCode(String code);
}
