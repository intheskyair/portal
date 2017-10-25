package com.cudatec.flow.framework.action;

/*
 * 收文
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
import org.snaker.engine.access.Page;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryOrder;
import org.snaker.engine.entity.HistoryTask;
import org.snaker.engine.entity.Process;
import org.snaker.engine.entity.Task;
import org.snaker.engine.entity.WorkItem;
import org.snaker.engine.helper.StringHelper;
import org.snaker.engine.model.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;

import com.cudatec.flow.framework.dao.TaskManageDao;
import com.cudatec.flow.framework.service.ApprovalManager;
import com.cudatec.flow.framework.service.SnakerEngineFacets;
import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.dal.bean.Admin;
import com.zqkj.bsm.util.BHUtil;
import com.zqkj.bsm.util.ClientInfoUtils;
import com.zqkj.bsm.util.CommonUtil;

@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results({@Result(name = "all", location = "/WEB-INF/jsp/flow/all/all.jsp"),
    @Result(name = "recdoc", location = "/WEB-INF/jsp/flow/Receivedocument/recdoc.jsp"),
    @Result(name = "recdocSend", location = "/WEB-INF/jsp/flow/Receivedocument/recdocSend.jsp"),
    @Result(name = "activeTask", location = "/WEB-INF/jsp/flow/activeTask.jsp"),
    @Result(name = "error", location = "/failure.jsp"),
    @Result(name = "approve", location = "/WEB-INF/jsp/flow/Receivedocument/approve.jsp"),
    @Result(name = "mydispatch", location = "/WEB-INF/jsp/flow/my_dispatch/my_dispatch.jsp")})
@ExceptionMappings({@ExceptionMapping(exception = "java.lange.RuntimeException", result = "error")})
/**
 * 收文登记的后台处理
 * @author MJ001
 *
 */
public class ReceiveDocuAction extends BaseAction
{
    
    /**
	 * 
	 */
    private static final long serialVersionUID = -6233817933048958378L;
    
    @Autowired
    SnakerEngineFacets facets;
    
    @Autowired
    ApprovalManager manager;
    
    /**
     * 收文流程启动
     * 
     * @return
     */
    @Action(value = "recdoc")
    public String alldocumet()
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
     * 收文流程的代办事项
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
     * 收文转发的流程的表单
     * 
     * @return
     */
    public String recdocSend()
    {
        String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
        String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
        String taskId = CommonUtil.nullToDefault(request.getParameter("taskId"), "");
        String step = CommonUtil.nullToDefault(request.getParameter("step"), "");
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
        // 流程发起
        if (StringHelper.isNotEmpty(orderId))
        {
            map = TaskManageDao.getInstance().getForm_List(orderId);
        }
        map.put("label", label);
        map.put("bakurl", "recdoc!active.action?processId=" + processId);
        request.setAttribute("year", BHUtil.getTime());
        request.setAttribute("map", map);
        request.setAttribute("step", step);
        request.setAttribute("processId", processId);
        request.setAttribute("orderId", orderId);
        request.setAttribute("taskId", taskId);
        request.setAttribute("path", "recdocView");
        
        return "recdocSend";
    }
    
    /**
     * 收文流程表单展示
     * 
     * @Title: plan
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public String recdoc()
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
        Admin admin = (Admin)request.getSession().getAttribute("admin");
        // 流程发起
        map = facets.chooseStep(processId, orderId, taskId, admin);
        map.put("path", "recdocView"); // 请假表单查看result
        map.put("action", "recdoc");
        map.put("label", label);
        map.put("bakurl", "recdoc!active.action?processId=" + processId);
        map.put("userName", admin.getTruename());
        map.put("thisTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        if (map.get("step").toString().equals("1"))
        {
            if (StringHelper.isEmpty(orderId))
            {
                map.put("num", BHUtil.getXXTransID("csw_seq"));
            }
            // map.put("year", BHUtil.getTime());
            map.put("no_dig", "0"); // 0,领导 1，文书
            request.setAttribute("map", map);
            return "recdoc";
        }
        else if (map.get("step").toString().equals("4") || map.get("step").toString().equals("6")
            || map.get("step").toString().equals("7"))
        {
            map.put("head_title", "收文登记流程");
            // 领导审批的时候不需要出现弹出框
            map.put("no_dig", "1");
            request.setAttribute("map", map);
            return "approve";
        }
        else
        {
            map.put("head_title", "收文登记流程");
            // 领导审批的时候不需要出现弹出框
            map.put("no_dig", "0");
            request.setAttribute("map", map);
            return "approve";
        }
    }
    
    /**
     * 发文，文号类型
     */
    public void getSeqByType()
    {
        String swType = CommonUtil.nullToDefault(request.getParameter("sw_type"), "");
        writeGson(response, BHUtil.getCurrTransID(swType));
    }
    
    /**
     * 表单步骤的流转的统一方法
     * 
     * @throws IOException
     */
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
        Map<String, String> map = null;
        Enumeration<String> paraNames = request.getParameterNames();
        // 流程中保存的数据要少，只要保存标题和步骤就行
        Map<String, Object> params_title = this.dealParams(paraNames, "title");
        params_title.put("ip", ClientInfoUtils.getIP(request));
        Enumeration<String> paraNames_ = request.getParameterNames();
        // 表单中保存的数据要多，所有数据都要保存
        Map<String, Object> params_all = this.dealParams(paraNames_, null);
        // 对领导审批进行分类处理,(一个流程重多次重复使用approve.jsp)
        Map<String, Object> newParams = changeParam(params_all, taskName);
        // 下步流程的taskId
        String newTaskId = null;
        int tasktype = 0;
        String displayName = "";
        String status = "0";
        String sql = null;
        Map<String, Object> startMap = null;
        // 第一步启动流程
        if (StringHelper.isEmpty(orderId) && StringHelper.isEmpty(taskId))
        {
            params_title.put("path", "recdocView");
            map = facets.startAndExecute(processId, admin.getName(), params_title);
            BHUtil.updateTransID(params_all.get("sw_type").toString());
            params_all.remove("sw_type");
            // 执行下一步流程
            orderId = map.get("orderId");
            taskId = map.get("taskId");
            newTaskId = map.get("newTaskId");
            displayName = map.get("displayName");
            
            // ClientDao dao = ClientDao.getInstance();
            // String seq = dao.getSeq(newParams.get("sw_type").toString());
            // newParams.put("num", seq);
            
            listObj = TaskManageDao.getInstance().save(newParams, orderId, taskId);
        }
        // 其他步骤进行审批
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
                List<Task> task = null;
                List<HistoryTask> list = null;
                Process process = facets.getEngine().process().getProcessById(processId);
                List<TaskModel> models = process.getModel().getModels(TaskModel.class);
                for (TaskModel model : models)
                {
                    if (model.getName().equals(taskName))
                    {
                        if (model.getPerformType().equals("ALL"))
                        {
                            tasktype = 1;
                        }
                        
                        // 此处步骤使用会签的数据保存方式，仅此而已
                        if (model.getOutputs().get(0).getTo().equals("end"))
                        {
                            tasktype = 2;
                        }
                        break;
                    }
                    
                }
                
                if (tasktype == 1 || tasktype == 2)
                {
                    // 会签模式
                    Map<String, Object> taskParam = new HashMap<String, Object>();
                    List<Map<String, Object>> listForm = TaskManageDao.getInstance().getCounterJson(taskName, orderId);
                    List<Map<String, Object>> listSuggest = new ArrayList<Map<String, Object>>();
                    Map<String, Object> mapV = new HashMap<>();
                    mapV.put("uid", admin.getAdminId());
                    mapV.put("truename", admin.getTruename());
                    mapV.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    mapV.put("method", method);
                    mapV.put("suggest",
                        newParams.get(taskName + "suggest") == null ? "" : newParams.get(taskName + "suggest"));
                    JSONArray jsonArray = null;
                    if (listForm.size() == 0)
                    {
                        sql = BATCH_SQL;
                        listSuggest.add(mapV);
                        jsonArray = JSONArray.fromObject(listSuggest);
                        taskParam.put(taskName, jsonArray.toString());
                        listObj = TaskManageDao.getInstance().save(taskParam, orderId, taskId);
                    }
                    else
                    {
                        boolean flag = true;
                        sql = UPDATE_SQL;
                        String json = listForm.get(0).get("HTML_VALUE").toString();
                        jsonArray = JSONArray.fromObject(json);
                        listSuggest = (List<Map<String, Object>>)JSONArray.toCollection(jsonArray, HashMap.class);
                        for (Map<String, Object> mapSuggest : listSuggest)
                        {
                            if (mapSuggest.get("uid").toString().equals(String.valueOf(admin.getAdminId())))
                            {
                                mapSuggest.put("method", method);
                                mapSuggest.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                                mapSuggest.put("suggest",
                                    newParams.get(taskName + "suggest") == null ? "" : newParams.get(taskName
                                        + "suggest"));
                                flag = false;
                            }
                        }
                        if (flag)
                        {
                            listSuggest.add(mapV);
                        }
                        jsonArray = JSONArray.fromObject(listSuggest);
                        taskParam.put(taskName, jsonArray.toString());
                        listObj = TaskManageDao.getInstance().update(taskParam, orderId);
                    }
                    
                }
                else
                {
                    // 串行模式
                    list = facets.getEngine().query().getHistoryTasks(new QueryFilter().setOrderId(orderId));
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
                    
                }
                
                // 第一步用户不用保存意见
                startMap = getFirstStep(orderId);
                if (!admin.getName().equals(startMap.get("operator")))
                {
                    params_title.put("method", method);
                }
                // 領導同意，或下一步流程
                if (method.equals("0"))
                {
                    task = facets.execute(taskId, admin.getName(), params_title);
                }
                else
                {
                    // 領導不同意
                    String parentId = null;
                    startMap = getFirstStep(orderId);
                    task =
                        facets.executeAndJump(taskId, admin.getName(), params_title, startMap.get("taskName")
                            .toString());
                    facets.addTaskActor(task.get(0).getId(), 0, new String[] {startMap.get("operator").toString()});
                    List<HistoryTask> lihst =
                        facets.getEngine()
                            .query()
                            .getHistoryTasks(new QueryFilter().setOrderId(orderId).setTaskId(taskId));
                    // 这里还是很奇怪，你麻痹，精确查找还是只能所有记录，这个snaker有问题啊，艹
                    for (HistoryTask historyTask : lihst)
                    {
                        if (historyTask.getId().equals(taskId))
                        {
                            parentId = historyTask.getParentTaskId();
                        }
                    }
                    // 删除会签任务
                    TaskManageDao.getInstance().del(parentId);
                    
                }
                
                // 当上一步流程用户为空时，调用
                if (params_all.get("assignee") != null && params_all.get("assignee").toString().equals("-2"))
                {
                    list = facets.getEngine().query().getHistoryTasks(new QueryFilter().setOrderId(orderId));
                    for (HistoryTask historyTask : list)
                    {
                        if (historyTask.getParentTaskId().equals("start"))
                        {
                            params_all.put("userList", historyTask.getOperator() + ",");
                        }
                    }
                    
                }
                
                // 审批完成后，去掉提醒
                manager.updateMessage(admin.getName(), taskId, orderId);
                newTaskId = (task.size() != 0) ? task.get(0).getId() : "-1";
                displayName = (task.size() != 0) ? task.get(0).getDisplayName() : "";
                /*
                 * if(task.size() != 0 && method.equals("0")) { String task_Name = task.get(0).getTaskName(); Process
                 * process = facets.getEngine().process().getProcessById(processId); List<TaskModel> models =
                 * process.getModel().getModels(TaskModel.class); for(TaskModel model:models) {
                 * if(model.getName().equals(task_Name)) { if(model.getPerformType().equals("ALL")) { tasktype = 1; } }
                 * } }
                 */
            }
            
        }
        // 非会签模式保存数据方式,会签方式不适用
        if (tasktype != 1 && tasktype != 2)
        {
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
        }
        
        manager.executeBatch_Pre(sql, listObj);
        if (!newTaskId.equals("-1"))
        {
            if (method.equals("0"))
            {
                if (tasktype == 1)
                {
                    // 清除会签之前不同意的意见(第一次会签参与并不同意，第二次没参与的领导)
                }
                
                // 去除下一步流程的权限ID记录
                TaskManageDao.getInstance().DeActorId(newTaskId);
                // 用户流程添加
                facets.getEngine().task().addTaskActor(newTaskId, 0, newParams.get("userList").toString().split(","));
                // 普通步骤进行流程提醒
                addMsg(newParams.get("userList").toString(),
                    orderId,
                    newTaskId,
                    processId,
                    "recdoc!alldocumet.action",
                    displayName);
            }
            else
            {
                addMsg(startMap.get("operator").toString(),
                    orderId,
                    newTaskId,
                    processId,
                    "recdoc!alldocumet.action",
                    displayName);
            }
        }
        else
        {
            if (tasktype == 2)
            {
                // 流程结束后，添加数据至flow_gwgd，方便对已结束的收文流程进行收文归档
                Map<String, Object> list_form = TaskManageDao.getInstance().getForm_List(orderId);
                //
                List<Object> list = new ArrayList<Object>();
                list.add(orderId); // orderId
                list.add(list_form.get("rec_time")); // 来文日期
                list.add(list_form.get("call").toString()+" "+list_form.get("num").toString()); // 文号
                list.add(list_form.get("title"));// 标题
                list.add(list_form.get("path_plann_file"));// 文件保存地址
                list.add(0); // 类型：收文
                list.add(list_form.get("bcqx")); // 保存期限
                list.add(list_form.get("promotionShow")); // 文件名称
                list.add(list_form.get("call")); // 收文类型
                list.add(0);
                TaskManageDao.getInstance().addFlowGWGD(list);
            }
        }
        response.getWriter().write("success");
    }
    
    /**
     * 流程转派的统一方法
     * 
     * @throws IOException
     */
    public void processtransfer()
        throws IOException
    {
        String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
        String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
        String taskId = CommonUtil.nullToDefault(request.getParameter("taskId"), "");
        String jump_task_Name = CommonUtil.nullToDefault(request.getParameter("task_Name"), "");
        Admin admin = (Admin)session.getAttribute("admin");
        Enumeration<String> paraNames = request.getParameterNames();
        // 表单中保存的数据要多，所有数据都要保存
        Map<String, Object> params_all = this.dealParams(paraNames, null);
        // 下步流程的taskId
        String newTaskId = null;
        int tasktype = 0;
        String displayName = "";
        Map<String, String> map = null;
        // 第一步启动流程
        if (StringHelper.isEmpty(orderId) && StringHelper.isEmpty(taskId))
        {
        }
        // 其他步骤进行审批
        else
        {
            map =
                zyFlowHandle(orderId,
                    taskId,
                    jump_task_Name,
                    params_all,
                    admin,
                    processId,
                    ClientInfoUtils.getIP(request));
            tasktype = map == null ? 0 : Integer.valueOf(map.get("tasktype"));
        }
        // 最新流程Id
        newTaskId = map.get("newTaskId");
        // 当前流程名称
        displayName = map.get("displayName");
        // 处理下一步步骤
        if (!newTaskId.equals("-1"))
        {
            String ob[] = facets.getEngine().query().getTaskActorsByTaskId(newTaskId);
            String nextOperator = CommonUtil.nullToDefault(params_all.get("nextOperator"), "");
            facets.getEngine().task().addTaskActor(newTaskId, tasktype, nextOperator.split(","));
            log.info("收文流程流程转发newTaskId=" + newTaskId + ",下一个步骤处理人=" + nextOperator);
            if (ob != null && ob.length > 0)
            {
                if (tasktype == 1)
                {
                    facets.getEngine().task().removeTaskActor(newTaskId, ob[0]);
                    TaskManageDao.getInstance().deleteHq(orderId);
                }
            }
            else
            {
                if (tasktype == 1)
                {
                    TaskManageDao.getInstance().deleteHq(orderId);
                }
            }
            if (tasktype == 1)
            {
                
                // 会签提醒前清空存在的会签领导意见
                TaskManageDao.getInstance().delCounterJson(jump_task_Name, orderId);
                
                List<Map<String, Object>> list = TaskManageDao.getInstance().queryTaskNowByOrderId(orderId);
                for (Map<String, Object> map_hj : list)
                {
                    // 会签进行流程提醒
                    addMsg(String.valueOf(map_hj.get("OPERATOR")),
                        orderId,
                        String.valueOf(map_hj.get("ID")),
                        processId,
                        "recdoc!alldocumet.action",
                        displayName);
                }
            }
            else
            {
                // 普通步骤进行流程提醒
                addMsg(nextOperator.toString(), orderId, newTaskId, processId, "recdoc!alldocumet.action", displayName);
            }
            
        }
        response.getWriter().write("success");
    }
    
}
