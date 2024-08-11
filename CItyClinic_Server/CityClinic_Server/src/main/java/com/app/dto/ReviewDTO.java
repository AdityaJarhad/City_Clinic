package com.app.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
public class ReviewDTO {
    
    @NotNull
    private Long patientId;
    
    @NotNull
    private Long doctorId;
    
    @Min(1)
    @Max(10)
    private Long rating;
    
    @NotNull
    private LocalDate date;
}
