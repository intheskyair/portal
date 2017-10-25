package com.zqkj.bsm.mzpc.action;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.mzpc.dao.MzpcDao;
import com.zqkj.bsm.util.CommonUtil;
@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results( { @Result(name = "success", location = "/WEB-INF/jsp/mzpc/mzpc_begin.jsp"),  
			@Result(name = "bg111", location = "/model_pc/bg1.jsp"),
            @Result(name = "error", location = "/failure.jsp")
        }) 
@ExceptionMappings( { @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") })
@Action(value = "mzpf") 
public class MzpfAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	
	public String begin(){
		return "success";
	}
	
	
	public void jumpDiv(){
		 String id = CommonUtil.nullToDefault(request.getParameter("id"), "");
		 List<Map<String, Object>> list = MzpcDao.getInstance().getRadomStatus(id);
		 Map<String, Object> map = new HashMap<String,Object>();
		 if(list.size() == 0){
			 map.put("error", "该随机码无效");
		 }else{
			 map.put("list", list);
		 }
		 writeJson(response, map);
	}
	
	
	
	
	public void jump() throws ParseException, IOException, ServletException{
 		 String id = CommonUtil.nullToDefault(request.getParameter("id"), "");
 		 String radom = CommonUtil.nullToDefault(request.getParameter("radom"), "");
		 List<Map<String, Object>> list = MzpcDao.getInstance().getRadomStatusTb(radom,id);
		 Map<String, Object> map = new HashMap<String,Object>();
		 String kr_status = null;
		 String begtime = null;
		 String endtime = null;
		 String km_status = null;
		 boolean flag = false;
		 String mainId = "";
		 if(list.size() != 0){
			  kr_status = list.get(0).get("KR_STATUS").toString();
			  begtime = list.get(0).get("BEGTIME").toString();
			  endtime = list.get(0).get("ENDTIME").toString();
			  km_status = list.get(0).get("KM_STATUS").toString();
			  mainId = list.get(0).get("ID").toString();
			 if(kr_status.equals("0")){
				 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				 //开始时间
				 Date beginTime = df.parse(begtime);
				 //结束时间
				 Date endTime = df.parse(endtime);
				 int begin = beginTime.compareTo(df.parse(df.format(new Date())));
				 int end = endTime.compareTo(df.parse(df.format(new Date())));
				 
				 if(begin != 1 && end != -1 && km_status.equals("0")){
					 flag = true;
				 }
			 }else{
				 flag =true;
			 }
		 }
		 if(flag){
			 Map<String, Object> list_tmp = MzpcDao.getInstance().queryById(mainId);
			 String DIC_PAHT = CommonUtil.nullToDefault(list_tmp.get("DIC_PAHT"), "");
			 DIC_PAHT =  DIC_PAHT.replace(".html", ".jsp");
             String[] dicpahts = DIC_PAHT.split("/");
			 if(kr_status.equals("0")){
				 map.put("view_type", 0);
				 DIC_PAHT= dicpahts[0]+"/mark_edit/"+dicpahts[1];
			 }else{
				 map.put("view_type", 1);
				 DIC_PAHT= dicpahts[0]+"/"+dicpahts[1];
			 }
			 map.put("url", DIC_PAHT);
             map.put("mainid", mainId);
             map.put("random", id);
		 }else{
			 map.put("error", "该随机码无效");
		 }
		 writeJson(response, map);
	}
	
	
	public void getType4User(){
		 String mainId = CommonUtil.nullToDefault(request.getParameter("mainId"), "");
		 Map<String, Object> map = MzpcDao.getInstance().getMainTitle(mainId);
		 writeJson(response, map);
	}
	
}
