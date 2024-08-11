package com.app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClinicDTO {

	@NotBlank(message = "Clinic name is required")
	private String name;

	@NotBlank(message = "Description is required")
	private String description;

	@NotBlank(message = "Email is required")
	@Email(message = "Email should be valid")
	private String email;

	@NotBlank(message = "Mobile number is required")
	@Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Mobile number is invalid")
	private String moNo;

	@NotNull(message = "Location ID is required")
	private Long locationId;
}
