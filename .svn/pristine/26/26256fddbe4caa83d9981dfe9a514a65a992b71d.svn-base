package com.zqkj.bsm.dal.dboper;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.zqkj.bsm.dal.client.BaseOpera;
import com.zqkj.bsm.system.action.SysPara;
import com.zqkj.bsm.system.action.util.StringHelper;
/**
 * 
 * @author 0242_gaoefeng
 *
 */

public class DbOperator extends BaseOpera
{
   protected Logger log = Logger.getLogger(this.getClass());
   protected static long _maxNumber = 100000;
   
   protected double sum(double d1,double d2)
   {
       BigDecimal db1 = new BigDecimal(Double.toString(d1));
       BigDecimal db2 = new BigDecimal(Double.toString(d2));
       return db1.add(db2).doubleValue();
   }
   
   protected String getTimeRequest( int cycle ,String statisticalTime){
		String request_time = "";
		if(cycle==1){//按天
			request_time = "date_format("+statisticalTime+", '%Y-%m-%d')";
		}else if(cycle==2){//按周
			request_time = "date_format("+statisticalTime+", '%Y-%u')";
		}else if(cycle==3){//按月
			request_time = "date_format("+statisticalTime+", '%Y-%m')";
		}
		return request_time;
	}
   
	protected String getCount(String sql) {
		String sql2 = "select count(1) count1 from ( " + sql + " ) a ";
		return sql2;
	}
   
	protected boolean executeInsert(String sql,final Object... obj) 
	{
		return this.executeUpdate(sql,obj);
	}
   
	protected long executeInsert_backID(String sql,final Object... obj) 
	{
		return this.executeUpdate_backID(sql,obj);
	}
	
	
	protected long executeUpdate_backID(String sql,final Object... obj) 
	{
		PreparedStatement ps = null;
		Connection conn = null;
		SqlSession session = getSession();
		long return_id = -1;
		try 
		{
			conn = session.getConnection();
			ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			if(obj!=null && obj.length>0)
			{
				for(int i=0,len=obj.length;i<len;i++)
				{
					ps.setObject(i+1, obj[i]);			
				}
			}			
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next())
			{
				return_id = rs.getInt(1);
			}
			conn.commit();
			return return_id;
		} catch (Exception e) 
		{
			log.error("异常:" + e);
			log.error("异常sql=" + sql);
			e.printStackTrace();
			return -1;
		} finally 
		{
			this.clean(null,ps,null);
			session.close();
		}
	}
	
	
	protected long executeUpdate_backID(String sql) 
	{
		PreparedStatement ps = null;
		Connection conn = null;
		SqlSession session = getSession();
		long return_id = -1;
		try 
		{
			conn = session.getConnection();
			ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);		
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next())
			{
				return_id = rs.getInt(1);
			}
			conn.commit();
			return return_id;
		} catch (Exception e) 
		{
			log.error("异常:" + e);
			log.error("异常sql=" + sql);
			e.printStackTrace();
			return -1;
		} finally 
		{
			this.clean(null,ps,null);
			session.close();
		}
	}
	
	
	protected boolean executeUpdate(String sql,final Object... obj) 
	{
		PreparedStatement ps = null;
		Connection conn = null;
		SqlSession session = getSession();
		try 
		{
			conn = session.getConnection();
			ps = conn.prepareStatement(sql);
			if(obj!=null && obj.length>0)
			{
				for(int i=0,len=obj.length;i<len;i++)
				{
					ps.setObject(i+1, obj[i]);			
				}
			}			
			ps.executeUpdate();
			conn.commit();
			return true;
		} catch (Exception e) {
			log.error("异常:" + e);
			log.error("异常sql==" + sql);
			e.printStackTrace();
			return false;
		} finally 
		{
			this.clean(null,ps,null);
			session.close();
		}
	}
   
	
	protected boolean executeUpdate(String sql) 
	{
		PreparedStatement ps = null;
		Connection conn = null;
		SqlSession session = getSession();
		try 
		{
			conn = session.getConnection();
			ps = conn.prepareStatement(sql);		
			ps.executeUpdate();
			conn.commit();
			return true;
		} catch (Exception e) {
			log.error("异常:" + e);
			log.error("异常sql=" + sql);
			e.printStackTrace();
			return false;
		} finally 
		{
			this.clean(null,ps,null);
			session.close();
		}
	}
   
  
   /**
    * 根据sql直接获取map,已经跟数据库中字段对�?
    * @param sql
    * @param obj
    * @return
    */
	protected ArrayList<Map<String, Object>> getList_Pre(String sql) 
	{
		log.debug("当前方法：getList_Pre");;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		SqlSession session = getSession();
		ArrayList<Map<String, Object>> pkv = new ArrayList<Map<String, Object>>();
		try 
		{
			conn = session.getConnection();
			if(conn!=null)
			{
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				conn.commit();
				rsmd = rs.getMetaData();
				int num = rsmd.getColumnCount();
				while (rs.next()) 
				{
					Map<String, Object> map = new HashMap<String, Object>();
					for (int i = 1; i <= num; i++) 
					{
						String key = rsmd.getColumnLabel(i).toUpperCase();
						int colType = rsmd.getColumnType(i);
						if (colType == Types.VARCHAR || colType == Types.CHAR) 
						{
							String value = rs.getString(i);
							map.put(key, value);
						} 
						else if (colType == Types.INTEGER || colType == Types.DECIMAL ||colType == Types.NUMERIC || 
								colType == Types.BIGINT || colType==Types.FLOAT || colType==Types.DOUBLE || colType==Types.REAL)
						{
							BigDecimal value = rs.getBigDecimal(i);
							if (value == null) 
							{
								value = new BigDecimal(0);
							}
							map.put(key, value);
						} else if (colType == Types.TIMESTAMP
								|| colType == Types.DATE) 
						{
							Date value = null;
							value = rs.getTimestamp(i);
							map.put(key, DateUtils.getTimeALL(value));
							map.put(key + "_SHOW_DATE",
									DateUtils.getTimeStr(value)[1]);
							map.put(key + "_FORMAT_DATE", DateUtils
									.getTimeStr(value)[0]);
						}
						else
						{
							Object value = rs.getString(i);
							map.put(key, value);
						}
					}
					pkv.add(map);
				}
				return pkv;
			}
			else
			{
				log.error("getList_Pre：获取数据库连接为null");
				return null;
			}
		} catch (Exception e) 
		{
			log.error("异常：" + e);
			log.error("异常sql:" + sql);
			e.fillInStackTrace();
			return null;
		} finally 
		{
			this.clean(rs, stmt, null);
			session.close();
		}
	}
	
	
	
	protected ArrayList<Map<String, Object>> getList_Pre(String sql,final Object... obj) 
	{
		log.debug("当前方法：getList_Pre");;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		SqlSession session = getSession();
		ArrayList<Map<String, Object>> pkv = new ArrayList<Map<String, Object>>();
		try 
		{
			conn = session.getConnection();
			if(conn!=null)
			{
				stmt = conn.prepareStatement(sql);
				if(obj!=null && obj.length>0)
				{
					for(int i=0,len=obj.length;i<len;i++)
					{
						if(obj[i]!=null)
						{
					       stmt.setObject(i+1, obj[i]);			
						}
						else
						{
							stmt.setObject(i+1, i+1);
						}
					}
				}
				rs = stmt.executeQuery();
				conn.commit();
				rsmd = rs.getMetaData();
				int num = rsmd.getColumnCount();
				while (rs.next()) 
				{
					Map<String, Object> map = new HashMap<String, Object>();
					for (int i = 1; i <= num; i++) 
					{
						String key = rsmd.getColumnLabel(i).toUpperCase();
						int colType = rsmd.getColumnType(i);
						if (colType == Types.VARCHAR || colType == Types.CHAR) 
						{
							String value = rs.getString(i);
							map.put(key, value);
						} 
						else if (colType == Types.INTEGER || colType == Types.DECIMAL ||colType == Types.NUMERIC || 
								colType == Types.BIGINT || colType==Types.FLOAT || colType==Types.DOUBLE || colType==Types.REAL)
						{
							BigDecimal value = rs.getBigDecimal(i);
							if (value == null) 
							{
								value = new BigDecimal(0);
							}
							map.put(key, value);
						} else if (colType == Types.TIMESTAMP
								|| colType == Types.DATE) 
						{
							Date value = null;
							value = rs.getTimestamp(i);
							map.put(key, DateUtils.getTimeALL(value));
							map.put(key + "_SHOW_DATE",
									DateUtils.getTimeStr(value)[1]);
							map.put(key + "_FORMAT_DATE", DateUtils
									.getTimeStr(value)[0]);
						}
						else
						{
							Object value = rs.getString(i);
							map.put(key, value);
						}
					}
					pkv.add(map);
				}
				return pkv;
			}
			else
			{
				log.error("getList_Pre：获取数据库连接为null");
				return null;
			}
		} catch (Exception e) 
		{
			log.error("异常：" + e);
			log.error("异常sql:" + sql);
			e.fillInStackTrace();
			return null;
		} finally 
		{
			this.clean(rs, stmt, null);
			session.close();
		}
	}
	
	
	/**
	 * 只获取一条首记录
	 * @param sql
	 * @param obj
	 * @return
	 */
	protected Map<String, Object> getHashMap_Pre(String sql,final Object... obj) 
	{
		List<Map<String, Object>> list = this.getList_Pre(sql,obj);
		if (list != null && list.size() > 0) 
		{
			Map<String, Object> map_tmp = list.get(0);
			list = null;
			return map_tmp;
		} 
		else 
		{
			return null;
		}
	}
	
	
	protected Map<String, Object> getHashMap_Pre(String sql) 
	{
		List<Map<String, Object>> list = this.getList_Pre(sql);
		if (list != null && list.size() > 0) 
		{
			Map<String, Object> map_tmp = list.get(0);
			list = null;
			return map_tmp;
		} 
		else 
		{
			return null;
		}
	}
	
	protected void clean(ResultSet rs, PreparedStatement ps, Statement st)
	{
		if (rs != null) 
		{
			try {
				Statement st2 = rs.getStatement();
				if (st2 != null) 
				{
					st2.close();
					st2 = null;
				}
				rs.close();
				rs = null;
			} catch (Exception e) {
				log.error("异常:" + e);
				e.printStackTrace();
			}
		}
		if (ps != null) 
		{
			try {
				ps.close();
				ps = null;
			} catch (Exception e) {
				log.error("异常:" + e);
				e.printStackTrace();
			}
		}
		if (st != null) 
		{
			try {
				st.close();
				st = null;
			} catch (Exception e) {
				log.error("异常:" + e);
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 判断记录有多少条
	 * @param sql
	 * @param obj
	 * @return
	 */
	protected int getCount(String sql,final Object... obj) 
	{
		String sql2 = "select count(1) count1 from (" + sql
				+ ") aaa ";
		Map<String, Object> map = null;
		if(obj!=null && obj.length>0)
		{
		  map = this.getHashMap_Pre(sql2, obj);
		}
		else
		{
		 map = this.getHashMap_Pre(sql2);
		}
		 return map==null?0:Integer.valueOf(String.valueOf(map.get("COUNT1"))).intValue();
	}
	
	protected int getCountNoCond(String sql)
	{
		String sql2 = "select count(1) count1 from (" + sql
				+ ") aaa";
				//limit 0," + _maxNumber;
		 Map<String, Object> map = this.getHashMap_Pre(sql2);
		 return map==null?0:Integer.valueOf(String.valueOf(map.get("COUNT1"))).intValue();
	}
	
	/**
	 * 获得数据库时间
	 * 
	 * @return
	 */
	protected Date getSysdate() 
	{
		String sql = "select NOW() today";
		Map<String, Object> map = this.getHashMap_Pre(sql);
		Date date_tmp = (Date) map.get("TODAY");
		map = null;
		return date_tmp;
	}
	
	
	
	/**
	 * 预执行批处理
	 * @param sql1
	 * @param obj_list1  数据集合1
	 * @param sql2
	 * @param obj_list2  数据集合2
	 * @param sql3
	 * @param obj_list3  数据集合3
	 * @return
	 */
	protected  boolean executeBatch_Pre(String sql1,ArrayList<Object[]> obj_list1,
							         String sql2,ArrayList<Object[]> obj_list2,
			                         String sql3,ArrayList<Object[]> obj_list3
	                                  ) 
	{
		log.debug("当前方法：executeBatch_Pre");;
		Connection conn = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;
		SqlSession session = getSession();
		try 
		{
			conn = session.getConnection();
			if(conn!=null)
			{
				conn.setAutoCommit(false);
				stmt1 = conn.prepareStatement(sql1);
				for(Object[] obj:obj_list1)
				{
					if(obj!=null && obj.length>0)
					{
						for(int i=0,len=obj.length;i<len;i++)
						{
							if(obj[i]!=null)
							{
						       stmt1.setObject(i+1, obj[i]);			
							}
							else
							{
								stmt1.setObject(i+1, "");
							}
						}
					}
					stmt1.addBatch();
				}
				
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
				
				stmt3 = conn.prepareStatement(sql3);
				for(Object[] obj:obj_list3)
				{
					if(obj!=null && obj.length>0)
					{
						for(int i=0,len=obj.length;i<len;i++)
						{
							if(obj[i]!=null)
							{
						       stmt3.setObject(i+1, obj[i]);			
							}
							else
							{
								stmt3.setObject(i+1, "");
							}
						}
					}
					stmt3.addBatch();
				}
				stmt1.executeBatch();
				stmt2.executeBatch();
				stmt3.executeBatch();
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
				rollback(conn);
				log.error("异常:" + e);
				e.printStackTrace();
				return false;
			} finally 
			{
				clean(null,null,stmt1);
				clean(null,null,stmt2);
				clean(null,null,stmt3);
				session.close();
			}
	}
	
	/**
	 * 预执行批处理
	 * @param sql1
	 * @param obj_list1  数据集合1
	 * @param sql2
	 * @param obj_list2  数据集合2
	 * @return
	 */
	protected  boolean executeBatch_Pre2(String sql1,ArrayList<Object[]> obj_list1,
							         String sql2,ArrayList<Object[]> obj_list2) 
	{
		log.debug("当前方法：executeBatch_Pre2");;
		Connection conn = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		SqlSession session = getSession();
		try 
		{
			conn = session.getConnection();
			if(conn!=null)
			{
				conn.setAutoCommit(false);
				stmt1 = conn.prepareStatement(sql1);
				for(Object[] obj:obj_list1)
				{
					if(obj!=null && obj.length>0)
					{
						for(int i=0,len=obj.length;i<len;i++)
						{
							if(obj[i]!=null)
							{
						       stmt1.setObject(i+1, obj[i]);			
							}
							else
							{
								stmt1.setObject(i+1, "");
							}
						}
					}
					stmt1.addBatch();
				}
				
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
				
				stmt1.executeBatch();
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
				rollback(conn);
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
	
	
	/**
	 * 执行批处理
	 * @param al
	 * @return
	 */
	protected boolean executeBatch(ArrayList<String> al) 
	{
		Connection conn = null;
		Statement st = null;
		SqlSession session = getSession();
		try {
			conn = session.getConnection();
			conn.setAutoCommit(false);
			st = conn.createStatement();
			for (int i = 0; i < al.size(); i++) 
			{
				st.addBatch(al.get(i));
			}
			st.executeBatch();
			conn.commit();
			return true;
		} catch (Exception e) 
		{
			rollback(conn);
			log.error("异常:" + e);
			for (int i = 0; i < al.size(); i++) {
				log.error("异常sql@ �?" + (i + 1) + "行："
								+ al.get(i));
			}
			e.printStackTrace();
			return false;
		} finally 
		{
			clean(null,null,st);
			session.close();
		}
	}
	
	
	private void rollback(Connection conn) 
	{
		try {
			conn.rollback();
		} catch (Exception e) {
			log.error("异常:" + e);
			e.printStackTrace();
		}
	}
	
	protected String getSqlForPageSelect(Long beginnum,Long num,String sql)
	{
		if(SysPara.db_bz == 0)
		{
		return 
			 "SELECT A.* FROM ("+sql+" ) A " +
			 " limit " +beginnum+","+num.longValue();
		}
		else
		{
			Long begin = beginnum;
			Long end = beginnum+num;
			String sql_ = "SELECT * FROM (SELECT AA.*, rownum rr  FROM (" + sql
					+ " )AA where rownum<=" + end + " )BB where rr > " + begin;
			return sql_;
		}
	}
	
	
	
	/**
	 * 获取oracle某行范围内的数据
	 * 
	 * @param sql
	 *            查询语句
	 * @param begin
	 *            开始行
	 * @param end
	 *            结束行
	 * @return list
	 */
	protected List<Map<String, Object>> getRangeList(String sql,String con_sql, int currentPage,
			int objectsPerPage,final Object... obj) 
	{
		  con_sql = con_sql==null?"":con_sql;
		  int begin = (currentPage)*objectsPerPage;
          String sql_ = 	"SELECT A.* FROM ("+sql+" ) A " +con_sql+
			 " limit " +begin+","+objectsPerPage;
          if(obj!=null && obj.length>0)
          {
            return this.getList_Pre(sql_, obj);
          }
          else
          {
        	  return this.getList_Pre(sql_);
          }
	}

	/**
	 * oracle的分页
	 * @param sql
	 * @param currentPage
	 * @param objectsPerPage
	 * @param obj
	 * @return
	 */
	protected List<Map<String, Object>> getRangeList_Oracle(String sql,int currentPage,
			int objectsPerPage, final Object... obj) 
	{
		int begin = (currentPage)*objectsPerPage;
		int end = (currentPage+1)*objectsPerPage;
		String sql_ = "SELECT * FROM (SELECT AA.*, rownum rr  FROM (" + sql
				+ " )AA where rownum<=" + end + " )BB where rr > " + begin;
		return this.getList_Pre(sql_, obj);
	}

	/**
	 * 获得序列的下一位
	 * 
	 * @param seq_name  select nextval('MovieSeq');
	 * @return
	 */
	protected String getSeq(String seq_string) 
	{
		String return_sql ="";
		String sql = "select nextval('"+seq_string+"') as NEXTVAL ";
		Map<String, Object> map = this.getHashMap_Pre(sql);
		int seq = Integer.valueOf(String.valueOf(map.get("NEXTVAL"))).intValue();
		if(seq> 9999)
		{
			String sql_ = "select setval('"+seq_string+"',1000) as NEXTVAL";
			Map<String, Object> map_ = this.getHashMap_Pre(sql_);
			return_sql = String.valueOf(map_.get("NEXTVAL"));
		}
		else
		{
			return_sql = String.valueOf(map.get("NEXTVAL"));
		}
		return return_sql;
	}
		
	public String getSeq_Next(String seq_string) 
	{
		String return_sql ="";
		String sql = "select nextval('"+seq_string+"') as NEXTVAL ";
		Map<String, Object> map = this.getHashMap_Pre(sql);
		return_sql = String.valueOf(map.get("NEXTVAL"));
		return return_sql;
	}
	public String getSeq_Curr(String seq_string) 
	{
		String return_sql ="";
		String sql = "select currval('"+seq_string+"') as NEXTVAL ";
		Map<String, Object> map = this.getHashMap_Pre(sql);
		return_sql = String.valueOf(map.get("NEXTVAL"));
		return return_sql;
	}
	
	public void upSeq(String seq_string) 
	{
		String sql = "select nextval('"+seq_string+"') as NEXTVAL ";
		this.getHashMap_Pre(sql);
	}
	
	
	public  boolean executeBatch_Pre(String sql1,ArrayList<Object[]> obj_list1) 
	{
		log.debug("当前方法：executeBatch_Pre");;
		Connection conn = null;
		PreparedStatement stmt1 = null;
		SqlSession session = getSession();
		try 
		{
			conn = session.getConnection();
			if(conn!=null)
			{
				conn.setAutoCommit(false);
				stmt1 = conn.prepareStatement(sql1);
				for(Object[] obj:obj_list1)
				{
					if(obj!=null && obj.length>0)
					{
						for(int i=0,len=obj.length;i<len;i++)
						{
							if(obj[i]!=null)
							{
						      stmt1.setObject(i+1, obj[i]);			
							}
							else
							{
								stmt1.setObject(i+1, "");
							}
						}
					}
					stmt1.addBatch();
				}			
				stmt1.executeBatch();
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
			rollback(conn);
			log.error("异常:" + e);
			e.printStackTrace();
			return false;
		} 
		finally 
		{
			clean(null,null,stmt1);
			session.close();
		}
	}
	
	/**
	 * @author 0387
	 * @param sql 调用过程的sql
	 * @param arrgs 传入存储过程的参数集合
	 * @param isback 是否有返回参数  0表示没有  1表示有
	 * @param returntypes 返回参数的类型集合,类型参考 java.sql.Types
	 * @return
	 */
	protected Object[] executeCall(String sql,int isback,int[] returntypes,final Object... arrgs)
	{
		log.debug("当前方法：executeCall");
		Connection conn = null;
		CallableStatement stmt = null;
		SqlSession session = getSession();
		Object[] result = new Object[returntypes.length];
		int cnt = 1;
		try {
			conn = session.getConnection();
			stmt = conn.prepareCall(sql);
			for(int i = 0; i < arrgs.length; i++){
				stmt.setObject(i+1, arrgs[i]);
				cnt++;
			}
			if(isback == 1){
				for(int i = 0; i < returntypes.length; i++){
					stmt.registerOutParameter(cnt + i, returntypes[i]);
				}
			}
			stmt.execute();
			for(int i = 0; i < returntypes.length; i++){
				result[i] = stmt.getObject(cnt+i);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			clean(null,null,stmt);
			session.close();
		}
	}
	
	
	protected String getSeq_oracle(String seq_name) 
	{
		String sql = "select " + seq_name + ".NextVal as NEXTVAL from dual";
		Map<String, Object> map = this.getHashMap_Pre(sql);
		return String.valueOf(map.get("NEXTVAL"));
	}
	/**
	 * 
	* @Title: executeBlob 
	* @Description: TODO(保存带有blob或者clob字段的表单信息) 
	* @param @param sql
	* @param @param type
	* @param @param obj
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws
	* @author wangwj
	* 
	 */
	protected boolean executeBlob(String sql,String[] type,final Object... obj) 
	{
		PreparedStatement ps = null;
		Connection conn = null;
		InputStream is = null;
		InputStreamReader isr = null;
		SqlSession session = getSession();
		try 
		{
			conn = session.getConnection();
			ps = conn.prepareStatement(sql);
			if(obj!=null && obj.length>0)
			{
				for(int i=0,len=obj.length;i<len;i++)
				{
					if(type[i].toString().toLowerCase().equals("blob")){
						byte[] bytes = obj[i].toString().getBytes();
						is = new ByteInputStream(bytes,bytes.length);
						ps.setBlob(i+1, is);
					}else if(type[i].toString().toLowerCase().equals("clob")){
						byte[] bytes = obj[i].toString().getBytes();
						isr = new InputStreamReader(new ByteInputStream(bytes,bytes.length));
						ps.setClob(i+1, isr);
					}else
						ps.setObject(i+1, obj[i]);
					
				}
			}			
			ps.executeUpdate();
			conn.commit();
			return true;
		} catch (Exception e) {
			log.error("异常:" + e);
			log.error("异常sql==" + sql);
			e.printStackTrace();
			return false;
		} finally 
		{
			this.clean(null,ps,null);
			this.cleanIO(is, isr, null);
			session.close();
		}
	}
	/**
	    * 根据sql直接获取带有blob或者clob字段的map
	    * @param sql
	    * @param obj
	    * @return
	    * @author wangwj
	    */
		protected ArrayList<Map<String, Object>> getListBlob_Pre(String sql) 
		{
			log.debug("当前方法：getList_Pre");;
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			ResultSetMetaData rsmd = null;
			InputStream is = null;
			SqlSession session = getSession();
			ArrayList<Map<String, Object>> pkv = new ArrayList<Map<String, Object>>();
			try 
			{
				conn = session.getConnection();
				if(conn!=null)
				{
					stmt = conn.prepareStatement(sql);
					rs = stmt.executeQuery();
					conn.commit();
					rsmd = rs.getMetaData();
					int num = rsmd.getColumnCount();
					while (rs.next()) 
					{
						Map<String, Object> map = new HashMap<String, Object>();
						for (int i = 1; i <= num; i++) 
						{
							String key = rsmd.getColumnLabel(i).toUpperCase();
							int colType = rsmd.getColumnType(i);
							if (colType == Types.VARCHAR || colType == Types.CHAR) 
							{
								String value = rs.getString(i);
								map.put(key, value);
							} 
							else if (colType == Types.INTEGER || colType == Types.DECIMAL ||colType == Types.NUMERIC || 
									colType == Types.BIGINT || colType==Types.FLOAT || colType==Types.DOUBLE || colType==Types.REAL)
							{
								BigDecimal value = rs.getBigDecimal(i);
								if (value == null) 
								{
									value = new BigDecimal(0);
								}
								map.put(key, value);
							} else if (colType == Types.TIMESTAMP
									|| colType == Types.DATE) 
							{
								Date value = null;
								value = rs.getTimestamp(i);
								map.put(key, DateUtils.getTimeALL(value));
								map.put(key + "_SHOW_DATE",
										DateUtils.getTimeStr(value)[1]);
								map.put(key + "_FORMAT_DATE", DateUtils
										.getTimeStr(value)[0]);
							}else if(colType == Types.LONGVARBINARY || colType == Types.BLOB){
								Blob blob = rs.getBlob(i);
								/*long n = blob.length();
								is = blob.getBinaryStream();
								byte[] bytes = new byte[10240];
								String content = "";
								int count = 0;
								while(is.read(bytes) != -1){
									content += new String(bytes);
									count++;
									if((n - 10240*count) < 10240 && (n - 10240*count) > 0)
										bytes = new byte[(int)(n - 10240*count)];
								}*/
								map.put(key, new String(blob.getBytes(1, (int)blob.length())));//new String(blob.getBytes(1, (int)blob.length())) //content
							}else if(colType == Types.CLOB){
								Clob clob = rs.getClob(i);
								map.put(key, clob.getSubString(1, (int)(clob.length())));
							}else
							{
								Object value = rs.getString(i);
								map.put(key, value);
							}
						}
						pkv.add(map);
					}
					return pkv;
				}
				else
				{
					log.error("getList_Pre：获取数据库连接为null");
					return null;
				}
			} catch (Exception e) 
			{
				log.error("异常：" + e);
				log.error("异常sql:" + sql);
				e.fillInStackTrace();
				return null;
			} finally 
			{
				this.clean(rs, stmt, null);
				this.cleanIO(is, null, null);
				session.close();
			}
		}
		
		protected void cleanIO(InputStream is,Reader r,OutputStream os)
		{
			try {
				if(is != null)
					is.close();
				if(r != null)
					r.close();
				if(os != null){
					os.flush();
					os.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		
		protected String getDesSql(Map<String, Object> map)
		{
			String condition="";
			String sortname = StringHelper.noEmpty2(map.get("sortname")).trim();
			String sortorder = StringHelper.noEmpty2(map.get("sortorder")).trim();
			if (!"undefined".equals(sortname) && !"".equals(sortname)) 
			{
				 condition += " order by " + sortname;
					if ("desc".equals(sortorder) || "asc".equals(sortorder)) 
					{
						condition += " " + sortorder;
					} 
					else 
					{
						condition += " desc";
					}
			} 
			else 
			{
					condition += " order by id desc";
			}
			return condition;
		}
		
		/**
		 * 获取一个表所有的列名
		 * @param sql
		 * @return
		 */
		protected Map<String, String> getList_Column(String sql)
		{
			log.debug("当前方法：getList_Pre");;
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			ResultSetMetaData data = null;
			SqlSession session = getSession();
			Map<String, String> pkv = new HashMap<String, String>();
			try 
			{
				conn = session.getConnection();
				if(conn!=null)
				{
					stmt = conn.prepareStatement(sql);
					rs = stmt.executeQuery();
					conn.commit();
					data = rs.getMetaData();
					int num = data.getColumnCount();
					for (int i = 1; i <= num; i++) 
					{
						// 获得指定列的列名
						String columnName = data.getColumnName(i);
						// 默认的列的标题
						String columnLabel = data.getColumnLabel(i);

						pkv.put(columnName.toUpperCase(), columnLabel);
//						System.out.println("获得列" + i + "的字段名称:" + columnName);
//						System.out.println("获得列" + i + "的类型,返回SqlType中的编号:"+ columnType);
//						System.out.println("获得列" + i + "的数据类型名:" + columnTypeName);
//						System.out.println("获得列" + i + "所在的Catalog名字:"+ catalogName);
//						System.out.println("获得列" + i + "对应数据类型的类:"+ columnClassName);
//						System.out.println("获得列" + i + "在数据库中类型的最大字符个数:"+ columnDisplaySize);
//						System.out.println("获得列" + i + "的默认的列的标题:" + columnLabel);
//						System.out.println("获得列" + i + "的模式:" + schemaName);
						
					}
				}
			}
			catch (Exception e) 
			{
				log.error("异常：" + e.fillInStackTrace());
				log.error("异常sql:" + sql);
				return null;
			} 
			finally 
			{
				this.clean(rs, stmt, null);
				session.close();
			}
			return pkv;
		}

		
		/*
		 * 
		 * 改方法只试用于单个字段查询，name为字段名称
		 */
		protected String getString(String sql,String value,String name) 
		{
			String status = null;
			PreparedStatement ps = null;
			Connection conn = null;
			SqlSession session = getSession();
			try 
			{
				conn = session.getConnection();
				ps = conn.prepareStatement(sql);	
				ps.setString(1, value);
				ResultSet rs = ps.executeQuery();
				conn.commit();
				//处理结果
		        while(rs.next()){
		        	status = rs.getString(name);
		        }
				return status;
			} catch (Exception e) {
				log.error("异常:" + e);
				log.error("异常sql=" + sql);
				e.printStackTrace();
				return null;
			} finally 
			{
				this.clean(null,ps,null);
				session.close();
			}
		}
		
		
		
		
	
		
}
