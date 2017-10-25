package com.zqkj.bsm.util;

import java.util.TimerTask;

public class RemoveLoginTimes extends TimerTask {
	private String ip;
	private String name;
	private int times;
	
	public RemoveLoginTimes(String ip,String name,int times) {
		this.ip = ip;
		this.name = name;
		this.times = times;
	}
	
	@Override
	public void run() {
		BHUtil.reMoveLockUser(ip, name, times);
	}
	
}
