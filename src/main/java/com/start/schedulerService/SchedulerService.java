package com.start.schedulerService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.start.scheduler.SchedulerWork;
import com.start.timer.info.TimerInfo;
import com.start.timer.util.TimerUtils;

@Service
public class SchedulerService {
	
	private static final Logger LOG = LoggerFactory.getLogger(SchedulerWork .class);
	
	private final Scheduler scheduler;

	@Autowired
	public SchedulerService(Scheduler scheduler) {

		this.scheduler = scheduler;
	}
	
	public void schedule(final Class jobClass,final TimerInfo info)
	{
		final JobDetail jobDetail =TimerUtils.buildJobDetail(jobClass, info);
		final Trigger trigger = TimerUtils.buildTrigger(jobClass, info);
		
		try {
			scheduler.scheduleJob(jobDetail, trigger);
		}catch(SchedulerException e)
		{
			LOG.error(e.getMessage(), e);
		}
	}
	
	@PostConstruct
	public void init()
	{
		try {
			scheduler.start();
		}catch(SchedulerException e)
		{
			LOG.error(e.getMessage(),e);
		}
	}
	
	@PreDestroy
	public void preDestroy()
	{
		try {
			scheduler.shutdown();
		}catch(SchedulerException e)
		{
			LOG.error(e.getMessage(),e);
		}
	}
	
}
