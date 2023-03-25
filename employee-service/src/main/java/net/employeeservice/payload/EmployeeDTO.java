package net.employeeservice.payload;

import jakarta.validation.constraints.Email;
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
public class EmployeeDTO {
	private Long id;
	@NotEmpty(message = "An employee must have a first name and it cannot be empty")
	private String firstName;
	@NotEmpty(message = "An employee must have a last name and it cannot be empty")
	private String lastName;
	@Email(message = "An employee must have a valid email address")
	private String email;
	private String departmentCode;
	private String organizationCode;
}
