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
    
    @NotNull
    private Long userId;
    
    @NotNull
    private Long specializationId;
    
    @NotNull
    private Long clinicId;
    
    @NotBlank
    private String qualifications;
    
    @NotBlank
    private String experience;
    
    @NotBlank
    private String availabilitySchedule;
    
    private String profilePicture;
}
