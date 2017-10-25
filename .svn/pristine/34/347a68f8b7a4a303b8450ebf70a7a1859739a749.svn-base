package com.zqkj.bsm.cluster.pdu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.zqkj.bsm.util.CommonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JobClientReq 
{
	private long user_id;
	
	private int total_frame;
	//0 多帧渲染 1：效果图
	private int mul_flag;
	//任务名
	protected String name;
	//渲染软件 Maya 3dsMax  Vray
	protected String software;

	//文件路径集合包
	protected String commitpath;
	//缓存文件集合包
	protected String cachefiles;
	//Maya(每层都有参数)
	/*
	 *  iprefix	string	输出图片名称
		maya_img_ext	string	输出图片格式
		maya_img_fnc	string	输出帧格式
		maya_img_pad	int32	序列帧位数
		maya_render	string	渲染器名
		maya_layers	string	层名
		cameras	array	镜头名
		width	int32	帧宽
		height	int32	帧高
		frames	string	帧序列
		sframe	int32	开始帧
		eframe	int32	结束帧
		stepframe	int32	帧间隔

	 */
	protected ArrayList<Map<String,Object>> maya_list;
	
	//3dsMax(只有一层)
	//镜头名
	protected String camers;
	//输出文件名
	protected String output_file;
	//光子模式
	protected String  vr_mode;
	//VRay Irradiance Map
	protected String  vrimap_file;
	//VRay Light cache
	protected String  vrlmap_file;
	//VRay Photon Map
	protected String  vrpmap_file;
	//VRay Caustics
	protected String  vrcmap_file;
	//节点个数
	protected int  agent_count=1;
	//帧宽
	protected int width;
	//帧高
	protected int height;
	
	protected int photon_height; //光子的高度
	protected int photon_width;  //光子的宽度
	protected int ge_frame; //光子隔帧	
	protected int render_mode;//硬渲或者光子渲染
	//场景文件
	protected String scene_file;
	//帧序列
	protected String frames;
	
	protected String max_render;
	
	
	protected JSONObject priceparams;
	
	//MAX,MAYA
	protected String soft_type;
	//优先渲染帧集合
	protected String key_frames;
	//是否继续渲染
	protected int render_continue;
	
    public String getSoft_type() {
		return soft_type;
	}
	public void setSoft_type(String soft_type) {
		this.soft_type = soft_type;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long userid) {
		this.user_id = userid;
	}
	
	public int getMul_flag() {
		return mul_flag;
	}
	public void setMul_flag(int mul_flag) {
		this.mul_flag = mul_flag;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSoftware() {
		return software;
	}
	public void setSoftware(String software) {
		this.software = software;
	}
	public String getCommitpath() {
		return commitpath;
	}
	public void setCommitpath(String commitpath) 
	{
		this.commitpath = commitpath;
	}
	public ArrayList<Map<String, Object>> getMaya_list() {
		return maya_list;
	}
	public void setMaya_list(ArrayList<Map<String, Object>> maya_list) {
		this.maya_list = maya_list;
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
	public String getFrames() {
		return frames;
	}
	public void setFrames(String frames) {
		this.frames = frames;
	}
	public int getRender_continue() {
		return render_continue;
	}
	public void setRender_continue(int render_continue) {
		this.render_continue = render_continue;
	}
	/**
	 * 解析客户端的参数
	 * @param ison_str
	 */
	public boolean jsontoMessage_client(String ison_str)
	{
		boolean return_flg = true;
		JSONObject item =JSONObject.fromObject(ison_str);	
		if(item.containsKey("mul_flag"))
		{
		  this.mul_flag = item.getInt("mul_flag");
		}
		else
		{
			return_flg = false;
		}
		if(item.containsKey("userId"))
		{
			this.user_id = item.getLong("userId");
		}
		if(item.containsKey("name"))
		{
		   this.name= item.getString("name");
		}
		if(item.containsKey("render_mode"))
		{
		   this.render_mode= item.getInt("render_mode");
		}
		
		if(item.containsKey("software"))
		{
		   this.software= item.getString("software");
		}
		else
		{
			return_flg = false;
		}
		if(item.containsKey("render_continue")){
			this.render_continue = item.getInt("render_continue");
		}else{
			this.render_continue = 1;
		}
		String softs = this.software.toUpperCase();
		JSONObject commitpath =item.getJSONObject("commitpath");
		//相当于inputpathd的相对路径
		this.commitpath = commitpath.getString("renderfiles");
		if(softs.indexOf("MAYA")>=0)
		{
			this.soft_type = "MAYA";
			this.software= this.getSoftware(this.software);
			if(item.containsKey("layers"))
			{
				 JSONArray layers = item.getJSONArray("layers");
				 maya_list = new ArrayList<Map<String,Object>>();
                 for(int i=0,len=layers.size();i<len;i++)
                 {
                	 Map<String,Object> map = new HashMap<String,Object>();
                	 JSONObject json = layers.getJSONObject(i);
                	 /*  iprefix	string	输出图片名称
             		maya_img_ext	string	输出图片格式
             		maya_img_fnc	string	输出帧格式
             		maya_img_pad	int32	序列帧位数
             		maya_render	string	渲染器名
             		maya_layers	string	层名
             		cameras	array	镜头名
             		width	int32	帧宽
             		height	int32	帧高
             		frames	string	帧序列
             		sframe	int32	开始帧
             		eframe	int32	结束帧
             		stepframe	int32	帧间隔*/
                	map.put("iprefix", json.getString("iprefix"));
                	map.put("maya_img_ext", json.getString("maya_img_ext"));
                	map.put("maya_img_fnc", json.getString("maya_img_fnc"));
                	map.put("maya_img_pad", json.getString("maya_img_pad"));
                	map.put("maya_render", json.getString("maya_render"));
                	map.put("maya_layers", json.getString("maya_layers"));
                	JSONArray cameras = json.getJSONArray("cameras");
                	int ca_num = cameras.size();
                	if(ca_num==0)
                	{
                		ca_num =1;
                	}
                	String  cameras_= cameras.toString();
                	cameras_ =  cameras_.substring(1, cameras_.length()-1);
                	cameras_ = cameras_.replace("\"", "");
                   	if(cameras_.equals("none"))
                   	{
                   		map.put("cameras", "");
                   	}
                   	else
                   	{
                	    map.put("cameras", cameras_);
                   	}

                	map.put("width", json.getInt("width"));
                	map.put("height", json.getInt("height"));
                	if(json.containsKey("frames"))
                	{
                	   map.put("frames", json.getString("frames"));
                	}
                	int total =0;
                	int sf =json.getInt("sframe");
                	int ef = json.getInt("eframe");
                	int setp =json.getInt("stepframe");
                	map.put("sframe", json.getInt("sframe"));
                	map.put("eframe", json.getInt("eframe"));
                	map.put("stepframe", json.getInt("stepframe"));

                		int x = Math.abs(sf -ef);
                		int y = Math.abs(setp);
                		if(y!=0)
                		{
                			total = (int)(x/y)+1;
                		}
                		else
                		{
                			total = x+1;
                		}
                	//必须要有相机个数算总帧
                    this.total_frame += (total*ca_num);
                	maya_list.add(map);
                 }
				
			}
			
		}
		else if(softs.indexOf("MAX")>=0)
		{
			this.soft_type = "MAX";
			this.software= this.getMaxSoftware(this.software);			
        	JSONArray cameras = item.getJSONArray("cameras");
        	int ca_num = cameras.size();
        	if(ca_num==0)
        	{
        		ca_num =1;
        	}
        	String  cameras_= cameras.toString();
           	cameras_ =  cameras_.substring(1, cameras_.length()-1);
           	cameras_ = cameras_.replace("\"", "");
           	if(cameras_.equals("none"))
           	{
           		this.camers = "";
           	}
           	else
           	{
        	this.camers = cameras_;
           	}
        	if(item.containsKey("max_output_file"))
        	{
			  this.output_file= item.getString("max_output_file");
        	}
			this.vrimap_file= item.getString("vrimap_file");
			if(this.vrimap_file.equals("none"))
			{
				this.vrimap_file ="";
			}
			this.vr_mode= item.getString("vr_mode");
			if(this.vr_mode.equals("none"))
			{
				this.vr_mode ="";
			}
			this.vrlmap_file= item.getString("vrlmap_file");
			if(this.vrlmap_file.equals("none"))
			{
				this.vrlmap_file ="";
			}
			this.vrpmap_file= item.getString("vrpmap_file");
			if(this.vrpmap_file.equals("none"))
			{
				this.vrpmap_file ="";
			}
			this.vrcmap_file= item.getString("vrcmap_file");
			if(this.vrcmap_file.equals("none"))
			{
				this.vrcmap_file ="";
			}
        	if(item.containsKey("photon_height"))
        	{			 
			  this.photon_height = Integer.parseInt(CommonUtil.nullToDefault(item.getString("photon_height").trim(), "0"));
        	}
        	if(item.containsKey("photon_width"))
        	{
        	 this.photon_width = Integer.parseInt(CommonUtil.nullToDefault(item.getString("photon_width").trim(), "0"));
        	}
        	if(item.containsKey("ge_frame"))
        	{
			 this.ge_frame = Integer.parseInt(CommonUtil.nullToDefault(item.getString("ge_frame").trim(), "0"));
        	}
        	//计费参数的信息
        	if(item.containsKey("priceparams"))
        	{
    		  this.priceparams =item.getJSONObject("priceparams");
        	}
        	else
        	{
        		this.priceparams = null;
        	}
        	if(item.containsKey("agent_count"))
        	{
			 this.agent_count= item.getInt("agent_count");
        	}
        	else
        	{
        		//add by gaofeng 只有效果图 并且是max都能支持3台分布式渲染
        		if(this.mul_flag==1)
        		{
        			this.agent_count= 3;
        		}
        	}
			this.width= item.getInt("width");
			this.height= item.getInt("height");
        	if(item.containsKey("scene_file"))
        	{
			  this.scene_file= item.getString("scene_file");
        	}
			//this.max_render= item.getString("max_render");
        	
			if(item.containsKey("key_frames"))
			{
			  this.key_frames= item.getString("key_frames");
			}
			if(item.containsKey("frames"))
			{
			  this.frames= item.getString("frames");
			}
			else
			{
				return_flg = false;
			}
			//计算帧的总数
			String[] fras = this.frames.split(",");
			//"1-200, 400-500:2, 708-600:-6,-800--600:-2"
			try
			{
			for(String str:fras)
			{
				String[] fr_ = str.split(":");
				//没有冒号，step默认为1
				if(fr_.length==1)
				{
					String beg = fr_[0];
					//开始就有负号
					if(beg.indexOf("-")==0)
					{
						String beg_ = beg.substring(1,beg.length());
						int dex = beg_.indexOf("-");
						if(dex>-1)
						{
						String x = beg.substring(0,dex).trim();
						String y = beg.substring(dex+1).trim();
						int x_ = Integer.valueOf(x);
						int y_ = Integer.valueOf(y);
						this.total_frame += Math.abs(x_-y_)+1;
						}
						else
						{
							this.total_frame += 1;
						}
					}
					//开始时正数
					else
					{
						int dex = beg.indexOf("-");
						if(dex>-1)
						{
							String x = beg.substring(0,dex).trim();
							String y = beg.substring(dex+1).trim();
							int x_ = Integer.valueOf(x);
							int y_ = Integer.valueOf(y);
							this.total_frame += Math.abs(x_-y_)+1;
						}
						else
						{
							this.total_frame += 1;
						}
					}
				}
				else if(fr_.length==2)
				{
					String beg = fr_[0];
					String step = fr_[1];
					int step_ = Math.abs(Integer.valueOf(step));
					//开始就有负号
					if(beg.indexOf("-")==0)
					{
						String beg_ = beg.substring(1,beg.length());
						int dex = beg_.indexOf("-")+1;
						String x = beg.substring(0,dex).trim();
						String y = beg.substring(dex+1).trim();
						int x_ = Integer.valueOf(x);
						int y_ = Integer.valueOf(y);
						this.total_frame += (int)(Math.abs(x_-y_)/step_)+1;
					}
					//开始时正数
					else
					{
						int dex = beg.indexOf("-");
						String x = beg.substring(0,dex).trim();
						String y = beg.substring(dex+1).trim();
						int x_ = Integer.valueOf(x);
						int y_ = Integer.valueOf(y);
						this.total_frame += (int)(Math.abs(x_-y_)/step_)+1;
					}
				}
			  }
			}
			catch(NumberFormatException e)
			{
				return_flg = false;
			}
			//必须要有相机个数算总帧
			this.total_frame = this.total_frame*ca_num;
		}
		else
		{
			return_flg = false;
		}

		return return_flg;
	}
	public int getTotal_frame() {
		return total_frame;
	}
	public void setTotal_frame(int total_frame) {
		this.total_frame = total_frame;
	}
	public String getCachefiles() {
		return cachefiles;
	}
	public void setCachefiles(String cachefiles) {
		this.cachefiles = cachefiles;
	}
	public String getMax_render() {
		return max_render;
	}
	public void setMax_render(String max_render) {
		this.max_render = max_render;
	}
	
	
	
	private String getSoftware(String softs)
	{
		String return_soft="";
		if(softs.indexOf("2009")>-1 || softs.indexOf("2010")>-1 || softs.indexOf("2011")>-1 || softs.indexOf("2008")>-1 || softs.indexOf("2012")>-1)
		{
			return_soft = "Maya2012 x64";
		}
		else
		{
			return_soft = softs;
		}
		return return_soft;
	}

	
	private String getMaxSoftware(String softs)
	{
		String return_soft="";
		if(softs.indexOf("2009")>-1 || softs.indexOf("2010")>-1 || softs.indexOf("2011")>-1 || softs.indexOf("2008")>-1 || softs.indexOf("2012")>-1)
		{
			return_soft = "3ds max 2012";
		}
		else
		{
			return_soft = softs;
		}
		return return_soft;
	}
	public int getRender_mode() {
		return render_mode;
	}
	public void setRender_mode(int render_mode) {
		this.render_mode = render_mode;
	}
	public int getPhoton_height() {
		return photon_height;
	}
	public void setPhoton_height(int photon_heigh) {
		this.photon_height = photon_heigh;
	}
	public int getPhoton_width() {
		return photon_width;
	}
	public void setPhoton_width(int photon_width) {
		this.photon_width = photon_width;
	}
	public int getGe_frame() {
		return ge_frame;
	}
	public void setGe_frame(int ge_frame) {
		this.ge_frame = ge_frame;
	}
	public JSONObject getPriceparams() {
		return priceparams;
	}
	public void setPriceparams(JSONObject priceparams) {
		this.priceparams = priceparams;
	}
	public String getKey_frames() {
		return key_frames;
	}
	public void setKey_frames(String key_frames) {
		this.key_frames = key_frames;
	}
}	


