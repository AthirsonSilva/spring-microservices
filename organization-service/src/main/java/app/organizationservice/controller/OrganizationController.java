package app.organizationservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.organizationservice.payload.OrganizationDTO;
import app.organizationservice.service.OrganizationService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/organizations")
@AllArgsConstructor
public class OrganizationController {
	private final OrganizationService organizationService;

	@PostMapping
	public ResponseEntity<OrganizationDTO> save(@RequestBody OrganizationDTO organizationDTO) {
		return new ResponseEntity<>(organizationService.save(organizationDTO), HttpStatus.CREATED);
	}

	@GetMapping("{id}")
	public ResponseEntity<OrganizationDTO> findById(@PathVariable Long id) {
		return new ResponseEntity<>(organizationService.findById(id), HttpStatus.FOUND);
	}

	@GetMapping
	public ResponseEntity<OrganizationDTO> findByCode(@PathParam("code") String code) {
		return new ResponseEntity<>(organizationService.findByCode(code), HttpStatus.FOUND);
	}
}
