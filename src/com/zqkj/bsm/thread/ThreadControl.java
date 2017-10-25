package com.zqkj.bsm.thread;

public class ThreadControl {
	public static boolean running = true;
	
	public static void stop(){
		running = false;
	}

	public static void restart(){
		running = true;
	}
}
