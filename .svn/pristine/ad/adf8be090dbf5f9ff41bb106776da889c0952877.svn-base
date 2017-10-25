package com.zqkj.bsm.chart.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zqkj.bsm.dal.dboper.DbOperator;

public class RechargeInfoDao extends DbOperator
{
	
	private static volatile RechargeInfoDao instance = null;
	
	private RechargeInfoDao()
	{
		
	}
	public static RechargeInfoDao getInstance()
	{
		if(instance==null)
		{
			synchronized (RechargeInfoDao.class)
			{
			instance = new RechargeInfoDao();
			}
		}
		return instance;
	}
	/**
	 * 按时间 充值总金额，实充金额，赠送金额
	 * @param send_time1
	 * @param send_time2
	 * @return
	 */
	public List<Map<String, Object>> queryRechargeInfo_trend(String send_time1,String send_time2,int cycle) {
		String request_time = getTimeRequest(cycle,"wd.createtime");
    	String sql= "select A.createtime as time,sum(A.money)as money,sum(A.zs_money)as zs_money,sum(A.sc_money) as sc_money"
    			+ " FROM ( SELECT "+request_time+" as createtime,wd.userid,"
    			+ "sum(wd.money)as money,sum(if(wd.type=5,wd.money,0.00))as zs_money,sum(if(wd.type!=5,wd.money,0.00))as sc_money"
    			+ " FROM web_deposit wd LEFT JOIN web_user wu ON wu.id = wd.userid "
    			+ "WHERE	wd.respCode = 'TRADE_SUCCESS'  and (wu.user_type is null or wu.user_type='注册用户' ) AND "
    			+ "date_format(wd.createtime,'%Y-%m-%d')<= '"+send_time2+"' AND date_format(wd.createtime,'%Y-%m-%d')>='"+send_time1
    			+"' GROUP BY "+request_time+",wd.userid)A GROUP BY A.createtime ";
		log.info("queryRechargeInfo_trend="+sql);
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
	 * 按时间 充值人数
	 * @param send_time1
	 * @param send_time2
	 * @return
	 */
	public List<Map<String, Object>> queryRechargeMan_trend(String send_time1,String send_time2,int cycle) {
		String request_time = getTimeRequest(cycle,"wd.createtime");
    	String sql= "SELECT A.createtime as time,count(A.userid) as man_num FROM ( SELECT "+request_time+"as createtime,wd.userid "
    			+ "FROM web_deposit wd LEFT JOIN web_user wu ON wu.id = wd.userid WHERE wd.respCode = 'TRADE_SUCCESS' "
    			+ "and (wu.user_type is null or wu.user_type='注册用户') AND wd.money > 0 and wd.type in (1,2,7,8) "
    			+ "and date_format(wd.createtime,'%Y-%m-%d')<= '"+send_time2
    			+"' AND date_format(wd.createtime,'%Y-%m-%d')>='"+send_time1+"' GROUP BY "+request_time+",wd.userid)A"
				+ " GROUP BY A.createtime ";
		log.info("queryRechargeMan_trend="+sql);
		List<Map<String, Object>> list = getList_Pre(sql.toString(),null);
		return list;
	}
	
	/**
	 * 按时间 充值次数
	 * @param send_time1
	 * @param send_time2
	 * @return
	 */
	public List<Map<String, Object>> queryRechargeTime_trend(String send_time1,String send_time2,int cycle) {
		String request_time = getTimeRequest(cycle,"wd.createtime");
    	String sql= "SELECT "+request_time+"as time,count(wd.id)as num_total,if(wd.money<=100,count(wd.id),0)as num_0_100,"
    			+ "if(wd.money<=500&&wd.money>100,count(wd.id),0)as num_100_500,"
    			+ "if(wd.money<=1000&&wd.money>500,count(wd.id),0)as num_500_1000,"
    			+ "if(wd.money>1000,count(wd.id),0)as num_1000 FROM web_deposit wd LEFT JOIN web_user wu ON wu.id = wd.userid "
    			+ "WHERE	wd.respCode = 'TRADE_SUCCESS' AND wd.money>0 and (wu.user_type is null or wu.user_type='注册用户' ) AND"
    			+ " wd.type in (1,2,7,8)  and date_format(wd.createtime,'%Y-%m-%d')<= '"+send_time2+"' AND date_format(wd.createtime,'%Y-%m-%d') >= '"+send_time1
    			+ "'  GROUP BY "+request_time;
		log.info("queryRechargeTime_trend="+sql);
		List<Map<String, Object>> list = getList_Pre(sql.toString(),null);
		return list;
	}
	/**
	 * 按地区 充值总金额，实充金额，赠送金额
	 * @param send_time1
	 * @param send_time2
	 * @param order : money、zs_money、sc_money、sucess_num
	 * @return
	 */
	public List<Map<String, Object>> queryRechargeInfo_place(String send_time1,String send_time2,String order) {
		String order_con = "ORDER BY money DESC";
		if(order!=""||!order.equals("")){
			order_con = "ORDER BY "+order+" DESC";
		}
		String sql= "SELECT A.* FROM (SELECT if(wu.province is NULL||wu.province ='','其他',wu.province) as province,"
				+ "sum(wd.money)as money,sum(if(wd.type=5,wd.money,0.00))as zs_money,sum(if(wd.type!=5,wd.money,0.00))as sc_money "
				+ " FROM web_deposit wd LEFT JOIN web_user wu ON wu.id = wd.userid"
				+ " WHERE wd.respCode = 'TRADE_SUCCESS' and (wu.user_type is null or wu.user_type='注册用户')AND "
				+ "date_format(wd.createtime,'%Y-%m-%d')<= '"+send_time2+"' AND date_format(wd.createtime,'%Y-%m-%d') >='"+send_time1
				+ "' GROUP BY LEFT(wu.province,2))A GROUP BY LEFT(A.province,2) "+ order_con;
		log.info("queryRechargeInfo_trend="+sql);
		List<Map<String, Object>> list = getList_Pre(sql.toString(),null);
		return list;
	}
	/**
	 * 按地区 充值笔数
	 * @param send_time1
	 * @param send_time2
	 * @return
	 */
	public List<Map<String, Object>> queryRechargeTime_place(String send_time1,String send_time2) {
		String sql= "SELECT A.province,sum(A.num)as num_total,sum(A.num1)AS num_0_100,sum(A.num2)as num_100_500,"
				+ "sum(A.num3)as num_500_1000,sum(A.num4)as num_1000 FROM ("
				+ "SELECT if(wu.province is NULL||wu.province ='','其他',wu.province) as province,wd.money,if(wd.money!=0,1,0)as num,"
				+ "if(wd.money<=100,1,0)as num1,if(wd.money<=500&&wd.money>100,1,0)as num2,"
				+ "if(wd.money<=1000&&wd.money>500,1,0)as num3,if(wd.money>1000,1,0)as num4 "
				+ "FROM web_deposit wd LEFT JOIN web_user wu ON wu.id = wd.userid WHERE "
				+ "wd.respCode = 'TRADE_SUCCESS' AND wd.money>0 and (wu.user_type is null or wu.user_type='注册用户') and wd.type in (1,2,7,8) and "
				+ "date_format(wd.createtime,'%Y-%m-%d')<= '"+send_time2+"' AND date_format(wd.createtime,'%Y-%m-%d') >='"+send_time1
				+ "')A GROUP BY left(A.province,2) ORDER BY sum(A.num) DESC";
		log.info("queryRechargeTime_place="+sql);
		List<Map<String, Object>> list = getList_Pre(sql.toString(),null);
		return list;
	}
	
	/**
	 * 按地区 充值人数
	 * @param send_time1
	 * @param send_time2
	 * @return
	 */
	public List<Map<String, Object>> queryRechargeMan_place(String send_time1,String send_time2) {
		String sql= "SELECT A.province,count(A.userid) as man_num FROM ("
				+ "SELECT wd.createtime,wd.userid,if(wu.province is NULL||wu.province ='','其他',wu.province) as province "
				+ "FROM web_deposit wd LEFT JOIN web_user wu ON wu.id = wd.userid WHERE wd.respCode = 'TRADE_SUCCESS'"
				+ " and (wu.user_type is null or wu.user_type='注册用户') and wd.money>0 and wd.type in (1,2,7,8) and date_format(wd.createtime,'%Y-%m-%d')<= '"+send_time2
				+ "' AND date_format(wd.createtime,'%Y-%m-%d')>='"+send_time1+"' GROUP BY LEFT(wu.province,2),wd.userId )A"
				+ " GROUP BY LEFT(A.province,2) ORDER BY count(A.userid) DESC";
		log.info("queryRechargeMan_place="+sql);
		List<Map<String, Object>> list = getList_Pre(sql.toString(),null);
		return list;
	}
	
	/**
	 * 占比图 充值笔数
	 * @param send_time1
	 * @param send_time2
	 * @return
	 */
	public List<Map<String, Object>> queryRechargeTime_pie(String send_time1,String send_time2) {
		String sql= "SELECT count(wd.id) as total_number,sum(if(wd.type = 1,1,0)) as unionpay_number,sum(if(wd.type = 2,1,0)) as alipay_number,"
				+ "sum(if(wd.type = 7 ||wd.type = 8,1,0)) as wechat_number FROM web_deposit wd LEFT JOIN web_user wu ON wu.id = wd.userid "
				+ " WHERE wd.respCode = 'TRADE_SUCCESS' AND wd.money > 0 AND (wu.user_type IS NULL OR wu.user_type = '注册用户')"
				+ " AND wd.type IN (1, 2, 7, 8)  and date_format(wd.createtime,'%Y-%m-%d')<= '"+send_time2
				+ "' AND date_format(wd.createtime,'%Y-%m-%d')>='"+send_time1+"'";
		log.info("queryRechargeTime_pie="+sql);
		List<Map<String, Object>> list = getList_Pre(sql.toString(),null);
		return list;
	}
	/**
	 * 充值挖掘 
	 * @param send_time1
	 * @param send_time2
	 * @param username
	 * @param pageNum
	 * @param pageSize
	 * @param sortName
	 * @param sortOrder
	 * @return
	 */
	public List<Map<String, Object>> queryRecharge(String send_time1,String send_time2, String username, int pageNum, int pageSize,String sortName, String sortOrder) {
		String condition1 = getSql(send_time1,send_time2,"wd.createtime");
		String condition2 = getSql(send_time1,send_time2,"wia.create_time");
		String condition3 = getSql(send_time1,send_time2,"rm.finish_time");
		String cost_plan = "SELECT ru.userId,sum(round((IF(rp.plansType=0,round(rp.plansAmount/30,4),0)+IF(rp.plansType=1,round(rp.plansAmount/90,4),0)"
				+ "+IF(rp.plansType=2,round(rp.plansAmount/356,4),0))*(IF('"+send_time1+"'<=ru.startDate && ru.startDate<='"+send_time2+"'&& ru.endDate>='"
				+send_time2+"',DATEDIFF('"+send_time2+"',ru.startDate)+1,0)+IF('"+send_time1+"'<=ru.startDate && ru.startDate <='"+send_time2+"'&& ru.endDate<='"
				+send_time2+"',DATEDIFF(ru.endDate,ru.startDate)+1,0)+IF(ru.startDate<'"+send_time1+"'&& ru.endDate>'"+send_time2+"',DATEDIFF('"
				+send_time2+"','"+send_time1+"')+1,0)+IF(ru.startDate<'"+send_time1+"'&& '"+send_time1+"'<ru.endDate && ru.endDate<='"+send_time2
				+"',DATEDIFF(ru.endDate,'"+send_time1+"')+1,0)),2))AS cost_plan FROM render_user2plans ru LEFT JOIN render_plans rp ON rp.id = ru.plansId "
						+ "LEFT JOIN web_user wu ON wu.id = ru.userId WHERE ru.qidType=2 GROUP BY  ru.userId";
		String param = "" ;
		Object[] object = new Object[1];
        if(username!= null && !username.trim().equals("")){
			param += " and wu.nickname like concat('%',?,'%')";
        	object[0]=username;
		}else{
			param += " and 1 = ? ";
			object[0]= null;
		}
		String  sql = "select wu.nickname,wu.qq,wu.phone,concat(province,city)as prcity,wu.email, "
				+ "(select sum(wd.money) from  web_deposit wd where wu.id = wd.userid and wd.respCode='TRADE_SUCCESS'"+condition1+") as recharge_money,"
				+ "(select max(wd.createtime) from  web_deposit wd where wu.id = wd.userid and wd.respCode='TRADE_SUCCESS' "+condition1+") as last_subtime,"
				+ "((select if(sum(wia.kd_money)is NULL,0.00,sum(wia.kd_money))from  web_invoice_acc wia where wu.id = wia.user_id "+condition2+") +"
				+ "if((select A.cost_plan from ("+cost_plan+")A where wu.id = A.userid) is null,0.00,(select A.cost_plan from ("+cost_plan+")A where wu.id = A.userid))+"
				+ "(select sum(rcd.nature_money) from render_mission rm left join render_acc_detail rcd on rm.trans_id = rcd.trans_id where rm.fee_user_id=wu.id and fee_type=0 "+condition3+")) as cost_money,"
				+ "(select sum(rm.seconds) from render_mission rm left join render_acc_detail rcd on rm.trans_id = rcd.trans_id where  rm.user_id=wu.id and fee_type=0 "+condition3+") as  time_he "
				+ "from web_user wu where (select sum(wd.money) from  web_deposit wd where wu.id = wd.userid and wd.respCode='TRADE_SUCCESS' "+condition1+") is not NULL" +param;
		if (!"undefined".equals(sortName) && !"".equals(sortName)) {
			sql += " order by " + sortName;
			if ("desc".equals(sortOrder) || "asc".equals(sortOrder)) {
				sql += " " + sortOrder;
			} else {
				sql += " desc";
			}
		} else {
			sql += " order by last_subtime desc";
		}
		sql += " limit " + (pageNum - 1) * pageSize + ", " + pageSize;
		List<Map<String, Object>> list = getList_Pre(sql.toString(),object);
		log.info("queryRecharge="+sql);
		return list;
	}
	
	private String getSql(String send_time1,String send_time2,String time) {
		String condition = "" ;
        if (!StringUtils.isEmpty(send_time1)) {
        	condition += " and date_format("+time+",'%Y-%m-%d') >=  '"+ send_time1+"'";
        }
        if (!StringUtils.isEmpty(send_time2)) {
        	condition += " and date_format("+time+",'%Y-%m-%d') <=  '"+ send_time2+"'";
        }
		return condition;
	}
	/**
	 * 充值挖掘的总条数
	 * @param send_time1
	 * @param send_time2
	 * @param username
	 * @return
	 */
	public int queryRechargeCount(String send_time1, String send_time2,String username) {
		String condition1 = getSql(send_time1,send_time2,"wd.createtime");
		String condition2 = getSql(send_time1,send_time2,"wia.create_time");
		String condition3 = getSql(send_time1,send_time2,"rm.finish_time");
		String cost_plan = "SELECT ru.userId,sum(round((IF(rp.plansType=0,round(rp.plansAmount/30,4),0)+IF(rp.plansType=1,round(rp.plansAmount/90,4),0)"
				+ "+IF(rp.plansType=2,round(rp.plansAmount/356,4),0))*(IF('"+send_time1+"'<=ru.startDate && ru.startDate<='"+send_time2+"'&& ru.endDate>='"
				+send_time2+"',DATEDIFF('"+send_time2+"',ru.startDate)+1,0)+IF('"+send_time1+"'<=ru.startDate && ru.startDate <='"+send_time2+"'&& ru.endDate<='"
				+send_time2+"',DATEDIFF(ru.endDate,ru.startDate)+1,0)+IF(ru.startDate<'"+send_time1+"'&& ru.endDate>'"+send_time2+"',DATEDIFF('"
				+send_time2+"','"+send_time1+"')+1,0)+IF(ru.startDate<'"+send_time1+"'&& '"+send_time1+"'<ru.endDate && ru.endDate<='"+send_time2
				+"',DATEDIFF(ru.endDate,'"+send_time1+"')+1,0)),2))AS cost_plan FROM render_user2plans ru LEFT JOIN render_plans rp ON rp.id = ru.plansId "
						+ "LEFT JOIN web_user wu ON wu.id = ru.userId WHERE ru.qidType=2 GROUP BY  ru.userId";
		String param = "" ;
		Object[] object = new Object[1];
        if(username!= null && !username.trim().equals("")){
			param += " and wu.nickname like concat('%',?,'%')";
        	object[0]=username;
		}else{
			param += " and 1 = ? ";
			object[0]= null;
		}
		String  sql = "select wu.nickname,wu.qq,wu.phone,concat(province,city)as prcity,wu.email, "
				+ "(select sum(wd.money) from  web_deposit wd where wu.id = wd.userid and wd.respCode='TRADE_SUCCESS'"+condition1+") as recharge_money,"
				+ "(select max(wd.createtime) from  web_deposit wd where wu.id = wd.userid and wd.respCode='TRADE_SUCCESS' "+condition1+") as last_subtime,"
				+ "((select if(sum(wia.kd_money)is NULL,0.00,sum(wia.kd_money))from  web_invoice_acc wia where wu.id = wia.user_id "+condition2+") +"
				+ "if((select A.cost_plan from ("+cost_plan+")A where wu.id = A.userid) is null,0.00,(select A.cost_plan from ("+cost_plan+")A where wu.id = A.userid))+"
				+ "(select sum(rcd.nature_money) from render_mission rm left join render_acc_detail rcd on rm.trans_id = rcd.trans_id where rm.fee_user_id=wu.id and fee_type=0 "+condition3+")) as cost_money,"
				+ "(select sum(rm.seconds) from render_mission rm left join render_acc_detail rcd on rm.trans_id = rcd.trans_id where  rm.user_id=wu.id and fee_type=0 "+condition3+") as  time_he "
				+ "from web_user wu where (select sum(wd.money) from  web_deposit wd where wu.id = wd.userid and wd.respCode='TRADE_SUCCESS' "+condition1+") is not NULL" +param;
		log.info("queryRechargeCount="+sql);
		int count = getCount(sql, object);
		return count;
	}
	/**
	 *充值挖掘导出
	 * @param username
	 * @param send_time1
	 * @param send_time2
	 * @return
	 */
	public List<Map<String, Object>> getRecharge(String username,String send_time1, String send_time2) {
		String condition1 = getSql(send_time1,send_time2,"wd.createtime");
		String condition2 = getSql(send_time1,send_time2,"wia.create_time");
		String condition3 = getSql(send_time1,send_time2,"rm.finish_time");
		String cost_plan = "SELECT ru.userId,sum(round((IF(rp.plansType=0,round(rp.plansAmount/30,4),0)+IF(rp.plansType=1,round(rp.plansAmount/90,4),0)"
				+ "+IF(rp.plansType=2,round(rp.plansAmount/356,4),0))*(IF('"+send_time1+"'<=ru.startDate && ru.startDate<='"+send_time2+"'&& ru.endDate>='"
				+send_time2+"',DATEDIFF('"+send_time2+"',ru.startDate)+1,0)+IF('"+send_time1+"'<=ru.startDate && ru.startDate <='"+send_time2+"'&& ru.endDate<='"
				+send_time2+"',DATEDIFF(ru.endDate,ru.startDate)+1,0)+IF(ru.startDate<'"+send_time1+"'&& ru.endDate>'"+send_time2+"',DATEDIFF('"
				+send_time2+"','"+send_time1+"')+1,0)+IF(ru.startDate<'"+send_time1+"'&& '"+send_time1+"'<ru.endDate && ru.endDate<='"+send_time2
				+"',DATEDIFF(ru.endDate,'"+send_time1+"')+1,0)),2))AS cost_plan FROM render_user2plans ru LEFT JOIN render_plans rp ON rp.id = ru.plansId "
						+ "LEFT JOIN web_user wu ON wu.id = ru.userId WHERE ru.qidType=2 GROUP BY  ru.userId";
		String param = "" ;
		Object[] object = new Object[1];
        if(username!= null && !username.trim().equals("")){
			param += " and wu.nickname like concat('%',?,'%')";
        	object[0]=username;
		}else{
			param += " and 1 = ? ";
			object[0]= null;
		}
		String  sql = "select wu.nickname,wu.qq,wu.phone,concat(province,city)as prcity,wu.email, "
				+ "(select sum(wd.money) from  web_deposit wd where wu.id = wd.userid and wd.respCode='TRADE_SUCCESS'"+condition1+") as recharge_money,"
				+ "(select max(wd.createtime) from  web_deposit wd where wu.id = wd.userid and wd.respCode='TRADE_SUCCESS' "+condition1+") as last_subtime,"
				+ "((select if(sum(wia.kd_money)is NULL,0.00,sum(wia.kd_money))from  web_invoice_acc wia where wu.id = wia.user_id "+condition2+") +"
				+ "if((select A.cost_plan from ("+cost_plan+")A where wu.id = A.userid) is null,0.00,(select A.cost_plan from ("+cost_plan+")A where wu.id = A.userid))+"
				+ "(select sum(rcd.nature_money) from render_mission rm left join render_acc_detail rcd on rm.trans_id = rcd.trans_id where rm.fee_user_id=wu.id and fee_type=0 "+condition3+")) as cost_money,"
				+ "(select sum(rm.seconds) from render_mission rm left join render_acc_detail rcd on rm.trans_id = rcd.trans_id where  rm.user_id=wu.id and fee_type=0 "+condition3+") as  time_he "
				+ "from web_user wu where (select sum(wd.money) from  web_deposit wd where wu.id = wd.userid and wd.respCode='TRADE_SUCCESS' "+condition1+") is not NULL" +param;
		List<Map<String, Object>> list = getList_Pre(sql.toString(),object);
		log.info("getRecharge="+sql);
		return list;
	}
}