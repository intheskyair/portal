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
import com.zqkj.bsm.slProject.dao.ZtbDao;
import com.zqkj.bsm.system.action.page.OraPaginatedList;
import com.zqkj.bsm.system.action.util.StringHelper;
import com.zqkj.bsm.util.CommonUtil;
import com.zqkj.bsm.util.PageData;
@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results({ 
			@Result(name = "zbdlr", location = "/WEB-INF/jsp/slProject/zbdlr.jsp") ,
			@Result(name = "zbgg", location = "/WEB-INF/jsp/slProject/zbgg.jsp"),
			@Result(name = "zbyqh", location = "/WEB-INF/jsp/slProject/zbyqh.jsp")
        }) 
@ExceptionMappings({ 
	@ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") 
}) 
@Action(value = "ztb") 
/**
 * 项目招标及合同管理
 * @author 高峰
 *
 */
public class ZtbAction extends BaseAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5439350760331573563L;
	
    /**
     * 导出数据
     * @throws IOException
     */
    public void exportJobs_zbyqh() throws IOException 
    {
        request.setCharacterEncoding("utf-8");
        //目前不做，按用户条件字段删选导出，
        //JSONArray columns = JSONArray.fromObject(request.getParameter("columns"));
        // 检索参数
        Enumeration<String> paraNames = request.getParameterNames();
        Map<String, Object> params_all = this.dealParams(paraNames,null);
		//按条件查询中所有需要导出的数据    规划项目列表
		List<Map<String, Object>> items = ZtbDao.getInstance().getExportDara_zbyqh(params_all);
		
		String file_name = "招标公告列表";
		//导出的字段和excel表头的对应关系
        Map<String, String> map_columns = ZtbDao.getInstance().getList_Column_zbyqh();
		this.exportGK(items,map_columns, file_name);
    }
	/**
     * 导出数据
     * @throws IOException
     */
    public void exportJobs_zbgg() throws IOException 
    {
        request.setCharacterEncoding("utf-8");
        //目前不做，按用户条件字段删选导出，
        //JSONArray columns = JSONArray.fromObject(request.getParameter("columns"));
        // 检索参数
        Enumeration<String> paraNames = request.getParameterNames();
        Map<String, Object> params_all = this.dealParams(paraNames,null);
		//按条件查询中所有需要导出的数据    规划项目列表
		List<Map<String, Object>> items = ZtbDao.getInstance().getExportDara_zbgg(params_all);
		
		String file_name = "招标公告列表";
		//导出的字段和excel表头的对应关系
        Map<String, String> map_columns = ZtbDao.getInstance().getList_Column_zbgg();
		this.exportGK(items,map_columns, file_name);
    }
    /**
     * 导出数据
     * @throws IOException
     */
    public void exportJobs_zbdlr() throws IOException 
    {
        request.setCharacterEncoding("utf-8");
        //目前不做，按用户条件字段删选导出，
        //JSONArray columns = JSONArray.fromObject(request.getParameter("columns"));
        // 检索参数
        Enumeration<String> paraNames = request.getParameterNames();
        Map<String, Object> params_all = this.dealParams(paraNames,null);
		//按条件查询中所有需要导出的数据    规划项目列表
		List<Map<String, Object>> items = ZtbDao.getInstance().getExportDara_zbdlr(params_all);
		
		String file_name = "招标代理人列表";
		//导出的字段和excel表头的对应关系
        Map<String, String> map_columns = ZtbDao.getInstance().getList_Column_zbdlr();
		this.exportGK(items,map_columns, file_name);
    }	
	
	/**
	 * 招标代理人基本信息列表
	 * @return
	 */
	public String zbdlrList()
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
            OraPaginatedList list_tmp = ZtbDao.getInstance().queryproject_zbdlrjbxx(params_all, pageNum, pageSize);
            // 分页对象
            PageData pg = CommonUtil.fomateResult(list_tmp.getList(), pageNum + 1, pageSize, list_tmp.getFullListSize());
            writeJson(response, CommonUtil.formatFGMap(pg));
            return null;
        } else 
        {
            return "zbdlr";
        }
	}
	
	
    public void savezbdlr()
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return="";
        Enumeration<String> paraNames_ = request.getParameterNames();
        Map<String, Object> params_all = this.dealParams(paraNames_,null);
        Admin admin = (Admin)session.getAttribute("admin");
    	if(ZtbDao.getInstance().saveproject_zbdlrjbxx(params_all,String.valueOf(admin.getAdminId())))
    	{
    		res_return= "{msg:\"ok\"}";
    	}
    	else
    	{
    		res_return="{msg:\"保存招标代理人失败\"}";
    	}
    	writeJson_Str(response,res_return);       
    }
    
    
    public void updatezbdlr()
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return="";
        Enumeration<String> paraNames_ = request.getParameterNames();
        Map<String, Object> params_all = this.dealParams(paraNames_,null);
    	if(ZtbDao.getInstance().updateproject_zbdlrjbxx(params_all))
    	{
    		res_return= "{msg:\"ok\"}";
    	}
    	else
    	{
    		res_return="{msg:\"更新招标代理人失败\"}";
    	}
    	writeJson_Str(response,res_return);       
    }
    
    
    public void delzbdlr()
    {
        response.setContentType("text/html; charset=UTF-8");
        String id  = StringHelper.noEmpty2(request.getParameter("id")).trim();
        String res_return="";
    	if(ZtbDao.getInstance().delzbdlrjbxx(id))
    	{
    		res_return= "{msg:\"ok\"}";
    	}
    	else
    	{
    		res_return="{msg:\"删除招标代理人失败\"}";
    	}
    	writeJson_Str(response,res_return);
    }
    
    
    public void getzbdlr()
    {
    	String id = CommonUtil.nullToDefault(request.getParameter("id"), "0");
        Map<String, Object> params_all = ZtbDao.getInstance().queryzbdlrjbxx(id);
        writeGson(response,params_all);       
    }
    
    
    
	/**
	 * 招标公告信息列表
	 * @return
	 */
	public String zbggList()
	{
		String operation = request.getParameter("operation");
		request.setAttribute("head", "招标公告");
        if("init".equals(operation))
        {
            // 检索参数
            int pageNum = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagenum"), "1"))-1;
            int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "10"));
            Enumeration<String> paraNames = request.getParameterNames();
            Map<String, Object> params_all = this.dealParams(paraNames,null);
            //分页封装方法
            OraPaginatedList list_tmp = ZtbDao.getInstance().queryproject_zbgg(params_all, pageNum, pageSize);
            // 分页对象
            PageData pg = CommonUtil.fomateResult(list_tmp.getList(), pageNum + 1, pageSize, list_tmp.getFullListSize());
            writeJson(response, CommonUtil.formatFGMap(pg));
            return null;
        } else 
        {
            return "zbgg";
        }
	}
	
	
    public void savezbgg()
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return="";
        Enumeration<String> paraNames_ = request.getParameterNames();
        Map<String, Object> params_all = this.dealParams(paraNames_,null);
        Admin admin = (Admin)session.getAttribute("admin");
    	if(ZtbDao.getInstance().saveproject_zbgg(params_all,String.valueOf(admin.getAdminId())))
    	{
    		res_return= "{msg:\"ok\"}";
    	}
    	else
    	{
    		res_return="{msg:\"保存招标公告失败\"}";
    	}
    	writeJson_Str(response,res_return);       
    }
    
    
    public void updatezbgg()
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return="";
        Enumeration<String> paraNames_ = request.getParameterNames();
        Map<String, Object> params_all = this.dealParams(paraNames_,null);
    	if(ZtbDao.getInstance().updateproject_zbgg(params_all))
    	{
    		res_return= "{msg:\"ok\"}";
    	}
    	else
    	{
    		res_return="{msg:\"更新招标公告失败\"}";
    	}
    	writeJson_Str(response,res_return);       
    }
    
    public void delzbgg()
    {
        response.setContentType("text/html; charset=UTF-8");
        String id  = StringHelper.noEmpty2(request.getParameter("id")).trim();
        String res_return="";
    	if(ZtbDao.getInstance().delzbgg(id))
    	{
    		res_return= "{msg:\"ok\"}";
    	}
    	else
    	{
    		res_return="{msg:\"删除招标公告失败\"}";
    	}
    	writeJson_Str(response,res_return);
    }
    
    
    
    public void getzbgg()
    {
    	String id = CommonUtil.nullToDefault(request.getParameter("id"), "0");
        Map<String, Object> params_all = ZtbDao.getInstance().queryzbgg(id);
        writeGson(response,params_all);       
    }
    
    
    
    
    
    
    
    public String zbyqhList()
	{
		String operation = request.getParameter("operation");
		request.setAttribute("head", "招标文件、投标邀请函");
        if("init".equals(operation))
        {
            // 检索参数
            int pageNum = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagenum"), "1"))-1;
            int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "10"));
            Enumeration<String> paraNames = request.getParameterNames();
            Map<String, Object> params_all = this.dealParams(paraNames,null);
            //分页封装方法
            OraPaginatedList list_tmp = ZtbDao.getInstance().queryproject_zbyqh(params_all, pageNum, pageSize);
            // 分页对象
            PageData pg = CommonUtil.fomateResult(list_tmp.getList(), pageNum + 1, pageSize, list_tmp.getFullListSize());
            writeJson(response, CommonUtil.formatFGMap(pg));
            return null;
        } else 
        {
            return "zbyqh";
        }
	}
	
	
    public void savezbyqh()
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return="";
        Enumeration<String> paraNames_ = request.getParameterNames();
        Map<String, Object> params_all = this.dealParams(paraNames_,null);
        Admin admin = (Admin)session.getAttribute("admin");
    	if(ZtbDao.getInstance().saveproject_zbyqh(params_all,String.valueOf(admin.getAdminId())))
    	{
    		res_return= "{msg:\"ok\"}";
    	}
    	else
    	{
    		res_return="{msg:\"保存招标文件、投标邀请函失败\"}";
    	}
    	writeJson_Str(response,res_return);       
    }
    
    
    public void updatezbyqh()
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return="";
        Enumeration<String> paraNames_ = request.getParameterNames();
        Map<String, Object> params_all = this.dealParams(paraNames_,null);
    	if(ZtbDao.getInstance().updateproject_zbyqh(params_all))
    	{
    		res_return= "{msg:\"ok\"}";
    	}
    	else
    	{
    		res_return="{msg:\"更新招标文件、投标邀请函失败\"}";
    	}
    	writeJson_Str(response,res_return);       
    }
    
    public void delzbyqh()
    {
        response.setContentType("text/html; charset=UTF-8");
        String id  = StringHelper.noEmpty2(request.getParameter("id")).trim();
        String res_return="";
    	if(ZtbDao.getInstance().delzbyqh(id))
    	{
    		res_return= "{msg:\"ok\"}";
    	}
    	else
    	{
    		res_return="{msg:\"删除招标公告失败\"}";
    	}
    	writeJson_Str(response,res_return);
    }
    
    
    
    public void getzbyqh()
    {
    	String id = CommonUtil.nullToDefault(request.getParameter("id"), "0");
        Map<String, Object> params_all = ZtbDao.getInstance().queryzbyqh(id);
        writeGson(response,params_all);       
    }
    
    
    
    

}
