package com.zqkj.bsm.mzpc.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import com.zqkj.bsm.dal.dboper.DbOperator;
import com.zqkj.bsm.system.action.page.OraPaginatedList;
import com.zqkj.bsm.system.action.util.StringHelper;
import com.zqkj.bsm.util.BHUtil;
import com.zqkj.bsm.util.CommonUtil;

public class MzpcDao extends DbOperator
{

	
	private static MzpcDao instance = null;
	public static MzpcDao getInstance()
	{
		if(instance==null)
		{
			instance = new MzpcDao();
		}
		return instance;
	}
	
	
	public OraPaginatedList queryMzpc(String title,int pageNum,int pageSize,String sortorder,String sortname) 
	{
		String condition="";
		String conditionP = "";
		String param = "";
		if(title!= null && !title.trim().equals(""))
		{
			param += " and a.title like concat('%',?,'%') ";
			conditionP += title.trim() + ",";
		}
		if (!"undefined".equals(sortname) && !"".equals(sortname)) 
		{
			 condition += " order by " + sortname;
				if ("desc".equals(sortorder) || "asc".equals(sortorder)) {
					condition += " " + sortorder;
				} else {
					condition += " desc";
				}
			} else {
				condition += " order by id desc";
			}
		String sql = "select a.*,b.DIC_NAME from kh_main a,kh_dic b "
                +" where a.type=b.Id "+param ;
		Object[] oArray_count = null;
		if (conditionP != null && !"".equals(conditionP)) {
			conditionP = conditionP.substring(0, conditionP.length() - 1);
			oArray_count = conditionP.split(",");
		}
		
		OraPaginatedList list = new OraPaginatedList(sql,oArray_count);
		list.setSortCriterion(condition);
        list.setCurrentPage(pageNum);
        list.setObjectsPerPage(pageSize);
        return list;
	}
	
	
	
	
	public OraPaginatedList candfMzpc(String title,int pageNum,int pageSize,String sortorder,String sortname,long operator) 
	{
		String condition="";
		String conditionP = "";
		String param = "";
		if(title!= null && !title.trim().equals(""))
		{
			param += " and a.title like concat('%',?,'%') ";
			conditionP += title.trim() + ",";
		}
		if (!"undefined".equals(sortname) && !"".equals(sortname)) 
		{
			 condition += " order by " + sortname;
				if ("desc".equals(sortorder) || "asc".equals(sortorder)) {
					condition += " " + sortorder;
				} else {
					condition += " desc";
				}
			} else {
				condition += " order by id desc";
			}
		String sql = "select a.*,b.DIC_NAME,(SELECT count(1) FROM kh_pf c "+
                   " where c.main_id=a.id and c.radom="+operator+" ) as df_bz from kh_main a,kh_dic b "
                +" where a.type=b.Id and a.status=0 and a.begtime<= now() and a.endtime>now() "+param ;
		Object[] oArray_count = null;
		if (conditionP != null && !"".equals(conditionP)) {
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
	 * 评测表的字典配置
	 * @return
	 */
	public List<Map<String, Object>> querykh_dic()
	{
		String sql = "select a.* from kh_dic a";
		return  this.getList_Pre(sql);
	}
	
	/**
	 *  保存评测表的配置
	 * @param request
	 * @return
	 */
	public boolean savekh_main(HttpServletRequest request)
	{
		boolean flag = false;
		String title=StringHelper.noEmpty2(request.getParameter("title")).trim();
		String type=StringHelper.noEmpty2(request.getParameter("type")).trim();
		String begtime=StringHelper.noEmpty2(request.getParameter("begtime")).trim();
		String endtime=StringHelper.noEmpty2(request.getParameter("endtime")).trim();
		String zb=StringHelper.noEmpty2(request.getParameter("pjdj")).trim();
		String PARAVALUE=StringHelper.noEmpty2(request.getParameter("dwgr")).trim();
		String post=StringHelper.noEmpty2(request.getParameter("post")).trim();
		String person=StringHelper.noEmpty2(request.getParameter("person")).trim();
		String max=StringHelper.noEmpty2(request.getParameter("max")).trim();
		String sss = CommonUtil.nullToDefault(request.getParameter("sss"), "0");
		StringBuilder sb=new StringBuilder();
		Object[] object = null;
		if(sss.equals("0")){
			sb.append("insert into kh_main(title,type,begtime,endtime,status,zb,PARAVALUE,post,max) values(?,?,?,?,0,?,?,?,?) ");
			object = new Object[8];
			object[0] = title;
			object[1] = type;
			object[2] = begtime;
			object[3] = endtime;
			object[4] = zb;
			object[5] = PARAVALUE;
			object[6] = post;
			object[7] = max;
		}else{
			String sex = CommonUtil.nullToDefault(request.getParameter("sex"), "");
			String birthday = CommonUtil.nullToDefault(request.getParameter("birthday"), "");
			String oldjob = CommonUtil.nullToDefault(request.getParameter("oldjob"), "");
			String newjob = CommonUtil.nullToDefault(request.getParameter("newjob"), "");
			String jobtime = CommonUtil.nullToDefault(request.getParameter("jobtime"), "");
			sb.append("insert into kh_main(title,type,begtime,endtime,status,zb,PARAVALUE,post,max,sex,birthday,oldjob,newjob,jobtime) values(?,?,?,?,0,?,?,?,?,?,?,?,?,?) ");
			object = new Object[13];
			object[0] = title;
			object[1] = type;
			object[2] = begtime;
			object[3] = endtime;
			object[4] = zb;
			object[5] = PARAVALUE;
			object[6] = post;
			object[7] = max;
			object[8] = sex;
			object[9] = birthday;
			object[10] = oldjob;
			object[11] = newjob;
			object[12] = jobtime;
		}
		String sql2 = "insert into kh_radom (radom,mainid) values(?,?)";
		ArrayList<Object[]> obj_list2 = new ArrayList<Object[]>();
		Object[] obj = null;
		int seq = Integer.parseInt(BHUtil.getMZPCTransID("mzpc")); 
		for (int i = 0; i < Integer.parseInt(person); i++){
			obj = new Object[]{String.format("%04d", seq)+BHUtil.GetRandomString(5),""};
			obj_list2.add(obj);
		}
		flag = this.addBatch_PreMzpc(sb.toString(), object, sql2, obj_list2,seq);
		return flag;
		}
	
	
	public void updateRadomList(ArrayList<Object[]> list){
		String sql2 = "insert into kh_radom (radom,mainid) values(?,?)";
		this.executeBatch_Pre(sql2, list);
	}
	
	
	public boolean updatekh_main(HttpServletRequest request)
	{
		String title =StringHelper.noEmpty2(request.getParameter("title")).trim();
		String type=StringHelper.noEmpty2(request.getParameter("type")).trim();
		String begtime=StringHelper.noEmpty2(request.getParameter("begtime")).trim();
		String endtime=StringHelper.noEmpty2(request.getParameter("endtime")).trim();
		String zb=StringHelper.noEmpty2(request.getParameter("pjdj")).trim();
		String PARAVALUE=StringHelper.noEmpty2(request.getParameter("dwgr")).trim();
		String id=StringHelper.noEmpty2(request.getParameter("id")).trim();
		String post=StringHelper.noEmpty2(request.getParameter("post")).trim();
		String person=StringHelper.noEmpty2(request.getParameter("person")).trim();
		String max=StringHelper.noEmpty2(request.getParameter("max")).trim();
		String editType=StringHelper.noEmpty2(request.getParameter("editType")).trim();
		StringBuilder sb=new StringBuilder();
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		Object[] obj = null;
		Object[] object = null;
		boolean flag = true;
		if(editType.endsWith("1")){
			//删除指定数据
			flag = delRodam(Long.valueOf(id));
			//删除已经打分的记录
			flag = delPf(Long.valueOf(id));
			if(flag){
				//重新生成随机码
				int seq = Integer.parseInt(BHUtil.getMZPCTransID("mzpc")); 				
				for (int i = 0; i < Integer.parseInt(person); i++) {
					String code = String.format("%04d", seq) + BHUtil.GetRandomString(5);
					obj = new Object[]{code,id};
					list.add(obj);
				}
				upSeq("mzpc");
				flag = updateRodam(list);
			}
			
		}
		
			if(flag){
				String sss = CommonUtil.nullToDefault(request.getParameter("sss"), "0");
				if(sss.equals("0")){
					 object = new Object[9];
					sb.append("update kh_main set title=?,type=?,begtime=?,endtime=?,zb=?,PARAVALUE=?,post=?,max=? where id = ?");
					object[0] = title;
					object[1] = type; 
					object[2] = begtime; 
					object[3] = endtime;
					object[4] = zb;
					object[5] = PARAVALUE;
					object[6] = post;
					object[7] = max;
					object[8] = id;
					flag = this.executeUpdate(sb.toString(), object);
				}else{
					String sex = CommonUtil.nullToDefault(request.getParameter("sex"), "");
					String birthday = CommonUtil.nullToDefault(request.getParameter("birthday"), "");
					String oldjob = CommonUtil.nullToDefault(request.getParameter("oldjob"), "");
					String newjob = CommonUtil.nullToDefault(request.getParameter("newjob"), "");
					String jobtime = CommonUtil.nullToDefault(request.getParameter("jobtime"), "");
					 object = new Object[14];
					sb.append("update kh_main set title=?,type=?,begtime=?,endtime=?,zb=?,PARAVALUE=?,post=?,max=?,sex=?,birthday=?,oldjob=?,newjob=?,jobtime=? where id = ?");
					object[0] = title;
					object[1] = type; 
					object[2] = begtime; 
					object[3] = endtime;
					object[4] = zb;
					object[5] = PARAVALUE;
					object[6] = post;
					object[7] = max;
					object[8] = sex;
					object[9] = birthday;
					object[10] = oldjob;
					object[11] = newjob;
					object[12] = jobtime;
					object[13] = id;
				}
				flag = this.executeUpdate(sb.toString(), object);
			}
		return flag;
		}
	
	
	public boolean delkh_main(HttpServletRequest request)
	{
		String id=StringHelper.noEmpty2(request.getParameter("id")).trim();
		StringBuilder sb=new StringBuilder();
		sb.append("update kh_main set status=1  where id =? ");
		Object[] object = new Object[1];
		object[0] = id;
		return this.executeUpdate(sb.toString(), object);
	}

	/**
	 * 评测表的字典配置,根据配置
	 * @return
	 */
	public Map<String, Object> queryById(String id)
	{
		String sql = "select a.*,b.DIC_PAHT,b.DIC_NAME,b.ZT_PJ,b.TYPE as BTYPE,b.ID as DIC_ID,count(c.RADOM) as PERSON from kh_main a,kh_dic b,kh_radom c where a.type=b.id and c.mainid = a.Id and  a.id="+id;
		return  this.getHashMap_Pre(sql);
	}
	
	
	
	public List<Map<String, Object>> querykh_pf(String mainid,String radom)
	{
		String sql = "select a.* from kh_pf a where a.main_id=? and radom=? order by id ";
		return  this.getList_Pre(sql,new Object[]{mainid,radom});
	}
		
	
	public boolean updateRodam(ArrayList<Object[]> list){
		String sql = "insert into kh_radom (radom,mainid) values(?,?)";
		return this.executeBatch_Pre(sql, list);
	}
	
	public List<Map<String, Object>> getRadom(String mainid,String status){
		Object[] obj = null;
		String sql = "select radom from kh_radom where mainid = ? ";
		if(status != null){
			 sql += " and status = ?";
			 obj = new Object[]{mainid,status};
		}else{
			obj = new Object[]{mainid};
		}
		return this.getList_Pre(sql, obj);
	}
	

	
	
	public boolean delRodam(long id){
		String sql = "delete from kh_radom where mainid = ?";
		return this.executeUpdate(sql,new Object[]{id});
	}
	
	public boolean delPf(long id){
		String sql = "delete from kh_pf where main_id = ?";
		return this.executeUpdate(sql,new Object[]{id});
	}
	
	
	public List<Map<String, Object>> totalkh_pf(String mainid,int from,int to,String sortorder,String sortname, Map<String, Integer> mapcount,String type)
	{
		String sql = "";
		if(type.equals("2")){
			sql = "select a.name,sum(case a.total when '10' then 1 else 0 end) as '10_TOTAL',"
					+ "sum(case a.total when '9' then 1 else 0 end) as '9_TOTAL',"
					+ "sum(case a.total when '8' then 1 else 0 end) as '8_TOTAL',"
					+ "sum(case a.total when '7' then 1 else 0 end) as '7_TOTAL',"
					+ "sum(case a.total when '6' then 1 else 0 end) as '6_TOTAL', "
					+ "sum(case a.total when '5' then 1 else 0 end) as '5_TOTAL', "
					+ "sum(case a.total when '4' then 1 else 0 end) as '4_TOTAL', "
					+ "sum(case a.total when '3' then 1 else 0 end) as '3_TOTAL', "
					+ "sum(case a.total when '2' then 1 else 0 end) as '2_TOTAL', "
					+ "sum(case a.total when '1' then 1 else 0 end) as '1_TOTAL' "
					+ " from kh_pf a where a.main_id="+mainid+"  group by a.name  ";
		}else{
			sql = "select a.name,sum(case a.total when 'A+' then 1 else 0 end) AJ_TOTAL,"
					+ "sum(case a.total when 'A' then 1 else 0 end) A_TOTAL,"
					+ "sum(case a.total when 'B' then 1 else 0 end) B_TOTAL,"
					+ "sum(case a.total when 'C' then 1 else 0 end) C_TOTAL,"
					+ "sum(case a.total when 'D' then 1 else 0 end) D_TOTAL "
					+ " from kh_pf a where a.main_id="+mainid+"  group by a.name  ";
		}
		
		int total = this.getCountNoCond(sql);
		mapcount.put("total", total);
		sql += " limit "+from+","+to;
		return  this.getList_Pre(sql);
	}
	
	
	/**
	 * 批量保存打分结果
	 * @param main_id
	 * @param operator
	 * @param zb
	 * @param list_tmp
	 * @return
	 */
	public boolean savepdf(String main_id,String radom,String zb,List<Map<String, Object>> list_tmp)
	{

		StringBuilder sb=new StringBuilder();
		sb.append("insert into kh_pf(main_id,radom,name,zb,para_value,total,df_time) values(?,?,?,?,?,?,now()) ");
		ArrayList<Object[]> obj_list1 = new ArrayList<Object[]>();
		for(Map<String, Object> map: list_tmp)
		{
			Object[] object = new Object[6];
			object[0] = main_id;
			object[1] = radom;
			object[2] = map.get("NAME");
			object[3] = zb;
			object[4] = map.get("para_value");
			object[5] = map.get("PJ");
			obj_list1.add(object);
		}
		return this.executeBatch_Pre(sb.toString(), obj_list1);
		}
	
	
	/**
	 * 保存选人用人打分结果
	 * @param main_id
	 * @param operator
	 * @param zb
	 * @param list_tmp
	 * @return
	 */
	public boolean savepdf3(String main_id,String radom,String json,String msg,String total)
	{

		StringBuilder sb=new StringBuilder();
		sb.append("insert into kh_pf(main_id,radom,para_value,msg,total,df_time) values(?,?,?,?,?,now())");
		
		return this.executeInsert(sb.toString(), new Object[]{main_id,radom,json,msg,total}); 
		}
	
	
	/**
	 * 民主测评sql1返回主键 sql2使用
	 * @param sql1
	 * @param obj_list1
	 * @param sql2
	 * @param obj_list2
	 * @return
	 */
	protected  boolean addBatch_PreMzpc(String sql1,Object[] obj1,
							         String sql2,ArrayList<Object[]> obj_list2
	                                 ,int seq ) 
	{
		log.debug("当前方法：addBatch_PreMzpc");;
		Connection conn = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		SqlSession session = getSession();
		try 
		{
			long return_id = -1;
			conn = session.getConnection();
			if(conn!=null)
			{
				conn.setAutoCommit(false);
				stmt1 = conn.prepareStatement(sql1,Statement.RETURN_GENERATED_KEYS);
				if(obj1!=null && obj1.length>0)
				{
					for(int i=0,len=obj1.length;i<len;i++)
					{
						stmt1.setObject(i+1, obj1[i]);			
					}
				}
				stmt1.executeUpdate();		
				ResultSet rs = stmt1.getGeneratedKeys();
				if (rs.next())
				{
					return_id = rs.getInt(1);
				}
			//单独使用去除下面即可 
			//开始	
				
				for (int i = 0; i < obj_list2.size(); i++) {
					Object[] obj = obj_list2.get(i);
					String radom = String.valueOf(obj[0]);
					obj[0] = radom;
					obj[1] = return_id;
				}
				upSeq("mzpc");
			//结束	
				stmt2 = conn.prepareStatement(sql2);
				for(Object[] obj:obj_list2)
				{
					if(obj!=null && obj.length>0)
					{
						for(int i=0,len=obj.length;i<len;i++)
						{
							if(obj[i]!=null)
							{
						       stmt2.setObject(i+1, obj[i]);			
							}
							else
							{
								stmt2.setObject(i+1, "");
							}
						}
					}
					stmt2.addBatch();
				}
				stmt2.executeBatch();
				conn.commit();
				return true;
			}
			else
			{
				return false;
			}

		}
		 catch (Exception e) 
			{
			 	try {
					conn.rollback();
				} catch (Exception e1) {
					log.error("异常:" + e1);
					e.printStackTrace();
				}
				log.error("异常:" + e);
				e.printStackTrace();
				return false;
			} finally 
			{
				clean(null,null,stmt1);
				clean(null,null,stmt2);
				session.close();
			}
	}
	
	
	public List<Map<String, Object>> getRadomStatus(String radom_df){
		String sql = "SELECT kr.mainid as id,kr.`status` AS kr_status,km.begtime,km.endtime,km.`status` AS km_status,km.title,kr.radom FROM kh_radom kr,kh_main km WHERE kr.mainid = km.Id and kr.radom = ? and km.`status` != 1 and date_format(km.begtime,'%Y-%m-%d') <= date_format(now(),'%Y-%m-%d')  and date_format(km.endtime,'%Y-%m-%d') >= date_format(now(),'%Y-%m-%d')";
		return this.getList_Pre(sql, new Object[]{radom_df});
	}
	
	public List<Map<String, Object>> getRadomStatusTb(String radom_df,String mainId){
		String sql = "SELECT kr.mainid as id,kr.`status` AS kr_status,km.begtime,km.endtime,km.`status` AS km_status,km.title,kr.radom FROM kh_radom kr,kh_main km WHERE kr.mainid = km.Id and kr.radom = ? and km.id = ?";
		return this.getList_Pre(sql, new Object[]{radom_df,mainId});
	}
	
	
	public boolean updateRadom(String radom,String mainid){
		String sql = "update kh_radom set status = 1 where radom = ? and mainid = ?";
		return this.executeUpdate(sql, new Object[]{radom,mainid});
	}
	
	
	/**
	 * 评测表的字典配置
	 * @return
	 */
	public Map<String, String> querykh_dic(String mainid)
	{
		String sql = "select a.ZT_PJ,a.TYPE from kh_dic a,kh_main b where a.Id=b.type and b.Id=?";
		Map<String, Object> map = this.getHashMap_Pre(sql, mainid);
		Map<String, String> map_return=null;
		if(map!=null && map.size()>0)
		{
			map_return = new HashMap<String, String>();
			String ZT_PJ =String.valueOf(map.get("ZT_PJ"));
			String[] a =ZT_PJ.split("~")[0].split(",");
			String[] a_value=ZT_PJ.split("~")[1].split(",");			
			for(int i=0,len=a.length;i<len;i++)
			{
				map_return.put(a[i], a_value[i]);
			}
			map_return.put("TYPE", map.get("TYPE").toString());
		}
		return map_return;
	}
	
	
	/** 
	* @Title: getRadomUsed 
	* @Description: TODO(获取当前评分记录) 
	* @param @param id
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	public int getRadomUsed(String id){
		String sql = "select * from kh_pf where main_id = ?";
		return this.getCount(sql, new Object[]{id});
	}
	
	/** 
	* @Title: getRadomUsed 
	* @Description: TODO(获取第二类投票信息) 
	* @param @param id
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	public ArrayList<Map<String, Object>> getTypeOne(String id){
		String sql = "SELECT kp.name,kp.para_value,a.org_infor FROM kh_pf kp LEFT JOIN admin a ON kp.`name` = a.truename WHERE kp.main_id = ?";
		return this.getList_Pre(sql,new Object[]{id});
	}
	
	
	/** 
	* @Title: getMainTitle 
	* @Description: TODO(获取当前标题) 
	* @param @param id
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	public Map<String, Object> getMainTitle(String id){
		String sql = "select * from kh_main where id = ?";
		return this.getHashMap_Pre(sql, new Object[]{id});
	}
	
	/** 
	* @Title: getResultRadom 
	* @Description: TODO(获取当前随机码使用情况) 
	* @param @param id
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	public Map<String, Object> getResultRadom(String id){
		String sql = "SELECT sum(CASE when status = 1 then 1 else 0 end) as oldradom ,sum(CASE when status = 0 then 1 else 0 end) as newradom,count(*) as allradom FROM kh_radom WHERE mainid = ?";
		return this.getHashMap_Pre(sql, new Object[]{id});
	}
	
	
	
	/** 
	* @Title: getMzpcStatus 
	* @Description: TODO(获取当前民主评测类型) 
	* @param @param id
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	public Map<String, Object> getMzpcStatus(String id){
		String sql = "SELECT kd.type as type FROM kh_main km,kh_dic kd WHERE km.type = kd.Id and km.Id = ?";
		return this.getHashMap_Pre(sql, new Object[]{id});
	}
	
	/** 
	* @Title: getMzpfResult 
	* @Description: TODO(第一类民主评测查询) 
	* @param @param mainid
	* @param @return    设定文件 
	* @return ArrayList<Map<String,Object>>    返回类型 
	* @throws 
	*/
	public ArrayList<Map<String, Object>> getMzpfResult(String mainid){
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT NAME, ");
		sb.append("IFNULL(sum(CASE WHEN total = 'A+' THEN 1 END),0) AS 'A+', ");
		sb.append("IFNULL(sum(CASE WHEN total = 'A' THEN 1 END),0) AS 'A', ");
		sb.append("IFNULL(sum(CASE WHEN total = 'B' THEN 1 END),0) AS 'B', ");
		sb.append("IFNULL(sum(CASE WHEN total = 'C' THEN 1 END),0) AS 'C', ");
		sb.append("IFNULL(sum(CASE WHEN total = 'D' THEN 1 END),0) AS 'D' ");
		sb.append("FROM kh_pf where main_id = ? GROUP BY NAME ");
		return this.getList_Pre(sb.toString(), new Object[]{mainid});
	}
	
	
	
	/** 
	* @Title: getMzpfResult 
	* @Description: TODO(第一类民主评测查询) 
	* @param @param mainid
	* @param @return    设定文件 
	* @return ArrayList<Map<String,Object>>    返回类型 
	* @throws 
	*/
	public ArrayList<Map<String, Object>> getMzpfResultTwo(String mainid){
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT NAME, ");
		sb.append("IFNULL(sum(CASE WHEN total = '10' THEN 1 END),0) AS '10分', ");
		sb.append("IFNULL(sum(CASE WHEN total = '9' THEN 1 END),0) AS '9分', ");
		sb.append("IFNULL(sum(CASE WHEN total = '8' THEN 1 END),0) AS '8分', ");
		sb.append("IFNULL(sum(CASE WHEN total = '7' THEN 1 END),0) AS '7分', ");
		sb.append("IFNULL(sum(CASE WHEN total = '6' THEN 1 END),0) AS '6分', ");
		sb.append("IFNULL(sum(CASE WHEN total = '5' THEN 1 END),0) AS '5分', ");
		sb.append("IFNULL(sum(CASE WHEN total = '4' THEN 1 END),0) AS '4分', ");
		sb.append("IFNULL(sum(CASE WHEN total = '3' THEN 1 END),0) AS '3分', ");
		sb.append("IFNULL(sum(CASE WHEN total = '2' THEN 1 END),0) AS '2分', ");
		sb.append("IFNULL(sum(CASE WHEN total = '1' THEN 1 END),0) AS '1分' ");
		sb.append("FROM kh_pf where main_id = ? GROUP BY NAME ");
		return this.getList_Pre(sb.toString(), new Object[]{mainid});
	}
	
	
	/** 
	* @Title: getMzpfResult 
	* @Description: TODO(第一类民主评测查询) 
	* @param @param mainid
	* @param @return    设定文件 
	* @return ArrayList<Map<String,Object>>    返回类型 
	* @throws 
	*/
	public ArrayList<Map<String, Object>> getMzpfResultThree(String mainid){
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM kh_pf where main_id = ?");
		return this.getList_Pre(sb.toString(), new Object[]{mainid});
	}
	
	
	
}
