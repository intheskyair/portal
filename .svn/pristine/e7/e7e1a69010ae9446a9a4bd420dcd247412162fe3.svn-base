package com.cudatec.flow.framework.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.joda.time.DateTime;
import org.snaker.engine.entity.Process;
import org.snaker.engine.helper.StringHelper;
import org.snaker.engine.model.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;

import com.cudatec.flow.framework.dao.TaskManageDao;
import com.cudatec.flow.framework.entity.Approval;
import com.cudatec.flow.framework.service.ApprovalManager;
import com.cudatec.flow.framework.service.SnakerEngineFacets;
import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.dal.bean.Admin;
import com.zqkj.bsm.system.action.dao.SysUserDao;
import com.zqkj.bsm.util.CommonUtil;


@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results( { @Result(name = "all", location = "/WEB-INF/jsp/flow/snaker/all.jsp"),  
            @Result(name = "appoval", location = "/WEB-INF/jsp/flow/snaker/approval.jsp"),
            @Result(name = "appovalView", location = "/WEB-INF/jsp/flow/snaker/approvalView.jsp"),
            @Result(name = "error", location = "/failure.jsp")
        }) 
@ExceptionMappings( { @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") }) 
public class FlowAction extends BaseAction {

    public static final String PARA_PROCESSID = "processId";
    public static final String PARA_ORDERID = "orderId";
    public static final String PARA_TASKID = "taskId";
    public static final String PARA_TASKNAME = "taskName";
    public static final String PARA_METHOD = "method";
    public static final String PARA_NEXTOPERATOR = "nextOperator";
    public static final String PARA_NODENAME = "nodeName";
    public static final String PARA_CCOPERATOR = "ccOperator";
    public static final String PARA_dyOPERATOR = "dyOperator";
   
    @Autowired
    SnakerEngineFacets facets;
    @Autowired
    ApprovalManager manager;

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 启动流程
     * @return
     */
    @Action(value = "flow") 
    public String all() {
        //流程编号
        String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
        String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
        String taskId = CommonUtil.nullToDefault(request.getParameter("taskId"), "");
        request.setAttribute("processId", processId);
        request.setAttribute("orderId", orderId);
        request.setAttribute("taskId", taskId);
        if (StringHelper.isNotEmpty(processId)) {
        Object oo=facets.getEngine().process().getProcessById(processId);
        	request.setAttribute("process", oo);
        }
        if (StringHelper.isNotEmpty(orderId)) {
        	request.setAttribute("order", facets.getEngine().query().getOrder(orderId));
        }
        if (StringHelper.isNotEmpty(taskId)) {
        	request.setAttribute("task", facets.getEngine().query().getTask(taskId));
        }
        return "all";
    }

    public void node() {
        //根据processId跟orderId查出任务
    	//根据任务处理人处理
        String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
        String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
        String taskId = CommonUtil.nullToDefault(request.getParameter("taskId"), "");
        Process process = facets.getEngine().process().getProcessById(processId);
//       Admin admin = (Admin)session.getAttribute("admin");
        TaskManageDao taskManager = TaskManageDao.getInstance();
//        String auths = SysUserDao.getInstance().queryAuthIDs(String.valueOf(admin.getAdminId()));
//        list.add(admin.getName());
//        String[] ass = auths.split(",");
//        for(String as:ass)
//        {
//        	list.add(as);
//        }
//        String[] assignees = new String[list.size()];
//        list.toArray(assignees);       
        List<Map<String, Object>> list1 =null;
        list1 = taskManager.queryNameByOrderId(orderId,taskId);
//        for(String auth:assignees)
//        {
//        	
//        	if(list1!=null && list1.size()>0)
//        	{
//        		break;
//        	}
//        }
       // List<Map<String, Object>> list1 = taskManager.queryNameByOrderId(orderId, name);
        List<Map<String, Object>> list2 = taskManager.queryHistNameByOrderId(orderId);
        String displayName1 = "";
        String displayName2 = "";
        if(list1.size()!=0||list2.size()!=0){
        	Map<String,Object> maps1 = list1.get(0);
            Map<String,Object> maps2 = list2.get(0);
            displayName1= maps1.get("DISPLAY_NAME").toString();
             displayName2= maps2.get("DISPLAY_NAME").toString();
        }      
        List<TaskModel> models = process.getModel().getModels(TaskModel.class);
        List<TaskModel> viewModels = new ArrayList<TaskModel>();
        for (TaskModel model : models) {
            TaskModel viewModel = new TaskModel();
            if(list1.size()==0||list2.size()==0){
            	 viewModel.setName(model.getName());
                 viewModel.setDisplayName(model.getDisplayName());
                 viewModel.setForm(model.getForm());
                 viewModels.add(viewModel);
            }else if(model.getDisplayName().equals(displayName1)||model.getDisplayName().equals(displayName2)){
            viewModel.setName(model.getName());
            viewModel.setDisplayName(model.getDisplayName());
            viewModel.setForm(model.getForm());
            viewModels.add(viewModel);
            }
        }
        writeGson(response, viewModels);
    }

    /**
     * 由于审批流程在各个业务经常出现，所以该方法统一审批Action
     * 
     * @return
     */
    public String appoval() 
    {
        String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
        String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
        String taskId = CommonUtil.nullToDefault(request.getParameter("taskId"), "");
        request.setAttribute("processId", processId);
        request.setAttribute("orderId", orderId);
        request.setAttribute("taskId", taskId);
        if (StringHelper.isNotEmpty(taskId)) {
            return "appoval";
        } else {
        	//如果orderId非空、taskId为空，则表示申请步骤已提交，此时可获取申请数据
            //由于请假流程中的业务数据，是保存在任务表的variable字段中，所以通过flowData方法获取
            //如果业务数据保存在业务表中，需要业务表的orderId字段来关联流程，进而根据orderId查询出业务数据
            String taskName = CommonUtil.nullToDefault(request.getParameter("taskName"), "");
            Map<String, Object> attrs = facets.flowData(orderId, taskName);
            for (String key : attrs.keySet()) {
                request.setAttribute(key, attrs.get(key));
            }
            return "appovalView";
        }
    }

    /**
     * 审批环节的提交处理
     * 其中审批表可根据具体审批的业务进行定制，此处仅仅是举例
     * @throws IOException 
     * @throws ServletException 
     */
    @SuppressWarnings("unchecked")
	public void doApproval() throws ServletException, IOException 
    {
    	ArrayList<Object[]> listObj = new ArrayList<Object[]>();
        Enumeration<String> paraNames = request.getParameterNames();
        Map<String, Object> params = this.dealParams(paraNames,"");
        String taskId = CommonUtil.nullToDefault(request.getParameter("taskId"), "");
        String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
       // String result = CommonUtil.nullToDefault(request.getParameter("result"), "");
        String result="";
        String description = CommonUtil.nullToDefault(request.getParameter("description"), "");
        String nextOperator = request.getParameter(PARA_NEXTOPERATOR);       
    	Approval model = new Approval();
    	model.setOrderId(orderId);
    	model.setTaskId(taskId);
    	model.setContent(description);
        model.setOperateTime(new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
        Admin admin = (Admin)session.getAttribute("admin");
        model.setOperator(admin.getName());
        String methodStr = request.getParameter(PARA_METHOD);
        int method;
        try 
        {
            method = Integer.parseInt(methodStr);
        } catch(Exception e) 
        {
            method = 0;
        }
        //查询出改任务id下有多少处理人
        List<Map<String, Object>> task = TaskManageDao.getInstance().queryTaskByTaskId(taskId);
        String auths = SysUserDao.getInstance().queryAuthIDs(String.valueOf(admin.getAdminId()));
        List<String> list = new ArrayList<String>();
        list.add(admin.getName());
        String[] ass = auths.split(",");
        for(String as:ass)
        {
        	list.add(as);
        }
        String[] assignees = new String[list.size()];
        list.toArray(assignees);   
        boolean zx_bz =false;
        String actor_id="";
        //根据权限和自身的id,匹配到一个处理人
        for(String auth:assignees)
        {
        	for(Map<String, Object> map_acotr:task)
        	{
        		String actor_id_tmp = String.valueOf(map_acotr.get("ACTOR_ID"));
                if(auth.equals(actor_id_tmp))
                {
                	zx_bz = true;
                	actor_id = auth;
                	break;
                }
        	}
        }
        if(zx_bz)
        {
            switch(method) 
            {
                case 0://任务执行
                	result = "agree";
                	facets.execute(taskId, actor_id, params);
                    break;
                case -1://驳回、任意跳转
                	result = "unagree";
                    facets.executeAndJump(taskId, actor_id, params, request.getParameter(PARA_NODENAME));
                    break;
                case 1://转办
                    if(StringHelper.isNotEmpty(nextOperator)) {
                        facets.transferMajor(taskId, actor_id, nextOperator.split(","));
                    }
                    break;
                case 2://协办
                    if(StringHelper.isNotEmpty(nextOperator)) {
                        facets.transferAidant(taskId, actor_id, nextOperator.split(","));
                    }
                    break;
                default:
                    facets.execute(taskId, actor_id, params);
                    break;
            }
        }
    	model.setResult(result);
        //manager.save(model);
    	listObj = TaskManageDao.getInstance().save(params, orderId, taskId);
    	manager.executeBatch_Pre(BATCH_SQL, listObj);
	    String ccOperator = request.getParameter(PARA_CCOPERATOR);
	    if(StringHelper.isNotEmpty(ccOperator)) {
	        facets.getEngine().order().createCCOrder(orderId, ccOperator.split(","));
	    }
         //response.sendRedirect("task!active.action");
          response.getWriter().write("success");            
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("result", model.getResult());
//        params.put("content", model.getContent());
//        params.put("operator", model.getOperator());
//        params.put("operateTime", model.getOperateTime());
    }

   
    
    
    @SuppressWarnings("unchecked")
	public void process() throws IOException {
    	Admin admin = (Admin)session.getAttribute("admin");
        Map<String, String> map = new HashMap<>();
        ArrayList<Object[]> listObj = new ArrayList<Object[]>();
        Enumeration<String> paraNames = request.getParameterNames();
        Map<String, Object> params = this.dealParams(paraNames,null);
        String processId = request.getParameter(PARA_PROCESSID);
        String orderId = request.getParameter(PARA_ORDERID);
        String taskId = request.getParameter(PARA_TASKID);
        String nextOperator = request.getParameter(PARA_NEXTOPERATOR);
        String description = CommonUtil.nullToDefault(request.getParameter("reason"), "");
    	//Approval model = new Approval();
    	//model.setOrderId(orderId);
    	//model.setTaskId(taskId);
    	//model.setContent(description);
        //model.setOperateTime(new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
       // model.setOperator(admin.getName());        
        if (StringHelper.isEmpty(orderId) && StringHelper.isEmpty(taskId)) 
        {
        	map = facets.startAndExecute(processId, admin.getName(), params);
        	//流程ID
        	listObj = TaskManageDao.getInstance().save(params, orderId, taskId);
        }
        else 
        {
            String methodStr = request.getParameter(PARA_METHOD);
            int method;
            try {
                method = Integer.parseInt(methodStr);
            } catch(Exception e) {
                method = 0;
            }
            List<Map<String, Object>> task = TaskManageDao.getInstance().queryTaskByTaskId(taskId);
            //String actor_id = String.valueOf(task.get("ACTOR_ID"));
            String auths = SysUserDao.getInstance().queryAuthIDs(String.valueOf(admin.getAdminId()));
            List<String> list = new ArrayList<String>();
            list.add(admin.getName());
            String[] ass = auths.split(",");
            for(String as:ass)
            {
            	list.add(as);
            }
            String[] assignees = new String[list.size()];
            list.toArray(assignees);   
            boolean zx_bz =false;
            String actor_id="";
            for(String auth:assignees)
            {
            	for(Map<String, Object> map_acotr:task)
            	{
            		String actor_id_tmp = String.valueOf(map_acotr.get("ACTOR_ID"));
	                if(auth.equals(actor_id_tmp))
	                {
	                	zx_bz = true;
	                	actor_id = auth;
	                	break;
	                }
            	}
            }
            if(zx_bz)
            {
	            switch(method) 
	            {
	                case 0://任务执行
	                    facets.execute(taskId, actor_id, params);
	                    break;
	                case -1://驳回、任意跳转
	                    facets.executeAndJump(taskId, actor_id, params, request.getParameter(PARA_NODENAME));
	                    break;
	                case 1://转办
	                    if(StringHelper.isNotEmpty(nextOperator)) {
	                        facets.transferMajor(taskId, actor_id, nextOperator.split(","));
	                    }
	                    break;
	                case 2://协办
	                    if(StringHelper.isNotEmpty(nextOperator)) {
	                        facets.transferAidant(taskId, actor_id, nextOperator.split(","));
	                    }
	                    break;
	                default:
	                    facets.execute(taskId, actor_id, params);
	                    break;
	            }
	    	    String dyOperator = request.getParameter(PARA_dyOPERATOR);
	    	    if(StringHelper.isNotEmpty(dyOperator) && method==0) 
	    	    {
	                TaskManageDao sud = TaskManageDao.getInstance();
	                List<Map<String, Object>> list_ = sud.queryTaskId(taskId); 
	                String taskIds = list_.get(0).get("ID").toString();
	    	    	String[] Operators = dyOperator.split(",");
	    	    	log.info("增加处理人="+dyOperator);
	    	        facets.getEngine().task().addTaskActor(taskIds,1,Operators);
	    	    }
            }
        }
      
        manager.executeBatch_Pre(BATCH_SQL, listObj);
        String ccOperator = request.getParameter(PARA_CCOPERATOR);
        if(StringHelper.isNotEmpty(ccOperator)) {
            facets.getEngine().order().createCCOrder(orderId, ccOperator.split(","));
        }

        //response.sendRedirect("task!active.action");
        response.getWriter().write("success");
    }
    
}
