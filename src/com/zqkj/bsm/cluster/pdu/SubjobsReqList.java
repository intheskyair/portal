package com.zqkj.bsm.cluster.pdu;

import java.util.ArrayList;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 获取所有子任务的数据.
 * 
 * @author 0188
 *
 */
public class SubjobsReqList {

    private ArrayList<SubjobsReq> list ; // 子任务对象的队列

    private int totalItems;

    /**
     * 获取子任务对象的队列
     * 
     * @return
     */
    public ArrayList<SubjobsReq> getList() {
        return list;
    }

    /**
     * 获取总条数
     * 
     * @return
     */
    public int getTotalItems() {
        return totalItems;
    }


    /**
     * 将集群传过来的结果转化为子任务对象的队列.
     * 
     * @param item_str
     */
    public void jsontoArray(String  item_str,Map<String, Object> map)
    {
        list = new ArrayList<SubjobsReq>();
        JSONObject clusterSlaveGroupSource =JSONObject.fromObject(item_str);
        JSONArray items = clusterSlaveGroupSource.getJSONArray("subjobs");
        totalItems = clusterSlaveGroupSource.getInt("total_items");
        for(int i=0;i<items.size();i++)
        {
            JSONObject item = items.getJSONObject(i);
            SubjobsReq req = new SubjobsReq();
            req.jsontoMessage(item,map);
            list.add(req);
        }
    }
}
