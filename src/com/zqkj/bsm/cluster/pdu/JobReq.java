package com.zqkj.bsm.cluster.pdu;
//import java.lang.reflect.Field;
//import java.text.SimpleDateFormat;
//import com.zqkj.bsm.util.FTPConnect;

//import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JobReq 
{
	//渲染模式
	private int plugin;
	//0 多帧渲染 1：效果图
	private int mul_flag;

	//集群返回的任务id
	protected long id;
	//任务名
	protected String name;
	//用户ID
	protected Long user_id;
	//渲染软件 Maya 3dsMax  Vray
	protected String software;
	//帧宽
	protected int width;
	//帧高
	protected int height;
	//场景文件
	protected String scene_file;
	//输出路径
	protected String output_path;
	//帧序列
	protected String frames;
	//任务状态（0:运行中/1:排队中/2:暂停中/3:已暂停/4:已完成/5:已取消）
	protected int status;
	//任务状态描述
	protected String status_text;
	//总帧数
	protected int total_frame;
	//成功帧数
	protected int success_frame;
	//失败帧数
	protected int failure_frame;
	//渲染时间(核秒数)
	protected Long seconds;
	//渲染时间(核秒数)
	protected Long core_seconds;
	//提交时间
	protected String submit_time;
	//当前并行数
	protected int cur_concurrency;
	//最大并行数
	protected int max_concurrency=0;
	
	//Maya
	//项目路径
	protected String project_path;
	//输出图片格式
	protected String img_ext;
	//输出帧格式
	protected String img_fnc;
	//序列帧位数
	protected int img_pad;
	//层名(多个层名用英文’,’或’;’隔开)
	protected String render;	
	//渲染器名
	protected String layers;
	
	
	//eidt by gaofeng  由于集群接口的变动导致
	protected String maya_proj_path;
	protected String maya_img_ext;
	protected String maya_img_fnc	;
	protected int maya_img_pad;
	protected String maya_render;	
	protected String maya_layers;

	
	//3dsMax
	//镜头名
	protected String camers;
	//输出文件名
	protected String output_file;
	//光子模式
	protected String vr_mode;
	//VRay Irradiance Map
	protected String vrimap_file;
	//VRay Light cache
	protected String vrlmap_file;
	//VRay Photon Map
	protected String vrpmap_file;
	//VRay Caustics
	protected String vrcmap_file;
	//节点个数
	protected int agent_count;
	
	
	//eidt by gaofeng  由于集群接口的变动导致
	protected String max_cameras;
	//输出文件名
	protected String max_output_file;
	//光子模式
	protected String max_vr_mode;
	//VRay Irradiance Map
	protected String max_vrimap_file;
	//VRay Light cache
	protected String max_vrlmap_file;
	//VRay Photon Map
	protected String max_vrpmap_file;
	//VRay Caustics
	protected String max_vrcmap_file;
	//节点个数
	protected int max_agent_count;
	
	

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
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getSoftware() {
		return software;
	}
	public void setSoftware(String software) {
		this.software = software;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getScene_file() {
		return scene_file;
	}
	public void setScene_file(String scene_file) {
		this.scene_file = scene_file;
	}
	public String getOutput_path() {
		return output_path;
	}
	public void setOutput_path(String output_path) {
		this.output_path = output_path;
	}
	public String getFrames() {
		return frames;
	}
	public void setFrames(String frames) {
		this.frames = frames;
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
	public Long getSeconds() {
		return seconds;
	}
	public void setSeconds(Long seconds) {
		this.seconds = seconds;
	}
	public String getSubmit_time() {
		return submit_time;
	}
	public void setSubmit_time(String submit_time) {
		this.submit_time = submit_time;
	}
	public String getProject_path() {
		return project_path;
	}
	public void setProject_path(String project_path) {
		this.project_path = project_path;
	}
	public String getImg_ext() {
		return img_ext;
	}
	public void setImg_ext(String img_ext) {
		this.img_ext = img_ext;
	}
	public String getImg_fnc() {
		return img_fnc;
	}
	public void setImg_fnc(String img_fnc) {
		this.img_fnc = img_fnc;
	}
	public int getImg_pad() {
		return img_pad;
	}
	public void setImg_pad(int img_pad) {
		this.img_pad = img_pad;
	}
	public String getRender() {
		return render;
	}
	public void setRender(String render) {
		this.render = render;
	}
	public String getLayers() {
		return layers;
	}
	public void setLayers(String layers) {
		this.layers = layers;
	}
	public String getCamers() {
		return camers;
	}
	public void setCamers(String camers) {
		this.camers = camers;
	}
	public String getOutput_file() {
		return output_file;
	}
	public void setOutput_file(String output_file) {
		this.output_file = output_file;
	}
	public String getVr_mode() {
		return vr_mode;
	}
	public void setVr_mode(String vr_mode) {
		this.vr_mode = vr_mode;
	}
	public String getVrimap_file() {
		return vrimap_file;
	}
	public void setVrimap_file(String vrimap_file) {
		this.vrimap_file = vrimap_file;
	}
	public String getVrlmap_file() {
		return vrlmap_file;
	}
	public void setVrlmap_file(String vrlmap_file) {
		this.vrlmap_file = vrlmap_file;
	}
	public String getVrpmap_file() {
		return vrpmap_file;
	}
	public void setVrpmap_file(String vrpmap_file) {
		this.vrpmap_file = vrpmap_file;
	}
	public String getVrcmap_file() {
		return vrcmap_file;
	}
	public void setVrcmap_file(String vrcmap_file) {
		this.vrcmap_file = vrcmap_file;
	}
	public int getAgent_count() {
		return agent_count;
	}
	public void setAgent_count(int agent_count) {
		this.agent_count = agent_count;
	}
	

	
	public void jsontoMessage_render(String ison_str)
	{
		JSONObject item =JSONObject.fromObject(ison_str);
		//JSONObject item = clusterSlaveGroupSource.getJSONObject("job");		
		if(item.containsKey("id"))
		{
		  this.id = item.getLong("id");
		}
		this.name= item.getString("name");
		this.user_id = item.getLong("user_id");
		this.software= item.getString("software");
//		this.width = item.getInt("width");
//		this.height = item.getInt("height");
//		this.scene_file= item.getString("scene_file");
		if(item.containsKey("output_path"))
		{
		   this.output_path= item.getString("output_path");
		}
		if(item.containsKey("frames"))
		{
		this.frames= item.getString("frames");
		}
		if(item.containsKey("status"))
		{
		this.status=  item.getInt("status");
		}
		if(item.containsKey("status_text"))
		{
		this.status_text=  item.getString("status_text");
		}
		if(item.containsKey("total_frame"))
		{
		this.total_frame=  item.getInt("total_frame");
		}
		if(item.containsKey("success_frame"))
		{
		this.success_frame=  item.getInt("success_frame");
		}
		if(item.containsKey("failure_frame"))
		{
		this.failure_frame=  item.getInt("failure_frame");
		}
		if(item.containsKey("seconds"))
		{
		this.seconds = item.getLong("seconds");	
		}
		if(item.containsKey("core_seconds"))
		{
		this.core_seconds = item.getLong("core_seconds");	
		}
		if(item.containsKey("submit_time"))
		{
		this.submit_time= item.getString("submit_time");
		}
		if(item.containsKey("cur_concurrency"))
		{
			this.cur_concurrency= item.getInt("cur_concurrency");			
		}
		if(item.containsKey("maya_proj_path"))
		{
			this.project_path= item.getString("maya_proj_path");			
		}
		if(item.containsKey("maya_img_ext"))
		{	
			this.img_ext= item.getString("maya_img_ext");		
		}
		if(item.containsKey("maya_img_fnc"))
		{	
			this.img_fnc=  item.getString("maya_img_fnc");
		
		}
		if(item.containsKey("maya_img_pad"))
		{	
			this.img_pad=  item.getInt("maya_img_pad");		
		}
		if(item.containsKey("maya_render"))
		{
			this.render= item.getString("maya_render");
		}
		if(item.containsKey("maya_layers"))
		{
			this.layers= item.getString("maya_layers");
		}		
		if(item.containsKey("max_cameras"))
		{
			this.camers=   item.getString("max_cameras");			
		}
		if(item.containsKey("max_output_file"))
		{
			this.output_file=  item.getString("max_output_file");			
		}
		if(item.containsKey("max_vr_mode"))
		{
			this.vr_mode=  item.getString("max_vr_mode");
		}		
		if(item.containsKey("max_vrimap_file"))
		{
			this.vrimap_file=  item.getString("max_vrimap_file");
		}
		if(item.containsKey("max_vrlmap_file"))
		{
			this.vrlmap_file=   item.getString("max_vrlmap_file");
		}
		if(item.containsKey("max_vrpmap_file"))
		{
			this.vrpmap_file=   item.getString("max_vrpmap_file");
		}
		if(item.containsKey("max_vrcmap_file"))
		{	
			this.vrcmap_file=   item.getString("max_vrcmap_file");		
		}
		if(item.containsKey("max_agent_count"))
		{
			this.agent_count=   item.getInt("max_agent_count");
		}

	}
	
	/**
	 * 编码成json格式 发送到集群，提交渲染任务请求包
	 * @param 
	 * @return
	 */
	public JSONObject MessageTojson(ArrayList<Map<String, Object>> list,String trans_id,String bd_path,long queue_id,int render_flag)
	{

		JSONObject jsonEntity = new JSONObject();
		if(list!=null && list.size()>0)
		{
			Map<String, Object> map_first= list.get(0);
			jsonEntity.put("name", map_first.get("NAME"));
			jsonEntity.put("user_id", map_first.get("FEE_USER_ID"));
			jsonEntity.put("software", map_first.get("SOFTWARE"));
			//0 标识线上，1表示线下
			jsonEntity.put("render_flag", render_flag);
			if(queue_id!=0)
			{
				jsonEntity.put("queue_id", queue_id);
			}
			//项目单号(线下)
			if(trans_id!=null)
			{
			   jsonEntity.put("trans_id", trans_id);
			}	
			jsonEntity.put("render_strategy", map_first.get("render_strategy".toUpperCase()));
			String softs = String.valueOf(map_first.get("SOFTWARE")).toUpperCase();
			if(softs.indexOf("MAYA")>=0)
			{
				jsonEntity.put("storages", map_first.get("STORAGES"));
				JSONArray list_json = new JSONArray();
				for(Map<String, Object> map:list)
				{
					JSONObject json= new JSONObject();
					//需要储存带过来的参数填写
					json.put("scene_file", map.get("INPUT_PATH"));
					json.put("output_path", map.get("OUTPUT_PATH"));
					json.put("keyframes", map.get("KEY_FRAMES"));					
					json.put("frames", map.get("frames".toUpperCase()));
					json.put("width", map.get("width".toUpperCase()));
					json.put("height", map.get("height".toUpperCase()));
					json.put("layer", map.get("maya_layers".toUpperCase()));
					json.put("cameras", map.get("camers".toUpperCase()));
					json.put("render", map.get("maya_render".toUpperCase()));
					json.put("proj_path", "");
					json.put("img_ext", map.get("maya_img_ext".toUpperCase()));
					json.put("img_fnc", map.get("maya_img_fnc".toUpperCase()));
					json.put("img_pad", map.get("maya_img_pad".toUpperCase()));
					
					list_json.add(json);
				}
				
				jsonEntity.put("maya_params", list_json);
			}
			else if(softs.indexOf("MAX")>=0)
			{
				JSONArray list_json = new JSONArray();
				for(Map<String, Object> map:list)
				{
					JSONObject json= new JSONObject();
					//需要储存带过来的参数填写
					json.put("scene_file", map.get("INPUT_PATH"));
					json.put("output_path", map.get("OUTPUT_PATH"));
					json.put("frames", map.get("frames".toUpperCase()));
					json.put("keyframes", map.get("KEY_FRAMES"));
					json.put("width", map.get("width".toUpperCase()));
					json.put("height", map.get("height".toUpperCase()));
					json.put("cameras", map.get("camers".toUpperCase()));
					json.put("output_file", map.get("output_file".toUpperCase()));
					json.put("vr_mode", map.get("vr_mode".toUpperCase()));
					json.put("vrimap_file", map.get("vrimap_file".toUpperCase()));
					json.put("vrlmap_file", map.get("vrlmap_file".toUpperCase()));
					json.put("vrpmap_file", map.get("vrpmap_file".toUpperCase()));
					json.put("vrcmap_file", map.get("vrcmap_file".toUpperCase()));
					json.put("root_path", bd_path);
					//add by gaofeng 为了效果图多台分布式渲染
					json.put("agent_count", map.get("agent_count".toUpperCase()));
					list_json.add(json);
				}
				
				jsonEntity.put("max_params", list_json);
			}
			
		}

		return jsonEntity;
	}
	
	/**
	 * 编码成json格式 发送到集群，提交渲染任务请求包
	 * @param 
	 * @return
	 */
	public JSONObject MessageTojsonMaya(ArrayList<Map<String, Object>> list,List<Map<String,Object>> storages,String trans_id,String bd_path,long queue_id,int render_flag)
	{

		JSONObject jsonEntity = new JSONObject();
		if(list!=null && list.size()>0)
		{
			Map<String, Object> map_first= list.get(0);
			jsonEntity.put("name", map_first.get("NAME"));
			jsonEntity.put("user_id", map_first.get("FEE_USER_ID"));
			jsonEntity.put("software", map_first.get("SOFTWARE"));
			jsonEntity.put("storages", storages);
			//0 标识线上，1表示线下
			jsonEntity.put("render_flag", render_flag);
			if(queue_id!=0)
			{
				jsonEntity.put("queue_id", queue_id);
			}
			//项目单号(线下)
			if(trans_id!=null)
			{
			   jsonEntity.put("trans_id", trans_id);
			}	
			jsonEntity.put("render_strategy", map_first.get("render_strategy".toUpperCase()));
			String softs = String.valueOf(map_first.get("SOFTWARE")).toUpperCase();
			/*if(softs.indexOf("MAYA")>=0)
			{*/
				JSONArray list_json = new JSONArray();
				for(Map<String, Object> map:list)
				{
					JSONObject json= new JSONObject();
					//需要储存带过来的参数填写
					json.put("scene_file", map.get("INPUT_PATH"));
					json.put("output_path", map.get("OUTPUT_PATH"));
					json.put("keyframes", map.get("KEY_FRAMES"));					
					json.put("frames", map.get("frames".toUpperCase()));
					json.put("width", map.get("width".toUpperCase()));
					json.put("height", map.get("height".toUpperCase()));
					json.put("layer", map.get("maya_layers".toUpperCase()));
					json.put("cameras", map.get("camers".toUpperCase()));
					json.put("render", map.get("maya_render".toUpperCase()));
					json.put("proj_path", "");
					json.put("img_ext", map.get("maya_img_ext".toUpperCase()));
					json.put("img_fnc", map.get("maya_img_fnc".toUpperCase()));
					json.put("img_pad", map.get("maya_img_pad".toUpperCase()));
					
					list_json.add(json);
				}
				
				jsonEntity.put("maya_params", list_json);
			/*}
			else if(softs.indexOf("MAX")>=0)
			{
				JSONArray list_json = new JSONArray();
				for(Map<String, Object> map:list)
				{
					JSONObject json= new JSONObject();
					//需要储存带过来的参数填写
					json.put("scene_file", map.get("INPUT_PATH"));
					json.put("output_path", map.get("OUTPUT_PATH"));
					json.put("frames", map.get("frames".toUpperCase()));
					json.put("keyframes", map.get("KEY_FRAMES"));
					json.put("width", map.get("width".toUpperCase()));
					json.put("height", map.get("height".toUpperCase()));
					json.put("cameras", map.get("camers".toUpperCase()));
					json.put("output_file", map.get("output_file".toUpperCase()));
					json.put("vr_mode", map.get("vr_mode".toUpperCase()));
					json.put("vrimap_file", map.get("vrimap_file".toUpperCase()));
					json.put("vrlmap_file", map.get("vrlmap_file".toUpperCase()));
					json.put("vrpmap_file", map.get("vrpmap_file".toUpperCase()));
					json.put("vrcmap_file", map.get("vrcmap_file".toUpperCase()));
					json.put("root_path", bd_path);
					//add by gaofeng 为了效果图多台分布式渲染
					json.put("agent_count", map.get("agent_count".toUpperCase()));
					list_json.add(json);
				}
				
				jsonEntity.put("max_params", list_json);
			}*/
			
		}

		return jsonEntity;
	}
	
	public int getPlugin() {
		return plugin;
	}
	public void setPlugin(int plugin) {
		this.plugin = plugin;
	}
	public int getMul_flag() {
		return mul_flag;
	}
	public void setMul_flag(int mul_flag) {
		this.mul_flag = mul_flag;
	}
	
	/*
	public void map2Message(Map<String, Object> map,Object object)
	{

		Class cls = object.getClass();
		Field[] fields = cls.getDeclaredFields();
		try 
		{
			for (int i = 0; i < fields.length; i++) 
			{
				Field  field_ = fields[i];
				String field_name = fields[i].getName();
				String type =   fields[i].getType().getName();
				Object value = map.get(field_name.toUpperCase());
				if(type.equalsIgnoreCase("java.lang.String"))
				{
					    field_.setAccessible(true);
						field_.set(this, String.valueOf(value));
						field_.setAccessible(false);
				}
				if(type.equalsIgnoreCase("Long") || type.equalsIgnoreCase("java.lang.Long"))
				{
					    field_.setAccessible(true);
					    if(value!=null)
					    {
						 field_.set(this, Long.valueOf(String.valueOf(value)));
					    }
						field_.setAccessible(false);
				}
				if(type.equalsIgnoreCase("Int"))
				{
					    field_.setAccessible(true);
					    if(value!=null)
					    {
						 field_.set(this, Integer.valueOf(String.valueOf(value)));
					    }
						field_.setAccessible(false);
				}
			}
		}
		 catch (IllegalArgumentException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	public Long getCore_seconds() {
		return core_seconds;
	}
	public void setCore_seconds(Long core_seconds) {
		this.core_seconds = core_seconds;
	}
	public int getCur_concurrency() {
		return cur_concurrency;
	}
	public void setCur_concurrency(int cur_concurrency) {
		this.cur_concurrency = cur_concurrency;
	}
	public int getMax_concurrency() {
		return max_concurrency;
	}
	public void setMax_concurrency(int max_concurrency) {
		this.max_concurrency = max_concurrency;
	}
	public String getMaya_proj_path() {
		return maya_proj_path;
	}
	public void setMaya_proj_path(String maya_proj_path) {
		this.maya_proj_path = maya_proj_path;
	}
	public String getMaya_img_ext() {
		return maya_img_ext;
	}
	public void setMaya_img_ext(String maya_img_ext) {
		this.maya_img_ext = maya_img_ext;
	}
	public String getMaya_img_fnc() {
		return maya_img_fnc;
	}
	public void setMaya_img_fnc(String maya_img_fnc) {
		this.maya_img_fnc = maya_img_fnc;
	}
	public int getMaya_img_pad() {
		return maya_img_pad;
	}
	public void setMaya_img_pad(int maya_img_pad) {
		this.maya_img_pad = maya_img_pad;
	}
	public String getMaya_render() {
		return maya_render;
	}
	public void setMaya_render(String maya_render) {
		this.maya_render = maya_render;
	}
	public String getMaya_layers() {
		return maya_layers;
	}
	public void setMaya_layers(String maya_layers) {
		this.maya_layers = maya_layers;
	}
	public String getMax_cameras() {
		return max_cameras;
	}
	public void setMax_cameras(String max_cameras) {
		this.max_cameras = max_cameras;
	}
	public String getMax_output_file() {
		return max_output_file;
	}
	public void setMax_output_file(String max_output_file) {
		this.max_output_file = max_output_file;
	}
	public String getMax_vr_mode() {
		return max_vr_mode;
	}
	public void setMax_vr_mode(String max_vr_mode) {
		this.max_vr_mode = max_vr_mode;
	}
	public String getMax_vrimap_file() {
		return max_vrimap_file;
	}
	public void setMax_vrimap_file(String max_vrimap_file) {
		this.max_vrimap_file = max_vrimap_file;
	}
	public String getMax_vrlmap_file() {
		return max_vrlmap_file;
	}
	public void setMax_vrlmap_file(String max_vrlmap_file) {
		this.max_vrlmap_file = max_vrlmap_file;
	}
	public String getMax_vrpmap_file() {
		return max_vrpmap_file;
	}
	public void setMax_vrpmap_file(String max_vrpmap_file) {
		this.max_vrpmap_file = max_vrpmap_file;
	}
	public String getMax_vrcmap_file() {
		return max_vrcmap_file;
	}
	public void setMax_vrcmap_file(String max_vrcmap_file) {
		this.max_vrcmap_file = max_vrcmap_file;
	}
	public int getMax_agent_count() {
		return max_agent_count;
	}
	public void setMax_agent_count(int max_agent_count) {
		this.max_agent_count = max_agent_count;
	}
}
