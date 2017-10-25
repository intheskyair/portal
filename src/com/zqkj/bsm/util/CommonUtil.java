package com.zqkj.bsm.util;

import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.zqkj.bsm.dal.bean.Menu;

public class CommonUtil {

	public  static Logger log = Logger.getLogger(CommonUtil.class);

    /**
     * 将一个结果集转换成PageData<E>的对象。
     * 
     * @param list 结果集
     * @param pageNum 当前页号
     * @param pageSize 页面内数据数量
     * @return PageData<E> 分页对象
     */
    public static PageData fomateResult(List<Map<String, Object>> list, int pageNum, int pageSize, int totalCount) {

        PageData pd = new PageData();
        int totalPage = totalCount % pageSize > 0 ? totalCount / pageSize + 1 : totalCount / pageSize;
        // 拼装对象
        pd.setList(list);
        pd.setCurrPage(pageNum);
        pd.setTotalPage(totalPage);
        pd.setPageSize(pageSize);
        pd.setTotalDataCount(totalCount);

        return pd;
    }

    /**
     * 格式化成flexigrid需要的数据
     * @param pd PageData<BasePojo>需要格式话的数据
     * 
     * @return flexigrid需要的数据 Map<String, Object>
     */
    public static Map<String, Object> formatFGMap(PageData pd){
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("page", pd.getCurrPage() + "");
        result.put("total", pd.getTotalDataCount() + "");
        result.put("rows", formatFGList(pd));
        return result;
    }

    public static Map<String, Object> formatFGMap_client(PageData pd){
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("page", pd.getCurrPage());
        result.put("total", pd.getTotalDataCount());
        result.put("rows", formatFGList_client(pd));
        return result;
    }
    
    /**
     * 格式化成flexigrid所需要的List
     * @param pd PageData<Map<String, Object>>需要格式话的数据
     * 
     * @return flexigrid需要的数据 Map<String, Object>
     */
    public static List<Map<String, Object>> formatFGList(PageData pd){
        List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
        try{
            if(pd != null && pd.getList() != null && pd.getList().size() != 0){
                List<Map<String, Object>> list = pd.getList();
                int pageNum = pd.getCurrPage();
                int pageSize = pd.getPageSize();
                int xh = 1;
                Map<String, Object> cell = null;
                for(int i=0; i<list.size(); i++){
                    cell = new HashMap<String, Object>();
                    Map<String, Object> element = list.get(i);
                    element.put("xh", (pageNum - 1) * pageSize + xh);
                    cell.put("cell", element);
                    returnList.add(cell);
                    xh++;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new LazyAppException("转换FlexiGrid数据出错！");
        }
        return returnList;
    }
    
    public static List<Map<String, Object>> formatFGList_client(PageData pd){
        List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
        try{
            if(pd != null && pd.getList() != null && pd.getList().size() != 0){
                List<Map<String, Object>> list = pd.getList();
                int pageNum = pd.getCurrPage();
                int pageSize = pd.getPageSize();
                int xh = 1;
                for(int i=0; i<list.size(); i++)
                {
                    Map<String, Object> element = list.get(i);
                    element.put("xh", (pageNum - 1) * pageSize + xh);
                    returnList.add(element);
                    xh++;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new LazyAppException("转换FlexiGrid数据出错！");
        }
        return returnList;
    }
    

    /**
     * 将空的字符串转换成默认的字符串.
     * 
     * @param str 被转换对象
     * @return 字符串
     */
    public static String nullToDefault(Object o, String defaultVal) {
        return o == null || "".equals(o) ? defaultVal : o.toString();
    }
    

    /**
     * 将一个对象的所有属性copy到Map<String, Object>容器中.
     * 
     * @param o 被copy的对象
     * 
     * @throws 抛出反射机制异常
     */
    public static Map<String, Object> copyPropToMap(Object o) throws Exception {

        Map<String, Object> rs = new HashMap<String, Object>();
        // 获取实体类的所有属性，返回Field数组  
        Field[] field = o.getClass().getDeclaredFields();
        // 遍历所有属性
        for(int i=0; i<field.length; i++){
            // 获取属性的名字
            String name = field[i].getName();
            String methordName = "get"+Character.toUpperCase(name.charAt(0)) + name.substring(1);
            // 获取属性值
            Method m = o.getClass().getMethod(methordName);
            // 调用getter方法获取属性值
            rs.put(name, m.invoke(o));
        }

        return rs;
    }

    /**
     * 将一个对象的所有属性copy到Map<String, Object>容器中.
     * 
     * @param o 被copy的对象(javaBean)
     * 
     * @throws 抛出反射机制异常
     */
    public static List<Map<String, Object>> convToMapList(List<? extends Object> list) throws Exception {

    	if (list == null) return null;
        List<Map<String, Object>>  rs = new ArrayList<Map<String, Object>> ();

        for (Object o : list) {
            rs.add(copyPropToMap(o));
        }

        return rs;
    }

    /**
     * 将一个结果集格式化成一个树形所需要的数据.
     * 
     * @param list 树形数据的容器
     * @param menuList 结果集
     */
    public static void formateTreeData(List<Map<String, Object>> list,
    		List<Menu> menuList) {
        if (menuList != null && menuList.size() > 0) {
            for (int i = 0; i < menuList.size(); i++) {
            	Menu menu = menuList.get(i);
                long fmenu_id = menu.getUpMenuId();
                long menu_id = menu.getMenuId();
                String menu_name = menu.getMenuName();
                String url = menu.getMenuUrl();
                String isLeaf = menu.getIsLeaf();

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", menu_id);
                map.put("name", menu_name);
                map.put("pId", fmenu_id);
                if (fmenu_id == -1 || fmenu_id == 0) {
                    map.put("open", true);
                }
                if (url != null && !"".equals(url)) {
                    map.put("href", url);
                    map.put("isleaf", isLeaf);
                    if (url.startsWith("http://") || url.startsWith("https://")) {
                        map.put("isIframe", true);
                    }
                }
                
                list.add(map);
            }
        }
    }

    /**
     * 将一个结果集格式化成一个树形所需要的数据.
     * 
     * @param list 树形数据的容器
     * @param menuList 结果集
     */
    public static void formateTreeDataFormMap(List<Map<String, Object>> list,
    		List<Map<String, Object>> menuList) {
        if (menuList != null && menuList.size() > 0) {
            for (int i = 0; i < menuList.size(); i++) {
            	Map<String, Object> menu = menuList.get(i);
                String fmenu_id = nullToDefault(menu.get("PARENTID"), "");
                String menu_id = nullToDefault(menu.get("ID"), "");
                String menu_name = nullToDefault(menu.get("MENUNAME"), "");
                String url = nullToDefault(menu.get("MENUURL"), "");
                String isLeaf = nullToDefault(menu.get("IS_LEAF"), "");

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", menu_id);
                map.put("name", menu_name);
                map.put("pId", fmenu_id);
                if (("-1").equals(fmenu_id) || ("0").equals(fmenu_id)) {
                    map.put("open", true);
                }
                if (url != null && !"".equals(url)) {
                    map.put("href", url);
                    map.put("isleaf", isLeaf);

                    if (url.startsWith("http://") || url.startsWith("https://")) {
                        map.put("isIframe", true);
                    }
                }
                
                list.add(map);
            }
        }
    }
    
    /**
     * 将一个结果集格式化成一个树形所需要的数据.
     */
    public static void formateTreeDataFormMap2(List<Map<String, Object>> list,
    		List<Map<String, Object>> menuList) {
        if (menuList != null && menuList.size() > 0) {
            for (int i = 0; i < menuList.size(); i++) {
            	Map<String, Object> menu = menuList.get(i);
                String fmenu_id = nullToDefault(menu.get("MENU_ID"), "");
                String menu_id = nullToDefault(menu.get("ID"), "");
                String button_name = nullToDefault(menu.get("BUTTON_NAME"), "");
                String menu_name = nullToDefault(menu.get("MENU_NAME"), "");
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", menu_id);
                map.put("name", button_name);
                map.put("pName", menu_name);
                map.put("pId", fmenu_id);
                list.add(map);
            }
        }
    }

	//得到参数
	public static String readJSONStringFromRequestBody(HttpServletRequest request) 
	{
		StringBuffer json = new StringBuffer();
	    String line = null;
	    try {
	      BufferedReader reader = request.getReader();
	      while ( (line = reader.readLine()) != null) 
	      {
	        json.append(line);
	      }
	    }
	    catch (Exception e) 
	    {
	      log.info("Error reading JSON string: " + e.toString());
	    }
	    return json.toString();
	  }

	/**
	 * 
	 * 转化URL的特殊支付
	 * 
	 * @param url
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String EncodeUrl(String url) throws UnsupportedEncodingException {

    	Map<String, String> chaHex = new HashMap<String, String>();
    	//chaHex.put("%", "%25");
    	chaHex.put("^", "%5E");
    	//chaHex.put("&", "%26");
    	chaHex.put("`", "%60");
    	chaHex.put("<", "%3C");
    	chaHex.put(">", "%3E");
    	//chaHex.put("_", "%5F");
    	chaHex.put("\\", "%5C");
    	chaHex.put("|", "%7C");
    	chaHex.put("{", "%7B");
    	chaHex.put("}", "%7D");
    	//chaHex.put("\"", "%22");
    	//chaHex.put("'", "%27");
    	chaHex.put(" ", "%20");

        if (url != null) {

            // 特殊符号编码%^&`转码
            for (Entry<String, String> b : chaHex.entrySet()) {  
            	url = url.replace(b.getKey(), b.getValue());
           }  
            
        }
         return url;
	}
}
