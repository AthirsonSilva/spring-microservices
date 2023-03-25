package app.organizationservice.service;

import app.organizationservice.payload.OrganizationDTO;

public interface OrganizationService {
	OrganizationDTO save(OrganizationDTO organizationDTO);

	OrganizationDTO findById(String id);

	OrganizationDTO findByCode(String code);
}
