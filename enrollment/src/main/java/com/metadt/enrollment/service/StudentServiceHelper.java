package com.metadt.enrollment.service;

import com.metadt.enrollment.model.Student;
import com.metadt.enrollment.repository.StudentRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentServiceHelper {

	public StudentRepository studentRepository;

	@Autowired
	private StudentServiceHelper(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	
	public void runSplashReport() {
		
		List<Student> students = this.studentRepository.findAll();
		
		System.out.println("********************************** S T U D E N T S **********************************");		
		System.out.println("");

		for (Student s : students) System.out.println(s.toStringFull()); 
		
		System.out.println("");
		System.out.println("*************************************************************************************");
	}
	
}
