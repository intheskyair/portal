package com.zqkj.bsm.cluster.pdu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.zqkj.bsm.util.CommonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JobXXClientReq 
{
	
	//0 多帧渲染 1：效果图
	private int mul_flag;
	//分布式节点数量
	private int agent_count;
	
	private int total_frame;
	//提交人ID 
	private long userId;
	//项目号
	private String projectId;
	//订单号
	private String transId;
	//0：成功，1失败，描述信息参见 resolvingResult 字段
	private int resolvingStatus;
	private String resolvingResult;
	//0线上  1线下
	private int orderType;
	//渲染根路径
	private String renderPath;
	//结果根路径
	private String resultPath;
	//0:试渲染，1：正式渲染
	private int renderType;
	//0:max,1：maya
	private int renderFileType;
	private String renderFile;
	
	//当renderFileType为1的时候（maya）
	private int render_mode;
	//版本
	private String software;
	private String error;
	private String warning;
	
	private long fileId;
	/*	maya_layers_list:	
	  		{
				"cameras": [
					"perspShape"
				],//相机
				"sframe": 1,//开始帧
				"eframe": 1,//结束帧
				"stepframe": 1,//间隔
				"frames": "1",//帧信息			
				"maya_layers": "defaultRenderLayer",//层名
				"width": 900,
				"height": 500,
				"iprefix": "None",//前缀
				"maya_img_fnc": "name_#.ext",//输出帧格式
				"maya_img_pad": "1",//补充位数
				"maya_render": "file"//MAYA渲染器
			},{...},...
	 */
	private List<Map<String, Object>> maya_layers_list;
	
	//当renderFileType为0的时候（max）
	//相机
	private String cameras;
	//字符串帧 1,2,4-6
	private String framelist;
	//修改名称
	private String frames;
	private long width;
	private long height;
	//输出文件名+拓展名
	private String outputname;
	//渲染元素
	private String render_element;
	private String render_style;
	//可渲染相机
	private String rendercamera;
	//渲染器名称和版本
	private String renderer_name;
	//任务名称
	private String name;
	
	//订单号
	private String orderId;
	
	private String batch;
	
	private String version;
	
	private int maxNode;
	
	private int afterFirstFrameMode;
	
	private String keyFrames;
	
	private String[] layerNames;
	
	private List<Map<String,Object>> storages;
	
	private String layer;
	
	/**
	 * 解析客户端的参数
	 * @param ison_str
	 */
	public boolean jsontoXXLine_client(String ison_str)
	{
		boolean return_flg = true;
		JSONObject item =JSONObject.fromObject(ison_str);
		if(item.containsKey("userID"))
		{
			this.userId = item.getLong("userID");
		}
		if(item.containsKey("projectID")){
		   this.projectId= item.getString("projectID");
		}
		if(item.containsKey("name")){
			this.name= item.getString("name");
		}
		if(item.containsKey("orderID")){
		   this.transId= item.getString("orderID");
		}
		if(item.containsKey("resolvingStatus")){
			this.resolvingStatus = item.getInt("resolvingStatus");
		}
		if(item.containsKey("resolvingResult")){
			this.resolvingResult = item.getString("resolvingResult");
		}
		if(item.containsKey("orderType")){
			this.orderType = item.getInt("orderType");
		}
		if(item.containsKey("renderPath")){
			this.renderPath = item.getString("renderPath");
		}
		if(item.containsKey("resultPath")){
			this.resultPath = item.getString("resultPath");
		}
		if(item.containsKey("renderType")){
			this.renderType = item.getInt("renderType");
		}
		if(item.containsKey("renderFileType"))
		{
			this.renderFileType = item.getInt("renderFileType");
		}
		else
		{
			return_flg = false;
		}
		if(item.containsKey("renderFile")){
			this.renderFile = item.getString("renderFile");
		}
		if(item.containsKey("fileId")){
			this.fileId = item.getLong("fileId");
		}
		if(item.containsKey("batchName"))
		{
			batch = item.getString("batchName");
		}	
			if(item.containsKey("mayaModel"))
			{
				if(item.containsKey("version"))
    			{
    				version = item.getString("version");
    			}
    			if(item.containsKey("maxNode"))
    			{
    				maxNode = item.getInt("maxNode");
    			}
				 JSONObject item_tmp =item.getJSONObject("mayaModel");
				 if(!item_tmp.toString().equals("null")&&!item_tmp.toString().equals(""))
				 {
					 JSONArray layers = item_tmp.getJSONArray("layers");
					 maya_layers_list = new ArrayList<Map<String,Object>>();
					 if(item_tmp.containsKey("software"))
					 {
							this.software = "Maya" + item_tmp.getString("software");
					 }
					 if(item_tmp.containsKey("mul_flag"))
					 {
						this.mul_flag = Integer.valueOf(CommonUtil.nullToDefault(item_tmp.getString("mul_flag"),"0"));
						this.agent_count = 1;
					}						
					if(item_tmp.containsKey("error")){
						this.error = item_tmp.getString("error");
					}
					if(item_tmp.containsKey("warning")){
						this.warning = item_tmp.getString("warning");
					}
	                 for(int i=0,len=layers.size();i<len;i++)
	                 {
	                	 Map<String,Object> map = new HashMap<String,Object>();
	                	 JSONObject json = layers.getJSONObject(i);
	                	 /*  "cameras": [
								"perspShape"
							],//相机
							"sframe": 1,//开始帧
							"eframe": 1,//结束帧
							"stepframe": 1,//间隔
							"frames": "1",//帧信息			
							"maya_layers": "defaultRenderLayer",//层名
							"width": 900,
							"height": 500,
							"iprefix": "None",//前缀
							"maya_img_fnc": "name_#.ext",//输出帧格式
							"maya_img_pad": "1",//补充位数
							"maya_render": "file"//MAYA渲染器*/
	                	map.put("maya_layers", json.getString("maya_layers"));
	                	map.put("iprefix", json.getString("iprefix"));
	                	map.put("maya_img_fnc", json.getString("maya_img_fnc"));
	                	map.put("maya_img_pad", json.getString("maya_img_pad"));
	                	map.put("maya_render", json.getString("maya_render"));
	                	JSONArray cameras = json.getJSONArray("cameras");
	                	int ca_num = cameras.size();
	                	if(ca_num==0){
	                		ca_num =1;
	                	}
	                	String  cameras_= cameras.toString();
	                	cameras_ =  cameras_.substring(1, cameras_.length()-1);
	                	cameras_ = cameras_.replace("\"", "");
	                   	if(cameras_.equals("none")){
	                   		map.put("cameras", "");
	                   	}else{
	                	    map.put("cameras", cameras_);
	                   	}
	
	                	map.put("width", json.getInt("width"));
	                	map.put("height", json.getInt("height"));
	                	if(json.containsKey("frames")){
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
		        		if(y!=0){
		        			total = (int)(x/y)+1;
		        		}else{
		        			total = x+1;
		        		}
	                	//必须要有相机个数算总帧
	                    this.total_frame += (total*ca_num);
	                	maya_layers_list.add(map);
	                 }
					}
				
			}
			if(item.containsKey("maxModel"))
			{
			JSONObject item_tmp =item.getJSONObject("maxModel");
			if(!item_tmp.toString().equals("null")&&!item_tmp.toString().equals("")){
			
        	JSONArray cameras = item_tmp.getJSONArray("cameras");
        	int ca_num = cameras.size();
        	if(ca_num==0){
        		ca_num =1;
        	}
        	String  cameras_= cameras.toString();
           	cameras_ =  cameras_.substring(1, cameras_.length()-1);
           	cameras_ = cameras_.replace("\"", "");
           	if(cameras_.equals("none")){
           		this.cameras = "";
           	}
           	else{
           		this.cameras = cameras_;
           	}
			if(item_tmp.containsKey("mul_flag"))
			 {
				this.mul_flag = Integer.valueOf(item_tmp.getString("mul_flag"));
				if(this.mul_flag==0)
				{
				  this.agent_count = 1;
				}
				else
				{
					this.agent_count = 3;
				} 
			}	
			else
			{
				this.agent_count = 1;
			}
    	    if(item_tmp.containsKey("error")){
			  this.error= item_tmp.getString("error");
        	}
			if(item_tmp.containsKey("framelist")){
				this.framelist = item_tmp.getString("framelist");
			}
			if(item_tmp.containsKey("width")){
				this.width = item_tmp.getLong("width");
			}
			if(item_tmp.containsKey("height")){
				this.height = item_tmp.getLong("height");
			}
			if(item_tmp.containsKey("outputname")){
				this.outputname = item_tmp.getString("outputname");
			}
        	if(item_tmp.containsKey("render_element")){
        		this.render_element = item_tmp.getString("render_element").replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\"", "");
        	}
        	if(item_tmp.containsKey("render_style")){
        		this.render_style = item_tmp.getString("render_style");
        	}
        	if(item_tmp.containsKey("rendercamera")){
        		this.rendercamera = item_tmp.getString("rendercamera");
        	}
        	if(item_tmp.containsKey("renderer_name")){
        		this.renderer_name = item_tmp.getString("renderer_name");
        	}
        	if(item_tmp.containsKey("software")){
        		this.software = item_tmp.getString("software");
        	}
			if(item_tmp.containsKey("frames"))
			{
			  this.frames= item_tmp.getString("frames");
			}
			else
			{
				return_flg = false;
			}
			//计算帧的总数
			String[] fras = this.frames.split(",");
			//"1-200, 400-500:2, 708-600:-6,-800--600:-2"
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
			//必须要有相机个数算总帧
			this.total_frame = this.total_frame*ca_num;
			
			}
			}
		    return return_flg;
	}
	
	public boolean jsontoMayaLineClient(String ison_str){
		boolean return_flg = true;
		JSONObject item =JSONObject.fromObject(ison_str);
		if(item.containsKey("operator_id"))
		{
			this.userId = item.getLong("operator_id");
		}
		if(item.containsKey("afterFirstFrameMode"))
		{
			this.afterFirstFrameMode = item.getInt("afterFirstFrameMode");
		}
		if(item.containsKey("batchName"))
		{
			this.batch = item.getString("batchName");
		}
		if(item.containsKey("firstFrames"))
		{
			this.keyFrames = item.getString("firstFrames");
		}
		String layerNameInfo = "";
		if(item.containsKey("layerName")){
			layerNameInfo = item.getString("layerName");
		}
		if(!"".equals(layerNameInfo)&&layerNameInfo.split(",").length > 0){
			this.layerNames = layerNameInfo.split(",");
		}
		if(item.containsKey("projectId")){
			this.projectId = item.getString("projectId");
		}
		if(item.containsKey("orderType")){
			this.orderType = item.getInt("orderType");
		}
		if(item.containsKey("renderType")){
			this.renderType = item.getInt("renderType");
		}
		if(item.containsKey("version")){
			this.version = "Maya"+item.getString("version");
			this.software = "Maya"+item.getString("version");
		}
		if(item.containsKey("fileId")){
			this.fileId = item.getLong("fileId");
		}
		if(item.containsKey("frames")){
     	   this.frames = item.getString("frames");
     	}
		if(item.containsKey("renderFile")){
			this.renderFile = item.getString("renderFile");
		}else
			return_flg = false;
		if(item.containsKey("taskName")){
			this.name = item.getString("taskName");
		}
		return return_flg;
	}
	public boolean jsontoMaxLineClient(String ison_str){
		return true;
	}

	public boolean jsontoXXFillLine_client(String ison_str){
		boolean return_flg = true;
		JSONObject item =JSONObject.fromObject(ison_str);
		if(item.size() > 0){
			if(item.containsKey("projectId")){
				this.projectId = item.getString("projectId");
			}
			if(item.containsKey("orderId")){
				this.transId = item.getString("orderId");
			}
			if(item.containsKey("renderPath")){
				this.renderPath = item.getString("renderPath");
			}
			if(item.containsKey("resultPath")){
				this.resultPath = item.getString("resultPath");
			}
			if(item.containsKey("orderType")){
				this.orderType = item.getInt("orderType");
			}
			if(item.containsKey("operator_id")){
				this.userId = item.getInt("operator_id");
			}
			if(item.containsKey("renderFile")){
				this.renderFile = item.getString("renderFile");
			}
			if(item.containsKey("frames"))
     		{
     			this.frames = item.getString("frames");
     		}
			if(item.containsKey("camera"))
     		{
     			this.cameras = item.getString("camera");
     		}
		}else{
			return_flg = false;
		}
		
		
		return return_flg;
	}
	
	public boolean jsontoMayaXXFillLine_client(String ison_str){
		boolean return_flg = true;
		JSONObject item =JSONObject.fromObject(ison_str);
		if(item.size() > 0){
			if(item.containsKey("projectId")){
				this.projectId = item.getString("projectId");
			}
			if(item.containsKey("orderId")){
				this.transId = item.getString("orderId");
			}
			if(item.containsKey("renderPath")){
				this.renderPath = item.getString("renderPath");
			}
			if(item.containsKey("resultPath")){
				this.resultPath = item.getString("resultPath");
			}
			if(item.containsKey("orderType")){
				this.orderType = item.getInt("orderType");
			}
			if(item.containsKey("operator_id")){
				this.userId = item.getInt("operator_id");
			}
			if(item.containsKey("renderFile")){
				this.renderFile = item.getString("renderFile");
			}
			if(item.containsKey("frames")){
				this.frames = item.getString("frames");
			}
			if(item.containsKey("layer")){
				this.layer = item.getString("layer");
			}
			if(item.containsKey("camera")){
				this.cameras = item.getString("camera");
			}
		}else{
			return_flg = false;
		}
		
		
		return return_flg;
	}
	
	public long getFileId() {
		return fileId;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	public long getUserId() {
		return userId;
	}




	public void setUserId(long userId) {
		this.userId = userId;
	}




	public String getProjectId() {
		return projectId;
	}


	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getTransId() {
		return transId;
	}




	public void setTransId(String transId) {
		this.transId = transId;
	}




	public int getResolvingStatus() {
		return resolvingStatus;
	}




	public void setResolvingStatus(int resolvingStatus) {
		this.resolvingStatus = resolvingStatus;
	}




	public String getResolvingResult() {
		return resolvingResult;
	}




	public void setResolvingResult(String resolvingResult) {
		this.resolvingResult = resolvingResult;
	}




	public int getOrderType() {
		return orderType;
	}




	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}




	public String getRenderPath() {
		return renderPath;
	}




	public void setRenderPath(String renderPath) {
		this.renderPath = renderPath;
	}




	public String getResultPath() {
		return resultPath;
	}




	public void setResultPath(String resultPath) {
		this.resultPath = resultPath;
	}




	public int getRenderType() {
		return renderType;
	}




	public void setRenderType(int renderType) {
		this.renderType = renderType;
	}




	public int getRenderFileType() {
		return renderFileType;
	}




	public void setRenderFileType(int renderFileType) {
		this.renderFileType = renderFileType;
	}




	public String getRenderFile() {
		return renderFile;
	}




	public void setRenderFile(String renderFile) {
		this.renderFile = renderFile;
	}




	public int getRender_mode() {
		return render_mode;
	}




	public void setRender_mode(int render_mode) {
		this.render_mode = render_mode;
	}




	public String getSoftware() {
		return software;
	}




	public void setSoftware(String software) {
		this.software = software;
	}




	public String getError() {
		return error;
	}




	public void setError(String error) {
		this.error = error;
	}




	public String getWarning() {
		return warning;
	}




	public void setWarning(String warning) {
		this.warning = warning;
	}




	public List<Map<String, Object>> getMaya_layers_list() {
		return maya_layers_list;
	}




	public void setMaya_layers_list(List<Map<String, Object>> maya_layers_list) {
		this.maya_layers_list = maya_layers_list;
	}




	public String getCameras() {
		return cameras;
	}




	public void setCameras(String cameras) {
		this.cameras = cameras;
	}




	public String getFramelist() {
		return framelist;
	}




	public void setFramelist(String framelist) {
		this.framelist = framelist;
	}




	public String getFrames() {
		return frames;
	}




	public void setFrames(String frames) {
		this.frames = frames;
	}




	public long getWidth() {
		return width;
	}




	public void setWidth(long width) {
		this.width = width;
	}




	public long getHeight() {
		return height;
	}




	public void setHeight(long height) {
		this.height = height;
	}




	public String getOutputname() {
		return outputname;
	}




	public void setOutputname(String outputname) {
		this.outputname = outputname;
	}




	public String getRender_element() {
		return render_element;
	}




	public void setRender_element(String render_element) {
		this.render_element = render_element;
	}




	public String getRender_style() {
		return render_style;
	}




	public void setRender_style(String render_style) {
		this.render_style = render_style;
	}




	public String getRendercamera() {
		return rendercamera;
	}




	public void setRendercamera(String rendercamera) {
		this.rendercamera = rendercamera;
	}




	public String getRenderer_name() {
		return renderer_name;
	}




	public void setRenderer_name(String renderer_name) {
		this.renderer_name = renderer_name;
	}




	public int getTotal_frame() {
		return total_frame;
	}




	public void setTotal_frame(int total_frame) {
		this.total_frame = total_frame;
	}

	public int getMul_flag() {
		return mul_flag;
	}

	public void setMul_flag(int mul_flag) {
		this.mul_flag = mul_flag;
	}

	public int getAgent_count() {
		return agent_count;
	}

	public void setAgent_count(int agent_count) {
		this.agent_count = agent_count;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getMaxNode() {
		return maxNode;
	}

	public void setMaxNode(int maxNode) {
		this.maxNode = maxNode;
	}

	public int getAfterFirstFrameMode() {
		return afterFirstFrameMode;
	}

	public void setAfterFirstFrameMode(int afterFirstFrameMode) {
		this.afterFirstFrameMode = afterFirstFrameMode;
	}

	public String getKeyFrames() {
		return keyFrames;
	}

	public void setKeyFrames(String keyFrames) {
		this.keyFrames = keyFrames;
	}

	public String[] getLayerNames() {
		return layerNames;
	}

	public void setLayerNames(String[] layerNames) {
		this.layerNames = layerNames;
	}

	public List<Map<String, Object>> getStorages() {
		return storages;
	}

	public void setStorages(List<Map<String, Object>> storages) {
		this.storages = storages;
	}

	public String getLayer() {
		return layer;
	}

	public void setLayer(String layer) {
		this.layer = layer;
	}
	
	
}	


