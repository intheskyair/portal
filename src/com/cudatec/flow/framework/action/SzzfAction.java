package com.cudatec.flow.framework.action;

/**
 * 水政执法
 */
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.snaker.engine.access.Page;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryOrder;
import org.snaker.engine.entity.HistoryTask;
import org.snaker.engine.entity.WorkItem;
import org.snaker.engine.helper.StringHelper;

import com.cudatec.flow.framework.dao.TaskManageDao;
import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.dal.bean.Admin;
import com.zqkj.bsm.system.action.SysPara;
import com.zqkj.bsm.util.BHUtil;
import com.zqkj.bsm.util.ClientInfoUtils;
import com.zqkj.bsm.util.CommonUtil;
import com.zqkj.bsm.util.PageData;

@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results({@Result(name = "all", location = "/WEB-INF/jsp/flow/all/all.jsp"),
    @Result(name = "szzf", location = "/WEB-INF/jsp/flow/szzf/szzf.jsp"),
    @Result(name = "msg", location = "/WEB-INF/jsp/flow/szzf/msg.jsp"),
    @Result(name = "approve", location = "/WEB-INF/jsp/flow/all/approve.jsp"),
    @Result(name = "issued", location = "/WEB-INF/jsp/flow/szzf/issued.jsp"),
    @Result(name = "zdja", location = "/WEB-INF/jsp/flow/szzf/zdja.jsp"),
    @Result(name = "szxc", location = "/WEB-INF/jsp/flow/szzf/szxc.jsp"),
    @Result(name = "sb", location = "/WEB-INF/jsp/flow/szzf/sb.jsp"),
    @Result(name = "activeTask", location = "/WEB-INF/jsp/flow/activeTask.jsp"),
    @Result(name = "error", location = "/failure.jsp")})
@ExceptionMappings({@ExceptionMapping(exception = "java.lange.RuntimeException", result = "error")})
public class SzzfAction extends BaseAction
{
    
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 水政执法流程启动
     * 
     * @return
     */
    @Action(value = "szzf")
    public String allSzzf()
    {
        // 流程编号
        String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
        String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
        String taskId = CommonUtil.nullToDefault(request.getParameter("taskId"), "");
        request.setAttribute("processId", processId);
        request.setAttribute("orderId", orderId);
        request.setAttribute("taskId", taskId);
        if (StringHelper.isNotEmpty(processId))
        {
            Object oo = facets.getEngine().process().getProcessById(processId);
            request.setAttribute("process", oo);
        }
        if (StringHelper.isNotEmpty(orderId))
        {
            request.setAttribute("order", facets.getEngine().query().getOrder(orderId));
        }
        if (StringHelper.isNotEmpty(taskId))
        {
            request.setAttribute("task", facets.getEngine().query().getTask(taskId));
        }
        return "all";
    }
    
    /**
     * 水政执法监督
     * 
     * @Title: plan
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public String szzf()
    {
         String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
        String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
        String taskId = CommonUtil.nullToDefault(request.getParameter("taskId"), "");
        String label = CommonUtil.nullToDefault(request.getParameter("label"), "");
        try
        {
            label = URLDecoder.decode(label, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Admin admin = (Admin)request.getSession().getAttribute("admin");
        // 流程开始
        Map<String, Object> map = facets.chooseStep1(processId, orderId, taskId, admin);
        if (StringHelper.isEmpty(orderId))
        {
            map.put("transid", BHUtil.getTransID()); // 编号
        }
        map.put("path", "szzfView"); // 所有页面必传参数
        map.put("action", "szzf"); // //所有页面必传参数
        map.put("label", label);
        map.put("bakurl", "szzf!active.action?processId=" + processId);
        // 返回表单访问路径
        map.put("userName", admin.getTruename());
        map.put("thisTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        request.setAttribute("map", map);
        String taskName = map.get("taskName").toString();
        String path = null;
        if(taskName.equals("jsjb")){
        	path = "szzf";
        }else if(taskName.equals("zdbyj")){
        	 path = "approve";
        }else if(taskName.equals("xfaj")){
        	 path = "issued";
        }else if(taskName.equals("dccl")){
        	 path = "msg";
        }else if(taskName.equals("ja")){
        	path = "zdja";
        }else if(taskName.equals("sb")){
        	path = "sb";
        }
       
        return path;
    }
    
    public void process() throws IOException
    {
        ArrayList<Object[]> listObj = new ArrayList<Object[]>();
        String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
        String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
        String taskId = CommonUtil.nullToDefault(request.getParameter("taskId"), "");
        String title = CommonUtil.nullToDefault(request.getParameter("title"), "");
        String method = CommonUtil.nullToDefault(request.getParameter("method"), "0");
        String taskName = CommonUtil.nullToDefault(request.getParameter("taskName"), "");
        String result = CommonUtil.nullToDefault(request.getParameter("result"), "");
        Admin admin = (Admin)session.getAttribute("admin");
        Map<String, String> map = null;
        Enumeration<String> paraNames = request.getParameterNames();
        Map<String, Object> params = this.dealParams(paraNames, null);
        Map<String, Object> params_title = new HashMap<String, Object>();
        params_title.put("ip", ClientInfoUtils.getIP(request));
        // 下步流程的taskId
        String newTaskId = null;
        String displayName = "";
        boolean flag = true; // true ，新增，false，编辑
        String sql = BATCH_SQL;
        if (StringHelper.isEmpty(orderId))
        {
            // 第一步流程发起
            params_title.put("title", admin.getTruename() + "_" + title);
            params_title.put("path", "szzfView");
            map = facets.startAndExecute(processId, admin.getName(), params_title);
            BHUtil.updateTransID("MovieSeq");
            orderId = map.get("orderId");
            taskId = map.get("taskId");
            newTaskId = map.get("newTaskId");
            // listObj = TaskManageDao.getInstance().save(params_all, orderId, taskId);
        }
        else
        {
            List<HistoryTask> listHIs =
                facets.getEngine().query().getHistoryTasks(new QueryFilter().setOrderId(orderId));
            // 这里很奇怪，参数给的很详细还是不能查出指定数据，看来只能遍历，艹
            for (HistoryTask historyTask : listHIs)
            {
                if (historyTask.getId().equals(taskId))
                {
                    // 审批完成后，去掉提醒
                    manager.updateMessage(admin.getName(), taskId, orderId);
                    response.getWriter().write("repeat");
                    return;
                }
            }
            // 执行下一步流程
            map = nextFlowHandle(orderId, taskId, taskName, params, method, admin, processId);
            flag = map.get("status").equals("0") ? true : false;
        }
        
        // 最新流程Id
        newTaskId = map.get("newTaskId");
        // 当前流程名称
        displayName = map.get("displayName");
        
        // 对领导审批进行分类处理,(一个流程重多次重复使用approve.jsp)
        Map<String, Object> newParams = changeParam(params, taskName);
        
        if (flag)
        {
            sql = BATCH_SQL;
            listObj = TaskManageDao.getInstance().save(newParams, orderId, taskId);
        }
        else
        {
            sql = UPDATE_SQL;
            listObj = TaskManageDao.getInstance().update(newParams, orderId);
        }
        // 表单数据保存至flow_form
        manager.executeBatch_Pre(sql, listObj);
        // 流程结束时，newTaskId为-1
        if (!newTaskId.equals("-1"))
        {
            if (method.equals("0"))
            {
                // 去除下一步流程的权限ID记录
                facets.getEngine()
                    .task()
                    .removeTaskActor(newTaskId, new String[] {newParams.get("assignee").toString()});
                // 用户流程添加
                facets.getEngine().task().addTaskActor(newTaskId, 0, newParams.get("userList").toString().split(","));
                // 普通步骤进行流程提醒
                addMsg(newParams.get("userList").toString(),
                    orderId,
                    newTaskId,
                    processId,
                    "szzf!allSzzf.action",
                    displayName);
            }
            else
            {
                addMsg(map.get("startUser"), orderId, newTaskId, processId, "szzf!allSzzf.action", displayName);
            }
        }
        response.getWriter().write("success");
    }
    
    /**
     * 水政执法的代办事项
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public String active()
    {
        String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
        List<String> list = new ArrayList<String>();
        Admin admin = (Admin)session.getAttribute("admin");
        list.add(admin.getName());
        String[] assignees = new String[list.size()];
        list.toArray(assignees);
        Page<WorkItem> majorPage = new Page<WorkItem>(5);
        Page<WorkItem> aidantPage = new Page<WorkItem>(3);
        Page<HistoryOrder> ccorderPage = new Page<HistoryOrder>(3);
        Map<String, Object> map = this.getTaskPages(facets, processId, assignees, majorPage, aidantPage, ccorderPage);
        List<WorkItem> aidantWorks = (List<WorkItem>)map.get("aidantWorks");
        List<HistoryOrder> ccWorks = (List<HistoryOrder>)map.get("ccWorks");
        ;
        List<WorkItem> majorWorks = (List<WorkItem>)map.get("majorWorks");
        ;
        request.setAttribute("majorWorks", majorWorks);
        request.setAttribute("majorTotal", majorPage.getTotalCount());
        request.setAttribute("aidantWorks", aidantWorks);
        request.setAttribute("aidantTotal", aidantPage.getTotalCount());
        request.setAttribute("ccorderWorks", ccWorks);
        request.setAttribute("ccorderTotal", ccorderPage.getTotalCount());
        request.setAttribute("processId", processId);
        return "activeTask";
    }
    
    /**
     * 水政巡查上报
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public String szxcList()
    {
        String operation = request.getParameter("operation");
        request.setAttribute("head", "水政巡查");
        if ("init".equals(operation))
        {
            // 检索参数
            // String url_5 = "http://112.25.139.27:8066/DataService/serviceconfig/eventQuery.action?";
            String url_5 = SysPara.szxcurl;
            int pageNum = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagenum"), "1")) - 1;
            int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "10"));
            String sctime = CommonUtil.nullToDefault(request.getParameter("time"), "");
            // ****巡查时间
            String stSbry = CommonUtil.nullToDefault(request.getParameter("stSbry"), "");
            url_5 += "pageSize=" + pageSize + "&pageNo=" + pageNum + "&stSbry=" + stSbry + "&dSbrq=" + sctime;
            // //分页封装方法
            // // 分页对象
            Map<String, Object> map = TaskManageDao.getInstance().getszxcList(url_5);
            int rowCount = Integer.valueOf(CommonUtil.nullToDefault(map.get("rowCount"), "0"));
            List<Map<String, Object>> list = (List<Map<String, Object>>)map.get("list");
            PageData pg = CommonUtil.fomateResult(list, pageNum + 1, pageSize, rowCount);
            writeJson(response, CommonUtil.formatFGMap(pg));
            return null;
        }
        else
        {
            return "szxc";
        }
    }
    
    public void getszxcInfo()
    {
        // String url_5 = "http://112.25.139.27:8066/DataService/serviceconfig/eventQueryOne.action?";
        String url_5 = SysPara.szxcInfourl;
        String id = CommonUtil.nullToDefault(request.getParameter("id"), "0");
        url_5 += "id=" + id;
        // 分页封装方法
        // 分页对象
        Map<String, Object> map = TaskManageDao.getInstance().getszxcInfo(url_5);
        writeJson(response, map);
    }
    
}
