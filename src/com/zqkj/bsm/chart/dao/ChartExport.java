package com.zqkj.bsm.chart.dao;

public class ChartExport {
	public static String getLoginInforName(String key){
		String value = "";
		 if("USER_NAME".equals(key)){
			value = "用户名";
		}else if("CREATE_TIME".equals(key)){
			value = "注册时间";
		}else if("PHONE".equals(key)){
			value = "手机号";
		}else if("QQ".equals(key)){
			value = "QQ";
		}else if("EMAIL".equals(key)){
			value = "邮箱";
		}else if("PRCITY".equals(key)){
			value = "所在地";
		}else if("LAST_LOGINTIME".equals(key)){
			value = "最后登陆时间";
		}
		 return value;
	}
	public static String getRechargeInforName(String key){
		String value = "";
		 if("NICKNAME".equals(key)){
			value = "用户名";
		}else if("PHONE".equals(key)){
			value = "手机号";
		}else if("QQ".equals(key)){
			value = "QQ";
		}else if("EMAIL".equals(key)){
			value = "邮箱";
		}else if("PRCITY".equals(key)){
			value = "所在地";
		}else if("RECHARGE_MONEY".equals(key)){
			value = "充值金额";
		}else if("LAST_SUBTIME_FORMAT_DATE".equals(key)){
			value = "最后充值时间";
		}else if("COST_MONEY".equals(key)){
			value = "消费金额";
		}else if("TIME_HE".equals(key)){
			value = "渲染核小时";
		}
		 return value;
	}
}
