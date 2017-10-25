package com.zqkj.bsm.system.action.dao;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.zqkj.bsm.dal.dboper.DbOperator;
import com.zqkj.bsm.system.action.SysPara;
import com.zqkj.bsm.system.action.util.StringHelper;


/**
 * 公司组织架构数据库操作层
 * @author 0242
 *
 */
public class OrganizationDao extends DbOperator
{

	Logger log = Logger.getLogger(OrganizationDao.class);
	private static OrganizationDao instance = null;
	private OrganizationDao(){
		
	}
	public static OrganizationDao getInstance()
	{
		if(instance==null)
		{
			instance = new OrganizationDao();
		}
		return instance;
	}
	
	
	public  ArrayList<Map<String, Object>> getAllOrgs()
	{
		StringBuilder sb=new StringBuilder();
		sb.append("select * from organization t");
		sb.append(" where t.status=1 order by t.super_org_id, t.org_type asc");
		 ArrayList<Map<String, Object>> list=super.getList_Pre(sb.toString());		
		return list;
	}
	
	public  ArrayList<Map<String, Object>> getOrgByOrgId(String orgId)
    {
        StringBuilder sb=new StringBuilder();
        sb.append("select * from organization t");
        sb.append(" where t.status=1 and org_id="+orgId+" order by t.super_org_id, t.org_type asc");
         ArrayList<Map<String, Object>> list=super.getList_Pre(sb.toString());      
        return list;
    }
	 
	public String getOrgJson(ArrayList<Map<String, Object>> list)
	{
		StringBuilder sb=new StringBuilder();
		sb.append("{ id:1, pId:0, name:\"全部组织\",orgtype:\"3\", open:true},");
		for(int i=0;i<list.size();i++)
		{
			Map<String, Object> map=list.get(i);
			String org_id=StringHelper.noEmpty2(map.get("ORG_ID")).trim();
			String org_name=StringHelper.noEmpty2(map.get("ORG_NAME")).trim();
			String super_org_id=StringHelper.noEmpty2(map.get("SUPER_ORG_ID")).trim();
			String org_type=StringHelper.noEmpty2(map.get("ORG_TYPE")).trim();
			
			sb.append("{ id:'"+org_id+"', pId:"+super_org_id+",orgtype:\""+org_type+"\", name:\""+org_name+"\"},");
		}		
		String result=sb.toString();
		if(result.endsWith(",")){
			result=result.substring(0,result.length()-1);
			//log.info("获取全部组织列表--转JSON"+result);
		}
		return result;
	}
	
	
	
	public long addOrg(HttpServletRequest request)
	{
		String org_name=StringHelper.noEmpty2(request.getParameter("ORG_NAME")).trim();
		String super_org_id=StringHelper.noEmpty2(request.getParameter("SUPER_ORG_ID")).trim();
		String super_org_name=StringHelper.noEmpty2(request.getParameter("SUPER_ORG_NAME")).trim();
		String org_type=StringHelper.noEmpty2(request.getParameter("ORG_TYPE")).trim();
		StringBuilder sb=new StringBuilder();
		long r=-1;
		if(SysPara.db_bz==0)
		{
			sb.append("insert into organization");
			sb.append("  (org_name, super_org_id, super_org_name, org_type, status) values(?,?,?,?,1)");
			Object[] object = new Object[4];
			object[0] = org_name;
			object[1] = super_org_id;
			object[2] = super_org_name;
			object[3] = org_type;
			r=this.executeInsert_backID(sb.toString(), object);
		}
		else
		{
			String org_id = this.getSeq_oracle("SEQ_ORGANIZATION");
			sb.append("insert into organization");
			sb.append("  (org_id,org_name, super_org_id, super_org_name, org_type, status) values("+org_id+",?,?,?,?,1)");	
			Object[] object = new Object[4];
			object[0] = org_name;
			object[1] = super_org_id;
			object[2] = super_org_name;
			object[3] = org_type;
			this.executeInsert(sb.toString(), object);
			r = Long.valueOf(org_id);
		}
        return r;
	}

	/**
	 * 删除节点
	 * 
	 * @param request
	 * @return
	 */
	public String deleteOrg(HttpServletRequest request)
	{
		String org_id=StringHelper.noEmpty2(request.getParameter("ORG_ID")).trim();
		deleteSubOrg(org_id);

		return "ok";

	}

	/**
	 * 迭代逻辑删除子组织.
	 * 
	 * @param id
	 * @return
	 */
	public void deleteSubOrg(String id) {

		StringBuilder sb=new StringBuilder();
		sb.append("update organization");
		sb.append("   set  status = 0");
		sb.append(" where org_id =" + id);

		super.executeUpdate(sb.toString());
		
		List<Map<String, Object>> list = getSubOrg(id);
		for (Map<String, Object> map : list) {
			String orgId = StringHelper.noEmpty2(map.get("ORG_ID")).trim();
			deleteSubOrg(orgId);
		}
	}

	/**
	 * 根据父组织id获取子组织信息.
	 * 
	 * @param superOrgId 父组织id
	 * @return 子组织列表
	 */
	public List<Map<String, Object>> getSubOrg(String superOrgId) {
		StringBuilder sb=new StringBuilder();
		sb.append("select * from organization t");
		sb.append(" where t.status=1 and t.super_org_id = " + superOrgId);
		 List<Map<String, Object>> list=super.getList_Pre(sb.toString());		
		return list;
	}
	
	/**
     * 根据父组织id获取子组织Id.
     * 
     * @param superOrgId 父组织id
     * @return 子组织列表
     */
    public List<Map<String, Object>> getSubOrgIdBySupOrgId(String superOrgId) {
        StringBuilder sb=new StringBuilder();
        sb.append("select t.org_id from organization t");
        sb.append(" where t.status=1 and t.super_org_id in("+superOrgId+")");
         List<Map<String, Object>> list=super.getList_Pre(sb.toString());       
        return list;
    }
	

	/**
	 * 根据组织id修改组织名称节点
	 * 
	 * @param request
	 * @return
	 */
	public boolean updateOrg(HttpServletRequest request)
	{
		String org_id=StringHelper.noEmpty2(request.getParameter("ORG_ID")).trim();
		String org_name=StringHelper.noEmpty2(request.getParameter("ORG_NAME")).trim();
		StringBuilder sb=new StringBuilder();
		sb.append("update organization");
		sb.append("   set  org_name = '" + org_name + "'");
		sb.append(" where org_id = " + org_id);

		return super.executeUpdate(sb.toString());

	}

	public  ArrayList<Map<String, Object>> queryOrgs(String org_name)
	{
		StringBuilder sb=new StringBuilder();
		sb.append("select * from organization t");
		sb.append(" where t.status=1 and t.org_name like '%"+org_name+"%'");
		 ArrayList<Map<String, Object>> list=super.getList_Pre(sb.toString());		
		return list;
	}
	
	/**
	 * 根据运维用户ID获得其所在部门的ID
	 * 
	 */
	public BigDecimal getOrgId(String adminId) {
		BigDecimal orgId = null;
		String sql = "select org_id from admin  where 1=1 ";
		if (!StringUtils.isEmpty(adminId)) {
			sql +="and admin_id="+adminId;
		}
		Map<String, Object> map =this.getHashMap_Pre(sql);
		if(map!=null)
		{
			orgId = ((BigDecimal)map.get("ORG_ID"));
		}
		return orgId;
	}
	
	
	public boolean batchOrgain(ArrayList<Object[]> list){
		String sql = "update organization set org_type = ? where org_id = ?";
		return this.executeBatch_Pre(sql, list);
	}
}
