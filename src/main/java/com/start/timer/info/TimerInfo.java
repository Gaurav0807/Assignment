package com.start.timer.info;

public class TimerInfo {

	private int totalFireCount;
	private boolean runForever;
	private long repearIntervalMs;
	private long initialOffsetMs;
	private String callbackData;  //pass information when complex data 
	public int getTotalFireCount() {
		return totalFireCount;
	}
	public void setTotalFireCount(int totalFireCount) {
		this.totalFireCount = totalFireCount;
	}
	public boolean isRunForever() {
		return runForever;
	}
	public void setRunForever(boolean runForever) {
		this.runForever = runForever;
	}
	public long getRepearIntervalMs() {
		return repearIntervalMs;
	}
	public void setRepearIntervalMs(long repearIntervalMs) {
		this.repearIntervalMs = repearIntervalMs;
	}
	public long getInitialOffsetMs() {
		return initialOffsetMs;
	}
	public void setInitialOffsetMs(long initialOffsetMs) {
		this.initialOffsetMs = initialOffsetMs;
	}
	public String getCallbackData() {
		return callbackData;
	}
	public void setCallbackData(String callbackData) {
		this.callbackData = callbackData;
	}
	
	
}
