package com.zqkj.bsm.cluster.pdu;

import java.util.Map;

import net.sf.json.JSONObject;

/**
 * 子任务的JavaBean.
 * 
 * @author 0188
 *
 */
public class SubjobsReq {

    private long id; // 子任务ID
    private long job_id; // 任务ID
    private int frame_count; // 帧个数
    private int frame_from; // 起始帧
    private int frame_to; // 结束帧
    private int frame_step; // 步长
    private int status; // 子任务状态值（0:运行中/1:排队中/2:已暂停/3:成功/4:失败/5:已取消）
    private String status_text; // 子任务状态描述
    private int seconds; // 渲染时间(核秒数)
    private int core_seconds; // 渲染时间(核秒数)
    private String start_time; // 开始时间
    private String end_time; // 结束时间
    private String camera; // 镜头
    private String maya_layer;// 层名
    private String ip_addr;// ip地址
    private String m_cpu_usage;// 层名
    private String m_cpu_temperature;// ip地址
    private String reason;//子任务取消原因
    //下载状态
    private int down_status;
    /**
     * @return the id
     */
    public long getId() {
        return id;
    }



    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }



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



    public int getCore_seconds() {
		return core_seconds;
	}



	public void setCore_seconds(int core_seconds) {
		this.core_seconds = core_seconds;
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
     * @return the m_cpu_usage
     */
    public String getM_cpu_usage() {
        return m_cpu_usage;
    }



    /**
     * @param m_cpu_usage the m_cpu_usage to set
     */
    public void setM_cpu_usage(String m_cpu_usage) {
        this.m_cpu_usage = m_cpu_usage;
    }



    /**
     * @return the m_cpu_temperature
     */
    public String getM_cpu_temperature() {
        return m_cpu_temperature;
    }



    /**
     * @param m_cpu_temperature the m_cpu_temperature to set
     */
    public void setM_cpu_temperature(String m_cpu_temperature) {
        this.m_cpu_temperature = m_cpu_temperature;
    }



    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }



    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * 将集群接口传过来的JSONObject对象装化为子任务的JavaBean.
     * 
     * @param item
     */
    public void jsontoMessage(JSONObject item,Map<String, Object> map) {

        this.id = item.getLong("id");
        this.job_id = item.getLong("job_id");
        if(map!=null)
        {
        	String str = map.get(String.valueOf(this.id))==null?"0":String.valueOf(map.get(String.valueOf(this.id)));
        	this.down_status = Integer.valueOf(str).intValue();
        }
        else
        {
        	this.down_status = 0;
        }
        this.frame_count = item.getInt("frame_count");
        this.frame_from = item.getInt("frame_from");
        this.frame_to = item.getInt("frame_to");
        this.frame_step = item.getInt("frame_step");
        this.status = item.getInt("status");
        this.status_text = item.getString("status_text");
        if (item.containsKey("seconds")) {
            this.seconds = item.getInt("seconds");
        }
        if (item.containsKey("core_seconds")) {
        	this.core_seconds = item.getInt("core_seconds");
        }
        if (item.containsKey("start_time")) {
            this.start_time = item.getString("start_time");
        }
        if (item.containsKey("end_time")) {
            this.end_time     = item.getString("end_time");
        }
        if (item.containsKey("ip_addr")) {
            this.ip_addr = item.getString("ip_addr");
        }
        this.camera = item.getString("camera");
        if (item.containsKey("maya_layer")) {
            this.maya_layer = item.getString("maya_layer");
        }
        if (item.containsKey("m_cpu_usage")) {
            this.m_cpu_usage = item.getString("m_cpu_usage");
        }
        if (item.containsKey("m_cpu_temperature")) {
            this.m_cpu_temperature = item.getString("m_cpu_temperature");
        }
        if (item.containsKey("reason")) {
            this.reason = item.getString("reason");
        }
    }



	public int getDown_status() {
		return down_status;
	}



	public void setDown_status(int down_status) {
		this.down_status = down_status;
	}
}
