package com.zqkj.bsm.slProject.action;

import java.io.IOException;
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
import com.zqkj.bsm.dal.bean.Admin;
import com.zqkj.bsm.slProject.dao.PlanningDao;
import com.zqkj.bsm.system.action.page.OraPaginatedList;
import com.zqkj.bsm.system.action.util.StringHelper;
import com.zqkj.bsm.util.CommonUtil;
import com.zqkj.bsm.util.PageData;

@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results({ 
			@Result(name = "PlannIng", location = "/WEB-INF/jsp/slProject/plann.jsp") ,
			@Result(name = "project", location = "/WEB-INF/jsp/slProject/project.jsp"),
			@Result(name = "file", location = "/WEB-INF/jsp/slProject/file.jsp"),  
			@Result(name = "jihua", location = "/WEB-INF/jsp/slProject/jihua.jsp")
        }) 
@ExceptionMappings({ 
	@ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") 
}) 
@Action(value = "plann") 
/**
 * 水利工程建设
 * @author gf
 *
 */
public class PlanningAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 规划名录集合
	 * @return
	 */
	public String plannIngList()
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
            OraPaginatedList list_tmp = PlanningDao.getInstance().queryproject_ghmlc(params_all, pageNum, pageSize);
            // 分页对象
            PageData pg = CommonUtil.fomateResult(list_tmp.getList(), pageNum + 1, pageSize, list_tmp.getFullListSize());
            writeJson(response, CommonUtil.formatFGMap(pg));
            return null;
        } else 
        {
            return "PlannIng";
        }
	}
	
	
	
	
	/**
	 * 获取规划项目列表
	 * @return
	 */
	public String projectList()
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
            OraPaginatedList list_tmp = PlanningDao.getInstance().queryproject_ghxm(params_all, pageNum, pageSize);
            // 分页对象
            PageData pg = CommonUtil.fomateResult(list_tmp.getList(), pageNum + 1, pageSize, list_tmp.getFullListSize());
            writeJson(response, CommonUtil.formatFGMap(pg));
            return null;
        } else 
        {
            return "project";
        }
	}
	
	
	/**
	 * 规划文件列表
	 * @return
	 */
	public String fileList()
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
            OraPaginatedList list_tmp = PlanningDao.getInstance().queryproject_ghwj(params_all, pageNum, pageSize);
            // 分页对象
            PageData pg = CommonUtil.fomateResult(list_tmp.getList(), pageNum + 1, pageSize, list_tmp.getFullListSize());
            writeJson(response, CommonUtil.formatFGMap(pg));
            return null;
        } else 
        {
            return "file";
        }
	}
	
	
	/**
	 * 规划计划管理
	 * @return
	 */
	public String jihuaList()
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
            OraPaginatedList list_tmp = PlanningDao.getInstance().queryproject_ghjh(params_all, pageNum, pageSize);
            // 分页对象
            PageData pg = CommonUtil.fomateResult(list_tmp.getList(), pageNum + 1, pageSize, list_tmp.getFullListSize());
            writeJson(response, CommonUtil.formatFGMap(pg));
            return null;
        } else 
        {
            return "jihua";
        }
	}
	
	
	/**
	 * 保存规划名录
	 * @throws Exception
	 */
    public void saveplann() throws Exception
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return="";
        Enumeration<String> paraNames_ = request.getParameterNames();
        Map<String, Object> params_all = this.dealParams(paraNames_,null);
        Admin admin = (Admin)session.getAttribute("admin");
    	if(PlanningDao.getInstance().saveproject_ghml(params_all,String.valueOf(admin.getAdminId())))
    	{
    		res_return= "{msg:\"ok\"}";
    	}
    	else
    	{
    		res_return="{msg:\"保存规划名录失败\"}";
    	}
    	writeJson_Str(response,res_return);      
    }
    
    
    
    /**
     * 保存规划项目
     * @throws Exception
     */
    public void saveproject() throws Exception
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return="";
        Enumeration<String> paraNames_ = request.getParameterNames();
        Map<String, Object> params_all = this.dealParams(paraNames_,null);
        Admin admin = (Admin)session.getAttribute("admin");
    	if(PlanningDao.getInstance().saveproject_ghxm(params_all,String.valueOf(admin.getAdminId())))
    	{
    		res_return= "{msg:\"ok\"}";
    	}
    	else
    	{
    		res_return="{msg:\"保存规划项目失败\"}";
    	}
    	writeJson_Str(response,res_return);    
    }
    
    
    /**
     * 保存规划项目
     * @throws Exception
     */
    public void savefile() throws Exception
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return="";
        Enumeration<String> paraNames_ = request.getParameterNames();
        Map<String, Object> params_all = this.dealParams(paraNames_,null);
        Admin admin = (Admin)session.getAttribute("admin");
    	if(PlanningDao.getInstance().saveproject_ghwj(params_all,String.valueOf(admin.getAdminId())))
    	{
    		res_return= "{msg:\"ok\"}";
    	}
    	else
    	{
    		res_return="{msg:\"保存规划文件失败\"}";
    	}
    	writeJson_Str(response,res_return);    
    }
    
    public void savejihua() throws Exception
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return="";
        Enumeration<String> paraNames_ = request.getParameterNames();
        Map<String, Object> params_all = this.dealParams(paraNames_,null);
        Admin admin = (Admin)session.getAttribute("admin");
    	if(PlanningDao.getInstance().saveproject_ghjh(params_all,String.valueOf(admin.getAdminId())))
    	{
    		res_return= "{msg:\"ok\"}";
    	}
    	else
    	{
    		res_return="{msg:\"保存规划计划失败\"}";
    	}
    	writeJson_Str(response,res_return);      
    }
    
    
    public void updatejihua() throws Exception
    {
        response.setContentType("text/html; charset=UTF-8");
        Enumeration<String> paraNames_ = request.getParameterNames();
        Map<String, Object> params_all = this.dealParams(paraNames_,null);
        String res_return="";
    	if(PlanningDao.getInstance().updateproject_ghjh(params_all))
    	{
    		res_return= "{msg:\"ok\"}";
    	}
    	else
    	{
    		res_return="{msg:\"修改规划计划失败\"}";
    	}
    	writeJson_Str(response,res_return);      
    }
    
    
    /**
     * 修改规划文件
     * @throws Exception
     */
    public void updatefile() throws Exception
    {
        response.setContentType("text/html; charset=UTF-8");
        Enumeration<String> paraNames_ = request.getParameterNames();
        Map<String, Object> params_all = this.dealParams(paraNames_,null);
        String res_return="";
    	if(PlanningDao.getInstance().updateproject_ghwj(params_all))
    	{
    		res_return= "{msg:\"ok\"}";
    	}
    	else
    	{
    		res_return="{msg:\"修改规划文件失败\"}";
    	}
    	writeJson_Str(response,res_return);    
    }
    
    public void updateproject() throws Exception
    {
        response.setContentType("text/html; charset=UTF-8");
        Enumeration<String> paraNames_ = request.getParameterNames();
        Map<String, Object> params_all = this.dealParams(paraNames_,null);
        String res_return="";
    	if(PlanningDao.getInstance().updateproject_ghxm(params_all))
    	{
    		res_return= "{msg:\"ok\"}";
    	}
    	else
    	{
    		res_return="{msg:\"修改规划项目失败\"}";
    	}
    	writeJson_Str(response,res_return);       
    }
    
    
    public void updateplann() throws Exception
    {
        response.setContentType("text/html; charset=UTF-8");
        Enumeration<String> paraNames_ = request.getParameterNames();
        Map<String, Object> params_all = this.dealParams(paraNames_,null);
        String res_return="";
    	if(PlanningDao.getInstance().updateproject_ghml(params_all))
    	{
    		res_return= "{msg:\"ok\"}";
    	}
    	else
    	{
    		res_return="{msg:\"修改规划名录失败\"}";
    	}
    	writeJson_Str(response,res_return);      
    }
	
    
    
    public void delplann()
    {
        response.setContentType("text/html; charset=UTF-8");
        String id  = StringHelper.noEmpty2(request.getParameter("id")).trim();
        String res_return="";
    	if(PlanningDao.getInstance().delproject_ghml(id))
    	{
    		res_return= "{msg:\"ok\"}";
    	}
    	else
    	{
    		res_return="{msg:\"删除规划名录失败\"}";
    	}
    	writeJson_Str(response,res_return);    
    }
    
    public void delproject()
    {
        response.setContentType("text/html; charset=UTF-8");
        String id  = StringHelper.noEmpty2(request.getParameter("id")).trim();
        String res_return="";
    	if(PlanningDao.getInstance().delproject_ghxm(id))
    	{
    		res_return= "{msg:\"ok\"}";
    	}
    	else
    	{
    		res_return="{msg:\"删除规划项目失败\"}";
    	}
    	writeJson_Str(response,res_return);    
    }
    
    public void delfile() throws Exception
    {
        response.setContentType("text/html; charset=UTF-8");
        String id  = StringHelper.noEmpty2(request.getParameter("id")).trim();
        String res_return="";
    	if(PlanningDao.getInstance().delproject_ghwj(id))
    	{
    		res_return= "{msg:\"ok\"}";
    	}
    	else
    	{
    		res_return="{msg:\"删除规划文件失败\"}";
    	}
    	writeJson_Str(response,res_return);    
    }
    
    
    public void deljihua() throws Exception
    {
        response.setContentType("text/html; charset=UTF-8");
        String id  = StringHelper.noEmpty2(request.getParameter("id")).trim();
        String res_return="";
    	if(PlanningDao.getInstance().delproject_ghjh(id))
    	{
    		res_return= "{msg:\"ok\"}";
    	}
    	else
    	{
    		res_return="{msg:\"删除规划计划失败\"}";
    	}
    	writeJson_Str(response,res_return);    
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
		List<Map<String, Object>> items = PlanningDao.getInstance().getExportDara(params_all);
		
		String file_name = "规划名录列表";
		//导出的字段和excel表头的对应关系
        Map<String, String> map_columns = PlanningDao.getInstance().getList_Column();
		this.exportGK(items,map_columns, file_name);
    }    
    
    /**
     * 导出规划项目列表
     * @throws IOException
     */
    public void exportProject() throws IOException 
    {
        request.setCharacterEncoding("utf-8");
        //目前不做，按用户条件字段删选导出，
        //JSONArray columns = JSONArray.fromObject(request.getParameter("columns"));
        // 检索参数
        Enumeration<String> paraNames = request.getParameterNames();
        Map<String, Object> params_all = this.dealParams(paraNames,null);
		//按条件查询中所有需要导出的数据    规划项目列表
		List<Map<String, Object>> items = PlanningDao.getInstance().getExportDara_project(params_all);
		//导出的字段和excel表头的对应关系
        Map<String, String> map_columns = PlanningDao.getInstance().getList_Column_Project();
		String file_name = "规划项目列表";
		this.exportGK(items,map_columns,file_name);
    } 
    
    /**
     * 导出规划文件列表
     * @throws IOException
     */
    public void exportFiles() throws IOException 
    {
        request.setCharacterEncoding("utf-8");
        //目前不做，按用户条件字段删选导出，
        //JSONArray columns = JSONArray.fromObject(request.getParameter("columns"));
        // 检索参数
        Enumeration<String> paraNames = request.getParameterNames();
        Map<String, Object> params_all = this.dealParams(paraNames,null);
		//按条件查询中所有需要导出的数据    规划项目列表
		List<Map<String, Object>> items = PlanningDao.getInstance().getExportDara_file(params_all);
		//导出的字段和excel表头的对应关系
        Map<String, String> map_columns = PlanningDao.getInstance().getList_Column_File();
		String file_name = "规划文件列表";
		this.exportGK(items,map_columns,file_name);
    } 
    
    /**
     * 导出规划计划列表
     * @throws IOException
     */
    public void exportjihua() throws IOException 
    {
        request.setCharacterEncoding("utf-8");
        //目前不做，按用户条件字段删选导出，
        //JSONArray columns = JSONArray.fromObject(request.getParameter("columns"));
        // 检索参数
        Enumeration<String> paraNames = request.getParameterNames();
        Map<String, Object> params_all = this.dealParams(paraNames,null);
		//按条件查询中所有需要导出的数据    规划项目列表
		List<Map<String, Object>> items = PlanningDao.getInstance().getExportDara_plan(params_all);
		//导出的字段和excel表头的对应关系
        Map<String, String> map_columns = PlanningDao.getInstance().getList_Column_plan();
		String file_name = "规划计划列表";
		this.exportGK(items,map_columns,file_name);
    } 
    
    
    
}
