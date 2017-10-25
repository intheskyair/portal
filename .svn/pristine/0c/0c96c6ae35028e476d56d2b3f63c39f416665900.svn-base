package com.zqkj.bsm.sms.dao;

import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import com.zqkj.bsm.dal.dboper.DbOperator;
import com.zqkj.bsm.util.CommonUtil;

public class AddressBookDao extends DbOperator
{
   private static AddressBookDao instance=null;
   
   private AddressBookDao()
   {
	   
   }
   
	public static AddressBookDao getInstance()
	{
		if(instance==null)
		{
			instance = new AddressBookDao();
		}
		return instance;
	}
	
	
	/**
	 * 已发送短信查询
	 * @param phone
	 * @param user_name
	 * @param pageNum
	 * @param pagesize
	 * @return
	 */
	
	public List<Map<String, Object>> queryBook(String phone,String user_name,int pageNum,int pagesize)  {
		String sql = "select * from  address_book where";
		sql += getSql(phone, user_name);
		sql += " limit " + (pageNum - 1) * pagesize + ", " + pagesize;
		List<Map<String, Object>> map = getList_Pre(sql.toString(),null);
		return map;
	}

	private String getSql(String phone, String user_name) {
		String condition = " 1=1";
		
		if (!StringUtils.isEmpty(phone)) {
			condition += " and phone = "+ phone ;
		}

		if(!StringUtils.isEmpty(user_name)) {
			condition += " and user_name like concat('%','"+user_name+"','%')";
		}
		return condition;
	}
	
	public int getCountOfFeature(String phone, String user_name) {
		String sql = "select * from address_book where";
		sql += getSql(phone, user_name);
		int count = getCount(sql, null);
		return count;
	}
	
	/**
	 * 更新通讯录
	 * @param param
	 * @return
	 */
	public boolean updateBook(Map<String, Object> param)
	{
		String sql = null;
		Object[] objects = new Object[5];
		objects[0] = param.get("user_name");
		objects[1] = param.get("phone");
		objects[2] = param.get("email");
		objects[3] = param.get("status");
		objects[4] = param.get("id");
		sql = "update address_book  set user_name=?,phone=?,email=?,status=? where id=?";
		return this.executeUpdate(sql, objects);
	}
	/**
	 * 新增通讯录
	 * @param param
	 * @return
	 */
	public boolean addBook(Map<String, Object> param)
	{
		String sql = null;
		Object[] objects = new Object[5];
		objects[0] = param.get("user_name");
		objects[1] = param.get("phone");
		objects[2] = param.get("email");
		objects[3] = param.get("status");
		objects[4] = param.get("id");
	    sql = "insert into address_book set user_name=?,phone=?,email=?,status=? ,id=?";
		return this.executeUpdate(sql, objects);
	}

	 /**
     * 获取已发送信息列表
     * @param param
     * @return 
     */
    public List<Map<String, Object>> getExportDara(Map<String, Object> param)
    {

        String phone = CommonUtil.nullToDefault(param.get("phone"), "");
        String user_name = CommonUtil.nullToDefault(param.get("user_name"), "");
      
        String condition = "where 1 = 1";
        String conditionP = "";
        if (!"".equals(phone)) {
            condition += " and phone = ?";
            conditionP += phone + ",";
        }
        if (!"".equals(user_name)) {
        	condition += " and  user_name like concat('%','"+user_name+"','%')";
            conditionP += user_name + ",";
        }

        String[] oArray = null;
        if (conditionP != null && !"".equals(conditionP)) {
            conditionP = conditionP.substring(0, conditionP.length() - 1);
            oArray = conditionP.split(",");
        }

        String sql =  "select * from address_book " + condition;
        return this.getList_Pre(sql.toString(),null);
    }
    
    /**
     *删除某一条已发送信息
     * @param param
     * @return 
     */
    public boolean deleteBook(long id)
    {
        String sql ="delete from address_book where id="+id;
        return this.executeUpdate(sql, null);
    }
}
