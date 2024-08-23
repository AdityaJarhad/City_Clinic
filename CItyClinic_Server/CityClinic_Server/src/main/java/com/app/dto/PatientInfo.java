package com.app.dto;

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
public class PatientInfo {

	 
	    private Long userId;
	    
	    
	    private String name;
	    
	    
	    private String contactNumber;
}
