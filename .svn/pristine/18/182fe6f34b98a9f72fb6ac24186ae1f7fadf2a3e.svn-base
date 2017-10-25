package com.zqkj.bsm.cluster.pdu;

import net.sf.json.JSONObject;

public class ActiveJob {

    private long job_id; // 任务ID
    private String job_name; // 任务名称
    private String job_software; // 渲染类型
    private String camera; // 镜头
    private String maya_layer; // 分层
    private int frame_count; // 帧个数
    private int frame_from; // 起始帧
    private int frame_to; // 结束帧
    private int frame_step; // 步长
    private int status; // 子任务状态值（0:运行中/1:排队中/2:已暂停/3:成功/4:失败/5:已取消）
    private String status_text; // 子任务状态描述
    private int seconds; // 渲染时间(核秒数)
    private String start_time; // 开始时间
    private String end_time; // 结束时间
    private String ip_addr; // 节点ip
    /**
     * @return the job_id
     */
    public long getJob_id() {
        return job_id;
    }
    /**
     * @param job_id the job_id to set
     */
    public void setJob_id(long job_id) {
        this.job_id = job_id;
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
     * @return the job_software
     */
    public String getJob_software() {
        return job_software;
    }
    /**
     * @param job_software the job_software to set
     */
    public void setJob_software(String job_software) {
        this.job_software = job_software;
    }

    /**
	 * @return the camera
	 */
	public String getCamera() {
		return camera;
	}
	/**
	 * @param camera the camera to set
	 */
	public void setCamera(String camera) {
		this.camera = camera;
	}
	/**
	 * @return the maya_layer
	 */
	public String getMaya_layer() {
		return maya_layer;
	}
	/**
	 * @param maya_layer the maya_layer to set
	 */
	public void setMaya_layer(String maya_layer) {
		this.maya_layer = maya_layer;
	}
	/**
     * @return the frame_count
     */
    public int getFrame_count() {
        return frame_count;
    }
    /**
     * @param frame_count the frame_count to set
     */
    public void setFrame_count(int frame_count) {
        this.frame_count = frame_count;
    }
    /**
     * @return the frame_from
     */
    public int getFrame_from() {
        return frame_from;
    }
    /**
     * @param frame_from the frame_from to set
     */
    public void setFrame_from(int frame_from) {
        this.frame_from = frame_from;
    }
    /**
     * @return the frame_to
     */
    public int getFrame_to() {
        return frame_to;
    }
    /**
     * @param frame_to the frame_to to set
     */
    public void setFrame_to(int frame_to) {
        this.frame_to = frame_to;
    }
    /**
     * @return the frame_step
     */
    public int getFrame_step() {
        return frame_step;
    }
    /**
     * @param frame_step the frame_step to set
     */
    public void setFrame_step(int frame_step) {
        this.frame_step = frame_step;
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
     * @return the seconds
     */
    public int getSeconds() {
        return seconds;
    }
    /**
     * @param seconds the seconds to set
     */
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
    /**
     * @return the start_time
     */
    public String getStart_time() {
        return start_time;
    }
    /**
     * @param start_time the start_time to set
     */
    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }
    /**
     * @return the end_time
     */
    public String getEnd_time() {
        return end_time;
    }
    /**
     * @param end_time the end_time to set
     */
    public void setEnd_time(String end_time) {
        this.end_time = end_time;
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
     * 将集群接口传过来的JSONObject对象装化为子任务的JavaBean.
     * 
     * @param item
     */
    public void jsontoMessage(JSONObject item) {

    	if (item.containsKey("job_id")) {
            this.job_id = item.getLong("job_id");
    	}
    	if (item.containsKey("job_name")) {
            this.job_name = item.getString("job_name");
    	}
    	if (item.containsKey("job_software")) {
            this.job_software = item.getString("job_software");
    	}
    	if (item.containsKey("camera")) {
            this.camera = item.getString("camera");
    	}
        if (item.containsKey("maya_layer")) {
            this.maya_layer = item.getString("maya_layer");
        }
        //this.frame_count = item.getInt("frame_count");
        if (item.containsKey("frame_from")) {
            this.frame_from = item.getInt("frame_from");
        }
        if (item.containsKey("frame_to")) {
            this.frame_to = item.getInt("frame_to");
        }
        if (item.containsKey("frame_step")) {
            this.frame_step = item.getInt("frame_step");
        }

        //this.status = item.getInt("status");
        //this.status_text = item.getString("status_text");
        //this.seconds = item.getInt("seconds");
        if (item.containsKey("start_time")) {
            this.start_time = item.getString("start_time");
        }
        //this.end_time     = item.getString("end_time");
        //this.ip_addr     = item.getString("ip_addr");
    }
}
