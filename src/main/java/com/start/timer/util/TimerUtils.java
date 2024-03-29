package com.start.timer.util;

import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import com.start.timer.info.TimerInfo;

public final class TimerUtils {

	private TimerUtils()
	{
		
	}
	
	public static JobDetail buildJobDetail(final Class jobClass,final TimerInfo info)
	{
		final JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put(jobClass.getSimpleName(), info);
		
		return JobBuilder.newJob(jobClass)
				.withIdentity(jobClass.getSimpleName())
				.setJobData(jobDataMap)
				.build();
	}
	
	public static Trigger buildTrigger(final Class jobClass,final TimerInfo info)
	{
		SimpleScheduleBuilder builder = SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(info.getRepearIntervalMs());
		
		if(info.isRunForever())
		{
			builder = builder.repeatForever();
		}else {
			builder = builder.withRepeatCount(info.getTotalFireCount()-1);
		}
		
		return TriggerBuilder
				.newTrigger().withIdentity(jobClass.getSimpleName()).withSchedule(builder)
				.startAt(new Date(System.currentTimeMillis()+info.getInitialOffsetMs()))
				.build();
	}
}
