package com.zqkj.bsm.thread;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.zqkj.bsm.util.ConfigUtil;

public class scheduleTimer 
{
	private Logger logger = Logger.getLogger(this.getClass());
	// 每隔1小时监测存储节点是否正常
	private java.util.Timer db_timer = null;
	
	private java.util.Timer db_timer_node = null;

	public void cancel() 
	{
		db_timer.cancel();
	}

	public scheduleTimer() 
	{
		db_timer = new java.util.Timer(true);
		logger.info("启动检测存储节点定时器！！");
		int timeschdule = Integer.valueOf(ConfigUtil.getValue("timeschdule")).intValue();
		ConfigUtil.getValue("password");
		db_timer.schedule(new DbTask(),
				30 * 1 * 1000, timeschdule*60 * 1000);
		
		db_timer_node = new java.util.Timer(true);
		logger.info("启动集群节点温度告警定时器！！");
		db_timer_node.schedule(new NodeTask(),
				30 * 1 * 1000, 5*60*1000);
	}

	/**
	 * 该定时任务检测存储节点是否正常挂载状态
	 * 
	 * @author gaofeng
	 * 
	 */
	class DbTask extends TimerTask 
	{
		private boolean isRunning = false;
		public void run() 
		{
			try
			{
				if (!isRunning) 
				{
					isRunning = true;
					//顺带监测下存储个各种异常
					isRunning = false;
				}
				else 
				{
					 logger.warn("存储节点监控：上一次任务执行还未结束");
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				isRunning = false;
			}
		}
	}
	
	
	
	
	
	class NodeTask extends TimerTask 
	{
		private boolean isRunning = false;
		public void run() 
		{
			try
			{
				if (!isRunning) 
				{
					isRunning = true;
					//顺带监测下存储个各种异常
//					if(SysPara.exceptionMap.get("warning")==null)
//					{
//						SysPara.exceptionMap.put("warning", true);
//						SysPara.exceptionMap.put("warning_time", System.currentTimeMillis());
//					}
//					else
//					{
//						    boolean rt_bz = this.checkNode();	
//							SysPara.exceptionMap.put("warning", rt_bz);
//							SysPara.exceptionMap.put("warning_time", System.currentTimeMillis());						
//					}	
					isRunning = false;
				}
				else 
				{
					 logger.warn("集群节点监控：上一次任务执行还未结束");
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				isRunning = false;
			}
		}
		
	}
}
