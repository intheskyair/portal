package com.cudatec.flow.framework.action;

/**
 * 部门工作报表
 * 
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
import org.snaker.engine.entity.HistoryOrder;
import org.snaker.engine.entity.WorkItem;
import org.snaker.engine.helper.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;

import com.cudatec.flow.framework.dao.TaskManageDao;
import com.cudatec.flow.framework.service.ApprovalManager;
import com.cudatec.flow.framework.service.SnakerEngineFacets;
import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.dal.bean.Admin;
import com.zqkj.bsm.util.BHUtil;
import com.zqkj.bsm.util.ClientInfoUtils;
import com.zqkj.bsm.util.CommonUtil;

import net.sf.json.JSONArray;

@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results({@Result(name = "all", location = "/WEB-INF/jsp/flow/all/all.jsp"),
    @Result(name = "approve", location = "/WEB-INF/jsp/flow/all/approve.jsp"),
    @Result(name = "bmgz", location = "/WEB-INF/jsp/flow/bmgz/bmgz.jsp"),
    @Result(name = "activeTask", location = "/WEB-INF/jsp/flow/activeTask.jsp"),
    @Result(name = "error", location = "/failure.jsp")})
@ExceptionMappings({@ExceptionMapping(exception = "java.lange.RuntimeException", result = "error")})
@Action(value = "bmgz")
public class BmgzAction extends BaseAction
{
    
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private SnakerEngineFacets facets;
    
    @Autowired
    private ApprovalManager manager;
    
    /*
     * 
     * 部门工作流程启动
     */
    public String allBmgz()
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
    
    /**
     * @throws UnsupportedEncodingException 部门工作，设定到步骤配置里面的表单URL
     * 
     * @Title: plan
     * @Description:
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public String bmgz()
        throws UnsupportedEncodingException
    {
        String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
        String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
        String taskId = CommonUtil.nullToDefault(request.getParameter("taskId"), "");
        Admin admin = (Admin)request.getSession().getAttribute("admin");
        String label = CommonUtil.nullToDefault(request.getParameter("label"), "");
        label = URLDecoder.decode(label, "UTF-8");
        Map<String, Object> map = facets.chooseStep(processId, orderId, taskId, admin);
        map.put("userName", admin.getTruename());
        map.put("thisTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        map.put("path", "bmgzView"); // 请假表单查看result
        map.put("action", "bmgz"); // 请假表单查看action
        map.put("label", label);
        map.put("bakurl", "bmgz!active.action?processId=" + processId);
        request.setAttribute("processId", processId);
        request.setAttribute("orderId", orderId);
        request.setAttribute("taskId", taskId);
        if (StringHelper.isEmpty(orderId))
        {
            map.put("year", BHUtil.getTime_year());
            map.put("month", BHUtil.getTime_month());
            map.put("day", BHUtil.getTime_date());
        }else{
      	  int status = 0;
    	  List<Map<String, String>> list =new ArrayList<>();
    	  for (String key : map.keySet()) {
			if(key.contains("val")){
				status++;
			}
		}
    	 for (int i = 1; i <= status /3 ; i++) {
    		 Map<String, String> map1 = new HashMap<String,String>();
    		 map1.put("one", map.get("val"+(i * 3 - 2)).toString());
    		 map1.put("two", map.get("val"+(i * 3 - 1)).toString());
    		 map1.put("three", map.get("val"+(i * 3)).toString());
    		 list.add(map1);
		}
    	  //request.setAttribute("status", status);
    	  request.setAttribute("list", list);
        }
        map.put("userName", admin.getTruename());
        map.put("thisTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        request.setAttribute("map", map);
        if (map.get("step").toString().equals("1"))
        {
            return "bmgz";
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
        if (StringHelper.isEmpty(orderId))
        {
            // 执行第一步流程
            mapTitle.put("title", admin.getTruename() + "_" + params.get("title"));
            mapTitle.put("path", "bmgzView");
            map = facets.startAndExecute(processId, admin.getName(), mapTitle);
            orderId = map.get("orderId");
            taskId = map.get("taskId");
        }
        else
        {
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
                    "bmgz!allBmgz.action",
                    displayName);
            }
            else
            {
                addMsg(map.get("startUser"), orderId, newTaskId, processId, "bmgz!allBmgz.action", displayName);
            }
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
