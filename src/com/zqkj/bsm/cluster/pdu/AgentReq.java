package com.zqkj.bsm.cluster.pdu;

import net.sf.json.JSONObject;

public class AgentReq 
{
	 
	 //节点ID
     private Long id;
     //节点IP地址
     private String ip_addr;
     //节点MAC地址
     private String mac_addr;
     //是否禁用
     private boolean disabled;
     // 禁用原因
     private String disable_reason;
     //禁用人
     private String disable_user;
     //节点状态值（0:离线,1:空闲,2:渲染中,3:从属准备中,4:从属中,5:解析中）
     private int status;
     //节点状态文本
     private String status_text;
     //存储挂载状态
     private String mount_status;
     //软件安装状态
     private String software_status;
     //CPU总核数
     private int total_processors;
     //CPU可用核数
     private int avail_processors;
     //节点OS名
     private String os_name;
     //CPU使用率
     private int  cpu_usage;
     //CPU频率
     private long cpu_frequency;
     //CPU温度，单位摄氏度
     private int cpu_temperature;
     //内存使用率
     private int mem_usage;
     //总物理内存
     private long mem_phys_total;
     //可用物理内存
     private long mem_phys_avail;
     //节点加入时间
     private String join_time;
     // 内存差
     private int mem_diff;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the ip_addr
	 */
	public String getIp_addr() {
		return ip_addr;
	}

	/**
	 * @param ip_addr the ip_addr to set
	 */
	public void setIp_addr(String ip_addr) {
		this.ip_addr = ip_addr;
	}

	/**
	 * @return the mac_addr
	 */
	public String getMac_addr() {
		return mac_addr;
	}

	/**
	 * @param mac_addr the mac_addr to set
	 */
	public void setMac_addr(String mac_addr) {
		this.mac_addr = mac_addr;
	}

	/**
	 * @return the disabled
	 */
	public boolean getDisabled() {
		return disabled;
	}

	/**
	 * @param disabled the disabled to set
	 */
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	/**
	 * @return the disable_reason
	 */
	public String getDisable_reason() {
		return disable_reason;
	}

	/**
	 * @param disable_reason the disable_reason to set
	 */
	public void setDisable_reason(String disable_reason) {
		this.disable_reason = disable_reason;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the status_text
	 */
	public String getStatus_text() {
		return status_text;
	}

	/**
	 * @param status_text the status_text to set
	 */
	public void setStatus_text(String status_text) {
		this.status_text = status_text;
	}

	/**
	 * @return the mount_status
	 */
	public String getMount_status() {
		return mount_status;
	}

	/**
	 * @param mount_status the mount_status to set
	 */
	public void setMount_status(String mount_status) {
		this.mount_status = mount_status;
	}

	/**
	 * @return the software_status
	 */
	public String getSoftware_status() {
		return software_status;
	}

	/**
	 * @param software_status the software_status to set
	 */
	public void setSoftware_status(String software_status) {
		this.software_status = software_status;
	}

	/**
	 * @return the total_processors
	 */
	public int getTotal_processors() {
		return total_processors;
	}

	/**
	 * @param total_processors the total_processors to set
	 */
	public void setTotal_processors(int total_processors) {
		this.total_processors = total_processors;
	}

	/**
	 * @return the avail_processors
	 */
	public int getAvail_processors() {
		return avail_processors;
	}

	/**
	 * @param avail_processors the avail_processors to set
	 */
	public void setAvail_processors(int avail_processors) {
		this.avail_processors = avail_processors;
	}

	/**
	 * @return the os_name
	 */
	public String getOs_name() {
		return os_name;
	}

	/**
	 * @param os_name the os_name to set
	 */
	public void setOs_name(String os_name) {
		this.os_name = os_name;
	}

	/**
	 * @return the cpu_usage
	 */
	public int getCpu_usage() {
		return cpu_usage;
	}

	/**
	 * @param cpu_usage the cpu_usage to set
	 */
	public void setCpu_usage(int cpu_usage) {
		this.cpu_usage = cpu_usage;
	}

	/**
	 * @return the cpu_frequency
	 */
	public long getCpu_frequency() {
		return cpu_frequency;
	}

	/**
	 * @param cpu_frequency the cpu_frequency to set
	 */
	public void setCpu_frequency(long cpu_frequency) {
		this.cpu_frequency = cpu_frequency;
	}

	/**
	 * @return the cpu_temperature
	 */
	public int getCpu_temperature() {
		return cpu_temperature;
	}

	/**
	 * @param cpu_temperature the cpu_temperature to set
	 */
	public void setCpu_temperature(int cpu_temperature) {
		this.cpu_temperature = cpu_temperature;
	}

	/**
	 * @return the mem_usage
	 */
	public int getMem_usage() {
		return mem_usage;
	}

	/**
	 * @param mem_usage the mem_usage to set
	 */
	public void setMem_usage(int mem_usage) {
		this.mem_usage = mem_usage;
	}

	/**
	 * @return the mem_phys_total
	 */
	public long getMem_phys_total() {
		return mem_phys_total;
	}

	/**
	 * @param mem_phys_total the mem_phys_total to set
	 */
	public void setMem_phys_total(long mem_phys_total) {
		this.mem_phys_total = mem_phys_total;
	}

	/**
	 * @return the mem_phys_avail
	 */
	public long getMem_phys_avail() {
		return mem_phys_avail;
	}

	/**
	 * @param mem_phys_avail the mem_phys_avail to set
	 */
	public void setMem_phys_avail(long mem_phys_avail) {
		this.mem_phys_avail = mem_phys_avail;
	}

	/**
	 * @return the join_time
	 */
	public String getJoin_time() {
		return join_time;
	}

	/**
	 * @param join_time the join_time to set
	 */
	public void setJoin_time(String join_time) {
		this.join_time = join_time;
	}

	/**
	 * @return the mem_diff
	 */
	public int getMem_diff() {
		return mem_diff;
	}

	/**
	 * @param mem_diff the mem_diff to set
	 */
	public void setMem_diff(int mem_diff) {
		this.mem_diff = mem_diff;
	}

	public void jsontoMessage(JSONObject item)
	{
		this.id = item.containsKey("id") ? item.getLong("id") : 0;
		this.ip_addr = item.containsKey("ip_addr") ? item.getString("ip_addr") : "";
		this.mac_addr = item.containsKey("mac_addr") ? item.getString("mac_addr") : "";
		this.disabled = item.containsKey("disabled") ? item.getBoolean("disabled") : false;
		this.disable_reason = item.containsKey("disable_reason") ? item.getString("disable_reason") : "";
		if (item.containsKey("disable_user"))
		{
		  this.disable_user = item.getString("disable_user");
		}
		this.status = item.containsKey("status") ? item.getInt("status") : -1;
		this.status_text = item.containsKey("status_text") ? item.getString("status_text") : "";
		if (0 != this.status) {
			this.mount_status = item.containsKey("mount_status") ? item.getString("mount_status") : "";
			this.software_status = item.containsKey("software_status") ? item.getString("software_status") : "";
			this.os_name	 = item.containsKey("os_name") ? item.getString("os_name") : "";
			this.cpu_usage	 = item.containsKey("cpu_usage") ? item.getInt("cpu_usage") : 0;
			this.cpu_frequency	 = item.containsKey("cpu_frequency") ? item.getLong("cpu_frequency") : 0;
			this.cpu_temperature	 = item.containsKey("cpu_temperature") ? item.getInt("cpu_temperature") : 0;
			this.mem_usage	 = item.containsKey("mem_usage") ? item.getInt("mem_usage") : 0;
			this.mem_phys_total	 = item.containsKey("mem_phys_total") ? item.getLong("mem_phys_total") : 0;
			this.mem_phys_avail	 = item.containsKey("mem_phys_avail") ? item.getLong("mem_phys_avail") : 0;
			this.join_time = item.containsKey("join_time") ? item.getString("join_time") : "";
		}
		this.total_processors = item.containsKey("total_processors") ? item.getInt("total_processors") : 0;
		this.avail_processors = item.containsKey("avail_processors") ? item.getInt("avail_processors") : 0;
		if (item.containsKey("mem_diff")) {
			this.mem_diff = item.getInt("mem_diff");
		}
	}

	public String getDisable_user() {
		return disable_user;
	}

	public void setDisable_user(String disable_user) {
		this.disable_user = disable_user;
	}
}
