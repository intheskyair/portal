package com.zqkj.bsm.slProject.dao;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import com.zqkj.bsm.dal.dboper.DbOperator;
import com.zqkj.bsm.system.action.page.OraPaginatedList;
import com.zqkj.bsm.system.action.util.StringHelper;

public class ZtbDao extends DbOperator
{
	private static ZtbDao instance = null;
	public static ZtbDao getInstance()
	{
		if(instance==null)
		{
			synchronized (ZtbDao.class)
			{
			  instance = new ZtbDao();
			}
		}
		return instance;
	}
	/**
	 * 数据库字段和导出标题栏的对应关系
	 *
	 */
	public  Map<String, String> getList_Column_zbyqh()
	{
		StringBuilder sb=new StringBuilder();
		sb.append("SELECT ");
		sb.append(" project_name as '项目名称',");
		sb.append(" project_bianhao as '项目编号',");
		sb.append(" remark as '备注',");
		sb.append(" zb_num as '文件数量',");
		sb.append(" FROM project_zbyqh");
		return this.getList_Column(sb.toString());
	}
	
	/**
	 * 数据导出不分页，但是超过一定量会有性能问题
	 * @param map
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	 @SuppressWarnings("deprecation")
	public List<Map<String, Object>> getExportDara_zbyqh(Map<String, Object> map) throws UnsupportedEncodingException 
	 {
			String condition=this.getDesSql(map);
			String conditionP = "";
			String param = "";
			
			if(map!= null && !StringHelper.noEmpty2(map.get("project_name")).trim().equals(""))
			{
				param += " and a.project_name like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("project_name")).trim()) + ",";
			}
			if(map!= null && !StringHelper.noEmpty2(map.get("project_bianhao")).trim().equals(""))
			{
				param += " and a.project_bianhao like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("project_bianhao")).trim()) + ",";
			}
			String sql = "select a.* from project_zbyqh a  "
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
	 * 数据库字段和导出标题栏的对应关系
	 *
	 */
	public  Map<String, String> getList_Column_zbgg()
	{
		StringBuilder sb=new StringBuilder();
		sb.append("SELECT ");
		sb.append(" zb_title as '招标标题',");
		sb.append(" zb_project as '招标项目',");
		sb.append(" zb_pizhun as '项目批准人',");
		sb.append(" zb_yezhu as '项目业主',");
		sb.append(" zb_zjly as '建设资金来源',");
		sb.append(" zb_ren as '招标人',");
		sb.append(" zb_dljg as '招标代理机构',");
		sb.append(" zb_bianhao as '招标编号',");
		sb.append(" zb_jsdd as '建设地点',");
		sb.append(" zb_gcztz as '工程总投资（万元）',");
		sb.append(" zb_zgkj as '招标最高控价',");
		sb.append(" zb_bkjjje as '不可竞争金额',");
		sb.append(" zb_fanwei as '招标范围',");
		sb.append(" zb_begtime as '招标开始时间',");
		sb.append(" zb_endtime as '招标结束时间'");
		sb.append(" FROM project_zbgg");
		return this.getList_Column(sb.toString());
	}
	
	/**
	 * 数据导出不分页，但是超过一定量会有性能问题
	 * @param map
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	 @SuppressWarnings("deprecation")
	public List<Map<String, Object>> getExportDara_zbgg(Map<String, Object> map) throws UnsupportedEncodingException 
	 {
			String condition=this.getDesSql(map);
			String conditionP = "";
			String param = "";
			
			if(map!= null && !StringHelper.noEmpty2(map.get("zb_title")).trim().equals(""))
			{
				param += " and a.zb_title like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("zb_title")).trim()) + ",";
			}
			if(map!= null && !StringHelper.noEmpty2(map.get("zb_bianhao")).trim().equals(""))
			{
				param += " and a.zb_bianhao like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("zb_bianhao")).trim()) + ",";
			}
			String sql = "select a.* from project_zbgg a  "
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
		 * 数据库字段和导出标题栏的对应关系
		 *
		 */
		public  Map<String, String> getList_Column_zbdlr()
		{
			StringBuilder sb=new StringBuilder();
			sb.append("SELECT ");
			sb.append(" company_name as '企业名称',");
			sb.append(" company_yyzzh as '法人营业执照注册号',");
			sb.append(" company_phone as '企业联系电话',");
			sb.append(" company_nj_time as '企业年检最新日期',");
			sb.append(" company_gczbdlzzdj as '工程招标代理资质等级(甲乙)',");
			sb.append(" company_gczbdlzzdj_bh as '工程招标代理资质等级的证书编号',");
			sb.append(" company_gczbdlzzdj_time as '招标代理资质证书的有效期',");
			sb.append(" company_gczjzxzzdj as '工程造价咨询资质等级',");
			sb.append(" company_gczjzxzzdj_bh as '造价咨询资质证书的编号',");
			sb.append(" company_gczjzxzzdj_time as '造价咨询资质证书有效期',");
			sb.append(" company_address as '企业所在地址',");
			sb.append(" company_frdb as '企业法人代表',");
			sb.append(" company_frdb_phone as '法人代表的电话'");
			sb.append(" FROM project_zbdlrjbxx");
			return this.getList_Column(sb.toString());
		}
		
		/**
		 * 数据导出不分页，但是超过一定量会有性能问题
		 * @param map
		 * @return
		 * @throws UnsupportedEncodingException 
		 */
		 @SuppressWarnings("deprecation")
		public List<Map<String, Object>> getExportDara_zbdlr(Map<String, Object> map) throws UnsupportedEncodingException 
		 {
				String condition=this.getDesSql(map);
				String conditionP = "";
				String param = "";
				
				if(map!= null && !StringHelper.noEmpty2(map.get("company_name")).trim().equals(""))
				{
					param += " and a.company_name like concat('%',?,'%') ";
					conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("company_name")).trim()) + ",";
				}
				if(map!= null && !StringHelper.noEmpty2(map.get("company_yyzzh")).trim().equals(""))
				{
					param += " and a.company_yyzzh like concat('%',?,'%') ";
					conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("company_yyzzh")).trim()) + ",";
				}
				String sql = "select a.* from project_zbdlrjbxx a  "
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
	 * 按条件查询招标代理人基本信息
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public OraPaginatedList queryproject_zbdlrjbxx(Map<String, Object> map,int pageNum,int pageSize) 
	{
		String condition=this.getDesSql(map);
		String conditionP = "";
		String param = "";
		if(map!= null && !StringHelper.noEmpty2(map.get("company_name")).trim().equals(""))
		{
			param += " and a.company_name like concat('%',?,'%') ";
			conditionP += StringHelper.noEmpty2(map.get("company_name")).trim() + ",";
		}
		if(map!= null && !StringHelper.noEmpty2(map.get("company_yyzzh")).trim().equals(""))
		{
			param += " and a.company_yyzzh like concat('%',?,'%') ";
			conditionP += StringHelper.noEmpty2(map.get("company_yyzzh")).trim() + ",";
		}
		String sql = "select a.* from project_zbdlrjbxx a  "
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
	 *    `company_name` varchar(200) DEFAULT NULL COMMENT '企业名称',
		  `company_yyzzh` varchar(100) DEFAULT NULL COMMENT '法人营业执照注册号',
		  `company_yyzzh_path` varchar(200) DEFAULT NULL COMMENT '营业执照号的保存全路径',
		  `company_phone` varchar(50) DEFAULT NULL COMMENT '企业联系电话',
		  `company_nj_time` timestamp NULL DEFAULT NULL COMMENT '企业年检最新日期',
		  `company_gczbdlzzdj` varchar(50) DEFAULT NULL COMMENT '工程招标代理资质等级(甲乙）',
		  `company_gczbdlzzdj_path` varchar(200) DEFAULT NULL COMMENT '等级证书的保存全路径',
		  `company_gczbdlzzdj_bh` varchar(100) DEFAULT NULL COMMENT '工程招标代理资质等级的证书编号',
		  `company_gczbdlzzdj_time` timestamp NULL DEFAULT NULL COMMENT '该证书的有效期',
		  `company_gczbdlzzdj_ywfw` varchar(1000) DEFAULT NULL COMMENT '该证书可以承接的业务范围',
		  `company_gczjzxzzdj` varchar(50) DEFAULT NULL COMMENT '工程造价咨询资质等级',
		  `company_gczjzxzzdj_path` varchar(200) DEFAULT NULL COMMENT '该证书的保存全路径',
		  `company_gczjzxzzdj_bh` varchar(100) DEFAULT NULL COMMENT '该证书的编号',
		  `company_gczjzxzzdj_time` timestamp NULL DEFAULT NULL COMMENT '该证书的有效期至',
		  `company_gczjzxzzdj_ywfw` varchar(1000) DEFAULT NULL COMMENT '该证书可承接的业务范围',
		  `company_address` varchar(500) DEFAULT NULL COMMENT '企业所在地址',
		  `company_frdb` varchar(100) DEFAULT NULL COMMENT '企业法人代表',
		  `company_frdb_zw` varchar(100) DEFAULT NULL COMMENT '法人代表的职务',
		  `company_frdb_zc` varchar(100) DEFAULT NULL COMMENT '法人代表的职称',
		  `company_frdb_phone` varchar(50) DEFAULT NULL COMMENT '法人代表的电话',
	 * @param map
	 * @param operator
	 * @return
	 */
	public boolean saveproject_zbdlrjbxx(Map<String, Object> map,String operator)
	{
		StringBuilder sb=new StringBuilder();
		sb.append("insert into project_zbdlrjbxx(company_name,company_yyzzh,company_yyzzh_path,company_phone,company_nj_time,company_gczbdlzzdj,company_gczbdlzzdj_path,");
		sb.append("company_gczbdlzzdj_bh,company_gczbdlzzdj_time,company_gczbdlzzdj_ywfw,company_gczjzxzzdj,company_gczjzxzzdj_path,company_gczjzxzzdj_bh,company_gczjzxzzdj_time,");
		sb.append("company_gczjzxzzdj_ywfw,company_address,company_frdb,company_frdb_zw,company_frdb_zc,company_frdb_phone,operator)");
		sb.append("values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		Object[] object = new Object[21];
		int i=0;
		object[i] = StringHelper.noEmpty2(map.get("company_name")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_yyzzh")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_yyzzh_path")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_phone")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_nj_time")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_gczbdlzzdj")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_gczbdlzzdj_path")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_gczbdlzzdj_bh")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_gczbdlzzdj_time")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_gczbdlzzdj_ywfw")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_gczjzxzzdj")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_gczjzxzzdj_path")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_gczjzxzzdj_bh")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_gczjzxzzdj_time")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_gczjzxzzdj_ywfw")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_address")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_frdb")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_frdb_zw")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_frdb_zc")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_frdb_phone")).trim();i++;
		object[i] = operator;i++;
		return this.executeUpdate(sb.toString(), object);
	}
	
	
	public boolean updateproject_zbdlrjbxx(Map<String, Object> map)
	{

		StringBuilder sb=new StringBuilder();
		sb.append(" update project_zbdlrjbxx set company_name=?,company_yyzzh=?,company_yyzzh_path=?,company_phone=?,company_nj_time=?,");
		sb.append(" company_gczbdlzzdj=?,company_gczbdlzzdj_path=?,company_gczbdlzzdj_bh=?,company_gczbdlzzdj_time=?,company_gczbdlzzdj_ywfw=?,");
		sb.append(" company_gczjzxzzdj=?,company_gczjzxzzdj_path=?,company_gczjzxzzdj_bh=?,company_gczjzxzzdj_time=?,company_gczjzxzzdj_ywfw=?,");
		sb.append(" company_address=?,company_frdb=?,company_frdb_zw=?,company_frdb_zc=?,company_frdb_phone=?");
		sb.append(" where id =? ");
		Object[] object = new Object[21];
		int i=0;
		object[i] = StringHelper.noEmpty2(map.get("company_name")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_yyzzh")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_yyzzh_path")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_phone")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_nj_time")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_gczbdlzzdj")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_gczbdlzzdj_path")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_gczbdlzzdj_bh")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_gczbdlzzdj_time")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_gczbdlzzdj_ywfw")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_gczjzxzzdj")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_gczjzxzzdj_path")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_gczjzxzzdj_bh")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_gczjzxzzdj_time")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_gczjzxzzdj_ywfw")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_address")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_frdb")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_frdb_zw")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_frdb_zc")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("company_frdb_phone")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("id")).trim();i++;
		return this.executeUpdate(sb.toString(), object);
	}
	
	/**
	 * 查询一个指定id的招标代理人基本信息
	 * @param id
	 * @return
	 */
	public Map<String, Object> queryzbdlrjbxx(String id) 
	{
		String sql = "select a.* from project_zbdlrjbxx a  "
                +" where id=?  ";
        return this.getHashMap_Pre(sql, id);
	}
	
	
	public Map<String, Object> queryzbgg(String id) 
	{
		String sql = "select a.* from project_zbgg a  "
                +" where id=?  ";
        return this.getHashMap_Pre(sql, id);
	}
	
	public boolean delzbdlrjbxx(String id)
	{	
		StringBuilder sb=new StringBuilder();
		sb.append("delete from project_zbdlrjbxx where id =? ");
		return this.executeUpdate(sb.toString(), id);
	}
	
	
	/**
	 * 查询项目招标公告信息
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public OraPaginatedList queryproject_zbgg(Map<String, Object> map,int pageNum,int pageSize) 
	{
		String condition=this.getDesSql(map);
		String conditionP = "";
		String param = "";
		if(map!= null && !StringHelper.noEmpty2(map.get("zb_title")).trim().equals(""))
		{
			param += " and a.zb_title like concat('%',?,'%') ";
			conditionP += StringHelper.noEmpty2(map.get("zb_title")).trim() + ",";
		}
		if(map!= null && !StringHelper.noEmpty2(map.get("zb_bianhao")).trim().equals(""))
		{
			param += " and a.zb_bianhao like concat('%',?,'%') ";
			conditionP += StringHelper.noEmpty2(map.get("zb_bianhao")).trim() + ",";
		}
		String sql = "select a.* from project_zbgg a  "
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
	 *    `zb_title` varchar(200) DEFAULT NULL COMMENT '招标标题',
		  `zb_project` varchar(200) DEFAULT NULL,
		  `zb_pizhun` varchar(200) DEFAULT NULL COMMENT '项目批准人',
		  `zb_yezhu` varchar(200) DEFAULT NULL COMMENT '项目业主',
		  `zb_zjly` varchar(200) DEFAULT NULL COMMENT '建设资金来源',
		  `zb_ren` varchar(100) DEFAULT NULL COMMENT '招标人',
		  `zb_dljg` varchar(200) DEFAULT NULL COMMENT '招标代理机构',
		  `zb_bianhao` varchar(200) DEFAULT NULL COMMENT '招标编号',
		  `zb_jsdd` varchar(200) DEFAULT NULL COMMENT '建设地点',
		  `zb_gcztz` varchar(100) DEFAULT NULL COMMENT '工程总投资（万元）',
		  `zb_zgkj` varchar(100) DEFAULT NULL COMMENT '招标最高控价',
		  `zb_bkjjje` varchar(100) DEFAULT NULL COMMENT '不可竞争金额',
		  `zb_fanwei` varchar(1000) DEFAULT NULL COMMENT '招标范围',
		  `operator` varchar(50) DEFAULT NULL COMMENT '操作人id',
	 * @param map
	 * @param operator
	 * 保存招标公告信息
	 * @return
	 */
	public boolean saveproject_zbgg(Map<String, Object> map,String operator)
	{
		StringBuilder sb=new StringBuilder();
		sb.append("insert into project_zbgg(zb_title,zb_project,zb_pizhun,zb_yezhu,zb_zjly,zb_ren,zb_dljg,");
		sb.append("zb_bianhao,zb_jsdd,zb_gcztz,zb_zgkj,zb_bkjjje,zb_fanwei,operator,zb_begtime,zb_endtime)");
		sb.append("values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		Object[] object = new Object[16];
		int i=0;
		object[i] = StringHelper.noEmpty2(map.get("zb_title")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_project")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_pizhun")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_yezhu")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_zjly")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_ren")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_dljg")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_bianhao")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_jsdd")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_gcztz")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_zgkj")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_bkjjje")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_fanwei")).trim();i++;
		object[i] = operator;i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_begtime")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_endtime")).trim();i++;
		return this.executeUpdate(sb.toString(), object);
	}
	
	
	public boolean updateproject_zbgg(Map<String, Object> map)
	{

		StringBuilder sb=new StringBuilder();
		sb.append(" update project_zbgg set zb_title=?,zb_project=?,zb_pizhun=?,zb_yezhu=?,");
		sb.append(" zb_zjly=?,zb_ren=?,zb_dljg=?,zb_bianhao=?,zb_jsdd=?,");
		sb.append(" zb_gcztz=?,zb_zgkj=?,zb_bkjjje=?,zb_fanwei=?,zb_begtime=?,zb_endtime=?");
		sb.append(" where id =? ");
		Object[] object = new Object[16];
		int i=0;
		object[i] = StringHelper.noEmpty2(map.get("zb_title")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_project")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_pizhun")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_yezhu")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_zjly")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_ren")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_dljg")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_bianhao")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_jsdd")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_gcztz")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_zgkj")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_bkjjje")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_fanwei")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_begtime")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_endtime")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("id")).trim();i++;
		return this.executeUpdate(sb.toString(), object);
	}
	
	
	public boolean delzbgg(String id)
	{	
		StringBuilder sb=new StringBuilder();
		sb.append("delete from project_zbgg where id =? ");
		return this.executeUpdate(sb.toString(), id);
	}
	
	
	
	/**
	 *   `id` bigint(20) NOT NULL AUTO_INCREMENT,
		  `project_name` varchar(200) DEFAULT NULL COMMENT '项目名称',
		  `project_bianhao` varchar(100) DEFAULT NULL,
		  `zb_lxpw_path` varchar(200) DEFAULT NULL COMMENT '立项批文',
		  `zb_httk_path` varchar(200) DEFAULT NULL COMMENT '合同条款及合同协议格式',
		  `zb_gclqd_path` varchar(200) DEFAULT NULL COMMENT '工程量清单',
		  `zb_jsjy_path` varchar(200) DEFAULT NULL COMMENT '技术检验和要求',
		  `zb_sgtz_path` varchar(200) DEFAULT NULL COMMENT '施工图纸',
		  `zb_other_path` varchar(200) DEFAULT NULL COMMENT '其他附件',
		  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
	 * @param map
	 * @param operator
	 * @return
	 */
	public boolean saveproject_zbyqh(Map<String, Object> map,String operator)
	{
		StringBuilder sb=new StringBuilder();
		sb.append("insert into project_zbyqh(project_name,project_bianhao,zb_lxpw_path,zb_httk_path,zb_gclqd_path,zb_jsjy_path,zb_sgtz_path,");
		sb.append("zb_other_path,remark,operator,zb_num,zb_tbxz)");
		sb.append("values(?,?,?,?,?,?,?,?,?,?,?,?)");
		Object[] object = new Object[12];
		int i=0;
		object[i] = StringHelper.noEmpty2(map.get("project_name")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("project_bianhao")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_lxpw_path")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_httk_path")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_gclqd_path")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_jsjy_path")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_sgtz_path")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_other_path")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("remark")).trim();i++;
		object[i] = operator;i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_num")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_tbxz")).trim();i++;
		return this.executeUpdate(sb.toString(), object);
	}
	
	/**
	 * 修改招标邀请函
	 * @param map
	 * @return
	 */
	public boolean updateproject_zbyqh(Map<String, Object> map)
	{

		StringBuilder sb=new StringBuilder();
		sb.append(" update project_zbyqh set project_name=?,project_bianhao=?,zb_lxpw_path=?,zb_httk_path=?,");
		sb.append(" zb_gclqd_path=?,zb_jsjy_path=?,zb_sgtz_path=?,zb_other_path=?,zb_num=?,remark=?,zb_tbxz=?");
		sb.append(" where id =? ");
		Object[] object = new Object[12];
		int i=0;
		object[i] = StringHelper.noEmpty2(map.get("project_name")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("project_bianhao")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_lxpw_path")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_httk_path")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_gclqd_path")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_jsjy_path")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_sgtz_path")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_other_path")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_num")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("remark")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("zb_tbxz")).trim();i++;
		object[i] = StringHelper.noEmpty2(map.get("id")).trim();i++;
		return this.executeUpdate(sb.toString(), object);
	}
	
	
	
	/**
	 * 招标邀请函
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public OraPaginatedList queryproject_zbyqh(Map<String, Object> map,int pageNum,int pageSize) 
	{
		String condition=this.getDesSql(map);
		String conditionP = "";
		String param = "";
		if(map!= null && !StringHelper.noEmpty2(map.get("project_name")).trim().equals(""))
		{
			param += " and a.project_name like concat('%',?,'%') ";
			conditionP += StringHelper.noEmpty2(map.get("project_name")).trim() + ",";
		}
		if(map!= null && !StringHelper.noEmpty2(map.get("project_bianhao")).trim().equals(""))
		{
			param += " and a.project_bianhao like concat('%',?,'%') ";
			conditionP += StringHelper.noEmpty2(map.get("project_bianhao")).trim() + ",";
		}
		String sql = "select a.* from project_zbyqh a  "
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
	
	public boolean delzbyqh(String id)
	{	
		StringBuilder sb=new StringBuilder();
		sb.append("delete from project_zbyqh where id =? ");
		return this.executeUpdate(sb.toString(), id);
	}
	
	
	public Map<String, Object> queryzbyqh(String id) 
	{
		String sql = "select a.* from project_zbyqh a  "
                +" where id=?  ";
        return this.getHashMap_Pre(sql, id);
	}
}
