package com.cudatec.flow.framework.action;

/*
 * 发文
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

import com.cudatec.flow.framework.dao.PersonalProcessDao;
import com.cudatec.flow.framework.dao.TaskManageDao;
import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.dal.bean.Admin;
import com.zqkj.bsm.system.action.dao.SysUserDao;
import com.zqkj.bsm.system.action.page.OraPaginatedList;
import com.zqkj.bsm.util.BHUtil;
import com.zqkj.bsm.util.ClientInfoUtils;
import com.zqkj.bsm.util.CommonUtil;
import com.zqkj.bsm.util.PageData;

@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results({@Result(name = "all", location = "/WEB-INF/jsp/flow/all/all.jsp"),
    @Result(name = "fwg", location = "/WEB-INF/jsp/flow/document/fwg.jsp"),
    @Result(name = "fwg_print", location = "/WEB-INF/jsp/flow/document/fwg_print.jsp"),
    @Result(name = "fwgSend", location = "/WEB-INF/jsp/flow/document/fwgSend.jsp"),
    @Result(name = "activeTask", location = "/WEB-INF/jsp/flow/activeTask.jsp"),
    @Result(name = "error", location = "/failure.jsp"),
    @Result(name = "approve", location = "/WEB-INF/jsp/flow/document/approve.jsp"),
    @Result(name = "mydispatch", location = "/WEB-INF/jsp/flow/my_dispatch/my_dispatch.jsp"),
    @Result(name = "mypost", location = "/WEB-INF/jsp/flow/document/mypost.jsp")})
@ExceptionMappings({@ExceptionMapping(exception = "java.lange.RuntimeException", result = "error")})
@Action(value = "docum")
public class DocumentAction extends BaseAction
{
    
    /**
	 * 
	 */
    private static final long serialVersionUID = 1610160362768893171L;
    
    /**
     * 获取发文类型的下拉框
     * 
     * @throws IOException
     */
    public void getdocument_dic()
        throws IOException
    {
        List<Map<String, Object>> userList = manager.querydocument_dic();
        writeJson(response, userList);
    }
    
    /**
     * 发文流程启动
     * 
     * @return
     */
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
    
    /**
     * 发文流程表单展示
     * 
     * @Title: plan
     * @Description:
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public String fwg()
    {
        String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
        String gkprocessId = CommonUtil.nullToDefault(request.getParameter("gkprocessId"), "");
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
        // 包含表单信息，以及下一个步骤的权限等信息
        map = facets.chooseStep(processId, orderId, taskId, admin);
        Map<String, Object> m = TaskManageDao.getInstance().queryDispatchByOrderId(orderId);// 根据orderId获取带有blob或clob字段的内容信息
        if (m != null && m.size() > 0)
        {
            map.put("file_content", m.get("FILE_CONTENT"));
        }
        map.put("path", "documView"); // 发文表单查看result
        map.put("gk_path", "gkbmView"); // 发文表单查看result
        map.put("action", "docum");
        map.put("label", label);
        map.put("bakurl", "docum!active.action?processId=" + processId);
        map.put("userName", admin.getTruename());
        map.put("thisTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        if (map.get("step").toString().equals("1"))
        {
            map.put("year", BHUtil.getTime());
            map.put("no_dig", "0");
            map.put("gkprocessId", gkprocessId);
            request.setAttribute("map", map);
            return "fwg";
        }
        else if (map.get("step").toString().equals("5") || map.get("step").toString().equals("6")
            || map.get("step").toString().equals("7"))
        {
            map.put("head_title", "发文流程");
            map.put("no_dig", "1");
            request.setAttribute("map", map);
            return "approve";
        }
        // 本步骤为文书编号
        else if (map.get("step").toString().equals("8"))
        {
            // 发文编号的规则
            String wh = String.valueOf(map.get("document_wh"));
            String seq = "";
            switch (wh)
            {
                case "文号":
                    seq = "cwh_seq";
                    break;
                case "常防发":
                    seq = "cff_seq";
                    break;
                case "常水":
                    seq = "csf_seq";
                    break;
                default:
                    seq = "cwh_seq";
                    break;
            }
            map.put("head_title", "发文流程");
            map.put("wh_year", new SimpleDateFormat("yyyy").format(new Date()));
            map.put("no_dig", "0");
            request.setAttribute("map", map);
            return "fwg_print";
        }
        else
        {
            map.put("head_title", "发文流程");
            map.put("no_dig", "0");
            request.setAttribute("map", map);
            return "approve";
        }
    }
    
    /**
     * 发文转发页面
     * 
     * @return
     */
    public String fwgSend()
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
        // 包含表单信息，以及下一个步骤的权限等信息
        map = facets.chooseStep(processId, orderId, taskId, admin);
        Map<String, Object> m = TaskManageDao.getInstance().queryDispatchByOrderId(orderId);// 根据orderId获取带有blob或clob字段的内容信息
        if (m != null && m.size() > 0)
        {
            map.put("file_content", m.get("FILE_CONTENT"));
        }
        map.put("label", label);
        map.put("bakurl", "docum!active.action?processId=" + processId);
        map.put("path", "documView"); // 发文表单查看result
        map.put("gk_path", "gkbmView"); // 发文表单查看result
        request.setAttribute("map", map);
        return "fwgSend";
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
        if (StringHelper.isEmpty(orderId))
        {
            newParams.put("begintime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            params_title.put("path", "documView");
            map = facets.startAndExecute(processId, admin.getName(), params_title);
            // 执行下一步流程
            orderId = map.get("orderId");
            taskId = map.get("taskId");
            newTaskId = map.get("newTaskId");
            displayName = map.get("displayName");
            listObj = TaskManageDao.getInstance().save(newParams, orderId, taskId);
            // 这儿还需要保存fckedit的文件流
            String file_content = CommonUtil.nullToDefault(params_all.get("content"), "");
            if (!file_content.equals(""))
            {
                TaskManageDao.getInstance().saveDispatch(orderId, file_content); // 保存发文内容信息，另存表信息
            }
            // 开始公开审核流程
            if (newParams.get("sfgk").toString().equals("是"))
            {
                Map<String, Object> mapgk = new HashMap<String, Object>();
                mapgk.put("ip", params_title.get("ip"));
                mapgk.put("nextOperator", newParams.get("nextOperator"));
                mapgk.put("path", "gkbmView");
                mapgk.put("title", newParams.get("gk_title"));
                Map<String, String> map_ck =
                    facets.startAndPerson(newParams.get("gkprocessId").toString(), admin.getName(), mapgk);
                mapgk = new HashMap<String, Object>();
                mapgk.put("gk_title", newParams.get("gk_title"));
                mapgk.put("gk_time", dateFormat.format(new Date()));
                ArrayList<Object[]> listObj_gk =
                    TaskManageDao.getInstance().save(mapgk, map_ck.get("orderId"), map_ck.get("taskId"));
                manager.executeBatch_Pre(BATCH_SQL, listObj_gk);
                newParams.put("gk_orderId", map_ck.get("orderId"));
                // 普通步骤进行流程提醒
                addMsg(newParams.get("nextOperator").toString(),
                    map_ck.get("orderId"),
                    map_ck.get("newTaskId"),
                    newParams.get("gkprocessId").toString(),
                    "gkbm!allGkbm.action",
                    map_ck.get("displayName").toString());
                newParams.remove("nextOperator");
                newParams.remove("gkprocessId");
            }
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
                
                if (tasktype == 1)
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
                else
                {
                    // TaskManageDao.getInstance().updateDispatch(orderId, params_all.get("content").toString());
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
        if (tasktype != 1)
        {
            // 删除文本编辑器字段
            newParams.remove("content");
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
                    "docum!alldocumet.action",
                    displayName);
            }
            else
            {
                addMsg(startMap.get("operator").toString(),
                    orderId,
                    newTaskId,
                    processId,
                    "docum!alldocumet.action",
                    displayName);
            }
        }
        else
        {
            if (tasktype == 2)
            {
                // 流程结束后，添加数据至flow_gwgd，方便对已结束的发文流程进行收文归档
                Map<String, Object> list_form = TaskManageDao.getInstance().getForm_List(orderId);
                //
                List<Object> list = new ArrayList<Object>();
                list.add(orderId);
                list.add(list_form.get("begintime"));
                list.add(list_form.get("document_type").toString() + "[" + list_form.get("wh_year").toString() + "]"
                    + list_form.get("wh_num").toString());
                list.add(list_form.get("title"));
                list.add(list_form.get("path_plann_file"));
                list.add(1);
                list.add("");
                list.add(list_form.get("promotionShow"));
                list.add(list_form.get("document_type"));
                list.add(list_form.get("sfgk").toString().equals("是") ? 0 : 1);
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
                List<Map<String, Object>> list = TaskManageDao.getInstance().queryTaskNowByOrderId(orderId);
                for (Map<String, Object> map_hj : list)
                {
                    // 会签进行流程提醒
                    addMsg(String.valueOf(map_hj.get("OPERATOR")),
                        orderId,
                        String.valueOf(map_hj.get("ID")),
                        processId,
                        "docum!alldocumet.action",
                        displayName);
                    
                }
            }
            else
            {
                // 普通步骤进行流程提醒
                addMsg(nextOperator.toString(), orderId, newTaskId, processId, "docum!alldocumet.action", displayName);
            }
            
        }
        response.getWriter().write("success");
    }
    
    /**
     * 跳转：我的发文
     * 
     * @return
     */
    public String mypost()
    {
        String operation = request.getParameter("operation");
        String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
        if ("init".equals(operation))
        {
            // 检索参数
            int pageNum = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagenum"), "1")) - 1;
            int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "10"));
            String title = request.getParameter("title") == null ? "" : request.getParameter("title");
            String startTime = CommonUtil.nullToDefault(request.getParameter("startTime"), "");
            String endTime = CommonUtil.nullToDefault(request.getParameter("endTime"), "");
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("pageSize", pageSize);
            param.put("pageNum", pageNum);
            param.put("personalTop", request.getParameter("personalTop"));
            param.put("startTime", startTime);
            param.put("endTime", endTime);
            Admin admin = (Admin)session.getAttribute("admin");
            String userName = admin.getName();
            param.put("userName", userName);
            param.put("processId", processId);
            param.put("title", title);
            // 分页对象
            PersonalProcessDao perProcessDao = PersonalProcessDao.getInstance();
            OraPaginatedList perProcessList = perProcessDao.getPerProcessList(param);
            List<Map<String, Object>> list = perProcessList.getList();
            for (Map<String, Object> map : list)
            {
                String item_str = CommonUtil.nullToDefault(map.get("VARIABLE"), "{}");
                JSONObject Source = JSONObject.fromObject(item_str);
                String title_ = Source.containsKey("title") ? Source.getString("title") : "";
                map.put("TITLE", title_);
                if (Source.containsKey("path"))
                {
                    map.put("PATH", Source.getString("path"));
                }
            }
            // 分页对象
            PageData pg = CommonUtil.fomateResult(list, pageNum + 1, pageSize, perProcessList.getFullListSize());
            writeJson(response, CommonUtil.formatFGMap(pg));
            return null;
        }
        else
        {
            request.setAttribute("processId", processId);
            return "mypost";
        }
        
    }
    
    public String queryUserDepartInfos()
    {
        String info = CommonUtil.nullToDefault(request.getParameter("info"), "");
        String orgs = CommonUtil.nullToDefault(request.getParameter("orgs"), "");
        writeGson(response, SysUserDao.getInstance().getUserDepartmentInfos(info, orgs));
        return null;
    }
    
    public String queryOrganizationInfos()
    {
        writeGson(response, SysUserDao.getInstance().getOrganizationInfos());
        return null;
    }
    
    public String queryOrganizationInfos1()
    {
        String orgId = request.getParameter("orgId");
        Admin admin = (Admin)session.getAttribute("admin");
        String auths = SysUserDao.getInstance().queryAuthIDs(String.valueOf(admin.getAdminId()));
        String[] ass = auths.split(",");
        int i = 0;
        for (String as : ass)
        {
            if (as.equals("1") || as.equals("41"))
            {
                i = 1;
            }
        }
        if (i == 0)
        {
            writeGson(response, SysUserDao.getInstance().getOrganizationInfos2(orgId));
        }
        else
        {
            writeGson(response, SysUserDao.getInstance().getOrganizationInfos());
        }
        return null;
    }
    
}
