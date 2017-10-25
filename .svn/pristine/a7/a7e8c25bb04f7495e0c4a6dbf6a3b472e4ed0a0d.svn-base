package com.cudatec.flow.framework.action;

/*
 * 工作计划
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
import org.springframework.beans.factory.annotation.Autowired;

import com.cudatec.flow.framework.dao.TaskManageDao;
import com.cudatec.flow.framework.service.ApprovalManager;
import com.cudatec.flow.framework.service.SnakerEngineFacets;
import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.dal.bean.Admin;
import com.zqkj.bsm.system.action.page.OraPaginatedList;
import com.zqkj.bsm.util.ClientInfoUtils;
import com.zqkj.bsm.util.CommonUtil;
import com.zqkj.bsm.util.PageData;

@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results({@Result(name = "all", location = "/WEB-INF/jsp/flow/all/all.jsp"),
    @Result(name = "approve", location = "/WEB-INF/jsp/flow/all/approve.jsp"),
    @Result(name = "summary", location = "/WEB-INF/jsp/flow/plan/summary.jsp"),
    @Result(name = "summary1", location = "/WEB-INF/jsp/flow/plan/summary1.jsp"),
    @Result(name = "gzjh_j", location = "/WEB-INF/jsp/flow/plan/gzjh_j.jsp"),
    @Result(name = "gzjh_y", location = "/WEB-INF/jsp/flow/plan/gzjh_y.jsp"),
    @Result(name = "activeTask", location = "/WEB-INF/jsp/flow/activeTask.jsp"),
    @Result(name = "error", location = "/failure.jsp")})
@ExceptionMappings({@ExceptionMapping(exception = "java.lange.RuntimeException", result = "error")})
@Action(value = "plan")
/**
 * 工作计划流程
 * @author MJ001 
 *
 */
public class PlanAction extends BaseAction
{
    
    private static final long serialVersionUID = 1L;
    
    @Autowired
    SnakerEngineFacets facets;
    
    @Autowired
    ApprovalManager manager;
    
    /**
     * 工作计划流程启动
     * 
     * @return
     */
    public String allPlan()
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
     * 个人季度计划与总结表单
     * 
     * @Title: plan
     * @Description:
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public String summary()
    {
        String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
        String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
        String taskId = CommonUtil.nullToDefault(request.getParameter("taskId"), "");
        Admin amdin = (Admin)request.getSession().getAttribute("admin");
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
        // 流程开始
        
        Map<String, Object> map = facets.chooseStep(processId, orderId, taskId, amdin);
        map.put("userName", amdin.getTruename());
        map.put("thisTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        map.put("path", "planView"); // 所有页面必传参数
        map.put("action", "plan"); // //所有页面必传参数
        // 返回表单访问路径
        map.put("label", label);
        map.put("bakurl", "plan!active.action?processId=" + processId);
        String step = map.get("step").toString();
        if (step.equals("2"))
        {
            map.put("assignee", "-2");
            // -2.则返回第一步操作人
        }
        String path = null;
        switch (step)
        {
            case "1":
                path = "summary";
                break;
            case "3":
                path = "summary1";
                break;
            default:
                path = "approve";
                break;
        }
        request.setAttribute("map", map);
        return path;
        
    }
    
    public void process()
        throws IOException
    {
        ArrayList<Object[]> listObj = new ArrayList<Object[]>();
        String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
        String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
        String taskId = CommonUtil.nullToDefault(request.getParameter("taskId"), "");
        String title = CommonUtil.nullToDefault(request.getParameter("title"), "");
        String method = CommonUtil.nullToDefault(request.getParameter("method"), "0");
        String taskName = CommonUtil.nullToDefault(request.getParameter("taskName"), "");
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
            params_title.put("path", "planView");
            map = facets.startAndExecute(processId, admin.getName(), params_title);
            orderId = map.get("orderId");
            taskId = map.get("taskId");
            newTaskId = map.get("newTaskId");
            params.put("name", admin.getTruename()); // 第一步保存流程发起用户
            params.put("work", admin.getOrg_infor()); // 第一步保存流程发起用户部门
            params.put("section", admin.getOrg_name()); // 第一步保存流程用户发起职位
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
                    "plan!allPlan.action",
                    displayName);
            }
            else
            {
                addMsg(map.get("startUser"), orderId, newTaskId, processId, "plan!allPlan.action", displayName);
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
    
    public String gzjhList()
    {
        String operation = request.getParameter("operation");
        int type_sf = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("type"), "0"));
        if ("init".equals(operation))
        {
            // 检索参数
            int pageNum = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagenum"), "1")) - 1;
            int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "10"));
            int type = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("type"), "0"));
            String reference = request.getParameter("reference") == null ? "" : request.getParameter("reference");
            String refer = request.getParameter("refer") == null ? "" : request.getParameter("refer");
            String overtime = request.getParameter("overtime") == null ? "" : request.getParameter("overtime");
            String sw_type = request.getParameter("sw_type") == null ? "" : request.getParameter("sw_type");
            String bcqx = request.getParameter("bcqx") == null ? "" : request.getParameter("bcqx");
            String sfgk = request.getParameter("sfgk") == null ? "" : request.getParameter("sfgk");
            // 分页封装方法
            OraPaginatedList list_tmp =
                TaskManageDao.getInstance().getGwgdList(sw_type,
                    bcqx,
                    type,
                    reference,
                    refer,
                    overtime,
                    sfgk,
                    pageNum,
                    pageSize);
            // 分页对象
            PageData pg =
                CommonUtil.fomateResult(list_tmp.getList(), pageNum + 1, pageSize, list_tmp.getFullListSize());
            pg.setList(pg.getList());
            writeJson(response, CommonUtil.formatFGMap(pg));
            return null;
        }
        else
        {
            if (type_sf == 0)
            {
                return "swgdList";
            }
            else
            {
                return "fwgdList";
            }
        }
        
    }
    
    public String planList()
    {
        String operation = request.getParameter("operation");
        String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
        int type = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("type"), "0"));
        if ("init".equals(operation))
        {
            // 检索参数
            int pageNum = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagenum"), "1")) - 1;
            int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "10"));
            String yd = CommonUtil.nullToDefault(request.getParameter("yd"), "");
            String jd = CommonUtil.nullToDefault(request.getParameter("jd"), "");
            String creator = CommonUtil.nullToDefault(request.getParameter("creator"), "");
            // 分页封装方法
            OraPaginatedList list_tmp = TaskManageDao.getInstance().getPlanList(type, pageNum, pageSize, processId);
            // 分页对象
            PageData pg =
                CommonUtil.fomateResult(list_tmp.getList(), pageNum + 1, pageSize, list_tmp.getFullListSize());
            List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> list = pg.getList();
            List<Map<String, Object>> planOrderList = TaskManageDao.getInstance().getPlanOrderList(processId, type);
            for (Map<String, Object> map : list)
            {
                for (Map<String, Object> mapOrder : planOrderList)
                {
                    if (map.get("ID").toString().equals(mapOrder.get("ORDERID").toString()))
                    {
                        // 季月
                        if (mapOrder.get("HTML_KEY").toString().equals("yd"))
                        {
                            map.put("YD", mapOrder.get("HTML_VALUE"));
                        }
                        // 季月
                        if (mapOrder.get("HTML_KEY").toString().equals("jd"))
                        {
                            map.put("JD", mapOrder.get("HTML_VALUE"));
                        }
                        // 工作计划
                        if (mapOrder.get("HTML_KEY").toString().equals("gzjh"))
                        {
                            map.put("GZJH", mapOrder.get("HTML_VALUE"));
                        }
                        // 处室领导意见
                        if (mapOrder.get("HTML_KEY").toString().equals("csldyjmethod"))
                        {
                            map.put("CSLDYJ", mapOrder.get("HTML_VALUE").toString().equals("0") ? "同意" : "不同意");
                        }
                        // 工作总结
                        if (mapOrder.get("HTML_KEY").toString().equals("gzzj"))
                        {
                            map.put("GZZJ", mapOrder.get("HTML_VALUE"));
                        }
                        // 处室领导评价
                        if (mapOrder.get("HTML_KEY").toString().equals("csldpjmethod"))
                        {
                            map.put("CSLDPJ", mapOrder.get("HTML_VALUE").toString().equals("0") ? "同意" : "不同意");
                        }
                        // 分管领导评价
                        if (mapOrder.get("HTML_KEY").toString().equals("fgldpjmethod"))
                        {
                            map.put("FGLDPJ", mapOrder.get("HTML_VALUE").toString().equals("0") ? "同意" : "不同意");
                        }
                    }
                }
                // 流程未结束
                if (map.get("ORDER_STATE").equals("1"))
                {
                    if (!map.containsKey("CSLDYJ"))
                    {
                        map.put("CSLDYJ", "未审核");
                    }
                    if (!map.containsKey("GZZJ"))
                    {
                        map.put("GZZJ", "未总结");
                    }
                    if (!map.containsKey("CSLDPJ"))
                    {
                        map.put("CSLDPJ", "未审核");
                    }
                    if (!map.containsKey("FGLDPJ"))
                    {
                        map.put("FGLDPJ", "未审核");
                    }
                }
                boolean flag = true;
                if (!yd.equals(""))
                {
                    if (!yd.equals(map.get("YD").toString()))
                    {
                        flag = false;
                    }
                }
                if (!jd.equals(""))
                {
                    if (!jd.equals(map.get("JD").toString()))
                    {
                        flag = false;
                    }
                    
                }
                if (!creator.equals(""))
                {
                    if (!(map.get("TRUENAME").toString()).contains(creator))
                    {
                        flag = false;
                    }
                }
                if (flag)
                {
                    newList.add(map);
                }
            }
            pg.setList(newList);
            writeJson(response, CommonUtil.formatFGMap(pg));
            return null;
        }
        else
        {
            request.setAttribute("processId", processId);
            request.setAttribute("type", type);
            if (type == 0)
            {
                return "gzjh_j";
            }
            else
            {
                return "gzjh_y";
            }
        }
        
    }
    
}
