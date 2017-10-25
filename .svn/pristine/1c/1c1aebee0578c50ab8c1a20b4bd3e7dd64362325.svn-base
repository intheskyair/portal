package com.zqkj.bsm.cluster.pdu;

import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StorageResList {
	private ArrayList<StorageRes> list ;

    private int totalItems;

	public ArrayList<StorageRes> getList() {
		return list;
	}

	public void setList(ArrayList<StorageRes> list) {
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
		list = new ArrayList<StorageRes>();
		JSONObject clusterSlaveGroupSource =JSONObject.fromObject(item_str);
		JSONArray items = clusterSlaveGroupSource.getJSONArray("share_storages");
		totalItems = clusterSlaveGroupSource.getInt("total_items");
		for(int i=0;i<items.size();i++)
		{
			JSONObject item = items.getJSONObject(i);
			StorageRes res = new StorageRes();
			res.jsontoMessage(item);
			list.add(res);
		}
	}
}
