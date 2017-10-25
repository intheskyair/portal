package com.zqkj.bsm.slProject.dao;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import com.zqkj.bsm.dal.dboper.DbOperator;
import com.zqkj.bsm.system.action.page.OraPaginatedList;
import com.zqkj.bsm.system.action.util.StringHelper;

public class PlanningDao extends DbOperator
{

	private static PlanningDao instance = null;
	public static PlanningDao getInstance()
	{
		if(instance==null)
		{
			synchronized (PlanningDao.class)
			{
			  instance = new PlanningDao();
			}
		}
		return instance;
	}
	
	
	
	/**
	 * 保存 水利工程 规划名录表
	 * @param map
	 * @param operator
	 * @return
	 */
	public boolean saveproject_ghml(Map<String, Object> map,String operator)
	{
		StringBuilder sb=new StringBuilder();
		sb.append("insert into project_ghml(project_name,zs_num,bz_company,yd_address,yd_area,fz_time,operator,file_path,promotionShow) values(?,?,?,?,?,?,?,?,?) ");
		Object[] object = new Object[9];
		object[0] = StringHelper.noEmpty2(map.get("project_name")).trim();
		object[1] = StringHelper.noEmpty2(map.get("zs_num")).trim();
		object[2] = StringHelper.noEmpty2(map.get("bz_company")).trim();
		object[3] = StringHelper.noEmpty2(map.get("yd_address")).trim();
		object[4] = StringHelper.noEmpty2(map.get("yd_area")).trim();
		object[5] = StringHelper.noEmpty2(map.get("fz_time")).trim();
		object[6] = StringHelper.noEmpty2(operator).trim();
		object[7] = StringHelper.noEmpty2(map.get("file_path")).trim();
		object[8] = StringHelper.noEmpty2(map.get("promotionShow")).trim();
		return this.executeUpdate(sb.toString(), object);
	}
	
	
	
	public boolean saveproject_ghxm(Map<String, Object> map,String operator)
	{
		StringBuilder sb=new StringBuilder();
		sb.append("insert into project_ghxm(project_name,szfw,jsdw,cssj,kg,xzyjs,zpmfa,ydz,gcz,phgl,jghs,zrbm,operator) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		Object[] object = new Object[13];
		int i=0;
		object[i] = StringHelper.noEmpty2(map.get("project_name".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("szfw".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("jsdw".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("cssj".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("kg".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("xzyjs".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zpmfa".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("ydz".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("gcz".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("phgl".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("jghs".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zrbm".toUpperCase())).trim();i++;
		object[i] = operator;i++;
		return this.executeUpdate(sb.toString(), object);
	}
	
	/**
	 * 保存规划文件到数据库
	 * @param map
	 * @param operator
	 * @return
	 */
	public boolean saveproject_ghwj(Map<String, Object> map,String operator)
	{
		StringBuilder sb=new StringBuilder();
		sb.append("insert into project_ghwj(file_type,file_title,create_time,file_path,file_name,operator) "
				+ "values(?,?,now(),?,?,?) ");
		Object[] object = new Object[5];
		int i=0;
		object[i] = StringHelper.noEmpty2(map.get("file_type".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("file_title".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("file_path".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("file_name".toUpperCase())).trim();i++;
		object[i] = operator;i++;
		return this.executeUpdate(sb.toString(), object);
	}
	
	
	public boolean saveproject_ghjh(Map<String, Object> map,String operator)
	{
		StringBuilder sb=new StringBuilder();
		sb.append("insert into project_ghjh(file_type,file_title,create_time,file_path,file_name,operator) "
				+ "values(?,?,now(),?,?,?) ");
		Object[] object = new Object[5];
		int i=0;
		object[i] = StringHelper.noEmpty2(map.get("file_type".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("file_title".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("file_path".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("file_name".toUpperCase())).trim();i++;
		object[i] = operator;i++;
		return this.executeUpdate(sb.toString(), object);
	}
	
	
	public boolean updateproject_ghwj(Map<String, Object> map)
	{

		StringBuilder sb=new StringBuilder();
		sb.append("update project_ghwj set file_type=?,file_title=?,update_time=now(),file_path=?,file_name=? where id =? ");
		Object[] object = new Object[5];
		int i=0;
		object[i] = StringHelper.noEmpty2(map.get("file_type".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("file_title".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("file_path".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("file_name".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("id".toUpperCase())).trim();i++;
		return this.executeUpdate(sb.toString(), object);
	}
	
	public boolean updateproject_ghjh(Map<String, Object> map)
	{

		StringBuilder sb=new StringBuilder();
		sb.append("update project_ghjh set file_type=?,file_title=?,update_time=now(),file_path=?,file_name=? where id =? ");
		Object[] object = new Object[5];
		int i=0;
		object[i] = StringHelper.noEmpty2(map.get("file_type".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("file_title".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("file_path".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("file_name".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("id".toUpperCase())).trim();i++;
		return this.executeUpdate(sb.toString(), object);
	}
	
	
	public boolean updateproject_ghxm(Map<String, Object> map)
	{

		StringBuilder sb=new StringBuilder();
		sb.append("update project_ghxm set project_name=?,szfw=?,jsdw=?,cssj=?,kg=?,xzyjs=?,zpmfa=?,ydz=?,gcz=?,phgl=?,jghs=?,zrbm=? where id =? ");
		Object[] object = new Object[13];
		int i=0;
		object[i] = StringHelper.noEmpty2(map.get("project_name".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("szfw".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("jsdw".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("cssj".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("kg".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("xzyjs".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zpmfa".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("ydz".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("gcz".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("phgl".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("jghs".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zrbm".toUpperCase())).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("id".toUpperCase())).trim();i++;
		return this.executeUpdate(sb.toString(), object);
	}
	
	
	
	public boolean updateproject_ghml(Map<String, Object> map)
	{

		StringBuilder sb=new StringBuilder();
		sb.append("update project_ghml set project_name=?,zs_num=?,bz_company=?,yd_address=?,yd_area=?,fz_time=now(),file_path=?,promotionShow=?,fz_time=?  where id =? ");
		Object[] object = new Object[9];
		object[0] = StringHelper.noEmpty2(map.get("project_name")).trim();
		object[1] = StringHelper.noEmpty2(map.get("zs_num")).trim();
		object[2] = StringHelper.noEmpty2(map.get("bz_company")).trim();
		object[3] = StringHelper.noEmpty2(map.get("yd_address")).trim();
		object[4] = StringHelper.noEmpty2(map.get("yd_area")).trim();
		object[5] = StringHelper.noEmpty2(map.get("file_path")).trim();
		object[6] = StringHelper.noEmpty2(map.get("promotionShow")).trim();
		object[7] = StringHelper.noEmpty2(map.get("fz_time")).trim();
		object[8] = StringHelper.noEmpty2(map.get("id")).trim();
		return this.executeUpdate(sb.toString(), object);
	}
	
	/**
	 * 按条件查询规划名录
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public OraPaginatedList queryproject_ghmlc(Map<String, Object> map,int pageNum,int pageSize) 
	{
		String condition=this.getDesSql(map);
		String conditionP = "";
		String param = "";
		if(map!= null && !StringHelper.noEmpty2(map.get("project_name")).trim().equals(""))
		{
			param += " and a.project_name like concat('%',?,'%') ";
			conditionP += StringHelper.noEmpty2(map.get("project_name")).trim() + ",";
		}
		if(map!= null && !StringHelper.noEmpty2(map.get("zs_num")).trim().equals(""))
		{
			param += " and a.zs_num like concat('%',?,'%') ";
			conditionP += StringHelper.noEmpty2(map.get("zs_num")).trim() + ",";
		}
		if(map!= null && !StringHelper.noEmpty2(map.get("bz_company")).trim().equals(""))
		{
			param += " and a.bz_company like concat('%',?,'%') ";
			conditionP += StringHelper.noEmpty2(map.get("bz_company")).trim() + ",";
		}
		String sql = "select a.* from project_ghml a  "
                +" where 1=1  "+param;
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
	 * 按条件查询规划项目
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public OraPaginatedList queryproject_ghxm(Map<String, Object> map,int pageNum,int pageSize) 
	{
		String condition=this.getDesSql(map);
		String conditionP = "";
		String param = "";
		if(map!= null && !StringHelper.noEmpty2(map.get("project_name")).trim().equals(""))
		{
			param += " and a.project_name like concat('%',?,'%') ";
			conditionP += StringHelper.noEmpty2(map.get("project_name")).trim() + ",";
		}
		if(map!= null && !StringHelper.noEmpty2(map.get("jsdw")).trim().equals(""))
		{
			param += " and a.jsdw like concat('%',?,'%') ";
			conditionP += StringHelper.noEmpty2(map.get("jsdw")).trim() + ",";
		}
		String sql = "select a.* from project_ghxm a  "
                +" where 1=1  "+param;
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
	 * 分页查询，规划文件
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public OraPaginatedList queryproject_ghwj(Map<String, Object> map,int pageNum,int pageSize) 
	{
		String condition=this.getDesSql(map);
		String conditionP = "";
		String param = "";
		if(map!= null && !StringHelper.noEmpty2(map.get("file_type")).trim().equals(""))
		{
			param += " and a.file_type=? ";
			conditionP += StringHelper.noEmpty2(map.get("file_type")).trim() + ",";
		}
		if(map!= null && !StringHelper.noEmpty2(map.get("file_title")).trim().equals(""))
		{
			param += " and a.file_title like concat('%',?,'%') ";
			conditionP += StringHelper.noEmpty2(map.get("file_title")).trim() + ",";
		}
		if(map!= null && !StringHelper.noEmpty2(map.get("create_time")).trim().equals(""))
		{
			param += " and date_format(a.create_Time, '%Y-%m-%d') = ?";
			conditionP += StringHelper.noEmpty2(map.get("create_time")).trim() + ",";
		}		
		String sql = "select a.* from project_ghwj a  "
                +" where 1=1  "+param;
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
	 * 分页查询，规划计划
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public OraPaginatedList queryproject_ghjh(Map<String, Object> map,int pageNum,int pageSize) 
	{
		String condition=this.getDesSql(map);
		String conditionP = "";
		String param = "";
		if(map!= null && !StringHelper.noEmpty2(map.get("file_type")).trim().equals(""))
		{
			param += " and a.file_type=? ";
			conditionP += StringHelper.noEmpty2(map.get("file_type")).trim() + ",";
		}
		if(map!= null && !StringHelper.noEmpty2(map.get("file_title")).trim().equals(""))
		{
			param += " and a.file_title like concat('%',?,'%') ";
			conditionP += StringHelper.noEmpty2(map.get("file_title")).trim() + ",";
		}
		if(map!= null && !StringHelper.noEmpty2(map.get("create_time")).trim().equals(""))
		{
			param += " and date_format(a.create_Time, '%Y-%m-%d') = ?";
			conditionP += StringHelper.noEmpty2(map.get("create_time")).trim() + ",";
		}		
		String sql = "select a.* from project_ghjh a  "
                +" where 1=1  "+param;
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
			if(map!= null && !StringHelper.noEmpty2(map.get("project_name")).trim().equals(""))
			{
				param += " and a.project_name like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("project_name")).trim(),"utf-8") + ",";
			}
			if(map!= null && !StringHelper.noEmpty2(map.get("zs_num")).trim().equals(""))
			{
				param += " and a.zs_num like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("zs_num")).trim(),"utf-8") + ",";
			}
			if(map!= null && !StringHelper.noEmpty2(map.get("create_time")).trim().equals(""))
			{
				param += " and date_format(a.create_Time, '%Y-%m-%d') = ?";
				conditionP += StringHelper.noEmpty2(map.get("create_time")).trim() + ",";
			}		
			String sql = "select a.* from project_ghml a  "
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
	  *导出规划项目列表
	  * @param map
	  * @return
	 * @throws UnsupportedEncodingException 
	  */
	 public List<Map<String, Object>> getExportDara_project(Map<String, Object> map) throws UnsupportedEncodingException 
	 {
			String condition=this.getDesSql(map);
			String conditionP = "";
			String param = "";
			if(map!= null && !StringHelper.noEmpty2(map.get("project_name")).trim().equals(""))
			{
				param += " and a.project_name like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("project_name")).trim(),"utf-8") + ",";
			}
			if(map!= null && !StringHelper.noEmpty2(map.get("jsdw")).trim().equals(""))
			{
				param += " and a.jsdw like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("jsdw")).trim(),"utf-8") + ",";
			}
			String sql = "select a.* from project_ghxm a  "
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
	  * 导出规划文件列表
	  * @param map
	  * @return
	 * @throws UnsupportedEncodingException 
	  */
	 public List<Map<String, Object>> getExportDara_file(Map<String, Object> map) throws UnsupportedEncodingException 
	 {
			String condition=this.getDesSql(map);
			String conditionP = "";
			String param = "";
			if(map!= null && !StringHelper.noEmpty2(map.get("file_type")).trim().equals(""))
			{
				param += " and a.file_type like concat('%',?,'%') ";
				conditionP +=   URLDecoder.decode(StringHelper.noEmpty2(map.get("file_type")).trim(),"utf-8") + ",";
			}
			if(map!= null && !StringHelper.noEmpty2(map.get("file_title")).trim().equals(""))
			{
				param += " and a.file_title like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("file_title")).trim(),"utf-8") + ",";
			}
			String sql = "select a.* from project_ghwj a  "
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
	  *导出规划计划列表
	  * @param map
	  * @return
	 * @throws UnsupportedEncodingException 
	  */
	 public List<Map<String, Object>> getExportDara_plan(Map<String, Object> map) throws UnsupportedEncodingException 
	 {
			String condition=this.getDesSql(map);
			String conditionP = "";
			String param = "";
			if(map!= null && !StringHelper.noEmpty2(map.get("file_type")).trim().equals(""))
			{
				param += " and a.file_type like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("file_type")).trim(),"utf-8") + ",";
			}
			if(map!= null && !StringHelper.noEmpty2(map.get("file_title")).trim().equals(""))
			{
				param += " and a.file_title like concat('%',?,'%') ";
				conditionP +=  URLDecoder.decode(StringHelper.noEmpty2(map.get("file_title")).trim(),"utf-8") + ",";
			}
			if(map!= null && !StringHelper.noEmpty2(map.get("create_time")).trim().equals(""))
			{
				param += " and date_format(a.create_Time, '%Y-%m-%d') = ?";
				conditionP += StringHelper.noEmpty2(map.get("create_time")).trim() + ",";
			}		
			String sql = "select a.* from project_ghjh a  "
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
	 * 删除规划名录，暂时先不删除对应的文件
	 * @param id
	 * @return
	 */
	public boolean delproject_ghml(String id)
	{	
		StringBuilder sb=new StringBuilder();
		sb.append("delete from project_ghml where id =? ");
		return this.executeUpdate(sb.toString(), id);
	}
	
	public boolean delproject_ghxm(String id)
	{	
		StringBuilder sb=new StringBuilder();
		sb.append("delete from project_ghxm where id =? ");
		return this.executeUpdate(sb.toString(), id);
	}
	
	public boolean delproject_ghwj(String id)
	{	
		StringBuilder sb=new StringBuilder();
		sb.append("delete from project_ghwj where id =? ");
		return this.executeUpdate(sb.toString(), id);
	}
	
	public boolean delproject_ghjh(String id)
	{	
		StringBuilder sb=new StringBuilder();
		sb.append("delete from project_ghjh where id =? ");
		return this.executeUpdate(sb.toString(), id);
	}
	/**
	 * 数据库字段和导出标题栏的对应关系
	 */
	public  Map<String, String> getList_Column()
	{
		StringBuilder sb=new StringBuilder();
		sb.append("SELECT ");
		sb.append(" project_name as '项目名称',");
		sb.append(" zs_num as '证书号',");
		sb.append(" bz_company as '办证单位',");
		sb.append(" yd_address as '用地单位',");
		sb.append(" yd_area as '用地面积',");
		sb.append(" fz_time as '发证日期',");
		sb.append(" promotionShow as '文件名'");
		sb.append(" FROM project_ghml");
		return this.getList_Column(sb.toString());
	}
	
	/**
	 * 获取规划项目列表
	 * @return
	 */
	public  Map<String, String> getList_Column_Project()
	{
		StringBuilder sb=new StringBuilder();
		sb.append("SELECT ");
		sb.append(" project_name as '项目名称',");
		sb.append(" szfw as '施置范围',");
		sb.append(" jsdw as '建设单位',");
		sb.append(" cssj as '概规或城市设计',");
		sb.append(" kg as '控规',");
		sb.append(" xzyjs as '选址意见书',");
		sb.append(" zpmfa as '总平面方案',");
		sb.append(" ydz as '用地证',");
		sb.append(" gcz as '工程证',");
		sb.append(" phgl as '批后管理',");
		sb.append(" jghs as '竣工核实',");
		sb.append(" zrbm as '责任部门' ");
		sb.append(" FROM project_ghxm");
		return this.getList_Column(sb.toString());
	}
	

	/**
	 * 获取规划文件列表列名
	 * @return
	 */
	public  Map<String, String> getList_Column_File()
	{
		StringBuilder sb=new StringBuilder();
		sb.append("SELECT ");
		sb.append(" file_type as '文件类型',");
		sb.append(" file_title as '文件标题',");
		sb.append(" create_time as'创建时间',");
		sb.append(" update_time as'更新时间',");
		sb.append(" file_path as '文件路径',");
		sb.append(" file_name as '文件名'");
		sb.append(" FROM project_ghwj");
		return this.getList_Column(sb.toString());
	}
	
	/**
	 * 获取规划计划列表列名
	 * @return
	 */
	public  Map<String, String> getList_Column_plan()
	{
		StringBuilder sb=new StringBuilder();
		sb.append("SELECT ");
		sb.append(" file_type as '文件类型',");
		sb.append(" file_title as '文件标题',");
		sb.append(" create_time as'创建时间',");
		sb.append(" update_time as'更新时间',");
		sb.append(" file_path as '文件路径',");
		sb.append(" file_name as '文件名'");
	
		sb.append(" FROM project_ghjh");
		return this.getList_Column(sb.toString());
	}
	
	
}
