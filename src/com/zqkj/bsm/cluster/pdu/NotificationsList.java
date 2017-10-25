package com.zqkj.bsm.cluster.pdu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 集群通知消息接口
 * @author 0242
 *
 */

public class NotificationsList 
{
	private ArrayList<Map<String, Object>> list ;
	
	private ArrayList<Map<String, Object>> list_agent ;
	private Logger log = Logger.getLogger(NotificationsList.class);
	public ArrayList<Map<String, Object>> getList() 
	{
		return list;
	}

	public void setList(ArrayList<Map<String, Object>> list) 
	{
		this.list = list;
	}
	
	private static volatile NotificationsList instance = null;
	
	private NotificationsList()
	{
		
	}
	public static NotificationsList getInstance()
	{
		if(instance==null)
		{
			synchronized (NotificationsList.class)
			{
			  instance = new NotificationsList();
			}
		}
		return instance;
	}
	
	
	/**
	 * 集群返回通知消息解码
	 * @param item_str
	 * @return
	 */
	public long jsontoArray(String  item_str,long last_seq_begin)
	{
		long last_seq = 0;
		list = new ArrayList<Map<String, Object>>();
		list_agent = new ArrayList<Map<String, Object>>();
		JSONObject clusterSlaveGroupSource =JSONObject.fromObject(item_str);
		JSONArray items = clusterSlaveGroupSource.getJSONArray("notifications");
		for(int i=0,len=items.size();i<len;i++)
		{
			  JSONObject item = items.getJSONObject(i);

			   Map<String, Object> map = new HashMap<String, Object>();
			   int type = item.getInt("type");
			   if(type ==0)
			   {
				  if(!item.containsKey("job_id"))
				  {				  
					  log.info("*************集群返回消息*************type："+type+"：json="+item_str);
					  last_seq= item.getInt("seq");
					  break;
				  }
				   map.put("seq", item.getInt("seq"));
				   map.put("job_id", item.getString("job_id"));
				   map.put("status", item.getInt("status"));
				   map.put("status_text", item.getString("status_text"));
				   map.put("total_frame", item.getInt("total_frame"));
				   map.put("success_frame", item.getInt("success_frame"));
				   map.put("failure_frame", item.getInt("failure_frame"));
				   //edit by gaofeng 取消帧的数量
				   if(item.containsKey("cancel_frame"))
			       {				   
				       map.put("cancel_frame", item.getInt("cancel_frame"));
				   }
				   else
				   {
					   map.put("cancel_frame", 0);
				   }
				   //正渲染
				   if(item.containsKey("rendering_frame"))
			       {				   
				       map.put("rendering_frame", item.getInt("rendering_frame"));
				   }
				   else
				   {
					   map.put("rendering_frame", 0);
				   }
				   //未处理
				   if(item.containsKey("untreated_frame"))
			       {				   
				       map.put("untreated_frame", item.getInt("untreated_frame"));
				   }
				   else
				   {
					   map.put("untreated_frame", 0);
				   }
				   //子任务id
				   if(item.containsKey("subjob_id"))
			       {				   
				       map.put("subjob_id", item.getLong("subjob_id"));
				   }
				   else
				   {
					   map.put("subjob_id", 0);
				   }
				   //子任务完成状态 3：成功
				   if(item.containsKey("sj_status"))
			       {				   
				       map.put("sj_status", item.getInt("sj_status"));
				   }
				   else
				   {
					   map.put("sj_status", 0);
				   }
				   //状态描述
				   if(item.containsKey("sj_status_text"))
			       {				   
				       map.put("sj_status_text", item.getString("sj_status_text"));
				   }
				   //完成的帧
				   if(item.containsKey("finish_sjframe"))
			       {				   
				       map.put("finish_sjframe", item.getInt("finish_sjframe"));
				   }
				   map.put("user_id", item.getInt("user_id"));
					if(item.containsKey("core_seconds"))
					{
				      map.put("core_seconds", item.getInt("core_seconds"));
					}
					if(item.containsKey("cur_concurrency"))
					{
				      map.put("cur_concurrency", item.getInt("cur_concurrency"));
					}
					if(item.containsKey("render_flag"))
					{
				      map.put("render_flag", item.getInt("render_flag"));
					}
					//add by gaofeng 优先渲染通知部门
					// '渲染模式(0:优先帧完成后需要通知客户/1:全速模式)',
					if(item.containsKey("render_strategy"))
					{
				       map.put("render_strategy", item.getInt("render_strategy"));
					}
					else
					{
						map.put("render_strategy", -1);
					}
					if(item.containsKey("keyframe_finish"))
					{
				      map.put("keyframe_finish", item.getBoolean("keyframe_finish"));
					}	
					else
					{
						map.put("keyframe_finish", false);
					}
					if(item.containsKey("total_keyframe"))
					{
				      map.put("total_keyframe", item.getInt("total_keyframe"));
					}	
					if(item.containsKey("success_keyframe"))
					{
				      map.put("success_keyframe", item.getInt("success_keyframe"));
					}	
					if(item.containsKey("failure_keyframe"))
					{
				      map.put("failure_keyframe", item.getInt("failure_keyframe"));
					}	
					if(item.containsKey("rendering_keyframe"))
					{
				      map.put("rendering_keyframe", item.getInt("rendering_keyframe"));
					}	
					if(item.containsKey("untreated_keyframe"))
					{
				      map.put("untreated_keyframe", item.getInt("untreated_keyframe"));
					}	
					if(item.containsKey("cancel_keyframe"))
					{
				      map.put("cancel_keyframe", item.getInt("cancel_keyframe"));
					}
				   list.add(map);
			   }
			   else
			   {
					map.put("seq", item.getInt("seq"));
					map.put("agent_id", item.getInt("agent_id"));
					map.put("agent_ip", item.getString("agent_ip"));
					map.put("create_time", item.getString("create_time"));					
					if(item.containsKey("th_cpu_temperature"))
					{
					   map.put("th_cpu_temperature", item.getInt("th_cpu_temperature"));
					   map.put("cpu_temperature", item.getInt("cpu_temperature"));
					   map.put("op_cpu_temperature", item.getInt("op_cpu_temperature"));
					}
					else
					{
					   map.put("th_cpu_temperature", 0);
					   map.put("cpu_temperature", 0);
					   map.put("op_cpu_temperature", 0);
					}
					if(item.containsKey("mem_usage"))
					{
					   map.put("mem_usage", item.getInt("mem_usage"));
					   map.put("op_mem_usage", item.getInt("op_mem_usage"));
					   map.put("th_mem_usage", item.getInt("th_mem_usage"));
					}
					else
					{
					   map.put("mem_usage", 0);
					   map.put("op_mem_usage", 0);
					   map.put("th_mem_usage", 0);
					}
					if(item.containsKey("mem_difference"))
					{
					   map.put("mem_difference", item.getInt("mem_difference"));
					   map.put("op_mem_difference", item.getInt("op_mem_difference"));
					   map.put("th_mem_difference", item.getInt("th_mem_difference"));
					}
					else
					{
					   map.put("mem_difference", 0);
					   map.put("op_mem_difference", 0);
					   map.put("th_mem_difference", 0);
					}
					if(item.containsKey("cpufan_rotate_speed"))
					{
					   map.put("cpufan_rotate_speed", item.getInt("cpufan_rotate_speed"));
					   map.put("op_cpufan_rotate_speed", item.getInt("op_cpufan_rotate_speed"));
					   map.put("th_cpufan_rotate_speed", item.getInt("th_cpufan_rotate_speed"));
					}
					else
					{
					   map.put("cpufan_rotate_speed", 0);
					   map.put("op_cpufan_rotate_speed", 0);
					   map.put("th_cpufan_rotate_speed", 0);
					}
				   list_agent.add(map);
			   }
				if(i == (items.size()-1))
				{
					last_seq = item.getInt("seq");
				}
			}			
		return last_seq;
	}
	
	
	
			public  Map<String, Object> jsontoMap(String  item_str)
			{
		       JSONObject item =JSONObject.fromObject(item_str);
			   Map<String, Object> map = new HashMap<String, Object>();
			   int type = item.getInt("type");
			   String job_id;
			   String trans_id;
			   if(type ==0)
			   {
				  if(!item.containsKey("job_id"))
				  {				  
					  job_id = "0";
				  }
				  else
				  {
					  job_id = item.getString("job_id");
				  }
				  if(!item.containsKey("orderNum"))
				  {				  
					  trans_id = "0";
				  }
				  else
				  {
					  trans_id = item.getString("orderNum");
				  }
				   map.put("seq", item.getInt("seq"));
				   map.put("trans_id", trans_id);
				   map.put("job_id", job_id);
				   map.put("status", item.getInt("status"));
				   map.put("status_text", item.getString("status_text"));
				   map.put("total_frame", item.getInt("total_frame"));
				   map.put("success_frame", item.getInt("success_frame"));
				   map.put("failure_frame", item.getInt("failure_frame"));
				   //edit by gaofeng 取消帧的数量
				   if(item.containsKey("cancel_frame"))
			       {				   
				       map.put("cancel_frame", item.getInt("cancel_frame"));
				   }
				   else
				   {
					   map.put("cancel_frame", 0);
				   }
				   //正渲染
				   if(item.containsKey("rendering_frame"))
			       {				   
				       map.put("rendering_frame", item.getInt("rendering_frame"));
				   }
				   else
				   {
					   map.put("rendering_frame", 0);
				   }
				   //未处理
				   if(item.containsKey("untreated_frame"))
			       {				   
				       map.put("untreated_frame", item.getInt("untreated_frame"));
				   }
				   else
				   {
					   map.put("untreated_frame", 0);
				   }
				   //子任务id
				   if(item.containsKey("subjob_id"))
			       {				   
				       map.put("subjob_id", item.getLong("subjob_id"));
				   }
				   else
				   {
					   map.put("subjob_id", 0);
				   }
				   //子任务完成状态 3：成功
				   if(item.containsKey("sj_status"))
			       {				   
				       map.put("sj_status", item.getInt("sj_status"));
				   }
				   else
				   {
					   map.put("sj_status", 0);
				   }
				   //状态描述
				   if(item.containsKey("sj_status_text"))
			       {				   
				       map.put("sj_status_text", item.getString("sj_status_text"));
				   }
				   //完成的帧
				   if(item.containsKey("finish_sjframe"))
			       {				   
				       map.put("finish_sjframe", item.getInt("finish_sjframe"));
				   }
				   map.put("user_id", item.getInt("user_id"));
					if(item.containsKey("core_seconds"))
					{
				      map.put("core_seconds", item.getInt("core_seconds"));
					}
					if(item.containsKey("cur_concurrency"))
					{
				      map.put("cur_concurrency", item.getInt("cur_concurrency"));
					}
					if(item.containsKey("render_flag"))
					{
				      map.put("render_flag", item.getInt("render_flag"));
					}
					//add by gaofeng 优先渲染通知部门
					// '渲染模式(0:优先帧完成后需要通知客户/1:全速模式)',
					if(item.containsKey("render_strategy"))
					{
				       map.put("render_strategy", item.getInt("render_strategy"));
					}
					else
					{
						map.put("render_strategy", -1);
					}
					if(item.containsKey("keyframe_finish"))
					{
				      map.put("keyframe_finish", item.getBoolean("keyframe_finish"));
					}	
					else
					{
						map.put("keyframe_finish", false);
					}
					if(item.containsKey("total_keyframe"))
					{
				      map.put("total_keyframe", item.getInt("total_keyframe"));
					}	
					if(item.containsKey("success_keyframe"))
					{
				      map.put("success_keyframe", item.getInt("success_keyframe"));
					}	
					if(item.containsKey("failure_keyframe"))
					{
				      map.put("failure_keyframe", item.getInt("failure_keyframe"));
					}	
					if(item.containsKey("rendering_keyframe"))
					{
				      map.put("rendering_keyframe", item.getInt("rendering_keyframe"));
					}	
					if(item.containsKey("untreated_keyframe"))
					{
				      map.put("untreated_keyframe", item.getInt("untreated_keyframe"));
					}	
					if(item.containsKey("cancel_keyframe"))
					{
				      map.put("cancel_keyframe", item.getInt("cancel_keyframe"));
					}			
		         
			   }
			   else
			   {
				   map = null;
			   }
			   return map;
	}
	
	
	

	public ArrayList<Map<String, Object>> getList_agent() {
		return list_agent;
	}

	public void setList_agent(ArrayList<Map<String, Object>> list_agent) {
		this.list_agent = list_agent;
	}
	
}
