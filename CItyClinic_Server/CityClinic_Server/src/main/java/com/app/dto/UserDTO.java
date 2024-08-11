package com.app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
public class UserDTO {
    
    @NotBlank
    private String name;
    
    @NotBlank
    private String password;
    
    @Email
    @NotBlank
    private String email;
    
    @NotBlank
    private String role;
    
    @NotBlank
    private String contactNumber;
    
    @NotBlank
    private String address;
}
