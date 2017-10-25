package com.zqkj.bsm.tzgl.dao;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import com.zqkj.bsm.client.dao.ClientDao;
import com.zqkj.bsm.dal.dboper.DbOperator;
import com.zqkj.bsm.system.action.page.OraPaginatedList;
import com.zqkj.bsm.system.action.util.StringHelper;

public class FundManageDao extends DbOperator{
	
	private static FundManageDao instance = null;
	public static FundManageDao getInstance()
	{
		if(instance==null)
		{
			synchronized (ClientDao.class)
			{
			  instance = new FundManageDao();
			}
		}
		return instance;
	}
	
	/**
	 * 数据库字段和导出标题栏的对应关系
	 *
	 */
	public  Map<String, String> getList_Column()
	{
		StringBuilder sb=new StringBuilder();
		sb.append("SELECT ");
		sb.append(" projectname as '项目名称',");
		sb.append(" bulidscale as '建设规模',");
		sb.append(" totalinvest as '总投资',");
		sb.append(" firstquarter as '第一季度',");
		sb.append(" secondquarter as '第二季度',");
		sb.append(" thirdquarter as '第三季度',");
		sb.append(" fourthquarter as '第四季度'");
		sb.append(" FROM tzgl_fundmanage");
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
			if(map!= null && !StringHelper.noEmpty2(map.get("projectname")).trim().equals(""))
			{
				param += " and a.projectname like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("projectname")).trim()) + ",";
			}
			
			String sql = "select a.* from tzgl_fundmanage a  "
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
	 * 按条件查询获下达资金管理
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public OraPaginatedList queryFundManage(Map<String, Object> map,int pageNum,int pageSize) 
	{
		String condition=this.getDesSql(map);
		String conditionP = "";
		String param = "";
		if(map!= null && !StringHelper.noEmpty2(map.get("projectname")).trim().equals(""))
		{
			param += " and a.projectname like concat('%',?,'%') ";
			conditionP += StringHelper.noEmpty2(map.get("projectname")).trim() + ",";
		}
		
		String sql = "select a.* from tzgl_fundmanage a  "
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
	 * 保存下达资金管理
	 * @param map
	 * @param uid
	 * @return
	 * 
	 */
	public boolean addFundManage(Map<String, Object> map)
	{
		StringBuilder sb=new StringBuilder();
		sb.append("insert into tzgl_fundmanage(projectname,bulidscale,totalinvest,firstquarter,secondquarter,thirdquarter,fourthquarter) values(?,?,?,?,?,?,?)");
		Object[] object = new Object[7];
		object[0] = StringHelper.noEmpty2(map.get("projectname")).trim();
		object[1] = StringHelper.noEmpty2(map.get("bulidscale")).trim();
		object[2] = StringHelper.noEmpty2(map.get("totalinvest")).trim();
		object[3] = StringHelper.noEmpty2(map.get("firstquarter")).trim();
		object[4] = StringHelper.noEmpty2(map.get("secondquarter")).trim();
		object[5] = StringHelper.noEmpty2(map.get("thirdquarter")).trim();
		object[6] = StringHelper.noEmpty2(map.get("fourthquarter")).trim();
		return this.executeUpdate(sb.toString(), object);
		
		
	}

	/**
	 * 更新下达资金管理
	 * @param map
	 * @return
	 *  projectname as '项目名称',");
		sb.append(" bulidscale as '建设规模',");
		sb.append(" totalinvest as '总投资',");
		sb.append(" firstquarter as '第一季度',");
		sb.append(" secondquarter as '第二季度',");
		sb.append(" thirdquarter as
	 */
	public boolean updateFundManage(Map<String, Object> map)
	{

		StringBuilder sb=new StringBuilder();
		sb.append("update tzgl_fundmanage set projectname=?,bulidscale=?,totalinvest=?,firstquarter=?,secondquarter=?,thirdquarter=?,fourthquarter=? where id =? ");
		Object[] object = new Object[8];
		object[0] = StringHelper.noEmpty2(map.get("projectname")).trim();
		object[1] = StringHelper.noEmpty2(map.get("bulidscale")).trim();
		object[2] = StringHelper.noEmpty2(map.get("totalinvest")).trim();
		object[3] = StringHelper.noEmpty2(map.get("firstquarter")).trim();
		object[4] = StringHelper.noEmpty2(map.get("secondquarter")).trim();
		object[5] = StringHelper.noEmpty2(map.get("thirdquarter")).trim();
		object[6] = StringHelper.noEmpty2(map.get("fourthquarter")).trim();
        object[7] = StringHelper.noEmpty2(map.get("id")).trim();
		return this.executeUpdate(sb.toString(), object);
		
	}
	/**
	 * 删除下达资金管理
	 * @param id
	 * @return
	 */
	public boolean deleteFundManage(String id)
	{	
		StringBuilder sb=new StringBuilder();
		sb.append("delete from tzgl_fundmanage where id =? ");
		return this.executeUpdate(sb.toString(), id);
	}
	
	
}
