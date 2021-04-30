package com.start.scheduler;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.start.repositories.StudentRepository;
import com.start.repositories.TeacherRepository;

import java.util.Date;

import org.slf4j.Logger;

@Component

public class SchedulerWork {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	
  
	@Scheduled(initialDelay= 10000,fixedDelay=80000)
	public void totalStudentTeacher()
	{
		

		System.out.println("Numbers of students :- "+studentRepository.count());
		
		System.out.println("Numbers of Teachers :- "+teacherRepository.count());
	}
}
