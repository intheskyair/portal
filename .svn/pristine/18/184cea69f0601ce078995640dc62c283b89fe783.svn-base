package com.zqkj.bsm.chart.action;

import java.util.List;
import java.util.Map;

import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.chart.dao.Hours_unitInfoDao;
import com.zqkj.bsm.util.BHUtil;
import com.zqkj.bsm.util.CommonUtil;

public class Hours_unitInfo extends BaseAction{

	/**
	 * 核小时单价
	 */
	private static final long serialVersionUID = 1L;

	public String init(){
		return "initPage";
	}
	
	public String hour_self_render_Init(){
		return "hour_self_render_Init";
	}
	
	public String hour_package_render_Init(){
		return "hour_package_render_Init";
	}
	
	public String fact_total_render_Init(){
		return "fact_total_render_Init";
	}
	/**
	 * 套餐核小时单价  套餐扣费/实际渲染核小时
	 */
	public String queryJobsFeeHour_trend() throws Exception{
		// 查询时间段
		String send_time1 = CommonUtil.nullToDefault(request.getParameter("send_time1"), "");
		String send_time2 = CommonUtil.nullToDefault(request.getParameter("send_time2"), "");
		int cycle = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("cycle"), "1")) ;
		Hours_unitInfoDao dao = Hours_unitInfoDao.getInstance();
		List<Map<String, Object>> pg = dao.queryJobsFeeHour_trend( send_time1,send_time2,cycle);
		if(cycle == 2){
			pg = BHUtil.weekToDate(pg);
		}
		writeJson(response, pg);
		return null;
	}
	/**
	 * 自助核小时单价  max、maya、全部（总、实扣）
	 */
	public String queryJobsFeeHour_self_trend() throws Exception{
		// 查询时间段
		String send_time1 = CommonUtil.nullToDefault(request.getParameter("send_time1"), "");
		String send_time2 = CommonUtil.nullToDefault(request.getParameter("send_time2"), "");
		int cycle = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("cycle"), "1")) ;
		Hours_unitInfoDao dao = Hours_unitInfoDao.getInstance();
		List<Map<String, Object>> pg = dao.queryJobsFeeHour_self_trend( send_time1,send_time2,cycle);
		if(cycle == 2){
			pg = BHUtil.weekToDate(pg);
		}
		writeJson(response, pg);
		return null;
	}
	
	/**
	 * 实际总核小时单价(总，实扣)
	 */
	public String queryJobsFeeHour_total_trend() throws Exception{
		// 查询时间段
		String send_time1 = CommonUtil.nullToDefault(request.getParameter("send_time1"), "");
		String send_time2 = CommonUtil.nullToDefault(request.getParameter("send_time2"), "");
		int cycle = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("cycle"), "1")) ;
		Hours_unitInfoDao dao = Hours_unitInfoDao.getInstance();
		List<Map<String, Object>> pg = dao.queryJobsFeeHour_total_trend( send_time1,send_time2,cycle);
		if(cycle == 2){
			pg = BHUtil.weekToDate(pg);
		}
		writeJson(response, pg);
		return null;
	}
}
