package com.cudatec.flow.framework.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import org.snaker.engine.SnakerEngine;
import org.snaker.engine.access.Page;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryOrder;
import org.snaker.engine.entity.Task;
import org.snaker.engine.entity.WorkItem;
import org.snaker.engine.helper.StringHelper;
import org.snaker.engine.model.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;

import com.cudatec.flow.framework.dao.TaskManageDao;
import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.dal.bean.Admin;
import com.zqkj.bsm.util.ClientInfoUtils;
import com.zqkj.bsm.util.CommonUtil;

@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results({@Result(name = "approve_msg", location = "/WEB-INF/jsp/flow/gkbm/approve_msg.jsp"),
    @Result(name = "all", location = "/WEB-INF/jsp/flow/all/all.jsp"),
    @Result(name = "activeTask", location = "/WEB-INF/jsp/flow/activeTask.jsp"),
    @Result(name = "error", location = "/failure.jsp")})
@ExceptionMappings({@ExceptionMapping(exception = "java.lange.RuntimeException", result = "error")})
@Action(value = "gkbm")
public class GkbmAction extends BaseAction
{
    
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private SnakerEngine engine;
    
    public String allGkbm()
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
     * 发文流程的代办事项
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
    
    public String gkbm_()
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
        Map<String, Object> map = new HashMap<String, Object>();
        // 包含表单信息，以及下一个步骤的权限等信息
        String toName = null;
        List<TaskModel> list = engine.process().getProcessById(processId).getModel().getModels(TaskModel.class);
        if (StringHelper.isNotEmpty(orderId))
        {
            map = TaskManageDao.getInstance().getForm_List(orderId);
            List<Task> listTask =
                engine.query().getActiveTasks(new QueryFilter().setTaskId(taskId).setOrderId(orderId));
            for (TaskModel task : list)
            {
                if (task.getDisplayName().equals(listTask.get(0).getDisplayName()))
                {
                    map.put("taskName", task.getName());
                    toName = task.getOutputs().get(0).getTo();
                    break;
                }
            }
            for (TaskModel task : list)
            {
                if (task.getName().equals(toName))
                {
                    map.put("assignee", task.getAssignee());
                    map.put("displayName", task.getDisplayName());
                    break;
                }
                if (toName.equals("end"))
                {
                    map.put("displayName", "end");
                    break;
                }
            }
        }
        map.put("processId", processId);
        map.put("orderId", orderId);
        map.put("taskId", taskId);
        map.put("path", "gkbmView"); // 请假表单查看result
        map.put("label", label);
        map.put("bakurl", "gkbm!active.action?processId=" + processId);
        request.setAttribute("map", map);
        return "approve_msg";
    }
    
    public void process()
        throws IOException
    {
        
        String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
        String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
        String taskId = CommonUtil.nullToDefault(request.getParameter("taskId"), "");
        Admin admin = (Admin)session.getAttribute("admin");
        Map<String, Object> params_title = new HashMap<String, Object>();
        params_title.put("ip", ClientInfoUtils.getIP(request));
        Enumeration<String> paraNames_ = request.getParameterNames();
        Map<String, Object> params_all = this.dealParams(paraNames_, null);
        if (StringHelper.isEmpty(orderId))
        {
            // 暂无第一步
        }
        else
        {
            // 获取当前taskID的状态
            String taskStatus = TaskStatus(orderId, taskId, admin);
            if (taskStatus.equals("repeat"))
            {
                response.getWriter().write(taskStatus);
                return;
            }
            else if (taskStatus.equals("over"))
            {
                response.getWriter().write(taskStatus);
                return;
            }
            else
            {
                Map<String, Object> nike = new HashMap<String, Object>();
                nike.put(params_all.get("taskName").toString(), params_all.get("suggest"));
                nike.put(params_all.get("taskName").toString().concat("time"), dateFormat.format(new Date()));
                nike.put(params_all.get("taskName").toString().concat("user"), admin.getTruename());
                List<Task> task = facets.execute(taskId, admin.getName(), params_title);
                if (task.size() > 0)
                {
                    // 添加下一步处理人，可封装
                    facets.getEngine()
                        .task()
                        .addTaskActor(task.get(0).getId(), 0, params_all.get("nextOperator").toString().split(","));
                    // 添加提醒，可封装
                    String nextO = params_all.get("nextOperator").toString();
                    addMsg(nextO,
                        orderId,
                        task.get(0).getId(),
                        processId,
                        "gkbm!allGkbm.action",
                        params_all.get("displayName").toString());
                    
                }
                // 保存当前步骤顺序，可封装
                ArrayList<Object[]> listObj_gk = TaskManageDao.getInstance().save(nike, orderId, taskId);
                manager.executeBatch_Pre(BATCH_SQL, listObj_gk);
                // 审批完成后，去掉提醒，可封装
                manager.updateMessage(admin.getName(), taskId, orderId);
                response.getWriter().write("success");
                
            }
        }
    }
    
}
