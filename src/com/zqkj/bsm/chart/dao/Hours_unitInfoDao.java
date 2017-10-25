package com.zqkj.bsm.chart.dao;

import java.util.List;
import java.util.Map;

import com.zqkj.bsm.dal.dboper.DbOperator;

public class Hours_unitInfoDao extends DbOperator
{
	
	private static volatile Hours_unitInfoDao instance = null;
	
	private Hours_unitInfoDao()
	{
		
	}
	public static Hours_unitInfoDao getInstance()
	{
		if(instance==null)
		{
			synchronized (Hours_unitInfoDao.class)
			{
			instance = new Hours_unitInfoDao();
			}
		}
		return instance;
	}

	/**
	 * 套餐核小时单价  套餐扣费/实际渲染核小时
	 * @param send_time1
	 * @param send_time2
	 * @param cycle
	 * @return
	 */
	public List<Map<String, Object>> queryJobsFeeHour_trend(String send_time1,String send_time2,int cycle) {
		String request_time1 = getTimeRequest(cycle,"sd.statisticalTime");
		String request_time2 = getTimeRequest(cycle,"pd.statisticalTime");
    	String sql= "SELECT A.time,round(A.playDeduction_day/B.seconds,2) as cost_hour FROM ("
    			+ "SELECT "+request_time2+"as time,sum(pd.playDeduction_day) as playDeduction_day "
    			+ "from playDeduction_day pd  where pd.statisticalTime >='"+send_time1+"' and pd.statisticalTime <='"+send_time2+"' GROUP BY time)A LEFT JOIN ("
    			+ "SELECT "+request_time1+" as time,"
    			+ "sum(sd.render_seconds) AS seconds from statistical_day sd where sd.statisticalTime >='"+send_time1+"' and sd.statisticalTime <='"+send_time2
    			+"'  GROUP BY time)B ON A.time=B.time WHERE B.seconds is NOT null and B.seconds!=0";
		log.info("queryJobsFeeHour_trend="+sql);
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
	 * 自助核小时单价  max、maya、全部（总、实扣）
	 * @param send_time1
	 * @param send_time2
	 * @param cycle
	 * @return
	 */
	public List<Map<String, Object>> queryJobsFeeHour_self_trend(String send_time1,String send_time2,int cycle) {
		String request_time = getTimeRequest(cycle,"sd.statisticalTime");
    	String sql= "select "+request_time+" as time,sum(round(sd.natureMoney_self/sd.render_seconds_self,2))as cost_hour,"
    			+ "sum(if(sd.max_natureMoney_self=0,0,round(sd.max_natureMoney_self/sd.max_render_seconds_self,2)))as max_cost_hour,"
    			+ "sum(if(sd.maya_natureMoney_self=0,0,round(sd.maya_natureMoney_self/sd.maya_render_seconds_self,2)))as maya_cost_hour,"
    			+ "sum(if(sd.natureMoney_sc_self=0,0,round(sd.natureMoney_sc_self/sd.render_seconds_self,2)))as cost_sc_hour,"
    			+ "sum(if(sd.max_natureMoney_sc_self=0,0,round(sd.max_natureMoney_sc_self/sd.max_render_seconds_self,2)))as max_cost_sc_hour,"
    			+ "sum(if(sd.maya_natureMoney_sc_self=0,0,round(sd.maya_natureMoney_sc_self/sd.maya_render_seconds_self,2)))as maya_cost_sc_hour"
    			+ " FROM statistical_day sd WHERE  sd.natureMoney_self!=0 and sd.statisticalTime >='"+send_time1+"' and sd.statisticalTime <='"+send_time2
    			+"' GROUP BY time";
		log.info("queryJobsFeeHour_self_trend="+sql);
		List<Map<String, Object>> list = getList_Pre(sql.toString(),null);
		return list;
	}
	
	/**
	 * 实际总核小时单价(总，实扣)
	 * @param send_time1
	 * @param send_time2
	 * @param cycle
	 * @return
	 */
	public List<Map<String, Object>> queryJobsFeeHour_total_trend(String send_time1,String send_time2,int cycle) {
		String request_time1 = getTimeRequest(cycle,"A.statisticalTime");
		String request_time2 = getTimeRequest(cycle,"sd.statisticalTime");
    	String sql= "SELECT B.time,round(B.cost_sc / C.seconds, 2) AS cost_sc_hour FROM(SELECT "+request_time1+" AS time,"
    			+ "sum(A.sc_money) AS cost_sc FROM(SELECT statisticalTime,natureMoney_sc_self AS sc_money FROM statistical_day"
    			+ " UNION SELECT statisticalTime, playDeduction_day AS sc_money FROM playDeduction_day ) A WHERE A.statisticalTime  >='"+send_time1+"' "
				+ " AND A.statisticalTime  <='"+send_time2+"' GROUP BY "+request_time1 +") B LEFT JOIN ("
				+ "SELECT "+request_time2+" AS time,sum(sd.render_seconds + sd.render_seconds_self) AS seconds FROM statistical_day sd WHERE "
				+ "sd.statisticalTime>='"+send_time1+"' AND sd.statisticalTime <='"+send_time2+"' GROUP BY "+request_time2 +") C ON B.time = C.time"
				+ " WHERE  C.seconds IS NOT NULL AND C.seconds != 0";
		log.info("queryJobsFeeHour_total_trend="+sql);
		List<Map<String, Object>> list = getList_Pre(sql.toString(),null);
		return list;
	}

}