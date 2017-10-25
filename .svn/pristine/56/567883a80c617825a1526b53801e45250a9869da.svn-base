package com.zqkj.bsm.cluster.pdu;

import net.sf.json.JSONObject;

public class BFrameReq 
{
	//0 多帧渲染 1：效果图
	private int mul_flag;
	//任务名
	private String name;	
	//补帧序列
	private String frames;
	//补帧总帧数
	private int total_frame;
	//原订单号
	private String trans_id;

	public int getMul_flag() {
		return mul_flag;
	}
	public void setMul_flag(int mul_flag) {
		this.mul_flag = mul_flag;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFrames() {
		return frames;
	}
	public void setFrames(String frames) {
		this.frames = frames;
	}
	public int getTotal_frame() {
		return total_frame;
	}
	public void setTotal_frame(int total_frame) {
		this.total_frame = total_frame;
	}
	public String getTrans_id() {
		return trans_id;
	}
	public void setTrans_id(String trans_id) {
		this.trans_id = trans_id;
	}
	
	
	public void jsontoMessage(String ison_str)
	{
		JSONObject item =JSONObject.fromObject(ison_str);
		//JSONObject item = clusterSlaveGroupSource.getJSONObject("job");			
		if(item.containsKey("mul_flag"))
		{
		  this.mul_flag = item.getInt("mul_flag");
		}
		if(item.containsKey("name"))
		{
		  this.name = item.getString("name");
		}
		if(item.containsKey("frames"))
		{
		  this.frames = item.getString("frames");
		}
		if(item.containsKey("trans_id"))
		{
		  this.trans_id = item.getString("trans_id");
		}
		if(item.containsKey("total_frame"))
		{
		  this.total_frame = item.getInt("total_frame");
		}
	}

}
