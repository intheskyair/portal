package com.cudatec.flow.framework.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
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
import org.snaker.engine.entity.Process;
import org.snaker.engine.entity.Task;
import org.snaker.engine.helper.AssertHelper;
import org.snaker.engine.helper.StreamHelper;
import org.snaker.engine.helper.StringHelper;
import org.snaker.engine.model.ProcessModel;
import org.springframework.beans.factory.annotation.Autowired;

import com.cudatec.flow.framework.SnakerHelper;
import com.cudatec.flow.framework.dao.TaskManageDao;
import com.cudatec.flow.framework.service.ApprovalManager;
import com.cudatec.flow.framework.service.SnakerEngineFacets;
import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.system.action.dao.SysUserDao;
import com.zqkj.bsm.system.action.page.OraPaginatedList;
import com.zqkj.bsm.util.ClientInfoUtils;
import com.zqkj.bsm.util.CommonUtil;
import com.zqkj.bsm.util.PageData;

import net.sf.json.JSONObject;

@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results({@Result(name = "processList", location = "/WEB-INF/jsp/flow/processList.jsp"),
    @Result(name = "diagram", location = "/WEB-INF/jsp/flow/snaker/diagram.jsp"),
    @Result(name = "processDesigner", location = "/WEB-INF/jsp/flow/snaker/processDesigner.jsp"),
    @Result(name = "processEdit", location = "/WEB-INF/jsp/flow/snaker/processEdit.jsp"),
    @Result(name = "processDeploy", location = "/WEB-INF/jsp/flow/snaker/processDeploy.jsp"),
    @Result(name = "taskListManage", location = "/WEB-INF/jsp/flow/snaker/taskListManage.jsp"),
    @Result(name = "processDeployList", location = "/WEB-INF/jsp/flow/processDeployList.jsp"),
    @Result(name = "error", location = "/failure.jsp")})
@ExceptionMappings({@ExceptionMapping(exception = "java.lange.RuntimeException", result = "error")})
public class ProcessAction extends BaseAction
{
    
    private File snakerFile;
    
    public File getSnakerFile()
    {
        return snakerFile;
    }
    
    public void setSnakerFile(File snakerFile)
    {
        this.snakerFile = snakerFile;
    }
    
    @Autowired
    ApprovalManager manager;
    
    @Autowired
    SnakerEngineFacets facets;
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    @Action(value = "process")
    public String processList()
        throws Exception
    {
        // 检索参数
        
        return "processList";
    }
    
    public void getProcessList()  throws Exception
    {
        // 检索参数
        int pageNum = Integer.parseInt(request.getParameter("page"));
        int pageSize = Integer.parseInt(request.getParameter("rows"));
        
        String displayName1 = CommonUtil.nullToDefault(request.getParameter("displayName"), "");
        String displayName = URLDecoder.decode(displayName1, "utf-8");
        Page<Process> page = new Page<Process>();
        page.setPageNo(pageNum);
        page.setPageSize(pageSize);
        QueryFilter filter = new QueryFilter();
        if (StringHelper.isNotEmpty(displayName))
        {
            filter.setDisplayName(displayName);
        }
        facets.getEngine().process().getProcesss(page, filter);
        List<Process> plist = page.getResult();
        JSONObject obj = new JSONObject();
        obj.put("total", page.getTotalCount());
        obj.put("rows", plist);
        PrintWriter out = null;
        try
        {
            out = response.getWriter();
            out.write(obj.toString());
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
    
    
    /**
     * 查询所有的流程定义
     * 
     * @return
     * @throws Exception
     */
    public String proceList()
        throws Exception
    {
        // 检索参数
        int pageNum = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagenum"), "1"));
        int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "30"));
        String displayName = CommonUtil.nullToDefault(request.getParameter("displayName"), "");
        
        Page<Process> page = new Page<Process>();
        page.setPageNo(pageNum);
        page.setPageSize(pageSize);
        QueryFilter filter = new QueryFilter();
        if (StringHelper.isNotEmpty(displayName))
        {
            filter.setDisplayName(displayName);
        }
        facets.getEngine().process().getProcesss(page, filter);
        request.setAttribute("page", page);
        return "processDeployList";
    }
    
    public String diagram()
    {
        
        String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
        String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
        request.setAttribute("processId", processId);
        request.setAttribute("orderId", orderId);
        return "diagram";
    }
    
    public void json()
    {
        String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
        String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
        List<Task> tasks = null;
        if (StringHelper.isNotEmpty(orderId))
        {
            tasks = facets.getEngine().query().getActiveTasks(new QueryFilter().setOrderId(orderId));
        }
        Process process = facets.getEngine().process().getProcessById(processId);
        AssertHelper.notNull(process);
        ProcessModel model = process.getModel();
        Map<String, String> jsonMap = new HashMap<String, String>();
        if (model != null)
        {
            jsonMap.put("process", SnakerHelper.getModelJson(model));
        }
        
        // {"activeRects":{"rects":[{"paths":[],"name":"任务3"},{"paths":[],"name":"任务4"},{"paths":[],"name":"任务2"}]},"historyRects":{"rects":[{"paths":["TO 任务1"],"name":"开始"},{"paths":["TO 分支"],"name":"任务1"},{"paths":["TO 任务3","TO 任务4","TO 任务2"],"name":"分支"}]}}
        if (tasks != null && !tasks.isEmpty())
        {
            jsonMap.put("active", SnakerHelper.getActiveJson(tasks));
        }
        writeGson(response, jsonMap);
    }
    
    /**
     * 新建流程定义[web流程设计器]
     * 
     * @param model
     * @return
     */
    public String designer()
    {
        String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
        String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
        String status = CommonUtil.nullToDefault(request.getParameter("status"), "1");
        String orderIdStatus = CommonUtil.nullToDefault(request.getParameter("orderIdStatus"), "1");
        String back = CommonUtil.nullToDefault(request.getParameter("back"), "1"); //0 不显示返回
        request.setAttribute("status", status);
        request.setAttribute("back", back);
        // 1 ，显示流程图操作栏 0 ，不显示
        if (StringHelper.isNotEmpty(orderId))
        {
            List<Task> task_list = facets.getEngine().query().getActiveTasks(new QueryFilter().setOrderId(orderId));
            if (task_list.size() != 0)
            {
                request.setAttribute("displayName", task_list.get(0).getDisplayName());
            }
            orderIdStatus = "0";
        }
        if (StringHelper.isNotEmpty(processId))
        {
            Process process = facets.getEngine().process().getProcessById(processId);
            AssertHelper.notNull(process);
            ProcessModel processModel = process.getModel();
            if (processModel != null)
            {
                String json = SnakerHelper.getModelJson(processModel);
                request.setAttribute("process", json);
            }
            request.setAttribute("processId", processId);
        }
        request.setAttribute("orderIdStatus", orderIdStatus);
        return "processDesigner";
    }
    
    /**
     * 删除流程
     * 
     * @param model
     * @return
     */
    public String delete()
    {
        String id = CommonUtil.nullToDefault(request.getParameter("id"), "");
        facets.getEngine().process().undeploy(id);
        return "processList";
    }
    
    /**
     * 重新启动流程
     * 
     * @param model
     * @return
     */
    public String reStart()
    {
        String id = CommonUtil.nullToDefault(request.getParameter("id"), "");
        SysUserDao sud = SysUserDao.getInstance();
        sud.updateProcessRestart(id);
        return "processList";
    }
    
    /**
     * 流程定义查询列表
     * 
     * @param model
     * @return
     * @throws UnsupportedEncodingException 
     */
    public String list() throws UnsupportedEncodingException
    {
        // 检索参数
        int pageNum = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pageNo"), "1"));
        int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "30"));
        // String displayName1 = request.getParameter("displayName");
        String displayName = CommonUtil.nullToDefault(request.getParameter("displayName"), "").trim();
        String displayName1 = URLDecoder.decode(displayName, "utf-8");
        Page<Process> page1 = new Page<Process>();
        page1.setPageNo(pageNum);
        page1.setPageSize(pageSize);
        QueryFilter filter = new QueryFilter();
        if (StringHelper.isNotEmpty(displayName1))
        {
            filter.setDisplayName(displayName1);
        }
        facets.getEngine().process().getProcesss(page1, filter);
        request.setAttribute("page", page1);
        return "processList";
    }
    
    /**
     * 流程定义查询列表
     * 
     * @param model
     * @return
     */
    
    public String listS()
    {
        // 检索参数
        int pageNum = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pageNo"), "1"));
        int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "30"));
        // String displayName1 = request.getParameter("displayName");
        String displayName1 = CommonUtil.nullToDefault(request.getParameter("displayName"), "").trim();
        Page<Process> page1 = new Page<Process>();
        page1.setPageNo(pageNum);
        page1.setPageSize(pageSize);
        QueryFilter filter = new QueryFilter();
        if (StringHelper.isNotEmpty(displayName1))
        {
            filter.setDisplayName(displayName1);
        }
        facets.getEngine().process().getProcesss(page1, filter);
        request.setAttribute("page", page1);
        return "processDeployList";
    }
    
    /**
     * 编辑流程定义
     * 
     * @param model
     * @return
     */
    public String edit()
    {
        String id = CommonUtil.nullToDefault(request.getParameter("id"), "");
        Process process = facets.getEngine().process().getProcessById(id);
        request.setAttribute("process", process);
        if (process.getDBContent() != null)
        {
            try
            {
                request.setAttribute("content", StringHelper.textXML(new String(process.getDBContent(), "UTF-8")));
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
        }
        return "processEdit";
    }
    
    /**
     * 添加流程定义后的部署
     * 
     * @param snakerFile
     * @param id
     * @return
     */
    
    public String processDeploy()
        throws IOException
    {
        String id = CommonUtil.nullToDefault(request.getParameter("id"), "");
        InputStream input = new FileInputStream(snakerFile);
        try
        {
            
            if (StringUtils.isNotEmpty(id))
            {
                facets.getEngine().process().redeploy(id, input);
            }
            else
            {
                facets.getEngine().process().deploy(input);
            }
        }
        finally
        {
            if (input != null)
            {
                try
                {
                    input.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return "processList";
    }
    
    /**
     * 根据流程定义部署
     * 
     * @param model
     * @return
     */
    public String deploy()
    {
        return "processDeploy";
    }
    
    public String init()
    {
        facets.initFlows();
        return "processList";
    }
    
    /**
     * 保存流程定义[web流程设计器]
     * 
     * @param model
     * @return
     */
    
    public void deployXml()
    {
        String model = CommonUtil.nullToDefault(request.getParameter("model"), "");
        String id = CommonUtil.nullToDefault(request.getParameter("id"), "");
        InputStream input = null;
        try
        {
            String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" + SnakerHelper.convertXml(model);
            System.out.println("model xml=\n" + xml);
            input = StreamHelper.getStreamFromString(xml);
            if (StringHelper.isNotEmpty(id))
            {
                facets.getEngine().process().redeploy(id, input);
            }
            else
            {
                facets.getEngine().process().deploy(input);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            writeGson(response, false);
        }
        finally
        {
            if (input != null)
            {
                try
                {
                    input.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        writeGson(response, true);
    }
    
    /**
     * 查询可分配task
     * 
     * @param model
     * @return
     */
    
    public String taskListManage()
        throws Exception
    {
        String operation = request.getParameter("operation");
        if ("init".equals(operation))
        {
            // 检索参数
            Map<String, Object> param = new HashMap<String, Object>();
            int pageNum = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagenum"), "1")) - 1;
            int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "30"));
            String sortname = CommonUtil.nullToDefault(request.getParameter("sortname"), "");
            String sortorder = CommonUtil.nullToDefault(request.getParameter("sortorder"), "");
            
            param.put("pageSize", pageSize);
            param.put("processName", request.getParameter("processName"));
            param.put("status", request.getParameter("status"));
            param.put("pageNum", pageNum);
            param.put("sortname", sortname);
            param.put("sortorder", sortorder);
            
            // 分页对象
            TaskManageDao taskManageDao = TaskManageDao.getInstance();
            OraPaginatedList plansList = taskManageDao.getTaskInfoList(param);
            List<Map<String, Object>> list = plansList.getList();
            for (Map<String, Object> map : list) {
				JSONObject jsonObject = JSONObject.fromObject(map.get("ORDER_VARIABLE").toString());
				map.put("TITLE", jsonObject.getString("title"));
				map.put("PATH", jsonObject.getString("path"));
			}
            // 分页对象
            PageData pg = CommonUtil.fomateResult(list, pageNum + 1, pageSize, plansList.getFullListSize());
            writeJson(response, CommonUtil.formatFGMap(pg));
            return null;
        }
        else
        {
            return "taskListManage";
        }
    }
    
    /**
     * 查询可分配task
     * 
     * @param model
     * @return
     */
    
    public void selectOpertor()
        throws Exception
    {
        SysUserDao sud = SysUserDao.getInstance();
        String authId = "6";
        String authId2 = "3";
        List<Map<String, Object>> list = sud.queryUser2authId(authId);
        List<Map<String, Object>> list2 = sud.queryUser2authId(authId2);
        request.setAttribute("approveDeptList", list2);
        request.setAttribute("approveBossList", list);
        writeJson(response, list);
    }
    
    public void updateTaskNextName()
    {
        response.setContentType("text/html; charset=UTF-8");
        String taskId = CommonUtil.nullToDefault(request.getParameter("taskId"), "");
        String nextName = CommonUtil.nullToDefault(request.getParameter("nextName"), "");
        String operator = CommonUtil.nullToDefault(request.getParameter("operator"), "");
        String res_return = "";
        try
        {
            
            List<Task> list = facets.getEngine().task().createNewTask(taskId, 0, nextName);
            String newTaskId = list.get(0).getId();
            String newUserId = nextName;
            manager.updateMessage1(operator, taskId, newUserId, newTaskId);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("ip", ClientInfoUtils.getIP(request));
            map.put("method", "1"); // 1代表转发
            facets.getEngine().task().complete(taskId, operator, map);
            manager.updateTask(newTaskId, newUserId);
            res_return = "{msg:\"ok\"}";
        }
        catch (Exception e)
        {
            res_return = "{msg:\"分派失败，数据库错误\"}";
            e.printStackTrace();
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
            e.printStackTrace();
        }
        finally
        {
            out.close();
        }
        
    }
    
}
