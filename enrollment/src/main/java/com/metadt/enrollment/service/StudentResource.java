package com.metadt.enrollment.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.metadt.enrollment.exception.ResourceNotFoundException;
import com.metadt.enrollment.model.Student;
import com.metadt.enrollment.repository.StudentRepository;

@RestController
public class StudentResource {

	@Autowired
	private StudentRepository studentRepository;

	@GetMapping("/studentsx")
	public List<Student> retrieveAllStudents() {
		return studentRepository.findAll();
	}

	@GetMapping("/studentsx/{id}")
	public Student retrieveStudent(@PathVariable long id) {
		Optional<Student> student = studentRepository.findById(id);

		if (!student.isPresent())
			throw new ResourceNotFoundException("Student id-" + id);

		return student.get();
	}

	@DeleteMapping("/studentsx/{id}")
	public void deleteStudent(@PathVariable Long id) {
		studentRepository.deleteById(id);
	}

	@PostMapping("/studentsx")
	public ResponseEntity<Object> createStudent(@RequestBody Student student) {
		Student savedStudent = studentRepository.save(student);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedStudent.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("/studentsx/{id}")
	public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable Long id) {

		Optional<Student> studentOptional = studentRepository.findById(id);

		if (!studentOptional.isPresent())
			return ResponseEntity.notFound().build();

		student.setId(id);
		
		studentRepository.save(student);

		return ResponseEntity.noContent().build();
	}
}

