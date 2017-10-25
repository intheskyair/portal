package com.zqkj.bsm.cluster.pdu;

import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class LogResList {
	private ArrayList<LogRes> list ;

    private int totalItems;

	public ArrayList<LogRes> getList() {
		return list;
	}

	public void setList(ArrayList<LogRes> list) {
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
		list = new ArrayList<LogRes>();
		JSONObject clusterSlaveGroupSource =JSONObject.fromObject(item_str);
		JSONArray items = clusterSlaveGroupSource.getJSONArray("agent_logs");
		totalItems = clusterSlaveGroupSource.getInt("total_items");
		for(int i=0;i<items.size();i++)
		{
			JSONObject item = items.getJSONObject(i);
			LogRes res = new LogRes();
			res.jsontoMessage(item);
			list.add(res);
		}
	}
}
