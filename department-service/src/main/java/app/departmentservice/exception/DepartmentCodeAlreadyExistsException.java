package app.departmentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter
public class DepartmentCodeAlreadyExistsException extends RuntimeException {
	private String departmentCode;

	public DepartmentCodeAlreadyExistsException(String departmentCode) {
		super(String.format("A department with code %s already exists", departmentCode));

		this.departmentCode = departmentCode;
	}
}
