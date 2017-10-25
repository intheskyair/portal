package com.zqkj.bsm.cluster.pdu;

import net.sf.json.JSONObject;

public class StorageRes {

	private int id; // 文件服务器共享ID
	private String ip_addrs; // 文件服务器列表(以逗号分隔)
	private String share_directory; // 共享目录名称
	private String windows_driver; // 映射磁盘驱动器名
	private String user_name; // 用户名
	private String user_pwd; // 用户密码
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the ip_addrs
	 */
	public String getIp_addrs() {
		return ip_addrs;
	}

	/**
	 * @param ip_addrs the ip_addrs to set
	 */
	public void setIp_addrs(String ip_addrs) {
		this.ip_addrs = ip_addrs;
	}

	/**
	 * @return the share_directory
	 */
	public String getShare_directory() {
		return share_directory;
	}

	/**
	 * @param share_directory the share_directory to set
	 */
	public void setShare_directory(String share_directory) {
		this.share_directory = share_directory;
	}

	/**
	 * @return the windows_driver
	 */
	public String getWindows_driver() {
		return windows_driver;
	}

	/**
	 * @param windows_driver the windows_driver to set
	 */
	public void setWindows_driver(String windows_driver) {
		this.windows_driver = windows_driver;
	}

	/**
	 * @return the user_name
	 */
	public String getUser_name() {
		return user_name;
	}

	/**
	 * @param user_name the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	/**
	 * @return the user_pwd
	 */
	public String getUser_pwd() {
		return user_pwd;
	}

	/**
	 * @param user_pwd the user_pwd to set
	 */
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}

	public void jsontoMessage(JSONObject item){

		this.id = item.getInt("id");
		this.ip_addrs = item.getString("ip_addrs");
		this.share_directory = item.getString("share_directory");
		this.windows_driver = item.getString("windows_driver");
		this.user_name = item.getString("user_name");
		this.user_pwd = item.getString("user_pwd");
	}
}
