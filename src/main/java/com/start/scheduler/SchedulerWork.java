package com.start.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.start.repositories.StudentRepository;
import com.start.repositories.TeacherRepository;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class SchedulerWork  implements Job{
	
	private static final Logger LOG = LoggerFactory.getLogger(SchedulerWork.class);

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
//		System.out.println("Numbers of students :- "+studentRepository.count());
//		int a = (int) studentRepository.count();
//		LOG.info("Testing");
		System.out.println("Numbers of Teachers :- "+teacherRepository.count());
		System.out.println("Numbers of Teachers :- "+teacherRepository.count());
	}
	
	
  
//	@Scheduled(initialDelay= 10000,fixedDelay=80000)
//	public void totalStudentTeacher()
//	{
//		
//
//		System.out.println("Numbers of students :- "+studentRepository.count());
//		
//		System.out.println("Numbers of Teachers :- "+teacherRepository.count());
//	}
}
