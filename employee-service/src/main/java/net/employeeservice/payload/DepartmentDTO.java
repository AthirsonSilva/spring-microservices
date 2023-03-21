package net.employeeservice.payload;

import jakarta.validation.constraints.NotEmpty;
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
public class DepartmentDTO {
	private Long id;
	@NotEmpty(message = "A department must have a name and it cannot be empty")
	private String departmentName;
	@NotEmpty(message = "A department must have a description and it cannot be empty")
	private String departmentDescription;
	@NotEmpty(message = "A department must have a code and it cannot be empty")
	private String departmentCode;
}
