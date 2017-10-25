package com.zqkj.bsm.worklog.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.opensymphony.xwork2.ActionContext;
import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.dal.bean.Admin;
import com.zqkj.bsm.mzpc.dao.MzpcDao;
import com.zqkj.bsm.system.action.SysPara;
import com.zqkj.bsm.system.action.dao.OrganizationDao;
import com.zqkj.bsm.system.action.dao.SysUserDao;
import com.zqkj.bsm.system.action.page.OraPaginatedList;
import com.zqkj.bsm.util.BHUtil;
import com.zqkj.bsm.util.CommonUtil;
import com.zqkj.bsm.util.PageData;
import com.zqkj.bsm.worklog.dao.WorkLogDao;

@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results({@Result(name = "MzpcList", location = "/WEB-INF/jsp/mzpc/mzpcList.jsp"),
    @Result(name = "canDfList", location = "/WEB-INF/jsp/mzpc/canDfList.jsp"),
    @Result(name = "resultDfList", location = "/WEB-INF/jsp/worklog/resultDfList.jsp"),
    @Result(name = "logList", location = "/WEB-INF/jsp/worklog/allLogList.jsp"),
    @Result(name = "work_record", location = "/WEB-INF/jsp/work_time/work_record.jsp"),
    @Result(name = "allTimeList", location = "/WEB-INF/jsp/work_time/allWorkTimeList.jsp"),
    @Result(name = "error", location = "/failure.jsp")})
@ExceptionMappings({@ExceptionMapping(exception = "java.lange.RuntimeException", result = "error")})
public class WorkLogAction extends BaseAction
{
    
    /**
	 * 
	 */
    private static final long serialVersionUID = -4968938517351362927L;
    
    /**
     * 工作日志信息
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Action(value = "worklog")
    public String MzpcList()
    {
        String operation = request.getParameter("operation");
        if ("init".equals(operation))
        {
            // 检索参数
            int pageNum = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagenum"), "1")) - 1;
            int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "10"));
            String uid = request.getParameter("title") == null ? "" : request.getParameter("title");
            String sortname = CommonUtil.nullToDefault(request.getParameter("sortname"), "");
            String sortorder = CommonUtil.nullToDefault(request.getParameter("sortorder"), "");
            // 分页封装方法
            OraPaginatedList list_tmp = MzpcDao.getInstance().queryMzpc(uid, pageNum, pageSize, sortorder, sortname);
            // 分页对象
            PageData pg =
                CommonUtil.fomateResult(list_tmp.getList(), pageNum + 1, pageSize, list_tmp.getFullListSize());
            writeJson(response, CommonUtil.formatFGMap(pg));
            return null;
        }
        else
        {
            return "MzpcList";
        }
        
    }
    
    public String logList()
        throws Exception
    {
        String operation = request.getParameter("operation");
        if ("init".equals(operation))
        {
            // 检索参数
            int pageNum = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagenum"), "1")) - 1;
            int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "30"));
            String uid = request.getParameter("uid_sel") == null ? "" : request.getParameter("uid_sel");
            String username = request.getParameter("username") == null ? "" : request.getParameter("username");
            String log_date = request.getParameter("log_date") == null ? "" : request.getParameter("log_date");
            String sortname = CommonUtil.nullToDefault(request.getParameter("sortname"), "");
            String sortorder = CommonUtil.nullToDefault(request.getParameter("sortorder"), "");
            OraPaginatedList list = WorkLogDao.getInstance().queryMzpc(uid,username,log_date, pageNum, pageSize, sortorder, sortname);
            // 分页对象
            PageData pg = CommonUtil.fomateResult(list.getList(), pageNum + 1, pageSize, list.getFullListSize());
            if(!uid.equals("")){
                writeJson(response, CommonUtil.formatFGMap(pg));
            }else{
                writeJson(response, null);
            }
            return null;
        }
        else
        {
            return "logList";
        }
        
    }
    
    /**
     * 新增工作日志
     */
    public void addWorkLog()
    {
        response.setContentType("text/html; charset=UTF-8");
        boolean isok = WorkLogDao.getInstance().addWorkLog(request);
        String res_return = "";
        if (isok)
        {
            res_return = "{msg:\"ok\"}";
        }
        else
        {
            res_return = "{msg:\"保存失败\"}";
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
    
    public void updateWork()
        throws Exception
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return = "";
        if (WorkLogDao.getInstance().updateWorkLog(request))
        {
            res_return = "{msg:\"ok\"}";
        }
        else
        {
            res_return = "{msg:\"修改失败\"}";
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
    
    /**
     * 获取指定日志信息
     * 
     * @param id
     */
    public void getWorkInfo()
    {
        String id = CommonUtil.nullToDefault(request.getParameter("id"), "");
        Map<String, Object> map = WorkLogDao.getInstance().queryById(id);
        writeJson(response, map);
    }
    
    /**
     * 考勤登记信息 1:上午 2：下午
     * 
     * @return
     */
    @SuppressWarnings("rawtypes")
    public String workRecord()
    {
        Admin admin = (Admin)session.getAttribute("admin");
        Map zs = WorkLogDao.getInstance().isTime(String.valueOf(admin.getAdminId()), 1);
        Map xw = WorkLogDao.getInstance().isTime(String.valueOf(admin.getAdminId()), 2);
        ActionContext.getContext().put("zs", zs);
        ActionContext.getContext().put("xw", xw);
        ActionContext.getContext().put("work_time", SysPara.work_time);
        ActionContext.getContext().put("off_time", SysPara.off_time);
        return "work_record";
    }
    
    public String allTimeList()
    {
        return "allTimeList";
    }
    
    private StringBuilder sb=new StringBuilder();
    public String getOrgAllUser(String orgId){
         List<Map<String, Object>> olist = OrganizationDao.getInstance().getSubOrgIdBySupOrgId(orgId);
         if(olist.size() >0 ){
             for(int i=0;i<olist.size();i++)
             {
                 String oid=olist.get(i).get("ORG_ID").toString();
                 sb.append(oid);
                 sb.append(",");
                 getOrgAllUser(oid);
             }
         }
        String sb1=sb.toString();
        if(sb1.endsWith(",")){
            String sb2= sb1.substring(0, sb1.length()-1);
            return sb2;
        }else{
            return sb1;
        }
        
        
    }
    
    public void timeTable()
        throws ParseException, UnsupportedEncodingException
    {
        // 页面显示记录数
        Admin admin = (Admin)session.getAttribute("admin");
        String orgId=String.valueOf(admin.getOrg_id());
        String auths = SysUserDao.getInstance().queryAuthIDs(String.valueOf(admin.getAdminId()));
        String[] ass = auths.split(",");
        int ai=0;
        for(String as:ass)
        {
            if(as.equals("1")||as.equals("41")){
                ai=1;
            }
        }
        int page = Integer.parseInt(request.getParameter("page"));
        int rows = Integer.parseInt(request.getParameter("rows"));
        String orgId2= request.getParameter("orgId")==null?"":request.getParameter("orgId");
        page = (page == 0 ? 1 : page);
        rows = (rows == 0 ? 30 : rows);
        int start = (page - 1) * rows;
        String startTime = CommonUtil.nullToDefault(request.getParameter("startTime"), "");
        String username1 = CommonUtil.nullToDefault(request.getParameter("username"), "");
        String username=URLDecoder.decode(username1, "utf-8");
        List<Map<String, Object>> list = WorkLogDao.getInstance().queryAllTime(startTime);
        List<Map<String, Object>> list_user;
        List<Map<String, Object>> list_user_count;
        if(ai==0){
                String o2=getOrgAllUser(orgId)+","+orgId;
                if(o2.startsWith(",")){
                    o2=o2.substring(1, o2.length());
                }
                list_user_count = SysUserDao.getInstance().queryAllUserCount2(username,o2,startTime);
                list_user = SysUserDao.getInstance().queryAllUser2(start, rows, username,o2,startTime);
        }else{
             list_user_count = SysUserDao.getInstance().queryAllUserCount(username,startTime);
             list_user = SysUserDao.getInstance().queryAllUser(start, rows, username,startTime);
        }
        if(!orgId2.equals("")){
            String o2=getOrgAllUser(orgId2)+","+orgId2;
            if(o2.startsWith(",")){
                o2=o2.substring(1, o2.length());
            }
            list_user_count = SysUserDao.getInstance().queryAllUserCount2(username,o2,startTime);
            list_user = SysUserDao.getInstance().queryAllUser2(start, rows, username,o2,startTime);
        }
        int mnum = BHUtil.getDaysByYearMonth(2017, 3);
        Map<String, Integer> m = new HashMap<String, Integer>();
        Map<Integer, String> m_f = new HashMap<Integer, String>();
        int v = 1;
        JSONObject obj = new JSONObject();
        if (null != list_user)
        {
            for (Map<String, Object> map_usr : list_user)
            {
                String key = String.valueOf(map_usr.get("TRUENAME")) + "_上午";
                m.put(key, v);
                m_f.put(v, key);
                String key1 = String.valueOf(map_usr.get("TRUENAME")) + "_下午";
                v++;
                m.put(key1, v);
                m_f.put(v, key1);
                v++;
            }
            
            int a_i = list_user.size() * 2 + 1;
            int a_j = mnum + 2;
            String[][] a = new String[a_i][a_j];
            a[0][0] = "";
            a[0][1] = "姓名";
            for (int i = 1; i <= mnum; i++)
            {
                a[0][i + 1] = "" + i;
            }
            for (int i = 1; i < a_i; i++)
            {
                for (int j = 0; j < a_j; j++)
                {
                    if (j == 0)
                    {
                        a[i][j] = m_f.get(i).split("_")[1];
                    }
                    else if (j == 1)
                    {
                        a[i][j] = m_f.get(i).split("_")[0];
                    }
                    else
                    {
                        a[i][j] = "-1";
                    }
                }
            }
            
            // System.out.println(Arrays.deepToString(a));
            for (Map<String, Object> map_data : list)
            {
                String key = String.valueOf(map_data.get("UNAME")) + "_" + String.valueOf(map_data.get("TYPE"));
                if (null != m.get(key))
                {
                    int index = m.get(key);
                    // a[index][0]= String.valueOf(index);
                    // a[index][1]= String.valueOf(map_data.get("UNAME"));
                    String date = String.valueOf(map_data.get("DATE"));
                    int index_y = Integer.valueOf(date.substring(8));
                    a[index][index_y + 1] = String.valueOf(map_data.get("T_STATUS"));
                }
            }
            List<Map<String, Object>> list_mdata = new ArrayList<Map<String, Object>>();
            for (int i = 1; i < a_i; i++)
            {
                Map<String, Object> m_data_list = new HashMap<>();
                for (int j = 0; j < a_j; j++)
                {
                    
                    if (j == 0)
                    {
                        m_data_list.put("anpm", a[i][j]);
                    }
                    else if (j == 1)
                    {
                        m_data_list.put("name", a[i][j]);
                    }
                    else
                    {
                        m_data_list.put("key" + (j - 1), a[i][j]);
                    }
                }
                list_mdata.add(m_data_list);
            }
            obj.put("rows", list_mdata); // 这里的 rows 和total 的key 是固定的
            obj.put("total", list_user_count.get(0).get("NUM"));
        }
        
        //
        // System.out.println(alist);
        // System.out.println(Arrays.deepToString(a));
        
        // JSONArray json = JSONArray.fromObject(list_mdata);
        // Map countryCapitals = ArrayUtils.toMap(a);
        PrintWriter out = null;
        try
        {
            out = response.getWriter();
            out.write(obj.toString());
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
     * 新增工作日志
     */
    public void addWorkTime()
    {
        response.setContentType("text/html; charset=UTF-8");
        Admin admin = (Admin)session.getAttribute("admin");
        boolean isok = WorkLogDao.getInstance().addWorkTime(request, admin.getTruename());
        String res_return = "";
        if (isok)
        {
            res_return = "{msg:\"ok\"}";
        }
        else
        {
            res_return = "{msg:\"保存失败\"}";
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
    
    /**
     * 工作日志列表
     * 
     * @return
     */
    public String resultDfList()
    {
        String operation = request.getParameter("operation");
        if ("init".equals(operation))
        {
            // 检索参数
            int pageNum = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagenum"), "1")) - 1;
            int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "30"));
            String uid = request.getParameter("title") == null ? "" : request.getParameter("title");
            String username = request.getParameter("username") == null ? "" : request.getParameter("username");
            String log_date = request.getParameter("log_date") == null ? "" : request.getParameter("log_date");
            String sortname = CommonUtil.nullToDefault(request.getParameter("sortname"), "");
            String sortorder = CommonUtil.nullToDefault(request.getParameter("sortorder"), "");
            OraPaginatedList list = WorkLogDao.getInstance().queryMzpc(uid,username,log_date, pageNum, pageSize, sortorder, sortname);
            // 分页对象
            PageData pg = CommonUtil.fomateResult(list.getList(), pageNum + 1, pageSize, list.getFullListSize());
            writeJson(response, CommonUtil.formatFGMap(pg));
            return null;
        }
        else
        {
            return "resultDfList";
        }
        
    }
    
    /**
     * 动态加载日历事件标记
     * 
     * @return
     */
    public void dateToJson()
    {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = null;
        // 当前用户
        Admin admin = (Admin)session.getAttribute("admin");
        OraPaginatedList list_date = WorkLogDao.getInstance().dateToJson(admin.getAdminId(), 0, 100);
        // 存放json格式
        JSONObject jsonObject = new JSONObject();
        JSONArray arr = new JSONArray();
        Calendar now = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        for (int i = 0; i < list_date.getList().size(); i++)
        {
            // 得到数据库中日期，动态封装
            // BigDecimal d = (BigDecimal) list_date.getList().get(i).get("LOG_DATE");
            // now.set(Calendar.DATE, d.intValue());
            jsonObject.put("title", "已填报");
            // 日期格式转换
            jsonObject.put("start", list_date.getList().get(i).get("LOG_DATE"));
            arr.add(jsonObject);
        }
        try
        {
            out = response.getWriter();
            out.write(arr.toString());
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
    
}
