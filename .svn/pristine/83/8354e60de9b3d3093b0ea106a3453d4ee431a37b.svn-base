package com.zqkj.bsm.boardroom.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.zqkj.bsm.system.action.page.OraPaginatedList;
import com.zqkj.bsm.system.action.util.StringHelper;
import com.zqkj.bsm.util.CommonUtil;
import com.zqkj.bsm.boardroom.bean.Bespeak;
import com.zqkj.bsm.carmanager.dao.CarManagerDao;
import com.zqkj.bsm.dal.dboper.DbOperator;

public class BespeakDao extends DbOperator{
	
	private static BespeakDao instance = null;
	public static BespeakDao getInstance()
	{
		if(instance==null)
		{
			synchronized (BespeakDao.class)
			{
			  instance = new BespeakDao();
			}
		}
		return instance;
	}
	
	
	/**
	 * 获取会议室详情
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public OraPaginatedList queryboardroom(Map<String, Object> map,int pageNum,int pageSize) 
	{
		String condition=this.getDesSql(map);
		StringBuffer conditionP = new StringBuffer();
		StringBuffer sb = new StringBuffer();
		String boardroom_name = CommonUtil.nullToDefault(map.get("boardroom_name"),"").trim();
		String boardroom_location = CommonUtil.nullToDefault(map.get("boardroom_location"),"").trim();
		String status = CommonUtil.nullToDefault(map.get("status"),"").trim();
		sb.append("select * from boardroom t where 1=1 ");
		if(!"".equals(boardroom_name)){
			conditionP.append(" and t.boardroom_name like '%").append(boardroom_name).append("%'");
		}
		if(!"".equals(boardroom_location)){
			conditionP.append(" and t.boardroom_location like '%").append(boardroom_location).append("%'");
		}
		if(!"".equals(status) && Integer.valueOf(status) != -1){
			conditionP.append(" and t.boardroom_status = '").append(status).append("'");			
		}
		Object[] oArray_count = null;
		
		sb.append(conditionP).append(" ").append(condition);
		OraPaginatedList list = new OraPaginatedList(sb.toString(),oArray_count);
		list.setSortCriterion(condition);
        list.setCurrentPage(pageNum);
        list.setObjectsPerPage(pageSize);
		return list;
	}
	
	
	/**
	 * 新增会议室
	 * @param params
	 * @return
	 */
	public boolean addBoardroom(Map<String, Object> map){
		StringBuilder sb=new StringBuilder();
		sb.append("insert into boardroom(boardroom_name,boardroom_location,boardroom_status) "
				+ "values(?,?,?) ");
		Object[] object = new Object[3];
		
		object[0] = StringHelper.noEmpty2(map.get("boardroom_name")).trim();
		object[1] = StringHelper.noEmpty2(map.get("boardroom_location")).trim();
		object[2] = StringHelper.noEmpty2(map.get("boardroom_status")).trim();
		
		return this.executeUpdate(sb.toString(), object);
	}
	
    /**
     * 修改会议室信息
     * @param map
     * @return
     */
	public boolean updateboardroom(Map<String, Object> map)
	{
     
		StringBuilder sb=new StringBuilder();
		sb.append("update boardroom set boardroom_name=?,boardroom_location=?,boardroom_status=? where id =?");
        Object[] object = new Object[4];
		object[0] = StringHelper.noEmpty2(map.get("boardroom_name")).trim();
		object[1] = StringHelper.noEmpty2(map.get("boardroom_location")).trim();
		object[2] = StringHelper.noEmpty2(map.get("boardroom_status")).trim();
		object[3] = StringHelper.noEmpty2(map.get("id")).trim();
		
		return this.executeUpdate(sb.toString(), object);
	}
	
	/**
	 * 根据ID删除会议室
	 * @param id
	 * @return
	 */
	public boolean deleteBoardroom(String id)
	{	
		StringBuilder sb=new StringBuilder();
		sb.append("delete from boardroom where id =? ");
		return this.executeUpdate(sb.toString(), id);
	}
	
	/**
	 * 删除会议
	 * @param id
	 * @return
	 */
	public boolean deleteBespeak(String id,String truename)
	{	
		StringBuffer s = new StringBuffer();
		s.append("select * from boardroom_bespeak t where id = " + id + " and truename='" + truename + "'");
		 
		List<Map<String, Object>> list =  this.getList_Pre(s.toString());
		if(list != null && list.size() != 0){
		StringBuilder sb=new StringBuilder();
		sb.append("delete from boardroom_bespeak where id =? ");
		return this.executeUpdate(sb.toString(), id);
		}else{
			return false;
		}
	}
	
	/**
	 * 根据会议室状态获取会议室信息
	 * @return
	 */
	public List<Map<String, Object>> queryBoardroomByStatus()
	{   
		
		StringBuffer sb = new StringBuffer();
		sb.append("select * from boardroom t where boardroom_status= '1'");
		return this.getList_Pre(sb.toString());
	}
	
	public List<Map<String, Object>> queryBoardroomByName(String bname)
	{   
		
		StringBuffer sb = new StringBuffer();
		sb.append("select * from boardroom_bespeak b where boardroom_name= '"+ bname +"' and end_time >= NOW()");
		return this.getList_Pre(sb.toString());
	}
	
	/**
	 * 会议预约
	 * @param map
	 * @return
	 * @throws ParseException 
	 */
	public boolean bespeakBoardroom(Map<String, Object> map) throws ParseException{
		
		String Boardroom = StringHelper.noEmpty2(map.get("Boardroom"));
		String startTime = StringHelper.noEmpty2(map.get("start_time"));
		String end_time = StringHelper.noEmpty2(map.get("end_time"));
		StringBuffer s = new StringBuffer();
		s.append("select * from boardroom_bespeak t where boardroom_name = '" + Boardroom + "' "
				+ "and (('" + startTime + "' between start_time and end_time) or ('" + end_time + "' between start_time and end_time) or ( '" + startTime + "'<=start_time"
						+ " and '" + end_time +"' >=end_time))");
		 
		List<Map<String, Object>> list =  this.getList_Pre(s.toString());
	
			if(list == null || list.size() == 0){
		StringBuilder sb=new StringBuilder();
		sb.append("insert into boardroom_bespeak(boardroom_name,meeting_name,start_time,end_time,truename,org_name,bespeak_time,meeting_staff) "
				+ "values(?,?,?,?,?,?,?,?) ");
		Object[] object = new Object[8];
		
		object[0] = StringHelper.noEmpty2(map.get("Boardroom")).trim();
		object[1] = StringHelper.noEmpty2(map.get("meeting_name")).trim();
		object[2] = StringHelper.noEmpty2(map.get("start_time")).trim();
		object[3] = StringHelper.noEmpty2(map.get("end_time")).trim();
		object[4] = StringHelper.noEmpty2(map.get("truename")).trim();
		object[5] = StringHelper.noEmpty2(map.get("org_name")).trim();
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		object[6] = format.format(date); 
		object[7] = StringHelper.noEmpty2(map.get("meeting_staff")).trim();
		return this.executeUpdate(sb.toString(), object);
			}else{
			return false;
			}
		}
			
		
	
		
	/**
	 * 获取会议预约列表
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public OraPaginatedList myMeetingBespeak(Map<String, Object> map,int pageNum,int pageSize) 
	{
		String condition=this.getDesSql(map);
		String conditionP = "";
		String param = "";
		if(map!= null && !StringHelper.noEmpty2(map.get("boardroom_name")).trim().equals(""))
		{
			
			param += " and a.boardroom_name like concat('%',?,'%') ";
			conditionP += StringHelper.noEmpty2(map.get("boardroom_name")).trim() + ",";
		}
		if(map!= null && !StringHelper.noEmpty2(map.get("meeting_name")).trim().equals(""))
		{
			param += " and a.meeting_name like concat('%',?,'%') ";
			conditionP += StringHelper.noEmpty2(map.get("meeting_name")).trim() + ",";
		}
		if(map!= null && !StringHelper.noEmpty2(map.get("start_time")).trim().equals(""))
		{   
			param += " and date_format(a.start_time, '%Y-%m-%d %H:%i:%s') >= ?";
			conditionP += StringHelper.noEmpty2(map.get("start_time")).trim() + ",";	
		}		
		if(map!= null && !StringHelper.noEmpty2(map.get("start_time2")).trim().equals(""))
		{
			param += " and date_format(a.start_time, '%Y-%m-%d %H:%i:%s') <= ?";
			conditionP += StringHelper.noEmpty2(map.get("start_time2")).trim() + ",";
		}		
		String sql = "select a.* from boardroom_bespeak a  "
                +" where 1=1  "+param ;
		
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
	
}
