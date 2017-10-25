package com.zqkj.bsm.jsgl.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
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

import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.jsgl.dao.AcceptanceManageDao;
import com.zqkj.bsm.jsgl.dao.ScheduleManageDao;
import com.zqkj.bsm.system.action.page.OraPaginatedList;
import com.zqkj.bsm.system.action.util.StringHelper;
import com.zqkj.bsm.util.CommonUtil;
import com.zqkj.bsm.util.PageData;
@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results({ 
			@Result(name = "schedulemanage", location = "/WEB-INF/jsp/jsgl/schedulemanage.jsp")  
        }) 
@ExceptionMappings({ 
	@ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") 
}) 
@Action(value = "schedulemanage") 
/**
 * 进度管理
 * @author xj
 *
 */
public class ScheduleManageAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		List<Map<String, Object>> items = ScheduleManageDao.getInstance().getExportDara(params_all);
		
		String file_name = "进度管理列表";
		//导出的字段和excel表头的对应关系
        Map<String, String> map_columns = ScheduleManageDao.getInstance().getList_Column();
		this.exportGK(items,map_columns, file_name);
    }	
/**
 * 获取进度管理列表
 * @return
 */
	public String getScheduleManageList()
	{
		String operation = request.getParameter("operation");
        if("init".equals(operation))
        {
            // 检索参数
            int pageNum = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagenum"), "1"))-1;
            int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "10"));
            Enumeration<String> paraNames = request.getParameterNames();
            Map<String, Object> params_all = this.dealParams(paraNames,null);
            //分页封装方法
            OraPaginatedList list_tmp = ScheduleManageDao.getInstance().queryScheduleManage(params_all, pageNum, pageSize);
            // 分页对象
            PageData pg = CommonUtil.fomateResult(list_tmp.getList(), pageNum + 1, pageSize, list_tmp.getFullListSize());
            writeJson(response, CommonUtil.formatFGMap(pg));
            return null;
        } else 
        {
            return "schedulemanage";
        }
	}
	
	
	
	/**
	 * 保存进度管理
	 * @throws Exception
	 */
    public void addScheduleManage() throws Exception
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return="";
        Enumeration<String> paraNames_ = request.getParameterNames();
        Map<String, Object> params_all = this.dealParams(paraNames_,null);
    	if(ScheduleManageDao.getInstance().addScheduleManage(params_all))
    	{
    		res_return= "{msg:\"ok\"}";
    	}
    	else
    	{
    		res_return="{msg:\"保存进度管理失败\"}";
    	}
        PrintWriter out=null;
        try 
        {
            out=response.getWriter();
            out.write(res_return);
            out.flush();
        } catch (IOException e) 
        {
            e.printStackTrace();
        }finally{
            out.close();
        }      
    }
	
    /**
     * 修改进度管理
     * @throws Exception
     */
    public void updateScheduleManage() throws Exception
    {
        response.setContentType("text/html; charset=UTF-8");
        Enumeration<String> paraNames_ = request.getParameterNames();
        Map<String, Object> params_all = this.dealParams(paraNames_,null);
        String res_return="";
        boolean flag = ScheduleManageDao.getInstance().updateScheduleManage(params_all);
    	if(flag)
    	{
    		res_return= "{msg:\"ok\"}";
    	}
    	else
    	{
    		res_return="{msg:\"修改进度管理失败\"}";
    	}
        PrintWriter out=null;
        try 
        {
            out=response.getWriter();
            out.write(res_return);
            out.flush();
        } catch (IOException e) 
        {
            e.printStackTrace();
        }finally{
            out.close();
        }      
    }
    /**
     * 删除进度管理
     */
    public void deleteScheduleManage()
    {
        response.setContentType("text/html; charset=UTF-8");
        String id  = StringHelper.noEmpty2(request.getParameter("id")).trim();
        String res_return="";
    	if(ScheduleManageDao.getInstance().deleteScheduleManage(id))
    	{
    		res_return= "{msg:\"ok\"}";
    	}
    	else
    	{
    		res_return="{msg:\"删除进度管理失败\"}";
    	}
    	writeJson_Str(response,res_return);    
    }
    
}
