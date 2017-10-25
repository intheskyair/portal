package com.zqkj.bsm.chart.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.chart.dao.RenderInfoDao;
import com.zqkj.bsm.util.BHUtil;
import com.zqkj.bsm.util.CommonUtil;

public class RenderInfo extends BaseAction {

	/**
	 * 渲染信息统计
	 */
	private static final long serialVersionUID = 1L;

	public String init() {
		return "initPage";
	}

	public String subPersonsInit(){
		return "subPersonsInit";
	}
	
	public String taskCountInit(){
		return "taskCountInit";
	}
	
	public String renderFramesInit(){
		return "renderFramesInit";
	}
	
	public String hoursUnitInit(){
		return "hoursUnitInit";
	}

	public String hoursFrameInit(){
		return "hoursFrameInit";
	}
	
	/**
	 * 按时间---查询任务数、渲染帧数、总核小时、单帧核小时（趋势图，自助渲染，分max、maya、全部）
	 */
	public String queryJobsInfo_trend() throws Exception{
		// 查询时间段
		String send_time1 = CommonUtil.nullToDefault(request.getParameter("send_time1"), "");
		String send_time2 = CommonUtil.nullToDefault(request.getParameter("send_time2"), "");
		int cycle = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("cycle"), "1")) ;
		RenderInfoDao dao = RenderInfoDao.getInstance();
		List<Map<String, Object>> pg = dao.queryJobsInfo_trend( send_time1,send_time2,cycle);
		if(cycle == 2){
			pg = BHUtil.weekToDate(pg);
		}
		writeJson(response, pg);
		return null;
	}
	/**
	 * 按时间---提交人（自助渲染，分max、maya、全部） 
	 */
	public String queryJobsPeople_trend() throws Exception{
		// 查询时间段
		String send_time1 = CommonUtil.nullToDefault(request.getParameter("send_time1"), "");
		String send_time2 = CommonUtil.nullToDefault(request.getParameter("send_time2"), "");
		int cycle = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("cycle"), "1")) ;
		RenderInfoDao dao = RenderInfoDao.getInstance();
		List<Map<String, Object>> pg = dao.queryJobsPeople_trend( send_time1,send_time2,cycle);
		if(cycle == 2){
			pg = BHUtil.weekToDate(pg);
		}
		writeJson(response, pg);
		return null;
	}
	
	/**
	 * 按地区---查询任务数、渲染帧数、总核小时、单帧核小时（分max、maya、全部）
	 */
	public String queryJobsInfo_place() throws Exception{
		// 查询时间段
		String send_time1 = CommonUtil.nullToDefault(request.getParameter("send_time1"), "");
		String send_time2 = CommonUtil.nullToDefault(request.getParameter("send_time2"), "");
		//排序------任务数：max_num/maya_num，总帧数：max_total_frame/maya_total_frame，
		//总核小时：max_seconds/maya_seconds,单帧核小时:max_hourHe_frame/maya_hourHe_frame (按全部排序 不用传order)
		String order = CommonUtil.nullToDefault(request.getParameter("order"), "");
		RenderInfoDao dao = RenderInfoDao.getInstance();
		List<Map<String, Object>> pg = dao.queryJobsInfo_place(send_time1,send_time2,order);
		writeJson(response, pg);
		return null;
	}
	/**
	 * 按地区---提交人（自助渲染，分max、maya、全部） 
	 */
	public String queryJobsPeople_place() throws Exception{
		// 查询时间段
		String send_time1 = CommonUtil.nullToDefault(request.getParameter("send_time1"), "");
		String send_time2 = CommonUtil.nullToDefault(request.getParameter("send_time2"), "");
		//排序------max提交人：max_man_num；maya提交人：maya_man_num (按全部排序 不用传order)
		String order = CommonUtil.nullToDefault(request.getParameter("order"), "");
		RenderInfoDao dao = RenderInfoDao.getInstance();
		List<Map<String, Object>> pg = dao.queryJobsPeople_place(send_time1,send_time2,order);
		writeJson(response, pg);
		return null;
	}
	
	public void query_JobsType(){
		// 查询时间段
		String send_time1 = CommonUtil.nullToDefault(request.getParameter("send_time1"), "");
		String send_time2 = CommonUtil.nullToDefault(request.getParameter("send_time2"), "");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cald = Calendar.getInstance();
		if(send_time2.length() == 0){
			cald.add(Calendar.DATE, -1);
			send_time2 = df.format(cald.getTime());
		}
		if(send_time1.length() == 0){
			cald.add(Calendar.DATE, -7);
			send_time1 = df.format(cald.getTime());
		}
		RenderInfoDao rid = RenderInfoDao.getInstance();
		List<Map<String, Object>> seedList = rid.getJobsForSpeed(new Object[]{send_time1,send_time2,
				send_time1,send_time2,send_time1,send_time2});
		
		writeJson(response, seedList);
	}
	
	public void query_JobsStatus(){
		// 查询时间段
		String send_time1 = CommonUtil.nullToDefault(request.getParameter("send_time1"), "");
		String send_time2 = CommonUtil.nullToDefault(request.getParameter("send_time2"), "");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cald = Calendar.getInstance();
		if(send_time2.length() == 0){
			cald.add(Calendar.DATE, -1);
			send_time2 = df.format(cald.getTime());
		}
		if(send_time1.length() == 0){
			cald.add(Calendar.DATE, -7);
			send_time1 = df.format(cald.getTime());
		}
		RenderInfoDao rid = RenderInfoDao.getInstance();
		Map<String,Object> statusMap = rid.getJobsForStatus(new Object[]{send_time1,send_time2});
		writeJson(response, statusMap);
	}
}
