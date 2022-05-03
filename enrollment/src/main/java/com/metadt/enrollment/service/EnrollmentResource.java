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
import com.metadt.enrollment.model.Enrollment;
import com.metadt.enrollment.repository.EnrollmentRepository;

@RestController
public class EnrollmentResource {

	@Autowired
	private EnrollmentRepository enrollmentRepository;

	@GetMapping("/enrollmentsx")
	public List<Enrollment> retrieveAllEnrollments() {
		return enrollmentRepository.findAll();
	}

	@GetMapping("/enrollmentsx/{id}")
	public Enrollment retrieveEnrollment(@PathVariable long id) {
		Optional<Enrollment> enrollment = enrollmentRepository.findById(id);

		if (!enrollment.isPresent())
			throw new ResourceNotFoundException("Enrollment id-" + id);

		return enrollment.get();
	}

	@DeleteMapping("/enrollmentsx/{id}")
	public void deleteEnrollment(@PathVariable Long id) {
		enrollmentRepository.deleteById(id);
	}

	@PostMapping("/enrollmentsx")
	public ResponseEntity<Object> createEnrollment(@RequestBody Enrollment enrollment) {
		Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedEnrollment.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("/enrollmentsx/{id}")
	public ResponseEntity<Object> updateEnrollment(@RequestBody Enrollment enrollment, @PathVariable Long id) {

		Optional<Enrollment> enrollmentOptional = enrollmentRepository.findById(id);

		if (!enrollmentOptional.isPresent())
			return ResponseEntity.notFound().build();

		enrollment.setId(id);
		
		enrollmentRepository.save(enrollment);

		return ResponseEntity.noContent().build();
	}
}

