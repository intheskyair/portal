package com.zqkj.bsm.cluster.pdu;

import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 所有的节点组信息
 * @author 0283
 *
 */
public class AgentsTeamsResList {

	private ArrayList<AgentsTeamsReq> list ;//节点组对象 ArrayList
    private int totalItems; // 节点组对象的个数

	public ArrayList<AgentsTeamsReq> getList() {
		return list;
	}
	public void setList(ArrayList<AgentsTeamsReq> list) {
		this.list = list;
	}
    public int getTotalItems() {
        return totalItems;
    }

    //将集群响应结果对应到任务队列对象中
	public void jsontoArray(String  item_str)
	{
		list = new ArrayList<AgentsTeamsReq>();
		JSONObject clusterSlaveGroupSource =JSONObject.fromObject(item_str);
		JSONArray items = clusterSlaveGroupSource.getJSONArray("agent_teams");
		totalItems = clusterSlaveGroupSource.getInt("total_items");
		for(int i=0;i<items.size();i++)
		{
			JSONObject item = items.getJSONObject(i);
			AgentsTeamsReq req = new AgentsTeamsReq();
			req.jsontoMessage(item);
			list.add(req);
		}
	}
	//将集群响应结果对应到任务队列对象中
	public JSONArray getArray(String  item_str)
	{
		JSONObject clusterSlaveGroupSource =JSONObject.fromObject(item_str);
		JSONArray items = clusterSlaveGroupSource.getJSONArray("agent_teams");
		return items;
	}
}
