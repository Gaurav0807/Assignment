package com.start.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.start.Utility.AuthTokenUtility;
import com.start.entities.Student;
import com.start.entities.Teacher;
import com.start.exception.CustomErrorType;
import com.start.exception.ResourceNotFoundException;
import com.start.repositories.TeacherRepository;

@Controller
@RequestMapping("/api2")
public class TeacherController {
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	
	@GetMapping("/teachers")
	public  ResponseEntity<List<Teacher>> getAllTeacher(HttpServletRequest request)
	{
		AuthTokenUtility.secretTokenCheck(request);
		 List<Teacher> teacher = teacherRepository.findAll();
	        if (teacher.isEmpty()) {
	            return  new ResponseEntity(HttpStatus.NO_CONTENT);
	            // You many decide to return HttpStatus.NOT_FOUND
	        }
	        return  new ResponseEntity<List<Teacher>>(HttpStatus.OK);
	}
	
	
	@GetMapping("/teachers/{id}")
	public ResponseEntity<?> getTeacherById(@PathVariable("id") int id)
		{
		 Optional<Teacher> teacher = teacherRepository.findById(id);
	        if (teacher == null) {
	            return new ResponseEntity(new CustomErrorType("Teacher with id " + id 
	                    + " not found"), HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<Teacher>(HttpStatus.OK);
	}
	
	@PostMapping("/teachers")
	public ResponseEntity<?> createTeacher( @RequestBody Teacher student) {
		teacherRepository.save(student);
     return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	
	
	@PutMapping("/teachers/{id}")
	public ResponseEntity<?> updateTeacher(@PathVariable(value = "id") int id,
			 @RequestBody Teacher t) throws ResourceNotFoundException {
	Teacher teacher= teacherRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Teacher not found for this id :: " + id));
				teacher.setName(t.getName());
				teacher.setSalary(t.getSalary());
				teacher.setAddress(t.getAddress());
				teacherRepository.save(teacher);
		return new ResponseEntity<Teacher>(teacher,HttpStatus.OK);
	}

	@DeleteMapping("/teachers/{id}")
	public ResponseEntity<?> deleteTeacher(@PathVariable(value = "id") int id)
			throws ResourceNotFoundException {
		Teacher teacher = teacherRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Teacher not found for this id :: " + id));

		teacherRepository.delete(teacher);
		
		return new ResponseEntity<Teacher>(HttpStatus.NO_CONTENT);
	}
	

}
