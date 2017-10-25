package com.zqkj.bsm.sms.dao;
import java.util.Map;

import com.zqkj.bsm.dal.dboper.DbOperator;

public class SmsDao extends DbOperator
{

	private static  SmsDao instance=null;
	
	public static SmsDao getInstance()
	{
        if(instance ==null)
        {
            instance = new SmsDao();
        }
        return instance;
	}
	
	private SmsDao()
	{
		
	}
	
	
    public boolean addSMS(Map<String, Object> map)
    {
    	String sql =" insert into send_sms(sys_name, phone, content,returnstatus,message,remainpoint,send_time) values(?,?,?,?,?,?,now())";
    	Object[] obj = new Object[6];
    	obj[0] = map.get("sys_name");
    	obj[1] = map.get("phone");
    	obj[2] = map.get("content");
    	obj[3] = map.get("returnstatus");
    	obj[4] = map.get("message");
    	obj[5] = map.get("remainpoint");
    	return this.executeInsert(sql, obj);
    }
}
