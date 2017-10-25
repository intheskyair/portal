package com.zqkj.bsm.system.action;

import java.io.PrintWriter;
import java.util.ArrayList;
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

import com.opensymphony.xwork2.ActionContext;
import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.dal.bean.Admin;
import com.zqkj.bsm.system.action.dao.XtmenuDao;
import com.zqkj.bsm.util.CommonUtil;

@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results( { @Result(name = "sucess_qxToRole", location = "/WEB-INF/jsp/system/qxToRole.jsp"),  
            @Result(name = "error", location = "/failure.jsp")
        }) 
@ExceptionMappings( { @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") }) 
public class MenuAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8499608403942248240L;		
	private String roleId;//角色ID
	private String roleName;//角色名称
	private List<Map<String, Object>> list1;//角色已有权限
	private List<Map<String, Object>> list2 = new ArrayList<Map<String,Object>>();//�?有权�?
	private List<Map<String, Object>> list3 = new ArrayList<Map<String,Object>>();//菜单按钮列表
	private List<Map<String, Object>> list4 = new ArrayList<Map<String,Object>>();//操作按钮已有权限
	private String[] checkbox;
	private String notes;//处理信息
	@Action(value = "doauth") 	
	/**
	 * 给角色分配权限(菜单列表)
	 * @return
	 */
	public String qxToRole() throws Exception
	{
        roleId = request.getParameter("roleId") == null ? "" : request.getParameter("roleId");
        XtmenuDao xmd = XtmenuDao.getInstance();
        list1 = xmd.getMenuByAuth(roleId);
        list4 = xmd.getButtonByAuth(roleId);
        CommonUtil.formateTreeDataFormMap(list2, xmd.getAllMenu());
        CommonUtil.formateTreeDataFormMap2(list3 ,xmd.getAllButton());
        Map<String, Object> rs = new HashMap<String,Object>();
        rs.put("authedMenu", list1);
        rs.put("allMenu", list2);
        rs.put("authedButton", list4);
        rs.put("allButton", list3);
        writeJson(response, rs);
        return null;
	}
	/**
	 * 确认修改角色权限
	 * @return
	 */
	public void doModifyMenuToRole() throws Exception{
		roleId = CommonUtil.nullToDefault(request.getParameter("roleId"), "");
		XtmenuDao xmd = XtmenuDao.getInstance();
		String menuIds = request.getParameter("menuIds");
		String[] checkbox_menu = menuIds == null || "".equals(menuIds) ? null : menuIds.split(",");
		String buttonIds = request.getParameter("buttonIds");
		String[] checkbox_button = buttonIds == null || "".equals(buttonIds) ? null : buttonIds.split(",");
		boolean flag = xmd.editauth2menu(checkbox_menu, checkbox_button,roleId);
		PrintWriter out = response.getWriter();
		if(flag){
			out.print("yes");
		}else{
			out.print("no");
		}
		out.flush();
		out.close();
	}
	
	/**
	 * 根据菜单id和用户id，获得该用户在当前菜单的操作按钮权限
	 * @return
	 */
	public String getButtons() throws Exception
	{
		List<Map<String, Object>> list_buttons = new ArrayList<Map<String,Object>>();
		//当前菜单的id
        String menu_id = request.getParameter("menu_id");
        Admin admin = (Admin)ActionContext.getContext().getSession().get("admin");
        //当前用户的id
		if(admin!=null){
			String userId = String.valueOf(admin.getAdminId());
			XtmenuDao xmd = XtmenuDao.getInstance();
			//当前用户的角色id
			String  AuthId = xmd.getAuthId(userId);
			list_buttons = xmd.getButtons(menu_id,AuthId);
			Map<String, Object> rs = new HashMap<String,Object>();
			rs.put("buttons", list_buttons);
			writeJson(response, rs);
		}else{
			System.out.println("用户失效！admin="+admin);
		}
        return null;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public List<Map<String, Object>> getList1() {
		return list1;
	}

	public void setList1(List<Map<String, Object>> list1) {
		this.list1 = list1;
	}

	public List<Map<String, Object>> getList2() {
		return list2;
	}

	public void setList2(List<Map<String, Object>> list2) {
		this.list2 = list2;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String[] getCheckbox() {
		return checkbox;
	}
	public void setCheckbox(String[] checkbox) {
		this.checkbox = checkbox;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	

}
