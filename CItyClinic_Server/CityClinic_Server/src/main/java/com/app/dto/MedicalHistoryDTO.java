package com.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MedicalHistoryDTO {
    
    @NotNull
    private Long patientId;
    
    @NotNull
    private Long doctorId;
    
    @NotBlank
    private String diagnosis;
    
    @NotBlank
    private String treatment;
    
    @NotNull
    private LocalDate visitDate;
    
    private String documentURL;
}
