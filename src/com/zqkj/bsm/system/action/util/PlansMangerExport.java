package com.zqkj.bsm.system.action.util;

public class PlansMangerExport {
	public static String getColName(String key){
		String value = "";
		if ("ID".equals(key)) {
			value = "套餐ID";
		}else if("PLANSNAME".equals(key)){
			value = "套餐名";
		}else if("PLANSAMOUNT".equals(key)){
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
		}else if ("STATUS".equals(key)) {
			value = "状态";
		}else if ("QIDTYPE".equals(key)) {
			value = "扣费方式";
		}
		return value;
	}
}
