package com.zqkj.bsm.system.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.dal.bean.Admin;
import com.zqkj.bsm.system.action.dao.OrganizationDao;
import com.zqkj.bsm.system.action.dao.SysUserDao;
import com.zqkj.bsm.system.action.util.StringHelper;

@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results( { @Result(name = "toConfigOrgs", location = "/WEB-INF/jsp/system/toConfigOrgs.jsp"),  
            @Result(name = "queryOrg", location = "/WEB-INF/jsp/system/org_list_query.jsp"),
            @Result(name = "error", location = "/failure.jsp")
        }) 
@ExceptionMappings( { @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") }) 
public class OrganizationAction extends BaseAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3161296675317579445L;
	@Action(value = "org") 
	public String toConfigOrg() throws IOException
	{	
        String operation = request.getParameter("operation");
        if("init".equals(operation)){
			OrganizationDao configDao=OrganizationDao.getInstance();
//			String orgJson=configDao.getOrgJson(configDao.getAllOrgs());
//			response.getWriter().print(orgJson);
			//request.setAttribute("orgJson", orgJson);
			writeJson(response, configDao.getAllOrgs());
			return null;
		} else {
			return "toConfigOrgs";
		}
	}
	
	   public String toConfigOrg1() throws IOException
	    {   
	        Admin admin = (Admin)session.getAttribute("admin");
	        String orgId= String.valueOf(admin.getOrg_id());
	        String auths = SysUserDao.getInstance().queryAuthIDs(String.valueOf(admin.getAdminId()));
	        String[] ass = auths.split(",");
	        int i=0;
	        for(String as:ass)
	        {
	            if(as.equals("1")||as.equals("41")){
	                i=1;
	            }
	        }
	        String operation = request.getParameter("operation");
	        if("init".equals(operation)){
	            OrganizationDao configDao=OrganizationDao.getInstance();
//	          String orgJson=configDao.getOrgJson(configDao.getAllOrgs());
//	          response.getWriter().print(orgJson);
	            //request.setAttribute("orgJson", orgJson);
	            if(i==0){
	                writeJson(response, configDao.getOrgByOrgId(orgId));
	            }else{
	                writeJson(response, configDao.getAllOrgs());
	            }
	            return null;
	        } else {
	            return "toConfigOrgs";
	        }
	    }
	

	/**
	 * 新增组织结构
	 * 
	 * @throws IOException 
	 */
	public void addOrg() throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String res_return="";
		OrganizationDao managementDao=OrganizationDao.getInstance();
		long result=managementDao.addOrg(request);	

		if(result!=-1)
		{
			res_return="{\"msg\":\"新增组织成功！\",\"orgid\":\""+result+"\"}";
		
		}else
		{
			res_return="{\"msg\":\"新增组织失败！\"}";
		}
		PrintWriter out=null;
		try {
			out=response.getWriter();
			out.write(res_return);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
	
	
	public void deleteOrg()
	{
		response.setContentType("text/html; charset=UTF-8");
		
		OrganizationDao managementDao=OrganizationDao.getInstance();
		String result=managementDao.deleteOrg(request);		
		PrintWriter out=null;
		try {
			out=response.getWriter();
			out.print(result);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.close();
		}
	}

	/**
	 * 编辑组织名称并且更新.
	 * 
	 */
	public void updateOrg()
	{
		response.setContentType("text/html; charset=UTF-8");
		
		OrganizationDao managementDao=OrganizationDao.getInstance();
		boolean result = managementDao.updateOrg(request);
		String res_return = "";
		if(result)
		{
			res_return="{\"msg\":\"重新命名成功！\"}";
		
		}else
		{
			res_return="{\"msg\":\"重新命名失败！\"}";
		}
		PrintWriter out=null;
		try {
			out=response.getWriter();
			out.write(res_return);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
	
	public String queryOrg()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html; charset=UTF-8");
		
		OrganizationDao managementDao=OrganizationDao.getInstance();
		//JsonHelper jsonHelper=new JsonHelper();		
		String org_name=StringHelper.noEmpty2(request.getParameter("org_name")).trim();
		List<Map<String, Object>> list=managementDao.queryOrgs(org_name);
		//String json=jsonHelper.list2json(list);		
//		PrintWriter out=null;
//		try
//		{
//			out=response.getWriter();
//			out.write(json);
//			out.flush();
//		} catch (IOException e) 
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally
//		{
//			out.close();
//		}
		request.setAttribute("list", list);
		request.setAttribute("org_name", org_name);
		return "queryOrg";
	}
	
	/**
	 * 获得部门树
	 * @return
	 */
	public String getDepartment() throws Exception
	{
		String operation = request.getParameter("operation");
		String adminId = request.getParameter("id") == null ? "" : request.getParameter("id");
		BigDecimal org_id =null;
		if("init".equals(operation)){
			OrganizationDao configDao=OrganizationDao.getInstance();	
			if(adminId.equals("")){
				org_id = new BigDecimal("0");
			}else{
				org_id = configDao.getOrgId(adminId);
			}
			List<Map<String, Object>>  list2 = new ArrayList<Map<String,Object>>();
			OrganizationAction.formateTreeData(list2, configDao.getAllOrgs());
			Map<String, Object> rs = new HashMap<String,Object>();
	        rs.put("org_id", org_id);
	        rs.put("allMenu", list2);
			writeJson(response, rs);
			
		}
        return null;
	}
	  /**
     * 将一个结果集格式化成一个树形所需要的数据.
     * 
     * @param list 树形数据的容器
     * @param menuList 结果集
     */
    public static void formateTreeData(List<Map<String, Object>> list,
    		List<Map<String, Object>> menuList) {
        if (menuList != null && menuList.size() > 0) {
            for (int i = 0; i < menuList.size(); i++) {
            	Map<String, Object> menu = menuList.get(i);
                String fmenu_id = nullToDefault(menu.get("SUPER_ORG_ID"), "");
                String menu_id = nullToDefault(menu.get("ORG_ID"), "");
                String menu_name = nullToDefault(menu.get("ORG_NAME"), "");
                String org_type = nullToDefault(menu.get("ORG_TYPE"), "");

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", menu_id);
                map.put("name", menu_name);
                map.put("pId", fmenu_id);
                if(org_type.equals("1")){
	                map.put("isParent", true);
	                map.put("open", true);
                }else{
                	map.put("isParent", false);
	                map.put("open", false);
                }
                list.add(map);
            }
        }
    }
	  public static String nullToDefault(Object o, String defaultVal) {
	    return o == null || "".equals(o) ? defaultVal : o.toString();
	}
	  
	  
	  public void sortOrg() throws IOException{
		  String id = request.getParameter("id");
		  String targetId = request.getParameter("targetId");
		  String moveType = request.getParameter("moveType");
		  String pid = request.getParameter("pid");
		  List<Map<String, Object>> listResult = new ArrayList<Map<String,Object>>();
		  List<Map<String, Object>> list = OrganizationDao.getInstance().getAllOrgs();
		  ArrayList<Object[]> listObj = new ArrayList<Object[]>();
		  Object[] obj = null;
		  List<Map<String, Object>> result2 = new ArrayList<>();
		  for (Map<String, Object> map : list) {
			  if(map.get("SUPER_ORG_ID").toString().equals(pid)){
				  listResult.add(map);
			  }
		  }
		 
		  Collections.sort(listResult, new Comparator<Map<String, Object>>() {
              public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                  int map1value = Integer.parseInt(o1.get("ORG_TYPE").toString());
                  int map2value = Integer.parseInt(o2.get("ORG_TYPE").toString());
                  return map1value - map2value;
              }
          });
		  int id_index = 0;
		  int targetId_index = 0;
		  for (int i = 0; i < listResult.size(); i++) {
			if(id.equals(listResult.get(i).get("ORG_ID").toString())){
				id_index = i;
			}
			if(targetId.equals(listResult.get(i).get("ORG_ID").toString())){
				targetId_index = i;
			}
		  }
		  Map<String, Object> id_index_map = listResult.get(id_index);
		  Map<String, Object> targetId_index_index_map = listResult.get(targetId_index);
		 
		  if(moveType.equals("next")){
			 // id在targetid 的下面
			  for (int i = 0; i < listResult.size(); i++) {
				if(i == targetId_index){
					result2.add(listResult.get(i));
					result2.add(id_index_map);
					
				}else{
					if(i != id_index){
						result2.add(listResult.get(i));
					}
				}
			}
			 
		  }else if(moveType.equals("prev")){
				//id在targetid的上面
				  for (int i = 0; i < listResult.size(); i++) {
					  if(i == targetId_index){
						  result2.add(id_index_map);
						  result2.add(listResult.get(i));
					  }else{
						  if(i != id_index){
							  result2.add(listResult.get(i));
						  }
					  }
				  }
		  }
		  //重新排序
		  for (int i = 0; i < result2.size(); i++) {
				 result2.get(i).put("ORG_TYPE", i);
				 obj = new Object[]{ result2.get(i).get("ORG_TYPE"),result2.get(i).get("ORG_ID")}; 
				 listObj.add(obj);
			}
		  boolean flag = OrganizationDao.getInstance().batchOrgain(listObj);
			//批处理
		  if(flag){
			  response.getWriter().write("success");
		  }else{
			  response.getWriter().write("error");
		  }
		  
		  
	  }
}
