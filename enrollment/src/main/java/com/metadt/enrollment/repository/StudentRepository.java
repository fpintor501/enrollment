package com.metadt.enrollment.repository;

import com.metadt.enrollment.model.Course;
import com.metadt.enrollment.model.Student;

import java.util.Collection;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student>, QuerydslPredicateExecutor<Student> {
	
	List<Student> findByFirstName(String firstName);
	
	List<Student> findByFirstNameContainingIgnoreCase(String firstName);
	
	List<Student> findByLastName(String lastName);
	
	List<Student> findByLastNameContainingIgnoreCase(String lastName);
	
	List<Student> findByEmail(String email);
	
	List<Student> findByEmailContainingIgnoreCase(String email);
	
	@Query("SELECT s FROM Student s WHERE NOT EXISTS (SELECT e.id FROM Enrollment e WHERE e.student.id = s.id)")
	Collection<Student> searchStudentsWithNoCourses();
	
	@Query("SELECT s FROM Student s WHERE NOT EXISTS (SELECT e.id FROM Enrollment e WHERE e.student.id = s.id and e.semester=?1)")
	Collection<Course> searchStudentsWithNoCoursesInSemester(String semester);

}

