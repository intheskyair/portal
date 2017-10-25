package com.zqkj.bsm.log.action;

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
import com.zqkj.bsm.log.dao.LogoptDao;
import com.zqkj.bsm.system.action.SysPara;
import com.zqkj.bsm.system.action.util.StringHelper;
@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results( { @Result(name = "operation", location = "/WEB-INF/jsp/log/operationLogList.jsp"),  
            @Result(name = "error", location = "/failure.jsp")
        }) 
@ExceptionMappings( { @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") }) 
@Action(value = "operation")
public class OperationLogAction extends BaseAction
{
	private static final long serialVersionUID = -6607990332884068829L;

	/**
	 * 查询操作日志
	 * @return
	 * @throws Exception
	 */
	public String operationLogList() throws Exception 
	{
		
		Long num;
		Long start_num;
		Long next_num;
		Long updownbz= Long.valueOf(StringHelper.noEmptyNumic(request.getParameter("updownbz")));
		if(request.getParameter("nextnum")==null || request.getParameter("nextnum").equals(""))
		{
			num = new Long(0);
		}
		else
		{
		    num = Long.valueOf(request.getParameter("nextnum"));
		}
		String cz_user = StringHelper.noEmpty2(request.getParameter("cz_user"));
		String cz_time = StringHelper.noEmpty2(request.getParameter("cz_time"));
		//下一页
		if(updownbz==0)
		{
			start_num= num;
			next_num = num+Long.valueOf(SysPara.page_num);
		}
		//上一页
		else
		{
			next_num =num;
			start_num = num -Long.valueOf(SysPara.page_num);
			if(start_num<0)
			{
				start_num = new Long(0);
			}
			if(next_num<=0)
			{
				next_num = Long.valueOf(SysPara.page_num);
			}
		}
		LogoptDao dao = LogoptDao.getInstance();
		List<Map<String, Object>> log_list = dao.getLogAction(start_num, Long.valueOf(SysPara.page_num), cz_time, cz_user);
		if(updownbz==0)
		{
			if(log_list!=null && log_list.size() == SysPara.page_num)
			{
				request.setAttribute("nextnum", num+SysPara.page_num);
			}
			else if(log_list!=null && log_list.size() < SysPara.page_num)
			{
				request.setAttribute("nextnum", num);
			}
			else
			{
				request.setAttribute("nextnum", "");
			}
		}
		else
		{
				request.setAttribute("nextnum", num-SysPara.page_num);
		}
		request.setAttribute("log_list", log_list);
		request.setAttribute("cz_time", cz_time);
		request.setAttribute("cz_user", cz_user);
		return "operation";
	}
}
