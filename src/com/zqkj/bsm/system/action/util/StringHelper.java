package com.zqkj.bsm.system.action.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class StringHelper 
{
	public static String noEmpty(Object obj){
		if(obj==null)
		{
			return "";
		}else
		{
			String obj_string = obj.toString().trim();
			obj_string = obj_string.replaceAll("'", "''");
			return obj_string;
		}
	}
	public static String noEmpty2(Object obj){
		if(obj==null)
		{
			return "";
		}else
		{
			return obj.toString();
		}
	}
	
	public static String noEmptyNumic(Object obj)
	{
		if(obj==null)
		{
			return "0";
		}
		else if(obj.toString().trim().equals(""))
		{
			return "0";
		}
		else
		{
			String obj_string = obj.toString().trim();
			obj_string = obj_string.replaceAll("'", "''");
			return obj_string;
		}
	}
	
	public static String processFileName(HttpServletRequest request,String fileName) throws UnsupportedEncodingException{
		String codedfilename = null;
		String agent = request.getHeader("USER-AGENT");
		if(agent != null && (agent.indexOf("MSIE") != -1 || agent.indexOf("Trident") != -1)){
			codedfilename = java.net.URLEncoder.encode(fileName,"UTF-8");
		}else{
			codedfilename = new String((fileName).getBytes("UTF-8"),"ISO8859-1");
		}
		return codedfilename;
	}
}
