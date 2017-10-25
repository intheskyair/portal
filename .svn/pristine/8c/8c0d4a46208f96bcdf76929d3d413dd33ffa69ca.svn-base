package com.zqkj.bsm.slProject.action;

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
import com.zqkj.bsm.dal.bean.Admin;
import com.zqkj.bsm.slProject.dao.ProposalDao;
import com.zqkj.bsm.system.action.page.OraPaginatedList;
import com.zqkj.bsm.system.action.util.StringHelper;
import com.zqkj.bsm.util.CommonUtil;
import com.zqkj.bsm.util.PageData;
@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results({ 
			@Result(name = "proposal", location = "/WEB-INF/jsp/slProject/proposal.jsp")  
        }) 
@ExceptionMappings({ 
	@ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") 
}) 
@Action(value = "proposal") 
/**
 * 水利工程建设
 * @author gf
 *
 */
public class ProposalAction extends BaseAction{

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
		List<Map<String, Object>> items = ProposalDao.getInstance().getExportDara(params_all);
		
		String file_name = "项目建议书列表";
		//导出的字段和excel表头的对应关系
        Map<String, String> map_columns = ProposalDao.getInstance().getList_Column();
		this.exportGK(items,map_columns, file_name);
    }
	
/**
 * 获取项目建议书列表
 * @return
 */
	public String getProposalList()
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
            OraPaginatedList list_tmp = ProposalDao.getInstance().queryProject_jys(params_all, pageNum, pageSize);
            // 分页对象
            PageData pg = CommonUtil.fomateResult(list_tmp.getList(), pageNum + 1, pageSize, list_tmp.getFullListSize());
            writeJson(response, CommonUtil.formatFGMap(pg));
            return null;
        } else 
        {
            return "proposal";
        }
	}
	
	
	
	/**
	 * 保存项目建议书
	 * @throws Exception
	 */
    public void saveProject_jys() throws Exception
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return="";
        Enumeration<String> paraNames_ = request.getParameterNames();
        Map<String, Object> params_all = this.dealParams(paraNames_,null);
        Admin admin = (Admin)session.getAttribute("admin");
    	if(ProposalDao.getInstance().saveProject_jys(params_all,String.valueOf(admin.getAdminId())))
    	{
    		res_return= "{msg:\"ok\"}";
    	}
    	else
    	{
    		res_return="{msg:\"保存项目建议书失败\"}";
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
     * 修改项目建议书
     * @throws Exception
     */
    public void updateProject_jys() throws Exception
    {
        response.setContentType("text/html; charset=UTF-8");
        Enumeration<String> paraNames_ = request.getParameterNames();
        Map<String, Object> params_all = this.dealParams(paraNames_,null);
        Admin admin = (Admin)session.getAttribute("admin");
        String res_return="";
        boolean flag = ProposalDao.getInstance().updateProject_jys(params_all,String.valueOf(admin.getAdminId()));
    	if(flag)
    	{
    		res_return= "{msg:\"ok\"}";
    	}
    	else
    	{
    		res_return="{msg:\"修改项目建议书失败\"}";
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
    
    public void deleteProject_jys()
    {
        response.setContentType("text/html; charset=UTF-8");
        String id  = StringHelper.noEmpty2(request.getParameter("id")).trim();
        String res_return="";
    	if(ProposalDao.getInstance().deleteProject_jys(id))
    	{
    		res_return= "{msg:\"ok\"}";
    	}
    	else
    	{
    		res_return="{msg:\"删除项目建议书失败\"}";
    	}
    	writeJson_Str(response,res_return);    
    }
    
}
