package com.start.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.start.Utility.AuthTokenUtility;
import com.start.entities.Student;
import com.start.exception.CustomErrorType;
import com.start.exception.ResourceNotFoundException;
import com.start.repositories.StudentRepository;
import com.start.timerplayground.PlaygroundService;




@RestController
@RequestMapping("/api1")
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private PlaygroundService p;
	
	
	@GetMapping("/students")
	public  ResponseEntity<List<Student>> getAllStudent(HttpServletRequest request)
	{
		AuthTokenUtility.secretTokenCheck(request);
		 List<Student> student = studentRepository.findAll();
	        if (student.isEmpty()) {
	            return  new ResponseEntity(HttpStatus.NO_CONTENT);
	           
	        }
	        return  new ResponseEntity<List<Student>>(HttpStatus.OK);
	}
	
	
	@GetMapping("/students/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable("id") int id,HttpServletRequest request)
		{
		AuthTokenUtility.secretTokenCheck(request);
		 Optional<Student> student = studentRepository.findById(id);
	        if (student == null) {
	            return new ResponseEntity(new CustomErrorType("User with id " + id 
	                    + " not found"), HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<Student>(HttpStatus.OK);
	}
	
	@PostMapping("/students")
	public ResponseEntity<?> createStudent( @RequestBody Student student,HttpServletRequest request) {
		AuthTokenUtility.secretTokenCheck(request);
	studentRepository.save(student);
     return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	
	
	@PutMapping("/students/{id}")
	public ResponseEntity<?> updateStudent(@PathVariable(value = "id") int id,
			 @RequestBody Student s ,HttpServletRequest request) throws ResourceNotFoundException {
		AuthTokenUtility.secretTokenCheck(request);
	Student student = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + id));
				student.setName(s.getName());
				student.setRollno(s.getRollno());
				student.setAddress(s.getAddress());
	 studentRepository.save(student);
		return new ResponseEntity<Student>(student,HttpStatus.OK);
	}

	@DeleteMapping("/students/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable(value = "id") int id,HttpServletRequest request)
			throws ResourceNotFoundException {
		AuthTokenUtility.secretTokenCheck(request);
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + id));

		studentRepository.delete(student);
		
		return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
	}
	@Autowired
	private Environment env;
	
	@GetMapping("/envdetails")
	public  String envDetails()
	{
		return env.getProperty("server.port");
	}
	
	@PostMapping("/hello")
	public void runHello()
	{
		p.runHelloWorld();
	}
	
}
