package com.zqkj.bsm.system.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import com.opensymphony.xwork2.ModelDriven;
import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.dal.bean.Menu;
import com.zqkj.bsm.dal.bean.User;
import com.zqkj.bsm.dal.client.MenuDao;
import com.zqkj.bsm.dal.client.MenuOpera;
import com.zqkj.bsm.dal.client.UserOpera;
import com.zqkj.bsm.util.CommonUtil;
import com.zqkj.bsm.util.PageData;

@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results( { @Result(name = "menu", location = "/WEB-INF/jsp/system/menu.jsp"),  
            @Result(name = "privilege", location = "/WEB-INF/pages/systemManage/privilege.jsp"),
            @Result(name = "sendEmailPage", location = "/WEB-INF/pages/systemManage/send_email_page.jsp"),
            @Result(name = "sendPhoneMessagePage", location = "/WEB-INF/pages/systemManage/send_phone_message_page.jsp"),
            @Result(name = "error", location = "/failure.jsp")
        }) 
@ExceptionMappings( { @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") }) 
public class SystemManagementAction extends BaseAction implements ModelDriven<User>{
	private static final long serialVersionUID = 1L;
	private User user=new User();
	@Action(value = "system") 
    /*
	 * 新建菜单
	 * @author 高峰
	 * @throws IOException 
	 * @date Aug 12, 2013
	 */
	public void createMenu() throws IOException {
		JSONArray buttonname = JSONArray.fromObject(request.getParameter("buttonname"));
		Menu menu = new Menu();
		menu.setIsLeaf(request.getParameter("is_leaf"));
		menu.setMenuName(request.getParameter("menu_name"));
		menu.setUpMenuId(Long.parseLong(request.getParameter("fmenu_id")));
		menu.setMenuUrl(request.getParameter("url"));
		menu.setIsIframe(request.getParameter("isIframe"));
		MenuDao md = MenuDao.getInstance();
		PrintWriter out = response.getWriter();
		if(md.createMenu(menu,buttonname)){
			out.print("yes");
		}else {
			out.print("no");
		}
		out.flush();
		out.close();
	}
	
	public void updateMenu() throws IOException 
	{
		JSONArray buttonname = JSONArray.fromObject(request.getParameter("buttonname"));
		Menu menu = new Menu();
		menu.setIsLeaf(request.getParameter("is_leaf"));
		menu.setMenuName(request.getParameter("menu_name"));
		menu.setUpMenuId(Long.parseLong(request.getParameter("fmenu_id")));
		menu.setMenuId(Long.parseLong(request.getParameter("menu_id")));
		menu.setMenuUrl(request.getParameter("url"));
		menu.setIsIframe(request.getParameter("isIframe"));
		MenuDao md = MenuDao.getInstance();
		PrintWriter out = response.getWriter();
		if(md.updateMenu(menu, buttonname))
		{
			out.print("yes");
		}else {
			out.print("no");
		}
		out.flush();
		out.close();
	}
	
	/**
	 * 菜单管理页面
	 * @return
	 */
	public String menu(){

        String operation = request.getParameter("operation");
        if("init".equals(operation)){
            // 检索参数
            Map<String, Object> param = new HashMap<String, Object>();
            int pageNum = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagenum"), "1")) - 1;
            int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "10"));
            String menu_name = request.getParameter("menu_name") == null ? "" : request.getParameter("menu_name");
            param.put("menuName", menu_name);
            param.put("from", pageNum * pageSize);
            param.put("to", pageSize);
            MenuOpera opera = MenuOpera.getInstance();
            List<Menu> menus = opera.readMenuAll(param);
            int totalCount = opera.getAllCount(param);
            // 分页对象
            PageData pg = null;;
			try {
				pg = CommonUtil.fomateResult(CommonUtil.convToMapList(menus), pageNum + 1, pageSize, totalCount);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            writeJson(response, CommonUtil.formatFGMap(pg));
            return null;
        } else {
            return "menu";
        }
	}

	/**
	 * 菜单中菜单的树形结构
	 * @return
	 */
	public String treeMenu(){

            // 检索参数
            Map<String, Object> param = new HashMap<String, Object>();
            int pageNum = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagenum"), "1")) - 1;
            int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "10"));
            String menu_name = request.getParameter("menu_name") == null ? "" : request.getParameter("menu_name");
            param.put("menuName", menu_name);
            param.put("from", pageNum * pageSize);
            param.put("to", pageSize);
            MenuOpera opera = MenuOpera.getInstance();
            List<Menu> menus = opera.readMenuAll(param);


            List<Map<String, Object>> rs = new ArrayList<Map<String,Object>>();
            CommonUtil.formateTreeData(rs, menus);
            writeJson(response, rs);
            return null;
	}
	
	/**
	 * 菜单管理页面
	 * @return
	 * @throws IOException 
	 */
	public void readOneGradeMenu() throws IOException{
		response.setCharacterEncoding("UTF-8");
		MenuOpera opera = MenuOpera.getInstance();
		List<Menu> menus = opera.readOneGradeMenu();
		PrintWriter out = response.getWriter();
		out.print(JSONArray.fromObject(menus));
	}
	/**
	 * 发送邮件
	 * @return
	 */
//	public String sendEmail(){
//		String type = request.getParameter("type");
//		String subject = request.getParameter("subject");
//		String content = request.getParameter("content");
//		List<String> emails = new ArrayList<String>();
//		if("custom".equals(type)){
//			String customEmails = request.getParameter("customEmails");
//			String[] splitEmails = customEmails.split(";");
//			for (String string : splitEmails){
//				emails.add(string);
//			}
//		}else{
//			UserOpera opera = UserOpera.getInstance();
//			emails = opera.readAllEmail();
//		}
//		ExecutorService pool = Executors.newFixedThreadPool(10);//创建指定10个线程的线程池
//		for (String email : emails) {
//			SendEmailThread thread = new SendEmailThread(email,subject,content);
//			pool.execute(thread);
//		}
//		pool.shutdown();
//		addActionMessage("发送邮件成功。");
//		return "success";
//	}
	/**
	 * 发送短信页面
	 * @return
	 */
	public String sendPhoneMessagePage(){
		UserOpera opera = UserOpera.getInstance();
		List<String> phones = opera.readAllPhone();
		request.setAttribute("phones", phones);
		return "sendPhoneMessagePage";
	}
	
	
	
	/**
	 * 发送短信
	 * @return
	 */
	
//	public String sendPhoneMessage(){
//		String type = request.getParameter("type");
//		String content = request.getParameter("content");
//		List<String> phones = new ArrayList<String>();
//		if("custom".equals(type)){
//			String customPhones = request.getParameter("customPhones");
//			String[] splitPhones = customPhones.split(";");
//			for (String string : splitPhones){
//				phones.add(string);
//			}
//		}else{
//			UserOpera opera = UserOpera.getInstance();
//			phones = opera.readAllPhone();
//		}
//		ExecutorService pool = Executors.newFixedThreadPool(10);//创建指定10个线程的线程池
//		for (String phone : phones) {
//			SendPhoneMessageThread thread = new SendPhoneMessageThread(phone,content);
//			pool.execute(thread);
//		}
//		pool.shutdown();
//		addActionMessage("发送短信成功。");
//		return "success";
//	}
	
	public User getModel() {
		return user;
	}
}
