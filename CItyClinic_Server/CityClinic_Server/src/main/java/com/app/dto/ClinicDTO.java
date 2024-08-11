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
public class ClinicDTO {
    
    @NotBlank
    private String name;
    
    @NotBlank
    private String address;
    
    @NotBlank
    private String moNo;
    
    @NotBlank
    private String email;
    
    private String description;
    
    @NotNull
    private Long locationId;
}
