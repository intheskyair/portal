package com.zqkj.bsm.chart.action;

import java.util.List;
import java.util.Map;

import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.chart.dao.Peak_countInfoDao;
import com.zqkj.bsm.util.BHUtil;
import com.zqkj.bsm.util.CommonUtil;

public class Peak_countInfo extends BaseAction{

	/**
	 * 峰值统计
	 */
	private static final long serialVersionUID = 1L;

	
	public String init(){
		return "initPage";
	}
	
	public String peak_count_Init(){
		return "peak_count";
	}
	
	public String peak_task_count_Init(){
		return "peak_task_count";
	}
	
	public String queryPeakCount() throws Exception{
		String send_time1 = CommonUtil.nullToDefault(request.getParameter("send_time1"), "");
		String send_time2 = CommonUtil.nullToDefault(request.getParameter("send_time2"), "");
		//峰值类型  1：峰值人数；2：任务提交峰值；3：任务运行峰值
		int Peak_type = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("Peak_type"), "1"));
		int cycle = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("cycle"), "1")) ;
		Peak_countInfoDao dao = Peak_countInfoDao.getInstance();
		List<Map<String, Object>> pg = dao.queryPeakCount( send_time1,send_time2,cycle,Peak_type);
		if(cycle == 2){
			pg = BHUtil.weekToDate(pg);
		}
		writeJson(response, pg);
		return null;
	}
}
