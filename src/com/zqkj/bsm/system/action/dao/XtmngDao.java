package com.zqkj.bsm.system.action.dao;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zqkj.bsm.dal.dboper.DbOperator;
import com.zqkj.bsm.system.action.SysPara;
import com.zqkj.bsm.system.action.page.OraPaginatedList;



/**
 * @方法描述：
 * @输入：
 * @输出：
 */
public class XtmngDao extends DbOperator 
{
	Logger log = Logger.getLogger(XtmngDao.class);
	private static XtmngDao instance=null;
	private XtmngDao() {

	}

	public static XtmngDao getInstance()
	{
		if(instance==null)
		{
			instance = new XtmngDao();
		}
		return instance;
	}
	/**
	 * @方法描述：
	 * @输入：
	 * @输出：
	 */
	public List<Map<String, Object>> mng() 
	{
		String sql = "select id,username from sys_user where status='00' order by id";
		return this.getList_Pre(sql);
	}

	/**
	 * @方法描述：
	 * @输入：
	 * @输出：
	 */
	public OraPaginatedList qxqry(Map<String, Object> param)
	{
		int pageNum = (Integer)param.get("pageNum");
		int pageSize = (Integer)param.get("pageSize");
		String sql = "select * from sys_auth ";
		OraPaginatedList list = new OraPaginatedList(sql);
        list.setCurrentPage(pageNum);
        list.setObjectsPerPage(pageSize);
		return list;
	}

	public List<Map<String, Object>> qxqry_all() 
	{
		String sql = "select * from sys_auth";
		return this.getList_Pre(sql);
	}
	
	public boolean checkMng(String authname)
	{
		String sql = "select count(*) NUM from sys_auth where AUTHNAME=?";
		Object[] obj = new Object[1];
		obj[0]= authname.trim();
		 Map<String, Object> map = this.getHashMap_Pre(sql, obj);
		Long num = Long.valueOf(map.get("NUM").toString());
		if(num >0)
		{
			return false;
		}
		else
		{
			return true;
		}
		
	}
	
	/**
	 * @方法描述：添加角色
	 * @输入：
	 * @输出：
	 */
	
	public boolean addRoles(String remark,String authname) throws Exception
	{
		String sql="";
		if(SysPara.db_bz==0)
		{
		    sql = "insert into sys_auth (remark,authname) values (?,?)";
		}
		else
		{
			sql = "insert into sys_auth (remark,authname) values (SEQ_SYS_AUTH.NEXTVAL,?,?)";
		}
		Object[] object = new Object[2];
		object[0] = remark;
		object[1] = authname; 
		return this.executeInsert(sql,object);
	}
	
	/**
	 * @方法描述：更新角色
	 * @输入：
	 * @输出：
	 */
	
	public boolean updateRoles(int id, String remark, String authname) throws Exception{
		String sql = "update sys_auth set remark = ?, authname = ? where id = ?";
		log.debug("sql:" + sql);
		Object[] object = new Object[3];
		object[0] = remark;
		object[1] = authname; 
		object[2] = id; 
		return this.executeUpdate(sql,object);
	}
	
	
	public int getAllCount() {
		String sql = "select * from sys_auth";
		return this.getCountNoCond(sql);
	}
	
}