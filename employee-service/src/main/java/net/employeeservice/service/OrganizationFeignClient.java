package net.employeeservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import net.employeeservice.payload.OrganizationDTO;

@FeignClient(name = "organization-service")
public interface OrganizationFeignClient {
	@GetMapping("/api/v1/organizations?code={code}")
	public OrganizationDTO getOrganization(@PathVariable String code);
}
