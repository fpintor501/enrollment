package com.metadt.enrollment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentService {
	
	private EnrollmentServiceHelper enrollmentServiceHelper;

	@Autowired
	public EnrollmentService(EnrollmentServiceHelper enrollmentServiceHelper) {
		this.enrollmentServiceHelper = enrollmentServiceHelper;
	}
	
	
	public void runSplashReport() {
		
		enrollmentServiceHelper.runSplashReport();
	
	}
	
}
