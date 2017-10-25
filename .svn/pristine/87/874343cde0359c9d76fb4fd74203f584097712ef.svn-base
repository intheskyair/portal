package com.zqkj.bsm.carmanager.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
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
import org.snaker.engine.entity.HistoryOrder;
import org.snaker.engine.entity.WorkItem;
import org.snaker.engine.helper.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;

import com.cudatec.flow.framework.dao.PersonalProcessDao;
import com.cudatec.flow.framework.dao.TaskManageDao;
import com.cudatec.flow.framework.service.SnakerEngineFacets;
import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.carmanager.dao.CarManagerDao;
import com.zqkj.bsm.dal.bean.Admin;
import com.zqkj.bsm.system.action.page.OraPaginatedList;
import com.zqkj.bsm.util.ClientInfoUtils;
import com.zqkj.bsm.util.CommonUtil;
import com.zqkj.bsm.util.PageData;

/**
 * 
 * @ClassName: CarManagerAction
 * @Description: TODO(车辆信息管理业务处理)
 * @author wangwj
 * @date 2016年4月21日 下午1:50:17
 * 
 */
@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results({@Result(name = "all", location = "/WEB-INF/jsp/flow/all/all.jsp"),
    @Result(name = "queryCarInfo", location = "/WEB-INF/jsp/car/carInfo.jsp"),
    @Result(name = "car", location = "/WEB-INF/jsp/car/car.jsp"),
    @Result(name = "carApplyList", location = "/WEB-INF/jsp/car/carApplyList.jsp"),
    @Result(name = "approve", location = "/WEB-INF/jsp/flow/all/approve.jsp"),
    @Result(name = "activeTask", location = "/WEB-INF/jsp/flow/activeTask.jsp"),
    @Result(name = "leaveView", location = "", type = "redirect"),})
@ExceptionMappings({@ExceptionMapping(exception = "java.lange.RuntimeException", result = "error")})
@Action(value = "carManager")
public class CarManagerAction extends BaseAction
{
    @Autowired
    private SnakerEngineFacets snakerEngine;
    
    private static final long serialVersionUID = 1L;
    
    public String listCarInfo()
    {
        String operation = CommonUtil.nullToDefault(request.getParameter("operation"), "");
        if (!"".equals(operation))
        {
            int pageNum = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagenum"), "1")) - 1;
            int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "30"));
            Enumeration<String> paraNames = request.getParameterNames();
            Map<String, Object> params_all = this.dealParams(paraNames, null);
            // 分页封装方法
            OraPaginatedList list_tmp = CarManagerDao.getInstance().queryCarInfo(params_all, pageNum, pageSize);
            // 分页对象
            PageData pg =
                CommonUtil.fomateResult(list_tmp.getList(), pageNum + 1, pageSize, list_tmp.getFullListSize());
            writeJson(response, CommonUtil.formatFGMap(pg));
            return null;
        }
        return "queryCarInfo";
    }
    
    public String createCar()
    {
        Map<String, Object> params_all = this.dealParams(request.getParameterNames(), null);
        writeJson_Str(response, CarManagerDao.getInstance().saveCarInfo(params_all) ? "{\"result\":\"00\"}"
            : "{\"result\":\"01\"}");
        return null;
    }
    
    /**
     * 判断车是否已存在
     * 
     * @throws Exception
     */
    public void judgeCarNo()
        throws Exception
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return = "";
        String carNo = CommonUtil.nullToDefault(request.getParameter("carNo"), "");
        long id = Long.parseLong(CommonUtil.nullToDefault(request.getParameter("id"), "0"));
        CarManagerDao cd = CarManagerDao.getInstance();
        if (cd.judgeCarNo(carNo, id))
        {
            res_return = "ok";
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            out.close();
        }
    }
    
    public String getCarInfoById()
    {
        String carId = CommonUtil.nullToDefault(request.getParameter("carId"), "-1");
        Map<String, Object> carInfo = CarManagerDao.getInstance().queryCarInfoById(carId);
        writeJson(response, carInfo);
        return null;
    }
    
    public String getCarInfoByCarStatus()
    {
        List<Map<String, Object>> carInfo = CarManagerDao.getInstance().queryCarInfoByCarStatus();
        writeJson(response, carInfo);
        return null;
        
    }
    
    public String modifyCarInfoById()
    {
        Map<String, Object> params_all = this.dealParams(request.getParameterNames(), null);
        writeJson_Str(response, CarManagerDao.getInstance().updateCarInfo(params_all) ? "{\"result\":\"00\"}"
            : "{\"result\":\"01\"}");
        return null;
    }
    
    public String removeCarInfoById()
    {
        String carId = CommonUtil.nullToDefault(request.getParameter("carId"), "-1");
        writeJson_Str(response, CarManagerDao.getInstance().deleteCarInfo(carId) ? "{\"result\":\"00\"}"
            : "{\"result\":\"01\"}");
        return null;
    }
    
    public String returnCar()
    {
        // boolean f = false;
        String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
        Map<String, Object> map = TaskManageDao.getInstance().getForm_List(orderId);
        
        writeJson_Str(response,
            CarManagerDao.getInstance().updateCarInfo(CommonUtil.nullToDefault(map.get("car_no"), ""), "1") ? "{\"result\":\"00\"}"
                : "{\"result\":\"01\"}");
        
        return null;
    }
    
    /**
     * 导出数据
     * 
     * @throws IOException
     */
    public void exportJobs()
        throws IOException
    {
        request.setCharacterEncoding("utf-8");
        // 检索参数
        Enumeration<String> paraNames = request.getParameterNames();
        Map<String, Object> params_all = this.dealParams(paraNames, null);
        // 按条件查询中所有需要导出的数据 规划项目列表
        List<Map<String, Object>> items = CarManagerDao.getInstance().queryCars(params_all);
        
        String file_name = "车辆信息";
        // 导出的字段和excel表头的对应关系
        Map<String, String> map_columns = CarManagerDao.getInstance().getList_Column();
        this.exportGK(items, map_columns, file_name);
    }
    
    /**
     * 流程启动
     * 
     * @return
     */
    
    public String allCar()
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
     * 车辆申请，设定到步骤配置里面的表单URL
     * 
     * @Title: plan
     * @Description:
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public String carApply()
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
        Map<String, Object> map = facets.chooseStep(processId, orderId, taskId, admin);
        map.put("path", "carView"); // 请假表单查看result
        map.put("action", "carManager"); // 请假表单查看action
        map.put("label", label);
        map.put("bakurl", "carManager!active.action?processId=" + processId);
        request.setAttribute("map", map);
        request.setAttribute("processId", processId);
        request.setAttribute("orderId", orderId);
        request.setAttribute("taskId", taskId);
        if (map.get("step").toString().equals("1"))
        {
            return "car";
        }
        else
        {
            return "approve";
        }
        
    }
    
    /**
     * 工作流流转的主要处理方法
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
        String step = CommonUtil.nullToDefault(request.getParameter("step"), "0");
        Admin admin = (Admin)session.getAttribute("admin");
        Map<String, String> map = new HashMap<String, String>();
        Enumeration<String> paraNames = request.getParameterNames();
        Map<String, Object> params = this.dealParams(paraNames, null);
        // 下步流程的taskId
        String newTaskId = null;
        String displayName = null;
        String sql = null;
        boolean flag = true;
        boolean car_status = true;
        List<Map<String, Object>> list = CarManagerDao.getInstance().queryCarInfoByCarStatus();
        if (StringHelper.isEmpty(orderId))
        {
            // 判断车辆的状态是否空闲,如果被占用了，直接返回失败
            if (list == null || list.size() == 0)
            {
                
                car_status = false;
            }
            if (car_status)
            {
                // 执行第一步流程
                Map<String, Object> mapTitle = new HashMap<String, Object>();
                mapTitle.put("ip", ClientInfoUtils.getIP(request));
                mapTitle.put("title", params.get("title"));
                mapTitle.put("path", "carView");
                map = facets.startAndExecute(processId, admin.getName(), mapTitle);
                orderId = map.get("orderId");
                taskId = map.get("taskId");
                params.put("name", admin.getTruename()); // 用户
                params.put("section", admin.getOrg_name()); // 部门
                params.put("work", admin.getOrg_infor());// 职务
                CarManagerDao.getInstance().updateCarInfo(CommonUtil.nullToDefault(params.get("car_no"), ""), "2");
            }
        }
        else
        {
            
            // 执行下一步流程
            map = nextFlowHandle(orderId, taskId, taskName, params, method, admin, processId);
            // 说明流程被回退过
            if (step.equals("1"))
            {
                CarManagerDao.getInstance().updateCarInfo(CommonUtil.nullToDefault(params.get("car_no"), ""), "2");
            }
            flag = map.get("status").equals("0") ? true : false;
            if (!method.equals("0"))
            {
                Map<String, Object> map_ = TaskManageDao.getInstance().getForm_List(orderId);
                CarManagerDao.getInstance().updateCarInfo(CommonUtil.nullToDefault(map_.get("car_no"), ""), "1");
            }
        }
        if (car_status)
        {
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
            // 保存表单信息到flow_form
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
                    facets.getEngine()
                        .task()
                        .addTaskActor(newTaskId, 0, newParams.get("userList").toString().split(","));
                    // 普通步骤进行流程提醒
                    addMsg(newParams.get("userList").toString(),
                        orderId,
                        newTaskId,
                        processId,
                        "carManager!allCar.action",
                        displayName);
                }
                else
                {
                    addMsg(map.get("startUser"), orderId, newTaskId, processId, "carManager!allCar.action", displayName);
                }
            }
            
            response.getWriter().write("success");
        }
        else
        {
            response.getWriter().write("fail");
        }
    }
    
    /**
     * 流程的代办事项
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
     * 自己发起的车辆申请流程
     * 
     * @return
     */
    public String carApplyList()
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
            return "carApplyList";
        }
        
    }
    
    public void viewStep()
    {
        String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
        List<Map<String, Object>> listHisTask = TaskManageDao.getInstance().getHisTaskList(orderId);
        List<Map<String, Object>> listTask = TaskManageDao.getInstance().getTaskList(orderId);
        Map<String, Object> map = new HashMap<String, Object>();
        listHisTask.addAll(listTask); // 流程进行中
        if (listTask.size() == 0)
        {
            map.put("status", "end"); // 流程结束
            listHisTask.add(map);
        }
        
        writeGson(response, listHisTask);
    }
    
}
