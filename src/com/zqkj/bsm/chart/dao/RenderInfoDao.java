package com.zqkj.bsm.chart.dao;

import java.util.List;
import java.util.Map;

import com.zqkj.bsm.dal.dboper.DbOperator;

public class RenderInfoDao extends DbOperator
{
	
	private static volatile RenderInfoDao instance = null;
	
	private RenderInfoDao()
	{
		
	}
	public static RenderInfoDao getInstance()
	{
		if(instance==null)
		{
			synchronized (RenderInfoDao.class)
			{
			instance = new RenderInfoDao();
			}
		}
		return instance;
	}
	/**
	 * 按时间 
	 * @param send_time1
	 * @param send_time2
	 * @return
	 */
	public List<Map<String, Object>> queryJobsInfo_trend(String send_time1,String send_time2,int cycle) {
		String request_time = getTimeRequest(cycle,"statisticalTime");
    	String sql= "select "+request_time+" as time,"
    			+ "sum(missionNumber_self+missionNumber)as missionNumber,sum(mayaNumber_self+mayaNumber)as maya_missionNumber,sum(maxNumber_self+maxNumber)as max_missionNumber,"
    			+ "sum(successFrames_self+successFrames)as successFrames,sum(max_successFrames_self+max_successFrames)as max_successFrames,sum(maya_successFrames_self+maya_successFrames)as maya_successFrames,"
    			+ "sum(render_seconds_self+render_seconds)as hourHe,SUM(max_render_seconds_self+max_render_seconds)as max_hourHe,sum(maya_render_seconds_self+maya_render_seconds) as maya_hourHe,"
    			+ "if(sum(render_seconds_self+render_seconds)!=0.00,round(sum(render_seconds_self+render_seconds)/sum(successFrames_self+successFrames),4),0.0000)as hourHe_frame,"
    			+ "if(sum(max_render_seconds_self+max_render_seconds)!=0.00,round(sum(max_render_seconds_self+max_render_seconds)/sum(max_successFrames_self+max_successFrames),4),0.0000)as max_hourHe_frame,"
    			+ "if(sum(maya_render_seconds_self+maya_render_seconds)!=0.00,round(sum(maya_render_seconds_self+maya_render_seconds)/sum(maya_successFrames_self+maya_successFrames),4),0.0000)as maya_hourHe_frame "
    			+ "FROM statistical_day WHERE statisticalTime >='"+send_time1+"' and statisticalTime <='"+send_time2+"' GROUP BY "+ request_time;
		log.info("queryJobsInfo_trend="+sql);
		List<Map<String, Object>> list = getList_Pre(sql.toString(),null);
		return list;
	}
	/**
	 * 统计任务提交人数(自助 ) statisticalTime，manNum_maya，manNum_max，manNum_total
	 * @param send_time1
	 * @param send_time2
	 * @param cycle
	 * @return
	 */
	public List<Map<String, Object>> queryJobsPeople_trend(String send_time1,String send_time2, int cycle) {
		String request_time = getTimeRequest(cycle,"rm.finish_time");
		String sql= "SELECT E.*,F.manNum_total FROM (SELECT C.statisticalTime,sum(C.manNum_maya)as manNum_maya,sum(C.manNum_max) as manNum_max "
				+ "FROM (SELECT B.statisticalTime,IF(B.software =0,sum(peopleNum_self),0)as manNum_maya,IF(B.software =1,sum(peopleNum_self),0 )as manNum_max"
				+ " FROM (SELECT A.statisticalTime,count(A.user_id) AS peopleNum_self,A.software FROM"
				+ " (select W.* from (SELECT "+request_time+" AS statisticalTime,rm.user_id,if(rm.software like '%Maya%',0,1)as software FROM render_mission rm "
				+ "LEFT JOIN render_acc_detail rcd ON rm.trans_id = rcd.trans_id LEFT JOIN web_user wu ON rm.user_id = wu.id "
				+ "WHERE rm.online_stage = 7 and rm.render_type !=3 AND rm.finish_time IS NOT NULL AND (wu.type = 1 OR wu.type = 2) and wu.user_type = '注册用户' "
				+ "AND date_format(rm.finish_time, '%Y-%m-%d') <= '"+send_time2+"'AND date_format(rm.finish_time, '%Y-%m-%d') >= '"+send_time1
				+ "' GROUP BY "+request_time+",rm.user_id,rm.software)W group by W.user_id,W.software,W.statisticalTime) A GROUP BY A.statisticalTime,A.software)B"
				+ " GROUP BY B.statisticalTime,B.software)C GROUP BY C.statisticalTime) E,"
				+ "(SELECT D.statisticalTime,count(D.user_id) AS manNum_total FROM(SELECT "+request_time+" AS statisticalTime,rm.user_id"
				+ " FROM render_mission rm LEFT JOIN render_acc_detail rcd ON rm.trans_id = rcd.trans_id LEFT JOIN web_user wu ON rm.user_id = wu.id"
				+ " WHERE rm.online_stage = 7 and rm.render_type !=3 AND rm.finish_time IS NOT NULL AND (wu.type = 1 OR wu.type = 2) and wu.user_type = '注册用户' "
				+ "AND date_format(rm.finish_time, '%Y-%m-%d') <= '"+send_time2+"'AND date_format(rm.finish_time, '%Y-%m-%d') >= '"+send_time1
				+ "' GROUP BY "+request_time+",rm.user_id) D GROUP BY D.statisticalTime)F WHERE E.statisticalTime=F.statisticalTime";
		log.info("queryJobsPeople_trend="+sql);
		List<Map<String, Object>> list = getList_Pre(sql.toString(),null);
		return list;
	}

	public String getTimeRequest( int cycle ,String statisticalTime){
		String request_time = "";
		if(cycle==1){//按天
			request_time = "date_format("+statisticalTime+", '%Y-%m-%d')";
		}else if(cycle==2){//按周
			request_time = "date_format("+statisticalTime+", '%Y-%u')";
		}else if(cycle==3){//按月
			request_time = "date_format("+statisticalTime+", '%Y-%m')";
		}else if(cycle==4){//按季度
			request_time = "concat(date_format("+statisticalTime+",'%Y'),'-',quarter("+statisticalTime+"))";
		}
		return request_time;
	}
	/**
	 * 按地区总量趋势 max/maya
	 * @param send_time1
	 * @param send_time2
	 * @param cycle
	 * @return
	 */
	public List<Map<String, Object>> queryJobsInfo_place(String send_time1,String send_time2,String order) {		
		String sql= "SELECT D.province,C.max_num,C.max_total_frame,C.max_success_frame,C.max_seconds,C.max_hourHe_frame,"
				+ "D.maya_num,D.maya_total_frame,D.maya_success_frame,D.maya_seconds,D.maya_hourHe_frame FROM "
				+ "(SELECT a.province,count(a.render_id) as max_num,sum(a.total_frame) as max_total_frame,"
				+ "sum(a.success_frame)as max_success_frame,round(sum(a.seconds)/3600,4)as max_seconds,"
				+ "round((sum(a.seconds)/3600)/sum(a.success_frame),4)as max_hourHe_frame  FROM ("
				+ "SELECT if(wu.province is NULL or wu.province='','其他',wu.province)as province,rm.render_id,rm.total_frame,rm.success_frame,"
				+ "rm.failure_frame, rm.seconds,rcd.nature_money,rcd.nature_sc,rcd.nature_zs FROM render_mission rm "
				+ "LEFT JOIN render_acc_detail rcd ON rm.trans_id = rcd.trans_id LEFT JOIN web_user wu ON rm.user_id = wu.id"
				+ " WHERE rm.online_stage = 7 and rm.render_type !=3  AND rm.software LIKE '%Max%'  AND rm.finish_time IS NOT NULL "
				+ "and date_format(rm.finish_time, '%Y-%m-%d')<='"+send_time2+"' and date_format(rm.finish_time, '%Y-%m-%d')>='"
				+send_time1+"')a GROUP BY left(a.province,2))C right JOIN (SELECT b.province,count(b.render_id) as maya_num,"
				+ "sum(b.total_frame) as maya_total_frame,sum(b.success_frame)as maya_success_frame,round(sum(b.seconds)/3600,4)as maya_seconds,"
				+ "round((sum(b.seconds)/3600)/sum(b.success_frame),4)as maya_hourHe_frame  FROM ("
				+ "SELECT if(wu.province is NULL or wu.province='' ,'其他',wu.province)as province,rm.render_id,rm.total_frame,rm.success_frame,"
				+ "rm.failure_frame, rm.seconds,rcd.nature_money,rcd.nature_sc,rcd.nature_zs FROM render_mission rm "
				+ "LEFT JOIN render_acc_detail rcd ON rm.trans_id = rcd.trans_id LEFT JOIN web_user wu ON rm.user_id = wu.id"
				+ " WHERE rm.online_stage = 7 and rm.render_type !=3 AND rm.software LIKE '%Maya%'  AND rm.finish_time IS NOT NULL and wu.user_type = '注册用户' "
				+ "and date_format(rm.finish_time, '%Y-%m-%d')<='"+send_time2+"' and date_format(rm.finish_time, '%Y-%m-%d')>='"+send_time1+"')b "
				+ "GROUP BY left(b.province,2))D ON left(C.province,2) = left(D.province,2) UNION "
				+ "SELECT G.*,H.maya_num,H.maya_total_frame,H.maya_success_frame,H.maya_seconds,H.maya_hourHe_frame FROM "
				+ "(SELECT e.province,count(e.render_id) as max_num,sum(e.total_frame) as max_total_frame,"
				+ "sum(e.success_frame)as max_success_frame,round(sum(e.seconds)/3600,4)as max_seconds,"
				+ "round((sum(e.seconds)/3600)/sum(e.success_frame),4)as max_hourHe_frame  FROM ("
				+ "SELECT if(wu.province is NULL or wu.province='' ,'其他',wu.province)as province,rm.render_id,rm.total_frame,rm.success_frame,"
				+ "rm.failure_frame, rm.seconds,rcd.nature_money,rcd.nature_sc,rcd.nature_zs FROM render_mission rm "
				+ "LEFT JOIN render_acc_detail rcd ON rm.trans_id = rcd.trans_id LEFT JOIN web_user wu ON rm.user_id = wu.id"
				+ " WHERE rm.online_stage = 7 and rm.render_type !=3 AND rm.software LIKE '%Max%'  AND rm.finish_time IS NOT NULL and wu.user_type = '注册用户' "
				+ "and date_format(rm.finish_time, '%Y-%m-%d')<='"+send_time2+"' and date_format(rm.finish_time, '%Y-%m-%d')>='"
				+send_time1+"')e GROUP BY left(e.province,2))G LEFT JOIN (SELECT f.province,count(f.render_id) as maya_num,"
				+ "sum(f.total_frame) as maya_total_frame,sum(f.success_frame)as maya_success_frame,round(sum(f.seconds)/3600,4)as maya_seconds,"
				+ "round((sum(f.seconds)/3600)/sum(f.success_frame),4)as maya_hourHe_frame  FROM ("
				+ "SELECT if(wu.province is NULL or wu.province='','其他',wu.province)as province,rm.render_id,rm.total_frame,rm.success_frame,"
				+ "rm.failure_frame, rm.seconds,rcd.nature_money,rcd.nature_sc,rcd.nature_zs FROM render_mission rm "
				+ "LEFT JOIN render_acc_detail rcd ON rm.trans_id = rcd.trans_id LEFT JOIN web_user wu ON rm.user_id = wu.id "
				+ "WHERE rm.online_stage = 7 and rm.render_type !=3 AND rm.software LIKE '%Maya%'  AND rm.finish_time IS NOT NULL and wu.user_type = '注册用户' "
				+ "and date_format(rm.finish_time, '%Y-%m-%d')<='"+send_time2+"' and date_format(rm.finish_time, '%Y-%m-%d')>='"+send_time1+"')f "
				+ "GROUP BY left(f.province,2))H ON left(H.province,2) = left(G.province,2)  ";
		if(order!=""||!order.equals("")){
			sql += " order by "+order+" desc";
		}
		log.info("queryJobsInfo_place="+sql);
		List<Map<String, Object>> list = getList_Pre(sql.toString(),null);
		return list;
	}
	
	/**
	 * 按区域 提交人 max、maya、全部
	 * @param send_time1
	 * @param send_time2
	 * @return
	 */
	public List<Map<String, Object>> queryJobsPeople_place( String send_time1,String send_time2,String order) {
		String sql ="select  K.*,W.total_man_num FROM "
				+ "(SELECT C.province,C.max_man_num,D.maya_man_num FROM(SELECT a.province,count(a.user_id)as max_man_num "
				+ "FROM (SELECT if(wu.province is null or wu.province='','其他',wu.province)as province,rm.user_id FROM render_mission rm "
				+ "LEFT JOIN render_acc_detail rcd ON rm.trans_id = rcd.trans_id LEFT JOIN web_user wu ON rm.user_id = wu.id "
				+ "WHERE rm.online_stage = 7 and rm.render_type !=3 AND rm.finish_time IS NOT NULL AND (wu.type = 1 or wu.type = 2) and wu.user_type = '注册用户' AND "
				+ "date_format(rm.finish_time, '%Y-%m-%d') <='"+send_time2+"' AND rm.software LIKE '%Max%' AND date_format(rm.finish_time, '%Y-%m-%d')>='"
				+send_time1+"' GROUP BY rm.user_id )a GROUP BY left(a.province,2))C LEFT  JOIN "
				+ "(SELECT b.province,count(b.user_id)as maya_man_num FROM "
				+ "(SELECT if(wu.province is null or wu.province='','其他',wu.province)as province,rm.user_id FROM render_mission rm "
				+ "LEFT JOIN render_acc_detail rcd ON rm.trans_id = rcd.trans_id LEFT JOIN web_user wu ON rm.user_id = wu.id "
				+ "WHERE rm.online_stage = 7 and rm.render_type !=3 AND rm.finish_time IS NOT NULL AND (wu.type = 1 or wu.type = 2)  and wu.user_type = '注册用户' AND "
				+ "date_format(rm.finish_time, '%Y-%m-%d') <='"+send_time2+"' AND rm.software LIKE '%Maya%'AND date_format(rm.finish_time, '%Y-%m-%d')>='"
				+send_time1+"' GROUP BY rm.user_id )b GROUP BY left(b.province,2))D ON LEFT(C.province,2) = LEFT(D.province,2)"
				+ "UNION "
				+ "SELECT G.province,G.max_man_num,H.maya_man_num FROM(SELECT e.province,count(e.user_id)as max_man_num FROM "
				+ "(SELECT if(wu.province is null or wu.province='','其他',wu.province)as province,rm.user_id FROM render_mission rm "
				+ "LEFT JOIN render_acc_detail rcd ON rm.trans_id = rcd.trans_id LEFT JOIN web_user wu ON rm.user_id = wu.id "
				+ "WHERE rm.online_stage = 7 and rm.render_type !=3 AND rm.finish_time IS NOT NULL AND (wu.type = 1 or wu.type = 2)  and wu.user_type = '注册用户' AND "
				+ "date_format(rm.finish_time, '%Y-%m-%d') <='"+send_time2+"' AND rm.software LIKE '%Max%' AND date_format(rm.finish_time, '%Y-%m-%d')>='"
				+send_time1+"' GROUP BY rm.user_id )e GROUP BY left(e.province,2))G RIGHT  JOIN "
				+ "(SELECT f.province,count(f.user_id)as maya_man_num FROM "
				+ "(SELECT if(wu.province is null or wu.province='','其他',wu.province)as province,rm.user_id FROM render_mission rm "
				+ "LEFT JOIN render_acc_detail rcd ON rm.trans_id = rcd.trans_id LEFT JOIN web_user wu ON rm.user_id = wu.id "
				+ "WHERE rm.online_stage = 7 and rm.render_type !=3 AND rm.finish_time IS NOT NULL AND (wu.type = 1 or wu.type = 2) and wu.user_type = '注册用户' AND "
				+ "date_format(rm.finish_time, '%Y-%m-%d') <='"+send_time2+"' AND rm.software LIKE '%Maya%'AND date_format(rm.finish_time, '%Y-%m-%d')>='"
				+send_time1+"' GROUP BY rm.user_id )f GROUP BY left(f.province,2))H ON LEFT(G.province,2) = LEFT(H.province,2) )K,"
				+"(SELECT J.province,count(J.user_id)as total_man_num FROM "
				+ "(SELECT if(wu.province is null or wu.province='','其他',wu.province)as province,rm.user_id FROM render_mission rm "
				+ "LEFT JOIN render_acc_detail rcd ON rm.trans_id = rcd.trans_id LEFT JOIN web_user wu ON rm.user_id = wu.id "
				+ "WHERE rm.online_stage = 7 and rm.render_type !=3 AND rm.finish_time IS NOT NULL AND (wu.type = 1 or wu.type = 2) and wu.user_type = '注册用户' AND "
				+ "date_format(rm.finish_time, '%Y-%m-%d') <='"+send_time2+"' AND date_format(rm.finish_time, '%Y-%m-%d')>='"
				+send_time1+"' GROUP BY rm.user_id )J GROUP BY left(J.province,2)) W where W.province = K.province";
		if(order!=""||!order.equals("")){
			sql += " order by "+order+" desc";
		}
		List<Map<String, Object>> list = getList_Pre(sql.toString(),null);
		log.info("queryJobsPeople_place="+sql);
		return list;
	}
	
	/**
	 * 统计业务类型，加速，极速，和普通
	 * @param obj1
	 * @param obj2
	 * @return
	 * @author 0281
	 */
	public List<Map<String, Object>> getJobsForSpeed(Object[] obj1){
		String sql = "SELECT @rownum :=@rownum + 1 rownum,d.* FROM (SELECT @rownum := 0) r,("
				+ "SELECT c.parameter_name,REPLACE (c.qj, '$', '~') qj,CASE "
				+ "WHEN c.parameter_name_e = 'render_rate' THEN c.render_rate_total ELSE "
				+ "c.total END total,CASE WHEN c.parameter_name_e = 'render_rate' "
				+ "AND c.qj = '普通' THEN ROUND(TRUNCATE ((c.render_rate_total - c.total) "
				+ "/ c.render_rate_total,5),4) WHEN c.parameter_name_e = 'render_rate' "
				+ "AND c.qj <> '普通' THEN ROUND(TRUNCATE (sum(c.qj_num) / c.render_rate_total,5),"
				+ "4) ELSE ROUND(TRUNCATE (sum(c.qj_num) / c.total, 5),4) END percent,CASE "
				+ "WHEN c.parameter_name_e = 'render_rate' AND c.qj = '普通' THEN "
				+ "c.render_rate_total - c.total ELSE sum(c.qj_num) END qj_total FROM ("
				+ "SELECT a.*, b.totalnum,b.price_value,CASE WHEN a.price_type = 1 "
				+ "AND b.price_value >= SUBSTRING_INDEX(a.qj, '$', 1) + 0 "
				+ "AND b.price_value <= SUBSTRING_INDEX(a.qj, '$' ,- 1) + 0 THEN b.totalnum "
				+ "WHEN (a.price_type = 2 OR a.price_type = 3) AND b.price_value = a.qj THEN "
				+ "b.totalnum WHEN a.price_type = 4 THEN b.totalnum ELSE 0 END qj_num,c.total,"
				+ "e.render_rate_total FROM (SELECT t.parameter_name,t.parameter_name_e,t.price_type,"
				+ "CASE WHEN t.price_type = 1 THEN SUBSTRING_INDEX(t1.price_value, '$', 2) "
				+ "WHEN t.price_type = 2 OR t.price_type = 3 THEN SUBSTRING_INDEX(t1.price_value, '$', 1) "
				+ "ELSE '1' END qj FROM render_price_param t RIGHT JOIN render_price_"
				+ "section t1 ON t.id = t1.price_param_id WHERE t.parameter_name_e = 'render_rate') a "
				+ "LEFT JOIN (SELECT t.price_param,count(1) totalnum,CASE "
				+ "WHEN t.price_param = 'resolution' THEN SUBSTRING_INDEX(t.price_value, '*', 1) * "
				+ "SUBSTRING_INDEX(t.price_value, '*' ,- 1) ELSE t.price_value END price_value "
				+ "FROM render_mission_price t LEFT JOIN render_mission t1 ON t1.trans_id = t.trans_id "
				+ "WHERE t.price_param = 'render_rate' "
				+ "AND t1.create_time >= DATE_FORMAT(?, '%Y-%m-%d') "
				+ "and t1.create_time <= DATE_FORMAT(?, '%Y-%m-%d') "
				+ "GROUP BY t.price_value) b ON a.parameter_name_e = b.price_param "
				+ "LEFT JOIN (SELECT t.price_param parameter_name,count(1) total FROM "
				+ "render_mission_price t LEFT JOIN render_mission t1 ON t1.trans_id = t.trans_id "
				+ "WHERE t.price_param = 'render_rate' AND "
				+ "t1.create_time >= DATE_FORMAT(?, '%Y-%m-%d') and "
				+ "t1.create_time <= DATE_FORMAT(?, '%Y-%m-%d')) c ON "
				+ "c.parameter_name = a.parameter_name_e LEFT JOIN (SELECT "
				+ "'render_rate' parameter_name,count(1) render_rate_total FROM render_mission rm "
				+ "WHERE rm.create_time >= DATE_FORMAT(?, '%Y-%m-%d') "
				+ "and rm.create_time <= DATE_FORMAT(?, '%Y-%m-%d')) e ON "
				+ "e.parameter_name = a.parameter_name_e) c GROUP BY "
				+ "c.render_rate_total,c.total,c.parameter_name,c.parameter_name_e,c.qj "
				+ "ORDER BY SUBSTRING_INDEX(c.qj, '$', 1) * 1 ASC) d";
		return this.getList_Pre(sql, obj1);
	}
	
	/**
	 * 统计任务状态:渲染成功,渲染失败,已取消
	 * @param obj
	 * @return
	 * @author 0281
	 */
	public Map<String,Object> getJobsForStatus(Object[] obj){
		String sql = "SELECT count(rmv.res_info) as total,rmv.finish_time,"
				+ "sum(case when rmv.res_info = '渲染成功' then 1 else 0 end) as succes,"
				+ "sum(case when rmv.res_info = '渲染失败' then 1 else 0 end) as fail,"
				+ "sum(case when rmv.res_info = '已取消' then 1 else 0 end) as cancal,"
				+ "TRUNCATE((sum(case when rmv.res_info = '渲染成功' then 1 else 0 end) / "
				+ "count(rmv.res_info)),4) as succes_percent,"
				+ "TRUNCATE((sum(case when rmv.res_info = '渲染失败' then 1 else 0 end) / "
				+ "count(rmv.res_info)),4) as fail_percent,"
				+ "TRUNCATE((sum(case when rmv.res_info = '已取消' then 1 else 0 end) / "
				+ "count(rmv.res_info)),4) as cancal_percent FROM render_mission_view rmv "
				+ "WHERE rmv.res_info IS NOT NULL AND rmv.finish_time is not null "
				+ "and rmv.finish_time >= DATE_FORMAT(?,'%Y-%m-%d') "
				+ "and rmv.finish_time <= DATE_FORMAT(?,'%Y-%m-%d')";
		return this.getHashMap_Pre(sql, obj);
	}
}