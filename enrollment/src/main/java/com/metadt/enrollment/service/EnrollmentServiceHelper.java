package com.metadt.enrollment.service;

import com.metadt.enrollment.model.Enrollment;
import com.metadt.enrollment.model.Student;
import com.metadt.enrollment.exception.ConditionNotAcceptableException;
import com.metadt.enrollment.exception.ResourceNotFoundException;
import com.metadt.enrollment.model.Course;
import com.metadt.enrollment.model.DTO.EnrollmentDTO;
import com.metadt.enrollment.repository.EnrollmentRepository;
import com.metadt.enrollment.repository.StudentRepository;
import com.metadt.enrollment.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnrollmentServiceHelper {

	public EnrollmentRepository enrollmentRepository;
	public CourseRepository courseRepository;
	public StudentRepository studentRepository;

	@Autowired
	private EnrollmentServiceHelper(EnrollmentRepository enrollmentRepository, 
			                        CourseRepository courseRepository, 
			                        StudentRepository studentRepository) {
		this.enrollmentRepository = enrollmentRepository;
		this.courseRepository = courseRepository;
		this.studentRepository = studentRepository;
	}
	
	
	@PostMapping("/enrollment")
	@ResponseBody
	public ResponseEntity<Enrollment> createEnrollment(@RequestBody EnrollmentDTO enrollmentDTO) {
		
		final Integer COURSES_PER_STUDENT_PER_SEMESTER_LIMIT = 5;
		final Integer STUDENTS_PER_COURSE_PER_SEMESTER_LIMIT = 50;
	    
		Optional<Student> studentOptional = studentRepository.findById(enrollmentDTO.getStudentId());
		Optional<Course> courseOptional = courseRepository.findById(enrollmentDTO.getCourseId());
	    
		if (!studentOptional.isPresent()) throw new ResourceNotFoundException("Student id-" + enrollmentDTO.getStudentId());
		
		if (!courseOptional.isPresent()) throw new ResourceNotFoundException("Course id-" + enrollmentDTO.getCourseId());
		
		Integer coursesPerStudent = enrollmentRepository.getCountCoursesForStudentInSemester(enrollmentDTO.getStudentId(), 
                enrollmentDTO.getSemester());
		
//		System.out.println("Courses for Student ID-"+enrollmentDTO.getStudentId()+" son :"+coursesPerStudent+" #");
//		System.out.println("Value for COURSES_PER_STUDENT_PER_SEMESTER_LIMIT-"+COURSES_PER_STUDENT_PER_SEMESTER_LIMIT+" #");
		
		if (coursesPerStudent >= COURSES_PER_STUDENT_PER_SEMESTER_LIMIT)
				throw new ConditionNotAcceptableException("Student id-" + enrollmentDTO.getStudentId()+" CANNOT enroll in more Courses.");
		
		Integer studentsPerCourse = enrollmentRepository.getCountStudentsInCourseForSemester(enrollmentDTO.getCourseId(), 
                enrollmentDTO.getSemester());

//		System.out.println("Students for Course ID-"+enrollmentDTO.getCourseId()+" son :"+studentsPerCourse+" #");
//		System.out.println("Value for STUDENTS_PER_COURSE_PER_SEMESTER_LIMIT-"+STUDENTS_PER_COURSE_PER_SEMESTER_LIMIT+" #");
		
		if (studentsPerCourse >= STUDENTS_PER_COURSE_PER_SEMESTER_LIMIT)
				throw new ConditionNotAcceptableException("Course id-" + enrollmentDTO.getCourseId()+" CANNOT get more Students enrolled.");
		
		Enrollment newEnrollment  = new Enrollment();
	    
	    newEnrollment.setStudent(studentOptional.get());
	    newEnrollment.setCourse(courseOptional.get());
	    
	    newEnrollment.setSection(enrollmentDTO.getSection());
	    newEnrollment.setRoom(enrollmentDTO.getRoom());
	    newEnrollment.setSemester(enrollmentDTO.getSemester());
	    newEnrollment.setGrade(enrollmentDTO.getGrade());   
	    
	    return new ResponseEntity<Enrollment>(enrollmentRepository.save(newEnrollment), HttpStatus.CREATED);
    }
	
	@PutMapping("/enrollment/{id}")
	@ResponseBody
	public ResponseEntity<Enrollment> updateEnrollment(@RequestBody EnrollmentDTO enrollmentDTO, @PathVariable Long id) {

		Optional<Enrollment> enrollmentOptional = enrollmentRepository.findById(id);

		if (!enrollmentOptional.isPresent()) throw new ResourceNotFoundException("Enrollment id-" + id);

	    Enrollment enrollment  = enrollmentOptional.get();
	    
	    if (enrollmentDTO.getSection()!=null) enrollment.setSection(enrollmentDTO.getSection());
	    if (enrollmentDTO.getRoom()!=null) enrollment.setRoom(enrollmentDTO.getRoom());
	    if (enrollmentDTO.getSemester()!=null) enrollment.setSemester(enrollmentDTO.getSemester());
	    if (enrollmentDTO.getGrade()!=null) enrollment.setGrade(enrollmentDTO.getGrade());
	    
	    enrollmentRepository.save(enrollment);
	    
		return new ResponseEntity<Enrollment>(HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/enrollment/{id}")
	@ResponseBody	
	public ResponseEntity<Enrollment> deleteEnrollment(@PathVariable Long id) {
		
		Optional<Enrollment> enrollmentOptional = enrollmentRepository.findById(id);

		if (!enrollmentOptional.isPresent()) throw new ResourceNotFoundException("Enrollment id-" + id);
		
		enrollmentRepository.deleteById(id);
		
		return new ResponseEntity<Enrollment>(HttpStatus.ACCEPTED);
	}
	
	public void runSplashReport() {
		
		List<Enrollment> enrollments = this.enrollmentRepository.findAll();
		
		System.out.println("******************************* E N R O L L M E N T S *******************************");		
		System.out.println("");

		for (Enrollment e : enrollments) System.out.println(e.toStringFull()); 
		
		System.out.println("");
		System.out.println("*************************************************************************************");
	}
	
}
