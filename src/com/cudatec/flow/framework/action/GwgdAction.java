package com.cudatec.flow.framework.action;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.snaker.engine.access.QueryFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.cudatec.flow.framework.dao.TaskManageDao;
import com.cudatec.flow.framework.service.SnakerEngineFacets;
import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.dal.bean.Admin;
import com.zqkj.bsm.slProject.dao.FeasibilityDao;
import com.zqkj.bsm.system.action.page.OraPaginatedList;
import com.zqkj.bsm.util.BHUtil;
import com.zqkj.bsm.util.CommonUtil;
import com.zqkj.bsm.util.PageData;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results( { @Result(name = "swgdList", location = "/WEB-INF/jsp/flow/swgd.jsp"),  
			@Result(name = "fwgdList", location = "/WEB-INF/jsp/flow/fwgd.jsp"),
            @Result(name = "error", location = "/failure.jsp")
        })
@ExceptionMappings( { @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") }) 
@Action(value = "gwgd") 
public class GwgdAction extends BaseAction {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;
	@Autowired
    SnakerEngineFacets facets;
	
	
	public String GwgdList()
    {
        String  operation = request.getParameter("operation");
        int type_sf = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("type"), "0"));
        if("init".equals(operation))
        {
            // 检索参数
            int pageNum = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagenum"), "1")) - 1;
            int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "10"));
            int type = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("type"), "0"));
            String reference = request.getParameter("reference") == null ? "" : request.getParameter("reference");
            String refer = request.getParameter("refer") == null ? "" : request.getParameter("refer");
            String overtime = request.getParameter("overtime") == null ? "" : request.getParameter("overtime");
            String sw_type = request.getParameter("sw_type") == null ? "" : request.getParameter("sw_type");
            String bcqx = request.getParameter("bcqx") == null ? "" : request.getParameter("bcqx");
            String sfgk = request.getParameter("sfgk") == null ? "" : request.getParameter("sfgk");
            //分页封装方法
            OraPaginatedList list_tmp = TaskManageDao.getInstance().getGwgdList(sw_type,bcqx ,type,reference, refer, overtime,sfgk, pageNum, pageSize);
            // 分页对象
            PageData pg = CommonUtil.fomateResult(list_tmp.getList(), pageNum + 1, pageSize, list_tmp.getFullListSize());
            pg.setList(pg.getList());
            writeJson(response, CommonUtil.formatFGMap(pg));
            return null;
        } else 
        {	
        	if(type_sf == 0){
        		return "swgdList";
        	}else{
        		return "fwgdList";
        	}
        }
        
    }
	
	
	/**
     * 导出数据
     * @throws IOException
     */
    public void exportJobs() throws IOException 
    {
        request.setCharacterEncoding("utf-8");
        //目前不做，按用户条件字段删选导出，
        //JSONArray columns = JSONArray.fromObject(request.getParameter("columns"));
        // 检索参数
        Enumeration<String> paraNames = request.getParameterNames();
        Map<String, Object> params_all = this.dealParams(paraNames,null);
        //按条件查询中所有需要导出的数据    规划项目列表
        List<Map<String, Object>> items = TaskManageDao.getInstance().getExportDara(params_all);
        //BEGINTIME_SHOW_DATE=上午12:00, REFERENCE=null, TRUENAME=null, SFGK=1, BEGINTIME=2017-02-15 00:00:00, REFER=常水农[2017]1, 
        //BEGINTIME_FORMAT_DATE=2017-02-15, STATUS=0, FJNAME=apache_mina_server_2.0中文参考手册v1.020170215150825_307.pdf, TITLE=123123}
        for (Map<String, Object> map : items)
        {
            if(map.get("STATUS").toString().equals("0")){
                map.put("STATUS", "未归档");
            }else{
                map.put("STATUS", "已归档");
            }
            if(map.get("SFGK").toString().equals("0")){
                map.put("SFGK", "是");
            }else{
                map.put("SFGK", "否");
            }
        }
        String file_name="";
        if(params_all.get("type").toString().equals("0")){
             file_name = "收文归档列表";
        }else{
             file_name = "发文归档列表";
        }
        //导出的字段和excel表头的对应关系
        Map<String, String> map_columns = TaskManageDao.getInstance().getList_Column();
        this.exportGK(items,map_columns, file_name);
    }
	
	
	
	
	public void viewStep(){
		String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
		List<Map<String, Object>> listHisTask = TaskManageDao.getInstance().getHisTaskList(orderId);
		for (Map<String, Object> map : listHisTask) {
			JSONObject object =JSONObject.fromObject(map.get("VARIABLE").toString());
    		map.put("IP", object.get("ip") == null ? null : object.get("ip"));
    		map.put("MARK", object.get("method") == null ? null : object.get("method"));
		}
		List<Map<String, Object>> listTask = TaskManageDao.getInstance().getTaskList(orderId);
		Map<String, Object> map = new HashMap<String,Object>();
		listHisTask.addAll(listTask); //流程进行中
		if(listTask.size() == 0){
			map.put("status", "end"); //流程结束
			listHisTask.add(map);
		}
		
		writeGson(response, listHisTask);
	}
	
	
	public void viewProcess(){
		List<org.snaker.engine.entity.Process> list = facets.getEngine().process().getProcesss(new QueryFilter());
		writeGson(response, list);
	}
	
	public void getMax() throws IOException{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", BHUtil.getTime_year());
		int count  = TaskManageDao.getInstance().getMax();
		String str = String.valueOf(count + 1);
		  int strLen = String.valueOf(str).length();
		    StringBuffer sb = null;
		    while (strLen < 4) {
		          sb = new StringBuffer();
		          sb.append("0").append(str);// 左(前)补0
		          str = sb.toString();
		          strLen = str.length();
		    }
		map.put("num", str);
		//获取历史档号
		List<Map<String, Object>> listHist = TaskManageDao.getInstance().getHisGwlist();
		map.put("list", listHist);
		writeGson(response, map);
	}
	
	public void referenceG() throws IOException{
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		String reference = request.getParameter("reference") == null ? "" : request.getParameter("reference");
		String 	id = request.getParameter("id") == null ? "" : request.getParameter("id");
		JSONArray jsonArray =  JSONArray.fromObject(id);
		for (int i = 0; i < jsonArray.size(); i++) {
			list.add(new Object[]{admin.getAdminId(),reference,jsonArray.get(i)});
		}
		boolean flag = TaskManageDao.getInstance().gwgdupdate(list);
		if(flag){
			response.getWriter().write("success");
		}else{
			response.getWriter().write("faild");
		}
	}
	
	public void editGwgd() throws IOException{
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		String 	id = request.getParameter("id") == null ? "" : request.getParameter("id");
		String 	page = request.getParameter("page") == null ? "" : request.getParameter("page");
		String 	remark = request.getParameter("remark") == null ? "" : request.getParameter("remark");
		boolean flag = TaskManageDao.getInstance().editGwgd(id, page, remark, admin.getAdminId());
		if(flag){
			response.getWriter().write("success");
		}else{
			response.getWriter().write("faild");
		}
	}
}
