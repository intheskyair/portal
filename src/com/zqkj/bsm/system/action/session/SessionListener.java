package com.zqkj.bsm.system.action.session;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zqkj.bsm.dal.bean.Admin;
import com.zqkj.bsm.log.dao.LogoptDao;

/** */
/**
 * session监听器，通过监听器来实现在线用户统计功能的类<br/>
 * 
 * @version : V1.0<br/>
 * @author : gaofeng<br/>
 * @date : 2010-8-17 上午09:41:11<br/>
 */
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener
{
    protected final Log logger = LogFactory.getLog(getClass());
    
    /** */
    /**
     * 这个值应该根据不同的应用，改成不同的值
     */
    private static final String LOGIN = "admin";
    
    /** */
    /**
     * 功能描述： 编码时间：2010-8-17 上午09:41:11 参数及返回值：
     * 
     * @param arg0
     * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)
    {
        // 时间: 2008-3-25 上午09:41:11
        // logger.info( " Session创建： " + se.getSession().getId());
        
    }
    
    /** */
    /**
     * 功能描述：session销毁监控，销毁时注销用户 编码时间：2010-8-17 上午09:41:11 上午09:41:11 参数及返回值：
     * 
     * @param arg0
     * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)
    {
        
        Admin name = (Admin)se.getSession().getAttribute(LOGIN);
        if (name != null)
        {
            String session_key = se.getSession().getId();
            String user_id = String.valueOf(name.getAdminId());
            Map<String, String> map = StoreFactory.getStore().getUserMap();
            String session_key_map = map.get(user_id);
            if (session_key.equals(session_key_map))
            {
                logger.info(" Session销毁： " + se.getSession().getId());
                StoreFactory.getStore().logoff(user_id);
                LogoptDao optdao = LogoptDao.getInstance();
                optdao.logout(session_key);
            }
            
            StoreFactory.getStore().getSessionMap().remove(session_key);
            se.getSession().invalidate();
            
        }
    }
    
    /** */
    /**
     * 
     * 功能描述：监控用户登录。有登录的，记录其状态。 编码时间：2010-8-17 上午09:41:11 参数及返回值：
     * 
     * @param event
     * @see javax.servlet.http.HttpSessionAttributeListener#attributeAdded(javax.servlet.http.HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent event)
    {
        // 时间: 2008-3-25 上午09:55:42
        String name = event.getName();
        HttpSession session = event.getSession();
        String session_key = event.getSession().getId();
        if (LOGIN.equals(name))
        {
            Admin admin = (Admin)event.getValue();
            String user_id = String.valueOf(admin.getAdminId());
            Map<String, String> map = StoreFactory.getStore().getUserMap();
            String session_key_map = map.get(user_id);
            if (session_key_map != null && !session_key_map.equals(""))
            {
                String login_str = "";
                SessionBean sessionbean = StoreFactory.getStore().getSessionMap().get(session_key_map);
                HttpSession session_last = sessionbean.getSession();
                if (sessionbean.getLogin_tp() == 0)
                {
                    login_str = "运维用户";
                }
                else
                {
                    login_str = "客户端用户";
                }
                // if (session_last != null)
                // {
                // session_last.invalidate();
                // logger.info(login_str + admin.getAdminId() + "已经登陆过，上次的 Sessionid： " + session_key_map
                // + "对上次的Session进行失效处理");
                // StoreFactory.getStore().getSessionMap().remove(session_key_map);
                //
                // }
            }
            // SessionBean sessionbean = new SessionBean();
            // sessionbean.setLogin_tp(admin.getLogin_tp());
            // sessionbean.setSession(session);
            // sessionbean.setUser_id(user_id);
            // StoreFactory.getStore().login(String.valueOf(admin.getAdminId()), session_key);
            // StoreFactory.getStore().getSessionMap().put(session_key, sessionbean);
            logger.info("有用户登录： " + admin.getAdminId() + " Sessionid： " + event.getSession().getId());
            
        }
    }
    
    public void attributeRemoved(HttpSessionBindingEvent event)
    {
        // 时间: 2010-8-17 上午09:41:11
        String name = event.getName();
        String session_key = event.getSession().getId();
        if (LOGIN.equals(name))
        {
            Admin admin = (Admin)event.getValue();
            if (admin != null)
            {
                String user_id = String.valueOf(admin.getAdminId());
                Map<String, String> map = StoreFactory.getStore().getUserMap();
                String session_key_map = map.get(user_id);
                String login_str = "";
                if (admin.getLogin_tp() == 0)
                {
                    login_str = "运维用户";
                }
                else
                {
                    login_str = "客户端用户";
                }
                // if (session_key.equals(session_key_map))
                // {
                logger.info(login_str + "有用户登出：session_id= " + session_key + "user_id="
                    + String.valueOf(admin.getAdminId()));
                StoreFactory.getStore().logoff(user_id);
                LogoptDao optdao = LogoptDao.getInstance();
                optdao.logout(session_key);
                // }
                StoreFactory.getStore().getSessionMap().remove(session_key);
                
            }
            
        }
    }
    
    public void attributeReplaced(HttpSessionBindingEvent event)
    {
        String name = event.getName();
        HttpSession session = event.getSession();
        // String session_key = event.getSession().getId();
        if (LOGIN.equals(name))
        {
            Admin admin = (Admin)event.getValue();
            logger.info("有用户再次登录： " + admin.getAdminId() + " Sessionid： " + session.getId());
            // 时间: 2010-8-17 上午09:41:11
        }
    }
    
}
