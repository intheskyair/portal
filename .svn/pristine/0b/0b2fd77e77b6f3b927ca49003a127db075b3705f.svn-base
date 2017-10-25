package com.zqkj.bsm.cluster.pdu;

import net.sf.json.JSONObject;

public class LogRes {

	private int id;
	private String trans_id;
	private String type;
	private String agent_ip;
	private String content;
	private String create_time;
	private String job_name;
	private String job_user_id;
	private String job_id;
	private String subjob_id;
	private boolean has_log;

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
	 * @return the trans_id
	 */
	public String getTrans_id() {
		return trans_id;
	}

	/**
	 * @param trans_id the trans_id to set
	 */
	public void setTrans_id(String trans_id) {
		this.trans_id = trans_id;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the agent_ip
	 */
	public String getAgent_ip() {
		return agent_ip;
	}

	/**
	 * @param agent_ip the agent_ip to set
	 */
	public void setAgent_ip(String agent_ip) {
		this.agent_ip = agent_ip;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the create_time
	 */
	public String getCreate_time() {
		return create_time;
	}

	/**
	 * @param create_time the create_time to set
	 */
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	/**
	 * @return the job_name
	 */
	public String getJob_name() {
		return job_name;
	}

	/**
	 * @param job_name the job_name to set
	 */
	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}

	/**
	 * @return the job_user_id
	 */
	public String getJob_user_id() {
		return job_user_id;
	}

	/**
	 * @param job_user_id the job_user_id to set
	 */
	public void setJob_user_id(String job_user_id) {
		this.job_user_id = job_user_id;
	}

	/**
	 * @return the job_id
	 */
	public String getJob_id() {
		return job_id;
	}

	/**
	 * @param job_id the job_id to set
	 */
	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}

	/**
	 * @return the subjob_id
	 */
	public String getSubjob_id() {
		return subjob_id;
	}

	/**
	 * @param subjob_id the subjob_id to set
	 */
	public void setSubjob_id(String subjob_id) {
		this.subjob_id = subjob_id;
	}



	/**
	 * @return the has_log
	 */
	public boolean getHas_log() {
		return has_log;
	}

	/**
	 * @param has_log the has_log to set
	 */
	public void setHas_log(boolean has_log) {
		this.has_log = has_log;
	}

	public void jsontoMessage(JSONObject item){

		this.id = item.getInt("id");
		this.type = item.getString("type");
		this.agent_ip = item.getString("agent_ip");
		this.content = item.getString("content");
		this.create_time = item.getString("create_time");
		if (item.containsKey("job_name")) {
			this.job_name = item.getString("job_name");
		}
		if (item.containsKey("job_user_id")) {
			this.job_user_id = item.getString("job_user_id");
		}
		if (item.containsKey("job_id")) {
			this.job_id = item.getString("job_id");
		}
		if (item.containsKey("subjob_id")) {
			this.subjob_id = item.getString("subjob_id");
		}
		if (item.containsKey("has_log")) {
			this.has_log = item.getBoolean("has_log");
		}
		if (item.containsKey("trans_id")) {
			this.trans_id = item.getString("trans_id");
		}
	}
}
