package com.zqkj.bsm.chart.action;

import java.util.List;
import java.util.Map;

import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.chart.dao.Fee_deductionInfoDao;
import com.zqkj.bsm.util.BHUtil;
import com.zqkj.bsm.util.CommonUtil;

public class Fee_deductionInfo extends BaseAction {

	/**
	 * 扣费信息统计
	 */
	private static final long serialVersionUID = 1L;

	public String init(){
		return "initPage";
	}
	
	public String selfRenderInit(){
		return "selfRenderInit";
	}
	
	public String packTimeInit(){
		return "packTimeInit";
	}
	
	public String selfPackTimeInit(){
		return "selfPackTimeInit";
	}
	
	public String feeAverAgeTimeInit(){
		return "feeAverAgeTimeInit";
	}
	
	/**
	 * 按时间--- 自助渲染扣费 (分总、实扣，分max、maya、全部)
	 */
	public String queryJobsFee_self_trend() throws Exception{
		// 查询时间段
		String send_time1 = CommonUtil.nullToDefault(request.getParameter("send_time1"), "");
		String send_time2 = CommonUtil.nullToDefault(request.getParameter("send_time2"), "");
		int cycle = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("cycle"), "1")) ;
		Fee_deductionInfoDao dao = Fee_deductionInfoDao.getInstance();
		List<Map<String, Object>> pg = dao.queryJobsFee_self_trend( send_time1,send_time2,cycle);
		if(cycle == 2){
			pg = BHUtil.weekToDate(pg);
		}
		writeJson(response, pg);
		return null;
	}
	
	/**
	 * 按时间--- 套餐扣费
	 */
	public String queryJobsFee_trend() throws Exception{
		// 查询时间段
		String send_time1 = CommonUtil.nullToDefault(request.getParameter("send_time1"), "");
		String send_time2 = CommonUtil.nullToDefault(request.getParameter("send_time2"), "");
		int cycle = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("cycle"), "1")) ;
		Fee_deductionInfoDao dao = Fee_deductionInfoDao.getInstance();
		List<Map<String, Object>> pg = dao.queryJobsFee_trend( send_time1,send_time2,cycle);
		if(cycle == 2){
			pg = BHUtil.weekToDate(pg);
		}
		writeJson(response, pg);
		return null;
	}
	
	/**
	 * 按时间--- 套餐扣费+自助 (总、实扣)
	 */
	public String queryJobsFee_total_trend() throws Exception {
		// 查询时间段
		String send_time1 = CommonUtil.nullToDefault(request.getParameter("send_time1"), "");
		String send_time2 = CommonUtil.nullToDefault(request.getParameter("send_time2"), "");
		int cycle = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("cycle"), "1")) ;
		Fee_deductionInfoDao dao = Fee_deductionInfoDao.getInstance();
		List<Map<String, Object>> pg = dao.queryJobsFee_total_trend( send_time1,send_time2,cycle);
		if(cycle == 2){
			pg = BHUtil.weekToDate(pg);
		}
		writeJson(response, pg);
		return null;
	}
	/**
	 * 按区域------ 自助（总、实扣）
	 */
	public String queryJobsFee_self_place() throws Exception{
		// 查询时间段
		String send_time1 = CommonUtil.nullToDefault(request.getParameter("send_time1"), "");
		String send_time2 = CommonUtil.nullToDefault(request.getParameter("send_time2"), "");
		//排序------扣款总额：nature_money，实际扣款：nature_sc
		String order = CommonUtil.nullToDefault(request.getParameter("order"), "");
		Fee_deductionInfoDao dao = Fee_deductionInfoDao.getInstance();
		List<Map<String, Object>> pg = dao.queryJobsFee_self_place( send_time1,send_time2,order);
		writeJson(response, pg);
		return null;
	}
	/**
	 * 按区域------ 套餐（实扣）
	 */
	public String queryJobsFee_place() throws Exception{
		// 查询时间段
		String send_time1 = CommonUtil.nullToDefault(request.getParameter("send_time1"), "");
		String send_time2 = CommonUtil.nullToDefault(request.getParameter("send_time2"), "");
		Fee_deductionInfoDao dao = Fee_deductionInfoDao.getInstance();
		List<Map<String, Object>> pg = dao.queryJobsFee_place( send_time1,send_time2);
		writeJson(response, pg);
		return null;
	}
	/**
	 * 按区域------ 套餐扣费+自助 (总、实扣)
	 */
	public String queryJobsFee_total_place() throws Exception{
		// 查询时间段
		String send_time1 = CommonUtil.nullToDefault(request.getParameter("send_time1"), "");
		String send_time2 = CommonUtil.nullToDefault(request.getParameter("send_time2"), "");
		//排序------扣款总额：nature_money，实际扣款：nature_sc
		String order = CommonUtil.nullToDefault(request.getParameter("order"), "");
		Fee_deductionInfoDao dao = Fee_deductionInfoDao.getInstance();
		List<Map<String, Object>> pg = dao.queryJobsFee_total_place( send_time1,send_time2,order);
		writeJson(response, pg);
		return null;
	}
	/**
	 * 平均扣费 (总、实扣)
	 */
	public String queryFee_day() throws Exception{
		// 查询时间段
		String send_time1 = CommonUtil.nullToDefault(request.getParameter("send_time1"), "");
		String send_time2 = CommonUtil.nullToDefault(request.getParameter("send_time2"), "");
		Fee_deductionInfoDao dao = Fee_deductionInfoDao.getInstance();
		List<Map<String, Object>> pg = dao.queryFee_day( send_time1,send_time2);
		writeJson(response, pg);
		return null;
	}
}
