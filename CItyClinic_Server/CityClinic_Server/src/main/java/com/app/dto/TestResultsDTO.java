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
public class TestResultsDTO {
    
    @NotNull
    private Long medicalHistoryId;
    
    @NotBlank
    private String testName;
    
    @NotNull
    private LocalDate testDate;
    
    @NotBlank
    private String status;
    
    private String documentURL;
}
