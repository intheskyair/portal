package com.zqkj.bsm.system.action.dao;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.zqkj.bsm.dal.bean.Admin;
import com.zqkj.bsm.dal.dboper.DbOperator;
import com.zqkj.bsm.system.action.SysPara;
import com.zqkj.bsm.system.action.page.OraPaginatedList;
import com.zqkj.bsm.system.action.util.StringHelper;
import com.zqkj.bsm.util.CookieUtil;

public class SysUserDao extends DbOperator
{

	Logger log = Logger.getLogger(SysUserDao.class);
	private static SysUserDao instance = null;
	private SysUserDao(){
		
	}
	public static SysUserDao getInstance()
	{
		if(instance==null)
		{
			instance = new SysUserDao();
		}
		
		
		
		
		return instance;
	}
	
	
	/**
	 * 根据用户列表
	 * @param username
	 * @param userpwd
	 * @return SysUserBean
	 */
	public OraPaginatedList query(String remark,String username,String org_infor,String mobile,String sys_id,int pageNum,int pageSize,
			String sortname, String sortorder,String department_menu_id) 
	{
		String condition="";
		String conditionP = "";
		String param = "";
		if(remark!= null && !remark.trim().equals(""))
		{
			param += " and ada.remark like concat('%',?,'%') ";
			conditionP += remark.trim() + ",";
		}
		if(username!= null && !username.trim().equals(""))
		{
			param += " and ada.truename like concat('%',?,'%') ";
			conditionP += username.trim() + ",";
		}
		if(org_infor!= null && !org_infor.trim().equals(""))
		{
			param += " and ada.org_infor like concat('%',?,'%') ";
			conditionP += org_infor.trim() + ",";
		}
		if(sys_id!= null && !sys_id.trim().equals(""))
		{
			param += " and ada.admin_id = ? ";
			conditionP += sys_id.trim() + ",";
		}
		if(department_menu_id!= null && !department_menu_id.trim().equals(""))
		{
			param += " and ada.org_id = ? or ada.org_id_lend = ?";
			conditionP += department_menu_id.trim() + ","+department_menu_id.trim() + ",";
		}
		if (!"undefined".equals(sortname) && !"".equals(sortname)) {
			 condition += " order by " + sortname;
				if ("desc".equals(sortorder) || "asc".equals(sortorder)) {
					condition += " " + sortorder;
				} else {
					condition += " desc";
				}
			} else {
				condition += " order by ada.seq asc";
			}

		String sql = "SELECT ada.org_id_lend,ada.org_name_lend,ada.remark,ada.admin_id,ada.username,ada.truename,ada.phone, ada.email,ada.org_id,ada.org_name,ada.status,ada.org_infor,ada.area,ada.qq,ada.seq,"
						+ "(select  if(group_concat(t2.authname)='','无',group_concat(t2.authname))  from sys_user2auth t1,sys_auth t2  where t1.userid=ada.admin_id and t1.authid=t2.id) as auth from admin ada "
                +" where 1=1 "+param + condition;
		Object[] oArray = null;
		if (conditionP != null && !"".equals(conditionP)) {
			conditionP = conditionP.substring(0, conditionP.length() - 1);
			oArray = conditionP.split(",");
		}
		OraPaginatedList list = new OraPaginatedList(sql,oArray);
        list.setCurrentPage(pageNum);
        list.setObjectsPerPage(pageSize);
        return list;
	}
	
	/**
	 *运维用户导出
	 * @return
	 */
	public  List<Map<String, Object>> exportUserList(String username, String mobile) {
		String condition = "";
		String conditionP = "";
		if (!"".equals(username.trim())) {
			condition += " and a.truename like concat('%',?,'%') ";
			conditionP += username.trim() + ",";
		}
		if (!"".equals(mobile.trim())) {
			condition += " and a.phone = ?";
			conditionP += mobile.trim() + ",";
		}
		Object[] oArray = null;
		if (conditionP != null && !"".equals(conditionP)) {
			conditionP = conditionP.substring(0, conditionP.length() - 1);
			oArray = conditionP.split(",");
		}
		StringBuilder buffer = new StringBuilder();
		buffer.append("SELECT ada.admin_id,ada.username,ada.truename,ada.phone, ada.email,ada.org_id,ada.org_name,ada.status,ada.org_infor,ada.area,ada.qq,");
		buffer.append("(select  if(group_concat(t2.authname)='','无',group_concat(t2.authname))  from sys_user2auth t1,sys_auth t2  where t1.userid=ada.admin_id and t1.authid=t2.id) as auth from admin ada  where 1=1");
		buffer.append(condition);
		return this.getList_Pre(buffer.toString(), oArray);
	}
	
	/**
	 * 根据用户ID,获取所有的用户的权限名称
	 * @param id
	 * @return
	 */
	public String queryAuthNames(String id) 
	{
		StringBuffer buff = new StringBuffer();
		Object[] object = new Object[1];
		object[0] = id;
		String sql = "select b.id,b.authname from sys_user2auth a,sys_auth b where a.authid=b.id and a.userid=?";
		List<Map<String, Object>> list = this.getList_Pre(sql, object);
        if(list!=null && list.size() >0)
        { 
        	for(int i=0,length=list.size();i<length;i++)
        	{
        		Map<String, Object> map_tmp = (Map<String, Object>)list.get(i);
        		buff.append((String)map_tmp.get("AUTHNAME"));
        		buff.append("~");
        	}
			String tmp = buff.toString();
			return tmp.substring(0, tmp.length()-1);
        }
        else
        {
        	return null;
        	
        }
	}
	/**
	 * 根据用户ID,获取所有的用户的权限ID
	 * @param id
	 * @return
	 */
	public String queryAuthIDs(String id) 
	{
		StringBuffer buff = new StringBuffer();
		Object[] object = new Object[1];
		object[0] = id;
		String sql = "select b.id from sys_user2auth a,sys_auth b where a.authid=b.id and upper(a.userid)=?";
		List<Map<String, Object>> list = this.getList_Pre(sql, object);
        if(list!=null && list.size() >0)
        { 
        	for(int i=0,length=list.size();i<length;i++)
        	{
        		Map<String, Object> map_tmp = (Map<String, Object>)list.get(i);
        		buff.append(((BigDecimal)map_tmp.get("ID")).intValue());
        		buff.append(",");
        	}
			String tmp = buff.toString();
			return tmp.substring(0, tmp.length()-1);
        }
        else
        {
        	return null;
        	
        }
	}
	
	public Map<String, Object> queryUser(String sys_id)
	{
		Map<String, Object> map = null;
		if(sys_id!= null && !sys_id.trim().equals(""))
		{
			StringBuilder sb=new StringBuilder();
			sb.append("select a.admin_id,a.org_id,a.org_name,");
			sb.append("       a.username,");
			sb.append("       a.phone,");
			sb.append("       a.email,");
			sb.append("       (select if(group_concat(t2.id)='','无',group_concat(t2.id))");
			sb.append("          from sys_user2auth t1, sys_auth t2");
			sb.append("         where t1.userid = a.admin_id");
			sb.append("           and t1.authid = t2.id) auth");
			sb.append("  from admin a");
			sb.append("  where ");
			sb.append("   a.admin_id = '"+sys_id+"'");
		    //log.info("查询用户$%#$##："+sb.toString());                
			map =  this.getHashMap_Pre(sb.toString());
		}
		return map;
	}
	
	//获得菜单的button
	public List<Map<String, Object>> queryButtons(String menu_id)
	{
			StringBuilder sb=new StringBuilder();
			sb.append("select mb.id,mb.button_name from sys_menu2button mb left JOIN sys_menu m ON mb.menu_id=m.ID where ");
			sb.append(" mb.menu_id= '"+menu_id+"'");
			return this.getList_Pre(sb.toString());
	}
	
	public Map<String, Object> queryUseRole(String sys_id)
	{
		Map<String, Object> map = null;
		if(sys_id!= null && !sys_id.trim().equals(""))
		{
			StringBuilder sb=new StringBuilder();
			sb.append("select a.admin_id,a.org_id,a.org_name,");
			sb.append("       t2.id as auth_id,");
			sb.append("       t2.authname ");
			sb.append("  from admin a,sys_user2auth t1, sys_auth t2");
			sb.append("  where t1.userid = a.admin_id");
			sb.append("           and t1.authid = t2.id and ");
			sb.append("   a.admin_id = '"+sys_id+"'");
		    //log.info("查询用户权限$%#$##："+sb.toString());                
			map =  this.getHashMap_Pre(sb.toString());
		}
		return map;
	}
	
	
	
	public List<Map<String, Object>> getAuths()
	{
		String sql = "select * from sys_auth";
		return this.getList_Pre(sql);
	}
	
	public List<Map<String, Object>> getAuths_area()
	{
		String sql = "select * from sys_auth where id in(460,440)";
		return this.getList_Pre(sql);
	}
	
	
	public List<Map<String, Object>> queryAllUser(int start,int rows,String username,String startTime){
		String flag = "";
		if(!username.equals("")){
			flag = " and truename like '%"+username+"%'";
		}
		String flag1 = "";
        String[] ds = startTime.split("-");
        if(!startTime.equals("")){
            flag1 = " and year(t_date)= "+ds[0]+" and month(t_date) = "+ds[1]+"";
        }
		String sql = "select truename from admin "
                +" where status = 0 "+flag+" and admin_id in(SELECT distinct uid FROM work_time where 1=1"+ flag1+") order by seq asc  limit "+start+","+rows+" ";
		return this.getList_Pre(sql);
	}
	public List<Map<String, Object>> queryAllUser2(int start,int rows,String username,String orgId,String startTime){
        String flag = "";
        if(!username.equals("")){
            flag = " and truename like '%"+username+"%'";
        }
        String flag1 = "";
        String[] ds = startTime.split("-");
        if(!startTime.equals("")){
            flag1 = " and year(t_date)= "+ds[0]+" and month(t_date) = "+ds[1]+"";
        }
        String sql = "select truename from admin "
                +" where status = 0 "+flag+" and org_id in("+orgId+") and admin_id in(SELECT distinct uid FROM work_time where 1=1"+ flag1+") order by seq asc  limit "+start+","+rows+" ";
        return this.getList_Pre(sql);
    }
	
	
	
	public List<Map<String, Object>> queryAllUserCount2(String username,String OrgId,String startTime){
		String flag = "";
		if(!username.equals("")){
			flag = " and truename like '%"+username+"%'";
		}
		String flag1 = "";
        String[] ds = startTime.split("-");
        if(!startTime.equals("")){
            flag1 = " and year(t_date)= "+ds[0]+" and month(t_date) = "+ds[1]+"";
        }
		String sql = "select count(*) as num from admin "
				+" where status = 0 "+flag+" and  org_id in("+OrgId+") and admin_id in(SELECT distinct uid FROM work_time where 1=1"+ flag1+") order by admin_id asc ";
		return this.getList_Pre(sql);
	}
	
	public List<Map<String, Object>> queryAllUserCount(String username,String startTime){
        String flag = "";
        if(!username.equals("")){
            flag = " and truename like '%"+username+"%'";
        }
        String flag1 = "";
        String[] ds = startTime.split("-");
        if(!startTime.equals("")){
            flag1 = " and year(t_date)= "+ds[0]+" and month(t_date) = "+ds[1]+"";
        }
        String sql = "select count(*) as num from admin "
                +" where status = 0 "+flag+" and admin_id in(SELECT distinct uid FROM work_time where 1=1"+ flag1+") order by admin_id asc ";
        return this.getList_Pre(sql);
    }
	
	
	
	public boolean editUser(String org_id,String org_name,String sys_id,String[] auths)
	{
		ArrayList<String> list = new ArrayList<String>();
		String sql = "delete from sys_user2auth where userid="+sys_id;
		list.add(sql);
		for(int i=0;i<auths.length;i++)
		{
			sql =" insert into sys_user2auth (userid,authid) values(" +
					"'"+sys_id+"','"+auths[i]+"')";
			list.add(sql);
		}
		return this.executeBatch(list);
	}
	
	
	public boolean createUser(Admin admin,String[] auths)
	{
		ArrayList<String> list = new ArrayList<String>();
		if(SysPara.db_bz==0)
		{
    		String sql_ = "insert into admin(privilege_id,username,password,phone,email,truename,status,org_name,org_id,org_infor,qq,org_name_lend,remark,org_id_lend) values("+
    				admin.getPrivilegeId()+",'"+admin.getName()+"','"+admin.getPassword()+
    				"','"+admin.getPhoneNum()+"','"+admin.getEmail()+"','"+admin.getTruename()+"','"+ 0 +"','"+admin.getDepartment()+"','"
    				+admin.getDepartId()+"','"+admin.getOrg_infor()+"','"+admin.getQq()+"','"+admin.getDepartment_t()+"','"+admin.getRemark()+"','"+admin.getDepartId_t()+"')";
    		long user_id = this.executeInsert_backID(sql_);
    		for(int i=0;i<auths.length;i++)
    		{
    			String sql =" insert into sys_user2auth (userid,authid) values(" +
    					"'"+user_id+"','"+auths[i]+"')";
    			list.add(sql);
    		}
		}
		else
		{
			String admin_id = this.getSeq_oracle("SEQ_CONGIF_IMPL_MAIN");
    		String sql_ = "insert into admin(admin_id,privilege_id,username,password,phone,email,truename,status,org_name,org_id,org_infor,qqorg_name_lend,remark,org_id_lend) values("+
    				admin_id+","+admin.getPrivilegeId()+",'"+admin.getName()+"','"+admin.getPassword()+
    				"','"+admin.getPhoneNum()+"','"+admin.getEmail()+"','"+admin.getTruename()+"','"+ 0 +"','"+admin.getDepartment()+"','"
    				+admin.getDepartId()+"','"+admin.getOrg_infor()+"','"+admin.getQq()+"','"+admin.getDepartment_t()+"','"+admin.getRemark()+"','"+admin.getDepartId_t()+"')";
    		if(this.executeUpdate(sql_))
    		{
	    		for(int i=0;i<auths.length;i++)
	    		{
	    			String sql =" insert into sys_user2auth (SEQ_SYS_USER2AUTH.NEXTVAL,userid,authid) values(" +
	    					"'"+admin_id+"','"+auths[i]+"')";
	    			list.add(sql);
	    		}
    		}
		}
		return this.executeBatch(list);
	 }
	
	/**
	 * 修改密码
	 * @param sys_id
	 * @param old_pwd
	 * @param userpwdnew
	 * @return
	 */
	public boolean changepwd(String sys_id,String old_pwd,String userpwdnew)
	{
		String pwdmd5 = CookieUtil.getMD5(userpwdnew);
		if (this.userLogin(sys_id, old_pwd)) 
		{

			
			StringBuffer sb = new StringBuffer("update admin set password = ? ");
			sb.append(" where admin_id = ? ");
			Object[] object = new Object[2];
			object[0] = pwdmd5;
			object[1] = sys_id;
			return this.executeUpdate(sb.toString(), object);
		}
		else 
		{
			return false;
		}
		
	} 	
	
	public boolean resetpwd(String sys_id)
	{
		    String pwdmd5 = CookieUtil.getMD5("123456");			
			StringBuffer sb = new StringBuffer("update admin set password = ? ");
			sb.append(" where admin_id = ? ");
			Object[] object = new Object[2];
			object[0] = pwdmd5;
			object[1] = sys_id;
			return this.executeUpdate(sb.toString(), object);
		
	} 	
	
	/**
	 * 判断旧密码是否正确
	 * @param sys_id
	 * @param old_pwd
	 * @return
	 */
	public boolean userLogin(String sys_id, String old_pwd) 
	{
		String pwdmd5 = CookieUtil.getMD5(old_pwd);
		String sql = "select t.* from admin t where t.admin_id = ? "
				+ " and t.password = ? ";
		Object[] object = new Object[2];
		object[0] = sys_id;
		object[1] = pwdmd5;
		long count_num = this.getCount(sql, object);
		if(count_num>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	/**
	 * 获取系统参数
	 * @return
	 */
	public HashMap<String, String> smsParam() 
	{
		String sql = "select * from sys_paramter";
		log.debug("sql:" + sql);
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<Map<String, Object>> list =  this.getList_Pre(sql);
			if(list!=null && list.size()>0)
			{
				for(Map<String, Object> map_:list)
				{
					map.put(String.valueOf(map_.get("PARANAME")), String.valueOf(map_.get("PARAVALUE")));
				}
			}
			return map;
	}
	
	/**
	 * 获取系统参数server.properties配置文件的参数
	 * @return
	 */
	public HashMap<String, String> serverParam(String replaceBegin,String activeString) 
	{
		String sql = "select * from sys_paramter t where t.PARANAME like '"+replaceBegin+"%'";
		log.debug("sql:" + sql);
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<Map<String, Object>> list =  this.getList_Pre(sql);
			if(list!=null && list.size()>0)
			{
				for(Map<String, Object> map_:list)
				{
					map.put(String.valueOf(map_.get("PARANAME")).replace(replaceBegin, ""), String.valueOf(map_.get("PARAVALUE")));
					map.put(String.valueOf(map_.get("PARANAME")).replace(replaceBegin, "")+activeString, String.valueOf(map_.get("ACTIVE")));
				}
			}
			return map;
	}
	
	
	/**
	 * 获取系统参数列表
	 * @param PARANAME
	 * @return
	 */
	public OraPaginatedList queryParam(Map<String, Object> param) 
	{

		// 检索的参数名称
		String paraName = param.size() == 0 ? null : param.get("paraName").toString();		
		int pageNum = (Integer)param.get("pageNum");
		int pageSize = (Integer)param.get("pageSize");
		if(paraName!=null && !paraName.equals(""))
		{
			String sql = "select t.*,CASE t.active WHEN 0 THEN '重启生效' when 1 then '立即生效' end AS ACTIVE_STR from sys_paramter t where t.PARANAME like '%"+paraName+"%'";
	        OraPaginatedList list = new OraPaginatedList(sql);
	        list.setCurrentPage(pageNum);
	        list.setObjectsPerPage(pageSize);
	        return list;
		}
		else
		{
			String sql = "select t.*,CASE t.active WHEN 0 THEN '重启生效' when 1 then '立即生效' end AS ACTIVE_STR from sys_paramter t ";
	        OraPaginatedList list = new OraPaginatedList(sql);
	        list.setCurrentPage(pageNum);
	        list.setObjectsPerPage(pageSize);
	        return list;
		}
		
	}

	/**
	 * 获取系统参数列表的总条数
	 * @return
	 */
	public int getParamCount(Map<String, Object> param) {
		// 检索的参数名称
		String paraName = param.size() == 0 ? null : param.get("paraName").toString();
		String sql ="";
		if(paraName!=null && !paraName.equals("")){
			sql = "select * from sys_paramter t where t.PARANAME like '%"+paraName+"%' ";
		}else{
			sql = "select * from sys_paramter t ";
		}
		return this.getCountNoCond(sql);
	}
	
	public boolean addSyspara(HttpServletRequest request)
	{

		String PARANAME=StringHelper.noEmpty2(request.getParameter("PARANAME")).trim();
		String PARAVALU=StringHelper.noEmpty2(request.getParameter("PARAVALU")).trim();
		String REMARK=StringHelper.noEmpty2(request.getParameter("REMARK")).trim();
		StringBuilder sb=new StringBuilder();
		if(SysPara.db_bz==0)
		{
		   sb.append("insert into sys_paramter ");
		   sb.append("  (PARANAME, PARAVALUE, REMARK, active) values(?,?,?,0)");
		}
		else
		{
			sb.append("insert into sys_paramter ");
			sb.append("  (id,PARANAME, PARAVALUE, REMARK, active) values(SEQ_SYS_PARAMTER.NEXTVAL,?,?,?,0)");
		}
		Object[] object = new Object[3];
		object[0] = PARANAME;
		object[1] = PARAVALU;
		object[2] = REMARK;
		return this.executeInsert(sb.toString(), object);

	}
	
	
	/**
	 * 更新系统参数
	 * @param request
	 * @return
	 */
	public boolean updateSyspara(HttpServletRequest request)
	{

		String id=StringHelper.noEmpty2(request.getParameter("id")).trim();
		String PARANAME=StringHelper.noEmpty2(request.getParameter("PARANAME")).trim();
		String PARAVALU=StringHelper.noEmpty2(request.getParameter("PARAVALU")).trim();
		String REMARK=StringHelper.noEmpty2(request.getParameter("REMARK")).trim();
		String SXTYPE=StringHelper.noEmpty2(request.getParameter("SXTYPE")).trim();
		StringBuilder sb=new StringBuilder();
		//重启生效
		if(SXTYPE.equals("1"))
		{
			sb.append("update sys_paramter ");
			sb.append(" set PARANAME =?,PARAVALUE=?,REMARK=? where id=?");
			//log.info("新增系统组织："+sb.toString());
			Object[] object = new Object[4];
			object[0] = PARANAME;
			object[1] = PARAVALU;
			object[2] = REMARK;
			object[3] = id;
			return this.executeUpdate(sb.toString(), object);
		}
		//理解生效，只能部分参数才可以
		else
		{
			sb.append("update sys_paramter ");
			sb.append(" set PARANAME =?,PARAVALUE=?,REMARK=? where id=?");
			//log.info("新增系统组织："+sb.toString());
			Object[] object = new Object[4];
			object[0] = PARANAME;
			object[1] = PARAVALU;
			object[2] = REMARK;
			object[3] = id;
			if(this.executeUpdate(sb.toString(), object))
			{
				log.info("理解更新系统参数PARANAME="+PARANAME+",PARAVALU="+PARAVALU);
				return this.setStaticSysparam(PARANAME, PARAVALU);
			}
			else
			{
				return false;
			}
				
		}
	}
	
	
	private boolean setStaticSysparam(String PARANAME,String PARAVALU)
	{
		boolean return_flag =true;
        switch(PARANAME) 
        {
        case "page_num":
        	SysPara.page_num = Integer.valueOf(PARAVALU).intValue();
            break;
        case "CLIENT_VESION":
        	SysPara.CLIENT_VESION = PARAVALU;
            break;
        case "szxcurl":
        	SysPara.szxcurl = PARAVALU;
            break;
        case "szxcInfourl":
        	SysPara.szxcInfourl = PARAVALU;
            break;
        default:
        	return_flag = false;
            break;
        
        }
		return return_flag;
	}
	
	/**
	 * 查询用户名是否存在
	 * @param nickname
	 * @return 
	 */
	public boolean judgeUser(String name,long id) {
		String sql = "select  * from admin where username=? and admin_id!=? and username is not null and username!=' '";
		Object[] object = new Object[2];
		object[0] = name;
		object[1] = id;
		boolean exist =false;
		Map<String, Object> map = this.getHashMap_Pre(sql, object);
		if(map != null && map.size() > 0) {
			exist = true;
		}
		return exist;
	}
	public boolean judge(String name) {
		String sql = "select  * from admin where username=?  and username is not null and username!=' '";
		Object[] object = new Object[1];
		object[0] = name;
		boolean exist =true;
		Map<String, Object> map = this.getHashMap_Pre(sql, object);
		if(map != null && map.size() > 0) {
			exist = false;
		}
		return exist;
	}
	
	/**
	 * 删除某一条运维用户
	 * 
	 * @param id
	 *            用户ID
	 * @return
	 */
	public boolean deleteOperUser(long id) {
		String sql = "delete from admin where admin_id=" + id;
        return this.executeUpdate(sql);
	}
	
	//TODO
	public List<Map<String, Object>> queryUser2authId(String authId) 
	{
		List<Map<String, Object>> list = null;
		if(authId!= null && !authId.trim().equals(""))
		{
			String sql="select t1.AUTHID as AUTHID,t1.USERID as USERID,t2.AUTHNAME as AUTHNAME,t2.ID as ID,a.admin_id as ADMIN_ID,a.username as USERNAME,a.truename as TRUENAME from sys_user2auth t1, sys_auth t2,admin a where t1.userid = a.admin_id and t1.authid = t2.id and t2.id="+authId+"";               
			list =  this.getList_Pre(sql);
		}
		return list;
	}
	
	public boolean updateProcessRestart(String id){
		
		
		String sql = "update wf_process set state = 1 where id = '"+id+"'";
        return this.executeUpdate(sql);
		
	}
	
	
	
	/**
	 *获得所有正常用户
	 * @return
	 */
	public List<Map<String, Object>> getUserList(String username,String assignee,String org_id) {
		String sql = "SELECT a.* FROM admin a , sys_user2auth s WHERE a.`status`=0";
		String conditionP = "";
		if(username != null && !username.equals(""))
		{
			sql += " and a.truename like concat('%',?,'%') ";
			conditionP += StringHelper.noEmpty2(username).trim() + ",";
		}
		if(assignee != null && !assignee.equals(""))
		{
			sql += " and s.USERID = a.admin_id and s.AUTHID = ?";
			conditionP += StringHelper.noEmpty2(assignee).trim() + ",";
		}
		if(org_id != null && !org_id.equals(""))
		{
			sql += " and a.org_id = ?";
			conditionP += StringHelper.noEmpty2(org_id).trim() + ",";
		}
		Object[] oArray_count = null;
		if (conditionP != null && !"".equals(conditionP)) 
		{
			conditionP = conditionP.substring(0, conditionP.length() - 1);
			oArray_count = conditionP.split(",");
		}
		List<Map<String, Object>> result = this.getList_Pre(sql,oArray_count);
		return result;
	}
	
	
	
	public Map<String, Object> getUserInfo(String username) 
	{
		String sql = "SELECT a.* FROM admin a  WHERE a.`status`=0";
		if(username != null && !username.equals(""))
		{
			sql += " and a.username=? ";
			return this.getHashMap_Pre(sql, username);
		}
		else
		{
			return null;
		}

	}
	
	
	/**
	 * 更新选中用户的角色
	 * @param adminids
	 * @param usernames
	 * @param auths
	 * @return
	 */
	public boolean updateAuth(String[] adminids, String[] usernames,String[] auths) {
		ArrayList<String> list = new ArrayList<String>();
		String sql ="";
		//然后循环为每个用户分配所有选中的角色
		for(int j=0;j<adminids.length;j++)
		{
			for(int k=0;k<auths.length;k++){
				if(this.getList_Pre("select * from sys_user2auth where userid ="+adminids[j]+" and authid ="+auths[k]).isEmpty())
				{
					if(SysPara.db_bz==0)
					{
					   sql =" insert into sys_user2auth (userid,authid) values('"+adminids[j]+"','"+auths[k]+"')";
					}
					else
					{
						sql =" insert into sys_user2auth (id,userid,authid) values(SEQ_SYS_USER2AUTH.NEXTVAL,'"+adminids[j]+"','"+auths[k]+"')";
					}
					list.add(sql);
				}
			}
		}
		return this.executeBatch(list);
	}
	
	/**
	 *获得所有第三方用户字典表
	 * @return
	 */
	public List<Map<String, Object>> getThirdPartyList() {
		String sql = "SELECT * FROM web_cooperator where status = 1";
		List<Map<String, Object>> result = this.getList_Pre(sql);
		return result;
	}
	
	public List<Map<String, Object>> getUserDepartmentInfos(String info,String orgs){
		StringBuffer sb = new StringBuffer();
		info = info.replaceAll("#", "##").replaceAll("%", "#%").replaceAll("_", "#_");
		sb.append("select * from admin t where (t.org_infor LIKE '%").append(info).append("%'");
		sb.append(" OR t.truename LIKE '%").append(info).append("%' ESCAPE '#') ");
		if(!"".equals(orgs))
			sb.append(" and t.org_id in (").append(orgs).append(")");
		return this.getList_Pre(sb.toString());
	}
	
	public List<Map<String, Object>> getOrganizationInfos(){
		StringBuffer sb = new StringBuffer();
		sb.append("select * from (select t.super_org_id,t.org_name,t.org_id,null as org_infor,null as seq from organization t WHERE t.status = 1 ORDER BY t.org_id ASC)as t1");
		sb.append(" union all ");
		sb.append("SELECT t1.org_id super_org_id,t1.truename org_name,CONCAT(t1.admin_id , '_personId_',t1.username) org_id,t1.org_infor,t1.seq as seq from admin t1 WHERE t1.status = 0 order by seq ASC");
		return this.getList_Pre(sb.toString());
	}
	public List<Map<String, Object>> getOrganizationInfos2(String orgId){
        StringBuffer sb = new StringBuffer();
        sb.append("select * from (select t.super_org_id,t.org_name,t.org_id,null as org_infor,null as seq from organization t WHERE t.status = 1 AND org_id="+orgId +" ORDER BY t.org_id ASC)as t1");
        sb.append(" union all ");
        sb.append("SELECT t1.org_id super_org_id,t1.truename org_name,CONCAT(t1.admin_id , '_personId_',t1.username) org_id,t1.org_infor,t1.seq as seq from admin t1 WHERE t1.status = 0 AND t1.org_id="+orgId+" order by seq ASC");
        return this.getList_Pre(sb.toString());
    }

	
	
	public List<Map<String, Object>> getOrgUser(long orgId){
		String sql = "SELECT a.truename,a.admin_id FROM admin a RIGHT JOIN organization as b ON b.org_id = a.org_id WHERE a.`status` = 0 AND a.org_id = ?  order by a.seq asc";
		return this.getList_Pre(sql, new Object[]{orgId});
	}

	
	
	
	
	/**
	 * 数据导出
	 * @param map
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	 @SuppressWarnings("deprecation")
	public List<Map<String, Object>> getExportDara_user(Map<String, Object> map) throws UnsupportedEncodingException 
	 {
			//String condition=this.getDesSql(map);
			String conditionP = "";
			String param = "";
			
			if(map!= null && !StringHelper.noEmpty2(map.get("truename")).trim().equals(""))
			{
				param += " and a.truename like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("truename")).trim(),"utf-8") + ",";
			}
			if(map!= null && !StringHelper.noEmpty2(map.get("org_infor")).trim().equals(""))
			{
				param += " and a.org_infor like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("org_infor")).trim(),"utf-8") + ",";
			}
			String sql =  "SELECT a.username,a.truename,a.org_name,CASE a.status WHEN '0' THEN '正常' else '被冻结' end AS  'status',a.org_infor,a.qq,"+
                         "s2.AUTHNAME  from (admin a left outer join sys_user2auth s1 on  s1.userid=a.admin_id) LEFT outer JOIN sys_auth s2 on  s1.authid=s2.id "
					
					
					+ " where 1=1 " +param +" GROUP BY  a.admin_id ";
					
	                
			Object[] oArray_count = null;
			if (conditionP != null && !"".equals(conditionP)) 
			{
				conditionP = conditionP.substring(0, conditionP.length() - 1);
				oArray_count = conditionP.split(",");
			}	
			return this.getList_Pre(sql, oArray_count);
		 
	 }	
	 
	 
	 /**
		 * 数据库字段和导出标题栏的对应关系
		 *
		 */
		public  Map<String, String> getList_Column_user(Map<String, Object> map) throws UnsupportedEncodingException 
		{	
			StringBuilder sb=new StringBuilder();
			sb.append("SELECT ");
			int i = 0;
			if(map!= null && !StringHelper.noEmpty2(map.get("un")).trim().equals("")){
			sb.append(" a.username as '登录账号',");
			}
			if(map!= null && !StringHelper.noEmpty2(map.get("tn")).trim().equals("")){
				sb.append(" a.truename as '员工姓名',");
				}
			if(map!= null && !StringHelper.noEmpty2(map.get("st")).trim().equals("")){
				sb.append(" a.status as '账号状态',");
				}
			if(map!= null && !StringHelper.noEmpty2(map.get("q")).trim().equals("")){
				sb.append(" a.qq as 'QQ号码',");
				}
			if(map!= null && !StringHelper.noEmpty2(map.get("au")).trim().equals("")){
				sb.append(" s2.AUTHNAME as  '角色',");
				}
			if(map!= null && !StringHelper.noEmpty2(map.get("dm")).trim().equals("")){
				sb.append(" a.org_name as '部门名称',");
				}
			if(map!= null && !StringHelper.noEmpty2(map.get("oi")).trim().equals("")){
				sb.append(" a.org_infor as '岗位信息'");
				}
			sb.append("from (admin a left outer join sys_user2auth s1 on  s1.userid=a.admin_id) LEFT outer JOIN sys_auth s2 on  s1.authid=s2.id GROUP BY  a.admin_id");
			 StringBuffer strb = new StringBuffer(sb);
				int d = sb.indexOf("from (admin a");
			    char more = sb.charAt(d-1);
				if(more == ',' ){
					strb.deleteCharAt(d-1);
				}
			
			return this.getList_Column(strb.toString());
		}
	 

}