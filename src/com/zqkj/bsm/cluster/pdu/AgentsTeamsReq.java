package com.zqkj.bsm.cluster.pdu;
import java.util.Map;
import net.sf.json.JSONObject;
/**
 * 节点组的JavaBean.
 * @author 0283
 */
public class AgentsTeamsReq {
	private long id;//节点组ID
	private String name; //节点组名称
	private String description; //节点组描述
	private String create_time;//节点组创建时间
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	 public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
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
		if(item.containsKey("description"))
		{
			this.description = item.getString("description");
		}
		if(item.containsKey("create_time"))
		{
			this.create_time = item.getString("create_time");
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
		return jsonEntity;
	}

	
}
