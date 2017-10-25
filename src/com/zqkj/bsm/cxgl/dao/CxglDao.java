package com.zqkj.bsm.cxgl.dao;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import com.zqkj.bsm.client.dao.ClientDao;
import com.zqkj.bsm.dal.dboper.DbOperator;
import com.zqkj.bsm.system.action.page.OraPaginatedList;
import com.zqkj.bsm.system.action.util.StringHelper;

public class CxglDao extends DbOperator{
	
	private static CxglDao instance = null;
	public static CxglDao getInstance()
	{
		if(instance==null)
		{
			synchronized (ClientDao.class)
			{
			  instance = new CxglDao();
			}
		}
		return instance;
	}
	
	/**
	 * 数据库字段和导出标题栏的对应关系
	 * 从业单位
	 */
	public  Map<String, String> getList_Column_cydwgl()
	{
		StringBuilder sb=new StringBuilder();
		sb.append("SELECT ");
		sb.append(" enterpriseName as '企业名称',");
		sb.append(" enterpiseQualification as '企业资质',");
		sb.append(" enterpriseType as '企业类型',");
		sb.append(" juridicalPerson as '法人代表'");
		sb.append(" FROM cxgl_cydwgl");
		return this.getList_Column(sb.toString());
	}
	
	public  Map<String, String> getList_Column_cyrygl()
	{
		StringBuilder sb=new StringBuilder();
		sb.append("SELECT ");
		sb.append(" name as '姓名',");
		sb.append(" sex as '性别',");
		sb.append(" education as '学历',");
		sb.append(" major as '专业',");
		sb.append(" title as '职称',");
		sb.append(" workUnit as '工作单位'");
		sb.append(" FROM cxgl_cyrygl");
		return this.getList_Column(sb.toString());
	}
	
	public  Map<String, String> getList_Column_blxwgl()
	{
		StringBuilder sb=new StringBuilder();
		sb.append("SELECT ");
		sb.append(" enterpriseName as '企业名称',");
		sb.append(" content as '内容',");
		sb.append(" type as '类型',");
		sb.append(" time as '时间'");
		sb.append(" FROM cxgl_blxwgl");
		return this.getList_Column(sb.toString());
	}
	
	public  Map<String, String> getList_Column_lhxwgl()
	{
		StringBuilder sb=new StringBuilder();
		sb.append("SELECT ");
		sb.append(" enterpriseName as '企业名称',");
		sb.append(" content as '内容',");
		sb.append(" type as '类型',");
		sb.append(" time as '时间'");
		sb.append(" FROM cxgl_lhxwgl");
		return this.getList_Column(sb.toString());
	}
	/**
	 * 数据导出不分页，但是超过一定量会有性能问题
	 * @param map
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	 @SuppressWarnings("deprecation")
	public List<Map<String, Object>> getExportDara_cydwgl(Map<String, Object> map) throws UnsupportedEncodingException 
	 {
			String condition=this.getDesSql(map);
			String conditionP = "";
			String param = "";
			if(map!= null && !StringHelper.noEmpty2(map.get("enterpriseName")).trim().equals(""))
			{
				param += " and a.enterpriseName like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("enterpriseName")).trim()) + ",";
			}
			if(map!= null && !StringHelper.noEmpty2(map.get("enterpiseQualification")).trim().equals(""))
			{
				param += " and a.enterpiseQualification like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("enterpiseQualification")).trim()) + ",";
			}
			if(map!= null && !StringHelper.noEmpty2(map.get("enterpriseType")).trim().equals(""))
			{
				param += " and a.enterpriseType like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("enterpriseType")).trim()) + ",";
			}
			if(map!= null && !StringHelper.noEmpty2(map.get("juridicalPerson")).trim().equals(""))
			{
				param += " and a.juridicalPerson like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("juridicalPerson")).trim()) + ",";
			}
			String sql = "select a.* from cxgl_cydwgl a  "
	                +" where 1=1  "+param + condition;
			Object[] oArray_count = null;
			if (conditionP != null && !"".equals(conditionP)) 
			{
				conditionP = conditionP.substring(0, conditionP.length() - 1);
				oArray_count = conditionP.split(",");
			}	
			return this.getList_Pre(sql, oArray_count);
		 
	 }
	 
	 public List<Map<String, Object>> getExportDara_cyrygl(Map<String, Object> map) throws UnsupportedEncodingException 
	 {
			String condition=this.getDesSql(map);
			String conditionP = "";
			String param = "";
			if(map!= null && !StringHelper.noEmpty2(map.get("name")).trim().equals(""))
			{
				param += " and a.name like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("name")).trim()) + ",";
			}
			if(map!= null && !StringHelper.noEmpty2(map.get("education")).trim().equals(""))
			{
				param += " and a.education like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("education")).trim()) + ",";
			}
			if(map!= null && !StringHelper.noEmpty2(map.get("major")).trim().equals(""))
			{
				param += " and a.major like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("major")).trim()) + ",";
			}
			if(map!= null && !StringHelper.noEmpty2(map.get("title")).trim().equals(""))
			{
				param += " and a.title like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("title")).trim()) + ",";
			}
			if(map!= null && !StringHelper.noEmpty2(map.get("workUnit")).trim().equals(""))
			{
				param += " and a.workUnit like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("workUnit")).trim()) + ",";
			}
			String sql = "select a.* from cxgl_cyrygl a  "
	                +" where 1=1  "+param + condition;
			Object[] oArray_count = null;
			if (conditionP != null && !"".equals(conditionP)) 
			{
				conditionP = conditionP.substring(0, conditionP.length() - 1);
				oArray_count = conditionP.split(",");
			}	
			return this.getList_Pre(sql, oArray_count);
		 
	 }
	

	
	public List<Map<String, Object>> getExportDara_lhxwgl(Map<String, Object> map) throws UnsupportedEncodingException 
	 {
			String condition=this.getDesSql(map);
			String conditionP = "";
			String param = "";
			if(map!= null && !StringHelper.noEmpty2(map.get("enterpriseName")).trim().equals(""))
			{
				param += " and a.enterpriseName like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("enterpriseName")).trim()) + ",";
			}
			if(map!= null && !StringHelper.noEmpty2(map.get("content")).trim().equals(""))
			{
				param += " and a.content like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("content")).trim()) + ",";
			}
			if(map!= null && !StringHelper.noEmpty2(map.get("type")).trim().equals(""))
			{
				param += " and a.type like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("type")).trim()) + ",";
			}
			if(map!= null && !StringHelper.noEmpty2(map.get("time")).trim().equals(""))
			{
				param += " and a.time like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("time")).trim()) + ",";
			}
			String sql = "select a.* from cxgl_lhxwgl a  "
	                +" where 1=1  "+param + condition;
			Object[] oArray_count = null;
			if (conditionP != null && !"".equals(conditionP)) 
			{
				conditionP = conditionP.substring(0, conditionP.length() - 1);
				oArray_count = conditionP.split(",");
			}	
			return this.getList_Pre(sql, oArray_count);
		 
	 }
	
	public List<Map<String, Object>> getExportDara_blxwgl(Map<String, Object> map) throws UnsupportedEncodingException 
	 {
			String condition=this.getDesSql(map);
			String conditionP = "";
			String param = "";
			if(map!= null && !StringHelper.noEmpty2(map.get("enterpriseName")).trim().equals(""))
			{
				param += " and a.enterpriseName like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("enterpriseName")).trim()) + ",";
			}
			if(map!= null && !StringHelper.noEmpty2(map.get("content")).trim().equals(""))
			{
				param += " and a.content like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("content")).trim()) + ",";
			}
			if(map!= null && !StringHelper.noEmpty2(map.get("type")).trim().equals(""))
			{
				param += " and a.type like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("type")).trim()) + ",";
			}
			if(map!= null && !StringHelper.noEmpty2(map.get("time")).trim().equals(""))
			{
				param += " and a.time like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("time")).trim()) + ",";
			}
			String sql = "select a.* from cxgl_blxwgl a  "
	                +" where 1=1  "+param + condition;
			Object[] oArray_count = null;
			if (conditionP != null && !"".equals(conditionP)) 
			{
				conditionP = conditionP.substring(0, conditionP.length() - 1);
				oArray_count = conditionP.split(",");
			}	
			return this.getList_Pre(sql, oArray_count);
		 
	 }

	
	/**
	 * 按条件查询获取
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public OraPaginatedList getCydwglList(Map<String, Object> map,int pageNum,int pageSize) 
	{
		String condition=this.getDesSql(map);
		String conditionP = "";
		String param = "";
		if(map!= null && !StringHelper.noEmpty2(map.get("enterpriseName")).trim().equals(""))
		{
			param += " and a.enterpriseName like concat('%',?,'%') ";
			conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("enterpriseName")).trim()) + ",";
		}
		if(map!= null && !StringHelper.noEmpty2(map.get("enterpiseQualification")).trim().equals(""))
		{
			param += " and a.enterpiseQualification like concat('%',?,'%') ";
			conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("enterpiseQualification")).trim()) + ",";
		}
		if(map!= null && !StringHelper.noEmpty2(map.get("enterpriseType")).trim().equals(""))
		{
			param += " and a.enterpriseType like concat('%',?,'%') ";
			conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("enterpriseType")).trim()) + ",";
		}
		if(map!= null && !StringHelper.noEmpty2(map.get("juridicalPerson")).trim().equals(""))
		{
			param += " and a.juridicalPerson like concat('%',?,'%') ";
			conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("juridicalPerson")).trim()) + ",";
		}
		String sql = "select a.* from cxgl_cydwgl a  "
                +" where 1=1  "+param + condition;
		Object[] oArray_count = null;
		if (conditionP != null && !"".equals(conditionP)) 
		{
			conditionP = conditionP.substring(0, conditionP.length() - 1);
			oArray_count = conditionP.split(",");
		}		
		OraPaginatedList list = new OraPaginatedList(sql,oArray_count);
		list.setSortCriterion(condition);
        list.setCurrentPage(pageNum);
        list.setObjectsPerPage(pageSize);
        return list;
	}
	
	public OraPaginatedList getCyryglList(Map<String, Object> map,int pageNum,int pageSize) 
	{
		String condition=this.getDesSql(map);
		String conditionP = "";
		String param = "";
		if(map!= null && !StringHelper.noEmpty2(map.get("name")).trim().equals(""))
		{
			param += " and a.name like concat('%',?,'%') ";
			conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("name")).trim()) + ",";
		}
		if(map!= null && !StringHelper.noEmpty2(map.get("education")).trim().equals(""))
		{
			param += " and a.education like concat('%',?,'%') ";
			conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("education")).trim()) + ",";
		}
		if(map!= null && !StringHelper.noEmpty2(map.get("major")).trim().equals(""))
		{
			param += " and a.major like concat('%',?,'%') ";
			conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("major")).trim()) + ",";
		}
		if(map!= null && !StringHelper.noEmpty2(map.get("title")).trim().equals(""))
		{
			param += " and a.title like concat('%',?,'%') ";
			conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("title")).trim()) + ",";
		}
		if(map!= null && !StringHelper.noEmpty2(map.get("workUnit")).trim().equals(""))
		{
			param += " and a.workUnit like concat('%',?,'%') ";
			conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("workUnit")).trim()) + ",";
		}
		String sql = "select a.* from cxgl_cyrygl a  "
                +" where 1=1  "+param + condition;
		Object[] oArray_count = null;
		if (conditionP != null && !"".equals(conditionP)) 
		{
			conditionP = conditionP.substring(0, conditionP.length() - 1);
			oArray_count = conditionP.split(",");
		}		
		OraPaginatedList list = new OraPaginatedList(sql,oArray_count);
		list.setSortCriterion(condition);
        list.setCurrentPage(pageNum);
        list.setObjectsPerPage(pageSize);
        return list;
	}
	
	public OraPaginatedList getLhxwglList(Map<String, Object> map,int pageNum,int pageSize) 
	{
		String condition=this.getDesSql(map);
		String conditionP = "";
		String param = "";
		if(map!= null && !StringHelper.noEmpty2(map.get("enterpriseName")).trim().equals(""))
		{
			param += " and a.enterpriseName like concat('%',?,'%') ";
			conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("enterpriseName")).trim()) + ",";
		}
		if(map!= null && !StringHelper.noEmpty2(map.get("content")).trim().equals(""))
		{
			param += " and a.content like concat('%',?,'%') ";
			conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("content")).trim()) + ",";
		}
		if(map!= null && !StringHelper.noEmpty2(map.get("type")).trim().equals(""))
		{
			param += " and a.type like concat('%',?,'%') ";
			conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("type")).trim()) + ",";
		}
		if(map!= null && !StringHelper.noEmpty2(map.get("time")).trim().equals(""))
		{
			param += " and a.time like concat('%',?,'%') ";
			conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("time")).trim()) + ",";
		}
		String sql = "select a.* from cxgl_lhxwgl a  "
                +" where 1=1  "+param + condition;
		Object[] oArray_count = null;
		if (conditionP != null && !"".equals(conditionP)) 
		{
			conditionP = conditionP.substring(0, conditionP.length() - 1);
			oArray_count = conditionP.split(",");
		}		
		OraPaginatedList list = new OraPaginatedList(sql,oArray_count);
		list.setSortCriterion(condition);
        list.setCurrentPage(pageNum);
        list.setObjectsPerPage(pageSize);
        return list;
	}
	
	public OraPaginatedList getBlxwglList(Map<String, Object> map,int pageNum,int pageSize) 
	{
		String condition=this.getDesSql(map);
		String conditionP = "";
		String param = "";
		if(map!= null && !StringHelper.noEmpty2(map.get("enterpriseName")).trim().equals(""))
		{
			param += " and a.enterpriseName like concat('%',?,'%') ";
			conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("enterpriseName")).trim()) + ",";
		}
		if(map!= null && !StringHelper.noEmpty2(map.get("content")).trim().equals(""))
		{
			param += " and a.content like concat('%',?,'%') ";
			conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("content")).trim()) + ",";
		}
		if(map!= null && !StringHelper.noEmpty2(map.get("type")).trim().equals(""))
		{
			param += " and a.type like concat('%',?,'%') ";
			conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("type")).trim()) + ",";
		}
		if(map!= null && !StringHelper.noEmpty2(map.get("time")).trim().equals(""))
		{
			param += " and a.time like concat('%',?,'%') ";
			conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("time")).trim()) + ",";
		}
		String sql = "select a.* from cxgl_blxwgl a  "
                +" where 1=1  "+param + condition;
		Object[] oArray_count = null;
		if (conditionP != null && !"".equals(conditionP)) 
		{
			conditionP = conditionP.substring(0, conditionP.length() - 1);
			oArray_count = conditionP.split(",");
		}		
		OraPaginatedList list = new OraPaginatedList(sql,oArray_count);
		list.setSortCriterion(condition);
        list.setCurrentPage(pageNum);
        list.setObjectsPerPage(pageSize);
        return list;
	}
	/**
	 * sb.append(" enterpriseName as '企业名称',");
		sb.append(" enterpiseQualification as '企业资质',");
		sb.append(" enterpriseType as '企业类型',");
		sb.append(" juridicalPerson as '法人代表'");
	 */
	public boolean saveCydwgl(Map<String, Object> map,String uid)
	{
		StringBuilder sb=new StringBuilder();
		sb.append("insert into cxgl_cydwgl(enterpriseName,enterpiseQualification,enterpriseType,juridicalPerson) values(?,?,?,?) ");
		Object[] object = new Object[4];
		object[0] = StringHelper.noEmpty2(map.get("enterpriseName")).trim();
		object[1] = StringHelper.noEmpty2(map.get("enterpiseQualification")).trim();
		object[2] = StringHelper.noEmpty2(map.get("enterpriseType")).trim();
		object[3] = StringHelper.noEmpty2(map.get("juridicalPerson")).trim();
		return this.executeUpdate(sb.toString(), object);
		
	}
	/**
	 * sb.append(" name as '姓名',");
		sb.append(" sex as '性别',");
		sb.append(" education as '学历',");
		sb.append(" major as '专业'");
		sb.append(" title as '职称'");
		sb.append(" workUnit as '工作单位'");
	 * @param map
	 * @param uid
	 * @return
	 */
	public boolean saveCyrygl(Map<String, Object> map,String uid)
	{
		StringBuilder sb=new StringBuilder();
		sb.append("insert into cxgl_cyrygl(name,sex,education,major,title,workUnit) values(?,?,?,?,?,?) ");
		Object[] object = new Object[6];
		object[0] = StringHelper.noEmpty2(map.get("name")).trim();
		object[1] = StringHelper.noEmpty2(map.get("sex")).trim();
		object[2] = StringHelper.noEmpty2(map.get("education")).trim();
		object[3] = StringHelper.noEmpty2(map.get("major")).trim();
		object[4] = StringHelper.noEmpty2(map.get("title")).trim();
		object[5] = StringHelper.noEmpty2(map.get("workUnit")).trim();
		return this.executeUpdate(sb.toString(), object);
		
	}
	
	/**
	 * sb.append(" enterpriseName as '企业名称',");
		sb.append(" content as '内容',");
		sb.append(" enterpriseType as '企业类型',");
		sb.append(" time as '时间'");
	 * @param map
	 * @param uid
	 * @return
	 */
	public boolean saveLhxwgl(Map<String, Object> map,String uid)
	{
		StringBuilder sb=new StringBuilder();
		sb.append("insert into cxgl_lhxwgl(enterpriseName,content,type,time) values(?,?,?,?) ");
		Object[] object = new Object[4];
		object[0] = StringHelper.noEmpty2(map.get("enterpriseName")).trim();
		object[1] = StringHelper.noEmpty2(map.get("content")).trim();
		object[2] = StringHelper.noEmpty2(map.get("type")).trim();
		object[3] = StringHelper.noEmpty2(map.get("time")).trim();
		return this.executeUpdate(sb.toString(), object);
		
	}
	public boolean saveBlxwgl(Map<String, Object> map,String uid)
	{
		StringBuilder sb=new StringBuilder();
		sb.append("insert into cxgl_blxwgl(enterpriseName,content,type,time) values(?,?,?,?) ");
		Object[] object = new Object[4];
		object[0] = StringHelper.noEmpty2(map.get("enterpriseName")).trim();
		object[1] = StringHelper.noEmpty2(map.get("content")).trim();
		object[2] = StringHelper.noEmpty2(map.get("type")).trim();
		object[3] = StringHelper.noEmpty2(map.get("time")).trim();
		return this.executeUpdate(sb.toString(), object);
	}

	/**
	 * sb.append(" enterpriseName as '企业名称',");
		sb.append(" enterpiseQualification as '企业资质',");
		sb.append(" enterpriseType as '企业类型',");
		sb.append(" juridicalPerson as '法人代表'");
	 */
	public boolean updateCydwgl(Map<String, Object> map,String uid)
	{

		StringBuilder sb=new StringBuilder();
		sb.append("update cxgl_cydwgl set enterpriseName=?,enterpiseQualification=?,enterpriseType=?,juridicalPerson=? where id =? ");
		Object[] object = new Object[5];
		object[0] = StringHelper.noEmpty2(map.get("enterpriseName")).trim();
		object[1] = StringHelper.noEmpty2(map.get("enterpiseQualification")).trim();
		object[2] = StringHelper.noEmpty2(map.get("enterpriseType")).trim();
		object[3] = StringHelper.noEmpty2(map.get("juridicalPerson")).trim();
		object[4] = StringHelper.noEmpty2(map.get("id")).trim();
		return this.executeUpdate(sb.toString(), object);
	}
	/**
	 * sb.append(" name as '姓名',");
		sb.append(" sex as '性别',");
		sb.append(" education as '学历',");
		sb.append(" major as '专业'");
		sb.append(" title as '职称'");
		sb.append(" workUnit as '工作单位'");
	 * @param map
	 * @param uid
	 * @return
	 */
	public boolean updateCyrygl(Map<String, Object> map,String uid)
	{

		StringBuilder sb=new StringBuilder();
		sb.append("update cxgl_cyrygl set name=?,sex=?,education=?,major=?,title=?,workUnit=? where id =? ");
		Object[] object = new Object[7];
		object[0] = StringHelper.noEmpty2(map.get("name")).trim();
		object[1] = StringHelper.noEmpty2(map.get("sex")).trim();
		object[2] = StringHelper.noEmpty2(map.get("education")).trim();
		object[3] = StringHelper.noEmpty2(map.get("major")).trim();
		object[4] = StringHelper.noEmpty2(map.get("title")).trim();
		object[5] = StringHelper.noEmpty2(map.get("workUnit")).trim();
		object[6] = StringHelper.noEmpty2(map.get("id")).trim();
		return this.executeUpdate(sb.toString(), object);
	}
	
	/**
	 * sb.append(" enterpriseName as '企业名称',");
		sb.append(" content as '内容',");
		sb.append(" enterpriseType as '企业类型',");
		sb.append(" time as '时间'");
	 * @param map
	 * @param uid
	 * @return
	 */
	public boolean updateLhxwgl(Map<String, Object> map,String uid)
	{

		StringBuilder sb=new StringBuilder();
		sb.append("update cxgl_lhxwgl set enterpriseName=?,content=?,type=?,time=? where id =? ");
		Object[] object = new Object[5];
		object[0] = StringHelper.noEmpty2(map.get("enterpriseName")).trim();
		object[1] = StringHelper.noEmpty2(map.get("content")).trim();
		object[2] = StringHelper.noEmpty2(map.get("type")).trim();
		object[3] = StringHelper.noEmpty2(map.get("time")).trim();
		object[4] = StringHelper.noEmpty2(map.get("id")).trim();
		return this.executeUpdate(sb.toString(), object);
	}
	
	public boolean updateBlxwgl(Map<String, Object> map,String uid)
	{

		StringBuilder sb=new StringBuilder();
		sb.append("update cxgl_blxwgl set enterpriseName=?,content=?,type=?,time=? where id =? ");
		Object[] object = new Object[5];
		object[0] = StringHelper.noEmpty2(map.get("enterpriseName")).trim();
		object[1] = StringHelper.noEmpty2(map.get("content")).trim();
		object[2] = StringHelper.noEmpty2(map.get("type")).trim();
		object[3] = StringHelper.noEmpty2(map.get("time")).trim();
		object[4] = StringHelper.noEmpty2(map.get("id")).trim();
		return this.executeUpdate(sb.toString(), object);
	}
	/**
	 * 删除项目建议书
	 * @param id
	 * @return
	 */
	public boolean deleteCydwgl(String id)
	{	
		StringBuilder sb=new StringBuilder();
		sb.append("delete from cxgl_cydwgl where id =? ");
		return this.executeUpdate(sb.toString(), id);
	}
	public boolean deleteCyrygl(String id)
	{	
		StringBuilder sb=new StringBuilder();
		sb.append("delete from cxgl_cyrygl where id =? ");
		return this.executeUpdate(sb.toString(), id);
	}
	public boolean deleteLhxwgl(String id)
	{	
		StringBuilder sb=new StringBuilder();
		sb.append("delete from cxgl_lhxwgl where id =? ");
		return this.executeUpdate(sb.toString(), id);
	}
	public boolean deleteBlxwgl(String id)
	{	
		StringBuilder sb=new StringBuilder();
		sb.append("delete from cxgl_blxwgl where id =? ");
		return this.executeUpdate(sb.toString(), id);
	}
	
	
	
	
}
