package com.metadt.enrollment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.metadt.enrollment.model.Course;

@Component
public class CourseService {
	
	private CourseServiceHelper courseServiceHelper;

	@Autowired
	public CourseService(CourseServiceHelper courseServiceHelper) {
		this.courseServiceHelper = courseServiceHelper;
	}

	public List<Course> findCourseQBE(Course course) {
		
		return courseServiceHelper.findCourseQBE(course);
	}
	
	public void runSplashReport() {
		
		courseServiceHelper.runSplashReport();
	
	}
	
}
