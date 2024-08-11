package com.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
public class DoctorDTO {
    
	@NotBlank(message = "Qualifications are required")
    private String qualifications;

    @NotBlank(message = "Experience is required")
    private String experience;

    @NotBlank(message = "Availability schedule is required")
    private String availabilitySchedule;

    private String profilePicture;

    @NotNull(message = "Specialization ID is required")
    private Long specializationId;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Clinic ID is required")
    private Long clinicId;
}
