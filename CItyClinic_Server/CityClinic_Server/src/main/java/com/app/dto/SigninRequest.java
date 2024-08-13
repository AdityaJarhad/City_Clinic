package com.app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SigninRequest {

	@NotEmpty(message = "Email can't be blank")
	@Email(message = "Invalid email format")
	private String email;

	@NotEmpty
	private String password;
}
