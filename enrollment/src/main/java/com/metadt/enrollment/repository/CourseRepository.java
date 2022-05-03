package com.metadt.enrollment.repository;

import com.metadt.enrollment.model.Course;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course>, QuerydslPredicateExecutor<Course> {
	
	List<Course> findByCode(String code);
	
	List<Course> findByCodeContains(String code);
	
	List<Course> findByTitle(String title);
	
	List<Course> findByTitleContains(String title);
	
	@Query("SELECT c FROM Course c WHERE NOT EXISTS (SELECT e.id FROM Enrollment e WHERE e.course.id = c.id)")
	Collection<Course> searchCoursesWithNoStudents();
	
	@Query("SELECT c FROM Course c WHERE NOT EXISTS (SELECT e.id FROM Enrollment e WHERE e.course.id = c.id and e.semester=?1)")
	Collection<Course> searchCoursesWithNoStudentsInSemester(String semester);

}

