package app.organizationservice.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
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
@Document(collection = "organizations")
public class OrganizationEntity implements Serializable {
	@Id
	private String id;
	private String organizationName;
	private String organizationDescription;
	private String organizationCode;
	@CreatedDate
	private LocalDateTime creationDate;
}