package app.organizationservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import app.organizationservice.entity.OrganizationEntity;
import app.organizationservice.payload.OrganizationDTO;

@Mapper
public interface OrganizationMapper {
	OrganizationMapper INSTANCE = Mappers.getMapper(OrganizationMapper.class);

	@Mapping(source = "id", target = "id")
	OrganizationDTO toOrganizationDTO(OrganizationEntity organization);

	@Mapping(source = "id", target = "id")
	OrganizationEntity toOrganizationEntity(OrganizationDTO organizationDTO);
}
