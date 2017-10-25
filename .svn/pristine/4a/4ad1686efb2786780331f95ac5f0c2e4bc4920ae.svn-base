package com.zqkj.bsm.boardroom.action;


import java.net.URLDecoder;
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
import com.zqkj.bsm.boardroom.dao.BespeakDao;
import com.zqkj.bsm.system.action.page.OraPaginatedList;
import com.zqkj.bsm.system.action.util.StringHelper;
import com.zqkj.bsm.util.CommonUtil;
import com.zqkj.bsm.util.PageData;

@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results({ 
			@Result(name = "boardroom_infor", location = "/WEB-INF/jsp/boardroom/boardroom.jsp") ,
			@Result(name = "bespeak", location = "/WEB-INF/jsp/boardroom/bespeak.jsp") ,
		
        }) 
@ExceptionMappings({ 
	@ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") 
}) 
@Action(value = "boardroom") 
/**
 * 会议室管理
 * @author Kim
 *
 */
public class BespeakAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 会议室查询
	 * @return
	 */
	public String boardroom_infor()
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
            OraPaginatedList list_tmp = BespeakDao.getInstance().queryboardroom(params_all, pageNum, pageSize);
            // 分页对象
            PageData pg = CommonUtil.fomateResult(list_tmp.getList(), pageNum + 1, pageSize, list_tmp.getFullListSize());
            writeJson(response, CommonUtil.formatFGMap(pg));
            return null;
        } else 
        {
            return "boardroom_infor";
        }
	}
	
	/**
	 * 新增会议室
	 * @return
	 */
	public void addBoardroom()throws Exception{
		 response.setContentType("text/html; charset=UTF-8");
	        String res_return="";
	        Enumeration<String> paraNames_ = request.getParameterNames();
	        Map<String, Object> params_all = this.dealParams(paraNames_,null);
	      
	    	if(BespeakDao.getInstance().addBoardroom(params_all))
	    	{
	    		res_return= "{msg:\"ok\"}";
	    	}
	    	else
	    	{
	    		res_return="{msg:\"新增会议室失败\"}";
	    	}
	    	writeJson_Str(response,res_return);    
	    }
	    
	/**
	 * 修改会议室信息
	 * @throws Exception
	 */
	 public void editBoardroom() throws Exception
	    {
	        response.setContentType("text/html; charset=UTF-8");
	        Enumeration<String> paraNames_ = request.getParameterNames();
	        Map<String, Object> params_all = this.dealParams(paraNames_,null);
	        String res_return="";
	    	if(BespeakDao.getInstance().updateboardroom(params_all))
	    	{
	    		res_return= "{msg:\"ok\"}";
	    	}
	    	else
	    	{
	    		res_return="{msg:\"修改会议室信息失败\"}";
	    	}
	    	writeJson_Str(response,res_return);    
	    }
	
	 /**
	  * 删除会议室
	  * @throws Exception
	  */
	  public void deleteBoardroom() throws Exception
	    {
	        response.setContentType("text/html; charset=UTF-8");
	        String id  = StringHelper.noEmpty2(request.getParameter("id")).trim();
	        String res_return="";
	    	if(BespeakDao.getInstance().deleteBoardroom(id))
	    	{
	    		res_return= "{msg:\"ok\"}";
	    	}
	    	else
	    	{
	    		res_return="{msg:\"删除会议室失败\"}";
	    	}
	    	writeJson_Str(response,res_return);    
	    }
	 
	  
	  
	  /** 
	   * 删除会议预约
	   * @throws Exception
	   */
	  public void deleteBespeak() throws Exception
	    {   
		     
	        response.setContentType("text/html; charset=UTF-8");
	       
	        
	        String id  = StringHelper.noEmpty2(request.getParameter("id")).trim();
	        String truename  = StringHelper.noEmpty2(request.getParameter("truename")).trim();
	        
	        String res_return="";
	    	if(BespeakDao.getInstance().deleteBespeak(id,truename))
	    	{
	    		res_return= "{msg:\"ok\"}";
	    	}
	    	else
	    	{
	    		res_return="{msg:\"禁止删除其他人员的会议预约！\"}";
	    	}
	    	writeJson_Str(response,res_return);    
	    }
	  /**
	   * 获取可用的会议室信息
	   * @return
	   */
	  public String queryBoardroomByStatus(){
			List<Map<String, Object>> boardroom = BespeakDao.getInstance().queryBoardroomByStatus();
			writeJson(response, boardroom);
			return null;
		
		
		}
		/**
		 * 根据选取的会议室获取该会议室的预约详情
		 * @return
		 */
	  public String queryBoardroomByName() throws Exception{
		  response.setContentType("text/html; charset=UTF-8");
		  
	        String bname  = URLDecoder.decode(StringHelper.noEmpty2(request.getParameter("sboardroom")).trim(),"utf-8");
			List<Map<String, Object>> situation = BespeakDao.getInstance().queryBoardroomByName(bname);
			writeJson(response, situation);
			return null;
		
		
		}
	  
	/**
	 * 会议预约列表
	 * @return
	 */
	public String bespeak(){
		String operation = request.getParameter("operation");
        if("init".equals(operation))
        {
            // 检索参数
            int pageNum = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagenum"), "1"))-1;
            int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "10"));
            Enumeration<String> paraNames = request.getParameterNames();
            Map<String, Object> params_all = this.dealParams(paraNames,null);
            //分页封装方法
            OraPaginatedList list_tmp = BespeakDao.getInstance().myMeetingBespeak(params_all, pageNum, pageSize);
            // 分页对象
            PageData pg = CommonUtil.fomateResult(list_tmp.getList(), pageNum + 1, pageSize, list_tmp.getFullListSize());
            writeJson(response, CommonUtil.formatFGMap(pg));
            return null;
        } else 
        {
		
		return "bespeak";
	}
        }
	
	
	/**
	 * 会议预约
	 * @throws Exception
	 */
	public void bespeakBoardroom()throws Exception{
		
		 response.setContentType("text/html; charset=UTF-8");
	        String res_return="";
	        Enumeration<String> paraNames_ = request.getParameterNames();
	        Map<String, Object> params_all = this.dealParams(paraNames_,null);
	      
	    	if(BespeakDao.getInstance().bespeakBoardroom(params_all))
	    	{
	    		res_return= "{msg:\"ok\"}";
	    	}
	    	else 
	    	{
	    		res_return="{msg:\"在您的预约时间内该会议室被占用，请先核对会议室使用状态，重新预约！\"}";
	    	}
	    	writeJson_Str(response,res_return);    
	    }
}