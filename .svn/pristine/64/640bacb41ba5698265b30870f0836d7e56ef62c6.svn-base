package com.cudatec.flow.framework.action;

/*
 * 请假申请
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

import net.sf.json.JSONObject;

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
import org.snaker.engine.entity.Task;
import org.snaker.engine.entity.WorkItem;
import org.snaker.engine.helper.StringHelper;

import com.cudatec.flow.framework.dao.TaskManageDao;
import com.cudatec.flow.framework.ext.ExtTaskModel;
import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.dal.bean.Admin;
import com.zqkj.bsm.system.action.page.OraPaginatedList;
import com.zqkj.bsm.util.BHUtil;
import com.zqkj.bsm.util.ClientInfoUtils;
import com.zqkj.bsm.util.CommonUtil;
import com.zqkj.bsm.util.PageData;

@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results({@Result(name = "all", location = "/WEB-INF/jsp/flow/all/all.jsp"),
    @Result(name = "leaveView", location = "/WEB-INF/jsp/flow/leave/leaveView.jsp"),
    @Result(name = "leave", location = "/WEB-INF/jsp/flow/leave/leave.jsp"),
    @Result(name = "approve", location = "/WEB-INF/jsp/flow/leave/approve.jsp"),
    @Result(name = "manager", location = "/WEB-INF/jsp/flow/leave/leaveManager.jsp"),
    @Result(name = "activeTask", location = "/WEB-INF/jsp/flow/activeTask.jsp"),
    @Result(name = "leaveLog", location = "/WEB-INF/jsp/flow/leave/leaveLog.jsp"),
    @Result(name = "error", location = "/failure.jsp")})
@ExceptionMappings({@ExceptionMapping(exception = "java.lange.RuntimeException", result = "error")})
@Action(value = "leave")
/**
 * 请假流程
 * @author MJ001
 *
 */
public class LeaveAction extends BaseAction
{
    
    /**
	 * 
	 */
    private static final long serialVersionUID = 3558775164515039490L;
    
    /**
     * 请假流程启动
     * 
     * @return
     */
    
    public String allLeave()
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
     * 请假申请，设定到步骤配置里面的表单URL
     * 
     * @Title: plan
     * @Description:
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public String leave()
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
        Map<String, Object> map = new HashMap<String, Object>();
        
        boolean flag = false; // true ,说明请假人有批假的权利，不属于一般员工， false ，属于一般员工
        int x = 0;
        List<ExtTaskModel> list =
            facets.getEngine().process().getProcessById(processId).getModel().getModels(ExtTaskModel.class);
        List<Task> listTask = new ArrayList<Task>();
        if (StringHelper.isNotEmpty(orderId))
        {
            map = TaskManageDao.getInstance().getForm_List(orderId);
            listTask =
                facets.getEngine().query().getActiveTasks(new QueryFilter().setTaskId(taskId).setOrderId(orderId));
        }
        if (StringHelper.isEmpty(orderId) || listTask.get(0).getTaskName().equals(list.get(0).getName()))
        {
            // 1，代表第一步请假
            x = 1;
            // 当前步骤名称
            map.put("taskName", list.get(0).getName());
            // 下一步操作人权限
            map.put("assignee", list.get(1).getAssignee());
            map.put("autoOrg", list.get(1).getAutoOrg());
            // 流程名称
            map.put("displayName", list.get(1).getDisplayName());
            map.put("result", list.get(1).getName());
            // 第一步组织ID
            map.put("ord", admin.getOrg_id());
            // 判断第一步用户是否在该流程中存在操作权限
            List<Map<String, Object>> listUser = TaskManageDao.getInstance().getUserAuth(admin.getAdminId());
            for (int i = 0; i < list.size(); i++)
            {
                String authId = list.get(i).getAssignee();
                for (Map<String, Object> map2 : listUser)
                {
                    if (authId.equals(map2.get("AUTHID").toString()))
                    {
                        // 属于请假流程中的人员请假
                        flag = true;
                        if (i == list.size() - 1)
                        {
                            // 最大领导不需要请假
                            map.put("assignee", "0");
                        }
                        else
                        {
                            // 下一步操作人权限
                            map.put("assignee", list.get(i + 1).getAssignee());
                            map.put("autoOrg", list.get(i + 1).getAutoOrg());
                            // 流程名称
                            map.put("displayName", list.get(i + 1).getDisplayName());
                            // 判断值
                            map.put("result", list.get(i + 1).getName());
                            
                        }
                    }
                }
            }
            
            // 获取当前用户年假天数
            List<Map<String, Object>> leaveList = TaskManageDao.getInstance().getUserYear(admin.getAdminId());
            if (leaveList.size() == 0)
            {
                map.put("year", 0);
                map.put("allday", 0);
            }
            else
            {
                Double year = Double.parseDouble(leaveList.get(0).get("YEAR").toString());
                Double used = Double.parseDouble(leaveList.get(0).get("USED").toString());
                map.put("year", BHUtil.jsj(year, used));
                map.put("allday", leaveList.get(0).get("ALL_TIME"));
            }
        }
        else
        {
            map.put("taskName", listTask.get(0).getTaskName());
            for (int i = 0; i < list.size(); i++)
            {
                if (list.get(i).getName().equals(listTask.get(0).getTaskName()))
                {
                    if (i == list.size() - 1)
                    {
                        // 最后一步
                        map.put("assignee", "-1");
                    }
                    else
                    {
                        // 下一步操作人权限
                        map.put("assignee", list.get(i + 1).getAssignee());
                        map.put("autoOrg", list.get(i + 1).getAutoOrg());
                        // 流程名称
                        map.put("displayName", list.get(i + 1).getDisplayName());
                    }
                    // 流程步骤
                    x = i + 1;
                }
            }
        }
        map.put("label", label);
        map.put("bakurl", "leave!active.action?processId=" + processId);
        map.put("flag", flag);
        // 当前用户组织ID
        map.put("ord", admin.getOrg_id());
        // 流程步骤序号
        map.put("step", String.valueOf(x));
        map.put("processId", processId);
        map.put("orderId", orderId);
        map.put("taskId", taskId);
        map.put("path", "leaveView"); // 请假表单查看result
        map.put("action", "leave"); // 请假表单查看action
        map.put("userName", admin.getTruename());
        map.put("thisTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        request.setAttribute("map", map);
        if (map.get("step").toString().equals("1"))
        {
            return "leave";
        }
        else
        {
            return "approve";
        }
        
    }
    
    public void process()
        throws IOException
    {
        ArrayList<Object[]> listObj = new ArrayList<Object[]>();
        String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
        String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
        String taskId = CommonUtil.nullToDefault(request.getParameter("taskId"), "");
        String taskName = CommonUtil.nullToDefault(request.getParameter("taskName"), "");
        String method = CommonUtil.nullToDefault(request.getParameter("method"), "0");
        Admin admin = (Admin)session.getAttribute("admin");
        String ipaddr = ClientInfoUtils.getIP(request);
        Map<String, String> map = new HashMap<String, String>();
        Enumeration<String> paraNames = request.getParameterNames();
        Map<String, Object> params = this.dealParams(paraNames, null);
        Map<String, Object> mapTitle = new HashMap<String, Object>();
        mapTitle.put("ip", ipaddr);
        // 下步流程的taskId
        String newTaskId = null;
        // 下步流程名称
        String displayName = null;
        String sql = null;
        // 状态 0 ，流程第一次发起，1，流程第N次发起
        String status = "0";
        // 第一步流程信息
        Map<String, Object> startMap = null;
        if (StringHelper.isEmpty(orderId))
        {
            // 执行第一步流程
            mapTitle.put("title", params.get("title"));
            mapTitle.put("path", "leaveView");
            mapTitle.put("result", params.get("result"));
            map = facets.startAndExecute(processId, admin.getName(), mapTitle);
            orderId = map.get("orderId");
            taskId = map.get("taskId");
            newTaskId = map.get("newTaskId");
            displayName = map.get("displayName");
            params.put("name", admin.getTruename()); // 用户
            params.put("section", admin.getOrg_name());// 部门
            params.put("work", admin.getOrg_infor());// 职务
            params.put("uid", admin.getAdminId());// 用户id
            
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
            
            startMap = getFirstStep(orderId);
            if (!admin.getName().equals(startMap.get("operator")))
            {
                mapTitle.put("method", method);
            }
            List<Task> task = null;
            List<HistoryTask> list = facets.getEngine().query().getHistoryTasks(new QueryFilter().setOrderId(orderId));
            for (HistoryTask historyTask : list)
            {
                // 判断当前流程是否执行过,并且不是转发任务
                JSONObject object = JSONObject.fromObject(historyTask.getVariable());
                String oldMethod = object.get("method") != null ? object.get("method").toString() : "";
                if (historyTask.getTaskName().equals(taskName) && !oldMethod.equals("1"))
                {
                    status = "1";
                }
            }
            
            // 領導同意，或下一步流程
            if (method.equals("0"))
            {
                task = facets.execute(taskId, admin.getName(), mapTitle);
            }
            else
            {
                // 領導不同意
                task = facets.executeAndJump(taskId, admin.getName(), mapTitle, startMap.get("taskName").toString());
                facets.addTaskActor(task.get(0).getId(), 0, new String[] {startMap.get("operator").toString()});
            }
            // 审批完成后，去掉提醒
            manager.updateMessage(admin.getName(), taskId, orderId);
            newTaskId = (task.size() != 0) ? task.get(0).getId() : "-1";
            displayName = (task.size() != 0) ? task.get(0).getDisplayName() : "";
        }
        
        // 对领导审批进行分类处理,(一个流程重多次重复使用approve.jsp)
        Map<String, Object> newParams = changeParam(params, taskName);
        
        if (status.equals("0"))
        {
            sql = BATCH_SQL;
            listObj = TaskManageDao.getInstance().save(newParams, orderId, taskId);
        }
        else
        {
            sql = UPDATE_SQL;
            listObj = TaskManageDao.getInstance().update(newParams, orderId);
        }
        // 保存表单信息
        manager.executeBatch_Pre(sql, listObj);
        // 流程结束时，newTaskId为-1
        if (!newTaskId.equals("-1"))
        {
            if (method.equals("0"))
            {
                // 去除下一步流程的权限ID记录
                TaskManageDao.getInstance().DeActorId(newTaskId);
                // 用户流程添加
                facets.getEngine().task().addTaskActor(newTaskId, 0, newParams.get("userList").toString().split(","));
                // 普通步骤进行流程提醒
                addMsg(newParams.get("userList").toString(),
                    orderId,
                    newTaskId,
                    processId,
                    "leave!allLeave.action",
                    displayName);
            }
            else
            {
                addMsg(startMap.get("operator").toString(),
                    orderId,
                    newTaskId,
                    processId,
                    "leave!allLeave.action",
                    displayName);
            }
        }
        else
        {
            // 流程结束保存数据
            Map<String, Object> AllResult = TaskManageDao.getInstance().getForm_List(orderId);
            // 更新年假表
            TaskManageDao.getInstance().updateUserleave(AllResult);
            // 更新请假表
            TaskManageDao.getInstance().insertLeave(new Object[] {Integer.parseInt(AllResult.get("uid").toString()),
                AllResult.get("qtype").toString(), AllResult.get("allleave").toString(), BHUtil.getTime_year(),
                BHUtil.getTime_month()});
            
        }
        response.getWriter().write("success");
    }
    
    /**
     * 工作计划的代办事项
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
        request.setAttribute("processId", processId);
        request.setAttribute("ccorderTotal", ccorderPage.getTotalCount());
        return "activeTask";
    }
    
    /**
     * 请假管理
     * 
     * @return
     */
    
    public String leaveManager()
    {
        String operation = request.getParameter("operation");
        if ("init".equals(operation))
        {
            // 检索参数
            int pageNum = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagenum"), "1")) - 1;
            int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "10"));
            String truename = request.getParameter("truename") == null ? "" : request.getParameter("truename");
            // 分页封装方法
            OraPaginatedList list_tmp = TaskManageDao.getInstance().getUserLeave(truename, pageNum, pageSize);
            // 分页对象
            PageData pg =
                CommonUtil.fomateResult(list_tmp.getList(), pageNum + 1, pageSize, list_tmp.getFullListSize());
            List<Map<String, Object>> list = pg.getList();
            pg.setList(list);
            writeJson(response, CommonUtil.formatFGMap(pg));
            return null;
        }
        else
        {
            return "manager";
        }
        
    }
    
    public void saveParam()
        throws IOException
    {
        boolean flag = false;
        String uid = CommonUtil.nullToDefault(request.getParameter("uid"), "");
        String year = CommonUtil.nullToDefault(request.getParameter("year"), "");
        flag = TaskManageDao.getInstance().UpdateUserLeave1(year, uid);
        if (flag)
        {
            response.getWriter().write("success");
        }
        else
        {
            response.getWriter().write("error");
        }
    }
    
    public String leaveLog()
    {
        return "leaveLog";
    }
    
    public void getLeaveList()
        throws UnsupportedEncodingException
    {
        request.setCharacterEncoding("UTF-8");
        int page = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("page"), "0"));
        int rows = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("rows"), "0"));
        int year = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("year"), BHUtil.getTime_year()));
        String username = CommonUtil.nullToDefault(request.getParameter("username"), "");
        List<Map<String, Object>> list1 = TaskManageDao.getInstance().getAdminYear(year, username, 0, 1000);
        List<Map<String, Object>> list =
            TaskManageDao.getInstance().getAdminYear(year, username, (page - 1) * rows, rows);
        Map<String, Object> map = new HashMap<>();
        map.put("total", list1.size());
        map.put("rows", list);
        writeGson(response, map);
    }
}
