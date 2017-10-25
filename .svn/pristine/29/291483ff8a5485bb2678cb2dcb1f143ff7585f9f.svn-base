package com.zqkj.bsm.cluster.pdu;

import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 获取所有的节点信息
 * @author 0242
 *
 */
public class AgentsResList 
{

	private ArrayList<AgentReq> list ;

    private int totalItems;

	public ArrayList<AgentReq> getList() {
		return list;
	}

	public void setList(ArrayList<AgentReq> list) {
		this.list = list;
	}

    /**
     * 获取总条数
     * 
     * @return
     */
    public int getTotalItems() {
        return totalItems;
    }

	public void jsontoArray(String  item_str)
	{
		list = new ArrayList<AgentReq>();
		JSONObject clusterSlaveGroupSource =JSONObject.fromObject(item_str);
		JSONArray items = clusterSlaveGroupSource.getJSONArray("agents");
		totalItems = clusterSlaveGroupSource.getInt("total_items");
		for(int i=0;i<items.size();i++)
		{
			JSONObject item = items.getJSONObject(i);
			AgentReq req = new AgentReq();
			req.jsontoMessage(item);
			list.add(req);
		}
	}
	//将集群响应结果对应到任务队列对象中
		public JSONArray getArray(String  item_str)
		{
			JSONObject clusterSlaveGroupSource =JSONObject.fromObject(item_str);
			JSONArray items = clusterSlaveGroupSource.getJSONArray("agents");
			return items;
		}
}
