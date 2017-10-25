package com.zqkj.bsm.Lock;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;


public class LockManImpl implements LockManager 
{

	public static  ConcurrentHashMap<String,Lock> lockMap=null;
    static 
    {          
    	lockMap = new ConcurrentHashMap<String,Lock>(); 
    }  
    
	/* (non-Javadoc)
	 * @see com.sgz.bsm.Lock.LockManager#getLockMap()
	 */
	public Map<String, Lock> getLockMap() {
		return lockMap;
	}
	
    /* (non-Javadoc)
	 * @see com.sgz.bsm.Lock.LockManager#getCount()
	 */
    public int getCount()
    {   
        return lockMap.size();   
    }
    
	/* (non-Javadoc)
	 * @see com.sgz.bsm.Lock.LockManager#addlock(java.lang.String, java.util.concurrent.locks.Lock)
	 */
	public  void  addlock(String user_id,Lock lock)
    {    
		lockMap.put(user_id, lock);//如果存在，会覆盖已有的值   
    }   
  
    /* (non-Javadoc)
	 * @see com.sgz.bsm.Lock.LockManager#removelock(java.lang.String)
	 */
    public  void   removelock(String user_id)
    {   
    	lockMap.remove(user_id);   
    }   
}
