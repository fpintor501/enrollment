package com.metadt.enrollment.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.metadt.enrollment.model.Course;
import com.metadt.enrollment.model.Enrollment;
import com.metadt.enrollment.model.Student;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>, JpaSpecificationExecutor<Enrollment>, QuerydslPredicateExecutor<Enrollment> {

	List<Enrollment> findByStudentId(Long studentId);
	
	List<Enrollment> findByCourseId(Long courseId);
	
	@Query("SELECT s FROM Student s WHERE s.id in (SELECT e.student.id FROM Enrollment e WHERE e.course.id = ?1)")
	Collection<Student> searchStudentsWithCourse(Long courseId);
	
	@Query("SELECT s FROM Student s WHERE s.id in (SELECT e.student.id FROM Enrollment e WHERE e.course.id = ?1 and e.semester=?2)")
	Collection<Student> searchStudentsWithCourseInSemester(Long courseId, String semester);
	
	@Query("SELECT c FROM Course c WHERE c.id in (SELECT e.course.id FROM Enrollment e WHERE e.student.id = ?1)")
	Collection<Course> searchCoursesWithStudent(Long studentId);
	
	@Query("SELECT c FROM Course c WHERE c.id in (SELECT e.course.id FROM Enrollment e WHERE e.student.id = ?1 and e.semester=?2)")
	Collection<Course> searchCoursesWithStudentInSemester(Long studentId, String semester);
	
	@Query("SELECT count(*) FROM Enrollment e WHERE e.student.id = ?1 and e.semester=?2")
	Integer getCountCoursesForStudentInSemester(Long studentId, String semester);
	
	@Query("SELECT count(*) FROM Enrollment e WHERE e.course.id = ?1 and e.semester=?2")
	Integer getCountStudentsInCourseForSemester(Long courseId, String semester);
	
	
	
}

