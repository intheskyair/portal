package com.zqkj.bsm.slProject.dao;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import com.zqkj.bsm.client.dao.ClientDao;
import com.zqkj.bsm.dal.dboper.DbOperator;
import com.zqkj.bsm.system.action.page.OraPaginatedList;
import com.zqkj.bsm.system.action.util.StringHelper;

public class PreliminaryDesignDao extends DbOperator{
	
	private static PreliminaryDesignDao instance = null;
	public static PreliminaryDesignDao getInstance()
	{
		if(instance==null)
		{
			synchronized (ClientDao.class)
			{
			  instance = new PreliminaryDesignDao();
			}
		}
		return instance;
	}
	/**
	 * 数据库字段和导出标题栏的对应关系
	 * title,code,unit,accept,approval,uid,filename,filepath,createtime
	 */
	public  Map<String, String> getList_Column()
	{
		StringBuilder sb=new StringBuilder();
		sb.append("SELECT ");
		sb.append(" title as '标题',");
		sb.append(" code as '事项编号',");
		sb.append(" unit as '申请单位',");
		sb.append(" accept as '受理部门',");
		sb.append(" approval as '审批部门',");
		sb.append(" filename as '文件名',");
		sb.append(" filepath as '文件路径',");
		sb.append(" createtime as '创建时间'");
		sb.append(" FROM kh_preliminarydesign");
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
			if(map!= null && !StringHelper.noEmpty2(map.get("title")).trim().equals(""))
			{
				param += " and a.title like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("title")).trim()) + ",";
			}
			if(map!= null && !StringHelper.noEmpty2(map.get("code")).trim().equals(""))
			{
				param += " and a.code like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("code")).trim()) + ",";
			}
			if(map!= null && !StringHelper.noEmpty2(map.get("approval")).trim().equals(""))
			{
				param += " and a.approval like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("approval")).trim()) + ",";
			}
			String sql = "select a.* from kh_preliminarydesign a  "
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
	 * 按条件查询获取初步设计
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public OraPaginatedList queryProject_cbsj(Map<String, Object> map,int pageNum,int pageSize) 
	{
		String condition=this.getDesSql(map);
		String conditionP = "";
		String param = "";
		if(map!= null && !StringHelper.noEmpty2(map.get("title")).trim().equals(""))
		{
			param += " and a.title like concat('%',?,'%') ";
			conditionP += StringHelper.noEmpty2(map.get("title")).trim() + ",";
		}
		if(map!= null && !StringHelper.noEmpty2(map.get("code")).trim().equals(""))
		{
			param += " and a.code like concat('%',?,'%') ";
			conditionP += StringHelper.noEmpty2(map.get("code")).trim() + ",";
		}
		if(map!= null && !StringHelper.noEmpty2(map.get("approval")).trim().equals(""))
		{
			param += " and a.approval like concat('%',?,'%') ";
			conditionP += StringHelper.noEmpty2(map.get("approval")).trim() + ",";
		}
		String sql = "select a.* from kh_preliminarydesign a  "
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
	 * 添加初步设计
	 * @param map
	 * @param uid
	 * @return
	 */
	public boolean addProject_cbsj(Map<String, Object> map,String uid)
	{
		StringBuilder sb=new StringBuilder();
		sb.append("insert into kh_preliminarydesign(title,code,unit,accept,approval,uid,filename,filepath,createtime) values(?,?,?,?,?,?,?,?,?) ");
		Object[] object = new Object[9];
		object[0] = StringHelper.noEmpty2(map.get("title")).trim();
		object[1] = StringHelper.noEmpty2(map.get("code")).trim();
		object[2] = StringHelper.noEmpty2(map.get("unit")).trim();
		object[3] = StringHelper.noEmpty2(map.get("accept")).trim();
		object[4] = StringHelper.noEmpty2(map.get("approval")).trim();
		object[5] = StringHelper.noEmpty2(uid).trim();
		object[6] = StringHelper.noEmpty2(map.get("filename")).trim();
		object[7] = StringHelper.noEmpty2(map.get("filepath")).trim();
		object[8] = StringHelper.noEmpty2(map.get("createtime")).trim();
		return this.executeUpdate(sb.toString(), object);
		
	}

	/**
	 * 更新初步设计与估算
	 * @param map
	 * @return
	 */
	public boolean updateProject_cbsj(Map<String, Object> map,String uid)
	{

		StringBuilder sb=new StringBuilder();
		sb.append("update kh_preliminarydesign set title=?,code=?,unit=?,accept=?,approval=?,uid=?,filename=?,filepath=? where id =? ");
		Object[] object = new Object[9];
		object[0] = StringHelper.noEmpty2(map.get("title")).trim();
		object[1] = StringHelper.noEmpty2(map.get("code")).trim();
		object[2] = StringHelper.noEmpty2(map.get("unit")).trim();
		object[3] = StringHelper.noEmpty2(map.get("accept")).trim();
		object[4] = StringHelper.noEmpty2(map.get("approval")).trim();
		object[5] = StringHelper.noEmpty2(uid).trim();
		object[6] = StringHelper.noEmpty2(map.get("filename")).trim();
		object[7] = StringHelper.noEmpty2(map.get("filepath")).trim();
		object[8] = StringHelper.noEmpty2(map.get("id")).trim();
		return this.executeUpdate(sb.toString(), object);
		
	}
	/**
	 * 删除初步设计与估算
	 * @param id
	 * @return
	 */
	public boolean deleteProject_cbsj(String id)
	{	
		StringBuilder sb=new StringBuilder();
		sb.append("delete from kh_preliminarydesign where id =?");
		return this.executeUpdate(sb.toString(), id);
	}
	
	
}
