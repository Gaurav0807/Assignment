package com.start.timerplayground;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.start.scheduler.SchedulerWork;
import com.start.schedulerService.SchedulerService;
import com.start.timer.info.TimerInfo;

@Service
public class PlaygroundService {
	
	private final SchedulerService s;

	@Autowired
	public PlaygroundService(SchedulerService s) {
			this.s = s;
	}
	
	
	public void runHelloWorld()
	{
		final TimerInfo info = new TimerInfo();
		info.setTotalFireCount(5);
		info.setRepearIntervalMs(2000);
		info.setInitialOffsetMs(1000);
		info.setCallbackData("My call back data");
		s.schedule(SchedulerWork.class, info);
	}
	
}
