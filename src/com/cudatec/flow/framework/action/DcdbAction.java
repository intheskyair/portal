package com.cudatec.flow.framework.action;

/**
 *督查督办
 */
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
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

@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results({@Result(name = "all", location = "/WEB-INF/jsp/flow/all/all.jsp"),
    @Result(name = "check", location = "/WEB-INF/jsp/flow/dcdb/check.jsp"),
    @Result(name = "dcdb", location = "/WEB-INF/jsp/flow/dcdb/dcdb.jsp"),
    @Result(name = "activeTask", location = "/WEB-INF/jsp/flow/activeTask.jsp"),
    @Result(name = "error", location = "/failure.jsp")})
@ExceptionMappings({@ExceptionMapping(exception = "java.lange.RuntimeException", result = "error")})
@Action(value = "dcdb")
public class DcdbAction extends BaseAction
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
    public String allDcdb()
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
     * 部门工作，设定到步骤配置里面的表单URL
     * 
     * @Title: plan
     * @Description:
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public String dcdb()
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
        if (StringHelper.isEmpty(orderId))
        {
            map.put("year", BHUtil.getTime_year());
            map.put("month", BHUtil.getTime_month());
            map.put("day", BHUtil.getTime_date());
        }
        else
        {
            map = TaskManageDao.getInstance().getForm_List(orderId);
        }
        map.put("label", label);
        map.put("bakurl", "dcdb!active.action?processId=" + processId);
        map.put("step", step);
        map.put("processId", processId);
        map.put("orderId", orderId);
        map.put("taskId", taskId);
        request.setAttribute("map", map);
        if (!step.equals("1"))
        {
        	int tabValStatus = 0;
        	int tabGgValStataus = 0;
        	for (String key : map.keySet()) {
				if(key.contains("tabval")){
					tabValStatus ++;
				}
				if(key.contains("tabGgval")){
					tabGgValStataus ++;
				}
			}
        	List<Map<String, String>> tabList = new ArrayList<Map<String,String>>();
        	List<Map<String, String>> tabGgList = new ArrayList<Map<String,String>>();
        	for (int i = 1; i <=tabValStatus ; i++) {
        		Map<String, String> map1 = new HashMap<String,String>();
        		map1.put("one", map.get("tabval"+(i * 3 - 2)).toString());
        		tabList.add(map1);
			}
        	
        	for (int i = 1; i <=tabGgValStataus ; i++) {
        		Map<String, String> map2 = new HashMap<String,String>();
        		map2.put("one", map.get("tabGgval"+(i * 3 - 2)).toString());
        		tabGgList.add(map2);
			}
        	request.setAttribute("tabList", tabList);
        	request.setAttribute("tabGgList", tabGgList);
            return "check";
        }
        else
        {
            return "dcdb";
        }
        
    }
    
    public void process()
        throws IOException
    {
        ArrayList<Object[]> listObj = new ArrayList<Object[]>();
        String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
        String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
        String taskId = CommonUtil.nullToDefault(request.getParameter("taskId"), "");
        Admin admin = (Admin)session.getAttribute("admin");
        Map<String, String> map = null;
        Enumeration<String> paraNames = request.getParameterNames();
        Map<String, Object> params = this.dealParams(paraNames, null);
        Map<String, Object> mapTitle = new HashMap<String, Object>();
        mapTitle.put("ip", ClientInfoUtils.getIP(request));
        // 下步流程的taskId
        String newTaskId = null;
        if (StringHelper.isEmpty(orderId))
        {
            // order示例中保存标题
            
            mapTitle.put("title", admin.getAuth_name() + "_责任制完成情况督查表");
            mapTitle.put("path", "dcdbView");
            map = facets.startAndExecute(processId, admin.getName(), mapTitle);
            orderId = map.get("orderId");
            taskId = map.get("taskId");
            newTaskId = map.get("newTaskId");
            params.put("year", BHUtil.getTime_year());
            params.put("month", BHUtil.getTime_month());
            params.put("day", BHUtil.getTime_date());
            facets.getEngine().task().addTaskActor(newTaskId, new String[] {params.get("uid").toString()});
            addMsg(params.get("uid").toString(),
                orderId,
                newTaskId,
                processId,
                "dcdb!allDcdb.action",
                map.get("displayName"));
        }
        else
        {
            // 审批完成后，去掉提醒
            facets.execute(taskId, admin.getName(), mapTitle);
            manager.updateMessage(admin.getName(), taskId, orderId);
        }
        listObj = TaskManageDao.getInstance().save(params, orderId, taskId);
        // 保存表单信息
        manager.executeBatch_Pre(BATCH_SQL, listObj);
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
    /**
     * 
     * 获取单位列表
     * 
     * @Title: getUnitList
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param 设定文件
     * @return void 返回类型
     * @throws
     */
    
}
