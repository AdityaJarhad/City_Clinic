package com.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorRegistrationRequest {
    private Long userId;                    // ID of the user (primary key)
    private Long specializationId;          // ID of the specialization
    private String qualifications;           // Qualifications of the doctor
    private String experience;               // Experience of the doctor
    private String availabilitySchedule;     // Availability schedule of the doctor
    private String profilePicture;           // Profile picture URL or path

    private String clinicName;               // Name of the clinic
    private String clinicMoNo;               // Mobile number of the clinic
    private String clinicEmail;              // Email of the clinic
    private String clinicDescription;        // Description of the clinic

    private String locationAddress;           // Address of the location
    private String locationCity;              // City of the location
    private String locationState;             // State of the location
    private String locationZipCode;           // Zip code of the location
    private String locationCountry;           // Country of the location
}
