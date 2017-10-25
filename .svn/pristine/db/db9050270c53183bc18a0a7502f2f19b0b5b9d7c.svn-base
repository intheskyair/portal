package com.zqkj.bsm.cluster.pdu;
import net.sf.json.JSONObject;
/**
 * 节点组关联任务队列的JavaBean.
 * @author 0283
 */
public class QueueTeamMapReq {
	private long queue_id;//任务队列ID
	private long  team_id; //节点组Id

	public long getQueue_id() {
		return queue_id;
	}

	public void setQueue_id(long queue_id) {
		this.queue_id = queue_id;
	}

	public long getTeam_id() {
		return team_id;
	}

	public void setTeam_id(long team_id) {
		this.team_id = team_id;
	}

	/**
     * 将集群接口传过来的JSONObjec对象装化为任务队列的JavaBean.
     * 
     * @param item
     */
	public void jsontoMessage(JSONObject item)
	{
		//JSONObject item =JSONObject.fromObject(ison_str);	
		if(item.containsKey("queue_id"))
		{
		  this.queue_id = item.getLong("queue_id");
		}
		else
		{
			this.queue_id = 0;
		}
		if(item.containsKey("team_id"))
		{
			this.team_id = item.getLong("team_id");
		}
		else
		{
			this.team_id = 0;
		}
	}
	
}
