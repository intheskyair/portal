package com.zqkj.bsm.sms.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import org.apache.commons.lang3.StringUtils;

import com.zqkj.bsm.dal.dboper.DbOperator;
import com.zqkj.bsm.util.CommonUtil;

public class SendedMessageDao extends DbOperator
{
   private static SendedMessageDao instance=null;
   
   private SendedMessageDao()
   {
	   
   }
   
	public static SendedMessageDao getInstance()
	{
		if(instance==null)
		{
			instance = new SendedMessageDao();
		}
		return instance;
	}
	
	
	/**
	 * 已发送短信查询
	 * @return
	 */
	
	public List<Map<String, Object>> queryMessage(String phone,String send_time1,String send_time2,int pageNum,int pagesize)  {
		String sql = "select * from  send_sms where";
		sql += getSql(phone, send_time1,send_time2);
		sql += " limit " + (pageNum - 1) * pagesize + ", " + pagesize;
		List<Map<String, Object>> map = getList_Pre(sql.toString(),null);
		return map;
	}
	
	/**
	 * 短信条数
	 * @return
	 */
	public int getCountOfFeature(String phone, String send_time1,String send_time2) {
		String sql = "select * from send_sms where";
		sql += getSql(phone, send_time1,send_time2);
		int count = getCount(sql, null);
		return count;
	}

	private String getSql(String phone, String send_time1,String send_time2) {
		String condition = " phone is not null ";
		
		if (!StringUtils.isEmpty(phone)) {
			condition += " and phone = "+ phone ;
		}
		if(!StringUtils.isEmpty(send_time1)) {
			condition += " and date_format(send_time,'%Y-%m-%d') >=  '"+ send_time1+"'" ;
		}
		if(!StringUtils.isEmpty(send_time2)) {
			condition += " and date_format(send_time,'%Y-%m-%d') <= '" + send_time2+"'";
		}
		condition += " order by send_time desc";
		return condition;
	}
	
	/**
	 * 已发送邮件查询
	 * @return
	 */
	public List<Map<String, Object>> queryEmail(String sender, String receive,String state, int dateType, String preDate, String postDate,
			int pageNum, int pageSize, String sortName, String sortOrder) {
		String param = "" ;
		Object[] object = new Object[3];
		if(sender!= null && !sender.trim().equals("")){
			param += " and esm.sender like concat('%',?,'%')";
        	object[0]=sender;
		}else{
			param += " and 1 = ? ";
			object[0]= null;
		}
		 if (receive!= null && !receive.trim().equals("")) {
			 param += " and esm.receive like concat('%',?,'%')";
			 object[1]= receive;
         }else{
        	 param += " and 2 = ?";
        	 object[1]= null;
         }
         if (state!= null && !state.trim().equals("")) {
        	 param += " and esm.state=?";
        	 object[2]= state;
         }else{
        	 param += " and 3 = ?";
        	 object[2]= null;
         }
         String sql = "SELECT esm.id,esm.sender,esm.title,esm.content,esm.send_time,esm.receive,esm.state FROM email_send_main esm where 1=1 " + param;
         sql += getSql(dateType,preDate,postDate);
         if (!"undefined".equals(sortName) && !"".equals(sortName)) {
        	 sql +=  " order by " + sortName;
             if ("desc".equals(sortOrder) || "asc".equals(sortOrder)) {
            	 sql += " " +sortOrder;
             } else {
            	 sql += " desc";
             }
         } else {
        	 sql += " order by esm.send_time desc";
         }
 		sql +=" limit " + (pageNum - 1) * pageSize + ", " + pageSize;
		return this.getList_Pre(sql, object);
	}
	
	private String getSql( int dateType, String preDate,String postDate) {
		String condition = "" ;
        switch (dateType) {
        case 1 :
            condition += " and to_days(date_format(esm.send_time, '%Y-%m-%d'))=to_days(now())";
            break;
        case 2 :
            condition += " and yearweek(date_format(esm.send_time, '%Y-%m-%d'))=yearweek(now())";
            break;
        case 3 :
            condition += " and date_format(esm.send_time, '%Y-%m')=date_format(now(), '%Y-%m')";
            break;
        case 4 :
            if (!StringUtils.isEmpty(preDate)) {
            	condition += " and date_format(esm.send_time,'%Y-%m-%d') >=  '"+ preDate+"'";
            }
            if (!StringUtils.isEmpty(postDate)) {
            	condition += " and date_format(esm.send_time,'%Y-%m-%d') <=  '"+ postDate+"'";
            }
            break;
        }
		return condition;
	}

	/**
	 * 邮件条数
	 * @return
	 */
	public int getCountOfEmail(String sender, String receive, String state,int dateType, String preDate, String postDate) {
		String param = "" ;
		Object[] object = new Object[3];
		if(sender!= null && !sender.trim().equals("")){
			param += " and esm.sender like concat('%',?,'%')";
        	object[0]=sender;
		}else{
			param += " and 1 = ? ";
			object[0]= null;
		}
		 if (receive!= null && !receive.trim().equals("")) {
			 param += " and esm.receive like concat('%',?,'%')";
			 object[1]= receive;
         }else{
        	 param += " and 2 = ?";
        	 object[1]= null;
         }
         if (state!= null && !state.trim().equals("")) {
        	 param += " and esm.state=?";
        	 object[2]= state;
         }else{
        	 param += " and 3 = ?";
        	 object[2]= null;
         }
         String sql = "SELECT esm.id,esm.sender,esm.title,esm.content,esm.send_time,esm.receive,esm.state FROM email_send_main esm where 1=1 " + param;
         sql += getSql(dateType,preDate,postDate);
         int count = getCount(sql, object);
         return count;
	}

	 /**
     * 获取已发送信息列表
     * @param param
     * @return 
     */
    public List<Map<String, Object>> getExportDara(Map<String, Object> param)
    {

        String phone = CommonUtil.nullToDefault(param.get("phone"), "");
        String send_time1 = CommonUtil.nullToDefault(param.get("send_time1"), "");
        String send_time2 = CommonUtil.nullToDefault(param.get("send_time2"), "");
      
        String condition = "where 1 = 1";
        String conditionP = "";
        if (!"".equals(phone)) {
            condition += " and phone = ?";
            conditionP += phone + ",";
        }
        if (!"".equals(send_time1)) {
            condition += "and date_format(send_time,'%Y-%m-%d') >= ? ";
            conditionP += send_time1 + ",";
        }
        if (!"".equals(send_time2)) {
            condition += " and date_format(send_time,'%Y-%m-%d') <=? ";
            conditionP += send_time2 + ",";
        }
        condition += " order by send_time desc";
        String[] oArray = null;
        if (conditionP != null && !"".equals(conditionP)) {
            conditionP = conditionP.substring(0, conditionP.length() - 1);
            oArray = conditionP.split(",");
        }

        String sql =  "select id,sys_name,phone,content,send_time,returnstatus,message from send_sms " + condition;

        return this.getList_Pre(sql, oArray);
    }
    
    /**
     *删除某一条已发送信息
     * @param param
     * @return 
     */
    public boolean deleteSms(long id)
    {
        String sql ="delete from send_sms where id="+id;
        return this.executeUpdate(sql, null);
    }
    
	/**
	 * 删除id对应的邮件信息
	 * @param ids
	 * @return
	 */
	public boolean deleteEmail(JSONArray ids) {
  		ArrayList<String> list = new ArrayList<String>();
  		for(int i=0;i<ids.size();i++)
  		{
  			String sql ="delete from email_send_main where id=" +ids.getString(i);
  			list.add(sql);
  		}
  		return this.executeBatch(list);
	}

	/**
	 * 导出邮件数据
	 * @return
	 */
	public List<Map<String, Object>> exportEmail(String sender, String receive,String state, int dateType, String preDate, String postDate) {
		String param = "" ;
		Object[] object = new Object[3];
		if(sender!= null && !sender.trim().equals("")){
			param += " and esm.sender like concat('%',?,'%')";
        	object[0]=sender;
		}else{
			param += " and 1 = ? ";
			object[0]= null;
		}
		 if (receive!= null && !receive.trim().equals("")) {
			 param += " and esm.receive like concat('%',?,'%')";
			 object[1]= receive;
         }else{
        	 param += " and 2 = ?";
        	 object[1]= null;
         }
         if (state!= null && !state.trim().equals("")) {
        	 param += " and esm.state=?";
        	 object[2]= state;
         }else{
        	 param += " and 3 = ?";
        	 object[2]= null;
         }
         String sql = "SELECT esm.id,esm.sender,esm.title,esm.content,esm.send_time,esm.receive,case when  esm.state = 02 then '成功'"
         		+ " when esm.state = 03 then '失败'  end state FROM email_send_main esm where 1=1 " + param;
         sql += getSql(dateType,preDate,postDate);
         sql += " order by esm.send_time desc";
         return this.getList_Pre(sql, object);
	}

}
