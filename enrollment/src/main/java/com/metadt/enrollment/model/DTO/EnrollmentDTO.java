package com.metadt.enrollment.model.DTO;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentDTO implements Serializable {
  
	private static final long serialVersionUID = 1L;

	private Long id;
    
    private Long studentId;

    private Long courseId;
    
    private String section;
    
    private String room;
    
    private String semester;
    
    private Integer grade;
 
}