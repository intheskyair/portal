package com.zqkj.bsm.system.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cudatec.render.util.Configuration;
import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.dal.bean.Admin;
import com.zqkj.bsm.dal.client.AdminOpera;
import com.zqkj.bsm.slProject.dao.ZtbDao;
import com.zqkj.bsm.system.action.dao.SysUserDao;
import com.zqkj.bsm.system.action.dao.XtdoroleDao;
import com.zqkj.bsm.system.action.page.OraPaginatedList;
import com.zqkj.bsm.system.action.util.StringHelper;
import com.zqkj.bsm.util.CommonUtil;
import com.zqkj.bsm.util.CookieUtil;
import com.zqkj.bsm.util.PageData;

/**
 * 用户管理相关操作
 * 
 * @author 0242
 * 
 */
@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results({@Result(name = "queryUser", location = "/WEB-INF/jsp/system/userList.jsp"),
    @Result(name = "success", location = "/WEB-INF/jsp/system/doRole.jsp"),
    @Result(name = "userinfo", location = "/WEB-INF/jsp/system/userinfo.jsp"),
    @Result(name = "editUser", location = "/WEB-INF/jsp/system/userList.jsp"),
    @Result(name = "changepwd", location = "/WEB-INF/jsp/system/changepwd.jsp"),
    @Result(name = "showSyspara", location = "/WEB-INF/jsp/system/showSyspara.jsp"),
    @Result(name = "threadList", location = "/WEB-INF/jsp/system/threadList.jsp"),
    @Result(name = "result", location = "/WEB-INF/jsp/result.jsp"), @Result(name = "error", location = "/failure.jsp")})
@ExceptionMappings({@ExceptionMapping(exception = "java.lange.RuntimeException", result = "error")})
public class UserAction extends BaseAction
{
    
    private static final long serialVersionUID = -6903687779830479572L;
    
    public String currentPage;
    
    public String username;// 登陆账号
    
    public String org_infor;
    
    public String mobile;// 手机号码
    
    public String sys_id;// 工号
    
    public List<Map<String, Object>> al;// 用户列表
    
    public String editImage;
    
    public String roles;// 提交的权限集合
    
    @Action(value = "queryUser")
    public String queryUser()
        throws Exception
    {
        String operation = request.getParameter("operation");
        if ("init".equals(operation))
        {
            // 检索参数
            int pageNum = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagenum"), "1")) - 1;
            int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "30"));
            String username = request.getParameter("username") == null ? "" : request.getParameter("username");
            // 备注
            String remark = request.getParameter("remark") == null ? "" : request.getParameter("remark");
            String org_infor = request.getParameter("org_infor") == null ? "" : request.getParameter("org_infor");
            String mobile = request.getParameter("mobile") == null ? "" : request.getParameter("mobile");
            String sys_id = request.getParameter("sys_id") == null ? "" : request.getParameter("sys_id");
            String sortname = CommonUtil.nullToDefault(request.getParameter("sortname"), "");
            String sortorder = CommonUtil.nullToDefault(request.getParameter("sortorder"), "");
            String department_menu_id = CommonUtil.nullToDefault(request.getParameter("department_menu_id"), "");
            SysUserDao sud = SysUserDao.getInstance();
            // 分页封装方法
            OraPaginatedList list_tmp =
                sud.query(remark,
                    username,
                    org_infor,
                    mobile,
                    sys_id,
                    pageNum,
                    pageSize,
                    sortname,
                    sortorder,
                    department_menu_id);
            // 分页对象
            PageData pg =
                CommonUtil.fomateResult(list_tmp.getList(), pageNum + 1, pageSize, list_tmp.getFullListSize());
            writeJson(response, CommonUtil.formatFGMap(pg));
            return null;
        }
        else
        {
            return "queryUser";
        }
        
    }
    
    /**
     * 格式化需要导出的数据.
     * 
     * @param items 被格式化数据
     * @return 格式化好的数据
     */
    @SuppressWarnings("unused")
    private JSONArray formatData(List<Map<String, Object>> list)
    {
        
        JSONArray rows = new JSONArray();
        for (int i = 0; i < list.size(); i++)
        {
            Map<String, Object> item = list.get(i);
            JSONObject row = new JSONObject();
            row.put("id", i + 1);
            row.put("username", item.get("USERNAME"));
            row.put("truename", item.get("TRUENAME"));
            row.put("phone", item.get("PHONE"));
            row.put("email", item.get("EMAIL"));
            String status = CommonUtil.nullToDefault(item.get("STATUS"), "0");
            String auth = CommonUtil.nullToDefault(item.get("AUTH"), "0");
            if (status.equals("0"))
            {
                status = "正常";
            }
            else
            {
                status = "被冻结";
            }
            row.put("status", status);
            row.put("qq", item.get("QQ"));
            row.put("auth", auth);
            row.put("department", item.get("ORG_NAME"));
            row.put("org_infor", item.get("ORG_INFOR"));
            if (auth.indexOf("业务员") < 0)
            {
                row.put("rendermoney", "");
            }
            else
            {
                row.put("rendermoney", item.get("RENDERMONEY"));
            }
            row.put("area", item.get("AREA"));
            rows.add(row);
        }
        return rows;
    }
    
    /**
     * 判断用户名是否存在
     * 
     * @throws Exception
     */
    public void judgeUserName()
        throws Exception
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return = "";
        String name = CommonUtil.nullToDefault(request.getParameter("name"), "");
        long id = Long.parseLong(CommonUtil.nullToDefault(request.getParameter("id"), "0"));
        SysUserDao sud = SysUserDao.getInstance();
        if (sud.judgeUser(name, id))
        {
            res_return = "ok";
        }
        PrintWriter out = null;
        try
        {
            out = response.getWriter();
            out.write(res_return);
            out.flush();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            out.close();
        }
    }
    
    public String userinfo()
        throws Exception
    {
        String sys_id = request.getParameter("sys_id");
        // HttpSession session = request.getSession(false);
        SysUserDao sud = SysUserDao.getInstance();
        List<Map<String, Object>> list = sud.getAuths();
        ;
        Map<String, Object> map = sud.queryUser(sys_id);
        String auth = map.get("AUTH") == null ? "" : String.valueOf(map.get("AUTH"));
        String[] userauths = auth.split(",");
        request.setAttribute("map", map);
        request.setAttribute("userauths", userauths);
        request.setAttribute("list", list);
        return "userinfo";
    }
    
    public String editUser()
        throws Exception
    {
        String sys_id = request.getParameter("sys_id");
        String org_id = request.getParameter("org_id");
        String[] auths = request.getParameterValues("ck");
        // String phone = request.getParameter("phone");
        String org_name = request.getParameter("org_name");
        SysUserDao sud = SysUserDao.getInstance();
        if (sud.editUser(org_id, org_name, sys_id, auths))
        {
            request.setAttribute("info", "修改成功");
        }
        else
        {
            request.setAttribute("info", "修改失败");
        }
        return "editUser";
    }
    
    public String modifyRoles()
        throws Exception
    {
        XtdoroleDao xdd = XtdoroleDao.getInstance();
        boolean flag = xdd.modifyRoles(username, roles);
        if (flag)
        {
            return "modifyRoles";
        }
        else
        {
            return "";
        }
        
    }
    
    /*
     * getter&&setter
     */
    public String getCurrentPage()
    {
        return currentPage;
    }
    
    public void setCurrentPage(String currentPage)
    {
        this.currentPage = currentPage;
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public String getEditImage()
    {
        return "";
    }
    
    public void setEditImage(String editImage)
    {
        this.editImage = editImage;
    }
    
    public String getRoles()
    {
        return roles;
    }
    
    public void setRoles(String roles)
    {
        this.roles = roles;
    }
    
    public String getOrg_infor()
    {
        return org_infor;
    }
    
    public void setOrg_infor(String org_infor)
    {
        this.org_infor = org_infor;
    }
    
    public String getMobile()
    {
        return mobile;
    }
    
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    
    public String changepwd()
        throws Exception
    {
        log.info("菜单操作：修改密码的方法：changepwd()");
        return "changepwd";
    }
    
    public String showSyspara()
        throws Exception
    {
        String operation = request.getParameter("operation");
        if ("init".equals(operation))
        {
            // 检索参数
            Map<String, Object> param = new HashMap<String, Object>();
            int pageNum = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagenum"), "1")) - 1;
            int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "10"));
            String paraName = request.getParameter("paraName") == null ? "" : request.getParameter("paraName");
            param.put("pageNum", pageNum);
            param.put("paraName", paraName);
            param.put("pageSize", pageSize);
            SysUserDao sud = SysUserDao.getInstance();
            // 分页封装方法
            OraPaginatedList list = sud.queryParam(param);
            // 分页对象
            PageData pg = CommonUtil.fomateResult(list.getList(), pageNum + 1, pageSize, list.getFullListSize());
            writeJson(response, CommonUtil.formatFGMap(pg));
            return null;
        }
        else
        {
            return "showSyspara";
        }
    }
    
    public void addSyspara()
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return = "";
        SysUserDao userDao = SysUserDao.getInstance();
        boolean result = userDao.addSyspara(request);
        if (result)
        {
            res_return = "{msg:\"ok\"}";
            
        }
        else
        {
            res_return = "{msg:\"新增系统参数失败\"}";
        }
        PrintWriter out = null;
        try
        {
            out = response.getWriter();
            out.write(res_return);
            out.flush();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            out.close();
        }
    }
    
    public void updateSyspara()
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return = "";
        SysUserDao userDao = SysUserDao.getInstance();
        boolean result = userDao.updateSyspara(request);
        if (result)
        {
            res_return = "{msg:\"ok\"}";
            
        }
        else
        {
            res_return = "{msg:\"更新系统参数失败,可能此参数无法立即生效\"}";
        }
        PrintWriter out = null;
        try
        {
            out = response.getWriter();
            out.write(res_return);
            out.flush();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            out.close();
        }
    }
    
    public void resetpwd()
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return = "";
        String sys_id = StringHelper.noEmpty2(request.getParameter("user_id")).trim();
        SysUserDao userDao = SysUserDao.getInstance();
        boolean result = userDao.resetpwd(sys_id);
        if (result)
        {
            res_return = "{msg:\"ok\"}";
            
        }
        else
        {
            res_return = "{msg:\"重置密码失败\"}";
        }
        PrintWriter out = null;
        try
        {
            out = response.getWriter();
            out.write(res_return);
            out.flush();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            out.close();
        }
    }
    
    /**
     * 修改用户密码,根据当前的用户id修改密码
     * 
     * @throws Exception
     */
    public void updatePw()
        throws Exception
    {
        
        // 获取当前的密码和新密码
        String oldPw = CommonUtil.nullToDefault(request.getParameter("oldPw"), "");
        String newPw = CommonUtil.nullToDefault(request.getParameter("newPw"), "");
        
        // 修改密码
        Admin admin = (Admin)session.getAttribute("admin");
        SysUserDao xd = SysUserDao.getInstance();
        boolean flag = xd.changepwd(String.valueOf(admin.getAdminId()), oldPw, newPw);
        
        PrintWriter out = response.getWriter();
        if (flag)
        {
            out.print("yes");
        }
        else
        {
            out.print("no");
        }
        out.flush();
        out.close();
    }
    
    /**
     * 检测密码是否正确
     * 
     * @throws IOException
     * 
     */
    public void checkPwIsValid()
        throws IOException
    {
        String oldPw = CommonUtil.nullToDefault(request.getParameter("oldPw"), "");
        Admin admin = (Admin)session.getAttribute("admin");
        SysUserDao xd = SysUserDao.getInstance();
        boolean flag = xd.userLogin(String.valueOf(admin.getAdminId()), oldPw);
        PrintWriter out = response.getWriter();
        if (flag)
        {
            out.print("yes");
        }
        else
        {
            out.print("no");
        }
        out.flush();
        out.close();
    }
    
    public void startThread()
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return = "";
        SysPara userDao = SysPara.getInstance();
        userDao.startThread();
        res_return = "{msg:\"ok\"}";
        PrintWriter out = null;
        try
        {
            out = response.getWriter();
            out.write(res_return);
            out.flush();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            out.close();
        }
    }
    
    public void stopThread()
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return = "";
        SysPara userDao = SysPara.getInstance();
        userDao.stopThread();
        res_return = "{msg:\"ok\"}";
        PrintWriter out = null;
        try
        {
            out = response.getWriter();
            out.write(res_return);
            out.flush();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            out.close();
        }
    }
    
    @SuppressWarnings("static-access")
    public String queryThread()
        throws Exception
    {
        String operation = request.getParameter("operation");
        if ("init".equals(operation))
        {
            // 检索参数
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("thread", SysPara.getInstance().thread_start);
            writeJson(response, param);
            return null;
        }
        else
        {
            return "threadList";
        }
    }
    
    public void getversion()
        throws Exception
    {
        
        PrintWriter out = response.getWriter();
        out.print(Configuration.YW_VERSION);
        out.flush();
        out.close();
    }
    
    /**
     * 获得所有用户的下拉框
     * 
     * @throws IOException
     */
    public void getUserList()
        throws IOException
    {
        String assignee = CommonUtil.nullToDefault(request.getParameter("assignee"), "");
        String userName = CommonUtil.nullToDefault(request.getParameter("username"), "");
        String org_id = CommonUtil.nullToDefault(request.getParameter("org_id"), "");
        // gaofeng add 判断是否需要组织
        String autoOrg = CommonUtil.nullToDefault(request.getParameter("autoOrg"), "N");
        SysUserDao sud = SysUserDao.getInstance();
        if (assignee != null && !assignee.equals(""))
        {
            List<Map<String, Object>> userList = null;
            if (autoOrg.equals("Y"))
            {
                userList = sud.getUserList(userName, assignee, org_id);
            }
            else
            {
                userList = sud.getUserList(userName, assignee, null);
            }
            writeJson(response, userList);
        }
        else
        {
            writeJson(response, "[]");
        }
    }
    
    /**
     * 批量分配角色
     * 
     * @throws Exception
     */
    public void distributeAuth()
        throws Exception
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return = "";
        String admin_id = CommonUtil.nullToDefault(request.getParameter("admin_id"), "");
        String[] admin_ids = admin_id == null || "".equals(admin_id) ? null : admin_id.split(",");
        String username = CommonUtil.nullToDefault(request.getParameter("username"), "");
        String[] usernames = username == null || "".equals(username) ? null : username.split(",");
        String auth = CommonUtil.nullToDefault(request.getParameter("privilegeId"), "");
        String[] auths = auth == null || "".equals(auth) ? null : auth.split(",");
        SysUserDao sadd = SysUserDao.getInstance();
        if (sadd.updateAuth(admin_ids, usernames, auths))
        {
            res_return = "{msg:\"ok\"}";
        }
        else
        {
            res_return = "{msg:\"批量分配角色出错，数据库更新失败\"}";
        }
        PrintWriter out = null;
        try
        {
            out = response.getWriter();
            out.write(res_return);
            out.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            out.close();
        }
    }
    
    public void getThirdPartys()
    {
        SysUserDao sud = SysUserDao.getInstance();
        List<Map<String, Object>> userList = sud.getThirdPartyList();
        writeJson(response, userList);
    }
    
    public void addUser()
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return = "";
        Admin admin = new Admin();
        admin.setName(request.getParameter("name").trim());
        admin.setPassword(CookieUtil.getMD5(request.getParameter("password")));
        admin.setPhoneNum(request.getParameter("phoneNum"));
        admin.setTruename(request.getParameter("truename").trim());
        admin.setEmail(request.getParameter("email").trim());
        admin.setDepartId(request.getParameter("departId").trim());
        admin.setDepartId_t(request.getParameter("departId_t").trim());
        admin.setRemark(request.getParameter("remark").trim());
        admin.setDepartment(request.getParameter("department").trim());
        admin.setDepartment_t(request.getParameter("department_2").trim());
        admin.setOrg_infor(request.getParameter("org_infor").trim());
        admin.setQq(request.getParameter("qq").trim());
        // admin.setPrivilegeId(Long.valueOf(request.getParameter("privilegeId")));
        String auth = String.valueOf(request.getParameter("privilegeId"));
        auth = auth.substring(0, auth.length() - 1);
        String[] auths = auth.split(",");
        AdminOpera adminOpera = AdminOpera.getInstance();
        Admin dbAdmin = adminOpera.readAdminByName(admin.getName());
        // 用户名是否存在
        if (dbAdmin != null)
        {
            res_return = "{msg:\"系统已经存在该用户：" + admin.getName() + "\"}";
        }
        else
        {
            if (SysUserDao.getInstance().createUser(admin, auths))
            {
                res_return = "{msg:\"ok\"}";
            }
            else
            {
                res_return = "{msg:\"新建后台用户出错，数据库写入失败\"}";
            }
        }
        PrintWriter out = null;
        try
        {
            out = response.getWriter();
            out.write(res_return);
            out.flush();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            out.close();
        }
    }
    
    public void updateUser()
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return = "";
        AdminOpera adminOpera = AdminOpera.getInstance();
        Admin admin = new Admin();
        admin.setName(request.getParameter("name").trim());
        admin.setAdminId(Long.valueOf(request.getParameter("id")));
        admin.setPhoneNum(request.getParameter("phoneNum"));
        admin.setTruename(request.getParameter("truename").trim());
        admin.setEmail(request.getParameter("email").trim());
        admin.setDepartId(request.getParameter("departId").trim());
        admin.setDepartment(request.getParameter("department").trim());
        admin.setOrg_infor(request.getParameter("org_infor").trim());
        admin.setQq(request.getParameter("qq").trim());
        admin.setRemark(request.getParameter("remark").trim());
        admin.setDepartId_t(request.getParameter("departId_t").trim());
        admin.setDepartment_t(request.getParameter("department_2").trim());
        String auth = String.valueOf(request.getParameter("privilegeId"));
        auth = auth.substring(0, auth.length() - 1);
        String[] auths = auth.split(",");
        SysUserDao sud = SysUserDao.getInstance();
        if (adminOpera.updateAdmin(admin) && sud.editUser("", "", String.valueOf(admin.getAdminId()), auths))
        {
            res_return = "{msg:\"ok\"}";
        }
        else
        {
            res_return = "{msg:\"更新后台用户出错，数据库写入失败\"}";
        }
        PrintWriter out = null;
        try
        {
            out = response.getWriter();
            out.write(res_return);
            out.flush();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            out.close();
        }
    }
    
    /**
     * 删除运维用户
     * 
     * @throws Exception
     */
    public void deleteOperUser()
        throws Exception
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return = "";
        long id = Long.parseLong(CommonUtil.nullToDefault(request.getParameter("id"), "0"));
        SysUserDao sud = SysUserDao.getInstance();
        if (sud.deleteOperUser(id))
        {
            res_return = "{msg:\"ok\"}";
        }
        else
        {
            res_return = "{msg:\"删除运维用户失败，数据库失败\"}";
        }
        PrintWriter out = null;
        try
        {
            out = response.getWriter();
            out.write(res_return);
            out.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            out.close();
        }
    }
    
    public void DJUser()
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return = "";
        AdminOpera adminOpera = AdminOpera.getInstance();
        Admin admin = new Admin();
        admin.setStatus(1);
        admin.setAdminId(Long.valueOf(request.getParameter("id")));
        if (adminOpera.updateUser(admin))
        {
            res_return = "{msg:\"ok\"}";
        }
        else
        {
            res_return = "{msg:\"冻结后台用户出错，数据库写入失败\"}";
        }
        PrintWriter out = null;
        try
        {
            out = response.getWriter();
            out.write(res_return);
            out.flush();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            out.close();
        }
    }
    
    public void ResumeUser()
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return = "";
        AdminOpera adminOpera = AdminOpera.getInstance();
        Admin admin = new Admin();
        admin.setStatus(0);
        admin.setAdminId(Long.valueOf(request.getParameter("id")));
        if (adminOpera.updateUser(admin))
        {
            res_return = "{msg:\"ok\"}";
        }
        else
        {
            res_return = "{msg:\"恢复后台用户出错，数据库写入失败\"}";
        }
        PrintWriter out = null;
        try
        {
            out = response.getWriter();
            out.write(res_return);
            out.flush();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            out.close();
        }
    }
    
    public void readprivileges()
        throws IOException
    {
        response.setCharacterEncoding("UTF-8");
        String sys_id = request.getParameter("sys_id");
        List<Map<String, Object>> privileges = XtdoroleDao.getInstance().allrole();
        JSONObject jsonEntity = new JSONObject();
        if (sys_id != null)
        {
            SysUserDao sud = SysUserDao.getInstance();
            Map<String, Object> map = sud.queryUser(sys_id);
            String auth = map.get("AUTH") == null ? "" : String.valueOf(map.get("AUTH"));
            String[] userauths = auth.split(",");
            jsonEntity.put("list_select", JSONArray.fromObject(userauths));
        }
        PrintWriter out = response.getWriter();
        jsonEntity.put("list_all", JSONArray.fromObject(privileges));
        
        out.print(jsonEntity);
    }
    
    
    /**
     * 导出数据
     * @throws IOException
     */
    public void exportJobs_user() throws IOException 
    {
        request.setCharacterEncoding("utf-8");
        Enumeration<String> paraNames = request.getParameterNames();
        Map<String, Object> params_all = this.dealParams(paraNames,null);
		//按条件查询中所有需要导出的数据    规划项目列表
		List<Map<String, Object>> items = SysUserDao.getInstance().getExportDara_user(params_all);
		
		String file_name = "系统用户列表";
		//导出的字段和excel表头的对应关系
        Map<String, String> map_columns = SysUserDao.getInstance().getList_Column_user(params_all);
		this.exportGK(items,map_columns, file_name);
    }	
}
