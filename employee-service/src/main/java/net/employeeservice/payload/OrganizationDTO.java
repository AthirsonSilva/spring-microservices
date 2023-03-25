package net.employeeservice.payload;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrganizationDTO {
	private Long id;
	private String organizationName;
	private String organizationDescription;
	private String organizationCode;
	private LocalDateTime creationDate;
}
