package com.zqkj.bsm.log.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
import com.zqkj.bsm.email.StorageSer;
import com.zqkj.bsm.log.dao.LogoptDao;
import com.zqkj.bsm.system.action.SysPara;
import com.zqkj.bsm.system.action.util.StringHelper;
@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results( { @Result(name = "logForPortal", location = "/WEB-INF/jsp/log/logList.jsp"),  
            @Result(name = "storageinfo", location = "/WEB-INF/jsp/log/storageinfo.jsp"),
            @Result(name = "ioinfo", location = "/WEB-INF/jsp/Storage/IoList.jsp"),
            @Result(name = "error", location = "/failure.jsp")
        }) 
@ExceptionMappings( { @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") }) 
public class LogManagerAction extends BaseAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6775077179760824207L;

	/**
	 * 查询运维登陆日志
	 * @return
	 * @throws Exception
	 */
	@Action(value = "log") 
	public String logForPortal() throws Exception 
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
		String ipadd = StringHelper.noEmpty2(request.getParameter("ipadd"));
		String logintime = StringHelper.noEmpty2(request.getParameter("logintime"));
		String username = StringHelper.noEmpty2(request.getParameter("username"));
		String phoneNO = StringHelper.noEmpty2(request.getParameter("phoneNO"));
		String email = StringHelper.noEmpty2(request.getParameter("email"));
		String useros = StringHelper.noEmpty2(request.getParameter("useros"));
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
		List<Map<String, Object>> list = dao.getLogforPortal(start_num, Long.valueOf(SysPara.page_num), ipadd, logintime, username,email,phoneNO,useros);
		if(updownbz==0)
		{
			if(list!=null && list.size() == SysPara.page_num)
			{
				request.setAttribute("nextnum", num+SysPara.page_num);
			}
			else if(list!=null && list.size() < SysPara.page_num)
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
		request.setAttribute("list", list);
		request.setAttribute("ipadd", ipadd);
		request.setAttribute("logintime", logintime);
		request.setAttribute("username", username);
		request.setAttribute("useros", useros);
		request.setAttribute("email", email);
		request.setAttribute("phoneNO", phoneNO);
		return "logForPortal";
	}
	
	
	/**
	 * 存储使用情况概要
	 * @return
	 * @throws Exception
	 */
	public String logForStorage() throws Exception 
	{
	
        String operation = request.getParameter("operation");
        if("init".equals(operation))
        {
		  Process exec = Runtime.getRuntime().exec("df -h");
		  
		  InputStream inputStream = exec.getInputStream();
		  InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		  BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		 
		  StringBuilder stringBuilder = new StringBuilder(10);
		  try {
		 
		   String tmp;
		   while ((tmp = bufferedReader.readLine()) != null) 
		   {
		    stringBuilder.append(tmp);
		    stringBuilder.append("<br>");
		   }
		 
		  } catch (IOException e) 
		  {
		     e.printStackTrace();
		  } finally 
		  {
		     bufferedReader.close();	 
		  }	
		 // request.setAttribute("info", stringBuilder.toString());
	      writeJson(response, stringBuilder.toString());
	      return null;
	}
    else 
    {
        return "storageinfo";
    }
  }
	
	
	public String ioinfo() throws Exception 
	{
		Map<String, ArrayList<Map<String, Object>>> map = StorageSer.getInstance().getStorageIo();
		request.setAttribute("map", map);
		return "ioinfo";
	}
	
}
