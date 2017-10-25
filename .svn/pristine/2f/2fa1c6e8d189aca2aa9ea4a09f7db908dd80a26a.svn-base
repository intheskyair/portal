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
import com.zqkj.bsm.jsgl.dao.StartOperationDao;
import com.zqkj.bsm.system.action.page.OraPaginatedList;
import com.zqkj.bsm.system.action.util.StringHelper;
import com.zqkj.bsm.util.CommonUtil;
import com.zqkj.bsm.util.PageData;
@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results({ 
			@Result(name = "startoperation", location = "/WEB-INF/jsp/jsgl/startoperation.jsp")  
        }) 
@ExceptionMappings({ 
	@ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") 
}) 
@Action(value = "startoperation") 
/**
 * 开工备案
 * @author xj
 *
 */
public class StartOperationAction extends BaseAction{

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
		List<Map<String, Object>> items = StartOperationDao.getInstance().getExportDara(params_all);
		
		String file_name = "开工管理列表";
		//导出的字段和excel表头的对应关系
        Map<String, String> map_columns = StartOperationDao.getInstance().getList_Column();
		this.exportGK(items,map_columns, file_name);
    }
	
/**
 * 获取开工备案列表
 * @return
 */
	public String getStartOperationList()
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
            OraPaginatedList list_tmp = StartOperationDao.getInstance().queryStartOperation(params_all, pageNum, pageSize);
            // 分页对象
            PageData pg = CommonUtil.fomateResult(list_tmp.getList(), pageNum + 1, pageSize, list_tmp.getFullListSize());
            writeJson(response, CommonUtil.formatFGMap(pg));
            return null;
        } else 
        {
            return "startoperation";
        }
	}
	
	
	
	/**
	 * 保存开工备案
	 * @throws Exception
	 */
    public void addStartOperation() throws Exception
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return="";
        Enumeration<String> paraNames_ = request.getParameterNames();
        Map<String, Object> params_all = this.dealParams(paraNames_,null);
    	if(StartOperationDao.getInstance().addStartOperation(params_all))
    	{
    		res_return= "{msg:\"ok\"}";
    	}
    	else
    	{
    		res_return="{msg:\"保存开工备案失败\"}";
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
     * 修改开工备案
     * @throws Exception
     */
    public void updateStartOperation() throws Exception
    {
        response.setContentType("text/html; charset=UTF-8");
        Enumeration<String> paraNames_ = request.getParameterNames();
        Map<String, Object> params_all = this.dealParams(paraNames_,null);
        String res_return="";
        boolean flag = StartOperationDao.getInstance().updateStartOperation(params_all);
    	if(flag)
    	{
    		res_return= "{msg:\"ok\"}";
    	}
    	else
    	{
    		res_return="{msg:\"修改开工备案失败\"}";
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
     * 删除开工备案
     */
    public void deleteStartOperation()
    {
        response.setContentType("text/html; charset=UTF-8");
        String id  = StringHelper.noEmpty2(request.getParameter("id")).trim();
        String res_return="";
    	if(StartOperationDao.getInstance().deleteStartOperation(id))
    	{
    		res_return= "{msg:\"ok\"}";
    	}
    	else
    	{
    		res_return="{msg:\"删除开工备案失败\"}";
    	}
    	writeJson_Str(response,res_return);    
    }
    
}
