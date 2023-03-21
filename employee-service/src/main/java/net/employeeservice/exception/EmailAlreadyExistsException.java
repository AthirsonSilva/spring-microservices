package net.employeeservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(value = HttpStatus.CONFLICT)
@Getter
public class EmailAlreadyExistsException extends RuntimeException {
	private String email;

	public EmailAlreadyExistsException(String email) {
		super(String.format("An employee with email %s already exists", email));

		this.email = email;
	}
}
