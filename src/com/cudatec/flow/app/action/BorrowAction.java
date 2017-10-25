package com.cudatec.flow.app.action;

import java.util.ArrayList;
import java.util.Collections;
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
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.Process;
import org.snaker.engine.entity.Task;
import org.snaker.engine.helper.StringHelper;
import org.snaker.engine.model.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;

import com.cudatec.flow.framework.dao.TaskManageDao;
import com.cudatec.flow.framework.service.SnakerEngineFacets;

import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.util.BHUtil;
import com.zqkj.bsm.util.CommonUtil;

import net.sf.json.JSONArray;
@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results({	@Result(name = "error", location = "/failure.jsp"),  
    		@Result(name = "szzfView", location = "/WEB-INF/jsp/flow/szzf/szzfView.jsp"),
    		@Result(name = "leaveView", location = "/WEB-INF/jsp/flow/leave/leaveView.jsp"),
    		@Result(name = "planView", location = "/WEB-INF/jsp/flow/plan/summaryView.jsp"),
    		@Result(name = "bmgzView", location = "/WEB-INF/jsp/flow/bmgz/bmgzView.jsp"),
    		@Result(name = "dcdbView", location = "/WEB-INF/jsp/flow/dcdb/dcdbView.jsp"),
    		@Result(name = "recdocView", location = "/WEB-INF/jsp/flow/Receivedocument/recdocView.jsp"),//收文查看
            @Result(name = "documView", location = "/WEB-INF/jsp/flow/document/fwgView.jsp"),//发文查看
            @Result(name = "carView", location = "/WEB-INF/jsp/car/carView.jsp"),//车辆审批查看
            @Result(name = "assetinputinfo", location = "/WEB-INF/jsp/asset/assetinputinfo.jsp"),//资产录入查看
            @Result(name = "assetapplyinfo", location = "/WEB-INF/jsp/asset/assetapplyinfo.jsp"),//资产领用查看
            @Result(name = "gkbmView", location = "/WEB-INF/jsp/flow/gkbm/gkbmView.jsp"),//公开保密审查
            @Result(name = "kqView", location = "/WEB-INF/jsp/flow/kq/kqView.jsp"),//每月考勤
            
}) 
@ExceptionMappings( { @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") }) 
@Action(value = "borrow") 
/**
 * 表单view的统一方法
 * @author MJ001
 *
 */
public class BorrowAction extends BaseAction {

	
    /*@Autowired
    BorrowManager manager;*/
    @Autowired
    private SnakerEngineFacets facets;
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    
    /**
     * 加载工作流的步骤，当前的步骤(方法通用)
     */
    public void node() {
        //根据processId跟orderId查出任务
    	//根据任务处理人处理
        String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
        String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
        String taskId = CommonUtil.nullToDefault(request.getParameter("taskId"), "");
        Process process = facets.getEngine().process().getProcessById(processId);
        List<TaskModel> viewModels = new ArrayList<TaskModel>();
        //加载全部表单
        TaskModel viewModel = new TaskModel();
        if(StringHelper.isEmpty(orderId)){
        	List<TaskModel> models = process.getModel().getModels(TaskModel.class);
        	 viewModel.setName(models.get(0).getName());
             viewModel.setDisplayName(models.get(0).getDisplayName());
             viewModel.setForm(models.get(0).getForm());
             viewModels.add(viewModel);
        }else{
          	boolean flag = true;
        	String status = TaskManageDao.getInstance().getMessageCenterStatus(taskId,"status");
        	if(status == null || status.equals("1")){
        		flag = false;
        	}
        	if(flag){
        		List<Task> list = facets.getEngine().query().getActiveTasks(new QueryFilter().setOrderId(orderId));
           	 	if(list.size() != 0){
           	 		viewModel.setName(list.get(0).getTaskName());
                    viewModel.setDisplayName(list.get(0).getDisplayName());
                    viewModel.setForm(list.get(0).getActionUrl());
                    viewModels.add(viewModel);
           	 	}
        	}
        	
        }
        
        writeGson(response, viewModels);
    }
    
    
    
    /**
     * 
     * 返回指定流程
    * @Title: taskBack 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param     设定文件 
    * @return void    返回类型 
    * @throws
     */
    public void taskBack() {
        String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
        String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
        List<Map<String, Object>> list = new ArrayList<>();
        List<Task> listTask = facets.getEngine().query().getActiveTasks(new QueryFilter().setOrderId(orderId));
        List<TaskModel> listTaskModel = facets.getEngine().process().getProcessById(processId).getModel().getModels(TaskModel.class);
        for (TaskModel taskModel : listTaskModel) {
        	Map<String, Object> map = new HashMap<String, Object>();
			if(taskModel.getName().equals(listTask.get(0).getTaskName())){
				break;
			}else{
				map.put("taskName", taskModel.getName());
				map.put("displayName", taskModel.getDisplayName());
				list.add(map);
			}
		}
        writeGson(response, list);
    }
    
    public String view(){
    	String path = CommonUtil.nullToDefault(request.getParameter("path"), "");
    	String orderId = CommonUtil.nullToDefault(request.getParameter("orderId"), "");
    	Map<String, Object> map = TaskManageDao.getInstance().getForm_List(orderId);
    	map.put("orderId", orderId);
    	if(StringHelper.isNotEmpty(path) && StringHelper.isNotEmpty(orderId)){
    		  Map<String,Object> m = TaskManageDao.getInstance().queryDispatchByOrderId(orderId);//根据orderId获取带有blob或clob字段的内容信息
              if(m!=null && m.size()>0)
              {
            	  map.put("file_content", m.get("FILE_CONTENT"));
              }
              
              
              //部门工作月报表-------------------------------------------------------------------
              if(path.equals("bmgzView")){
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
              
              //督查督办-------------------------------------------------------------------------
              if(path.equals("dcdbView")){
            	  int tabValStatus = 0;
              	int tabGgValStataus = 0;
              	for (String key : map.keySet()) {
      				if(key.contains("tabval")){
      					int a = Integer.parseInt(key.substring(6));
      					if((a + 2) % 3 == 0){
      						tabValStatus ++;
      					}
      					
      				}
      				if(key.contains("tabGgval")){
      					int a = Integer.parseInt(key.substring(8));
      					if((a + 2) % 3 == 0){
      						tabGgValStataus ++;
      					}
      				}
      			}
              	List<Map<String, String>> tabList = new ArrayList<Map<String,String>>();
              	List<Map<String, String>> tabGgList = new ArrayList<Map<String,String>>();
              	for (int i = 1; i <=tabValStatus; i++) {
              		Map<String, String> map1 = new HashMap<String,String>();
              		map1.put("one", map.get("tabval"+(i * 3 - 2)).toString());
              		map1.put("two", map.get("tabval"+(i * 3 - 1)) != null ?  map.get("tabval"+(i * 3 - 1)).toString() : "");
              		map1.put("three", map.get("tabval"+(i * 3)) != null ? map.get("tabval"+(i * 3)).toString() : "");
              		tabList.add(map1);
      			}
              	
              	for (int i = 1; i <=tabGgValStataus; i++) {
              		Map<String, String> map2 = new HashMap<String,String>();
              		map2.put("one", map.get("tabGgval"+(i * 3 - 2)).toString());
              		map2.put("two", map.get("tabGgval"+(i * 3 - 1)) != null ? map.get("tabGgval"+(i * 3 - 1)).toString():"");
              		map2.put("three", map.get("tabGgval"+(i * 3)) != null ? map.get("tabGgval"+(i * 3)).toString() : "");
              		tabGgList.add(map2);
      			}
              	request.setAttribute("tabList", tabList);
              	request.setAttribute("tabGgList", tabGgList);
              }
              //---------------------------------------------------------------------------
              //考勤管理
              if(path.equals("kqView")){
            	  List<Map<String, Object>> listVal = new ArrayList<>();
            	  List<Map<String, Object>> list = TaskManageDao.getInstance().getKqList(orderId);
            	  for (Map<String, Object> map2 : list) {
					Map<String, Object> mapResult = new HashMap<>();
					mapResult.put("TRUENAME", map2.get("TRUENAME"));
					List<Integer> ss = new ArrayList<>();
					JSONArray array = JSONArray.fromObject(map2.get("MSG").toString());
					for (int i = 0; i < array.size(); i++) {
						ss.add(array.getInt(i));
					}
					mapResult.put("dayList", ss);
					listVal.add(mapResult);
            	  }
            	  request.setAttribute("list", listVal);
            	  request.setAttribute("days", BHUtil.getDaysByYearMonth(Integer.parseInt(map.get("year").toString()), Integer.parseInt(map.get("month").toString())));
              }
            	  request.setAttribute("map", map);
              
    		
    		return path;
    	}else{
    		return "error";
    	}
    }
}
