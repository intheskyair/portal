package com.zqkj.bsm.sms.util;

public class ExportContent {
	public static String getColName(String key){
		String value = "";
		if("id".equals(key)){
			value = "序号";
		}else if("sys_name".equals(key)){
			value = "系统名称";
		}else if("phone".equals(key)){
			value = "手机号码";
		}else if("content".equals(key)){
			value = "信息内容";
		}else if("send_time".equals(key)){
			value = "发送时间";
		}else if("returnstatus".equals(key)){
			value = "返回状态";
		}else if("message".equals(key)){
			value = "状态描述";
		}
		return value;
	}
	
	public static String getName(String key){
		String value = "";
		if("id".equals(key)){
			value = "序号";
		}else if("time_day".equals(key)){
			value = "充值时间(按天)";
		 }else if("money".equals(key)){
			value = "充值金额";
		}else if("count".equals(key)){
			value = "成功条数";
		}
		return value;
	}
	
	public static String getEmailHead(String key){
		String value = "";
		if("send_time".equals(key)){
			value = "发送时间";
		}else if("sender".equals(key)){
			value = "邮件发送方";
		}else if("receive".equals(key)){
			value = "邮件接收方";
		}else if("title".equals(key)){
			value = "邮件标题";
		}else if("content".equals(key)){
			value = "邮件内容";
		}else if("state".equals(key)){
			value = "发送状态";
		}
		return value;
	}
}