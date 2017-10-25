package com.zqkj.bsm.jsgl.dao;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import com.zqkj.bsm.client.dao.ClientDao;
import com.zqkj.bsm.dal.dboper.DbOperator;
import com.zqkj.bsm.system.action.page.OraPaginatedList;
import com.zqkj.bsm.system.action.util.StringHelper;

public class ProjectFinalAccountsDao extends DbOperator{
	
	private static ProjectFinalAccountsDao instance = null;
	public static ProjectFinalAccountsDao getInstance()
	{
		if(instance==null)
		{
			synchronized (ClientDao.class)
			{
			  instance = new ProjectFinalAccountsDao();
			}
		}
		return instance;
	}
	
	/**
	 * 数据库字段和导出标题栏的对应关系
	 * publisher,type,createtime
	 */
	public  Map<String, String> getList_Column()
	{
		StringBuilder sb=new StringBuilder();
		sb.append("SELECT ");
		sb.append(" publisher as '发布者',");
		sb.append(" type as '类型',");
		sb.append(" createtime as '创建时间',");
		sb.append(" filename as '文件名',");
		sb.append(" filepath as '文件路径'");
		sb.append(" FROM jsgl_projectfinalaccounts");
		return this.getList_Column(sb.toString());
	}
	
	/**
	 * 数据导出不分页，但是超过一定量会有性能问题
	 * @param map
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	 @SuppressWarnings("deprecation")
	public List<Map<String, Object>> getExportDara(Map<String, Object> map) throws UnsupportedEncodingException 
	 {
			String condition=this.getDesSql(map);
			String conditionP = "";
			String param = "";
			if(map!= null && !StringHelper.noEmpty2(map.get("filename")).trim().equals(""))
			{
				param += " and a.filename like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("filename")).trim(),"utf-8") + ",";
			}
			if(map!= null && !StringHelper.noEmpty2(map.get("publisher")).trim().equals(""))
			{
				param += " and a.publisher like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("publisher")).trim(),"utf-8") + ",";
			}
			if(map!= null && !StringHelper.noEmpty2(map.get("type")).trim().equals(""))
			{
				param += " and a.type like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("type")).trim(),"utf-8") + ",";
			}
			String sql = "select a.* from jsgl_projectfinalaccounts a  "
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
	 * 按条件查询获项目决算
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public OraPaginatedList queryProjectFinalAccounts(Map<String, Object> map,int pageNum,int pageSize) 
	{
		String condition=this.getDesSql(map);
		String conditionP = "";
		String param = "";
		if(map!= null && !StringHelper.noEmpty2(map.get("filename")).trim().equals(""))
		{
			param += " and a.filename like concat('%',?,'%') ";
			conditionP += StringHelper.noEmpty2(map.get("filename")).trim() + ",";
		}
		if(map!= null && !StringHelper.noEmpty2(map.get("type")).trim().equals(""))
		{
			param += " and a.type like concat('%',?,'%') ";
			conditionP += StringHelper.noEmpty2(map.get("type")).trim() + ",";
		}
		if(map!= null && !StringHelper.noEmpty2(map.get("publisher")).trim().equals(""))
		{
			param += " and a.publisher like concat('%',?,'%') ";
			conditionP += StringHelper.noEmpty2(map.get("publisher")).trim() + ",";
		}
		String sql = "select a.* from jsgl_projectfinalaccounts a  "
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
	 * 保存项目决算
	 * @param map
	 * @param uid
	 * @return
	 */
	public boolean addProjectFinalAccounts(Map<String, Object> map)
	{
		StringBuilder sb=new StringBuilder();
		sb.append("insert into jsgl_projectfinalaccounts(filename,publisher,type,createtime,filepath) values(?,?,?,?,?)");
		Object[] object = new Object[5];
		object[0] = StringHelper.noEmpty2(map.get("filename")).trim();
		object[1] = StringHelper.noEmpty2(map.get("publisher")).trim();
		object[2] = StringHelper.noEmpty2(map.get("type")).trim();
		object[3] = StringHelper.noEmpty2(map.get("createtime")).trim();
		object[4] = StringHelper.noEmpty2(map.get("filepath")).trim();
		return this.executeUpdate(sb.toString(), object);
		
	}

	/**
	 * 更新项目决算
	 * @param map
	 * @return
	 */
	public boolean updateProjectFinalAccounts(Map<String, Object> map)
	{

		StringBuilder sb=new StringBuilder();
		sb.append("update jsgl_projectfinalaccounts set filename=?,publisher=?,type=?,createtime=?,filepath=?where id =? ");
		Object[] object = new Object[6];
		object[0] = StringHelper.noEmpty2(map.get("filename")).trim();
		object[1] = StringHelper.noEmpty2(map.get("publisher")).trim();
		object[2] = StringHelper.noEmpty2(map.get("type")).trim();
		object[3] = StringHelper.noEmpty2(map.get("createtime")).trim();
		object[4] = StringHelper.noEmpty2(map.get("filepath")).trim();
		object[5] = StringHelper.noEmpty2(map.get("id")).trim();
		return this.executeUpdate(sb.toString(), object);
		
	}
	/**
	 * 删除项目决算
	 * @param id
	 * @return
	 */
	public boolean deleteProjectFinalAccounts(String id)
	{	
		StringBuilder sb=new StringBuilder();
		sb.append("delete from jsgl_projectfinalaccounts where id =? ");
		return this.executeUpdate(sb.toString(), id);
	}
	
	
}
