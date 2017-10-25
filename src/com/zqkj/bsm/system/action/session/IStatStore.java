package com.zqkj.bsm.system.action.session;
import java.util.List;
import java.util.Map;


/** */ /**   
 * 在线人数统计的接口，可以使用多种方式实现，如：内存、数据库等，只要实现这个接口就可以了。  
 *<br/>  
 *  @version       :  V1.0<br/>  
 *  @author        : gaofeng<br/>  
 * @date           : 2010-8-17 下午02:01:07<br/>  
  */    
public interface IStatStore 
{
	
	  /** */ /**   
     * 记录登录状态  
     *<br/>  
     *编码：gaofeng     2010-8-17 下午02:01:07<br/>  
     *参数及返回值：<br/>  
     *   @param  username  
      */    
     public   void  login(String username,String session_key);   
      /** */ /**   
     * 取消登录状态  
     *<br/>  
     *编码：gaofeng     时间：2008-4-3  上午10:08:17<br/>  
     *参数及返回值：<br/>  
     *   @param  username  
      */    
     public   void  logoff(String username);   
       
      /** */ /**   
     * 返回登录用户及信息。  
     *<br/>  
     *编码：gaofeng     时间：2010-8-17 下午02:01:07<br/>  
     *参数及返回值：<br/>  
     *   @return 　链表里的对象是个数组，数组包含两个元素，０：用户名，１：登录时间。这样是为了便于为jstl标签处理  
      */    
     @SuppressWarnings("unchecked")
	public  List getUsers();   
       
      /** */ /**   
     * 在线用户数量  
     *<br/>  
     *编码：gaofeng     时间2010-8-17 下午02:01:07<br/>  
     *参数及返回值：<br/>  
     *   @return   
      */    
     public   int  getCount();   


     public  Map<String, String> getUserMap();
     
     public Map<String, SessionBean> getSessionMap();
}
