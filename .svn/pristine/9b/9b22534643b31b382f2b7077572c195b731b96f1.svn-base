package com.zqkj.bsm.cluster.pdu;

import net.sf.json.JSONObject;

public class MissionReq 
{
    //当前页数
	private int page=1;
	//每页大小
	private int pageSize=20;
	//订单号
	private String trans_id;
	//开始时间
	private String start_time;
	//结束时间
	private String end_time;
	//任务名称
	private String name;
	
	private int status;
	// 运维订单状态 0:草稿状态 1：上传完成 2:预扣费成功，渲染中 3：渲染完成 4：计费成功 8：余额不足 9:任务被取消 10:渲染异常
	private  String  yw_status;
	//排序字段
	private String column;
	//升序还是倒叙
	private String desc;
	
	private String project_seq_id;
	
	private long operator;
		
	public long getOperator() {
		return operator;
	}
	public void setOperator(long operator) {
		this.operator = operator;
	}
	private long user_id;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getTrans_id() {
		return trans_id;
	}
	public void setTrans_id(String trans_id) {
		this.trans_id = trans_id;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public void jsontoMessage(String ison_str)
	{
		JSONObject item =JSONObject.fromObject(ison_str);
		//JSONObject item = clusterSlaveGroupSource.getJSONObject("job");	
		if(item.containsKey("page"))
		{
		  this.page = item.getInt("page");
		}
		if(item.containsKey("pageSize"))
		{
		  this.pageSize = item.getInt("pageSize");
		}
		if(item.containsKey("trans_id"))
		{
		  this.trans_id = item.getString("trans_id");
		}
		if(item.containsKey("start_time"))
		{
		  this.start_time = item.getString("start_time");
		}
		if(item.containsKey("end_time"))
		{
		  this.end_time = item.getString("end_time");
		}
		if(item.containsKey("name"))
		{
		  this.name = item.getString("name");
		}
		if(item.containsKey("status"))
		{
		  this.status = item.getInt("status");
		}else{
			this.status = -1;
		}
		if(item.containsKey("userId"))
		{
		  this.user_id = item.getLong("userId");
		}
		if(item.containsKey("column"))
		{
		  this.column = item.getString("column");
		}
		if(item.containsKey("desc"))
		{
		  this.desc = item.getString("desc");
		}
		if(item.containsKey("yw_status"))
		{
		  this.yw_status = item.getString("yw_status");
		}
		if(item.containsKey("project_seq_id"))
		{
		  this.project_seq_id = item.getString("project_seq_id");
		}
		if(item.containsKey("operator"))
		{
		  this.operator = item.getLong("operator");
		}
		
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getYw_status() {
		return yw_status;
	}
	public void setYw_status(String yw_status) {
		this.yw_status = yw_status;
	}
	public String getProject_seq_id() {
		return project_seq_id;
	}
	public void setProject_seq_id(String project_seq_id) {
		this.project_seq_id = project_seq_id;
	}
	
}
