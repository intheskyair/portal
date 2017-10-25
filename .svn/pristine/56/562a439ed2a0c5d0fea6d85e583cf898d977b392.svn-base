package com.zqkj.bsm.cluster.pdu;

import net.sf.json.JSONObject;

public class ManageSoftwareReq {

	private long id;//渲染软件ID
	private String plugin; //插件名称
	private String name; //软件名称
	private String path;  //软件安装路径
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPlugin() {
		return plugin;
	}

	public void setPlugin(String plugin) {
		this.plugin = plugin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}


	/**
     * 将集群接口传过来的JSONObject对象装化为渲染软件列表的JavaBean.
     * 
     * @param item
     */
	public void jsontoMessage(JSONObject item)
	{
		if(item.containsKey("id"))
		{
		  this.id = item.getLong("id");
		}
		if(item.containsKey("name"))
		{
		  this.name = item.getString("name");
		}
		if(item.containsKey("plugin"))
		{
		  this.plugin = item.getString("plugin");
		}
		if(item.containsKey("path"))
		{
		  this.path = item.getString("path");
		}
	}
	
}
