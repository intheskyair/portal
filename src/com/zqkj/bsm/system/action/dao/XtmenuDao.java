package com.zqkj.bsm.system.action.dao;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zqkj.bsm.dal.dboper.DbOperator;


public class XtmenuDao extends DbOperator
{

	Logger log = Logger.getLogger(XtmenuDao.class);
    private static XtmenuDao instance;
    public static XtmenuDao getInstance()
    {
    	if(instance ==null)
    	{
    		instance = new XtmenuDao();
    	}
    	return instance;
    }
    
    private XtmenuDao()
    {
    	
    }

	/**
	 * 根据用户id获取菜单标题
	 * @param userid
	 * @return 菜单标题list
	 */
	public ArrayList<Map<String, Object>> getMenus(String userid)
	{
		log.debug("当前方法：getTitle");
		String sql = 
			" select t3.* from sys_menu t3 where exists (select menuid from ( " +
			" select t2.menuid from sys_auth2menu t2 where exists " +
			" (select t1.authid from sys_user2auth t1 where t1.userid = '" +userid+
			"' and t1.authid = t2.authid)) t4 where t4.menuid = t3.id ) order by menuseq";
		//log.info("getTitle="+sql);
		return this.getList_Pre(sql);
	}

//	/**
//	 * 根据用户id获取菜单标题
//	 * @param userid
//	 * @return 菜单标题list
//	 */
//	public ArrayList<Map<String, Object>> getTitle(String userid)
//	{
//		log.debug("当前方法：getTitle");
//		String sql = 
//			" select t3.* from sys_menu t3 where exists (select menuid from ( " +
//			" select t2.menuid from sys_auth2menu t2 where exists " +
//			" (select t1.authid from sys_user2auth t1 where t1.userid = '" +userid+
//			"' and t1.authid = t2.authid)) t4 where t4.menuid = t3.id ) and parentid = 0 order by menuseq";
//		log.info("getTitle="+sql);
//		return this.getList_Pre(sql, null);
//	}

	
	public Map<String, Object> getMenuDetail(String id)
	{
		log.debug("当前方法：getTitle");
		String sql = 
			" select t3.* from sys_menu t3 where t3.id="+id+" order by menuseq";
		return this.getHashMap_Pre(sql);
	}
	
//	/**
//	 * 根据用户id获取菜单内容
//	 * @param userid
//	 * @return 菜单标题list
//	 */
//	public ArrayList<Map<String, Object>> getItem(String userid)
//	{
//		log.debug("当前方法：getItem");
//		String sql = 
//			" select t3.* from sys_menu t3 where exists (select menuid from ( " +
//			" select t2.menuid from sys_auth2menu t2 where exists " +
//			" (select t1.authid from sys_user2auth t1 where t1.userid = '" +userid+
//			"' and t1.authid = t2.authid)) t4 where t4.menuid = t3.id ) and parentid <> 0 order by menuseq";
//		log.info("getItem="+sql);
//		return this.getList_Pre(sql, null);
//	}
	
	/**
	 * 根据权限id获得菜单ID列表
	 * @param authid
	 * @return 菜单list
	 */
	public ArrayList<Map<String, Object>> getMenuByAuth(String authid)
	{
		log.debug("当前方法：getMenuByAuth");
		String sql = "select t1.id from sys_menu t1, sys_auth2menu t2 where t1.id = t2.menuid and t2.authid = "+authid;
		return this.getList_Pre(sql);
	}
	
	public String getAuthName(String authid)
	{
		log.debug("当前方法：getAuthName");
		String sql = "select * from sys_auth where id="+authid;
		Map<String, Object> map = this.getHashMap_Pre(sql);
		return map.get("AUTHNAME")==null?"":map.get("AUTHNAME").toString();
	}
	
	/**
	 * 获取�?有菜�?
	 * @return
	 */
	public ArrayList<Map<String, Object>> getAllMenu()
	{
		log.debug("当前方法：getAllMenu");
		String sql = "select * from sys_menu order by parentid, menuseq";
		return this.getList_Pre(sql);
	}
	
	
	/**
	 * 更新权限-菜单关联关系
	 * @param menuid
	 * @param authid
	 * @return
	 */
	public boolean editauth2menu(String[] menuid, String[] buttonId,String authid)
	{
		ArrayList<String> list_sql = new ArrayList<String>();
		String sql_menu = "delete from sys_auth2menu where authid = "+authid;
		list_sql.add(sql_menu);
		if (menuid != null && menuid.length != 0) {
			for(int i=0;i<menuid.length;i++)
			{
				String sql2_menu = "insert into sys_auth2menu(authid,menuid) " +
						" values ("+authid+","+menuid[i]+")";
				list_sql.add(sql2_menu);
			}
		}
		//操作按钮的权限设置
		String sql_button = "delete from sys_auth2button where auth_id = "+authid;
		list_sql.add(sql_button);
		if (buttonId != null && buttonId.length != 0) {
			for(int i=0;i<buttonId.length;i++)
			{
				String sql2 = "select sm2.menu_id,sm2.button_name from sys_menu2button sm2 where sm2.id = " + buttonId[i];
				Map<String, Object> map = this.getHashMap_Pre(sql2);
				BigDecimal menu_id = (BigDecimal) map.get("MENU_ID") ;
				String button_name = (String) map.get("BUTTON_NAME") ;
				String sql2_button = "insert into sys_auth2button(auth_id,menu_id,button_name) " +
						" values ("+authid+","+menu_id+",'"+button_name+"')";
				list_sql.add(sql2_button);
			}
		}
		return this.executeBatch(list_sql);
	}
	
	/**
	 * 根据权限id和菜单id获得操作按钮
	 * @return buttonlist
	 */
	public ArrayList<Map<String, Object>> getButtons(String menu_id,String auth_id)
	{
		String sql = "SELECT DISTINCT  button_name FROM sys_auth2button WHERE menu_id= '"+menu_id+"' AND auth_id in("+auth_id+")";
		return this.getList_Pre(sql);
	}
	
	
	
	public String getMenu(String id) 
	{
		String sql = "select menuurl from sys_menu where id = " + id;
		Map<String, Object> map = this.getHashMap_Pre(sql);
		return map==null?"":(String)map.get("MENUURL");
	}

	//获得所有菜单的操作按钮
	public List<Map<String, Object>> getAllButton() {
		String sql = "select * from sys_menu2button order by id";
		return this.getList_Pre(sql);
	}

	//获得已有的操作按钮id
	public List<Map<String, Object>> getButtonByAuth(String authid) {
		String sql = "SELECT sm2.id FROM  sys_auth2button sa2  LEFT JOIN  sys_menu2button sm2 "
				+ "ON sm2.button_name=sa2. button_name AND sm2.menu_id=sa2.menu_id  WHERE sa2.auth_id= "+authid+" GROUP BY  sm2.id";
		return this.getList_Pre(sql);
	}
	
	//	通过用户的id来获取角色id
	public String getAuthId(String roleId) {
		String authid = "";
		String sql="select a.username,(select  if(group_concat(t2.id)='','无',group_concat(t2.id))  from  sys_user2auth t1,sys_auth t2 "
				+ " where t1.userid=a.admin_id and t1.authid=t2.id) as auth  from admin a   where a.admin_id = "+ roleId;
		Map<String, Object> result_map = this.getHashMap_Pre(sql);
		if(result_map!=null)
    	{
			authid =  String.valueOf(result_map.get("AUTH")==null?"":result_map.get("AUTH"));
    	}
		return authid;
	}
	
}