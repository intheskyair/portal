package com.zqkj.bsm.chart.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zqkj.bsm.dal.dboper.DbOperator;

public class UserInfoDao extends DbOperator
{
	
	private static volatile UserInfoDao instance = null;
	
	private UserInfoDao()
	{
		
	}
	public static UserInfoDao getInstance()
	{
		if(instance==null)
		{
			synchronized (UserInfoDao.class)
			{
			instance = new UserInfoDao();
			}
		}
		return instance;
	}

	/**
	 * 按时间 注册数
	 * @param send_time1
	 * @param send_time2
	 * @return
	 */
	public List<Map<String, Object>> queryRegisterNum_trend(String send_time1,String send_time2,int cycle) {
		String request_time = getTimeRequest(cycle,"wu.createtime");
    	String sql= "select "+request_time+" as time,count(wu.id)as num FROM web_user wu WHERE wu.user_type = '注册用户' AND (wu.type = 1 OR wu.type = 2) "
    			+ "and date_format(wu.createtime, '%Y-%m-%d') >='"+send_time1+"' and date_format(wu.createtime, '%Y-%m-%d') <='"+send_time2+"' GROUP BY "+ request_time;
		log.info("queryRegisterNum_trend="+sql);
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
	 * 按地区 注册数
	 * @param send_time1
	 * @param send_time2
	 * @return
	 */
	public List<Map<String, Object>> queryRegisterNum_place(String send_time1,String send_time2) {
    	String sql= "SELECT if(wu.province is null || wu.province='','其他',wu.province)as province,count(wu.id)as num "
    			+ "FROM web_user wu WHERE wu.user_type = '注册用户' AND (wu.type = 1 OR wu.type = 2)  and wu.createtime >='"+send_time1+"' and wu.createtime <='"
    			+send_time2+"' GROUP BY left(if(wu.province is null || wu.province='','其他',wu.province),2) order by num desc";
		log.info("queryRegisterNum_place="+sql);
		List<Map<String, Object>> list = getList_Pre(sql.toString(),null);
		return list;
	}
	
	/**
	 * 登录信息 
	 * @param send_time1
	 * @param send_time2
	 * @param username
	 * @param pageNum
	 * @param pageSize
	 * @param sortName
	 * @param sortOrder
	 * @return
	 */
	public List<Map<String, Object>> queryLoginInfor(String send_time1,String send_time2, String username, int pageNum, int pageSize,String sortName, String sortOrder) {
		String condition = getSql(send_time1,send_time2);
		String param = "" ;
		Object[] object = new Object[1];
        if(username!= null && !username.trim().equals("")){
			param += " and ll.user_name like concat('%',?,'%')";
        	object[0]=username;
		}else{
			param += " and 1 = ? ";
			object[0]= null;
		}
		String  sql = "SELECT ll.* , max(ll.logintime) as last_logintime,ll.address as prcity FROM login_info ll where 1=1  " +condition
				+param +" GROUP BY ll.user_name";
		if (!"undefined".equals(sortName) && !"".equals(sortName)) {
			sql += " order by " + sortName;
			if ("desc".equals(sortOrder) || "asc".equals(sortOrder)) {
				sql += " " + sortOrder;
			} else {
				sql += " desc";
			}
		} else {
			sql += " order by last_logintime desc";
		}
		sql += " limit " + (pageNum - 1) * pageSize + ", " + pageSize;
		List<Map<String, Object>> list = getList_Pre(sql.toString(),object);
		log.info("queryLoginInfor="+sql);
		return list;
	}
	
	private String getSql(String send_time1,String send_time2) {
		String condition = "" ;
        if (!StringUtils.isEmpty(send_time1)) {
        	condition += " and DATE_FORMAT(ll.logintime,'%Y-%m-%d') >=  '"+ send_time1+"'";
        }
        if (!StringUtils.isEmpty(send_time2)) {
        	condition += " and DATE_FORMAT(ll.logintime,'%Y-%m-%d') <=  '"+ send_time2+"'";
        }
		return condition;
	}
	/**
	 *登录信息的总条数
	 * @param send_time1
	 * @param send_time2
	 * @param username
	 * @return
	 */
	public int queryLoginInforCount(String send_time1, String send_time2,String username) {
		String condition = getSql(send_time1,send_time2);
		String param = "" ;
		Object[] object = new Object[1];
        if(username!= null && !username.trim().equals("")){
			param += " and ll.user_name like concat('%',?,'%')";
        	object[0]=username;
		}else{
			param += " and 1 = ? ";
			object[0]= null;
		}
        String  sql = "SELECT ll.* , max(ll.logintime) as last_logintime,ll.address as prcity FROM login_info ll where 1=1  " +condition
				+param +" GROUP BY ll.user_name";
		log.info("queryLoginInfor_count="+sql);
		int count = getCount(sql, object);
		return count;
	}
	/**
	 * 导出登录信息
	 * @param username
	 * @param send_time1
	 * @param send_time2
	 * @return
	 */
	public List<Map<String, Object>> getLoginInfor(String username,String send_time1, String send_time2) {
		String condition = getSql(send_time1,send_time2);
		String param = "" ;
		Object[] object = new Object[1];
        if(username!= null && !username.trim().equals("")){
			param += " and ll.user_name like concat('%',?,'%')";
        	object[0]=username;
		}else{
			param += " and 1 = ? ";
			object[0]= null;
		}
        String  sql = "SELECT ll.* , max(ll.logintime) as last_logintime,ll.address as prcity FROM login_info ll where 1=1  " +condition
				+param +" GROUP BY ll.user_name";
		List<Map<String, Object>> list = getList_Pre(sql.toString(),object);
		log.info("getLoginInfor_excel="+sql);
		return list;
	}
}