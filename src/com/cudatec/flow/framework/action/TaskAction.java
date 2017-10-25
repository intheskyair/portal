package com.cudatec.flow.framework.action;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import org.snaker.engine.entity.WorkItem;
import org.snaker.engine.helper.StringHelper;
import org.snaker.engine.model.TaskModel.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.cudatec.flow.framework.service.SnakerEngineFacets;
import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.dal.bean.Admin;
import com.zqkj.bsm.util.CommonUtil;
@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results( { @Result(name = "activeTask", location = "/WEB-INF/jsp/flow/activeTask.jsp"),  
            @Result(name = "activeTaskMore", location = "/WEB-INF/jsp/flow/activeTaskMore.jsp"),
            @Result(name = "error", location = "/failure.jsp")
        }) 
@ExceptionMappings( { @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") }) 
public class TaskAction extends BaseAction 
{
    @Autowired
    SnakerEngineFacets facets;
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
	private Page<WorkItem> page = new Page<WorkItem>();
    private Integer taskType;
	private Model     model;
	
	@Action(value = "task") 
    public String active() {
    	String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
        List<String> list = new ArrayList<String>();
        Admin admin = (Admin)session.getAttribute("admin");
        list.add(admin.getName());
        String[] assignees = new String[list.size()];
        list.toArray(assignees);
        Page<WorkItem> majorPage = new Page<WorkItem>(5);
        Page<WorkItem> aidantPage = new Page<WorkItem>(3);
        Page<HistoryOrder> ccorderPage = new Page<HistoryOrder>(3);
        List<WorkItem> majorWorks = null;
        if(StringHelper.isNotEmpty(processId)){
        	majorWorks = facets.getEngine().query().getWorkItems(majorPage, new QueryFilter().setProcessId(processId).setOperators(assignees).setTaskType(TaskType.Major.ordinal()));
        }else{
        	majorWorks = facets.getEngine().query().getWorkItems(majorPage, new QueryFilter().setOperators(assignees).setTaskType(TaskType.Major.ordinal()));
        }
        List<WorkItem> aidantWorks = facets.getEngine()
                .query()
                .getWorkItems(aidantPage, new QueryFilter()
                .setOperators(assignees)
                .setTaskType(TaskType.Aidant.ordinal()));
        List<HistoryOrder> ccWorks = facets.getEngine()
                .query()
                .getCCWorks(ccorderPage, new QueryFilter()
                .setOperators(assignees)
                .setState(1));
        for(WorkItem map:majorWorks)
        {
        	String item_str = CommonUtil.nullToDefault(map.getTaskVariable(), "{}");
        	JSONObject Source =JSONObject.fromObject(item_str);
        	String title = Source.containsKey("title") ? Source.getString("title") : "";
        	map.setTaskVariable(title);
        }
        request.setAttribute("majorWorks", majorWorks);
        request.setAttribute("majorTotal", majorPage.getTotalCount());
        request.setAttribute("aidantWorks", aidantWorks);
        request.setAttribute("aidantTotal", aidantPage.getTotalCount());
        request.setAttribute("ccorderWorks", ccWorks);
        request.setAttribute("ccorderTotal", ccorderPage.getTotalCount());
        return "activeTask";
    }
    
    /**
	 * 活动任务查询列表
	 * @param model
	 * @return
     * @throws ParseException 
     * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("deprecation")
	public String activeTaskList() throws ParseException, UnsupportedEncodingException {
		List<WorkItem> majorWorksResult = new ArrayList<WorkItem>();
		request.setCharacterEncoding("UTF-8");
		String stitle = CommonUtil.nullToDefault(request.getParameter("stitle"), "");
		String stime = CommonUtil.nullToDefault(request.getParameter("stime"), "");
		String pano = request.getParameter("pageNo");
		if(pano == "" || pano == null || pano.equals("NaN")){
			pano = "1";
		}
		List<String> list = new ArrayList<String>();
		 Admin admin = (Admin)session.getAttribute("admin");
        list.add(admin.getName());
        String[] assignees = new String[list.size()];
        list.toArray(assignees);
		String processId = CommonUtil.nullToDefault(request.getParameter("processId"), "");
		 taskType = Integer.parseInt(request.getParameter("taskType"));
		 int pageNum = Integer.parseInt(CommonUtil.nullToDefault(pano, "1"));
	     int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "15"));
	     Page<WorkItem> page = new Page<WorkItem>();
	     page.setPageNo(pageNum);
	     page.setPageSize(pageSize);
	     List<WorkItem> majorWorks = facets.getEngine().query().getWorkItems(page,new QueryFilter().setProcessId(processId).setOperators(assignees).setTaskType(TaskType.Major.ordinal()));
	     for(WorkItem map:majorWorks)
        {
        	String item_str = CommonUtil.nullToDefault(map.getTaskVariable(), "{}");
        	JSONObject Source =JSONObject.fromObject(item_str);
        	String title = Source.containsKey("title") ? Source.getString("title") : "";
        	String time = map.getTaskCreateTime().substring(0, 10);
        	map.setTaskVariable(title);
        	if(stime.equals("") && stitle.equals("")){
        		majorWorksResult.add(map);
        	}else{
        		if(stime.equals("") && !stitle.equals("") && stitle.equals(title)){
        			majorWorksResult.add(map);
        		}else if(stitle.equals("") &&  !stime.equals("") && stime.equals(time)){
        			majorWorksResult.add(map);
        		}else if(stitle.equals(title) && stime.equals(time)){
        			majorWorksResult.add(map);
        		}
        	}
        }
	     page.setTotalCount(majorWorksResult.size());
	     page.setResult(majorWorksResult);
		request.setAttribute("taskType", taskType);
		request.setAttribute("page", page);
		request.setAttribute("processId", processId);
		request.setAttribute("stime", stime);
		request.setAttribute("stitle", stitle);
		return "activeTaskMore";
	}
	
}
