package com.cudatec.flow.framework.action;

/**
 * 流程总览
 */
import java.util.ArrayList;
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
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.helper.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;

import com.cudatec.flow.framework.dao.TaskManageDao;
import com.cudatec.flow.framework.service.SnakerEngineFacets;
import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.system.action.page.OraPaginatedList;
import com.zqkj.bsm.util.CommonUtil;
import com.zqkj.bsm.util.PageData;

@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results({@Result(name = "LczlList", location = "/WEB-INF/jsp/flow/lczl.jsp"),
    @Result(name = "dcdbList", location = "/WEB-INF/jsp/flow/dcdb/dcdbzl.jsp"),
    @Result(name = "LczlList_pro", location = "/WEB-INF/jsp/flow/LczlList_pro.jsp"),
    @Result(name = "error", location = "/failure.jsp")})
@ExceptionMappings({@ExceptionMapping(exception = "java.lange.RuntimeException", result = "error")})
@Action(value = "lczl")
public class LczlAction extends BaseAction
{
    
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;
    
    @Autowired
    SnakerEngineFacets facets;
    
    public String LczlList()
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
            // 分页封装方法
            OraPaginatedList list_tmp =
                TaskManageDao.getInstance().getList(startTime, endTime, pageNum, pageSize, processId);
            // 分页对象
            PageData pg =
                CommonUtil.fomateResult(list_tmp.getList(), pageNum + 1, pageSize, list_tmp.getFullListSize());
            List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> list = pg.getList();
            for (Map<String, Object> map : list)
            {
                JSONObject object = JSONObject.fromObject(map.get("VARIABLE").toString());
                map.put("VARIABLE", object.get("title"));
                map.put("PATH", object.get("path"));
                ;
                if (StringHelper.isNotEmpty(title))
                {
                    if (object.get("title").toString().contains(title))
                    {
                        newList.add(map);
                    }
                }
                else
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
            if (StringHelper.isNotEmpty(processId))
            {
                request.setAttribute("processId", processId);
                return "dcdbList";
            }
            else
            {
                return "LczlList";
            }
        }
        
    }
    
    /**
     * 根据流程的类别来查询所有的流程数据
     * 
     * @return
     */
    public String LczlList_pro()
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
            // 分页封装方法
            OraPaginatedList list_tmp =
                TaskManageDao.getInstance().getList(startTime, endTime, pageNum, pageSize, processId);
            // 分页对象
            PageData pg =
                CommonUtil.fomateResult(list_tmp.getList(), pageNum + 1, pageSize, list_tmp.getFullListSize());
            List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> list = pg.getList();
            for (Map<String, Object> map : list)
            {
                JSONObject object = JSONObject.fromObject(map.get("VARIABLE").toString());
                map.put("VARIABLE", object.get("title"));
                map.put("PATH", object.get("path"));
                if (StringHelper.isNotEmpty(title))
                {
                    if (object.get("title").toString().contains(title))
                    {
                        newList.add(map);
                    }
                }
                else
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
            String DISPLAY_NAME = "";
            Object oo = facets.getEngine().process().getProcessById(processId).getDisplayName();
            if (DISPLAY_NAME.equals(""))
            {
                DISPLAY_NAME = CommonUtil.nullToDefault(oo.toString(), "");
            }
            request.setAttribute("processId", processId);
            request.setAttribute("DISPLAY_NAME", DISPLAY_NAME);
            return "LczlList_pro";
        }
        
    }
    
    public void viewStep()
    {
        String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
        List<Map<String, Object>> listHisTask = TaskManageDao.getInstance().getHisTaskList(orderId);
        for (Map<String, Object> map : listHisTask)
        {
            JSONObject object = JSONObject.fromObject(map.get("VARIABLE").toString());
            map.put("IP", object.get("ip") == null ? null : object.get("ip"));
            map.put("MARK", object.get("method") == null ? null : object.get("method"));
        }
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
    
    public void viewProcess()
    {
        List<org.snaker.engine.entity.Process> list = facets.getEngine().process().getProcesss(new QueryFilter());
        writeGson(response, list);
    }
}
