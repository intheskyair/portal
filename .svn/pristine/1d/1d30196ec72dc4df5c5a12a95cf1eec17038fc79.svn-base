package com.zqkj.bsm.cluster.pdu;
import net.sf.json.JSONObject;
/**
 * 集群任务列表的JavaBean.
 * @author 0283
 *
 */
public class RenderJobReq 
{
	private long id;//任务ID
	private String name; //任务名
	private long user_id ;//用户ID
	private long queue_id ;//任务队列ID
	private String trans_id; //项目单号
	private int agent_count;  //任务最大使用节点数
	private int render_strategy;     //渲染模式(0:测试模式/1:全速模式)
	private int render_flag;  //区分线上线下
	private int custom_flag;  //渲染速度
	private String software;  //渲染软件
	private int status;  //任务状态（0:运行中/1:排队中/2:暂停中/3:已暂停/4:已完成/5:已取消/6:同步中/7:已同步/8:同步失败）
	private String status_text;  //任务状态描述
	private int cur_concurrency;  //当前并行数
	private int total_frame;  //总帧数
	private int success_frame;  //成功帧数
	private int failure_frame;  //失败帧数
	private long seconds;  //渲染时间(秒数)
	private long core_seconds;  //渲染时间(核秒数)
	private long seq;  //渲染顺序数（越小越靠前）
	private String  create_time;  //提交时间
	public String getCreate_time() {
		return create_time;
	}


	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}


	public String getCreate_pople() {
		return create_pople;
	}


	public void setCreate_pople(String create_pople) {
		this.create_pople = create_pople;
	}


	private String  create_pople;  //提交人
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public long getUser_id() {
		return user_id;
	}


	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}


	public long getQueue_id() {
		return queue_id;
	}


	public void setQueue_id(long queue_id) {
		this.queue_id = queue_id;
	}


	public String getTrans_id() {
		return trans_id;
	}


	public void setTrans_id(String trans_id) {
		this.trans_id = trans_id;
	}


	public int getAgent_count() {
		return agent_count;
	}


	public void setAgent_count(int agent_count) {
		this.agent_count = agent_count;
	}


	public int getRender_strategy() {
		return render_strategy;
	}


	public void setRender_strategy(int render_strategy) {
		this.render_strategy = render_strategy;
	}


	public int getRender_flag() {
		return render_flag;
	}


	public void setRender_flag(int render_flag) {
		this.render_flag = render_flag;
	}


	public int getCustom_flag() {
		return custom_flag;
	}


	public void setCustom_flag(int custom_flag) {
		this.custom_flag = custom_flag;
	}


	public String getSoftware() {
		return software;
	}


	public void setSoftware(String software) {
		this.software = software;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getStatus_text() {
		return status_text;
	}


	public void setStatus_text(String status_text) {
		this.status_text = status_text;
	}


	public int getCur_concurrency() {
		return cur_concurrency;
	}


	public void setCur_concurrency(int cur_concurrency) {
		this.cur_concurrency = cur_concurrency;
	}


	public int getTotal_frame() {
		return total_frame;
	}


	public void setTotal_frame(int total_frame) {
		this.total_frame = total_frame;
	}


	public int getSuccess_frame() {
		return success_frame;
	}


	public void setSuccess_frame(int success_frame) {
		this.success_frame = success_frame;
	}


	public int getFailure_frame() {
		return failure_frame;
	}


	public void setFailure_frame(int failure_frame) {
		this.failure_frame = failure_frame;
	}


	public long getSeconds() {
		return seconds;
	}


	public void setSeconds(long seconds) {
		this.seconds = seconds;
	}


	public long getCore_seconds() {
		return core_seconds;
	}


	public void setCore_seconds(long core_seconds) {
		this.core_seconds = core_seconds;
	}


	public long getSeq() {
		return seq;
	}


	public void setSeq(long seq) {
		this.seq = seq;
	}


	 /**
    * 将集群接口传过来的JSONObjec对象装化为任务列表的JavaBean.
    * 
    * @param item
    */
	public void jsontoMessage( JSONObject item)
	{
		//JSONObject item =JSONObject.fromObject(json_str);
		if(item.containsKey("id"))
		{
		  this.id = item.getLong("id");
		}
		if(item.containsKey("seconds"))
		{
			this.seconds = item.getLong("seconds");
		}
		if(item.containsKey("core_seconds"))
		{
			this.core_seconds = item.getLong("core_seconds");
		}
		if(item.containsKey("seq"))
		{
			this.seq = item.getLong("seq");
		}
		if(item.containsKey("name"))
		{
		  this.name = item.getString("name");
		}
		if(item.containsKey("user_id"))
		{
		  this.user_id = item.getLong("user_id");
		}
		if(item.containsKey("queue_id"))
		{
			this.queue_id = item.getLong("queue_id");
		}
		if(item.containsKey("trans_id"))
		{
		  this.trans_id = item.getString("trans_id");
		}
		if(item.containsKey("software"))
		{
			this.software = item.getString("software");
		}
		if(item.containsKey("status_text"))
		{
			this.status_text = item.getString("status_text");
		}
		if(item.containsKey("agent_count"))
		{
		  this.agent_count = item.getInt("agent_count");
		}
		if(item.containsKey("total_frame"))
		{
			this.total_frame = item.getInt("total_frame");
		}
		if(item.containsKey("success_frame"))
		{
			this.success_frame = item.getInt("success_frame");
		}
		if(item.containsKey("failure_frame"))
		{
			this.failure_frame = item.getInt("failure_frame");
		}
		if(item.containsKey("status"))
		{
			this.status = item.getInt("status");
		}
		if(item.containsKey("cur_concurrency"))
		{
			this.cur_concurrency = item.getInt("cur_concurrency");
		}
		if(item.containsKey("render_strategy"))
		{
			this.render_strategy = item.getInt("render_strategy");
		}
		if(item.containsKey("render_flag"))
		{
			this.render_flag = item.getInt("render_flag");
		}
		if(item.containsKey("custom_flag"))
		{
			this.custom_flag = item.getInt("custom_flag");
		}
		if(item.containsKey("create_time"))
		{
			this.create_time = item.getString("create_time");
		}
		if(item.containsKey("create_pople"))
		{
			this.create_pople = item.getString("create_pople");
		}
	}
	
}
