package com.zqkj.bsm.cluster.pdu;

import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 所有的任务队列信息
 * @author 0283
 *
 */
public class JobQueueResList 
{

	private ArrayList<JobQueueReq> list ;//任务队列对象 ArrayList
    private int totalItems; // 任务队列对象的个数

	public ArrayList<JobQueueReq> getList() {
		return list;
	}
	public void setList(ArrayList<JobQueueReq> list) {
		this.list = list;
	}
    public int getTotalItems() {
        return totalItems;
    }

    //将集群响应结果对应到任务队列对象中
	public void jsontoArray(String  item_str)
	{
		list = new ArrayList<JobQueueReq>();
		JSONObject clusterSlaveGroupSource =JSONObject.fromObject(item_str);
		JSONArray items = clusterSlaveGroupSource.getJSONArray("job_queues");
		totalItems = clusterSlaveGroupSource.getInt("total_items");
		for(int i=0;i<items.size();i++)
		{
			JSONObject item = items.getJSONObject(i);
			JobQueueReq req = new JobQueueReq();
			req.jsontoMessage(item);
			list.add(req);
		}
	}
	//将集群响应结果对应到任务队列对象中
	public JSONArray getArray(String  item_str)
	{
		JSONObject clusterSlaveGroupSource =JSONObject.fromObject(item_str);
		JSONArray items = clusterSlaveGroupSource.getJSONArray("job_queues");
		return items;
	}
}
