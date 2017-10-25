package com.cudatec.flow.framework.action;

import java.io.File;
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
import org.springframework.beans.factory.annotation.Autowired;

import com.cudatec.flow.framework.dao.PersonalProcessDao;
import com.cudatec.flow.framework.service.SnakerEngineFacets;
import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.dal.bean.Admin;
import com.zqkj.bsm.system.action.page.OraPaginatedList;
import com.zqkj.bsm.util.CommonUtil;
import com.zqkj.bsm.util.PageData;

@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results( { @Result(name = "personalProcess", location = "/WEB-INF/jsp/flow/perProcessInfo.jsp"),  
            @Result(name = "error", location = "/failure.jsp")
        }) 
@ExceptionMappings( { @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") }) 
public class PersonalProcessAction extends BaseAction {
	
	private File snakerFile;

	public File getSnakerFile() {
		return snakerFile;
	}

	public void setSnakerFile(File snakerFile) {
		this.snakerFile = snakerFile;
	}


	@Autowired
    SnakerEngineFacets facets;
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    @Action(value = "personalProcess") 
    public String personalProcessList() throws Exception {
    	String operation = request.getParameter("operation");
        if("init".equals(operation)){
            // 检索参数
            Map<String, Object> param = new HashMap<String, Object>();
            int pageNum = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagenum"), "1")) - 1;
            int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "10"));
            param.put("pageSize", pageSize);
            param.put("pageNum", pageNum);
            param.put("personalTop", request.getParameter("personalTop"));
            param.put("startTime", request.getParameter("startTime"));
            param.put("endTime", request.getParameter("endTime"));
            Admin admin = (Admin)session.getAttribute("admin");
            String userName = admin.getName();
            param.put("userName", userName);
            // 分页对象
            PersonalProcessDao perProcessDao = PersonalProcessDao.getInstance();
            OraPaginatedList perProcessList = perProcessDao.getPerProcessList(param);
            List<Map<String, Object>> list = perProcessList.getList();
            for(Map<String, Object> map:list)
            {
            	String item_str = CommonUtil.nullToDefault(map.get("VARIABLE"), "{}");
            	JSONObject Source =JSONObject.fromObject(item_str);
            	String title = Source.containsKey("title") ? Source.getString("title") : "";
            	map.put("TITLE", title);
            	if(Source.containsKey("path")){
            		map.put("PATH", Source.getString("path"));
            	}
            }
            // 分页对象
            PageData pg = CommonUtil.fomateResult(list, pageNum + 1, pageSize, perProcessList.getFullListSize());
            writeJson(response, CommonUtil.formatFGMap(pg));
            return null;
        } else {
        	return "personalProcess";
        }
    }
    
    /**
     *根据开票单号，获得对应的充值记录
     * @throws Exception
     */
    public void getPersonalList() throws Exception {
        // 检索参数
        String id = request.getParameter("id");// 开票单号
        PersonalProcessDao perProcessDao = PersonalProcessDao.getInstance();
        List<Map<String, Object>> rechargeList = perProcessDao.getPersonalList(id);
        writeJson(response, rechargeList);
    }   
		   
	
}
