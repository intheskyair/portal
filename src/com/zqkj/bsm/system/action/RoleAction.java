package com.zqkj.bsm.system.action;
import java.io.PrintWriter;
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

import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.system.action.dao.XtmngDao;
import com.zqkj.bsm.system.action.page.OraPaginatedList;
import com.zqkj.bsm.util.CommonUtil;
import com.zqkj.bsm.util.PageData;

@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results( { @Result(name = "queryRole", location = "/WEB-INF/jsp/system/roleList.jsp"),  
            @Result(name = "addRole", location = "/WEB-INF/jsp/system/addRole.jsp"),
            @Result(name = "add_error", location = "/WEB-INF/jsp/result.jsp"),
            @Result(name = "error", location = "/failure.jsp")
        }) 
@ExceptionMappings( { @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") }) 
public class RoleAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3013922666081478145L;
	public String currentPage;
	public List<Map<String, Object>> al; //角色列表
	public String editImage;
	@Action(value = "queryRole") 
	/**
	 * 查询用户角色列表
	 * @return
	 * @throws Exception
	 */
	public String queryRole() throws Exception
	{
        String operation = request.getParameter("operation");
        if("init".equals(operation))
        {
            // 检索参数
            Map<String, Object> param = new HashMap<String, Object>();
            int pageNum = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagenum"), "1")) - 1;
            int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "30"));
            param.put("pageNum", pageNum);
            param.put("pageSize", pageSize);
    		XtmngDao xtd = XtmngDao.getInstance();
    		OraPaginatedList al_list = xtd.qxqry(param);
            // 分页对象
            PageData pg = CommonUtil.fomateResult(al_list.getList(), pageNum + 1, pageSize, al_list.getFullListSize());
            writeJson(response, CommonUtil.formatFGMap(pg));
            return null;
        } else {
            return "queryRole";
        }
	}
	/**
	 * 添加角色界面
	 * @return
	 * @throws Exception
	 */
	public String addRole() throws Exception{
		
		return "addRole";
	}
	/**
	 * 添加角色
	 * @return
	 */
	public void edit() throws Exception
	{
		XtmngDao xtd = XtmngDao.getInstance();
		String roleName= CommonUtil.nullToDefault(request.getParameter("roleName"), "");
		String roleRemark= CommonUtil.nullToDefault(request.getParameter("roleRemark"), "");
		String action= CommonUtil.nullToDefault(request.getParameter("action"), "");
		boolean flag = false;
		if ("add".equals(action)) {
			flag = xtd.addRoles(roleRemark, roleName);
		} else if ("edit".equals(action)) {
			int id= Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("id"), "0"));
			flag = xtd.updateRoles(id, roleRemark, roleName);
		}
		PrintWriter out = response.getWriter();
		if(flag){
			out.print("yes");
		}else{
			out.print("no");
		}
		out.flush();
		out.close();
	}
	
	/*
	 * getter&&setter
	 */
	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	public List<Map<String, Object>> getAl() {
		return al;
	}
	public void setAl(List<Map<String, Object>> al) {
		this.al = al;
	}
	public String getEditImage() {
		return "";
	}

	public void setEditImage(String editImage) {
		this.editImage = editImage;
	}
	
}
