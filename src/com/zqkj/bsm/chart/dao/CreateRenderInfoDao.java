package com.zqkj.bsm.chart.dao;

import java.util.List;
import java.util.Map;

import com.zqkj.bsm.dal.dboper.DbOperator;

public class CreateRenderInfoDao extends DbOperator
{
	
	private static volatile CreateRenderInfoDao instance = null;
	
	private CreateRenderInfoDao()
	{
		
	}
	public static CreateRenderInfoDao getInstance()
	{
		if(instance==null)
		{
			synchronized (CreateRenderInfoDao.class)
			{
			instance = new CreateRenderInfoDao();
			}
		}
		return instance;
	}
	
	public List<Map<String, Object>> queryRenderInfo(int pageNum, int pageSize) {
		String  sql = "SELECT * from statistical_day order by  statistical_day.statisticalTime";
		sql += " limit " + (pageNum - 1) * pageSize + ", " + pageSize;
		List<Map<String, Object>> list = getList_Pre(sql.toString(),null);
		return list;
	}
	
	public int queryRenderInfoCount() {
		String  sql = "SELECT * from statistical_day ";
		int count = getCount(sql, null);
		return count;
	}
	public boolean createRenderInfo(String date) {
		String sql = "INSERT INTO statistical_day (createtime,statisticalTime,natureMoney_self,max_natureMoney_self,maya_natureMoney_self,"
				+ "natureMoney_sc_self,max_natureMoney_sc_self,maya_natureMoney_sc_self,natureMoney_zs_self,max_natureMoney_zs_self,"
				+ "maya_natureMoney_zs_self,missionNumber_self,mayaNumber_self,maxNumber_self,missionNumber,mayaNumber,"
				+ "maxNumber,totalFrames_self,maxTotalFrames_self,mayaTotalFrames_self,totalFrames,maxTotalFrames,mayaTotalFrames,"
				+ "successFrames_self,max_successFrames_self,maya_successFrames_self,successFrames,max_successFrames,maya_successFrames,"
				+ "failureFrames_self,max_failureFrames_self,maya_failureFrames_self,failureFrames,max_failureFrames,maya_failureFrames,"
				+ "render_seconds_self,max_render_seconds_self,maya_render_seconds_self,render_seconds,max_render_seconds,maya_render_seconds) "
				+ "SELECT now(),B.statisticalTime,sum(natureMoney_self),sum(max_natureMoney_self),sum(maya_natureMoney_self),"
				+ "sum(natureMoney_sc_self),sum(max_natureMoney_sc_self),sum(maya_natureMoney_sc_self),sum(natureMoney_zs_self),"
				+ "sum(max_natureMoney_zs_self),sum(maya_natureMoney_zs_self),sum(missionNumber_self),sum(mayaNumber_self),"
				+ "sum(maxNumber_self),sum(missionNumber),sum(mayaNumber),sum(maxNumber),sum(totalFrames_self),sum(maxTotalFrames_self),"
				+ "sum(mayaTotalFrames_self),sum(totalFrames),sum(maxTotalFrames),sum(mayaTotalFrames),sum(successFrames_self),"
				+ "sum(max_successFrames_self),sum(maya_successFrames_self),sum(successFrames),sum(max_successFrames),sum(maya_successFrames),"
				+ "sum(failureFrames_self),sum(max_failureFrames_self),sum(maya_failureFrames_self),sum(failureFrames),sum(max_failureFrames),"
				+ "sum(maya_failureFrames),sum(render_seconds_self),sum(max_render_seconds_self),sum(maya_render_seconds_self),"
				+ "sum(render_seconds),sum(max_render_seconds),sum(maya_render_seconds)FROM("
				+ "SELECT A.statisticalTime,	IF (A.fee_type = 0,	A.nature_money,0.00	) AS natureMoney_self,"
				+ "IF (A.fee_type = 0,A.max_natureMoney,0.00) AS max_natureMoney_self,IF (A.fee_type = 0,A.maya_natureMoney,0.00) AS maya_natureMoney_self,"
				+ "IF (A.fee_type = 0,A.nature_sc,0.00) AS natureMoney_sc_self,IF (A.fee_type = 0,A.max_nature_sc,0.00) AS max_natureMoney_sc_self,"
				+ "IF (A.fee_type = 0,A.maya_nature_sc,0.00) AS maya_natureMoney_sc_self,IF (A.fee_type = 0,A.nature_zs,0.00) AS natureMoney_zs_self,"
				+ "IF (A.fee_type = 0,A.max_nature_zs,0.00) AS max_natureMoney_zs_self,IF (A.fee_type = 0,A.maya_nature_zs,0.00) AS maya_natureMoney_zs_self,"
				+ "IF (A.fee_type = 0, A.mission, 0) AS missionNumber_self,IF (A.fee_type = 0,A.mission_maya,0) AS mayaNumber_self,"
				+ "IF (A.fee_type = 0,A.mission_max,0) AS maxNumber_self,IF (A.fee_type = 1, A.mission, 0) AS missionNumber,"
				+ "IF (A.fee_type = 1,A.mission_maya,0) AS mayaNumber,IF (A.fee_type = 1,A.mission_max,0) AS maxNumber,"
				+ "IF (A.fee_type = 0,A.total_frame,0) AS totalFrames_self,IF (A.fee_type = 0,A.maxTotal_frame,0) AS maxTotalFrames_self,"
				+ "IF (A.fee_type = 0,A.mayaTotal_frame,0) AS mayaTotalFrames_self,IF (A.fee_type = 1,A.total_frame,0) AS totalFrames,"
				+ "IF (A.fee_type = 1,A.maxTotal_frame,0) AS maxTotalFrames,IF (A.fee_type = 1,A.mayaTotal_frame,0) AS mayaTotalFrames,"
				+ "IF (A.fee_type = 0,A.success_frame,0) AS successFrames_self,IF (A.fee_type = 0,A.max_success_frame,0) AS max_successFrames_self,"
				+ "IF (A.fee_type = 0,A.maya_success_frame,0) AS maya_successFrames_self,IF (A.fee_type = 1,A.success_frame,0) AS successFrames,"
				+ "IF (A.fee_type = 1,A.max_success_frame,0) AS max_successFrames,IF (A.fee_type = 1,A.maya_success_frame,0) AS maya_successFrames,"
				+ "IF (A.fee_type = 0,A.failure_frame,0) AS failureFrames_self,IF (A.fee_type = 0,A.max_failure_frame,0) AS max_failureFrames_self,"
				+ "IF (A.fee_type = 0,A.maya_failure_frame,0) AS maya_failureFrames_self,IF (A.fee_type = 1,A.failure_frame,0) AS failureFrames,"
				+ "IF (A.fee_type = 1,A.max_failure_frame,0) AS max_failureFrames,IF (A.fee_type = 1,A.maya_failure_frame,0) AS maya_failureFrames,"
				+ "IF (A.fee_type = 0,round(A.seconds / 3600, 4),0.0000) AS render_seconds_self,IF (A.fee_type = 0,round(A.max_seconds / 3600, 4),0.0000) AS max_render_seconds_self,"
				+ "IF (A.fee_type = 0,round(A.maya_seconds / 3600, 4),0.0000) AS maya_render_seconds_self,IF (A.fee_type = 1,round(A.seconds / 3600, 4),0.0000) AS render_seconds,"
				+ "IF (A.fee_type = 1,round(A.max_seconds / 3600, 4),0.0000) AS max_render_seconds,IF (A.fee_type = 1,round(A.maya_seconds / 3600, 4),0.0000) AS maya_render_seconds FROM("
				+ "SELECT date_format(rm.finish_time, '%Y-%m-%d') AS statisticalTime,SUM(rcd.nature_money) AS nature_money,"
				+ "IF (rm.software LIKE '%Max%',SUM(rcd.nature_money),0.00) AS max_natureMoney,IF (rm.software LIKE '%Maya%',SUM(rcd.nature_money),0.00) AS maya_natureMoney,"
				+ "SUM(rcd.nature_sc) AS nature_sc,IF (rm.software LIKE '%Max%',SUM(rcd.nature_sc),0.00) AS max_nature_sc,"
				+ "IF (rm.software LIKE '%Maya%',SUM(rcd.nature_sc),0.00) AS maya_nature_sc,SUM(rcd.nature_zs) AS nature_zs,"
				+ "IF (rm.software LIKE '%Max%',SUM(rcd.nature_zs),0.00) AS max_nature_zs,IF (rm.software LIKE '%Maya%',SUM(rcd.nature_zs),0.00) AS maya_nature_zs,"
				+ " count(rm.render_id) AS mission,sum(rm.software LIKE '%Maya%') AS mission_maya,sum(rm.software LIKE '%Max%') AS mission_max,sum(rm.total_frame) AS total_frame,"
				+ "IF (rm.software LIKE '%Max%',SUM(rm.total_frame),0) AS maxTotal_frame,IF (	rm.software LIKE '%Maya%',	SUM(rm.total_frame),0) AS mayaTotal_frame,"
				+ " sum(rm.success_frame) AS success_frame,IF (rm.software LIKE '%Max%',	SUM(rm.success_frame),0) AS max_success_frame,"
				+ "IF (rm.software LIKE '%Maya%',SUM(rm.success_frame),0) AS maya_success_frame, sum(rm.failure_frame) AS failure_frame,"
				+ "IF (rm.software LIKE '%Max%',SUM(rm.failure_frame),0) AS max_failure_frame,IF (rm.software LIKE '%Maya%',	SUM(rm.failure_frame),	0) AS maya_failure_frame,"
				+ " sum(rm.seconds) AS seconds,IF (rm.software LIKE '%Max%',	SUM(rm.seconds),	0) AS max_seconds,IF (	rm.software LIKE '%Maya%',	SUM(rm.seconds),	0) AS maya_seconds,"
				+ " rm.fee_type FROM render_mission rm LEFT JOIN render_acc_detail rcd ON rm.trans_id = rcd.trans_id LEFT JOIN web_user wu ON rm.user_id = wu.id WHERE "
				+ "rm.online_stage = 7 and rm.render_type !=3 AND rm.finish_time IS NOT NULL and (wu.user_type is null or wu.user_type='注册用户' ) GROUP BY rm.fee_type,date_format(rm.finish_time, '%Y-%m-%d')) A) B "
				+ " where B.statisticalTime<= '"+date +"' GROUP BY B.statisticalTime";
		return this.executeInsert(sql, null);
	}
}
