package com.zqkj.bsm.sms.util;

public class BookExport {
	public static String getColName(String key){
		String value = "";
		if("id".equals(key)){
			value = "序号";
		}else if("user_name".equals(key)){
			value = "联系人";
		}else if("phone".equals(key)){
			value = "手机号码";
		}else if("email".equals(key)){
			value = "邮箱";
		}else if("status".equals(key)){
			value = "用户状态";
		}
		return value;
	}
}