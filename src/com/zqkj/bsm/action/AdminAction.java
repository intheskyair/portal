package com.zqkj.bsm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cudatec.flow.framework.dao.TaskManageDao;
import com.zqkj.bsm.client.dao.ClientDao;
import com.zqkj.bsm.dal.bean.Admin;
import com.zqkj.bsm.log.dao.LogoptDao;
import com.zqkj.bsm.system.action.dao.Multi_Fram_Tab;
import com.zqkj.bsm.system.action.dao.SysUserDao;
import com.zqkj.bsm.system.action.dao.XtdoroleDao;
import com.zqkj.bsm.system.action.session.SessionBean;
import com.zqkj.bsm.system.action.session.StoreFactory;
import com.zqkj.bsm.util.BHUtil;
import com.zqkj.bsm.util.ClientInfo;
import com.zqkj.bsm.util.ClientInfoUtils;
import com.zqkj.bsm.util.CommonUtil;

/**
 * 管理员登录退出等操作类
 * @author 0242
 * @date Jul 17, 2014
 */
/**
 * 使用注解来配置Action
 * 
 */
@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results({@Result(name = "main", location = "/views/shell/desktop.jsp"),
    @Result(name = "onlineinfo", location = "/WEB-INF/jsp/system/onlineinfo.jsp"),
    @Result(name = "createAdminPage", location = "/WEB-INF/pages/systemManage/create_admin.jsp"),
    @Result(name = "todesk", location = "/model_pc/desk.jsp"), @Result(name = "error", location = "/failure.jsp"),
    @Result(name = "noauth", location = "/notAuth.jsp"), @Result(name = "logout", location = "/index.jsp")})
@ExceptionMappings({@ExceptionMapping(exception = "java.lange.RuntimeException", result = "error")})
@Action(value = "admin")
public class AdminAction extends BaseAction
{
    private String url;
    
    private String msg;
    
    public String getMsg()
    {
        return msg;
    }
    
    public void setMsg(String msg)
    {
        this.msg = msg;
    }
    
    public String getUrl()
    {
        return url;
    }
    
    public void setUrl(String url)
    {
        this.url = url;
    }
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 登录系统
     * 
     * @author 0242
     * @date Jul 17, 2014
     * @return
     */
    public String login()
    {
        // if (session.getAttribute("admin") != null)
        // {
        // return "main";
        // }
        String ip = getIpAddr();
        String name = request.getParameter("name");
        String psw = request.getParameter("password");
        // if( null == name || null == psw || "".equals(name) || "".equals(psw))
        // {
        // request.setAttribute("errorMessage", "请输入账号密码");
        // return "logout";
        // }
        if (null == name || "".equals(name))
        {
            request.setAttribute("errorMessage", "请输入账号");
            return "logout";
        }
        // 判断登录次数和IP
        // if(BHUtil.getNOTE_LIST().size() > 0)
        // {
        // int unlock_time = Integer.parseInt(CommonUtil.nullToDefault(ConfigUtil.getValue("login_times"),"10"));
        // if(BHUtil.isMore(ip, name)){
        // request.setAttribute("errorMessage", "登录次数过多，请"+ unlock_time +"分钟后再试");
        // return "logout";
        // }
        // }
        List<String[]> list = new ArrayList<String[]>();
        list.add(new String[] {ip, name, String.valueOf(0)});
        // AdminOpera adminOpera = AdminOpera.getInstance();
        LogoptDao optdao = LogoptDao.getInstance();
        // Admin admin = adminOpera.readAdminByName(name);
        Admin admin = ClientDao.getInstance().getAdminInfo(name);
        // if (admin != null && admin.getPassword().equals(CookieUtil.getMD5(psw)))
        if (admin != null)
        {
            if (admin.getStatus() == 0)
            {
                // 暂时获取一个权限
                Map<String, Object> map = SysUserDao.getInstance().queryUseRole(String.valueOf(admin.getAdminId()));
                if (map != null)
                {
                    Long AUTH_ID = Long.valueOf(String.valueOf(map.get("AUTH_ID")));
                    admin.setAuth_name(String.valueOf(map.get("AUTHNAME")));
                    admin.setPrivilegeId(AUTH_ID);
                    admin.setLogin_tp(0);
                }
                // 获得客户端信息
                String agent = ServletActionContext.getRequest().getHeader("user-agent");
                String sessionid = ServletActionContext.getRequest().getSession().getId();
                ClientInfo ci = ClientInfoUtils.getClientInfo(agent);
                String userbrowser = ci.getBrowserInfo();
                // String useros = ci.getOsInfo();
                String useros = "运维";
                // String ipaddr = request.getRemoteAddr();
                String ipaddr = ClientInfoUtils.getIP(request);
                // 登录日志
                optdao.login(String.valueOf(admin.getAdminId()),
                    String.valueOf(admin.getName()),
                    ipaddr,
                    userbrowser,
                    useros,
                    sessionid,
                    "",
                    "");
                log.info("用户登录成功：" + String.valueOf(admin.getName()) + "[]，来自：" + ipaddr);
                // BHUtil.reMoveLockUser(ip, name, 0);
                session.setAttribute("admin", admin);
                return "main";
            }
            else
            {
                request.setAttribute("errorMessage", "用户被冻结");
                log.info("用户登录失败：" + String.valueOf(admin.getName()) + "用户被冻结");
                return "logout";
            }
        }
        // else if(admin == null)
        else
        {
            // 限制IP与账号
            BHUtil.setNOTE_LIST(list);
            request.setAttribute("errorMessage", "用户不存在");
            log.info("用户登录失败：" + name + "用户不存在");
            return "logout";
        }
        // else if(admin != null && !admin.getPassword().equals(CookieUtil.getMD5(psw)))
        // {
        // //限制IP与账号
        // BHUtil.setNOTE_LIST(list);
        // request.setAttribute("errorMessage", "用户密码错误");
        // log.info("用户登录失败："+String.valueOf(admin.getName())+"用户密码错误");
        // return "logout";
        // }
        // else
        // {
        // return "logout";
        // }
        
    }
    
    /***
     * 获得客户端IP地址
     * 
     * @return
     */
    private String getIpAddr()
    {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP ");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    
    /**
     * 更新每次登录的间隔时间
     * 
     * @param admin
     * @param last_failure
     * @return
     */
    // private boolean update_Last_Failure(Admin admin,int last_failure){
    // LogoptDao optdao = LogoptDao.getInstance();
    // return optdao.updateLast_Failure(admin, last_failure);
    // }
    
    /**
     * 退出系统
     * 
     * @author 0242
     * @date Jul 17, 2013
     * @return
     */
    public String logout()
    {
        String ipaddr = request.getRemoteAddr();
        String sessionid = session.getId();
        Admin admin = (Admin)session.getAttribute("admin");
        session.removeAttribute("admin");
        session.invalidate();
        if (admin != null)
        {
            // 登录日志
            LogoptDao optdao = LogoptDao.getInstance();
            optdao.logout(sessionid);
            log.info("用户退出成功sessionid=：" + sessionid + "username=" + admin.getName() + "来自：" + ipaddr);
        }
        return "logout";
    }
    
    public String toSucess()
    {
        if (msg != null)
        {
            msg = ToCN(msg);
        }
        return "success";
    }
    
    /**
     * 新增后台用户页面
     * 
     * @return
     */
    public String createAdminPage()
    {
        // List<Privilege> privileges = PrivilegeOpera.getInstance().readPrivilegeAll();
        List<Map<String, Object>> privileges = XtdoroleDao.getInstance().allrole();
        request.setAttribute("privileges", privileges);
        return "createAdminPage";
    }
    
    /**
     * 在编辑菜单过程，获得已经选定的操作按钮
     */
    public void readButtons()
        throws IOException
    {
        response.setCharacterEncoding("UTF-8");
        String menu_id = request.getParameter("menu_id");
        JSONObject jsonEntity = new JSONObject();
        if (menu_id != null)
        {
            SysUserDao sud = SysUserDao.getInstance();
            List<Map<String, Object>> items = sud.queryButtons(menu_id);
            String operaButton = "";
            String[] operaButtons = new String[items.size()];
            for (int i = 0, len = items.size(); i < len; i++)
            {
                Map<String, Object> map = items.get(i);
                operaButton = map.get("BUTTON_NAME") == null ? "" : String.valueOf(map.get("BUTTON_NAME"));
                operaButtons[i] = operaButton;
            }
            jsonEntity.put("button_name", JSONArray.fromObject(operaButtons));
        }
        PrintWriter out = response.getWriter();
        out.print(jsonEntity);
    }
    
    /**
     * 节点温度告警，菜单标红，并且发送短信
     */
    public String warningcheck()
    {
        // HashMap<String,String> map_return = new HashMap<String,String>();
        // if((Boolean)SysPara.exceptionMap.get("warning"))
        // {
        // map_return.put("device", "正常");
        // }
        // else
        // {
        // map_return.put("device", "异常");
        // }
        // writeJson(response, map_return);
        return null;
    }
    
    /**
     * 获取多农场tab
     * 
     * @return
     */
    public String getFram_Tab()
    {
        Multi_Fram_Tab mft = Multi_Fram_Tab.getInstance();
        List<Map<String, Object>> framList = mft.getFram(0); // 0是有效，1是失效
        writeJson(response, framList);
        return null;
    }
    
    /**
     * 获取多农场tab
     * 
     * @return
     */
    public void getAllRoles()
    {
        List<Map<String, Object>> privileges = XtdoroleDao.getInstance().allrole();
        writeJson(response, privileges);
    }
    
    /**
     * 739ba060ba08406abab4aa1fa0ae6346 收文 8289579875e346da8ddc563bacf23081 发文
     * 
     * @return
     */
    public String todesk()
    {
        Admin admin = (Admin)request.getSession().getAttribute("admin");
        // 此处为手动添加流程ID
        Object[] obj =
            new Object[] {admin.getName(), "739ba060ba08406abab4aa1fa0ae6346", "8289579875e346da8ddc563bacf23081"};
        List<Map<String, Object>> list = TaskManageDao.getInstance().getTodesk(obj);
        for (Map<String, Object> map : list)
        {
            if (map.get("PROCESS_ID").equals("739ba060ba08406abab4aa1fa0ae6346"))
            {
                request.setAttribute("swCount", map.get("COUNT1"));
            }
            else if (map.get("PROCESS_ID").equals("8289579875e346da8ddc563bacf23081"))
            {
                request.setAttribute("fwCount", map.get("COUNT1"));
            }
        }
        return "todesk";
    }
    
    public static String ToCN(String str)
    {
        String strcn = null;
        try
        {
            strcn = new String(str.getBytes("iso-8859-1"), "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return strcn;
    }
    
    public void zjlogout()
    {
        String name = request.getParameter("name") == null ? "admin" : request.getParameter("name");
        String ipaddr = request.getRemoteAddr();
        Admin admin = ClientDao.getInstance().getAdminInfo(name);
        if (admin != null)
        {
            Map<String, String> map = StoreFactory.getStore().getUserMap();
            String session_key_map = map.get(String.valueOf(admin.getAdminId()));
            if (session_key_map != null && !session_key_map.equals(""))
            {
                SessionBean sessionbean = StoreFactory.getStore().getSessionMap().get(session_key_map);
                HttpSession session_last = sessionbean.getSession();
                if (session_last != null)
                {
                    session_last.invalidate();
                    StoreFactory.getStore().getSessionMap().remove(session_key_map);
                    
                }
                LogoptDao optdao = LogoptDao.getInstance();
                optdao.logout(session_key_map);
                log.info("用户退出成功sessionid=：" + session_key_map + "username=" + admin.getName() + "来自：" + ipaddr);
            }
        }
    }
    
    public String zjlogin()
    {
        // if (session.getAttribute("admin") != null)
        // {
        // String mk = CommonUtil.nullToDefault(request.getParameter("mk"), "");
        // request.setAttribute("mk", mk);
        // return "main";
        // }
        log.info("从主框架进入url：" + request.getRequestURI());
        String ip = getIpAddr();
        String name = request.getParameter("name") == null ? "" : request.getParameter("name");
        log.info("从主框架进入name：" + request.getParameter("name"));
        // String name= "admin";
        String psw = request.getParameter("password");
        String mk = CommonUtil.nullToDefault(request.getParameter("mk"), "");
        // if( null == name || null == psw || "".equals(name) || "".equals(psw))
        // {
        // request.setAttribute("errorMessage", "请输入账号密码");
        // return "logout";
        // }
        if (null == name || "".equals(name))
        {
            request.setAttribute("errorMessage", "请输入账号");
            return "noauth";
        }
        // 判断登录次数和IP
        // if(BHUtil.getNOTE_LIST().size() > 0)
        // {
        // int unlock_time = Integer.parseInt(CommonUtil.nullToDefault(ConfigUtil.getValue("login_times"),"10"));
        // if(BHUtil.isMore(ip, name)){
        // request.setAttribute("errorMessage", "登录次数过多，请"+ unlock_time +"分钟后再试");
        // return "logout";
        // }
        // }
        List<String[]> list = new ArrayList<String[]>();
        list.add(new String[] {ip, name, String.valueOf(0)});
        // AdminOpera adminOpera = AdminOpera.getInstance();
        LogoptDao optdao = LogoptDao.getInstance();
        // Admin admin = adminOpera.readAdminByName(name);
        Admin admin = ClientDao.getInstance().getAdminInfo(name.trim());
        // if (admin != null && admin.getPassword().equals(CookieUtil.getMD5(psw)))
        if (admin != null)
        {
            if (admin.getStatus() == 0)
            {
                // 暂时获取一个权限
                Map<String, Object> map = SysUserDao.getInstance().queryUseRole(String.valueOf(admin.getAdminId()));
                if (map != null)
                {
                    Long AUTH_ID = Long.valueOf(String.valueOf(map.get("AUTH_ID")));
                    admin.setAuth_name(String.valueOf(map.get("AUTHNAME")));
                    admin.setPrivilegeId(AUTH_ID);
                    admin.setLogin_tp(0);
                }
                // 获得客户端信息
                String agent = ServletActionContext.getRequest().getHeader("user-agent");
                String sessionid = ServletActionContext.getRequest().getSession().getId();
                ClientInfo ci = ClientInfoUtils.getClientInfo(agent);
                String userbrowser = ci.getBrowserInfo();
                // String useros = ci.getOsInfo();
                String useros = "运维";
                // String ipaddr = request.getRemoteAddr();
                String ipaddr = ClientInfoUtils.getIP(request);
                // 登录日志
                optdao.login(String.valueOf(admin.getAdminId()),
                    String.valueOf(admin.getName()),
                    ipaddr,
                    userbrowser,
                    useros,
                    sessionid,
                    "",
                    "");
                log.info("用户登录成功：" + String.valueOf(admin.getName()) + "[]，来自：" + ipaddr);
                BHUtil.reMoveLockUser(ip, name, 0);
                session.setAttribute("admin", admin);
                request.setAttribute("mk", mk);
                return "main";
            }
            else
            {
                request.setAttribute("errorMessage", "用户被冻结");
                log.info("用户登录失败：" + String.valueOf(admin.getName()) + "用户被冻结");
                return "noauth";
            }
        }
        // else if(admin == null)
        else
        {
            // 限制IP与账号
            BHUtil.setNOTE_LIST(list);
            request.setAttribute("errorMessage", "用户不存在");
            log.info("用户登录失败：" + name + "用户不存在");
            return "noauth";
        }
        // else if(admin != null && !admin.getPassword().equals(CookieUtil.getMD5(psw)))
        // {
        // //限制IP与账号
        // BHUtil.setNOTE_LIST(list);
        // request.setAttribute("errorMessage", "用户密码错误");
        // log.info("用户登录失败："+String.valueOf(admin.getName())+"用户密码错误");
        // return "logout";
        // }
        // else
        // {
        // return "logout";
        // }
        
    }
    
}
