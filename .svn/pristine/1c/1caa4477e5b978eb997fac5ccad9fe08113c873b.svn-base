package com.zqkj.bsm.log.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zqkj.bsm.dal.bean.Admin;
import com.zqkj.bsm.dal.dboper.DbOperator;



public class LogoptDao extends DbOperator 
{
	Logger log = Logger.getLogger(LogoptDao.class);
	private static LogoptDao instance = null;
	public static LogoptDao getInstance()
	{
		if(instance==null)
		{
			instance = new LogoptDao();
		}
		return instance;
	}
	
	
	/**
	 * 记录登录日志
	 * 
	 * @param userid
	 * @param username
	 * @param ipaddr
	 * @param userbrowser
	 * @param useros
	 * @param sessionid
	 */
	public boolean login(String userid, String username, String ipaddr,
			String userbrowser, String useros, String sessionid,String org_id,String DEPT_NAME) 
	{
		String sql = " insert into log_login "
				+ " (userid,username,logintime,ipaddr,userbrowser,useros,sessionid,org_id,DEPT_NAME) values"
				+ " ('" + userid + "','" + username
				+ "',now(),'" + ipaddr + "','" + userbrowser + "','" + useros
				+ "','" + sessionid + "','" + org_id+"','" + DEPT_NAME+"')";
		return this.executeInsert(sql, null);
	}
	
	/**
	 * 登录时每次更新登录时间
	 * @param admin
	 * @param last_failure
	 * @return
	 */
	public boolean updateLast_Failure(Admin admin,long last_failure){
		String sql = "update admin set last_failure = ? where admin_id = ?";
		return this.executeUpdate(sql, new Object[]{last_failure,admin.getAdminId()});
	}
	
	/**
	 * 记录mina终端登录日志
	 * wangwj
	 * @param userid
	 * @param username
	 * @param cpu:      主机cpu号
	 * @param diskNo:   硬盘号
	 * @param mac:    网卡mac
	 * @param screenH:  屏幕高
	 * @param screenW:  屏幕宽
	 */
	public boolean loginMinaTerminal(String userid, String username, String cpu,
			String diskNo, String mac, String screenH,String screenW) 
	{
		StringBuffer sb = new StringBuffer();
		sb.append("insert into log_login (userid,username,logintime,cpu,diskno,mac,screenh,screenw,userbrowser,useros,ipaddr) values ('");
		sb.append(userid+"','");
		sb.append(username+"',now(),'");
		sb.append(cpu+"','");
		sb.append(diskNo+"','");
		sb.append(mac+"','");
		sb.append(screenH+"','");
		sb.append(screenW+"',");
		sb.append("'客户端','mina客户端','").append(mac).append("' )");
//		String sql = " insert into log_login "
//				+ " (userid,username,logintime,cpu,diskno,mac,screenh,screenw) values"
//				+ " ('" + userid + "','" + username
//				+ "',now(),'" + cpu + "','" + userbrowser + "','" + useros
//				+ "','" + sessionid + "','" + org_id+"','" + DEPT_NAME+"')";
		return this.executeInsert(sb.toString(), null);
	}

	/**
	 * 记录登出日志
	 * 
	 * @param userid
	 */
	public boolean logout(String sessionid) 
	{

			String sql = " update  log_login set logouttime = now()" +
				"  where sessionid = '"+ sessionid+"'";
			return this.executeUpdate(sql, null);
	}

	/**
	 * 获得最后一次登录信息（倒数第二条登录信息）
	 * 
	 * @param userid
	 * @return
	 */
	public Map<String,Object> getLastLoginInfo(String userid) 
	{
		String sql = "select * from (select * from  log_login where userid = '"+userid+"' order by id desc) " +
				" where limit 0,1 order by id";
		return this.getHashMap_Pre(sql, null);
	}
	
	/**
	 * 获得本次登录信息（倒数第二条登录信息）
	 * 
	 * @param userid
	 * @return
	 */
	public Map<String,Object> getThisLoginInfo(String userid) 
	{
		String sql = "select * from (select * from  log_login where userid = '"+userid+"' order by id desc) " +
				" where limit 0 order by id";
		return this.getHashMap_Pre(sql, null);
	}

	/**
	 * 查询运维的登陆日志
	 * @param beginnum
	 * @param num
	 * @param ipadd
	 * @param logintime
	 * @param username
	 * @return
	 */
	public List<Map<String, Object>> getLogforPortal(Long beginnum,Long num,String ipadd,String logintime,String username,String email,String phoneNO,String useros)
	{
		String param = "";
		Object[] object = new Object[6];
		if(ipadd!= null && !ipadd.trim().equals(""))
		{
			param += " and ipaddr = ? ";
			object[0]= ipadd.trim();
		}
		else
		{
			param += " and 1 = ? ";
			object[0]= null;
		}
		if(logintime!= null && !logintime.trim().equals(""))
		{
			param += " and date_format(logintime,'%Y-%m-%d') = ? ";
			object[1]= logintime.trim();
		}
		else
		{
			param += " and 2 = ? ";
			object[1]= null;
		}
		if(username!= null && !username.trim().equals(""))
		{
			param += " and username like concat('%',? ,'%')";
			object[2]= username.trim();
		}
		else
		{
			param += " and 3 = ? ";
			object[2]= null;
		}
		if(phoneNO!= null && !phoneNO.trim().equals(""))
		{
			param += " and phoneNO = ? ";
			object[3]= phoneNO.trim();
		}
		else
		{
			param += " and 4 = ? ";
			object[3]= null;
		}
		if(email!= null && !email.trim().equals(""))
		{
			param += " and email = ? ";
			object[4]= email.trim();
		}
		else
		{
			param += " and 5 = ? ";
			object[4]= null;
		}
		if(useros!= null && !useros.trim().equals(""))
		{
			param += " and useros = ? ";
			object[5]= useros.trim();
		}
		else
		{
			param += " and 6 = ? ";
			object[5]= null;
		}
		param += " order by id desc ";
		String sql = "select * from log_login where 1=1 "+param;
		       sql= this.getSqlForPageSelect(beginnum, num, sql);
		//log.info("getLogforPortal="+sql);
		return this.getList_Pre(sql, object);
	}
	
	
	
	public boolean updatelogin(String userid, String sessionid) 
	{
		log.debug("开始:LogoptDao.updatelogin");
		boolean flag = false;
		String sql = "update  log_login set logouttime=sysdate where userid='"
				+ userid + "' and sessionid='" + sessionid + "'";
		log.debug("sql:" + sql);
		flag = this.executeUpdate(sql, null);
		log.debug("结束:LogoptDao.updatelogin");
		return flag;
	}
	
	
	
	
	public Map<String,Object> getActionDic() 
	{
		Map<String, Object> map_return = new HashMap<String,Object>();
		String sql = "select * from action_dic";
		List<Map<String, Object>> list =  this.getList_Pre(sql, null);
		for(Map<String, Object> map:list)
		{
			String action_name = String.valueOf(map.get("ACTION_NAME"));
			String action_url = String.valueOf(map.get("ACTION_URL"));
			map_return.put(action_url, action_name);
		}
		return map_return;
	}
	
	
	
	public boolean insertlog_action(String userid,String action_name,String action_url) 
	{
		Object[] object = new Object[3];
		object[0] =action_name;
		object[1] =action_url;
		object[2] =userid;
		String sql = " insert into log_action"
				+ " (action_name,action_url,user_id,cz_time) values"
				+ " (?,?,?,now())";
		return this.executeInsert(sql, object);
	}

/**
 * 操作日志
 * @param start_num 
 * @param num
 * @param cz_time 操作时间
 * @param cz_user 操作人
 * @return
 */
	public List<Map<String, Object>> getLogAction(Long start_num, Long num, String cz_time, String cz_user) {
		String param = "";
		Object[] object = new Object[2];
		if(cz_time!= null && !cz_time.trim().equals(""))
		{
			param += " and date_format(la.cz_time,'%Y-%m-%d') = ? ";
			object[0]= cz_time.trim();
		}
		else
		{
			param += " and 1 = ? ";
			object[0]= null;
		}
		if(cz_user!= null && !cz_user.trim().equals(""))
		{
			param += " and ad.username = ? ";
			object[1]= cz_user.trim();
		}
		else
		{
			param += " and 2 = ? ";
			object[1]= null;
		}
		param += " order by la.id desc ";
		String sql = "select la.*,ad.username as cz_user from log_action la LEFT JOIN admin ad ON la.user_id = ad.admin_id WHERE 1=1"+param;
		sql= this.getSqlForPageSelect(start_num, num, sql);
//		log.info("getLogAction="+sql);
		return this.getList_Pre(sql, object);
	}
	
}
