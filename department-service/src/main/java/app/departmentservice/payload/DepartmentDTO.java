package app.departmentservice.payload;

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

	private String departmentName;

	private String departmentDescription;

	private String departmentCode;
}
