package com.app.dto;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
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
public class AppointmentDTO {
    
    @NotNull
    private Long patientId;
    
    @NotNull
    private Long doctorId;
    
    @NotNull
    @FutureOrPresent
    private LocalDate appointmentDate;
    
    @NotBlank
    private String status;
}
