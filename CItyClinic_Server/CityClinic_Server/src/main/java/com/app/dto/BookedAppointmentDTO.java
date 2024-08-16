package com.app.dto;

import java.time.LocalDate;
import java.time.LocalTime;

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
public class BookedAppointmentDTO {
    private Long id;
    private String doctorName;
    private String clinicName;
    private LocalDate appointmentDate;
    private String status;

   
}
