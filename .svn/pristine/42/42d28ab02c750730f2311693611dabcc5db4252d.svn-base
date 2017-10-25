package com.zqkj.bsm.system.action.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

import com.zqkj.bsm.dal.dboper.DbOperator;


/**
 * @方法描述�?
 * @输入�?
 * @输出�?
 */
public class XtdoroleDao extends DbOperator 
{
	Logger log = Logger.getLogger(XtdoroleDao.class);

	private static XtdoroleDao instance = null;
	
	public static XtdoroleDao getInstance()
	{
		if(instance==null)
		{
			instance = new XtdoroleDao();
		}
		return instance;
	}

	private XtdoroleDao() {

	}


	public List<Map<String, Object>> dorole(String id) 
	{
		String sql = "select * from sys_auth where id in (select authid from sys_user2auth where userid='"
				+ id.toUpperCase() + "')";
		return this.getList_Pre(sql);
	}


	public List<Map<String, Object>> doNotinrole(String id) 
	{
		String sql = "select * from sys_auth where id not in (select authid from sys_user2auth where userid='"
				+ id.toUpperCase() + "')";
		return this.getList_Pre(sql);
	}


	public List<Map<String, Object>> allrole() 
	{
		String sql = "select * from sys_auth order by id";
		return this.getList_Pre(sql);
	}
	
	public boolean modifyRoles(String userid, String roles){
		String[] roles1 = roles.split("~");
		List<Map<String, Object>> al = this.dorole(userid);
		boolean status;
		int len = al.size();
		if("".equals(roles))
		{
			if(len!=0)
			{
				String sql = "delete from sys_user2auth where userid = '"
					+ userid+"'";
				status = this.executeUpdate(sql);
			}
			else
			{
				status = true;
			}
		}
		else
		{
			if (len == 0) 
			{
				// only insert
				ArrayList<String> sql_list = new ArrayList<String>();
				for (int i = 0; i < roles1.length; i++) 
				{
					String sql = "insert into sys_user2auth (id,userid,authid) values "
							+ "(SEQ_SYS_USER2AUTH.NextVal,upper('"
							+ userid
							+ "'),"
							+ Long.valueOf(roles1[i]) + ")";
					sql_list.add(sql);
					log.debug("sql:" + sql);
				}
				status = this.executeBatch(sql_list);
			} 
			else 
			{
				ArrayList<String> sql_list = new ArrayList<String>();
				String sql_ = "delete from sys_user2auth where userid = '"
						+ userid+"'";
				sql_list.add(sql_);
				for (int i = 1; i < roles1.length + 1; i++) 
				{
					String sql_temp = "insert into sys_user2auth (id,userid,authid) values "
							+ "(SEQ_SYS_USER2AUTH.NextVal,upper('"
							+ userid
							+ "'),"
							+ Long.valueOf(roles1[i - 1]) + ")";
					sql_list.add(sql_temp);
					log.debug("sql:" + sql_temp);
				}
				status = this.executeBatch(sql_list);
			}
		}
		return status;
	}
}