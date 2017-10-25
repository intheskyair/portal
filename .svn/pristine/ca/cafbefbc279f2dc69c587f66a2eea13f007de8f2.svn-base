package com.zqkj.bsm.cluster.pdu;

import net.sf.json.JSONObject;

public class NoticeReq 
{

	
    //当前页数
	private int page=1;
	//每页大小
	private int pageSize=20;
	//1待发布，2已发布，3已过期
	private int status;
	//开始日期
	private String preDate;
	//结束日期
	private String postDate;
	
	private long userId;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPreDate() {
		return preDate;
	}
	public void setPreDate(String preDate) {
		this.preDate = preDate;
	}
	public String getPostDate() {
		return postDate;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
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
		if(item.containsKey("status"))
		{
		  this.status = item.getInt("status");
		}
		if(item.containsKey("preDate"))
		{
		  this.preDate = item.getString("preDate");
		}
		if(item.containsKey("postDate"))
		{
		  this.postDate = item.getString("postDate");
		}
		if(item.containsKey("userId"))
		{
		  this.userId = item.getLong("userId");
		}
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
}
