package com.metadt.enrollment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import com.metadt.enrollment.model.Course;
import com.metadt.enrollment.repository.CourseRepository;

@Component
public class CourseServiceHelper {

	public CourseRepository courseRepository;

	@Autowired
	private CourseServiceHelper(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	public List<Course> findCourseQBE(Course course) { 
	      
	      Example<Course> courseExample = Example.of(course);
	      
	      List<Course> courses = this.courseRepository.findAll(courseExample);
	      
	      return courses;
 
	}
	
	public void runSplashReport() {
		
		List<Course> courses = this.courseRepository.findAll();
		
		System.out.println("************************************* C O U R S E S *************************************");		
		System.out.println("");

	      for (Course c : courses) System.out.println(c.toStringFull()); 
		
		System.out.println("");
		System.out.println("*****************************************************************************************");
	}
	
}
