package com.zqkj.bsm.cluster.pdu;

import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ManageSoftwareResList {


	private ArrayList<ManageSoftwareReq> list ;//软件管理对象 ArrayList
    private int totalItems; // 软件管理对象的个数

	public ArrayList<ManageSoftwareReq> getList() {
		return list;
	}
	public void setList(ArrayList<ManageSoftwareReq> list) {
		this.list = list;
	}
    public int getTotalItems() {
        return totalItems;
    }

    //将集群响应结果对应到软件管理对象中
	public void jsontoArray(String  item_str)
	{
		list = new ArrayList<ManageSoftwareReq>();
		JSONObject clusterSlaveGroupSource =JSONObject.fromObject(item_str);
		JSONArray items = clusterSlaveGroupSource.getJSONArray("softwares");
		totalItems = clusterSlaveGroupSource.getInt("total_items");
		for(int i=0;i<items.size();i++)
		{
			JSONObject item = items.getJSONObject(i);
			ManageSoftwareReq req = new ManageSoftwareReq();
			req.jsontoMessage(item);
			list.add(req);
		}
	}
	//将集群响应结果对应到任务队列对象中
	public JSONArray getArray(String  item_str)
	{
		JSONObject clusterSlaveGroupSource =JSONObject.fromObject(item_str);
		JSONArray items = clusterSlaveGroupSource.getJSONArray("softwares");
		return items;
	}

}
