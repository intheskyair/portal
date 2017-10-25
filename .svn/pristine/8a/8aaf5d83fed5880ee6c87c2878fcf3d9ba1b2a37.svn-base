package com.zqkj.bsm.cluster.pdu;
import java.util.Map;
import net.sf.json.JSONObject;
/**
 * 任务队列的JavaBean.
 * @author 0242
 *
 */
public class JobQueueReq 
{
	private long id;//任务队列ID
	private String name; //任务队列名
	private boolean disable;//是否禁用
	private String description; //描述
	private long user_id ;//用户ID
	private String trans_id;  //项目单号
	private int priority;     //优先级
	private int agent_count;  //最大节点数
	private int custom_flag;  //渲染速度
	public int getCustom_flag() {
		return custom_flag;
	}
	public void setCustom_flag(int custom_flag) {
		this.custom_flag = custom_flag;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getDisable() {
		return disable;
	}
	public void setDisable(boolean disable) {
		this.disable = disable;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getTrans_id() {
		return trans_id;
	}
	public void setTrans_id(String trans_id) {
		this.trans_id = trans_id;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getAgent_count() {
		return agent_count;
	}
	public void setAgent_count(int agent_count) {
		this.agent_count = agent_count;
	}

	 /**
     * 将集群接口传过来的JSONObjec对象装化为任务队列的JavaBean.
     * 
     * @param item
     */
	public void jsontoMessage(JSONObject item)
	{
		//JSONObject item =JSONObject.fromObject(ison_str);	
		if(item.containsKey("id"))
		{
		  this.id = item.getLong("id");
		}
		else
		{
			this.id = 0;
		}
		if(item.containsKey("name"))
		{
		  this.name = item.getString("name");
		}
		if(item.containsKey("disable"))
		{
		  this.disable = item.getBoolean("disable");
		}
		if(item.containsKey("description"))
		{
		  this.description = item.getString("description");
		}
		if(item.containsKey("user_id"))
		{
		  this.user_id = item.getLong("user_id");
		}
		if(item.containsKey("trans_id"))
		{
		  this.trans_id = item.getString("trans_id");
		}
		if(item.containsKey("priority"))
		{
		  this.priority = item.getInt("priority");
		}
		if(item.containsKey("agent_count"))
		{
		  this.agent_count = item.getInt("agent_count");
		}
		if(item.containsKey("custom_flag"))
		{
			this.custom_flag = item.getInt("custom_flag");
		}
	}
	
	
	public JSONObject MessageTojson(Map<String, Object> map)
	{
		JSONObject jsonEntity = new JSONObject();
		    if(map.get("NAME")!=null)
		    {
		    	jsonEntity.put("name", map.get("NAME"));
		    }
		    else
		    {
		    	jsonEntity.put("name", "Queue-"+map.get("trans_id").toString().substring(7));
		    }
		    if(map.get("disable")!=null)
		    {
			    jsonEntity.put("disable", map.get("disable"));
		    }
		    else
		    {
		    	 jsonEntity.put("disable", false);
		    }
		    if(map.get("description")!=null)
		    {
			    jsonEntity.put("description", map.get("description"));
		    }
		    else
		    {
		    	jsonEntity.put("description", "This is Queue-"+map.get("trans_id").toString().substring(7));
		    }
		    if(map.get("user_id")!=null)
		    {
			    jsonEntity.put("user_id", map.get("user_id"));
		    }
		    if(map.get("trans_id")!=null)
		    {
			    jsonEntity.put("trans_id", map.get("trans_id"));
		    }
		    if(map.get("priority")!=null)
		    {
			    jsonEntity.put("priority", map.get("priority"));
		    }
		    if(map.get("agent_count")!=null)
		    {
			    jsonEntity.put("agent_count", map.get("agent_count"));
		    }
		    if(map.get("custom_flag")!=null)
		    {
		    	jsonEntity.put("custom_flag", map.get("custom_flag"));
		    }
		return jsonEntity;
	}

	
}
