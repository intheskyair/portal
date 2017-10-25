package com.cudatec.flow.framework.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
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
import org.snaker.engine.entity.Task;
import org.snaker.engine.entity.WorkItem;
import org.snaker.engine.helper.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;

import com.cudatec.flow.framework.dao.TaskManageDao;
import com.cudatec.flow.framework.service.SnakerEngineFacets;
import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.dal.bean.Admin;
import com.zqkj.bsm.system.action.dao.SysUserDao;
import com.zqkj.bsm.util.BHUtil;
import com.zqkj.bsm.util.ClientInfoUtils;
import com.zqkj.bsm.util.CommonUtil;

@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results({@Result(name = "start", location = "/WEB-INF/jsp/flow/kq/kq.jsp"),
    @Result(name = "all", location = "/WEB-INF/jsp/flow/all/all.jsp"),
    @Result(name = "approve", location = "/WEB-INF/jsp/flow/kq/approve.jsp"),
    @Result(name = "activeTask", location = "/WEB-INF/jsp/flow/activeTask.jsp"),
    @Result(name = "error", location = "/failure.jsp")})
@ExceptionMappings({@ExceptionMapping(exception = "java.lange.RuntimeException", result = "error")})
@Action(value = "kq")
public class KqAction extends BaseAction
{
    
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private SnakerEngineFacets facets;
    
    /*
     * 
     * 部门工作流程启动
     */
    public String allKq()
    {
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
    
    public String handler()
        throws UnsupportedEncodingException, ParseException
    {
        String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
        String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
        String taskId = CommonUtil.nullToDefault(request.getParameter("taskId"), "");
        String label = CommonUtil.nullToDefault(request.getParameter("label"), "");
        Admin admin = (Admin)request.getSession().getAttribute("admin");
        String msg = null;
        label = URLDecoder.decode(label, "UTF-8");
        Map<String, Object> map = facets.getCs(processId, orderId, taskId, admin);
        map.put("userName", admin.getTruename());
        map.put("thisTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        map.put("path", "kqView"); // 请假表单查看result
        map.put("action", "kq"); // 请假表单查看action
        if (StringHelper.isEmpty(orderId) || map.get("flag") != null)
        {
            map.put("year", BHUtil.getTime_year());
            map.put("month", BHUtil.getTime_month());
            msg = "start";
        }
        else
        {
            msg = "approve";
        }
        map.put("label", label);
        map.put("bakurl", "kq!active.action?processId=" + processId);
        request.setAttribute("map", map);
        return msg;
    }
    
    public void showDays()
    {
        String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
        String year = CommonUtil.nullToDefault(request.getParameter("year"), BHUtil.getTime_year());
        String month = CommonUtil.nullToDefault(request.getParameter("month"), BHUtil.getTime_month());
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        int days = 0;
        Admin admin = (Admin)request.getSession().getAttribute("admin");
        if (!orderId.equals(""))
        {
            Map<String, Object> map2 = TaskManageDao.getInstance().getForm_List(orderId);
            days =
                BHUtil.getDaysByYearMonth(Integer.parseInt(map2.get("year").toString()),
                    Integer.parseInt(map2.get("month").toString()));
            List<Map<String, Object>> kq_his = TaskManageDao.getInstance().getKqList(orderId);
            for (Map<String, Object> mapKq : kq_his)
            {
                Map<String, Object> mapResult = new HashMap<>();
                mapResult.put("TRUENAME", mapKq.get("TRUENAME"));
                mapResult.put("ADMIN_ID", mapKq.get("ADMIN_ID"));
                List<Integer> listK = new ArrayList<Integer>();
                JSONArray jsonArray = JSONArray.fromObject(mapKq.get("MSG").toString());
                for (int i = 0; i < jsonArray.size(); i++)
                {
                    listK.add(jsonArray.getInt(i));
                }
                mapResult.put("dayList", listK);
                list.add(mapResult);
            }
            map.put("MSG", map2.get("msg"));
        }
        else
        {
            days = BHUtil.getDaysByYearMonth(Integer.parseInt(year), Integer.parseInt(month));
            list = SysUserDao.getInstance().getOrgUser(admin.getOrg_id());
            for (Map<String, Object> maps : list)
            {
                List<Integer> ss = new ArrayList<>();
                for (int i = 1; i <= days; i++)
                {
                    String week = BHUtil.getDayOfWeekByDate(year + "-" + month + "-" + i);
                    if (week.equals("星期六") || week.equals("星期日"))
                    {
                        ss.add(1);
                        ss.add(1);
                    }
                    else
                    {
                        ss.add(0);
                        ss.add(0);
                    }
                }
                maps.put("dayList", ss);
            }
        }
        map.put("list", list);
        map.put("days", days);
        writeGson(response, map);
    }
    
    public void process()
        throws IOException, ParseException
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
        Map<String, Object> params = this.dealParams(paraNames, null);
        Map<String, Object> mapTitle = new HashMap<String, Object>();
        mapTitle.put("ip", ClientInfoUtils.getIP(request));
        params.put("ip", ClientInfoUtils.getIP(request));
        // 下步流程的taskId
        String newTaskId = null;
        String displayName = null;
        String sql = null;
        boolean flag = true;
        // 状态 0 ，流程第一次发起，1，流程第N次发起
        String status = "0";
        // 第一步流程信息
        Map<String, Object> startMap = null;
        if (StringHelper.isEmpty(orderId))
        {
            // 执行第一步流程
            mapTitle.put("title", admin.getTruename() + "_" + params.get("title"));
            mapTitle.put("path", "kqView");
            map = facets.startAndExecute(processId, admin.getName(), mapTitle);
            orderId = map.get("orderId");
            taskId = map.get("taskId");
            newTaskId = map.get("newTaskId");
            displayName = map.get("displayName");
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
        // 最新流程Id
        
        // 对领导审批进行分类处理,(一个流程重多次重复使用approve.jsp)
        Map<String, Object> newParams = changeParam(params, taskName);
        String json = null;
        if (newParams.get("json") != null)
        {
            json = newParams.get("json").toString();
            newParams.remove("json");
            TaskManageDao.getInstance().saveKq(json,
                Integer.parseInt(newParams.get("year").toString()),
                Integer.parseInt(newParams.get("month").toString()),
                orderId,
                admin.getOrg_id());
        }
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
                    "kq!allKq.action",
                    displayName);
            }
            else
            {
                addMsg(startMap.get("operator").toString(),
                    orderId,
                    newTaskId,
                    processId,
                    "kq!allKq.action",
                    displayName);
            }
        }else{
        	Map<String, Object> mapResult = TaskManageDao.getInstance().getForm_List(orderId);
        	List<Map<String, Object>> list = TaskManageDao.getInstance().getKqList(orderId);
        	ArrayList<Object[]> listObjs = new ArrayList<Object[]>();
        	StringBuffer sb = new StringBuffer();
        	for (Map<String, Object> map2 : list) {
				String year = map2.get("YEAR").toString();
				String month = map2.get("MONTH").toString();
				String TRUENAME = map2.get("TRUENAME").toString();
				String ADMIN_ID = map2.get("ADMIN_ID").toString();
				sb.append(Integer.parseInt(ADMIN_ID)+",");
				JSONArray array = JSONArray.fromObject(map2.get("MSG").toString());
				for (int i = 0; i < array.size(); i++) {
					int val = array.getInt(i); 
					if(val != 0){
						Object[] object = new Object[5];
						object[0] = new SimpleDateFormat("yyyy-MM-dd").parse(year+"-"+month+"-"+(i % 2 == 0 ? (i+2)/2 : (i+2)/2)); 
						object[1] = i % 2 == 0 ? 1 : 2;
						object[2] = Integer.valueOf(ADMIN_ID);
						object[3] = val;
						object[4] = TRUENAME;
						listObjs.add(object);
					}
				}
			}
        	TaskManageDao.getInstance().deleteHisWorkLog(mapResult.get("year").toString(),mapResult.get("month").toString(),sb.toString());
        	TaskManageDao.getInstance().insertWorkLog(listObjs);
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
        request.setAttribute("ccorderTotal", ccorderPage.getTotalCount());
        request.setAttribute("processId", processId);
        return "activeTask";
    }
}
