package com.zqkj.bsm.zcgl.action;

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
import org.json.simple.JSONValue;
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
import com.cudatec.flow.framework.service.SnakerEngineFacets;
import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.dal.bean.Admin;
import com.zqkj.bsm.system.action.page.OraPaginatedList;
import com.zqkj.bsm.util.ClientInfoUtils;
import com.zqkj.bsm.util.CommonUtil;
import com.zqkj.bsm.util.PageData;
import com.zqkj.bsm.zcgl.dao.AssetApplyDao;
import com.zqkj.bsm.zcgl.dao.AssetInfoDao;
import com.zqkj.bsm.zcgl.dao.AssetManagerDao;


/**
 * 资产管理业务处理
 */
@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results({
		  @Result(name = "all", location = "/WEB-INF/jsp/flow/all/all.jsp"),
	      @Result(name="assetapply",location="/WEB-INF/jsp/asset/assetapply.jsp"),
          @Result(name = "approve", location = "/WEB-INF/jsp/flow/all/approve.jsp"),
          @Result(name = "activeTask", location = "/WEB-INF/jsp/flow/activeTask.jsp"), 
          @Result(name="myasset",location="/WEB-INF/jsp/asset/myasset.jsp"),

})
@ExceptionMappings({ 
	@ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") 
}) 
@Action(value = "assetapply")
public class AssetApplyAction extends BaseAction{
	@Autowired
	private SnakerEngineFacets snakerEngine;

	private static final long serialVersionUID = 1L;    
 


	
	public void getAssetDetailInfoById()
	{
		String s=(String) request.getParameter("Id");
		List<Map<String, Object>> list = AssetApplyDao.getInstance().getAssetDetailInfoById(s);
		writeGson(response, list);
        
	}
	public void  getAdminName(){
		Admin admin = (Admin)session.getAttribute("admin");//获取当前用户
		String name=admin.getTruename();
		writeGson(response, name);
	}


	public String getMyAssetList()
	{
		String operation = request.getParameter("operation");
        if("init".equals(operation))
        {
            // 检索参数
        	int pageNum = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagenum"), "1"))-1;
            int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "30"));
            Admin admin = (Admin) request.getSession().getAttribute("admin");
            String user=admin.getTruename();
    		OraPaginatedList list = AssetApplyDao.getInstance().getMyAssetList(pageNum,pageSize,user);
    		PageData pg = CommonUtil.fomateResult(list.getList(), pageNum + 1, pageSize, list.getFullListSize());
    		writeJson(response, CommonUtil.formatFGMap(pg));
            return null;
        } else 
        {
            return "myasset";
        }
	}	
	
	 /**
     * 打开资产管理页面加载iframe
     * @return
     */
	
    public String startFlow() 
    {
        String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");//流程编号
        String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");//流程实例ID
        String taskId = CommonUtil.nullToDefault(request.getParameter("taskId"), "");//流程步骤ID
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
    
    
	/**
	* 资产录入\审批
	* 
	 */
	public String assetApply()
	{
		
		 String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
		 String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
	     String taskId = CommonUtil.nullToDefault(request.getParameter("taskId"), "");
	     Admin admin = (Admin) request.getSession().getAttribute("admin");
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
	     Map<String, Object> map = facets.chooseStep(processId, orderId, taskId, admin);//根据orderid选择返回步骤所需数据
	     map.put("label", label);
	     map.put("path", "assetapplyinfo"); //点击查看  显示资产录入数据表单及审批详情  
	     map.put("action", "assetapply");	//action 对应的名字  
	     //map中的数据{taskId=, processId=af57a3325ede4424a4d8f9868cc8c1e3, action=assetmanager, path=assetinputinfo, ord=110, assignee=48, list=[{"ASSETNAME":"显示器","CODE":"XSQ"},
	     // {"ASSETNAME":"主机","CODE":"ZJ"},{"ASSETNAME":"电脑","CODE":"DN"},{"ASSETNAME":"键盘","CODE":"JP"}], displayName=资产审核, taskName=rect2, orderId=, step=1}
	     request.setAttribute("map", map);//把map封装到请求中
	    //如果为第一步，跳转到资产录入
	     if(map.get("step").toString().equals("1"))
	    {
	    	
	    	return "assetapply";
	    }
	     // //负责跳转到录入审批（因为流程只有2步）
	    else
	    {
	    	return "approve";
	    }
		
	}
    
	
	
	/**
	 * 工作流流转的主要处理方法
	 * @throws IOException
	 */
	public void process() throws IOException {
 		ArrayList<Object[]> listObj = new ArrayList<Object[]>();
	 	String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");//获取流程ID
	 	String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");//获取流程实例ID
	 	String taskId = CommonUtil.nullToDefault(request.getParameter("taskId"), "");//获取步骤ID
	 	String taskName = CommonUtil.nullToDefault(request.getParameter("taskName"), "");//获取当前步骤名称
	 	String method = CommonUtil.nullToDefault(request.getParameter("method"), "0");//同意0/不同意-1   默认0同意
    	Admin admin = (Admin)session.getAttribute("admin");//获取当前用户
    	String ipaddr =ClientInfoUtils.getIP(request);//获取用户IP地址
        Map<String, String> map = new HashMap<String,String>();
        Enumeration<String> paraNames = request.getParameterNames();//获取页面参数并序列化
        Map<String, Object> params = this.dealParams(paraNames,null);//把页面获取的参数转存MAP类型
        Map<String, Object> mapTitle = new HashMap<String, Object>();//流程详情MAP
        mapTitle.put("ip", ipaddr);//存入用户IP
       
        String newTaskId = null; //下步流程的Id
       
        String displayName= null; //下步流程名称
        String sql = null;
       
    	String status = "0"; //状态  0 ，流程第一次发起，1，流程第N次发起
    	
    	Map<String, Object> startMap = null;//第一步流程信息
        if (StringHelper.isEmpty(orderId)) 
        {	
        		//执行第一步流程
            	mapTitle.put("title", params.get("title"));//存入标题
            	mapTitle.put("path", "assetapplyinfo");//存入流程详情表单页面路径
            	map = facets.startAndExecute(processId, admin.getName(),mapTitle);//执行第一步流程产生OrderId
            	orderId = map.get("orderId");
            	taskId = map.get("taskId");
            	newTaskId = map.get("newTaskId");
            	displayName = map.get("displayName");
            	params.put("name", admin.getTruename()); //用户
            	params.put("section",admin.getOrg_name()); //部门	
            	params.put("work", admin.getOrg_infor());//职务
            	params.put("uid", admin.getAdminId());//用户id
        }else
        {	//获取历史纪录
        	List<HistoryTask> listHIs = facets.getEngine().query().getHistoryTasks(new QueryFilter().setOrderId(orderId));
    		//这里很奇怪，参数给的很详细还是不能查出指定数据，看来只能遍历，艹
    		for (HistoryTask historyTask : listHIs) {
    			//循环历史纪录，如果其他领导审批了，就返回repeat(其他领导不用再审批了，在需要多个领导审批时使用)
				if(historyTask.getId().equals(taskId)){
					response.getWriter().write("repeat");
					return;
				}
			}
        	//获取流程发起人名称，和第一步流程名称
        	startMap = getFirstStep(orderId);
        	if(!admin.getName().equals(startMap.get("operator"))){
        		mapTitle.put("method", method);
        	}
        	List<Task> task = null;
        	//从his_task获取历史步骤纪录
        	List<HistoryTask> list = facets.getEngine().query().getHistoryTasks(new QueryFilter().setOrderId(orderId));
    		//遍历历史纪录
        	for (HistoryTask historyTask : list) {
    			//判断当前步骤是否有历史纪录，有则将状态改为1
    			if(historyTask.getTaskName().equals(taskName)){
    				status = "1";
    			}
    		}
    		//如果领导同意，执行下一步流程
        	if(method.equals("0")){
        		
        		task = facets.execute(taskId, admin.getName(),mapTitle);
        	}else{
        	//領導不同意，流程跳转（打回）到发起人
        		task = facets.executeAndJump(taskId, admin.getName(),mapTitle, startMap.get("taskName").toString());
        		facets.addTaskActor(task.get(0).getId(), 0, new String[]{startMap.get("operator").toString()});
        	}
        	//审批完成后，去掉提醒
            manager.updateMessage(admin.getName(), taskId, orderId);
            //如果流程的当前步骤不为最后一步，取当前步骤的ID，如果为最后一步，下一步骤ID取—1（表示流程结束）
            newTaskId = (task.size() != 0)  ? task.get(0).getId() :"-1";
            displayName   = (task.size() != 0)  ? task.get(0).getDisplayName() :"";
        }
     
    	
        //对领导审批进行分类处理,(一个流程重多次重复使用approve.jsp)
        Map<String, Object> newParams = changeParam(params, taskName);
        //如果状态值为0插入数据到表flow_form,否则更新数据
        if(status.equals("0")){
        	sql = BATCH_SQL;
        	listObj = TaskManageDao.getInstance().save(newParams, orderId, taskId);
        }else{
        	sql = UPDATE_SQL;
        	listObj = TaskManageDao.getInstance().update(newParams, orderId);
        }
        //保存表单信息到flow_form
        manager.executeBatch_Pre(sql, listObj);
        Map<String, Object> getData =  TaskManageDao.getInstance().getForm_List(orderId);
        String uid=getData.get("asset_id").toString().trim();
        AssetApplyDao.getInstance().updateUserAndStatusById(null, "2", uid);
        //如果下一步ID不为—1
    	if(!newTaskId.equals("-1")){
    		//如果领导同意
    		if(method.equals("0")){
    			//去除下一步流程的权限ID记录
        		TaskManageDao.getInstance().DeActorId(newTaskId);
        		//用户流程添加
        		facets.getEngine().task().addTaskActor(newTaskId, 0, newParams.get("userList").toString().split(","));
        		//普通步骤进行流程提醒
        		addMsg(newParams.get("userList").toString(), orderId, newTaskId, processId, "assetapply!startFlow.action", displayName);
    		}else{
    			//如果领导不同意
    			addMsg(startMap.get("operator").toString(), orderId, newTaskId, processId, "assetapply!startFlow.action", displayName);
    			AssetApplyDao.getInstance().updateUserAndStatusById(null, "1", uid);
    		}
    	}else{
    		//流程结束，取出所有表单数据
    		Map<String, Object> AllResult =  TaskManageDao.getInstance().getForm_List(orderId);
    		//TaskManageDao.getInstance().updateUserleave(AllResult);
    		
    		//uid=1, work=处长, asset_price=1, asset_name=11, asset_brand=1, rect3method=0, asset_total=1, section=常州市水利局,处室(办公室), asset_purchasetime=2016-05-25, 
    		//time=2016/05/25 13:28:23, title=资产录入, asset_code=11, name=系统管理员, asset_type=1, asset_remark=11, rect3suggest=aaa, asset_purchaser=1
    		String id=AllResult.get("asset_id").toString().trim();
    		String user=AllResult.get("asset_user").toString().trim();
    		
    		AssetApplyDao.getInstance().updateUserAndStatusById(user, "3", id);
    	}
    	//返回前台success
        response.getWriter().write("success");
 }
    
	
	
	
 	/**
 	 * 流程的代办事项
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
        Map<String,Object> map =  this.getTaskPages(facets, processId, assignees, majorPage, aidantPage, ccorderPage);
         List<WorkItem> aidantWorks=(List<WorkItem>)map.get("aidantWorks");
         List<HistoryOrder> ccWorks=(List<HistoryOrder>)map.get("ccWorks");;
         List<WorkItem> majorWorks = (List<WorkItem>)map.get("majorWorks");;
        request.setAttribute("majorWorks", majorWorks);
        request.setAttribute("majorTotal", majorPage.getTotalCount());
        request.setAttribute("aidantWorks", aidantWorks);
        request.setAttribute("aidantTotal", aidantPage.getTotalCount());
        request.setAttribute("ccorderWorks", ccWorks);
        request.setAttribute("ccorderTotal", ccorderPage.getTotalCount());
        return "activeTask";
    }
    
	

    
    
	
	public void viewStep()
	{
		
		String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
		//获取历史步骤纪录
		List<Map<String, Object>> listHisTask = TaskManageDao.getInstance().getHisTaskList(orderId);
		//获取当前步骤纪录
		List<Map<String, Object>> listTask = TaskManageDao.getInstance().getTaskList(orderId);
		Map<String, Object> map = new HashMap<String,Object>();
		//将所有当年步骤纪录放入历史纪录
		listHisTask.addAll(listTask); //流程进行中
		//如果当前步骤数为0
		if(listTask.size() == 0){
			map.put("status", "end"); //流程结束
			listHisTask.add(map);
		}
		//将流程纪录返回前台
		writeGson(response, listHisTask);
	}
	
}
