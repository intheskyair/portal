package com.zqkj.bsm.cluster.pdu;

import net.sf.json.JSONObject;

public class SubMissionReq {
    //当前页数
    private int page=1;
    //每页大小
    private int pageSize=20;
    // 渲染单号
    private String trans_id;
    // 子任务状态
    private String status;
    //排序字段
    private String column;
    //升序还是倒叙
    private String desc;
    //user_id
    private String userId;
	//目标用户ID （监控中心-1l，网站-2l）
    private String targetId;
    //集群任务ID
    private String job_id;
    //子任务id
    private String subjob_id;
    //取消原因
    private String detail; 

    public String getJob_id() {
		return job_id;
	}

	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}

	public String getSubjob_id() {
		return subjob_id;
	}

	public void setSubjob_id(String subjob_id) {
		this.subjob_id = subjob_id;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getTargetId() {
    	return targetId;
    }
    
    public void setTargetId(String targetId) {
    	this.targetId = targetId;
    }
    /**
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
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
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the column
     */
    public String getColumn() {
        return column;
    }

    /**
     * @param column the column to set
     */
    public void setColumn(String column) {
        this.column = column;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
     * 将json字符串转化为子任务的条件对象.
     * 
     * @param json_str
     */
    public void jsontoMessage(String json_str)
    {
        JSONObject item =JSONObject.fromObject(json_str);  
        if(item.containsKey("page")) {
          this.page = item.getInt("page");
        }
        if(item.containsKey("pageSize")) {
            this.pageSize = item.getInt("pageSize");
        }
        if(item.containsKey("trans_id")) {
            this.trans_id = item.getString("trans_id");
        }
        if(item.containsKey("status")) {
            this.status = item.getString("status");
        }
        if(item.containsKey("column")) {
            this.column = item.getString("column");
        }
        if(item.containsKey("desc")) {
            this.desc = item.getString("desc");
        }
        if(item.containsKey("userId")) {
            this.userId = item.getString("userId");
        }
        if(item.containsKey("targetId")) {
            this.targetId = item.getString("targetId");
          }
        if(item.containsKey("sub_job_id")) {
            this.subjob_id = item.getString("sub_job_id");
          }
        if(item.containsKey("job_id")) {
            this.job_id = item.getString("job_id");
          }
    }
}
