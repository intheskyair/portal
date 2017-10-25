package com.cudatec.flow.framework.action;
/*
 * 提醒
 */
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import com.cudatec.flow.framework.service.ApprovalManager;
import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.dal.bean.Admin;


@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results( { @Result(name = "all", location = "/WEB-INF/jsp/flow/all/all.jsp"),  
            @Result(name = "error", location = "/failure.jsp")
        }) 
@ExceptionMappings( { @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") }) 
public class MessageAction extends BaseAction
{

	@Autowired
	ApprovalManager manager;
	/**
	 * 
	 */
	private static final long serialVersionUID = 3171986565010659640L;
	
	@Action(value = "message") 
	/**
	 * 获取需要提醒的流程数据
	 */
	public void message()
	{		
		Admin admin = (Admin)session.getAttribute("admin");
		if(admin!=null)
		{
		   writeJson(response, manager.queryMessage(admin.getName()));
		}
	}

}
