package com.app.dto;



import java.time.LocalDate;

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
public class DetailedAppointmentDTO {

    private Long appointmentId;
    private Long doctorId;
    private LocalDate appointmentDate;
    private String status;

    private Long patientId;
    private String patientName;
    private String patientEmail;
    private String patientContactNumber;
    private String patientAddress;

}
