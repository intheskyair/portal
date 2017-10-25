package com.zqkj.bsm.system.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

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
import com.zqkj.bsm.dal.bean.Menu;
import com.zqkj.bsm.system.action.dao.XtmenuDao;
import com.zqkj.bsm.util.CommonUtil;

@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results({@Result(name = "top", location = "/views/shell/head.jsp"),
    @Result(name = "menu", location = "/views/shell/menu.jsp"), @Result(name = "error", location = "/failure.jsp")})
@ExceptionMappings({@ExceptionMapping(exception = "java.lange.RuntimeException", result = "error")})
public class GetMenuAction extends BaseAction
{
    
    /**
	 * 
	 */
    private static final long serialVersionUID = 734774867614231896L;
    
    private ArrayList<Map<String, Object>> menulist1;
    
    private ArrayList<Map<String, Object>> menulist2;
    
    private String detail;
    
    public static List<Menu> menuList = new ArrayList<Menu>();
    
    public String getDetail()
    {
        return detail;
    }
    
    public void setDetail(String detail)
    {
        this.detail = detail;
    }
    
    public String viewMenu()
        throws Exception
    {
        String mk = CommonUtil.nullToDefault(request.getParameter("mk"), "");
        request.setAttribute("mk", mk);
        return "menu";
    }
    
    @Action(value = "getMenu")
    /**
     * 新版菜单
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getMenu()
        throws Exception
    {
        // 主要分3个模块，oa办公，水政执法，水利工程建设
        /**
         * N 20028 民主评测管理 0 0 N 12000 工作流 0 1 0 N 5 系统管理   0 10 0 N 20032 水政法监督管理 0 0 N 20034 公文管理 0 5 N 20037 日常办公 0 0
         * N 20057 水利工程建设 0 11 0
         */
        String mk = CommonUtil.nullToDefault(request.getParameter("mk"), "");
        String ids[] = null;
        // oa办公
        if (mk.equals("1"))
        {
            ids = new String[] {"20037", "20034", "12000", "20028", "5", "20134"};
        }
        // 水政法监督管理
        else if (mk.equals("2"))
        {
            ids = new String[] {"20032"};
        }
        // 水利工程建设
        else if (mk.equals("3"))
        {
            ids = new String[] {"20057"};
        }
        // 所有菜单
        else
        {
            ids = new String[] {"all"};
        }
        menuList.clear();
        XtmenuDao xd = XtmenuDao.getInstance();
        Admin admin = (Admin)ActionContext.getContext().getSession().get("admin");
        if (admin != null)
        {
            String sys_id = String.valueOf(admin.getAdminId());
            menulist1 = xd.getMenus(sys_id);
            List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
            
            if (menulist1 != null && menulist1.size() > 0)
            {
                for (int i = 0; i < menulist1.size(); i++)
                {
                    Map<String, Object> mainMenuItem = new Hashtable<String, Object>();
                    if (menulist1.get(i) == null)
                        continue;
                    // 主菜单
                    if ("-1".equals(convO2String(menulist1.get(i).get("PARENTID")))
                        || "0".equals(convO2String(menulist1.get(i).get("PARENTID"))))
                    {
                        String mainId = menulist1.get(i).get("ID").toString();
                        if (this.isContain(ids, mainId))
                        {
                            mainMenuItem.put("id", mainId);
                            mainMenuItem.put("name", convO2String(menulist1.get(i).get("MENUNAME")));
                            mainMenuItem.put("isLeaf", convO2String(menulist1.get(i).get("IS_LEAF")));
                            mainMenuItem.put("crosswise",
                                CommonUtil.nullToDefault(menulist1.get(i).get("CROSSWISE_TAB"), "0"));
                            // 递归增加子菜单
                            List<Map<String, Object>> subMenuList = new ArrayList<Map<String, Object>>();
                            getMenuItemRecursion(menulist1, mainId, mainId, subMenuList);
                            for (int j = 0; j < subMenuList.size(); j++)
                            {
                                returnList.add(subMenuList.get(j));
                            }
                            mainMenuItem.put("child", subMenuList);
                            returnList.add(mainMenuItem);
                        }
                    }
                }
            }
            
            writeJson(response, returnList);
        }
        return null;
    }
    
    /**
     * 根据主菜单id递归增加子菜单项.
     * 
     * @param allMenulist 菜单信息列表
     * @param p_fmenu_id 父菜单id
     * @param mainId 主菜单id
     * @param subMenuList 子菜单列表
     */
    public void getMenuItemRecursion(List<Map<String, Object>> allMenulist, String p_fmenu_id, String mainId,
        List<Map<String, Object>> subMenuList)
    {
        // 遍历整个菜单列表
        for (int i = 0; i < allMenulist.size(); i++)
        {
            Map<String, Object> menu = allMenulist.get(i);
            String fmenu_id = convO2String(menu.get("PARENTID"));
            String menu_id = convO2String(menu.get("ID"));
            String menu_name = convO2String(menu.get("MENUNAME"));
            String isleaf = convO2String(menu.get("IS_LEAF"));
            String url = convO2String(menu.get("MENUURL"));
            String crosswise = CommonUtil.nullToDefault(menu.get("CROSSWISE_TAB"), "0");
            int isIframe = ((BigDecimal)menu.get("ISIFRAME")).intValue();
            // 根据父菜单id过滤子菜单项
            if (fmenu_id.equals(p_fmenu_id))
            {
                Map<String, Object> subMenu = new HashMap<String, Object>();
                subMenu.put("id", menu_id);
                subMenu.put("name", menu_name);
                subMenu.put("pId", fmenu_id);
                subMenu.put("crosswise", crosswise);
                // if (fmenu_id.equals(mainId)) {
                // subMenu.put("open", true);
                // }
                if (!url.equals(""))
                {
                    subMenu.put("href", url);
                    // if (url.startsWith("http://") || url.startsWith("https://"))
                    if (isIframe == 1)
                    {
                        subMenu.put("isIframe", true);
                    }
                    else
                    {
                        subMenu.put("isIframe", false);
                    }
                }
                subMenu.put("isleaf", isleaf);
                subMenuList.add(subMenu);
                
                Menu m = new Menu();
                m.setMenuId(Long.valueOf(menu_id));
                m.setMenuName(menu_name);
                m.setMenuUrl(url);
                menuList.add(m);
                // 递归循环
                getMenuItemRecursion(allMenulist, menu_id, mainId, subMenuList);
            }
        }
    }
    
    private boolean isContain(String[] menulist, String pid)
    {
        if (menulist.length == 1 && menulist[0].equals("all"))
        {
            return true;
        }
        else
        {
            for (String element : menulist)
            {
                if (element.equals(pid))
                {
                    return true;
                }
            }
            return false;
        }
    }
    
    public String toTop()
        throws Exception
    {
        SimpleDateFormat localTime = new SimpleDateFormat("yyyy-MM-dd E");
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        String t_Str = localTime.format(cal.getTime());
        detail = "欢迎登陆<渲云后台管理系统>,今天是" + t_Str;
        return "top";
    }
    
    /*
     * getter&&setter方法
     */
    public ArrayList<Map<String, Object>> getMenulist1()
    {
        return menulist1;
    }
    
    public void setMenulist1(ArrayList<Map<String, Object>> menulist1)
    {
        this.menulist1 = menulist1;
    }
    
    public ArrayList<Map<String, Object>> getMenulist2()
    {
        return menulist2;
    }
    
    public void setMenulist2(ArrayList<Map<String, Object>> menulist2)
    {
        this.menulist2 = menulist2;
    }
    
    public String convO2String(Object o)
    {
        return o == null ? "" : o.toString();
    }
}
