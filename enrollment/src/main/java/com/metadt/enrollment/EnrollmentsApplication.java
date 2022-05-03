package com.metadt.enrollment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.metadt.enrollment.service.CourseService;
import com.metadt.enrollment.service.StudentService;
import com.metadt.enrollment.service.EnrollmentService;

import java.util.Scanner;

@SpringBootApplication
public class EnrollmentsApplication {
//public class TransApplication  extends SpringBootServletInitializer {


	public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(EnrollmentsApplication.class, args);
        
        CourseService courseService = applicationContext.getBean(CourseService.class);
        StudentService studentService = applicationContext.getBean(StudentService.class);
        EnrollmentService enrollmentService = applicationContext.getBean(EnrollmentService.class);
                
	
		try (Scanner scanner = new Scanner(System.in)) {
			
			System.out.println("Print Splash Reports(Y)?");
			String yes = scanner.next();

			if (yes.equals("Y")) {
				courseService.runSplashReport();		         
				studentService.runSplashReport();
				enrollmentService.runSplashReport();
			}
		}
		
	}

}
