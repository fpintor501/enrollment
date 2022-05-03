package com.metadt.enrollment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentService {
	
	private StudentServiceHelper studentServiceHelper;

	@Autowired
	public StudentService(StudentServiceHelper studentServiceHelper) {
		this.studentServiceHelper = studentServiceHelper;
	}

	public void runSplashReport() {
		
		studentServiceHelper.runSplashReport();
	
	}
	
}
