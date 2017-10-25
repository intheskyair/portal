package com.zqkj.bsm.system.action;

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
import org.json.simple.JSONValue;

import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.system.action.dao.CrosswiseDao;
import com.zqkj.bsm.util.CommonUtil;

@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results( { @Result(name = "crossTab", location = "/views/shell/crosswise_tab.jsp")
        }) 
@ExceptionMappings( { @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") })
@Action(value="cross")
public class CrosswiseAction extends BaseAction{
	private static final long serialVersionUID = 1L;

	public String toCrosswiseTab(){
		String menuId = CommonUtil.nullToDefault(request.getParameter("key"),"");
		if(!"".equals(menuId)){
			List<Map<String,Object>> list = CrosswiseDao.getNewInstance().getSubMenu(menuId);
			request.setAttribute("submenu", JSONValue.toJSONString(list));
		}
		return "crossTab";
	}
}
