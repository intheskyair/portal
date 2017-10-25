package com.zqkj.bsm.system.action.util;

public class PlansUserInfoExport {
	public static String getColName(String key){
		String value = "";
		if ("PLANSID".equals(key)) {
			value = "套餐ID";
		}else if("USERID".equals(key)){
			value = "用户ID";
		}else if("APPLICATIONTIME".equals(key)){
			value = "申请时间";
		}else if("STARTDATE".equals(key)){
			value = "开始时间";
		}else if ("ENDDATE".equals(key)) {
			value = "结束时间";
		}else if ("STATUS".equals(key)) {
			value = "套餐状态";
		}else if ("QID".equals(key)) {
			value = "扣费流水号";
		}else if ("QIDTYPE".equals(key)) {
			value = "计费方式";
		}else if ("PLANSNAME".equals(key)) {
			value = "套餐名";
		}else if ("PLANSAMOUNT".equals(key)) {
			value = "套餐金额";
		}else if ("PLANSTYPE".equals(key)) {
			value = "套餐类型";
		}else if ("NODE".equals(key)) {
			value = "节点数";
		}else if ("WEIGHT".equals(key)) {
			value = "权值";
		}else if ("BJCOUNTS".equals(key)) {
			value = "包机台数";
		}else if ("BJDAYS".equals(key)) {
			value = "包机小时数";
		}else if ("SKYDRIVE".equals(key)) {
			value = "网盘容量";
		}else if ("NICKNAME".equals(key)) {
			value = "用户昵称";
		}
		return value;
	}
}
