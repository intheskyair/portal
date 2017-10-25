package com.zqkj.bsm.cluster.pdu;

import java.util.ArrayList;
import net.sf.json.JSONObject;

public class AgentsInfoResList {
	private ArrayList<AgentsInfoReq> list ;

	public ArrayList<AgentsInfoReq> getList() {
		return list;
	}
	public void setList(ArrayList<AgentsInfoReq> list) {
		this.list = list;
	}

	public JSONObject getArray(String  item_str)
	{
		JSONObject clusterSlaveGroupSource =JSONObject.fromObject(item_str);
		return clusterSlaveGroupSource;
	}

}
