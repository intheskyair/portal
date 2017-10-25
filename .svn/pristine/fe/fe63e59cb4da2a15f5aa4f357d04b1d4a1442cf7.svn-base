package com.zqkj.bsm.chart.dao;

import java.util.List;
import java.util.Map;

import com.zqkj.bsm.dal.dboper.DbOperator;

public class Fee_deductionInfoDao extends DbOperator
{
	
	private static volatile Fee_deductionInfoDao instance = null;
	
	private Fee_deductionInfoDao()
	{
		
	}
	public static Fee_deductionInfoDao getInstance()
	{
		if(instance==null)
		{
			synchronized (Fee_deductionInfoDao.class)
			{
			instance = new Fee_deductionInfoDao();
			}
		}
		return instance;
	}
	
	/**
	 * 按时间 自助渲染扣费 (总、实扣)
	 * @param send_time1
	 * @param send_time2
	 * @return
	 */
	public List<Map<String, Object>> queryJobsFee_self_trend(String send_time1,String send_time2,int cycle) {
		String request_time = getTimeRequest(cycle,"sd.statisticalTime");
    	String sql= "select "+request_time+" as time,sum(sd.natureMoney_self) as natureMoney ,sum(sd.max_natureMoney_self) as max_natureMoney ,"
    			+ "sum(sd.maya_natureMoney_self) as maya_natureMoney,sum(sd.natureMoney_sc_self) as natureMoney_sc,"
    			+ "sum(sd.max_natureMoney_sc_self) as max_natureMoney_sc,sum(sd.maya_natureMoney_sc_self) as maya_natureMoney_sc "
    			+ "FROM statistical_day sd WHERE statisticalTime >='"+send_time1+"' and statisticalTime <='"+send_time2+"' GROUP BY "+ request_time;
		log.info("queryJobsFee_self_trend="+sql);
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
	 * 按时间 套餐扣费 
	 * @param send_time1
	 * @param send_time2
	 * @return
	 */
	public List<Map<String, Object>> queryJobsFee_trend(String send_time1,String send_time2,int cycle) {
		String request_time = getTimeRequest(cycle,"statisticalTime");
    	String sql= "select "+request_time+" as time,sum(playDeduction_day)as playDeduction FROM playDeduction_day"
    			+ " WHERE statisticalTime >='"+send_time1+"' and statisticalTime <='"+send_time2+"' GROUP BY "+ request_time;
		log.info("queryJobsFee_trend="+sql);
		List<Map<String, Object>> list = getList_Pre(sql.toString(),null);
		return list;
	}
	
	/**
	 * 按时间 套餐扣费+自助 (总、实扣)
	 * @param send_time1
	 * @param send_time2
	 * @return
	 */
	public List<Map<String, Object>> queryJobsFee_total_trend(String send_time1,String send_time2,int cycle) {
		String request_time = getTimeRequest(cycle,"A.statisticalTime");
    	String sql= " SELECT "+ request_time+"  AS time,sum(A.total_money)as cost_total,sum(A.sc_money) as cost_sc FROM "
    			+ "(SELECT statisticalTime,natureMoney_self as total_money,natureMoney_sc_self as sc_money FROM statistical_day sd UNION "
    			+ "SELECT statisticalTime,playDeduction_day as total_money,playDeduction_day as sc_money FROM playDeduction_day) A "
    			+ " WHERE A.statisticalTime >= '"
    			+send_time1+"' AND A.statisticalTime <= '"+send_time2+"'  GROUP BY "+request_time;
		log.info("queryJobsFee_total_trend="+sql);
		List<Map<String, Object>> list = getList_Pre(sql.toString(),null);
		return list;
	}
	
	/**
	 * 按区域 自助（总、实扣）
	 * @param send_time1
	 * @param send_time2
	 * @return
	 */
	public List<Map<String, Object>> queryJobsFee_self_place(String send_time1,String send_time2,String order) {
    	String sql= "SELECT a.province,sum(a.nature_money)as nature_money,sum(a.nature_sc) as nature_sc FROM ("
    			+ "SELECT if(wu.province is NULL or wu.province='','其他',wu.province)as province,rcd.nature_money,rcd.nature_sc FROM render_mission rm "
    			+ "LEFT JOIN render_acc_detail rcd ON rm.trans_id = rcd.trans_id LEFT JOIN web_user wu ON rm.user_id = wu.id "
    			+ "WHERE rm.online_stage = 7 and rm.render_type !=3 AND rm.fee_type=0 AND rm.finish_time IS NOT NULL and (wu.user_type is null or wu.user_type='注册用户' ) and date_format(rm.finish_time, '%Y-%m-%d')<='"
    			+ send_time2+"' and date_format(rm.finish_time, '%Y-%m-%d')>='"+send_time1+"')a GROUP BY left(a.province,2) ";
    	if(order!=""||!order.equals("")){
    		sql += " order by "+order+ " desc";
    	}
		log.info("queryJobsFee_self_place="+sql);
		List<Map<String, Object>> list = getList_Pre(sql.toString(),null);
		return list;
	}

	/**
	 * 按区域 套餐（实扣）
	 * @param send_time1
	 * @param send_time2
	 * @return
	 */
	public List<Map<String, Object>> queryJobsFee_place(String send_time1,String send_time2) {
    	String sql= "select if(province is null or province='' ,'其他',province) as province,sum(playDeduction_day)as playDeduction FROM playDeduction_day "
    			+ " WHERE  statisticalTime >='"+send_time1+"' and statisticalTime <='"+send_time2+"' GROUP BY 	LEFT(province,2) order by playDeduction desc";
		log.info("queryJobsFee_place="+sql);
		List<Map<String, Object>> list = getList_Pre(sql.toString(),null);
		return list;
	}
	
	/**
	 * 按地区 套餐扣费+自助 (总、实扣)
	 * @param send_time1
	 * @param send_time2
	 * @return
	 */
	public List<Map<String, Object>> queryJobsFee_total_place(String send_time1,String send_time2,String order) {
		String sql= "SELECT b.province,sum(nature_money)as nature_money, sum(nature_sc) as nature_sc FROM ("
    			+ "SELECT a.province,sum(a.nature_money)as nature_money,sum(a.nature_sc) as nature_sc FROM ("
    			+ "SELECT if(wu.province is NULL or wu.province='','其他',wu.province)as province,rcd.nature_money,rcd.nature_sc FROM render_mission rm "
    			+ "LEFT JOIN render_acc_detail rcd ON rm.trans_id = rcd.trans_id LEFT JOIN web_user wu ON rm.user_id = wu.id "
    			+ "WHERE rm.online_stage = 7 and rm.render_type !=3 AND rm.fee_type=0 AND rm.finish_time IS NOT NULL and (wu.user_type is null or wu.user_type='注册用户' ) and date_format(rm.finish_time, '%Y-%m-%d')<='"
    			+ send_time2+"' and date_format(rm.finish_time, '%Y-%m-%d')>='"+send_time1+"')a GROUP BY left(a.province,2) UNION "
				+ "select if(province is null or province='','其他',province)as province,sum(playDeduction_day)as nature_sc,sum(playDeduction_day)as nature_sc FROM playDeduction_day "
				+ "WHERE  statisticalTime >='"+send_time1+"' and statisticalTime <='"+send_time2+"' GROUP BY LEFT (province, 2) )b "
				+ " GROUP BY LEFT (b.province, 2)  ";
		if(order!=""||!order.equals("")){
    		sql += " order by "+order+ " desc";
    	}
		log.info("queryJobsFee_self_place="+sql);
		List<Map<String, Object>> list = getList_Pre(sql.toString(),null);
		return list;
	}
	/**
	 * 平均扣费 (总、实扣)
	 * @param send_time1
	 * @param send_time2
	 * @return
	 */
	public List<Map<String, Object>> queryFee_day(String send_time1,String send_time2) {
		String sql= "SELECT B.time,ROUND(B.cost_total/C.man_num ,2) AS cost_day,ROUND(B.cost_sc/C.man_num ,2) AS cost_sc_day "
				+ "from(SELECT date_format(A.statisticalTime,'%Y-%m-%d') AS time,sum(A.total_money) AS cost_total,sum(A.sc_money) AS cost_sc "
				+ "FROM(SELECT statisticalTime,natureMoney_self AS total_money,natureMoney_sc_self AS sc_money FROM statistical_day sd"
				+ " UNION SELECT statisticalTime,playDeduction_day AS total_money,playDeduction_day AS sc_money FROM playDeduction_day) A  "
				+ "WHERE A.statisticalTime >= '"+send_time1+"' AND A.statisticalTime <= '"+send_time2+"'  "
				+ " GROUP BY date_format(A.statisticalTime, '%Y-%m-%d'))B,"
				+ "(SELECT (count(D.id)+E.package_man_number) AS man_num,D.time FROM(SELECT wu.id,date_format(rm.finish_time, '%Y-%m-%d') AS time FROM render_mission rm "
				+ "LEFT JOIN render_acc_detail rcd ON rm.trans_id = rcd.trans_id LEFT JOIN web_user wu ON rm.user_id = wu.id "
				+ "WHERE rm.online_stage = 7 and rm.render_type !=3 AND rm.finish_time IS NOT NULL AND (wu.type = 1 OR wu.type = 2) and (wu.user_type is null or wu.user_type='注册用户' ) GROUP BY date_format(rm.finish_time, '%Y-%m-%d'),wu.id) D,"
				+ "(SELECT pd.package_man_number,pd.time FROM peakcount_day pd)E WHERE D.time=E.time  "
				+ "GROUP BY D.time) C WHERE B.time = C.time AND B.time >='"+send_time1+"' and B.time <='"+send_time2+"'";
		log.info("queryFee_day="+sql);
		List<Map<String, Object>> list = getList_Pre(sql.toString(),null);
		return list;
	}
}
