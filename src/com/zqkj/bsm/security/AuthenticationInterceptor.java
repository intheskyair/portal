package com.zqkj.bsm.security;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.dal.bean.Admin;
import com.zqkj.bsm.log.dao.LogoptDao;
import com.zqkj.bsm.system.action.SysPara;

public class AuthenticationInterceptor implements Interceptor
{
    private static final long serialVersionUID = 1L;
    
    private static ArrayList<String> list = null;
    
    public void destroy()
    {
        
    }
    
    public void init()
    {
        list = new ArrayList<String>();
        list.add("admin!zjlogin.action");
        list.add("admin!login.action");
        list.add("admin!login.action");
        list.add("admin!logout.action");
        list.add("getMenu!toTop.action");
        list.add("getMenu!getMenu.action");
        // 民主评测
        list.add("mzpc!cretateMzpc.action");
        list.add("mzpc!viewMzpc.action");
        list.add("mzpc!savepdf.action");
        list.add("mzpc!saveType3pdf.action");
        list.add("mzpc!getKHPF.action");
        list.add("mzpf!");
        // 手机端
        list.add("phone!");
    }
    
    public String intercept(ActionInvocation actionInvocation)
        throws Exception
    {
        Action action = (Action)actionInvocation.getAction();
        HttpServletRequest request =
            (HttpServletRequest)actionInvocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
        HttpServletResponse response =
            (HttpServletResponse)actionInvocation.getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
        if (action instanceof BaseAction)
        {
            String requestUrl = request.getRequestURI();
            HttpSession session = request.getSession(true);
            // 首先检查是否有session
            if (checkUrl(requestUrl))
            {
                response.setContentType("text/html;charset=UTF-8");
                Admin admin = (Admin)session.getAttribute("admin");
                String url = SysPara.rdc_url;
                if (admin == null)
                {
                    PrintWriter out = response.getWriter();
                    out.println("<a href=\"javascript:window.parent.location.href='" + url + "'\">登录已失效,请重新登录</a>");
                    out.close();
                }
                else
                {
                    String user_id = String.valueOf(admin.getAdminId());
                    this.checkAction(requestUrl, user_id);
                }
                // else
                // {
                // //检查是否已经登录
                // String session_key = session.getId();
                // String user_id = String.valueOf(admin.getAdminId());
                // Map<String, String> map = StoreFactory.getStore().getUserMap();
                // String session_key_map = map.get(user_id);
                // if(!session_key.equals(session_key_map))
                // {
                // PrintWriter out = response.getWriter();
                // out.println("<a href=\"javascript:window.parent.location.href=''\">登录已失效,其他地方登录，请重新登录</a>");
                // out.close();
                // }
                // else
                // {
                // this.checkAction(requestUrl, user_id);
                // }
                // }
                
            }
            
        }
        // else if (action instanceof ClientBaseAction)
        // {
        // String requestUrl =request.getRequestURI();
        // HttpSession session = request.getSession(true);
        // //首先检查是否有session
        // if(checkUrl(requestUrl))
        // {
        // response.setContentType("text/html;charset=UTF-8");
        // Admin admin = (Admin) session.getAttribute("admin");
        // String res_return="{\"msg\":\"用户session失效！\"}";
        // if (admin == null)
        // {
        // response.setStatus(555);
        // PrintWriter out=null;
        // try
        // {
        // out=response.getWriter();
        // out.write(res_return);
        // out.flush();
        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }finally{
        // out.close();
        // }
        // return null;
        // }
        // else
        // {
        // response.setContentType("text/html;charset=UTF-8");
        // //检查是否已经登录
        // String session_key = session.getId();
        //
        // String user_id = String.valueOf(admin.getAdminId());
        // Map<String, String> map = StoreFactory.getStore().getUserMap();
        // String session_key_map = map.get(user_id);
        // String res_return_="{\"msg\":\"用户session失效,此用户在其他地方被登录！\"}";
        // if(!session_key.equals(session_key_map))
        // {
        // response.setStatus(555);
        // PrintWriter out = response.getWriter();
        // out.println(res_return_);
        // out.close();
        // }
        // else
        // {
        // this.checkAction(requestUrl, user_id);
        // }
        // }
        // }
        // }
        return actionInvocation.invoke();
    }
    
    private boolean checkUrl(String requestUrl)
    {
        boolean retur_f = true;
        
        for (String str : list)
        {
            if (requestUrl.indexOf(str) > 0)
            {
                retur_f = false;
                break;
            }
        }
        return retur_f;
    }
    
    private boolean checkAction(String requestUrl, String user_id)
    {
        String action_name = "";
        Iterator<String> it = SysPara.actionMap.keySet().iterator();
        while (it.hasNext())
        {
            String key = (String)it.next();
            if (requestUrl.indexOf(key) > 0)
            {
                action_name = (String)SysPara.actionMap.get(key);
                break;
            }
        }
        if (!action_name.equals(""))
        {
            return LogoptDao.getInstance().insertlog_action(user_id, action_name, requestUrl);
        }
        else
        {
            return false;
        }
    }
    
}