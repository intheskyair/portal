package com.zqkj.bsm.worklog.dao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zqkj.bsm.dal.bean.Admin;
import com.zqkj.bsm.dal.dboper.DbOperator;
import com.zqkj.bsm.system.action.SysPara;
import com.zqkj.bsm.system.action.page.OraPaginatedList;
import com.zqkj.bsm.system.action.util.StringHelper;

public class WorkLogDao extends DbOperator
{

	
	private static WorkLogDao instance = null;
	public static WorkLogDao getInstance()
	{
		if(instance==null)
		{
			instance = new WorkLogDao();
		}
		return instance;
	}
	
	//判断是否考勤当天
	@SuppressWarnings("rawtypes")
	public Map isTime(String id,int type) 
	{
		String sql = "select * from work_time where t_date = CURDATE() and t_type="+type+" and uid = " + id;
		Map<String, Object> map = this.getHashMap_Pre(sql);
		return map;
	}
	
	//上下班登记
	public boolean addWorkTime(HttpServletRequest request,String uname) 
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm"); 
	    long start = System.currentTimeMillis();  
		String type=StringHelper.noEmpty2(request.getParameter("t_type")).trim();
		String uid=StringHelper.noEmpty2(request.getParameter("uid")).trim();
		
		Object[] object = new Object[6];
		object[0] = type;
		object[1] = sdf1.format(start);
		object[3] = sdf.format(start);
		object[4] = uid;
		object[5] = uname;
		String wt = SysPara.off_time;	
		if(Integer.valueOf(type) == 1 || Integer.valueOf(type) == 2){
			if(wt.compareTo(object[1].toString()) >=0){
				object[2] = 1;
			}else{
				object[2] = 2;
			}
		}
//		if(Integer.valueOf(type) == 2){
//			if(wt.compareTo(object[1].toString()) >=0){
//				object[2] = 2;
//			}else{
//				object[2] = 1;
//			}
//		}
		String sql_log = "insert into work_time(t_type,t_time,t_status,t_date,uid,uname) values(?,?,?,?,?,?)";
		return this.executeInsert(sql_log, object);		
	}
	
	public OraPaginatedList queryMzpc(String userid,String username,String log_date,int pageNum,int pageSize,String sortorder,String sortname) 
	{
		String condition="";
		String conditionP = "";
		String param = "";
		if(userid!= null && !userid.trim().equals(""))
		{
			param += " and userid= ? ";
			conditionP += userid.trim() + ",";
		}
		if(username!= null && !username.trim().equals(""))
        {
            param += " and username like concat('%',?,'%') ";
            conditionP += username.trim() + ",";
        }
		if(log_date!= null && !log_date.trim().equals(""))
        {
		    param += " and date_format(log_date, '%Y-%m-%d') = ?";
            conditionP += log_date.trim() + ",";
        }
		condition += " order by log_date desc";
		String sql = "select * from work_log "
                +" where 1=1 "+param ;
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
	
	public List<Map<String, Object>> queryAllTime(String startTime) throws ParseException{
		String flag = "";
		String[] ds = startTime.split("-");
		if(!startTime.equals("")){
			flag = "year(a.date)= "+ds[0]+" and month(a.date) = "+ds[1]+"";
		}else{
			flag = "date_format(a.date,'%Y-%m')=date_format(now(),'%Y-%m')";
		}
		String sql = "select a.date,b.uname,if(b.t_type=1,'上午','下午') as type,b.t_status,b.t_time from work_time b LEFT JOIN temp_date a    on  a.date = b.t_date"
                +" where 1=1 and "+flag+" order by a.date asc   ";
		return this.getList_Pre(sql);
	}
	
	public List<Map<String, Object>> queryAllTime2(String startTime,String orgId) throws ParseException{
        String flag = "";
        String[] ds = startTime.split("-");
        if(!startTime.equals("")){
            flag = "year(a.date)= "+ds[0]+" and month(date) = "+ds[1]+"";
        }else{
            flag = "date_format(a.date,'%Y-%m')=date_format(now(),'%Y-%m')";
        }
        String sql = "select a.date,b.uname,if(b.t_type=1,'上午','下午') as type,b.t_status,b.t_time from work_time b LEFT JOIN temp_date a    on  a.date = b.t_date"
                +" where 1=1 and "+flag+" order by a.date asc   ";
        return this.getList_Pre(sql);
    }
	
	
	
	public boolean addWorkLog(HttpServletRequest request) 
	{
		String title=StringHelper.noEmpty2(request.getParameter("title")).trim();
		String content=StringHelper.noEmpty2(request.getParameter("content")).trim();
		String uid=StringHelper.noEmpty2(request.getParameter("user_id")).trim();
		String username=StringHelper.noEmpty2(request.getParameter("username")).trim();
		String logdate=StringHelper.noEmpty2(request.getParameter("logdate")).trim();
		
		Object[] object = new Object[5];
		object[0] = title;
		object[1] = content;
		object[2] = uid;
		object[3] = username;
		object[4] = logdate;
		String sql_log = "insert into work_log(log_title,log_content,userid,username,log_date) values(?,?,?,?,?)";
		return this.executeInsert(sql_log, object);		
	}
	
	public boolean updateWorkLog(HttpServletRequest request) 
	{
		String title=StringHelper.noEmpty2(request.getParameter("title")).trim();
		String content=StringHelper.noEmpty2(request.getParameter("content")).trim();
		String uid=StringHelper.noEmpty2(request.getParameter("user_id")).trim();
		String username=StringHelper.noEmpty2(request.getParameter("username")).trim();
		String logdate=StringHelper.noEmpty2(request.getParameter("logdate")).trim();
		String id=StringHelper.noEmpty2(request.getParameter("id")).trim();
		
		Object[] object = new Object[6];
		object[0] = title;
		object[1] = content;
		object[2] = uid;
		object[3] = username;
		object[4] = logdate;
		object[5] = id;
		String sql_log = "update work_log set log_title = ? ,log_content=?,userid=? ,username=?,log_date=? where log_id =?";
		return this.executeUpdate(sql_log, object);		
	}
	
	public OraPaginatedList dateToJson(Long userid,int pageNum,int pageSize){
		String conditionP = "";
		String param = "";
		param += " where userid= ? ";
		conditionP += userid+ ",";
		String sql = "select DISTINCT DATE_FORMAT(log_date, '%Y-%m-%d')  as log_date  from work_log "
                +param ;
		Object[] oArray_count = null;
		if (conditionP != null && !"".equals(conditionP)) {
			conditionP = conditionP.substring(0, conditionP.length() - 1);
			oArray_count = conditionP.split(",");
		}
		
		OraPaginatedList list = new OraPaginatedList(sql,oArray_count);
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
	 * 查询日志详情
	 * @return
	 */
	public Map<String, Object> queryById(String id)
	{
		String sql = "select * from work_log where log_id="+id;
		return  this.getHashMap_Pre(sql);
	}
	
	
	public boolean delPf(long id){
		String sql = "delete from kh_pf where main_id = ?";
		return this.executeUpdate(sql,new Object[]{id});
	}
	
}
