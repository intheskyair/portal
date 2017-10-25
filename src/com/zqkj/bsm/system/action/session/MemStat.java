package com.zqkj.bsm.system.action.session;

//import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MemStat implements IStatStore 
{
	//private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static  Map<String,String> userMap=null;   
	private static  Map<String,SessionBean> sessionMap=null;
    static 
    {   
        userMap = Collections.synchronizedMap(new HashMap<String,String>());         
        sessionMap = Collections.synchronizedMap(new HashMap<String,SessionBean>()); 
    }   
  
    @SuppressWarnings("unchecked")
	public  void  login(String username,String session_key)
    {   
        //时间: 2010-8-17 上午10:09:13   
        userMap.put(username, session_key);//如果存在，会覆盖已有的值   
    }   
  
    public  void   logoff(String username)
    {   
        //时间: 2010-8-17 上午10:09:13   
    	//System.out.println("清除下线用户："+username);
        userMap.remove(username);   
    }   
  
    @SuppressWarnings("unchecked")
	public List getUsers()
    {   
        //时间: 2010-8-17 上午10:23:34   
        List list = new LinkedList();   
        String user = null;   
        for(Iterator it = userMap.keySet().iterator();it.hasNext();)
        {   
            user = (String) it.next();   
            list.add(new Object[]{user,userMap.get(user)});   
        }   
        return list;   
    }   
  
    public int getCount()
    {   
        //时间: 2010-8-17 下午01:53:25   
        return userMap.size();   
    }

	public Map<String, String> getUserMap() 
	{
		return userMap;
	}

	public Map<String, SessionBean> getSessionMap() {
		return sessionMap;
	}

	
}
