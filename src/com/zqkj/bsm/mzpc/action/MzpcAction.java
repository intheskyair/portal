package com.zqkj.bsm.mzpc.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.json.simple.JSONValue;

import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.dal.bean.Admin;
import com.zqkj.bsm.mzpc.dao.MzpcDao;
import com.zqkj.bsm.mzpc.entity.DePj;
import com.zqkj.bsm.mzpc.entity.Pj;
import com.zqkj.bsm.system.action.page.OraPaginatedList;
import com.zqkj.bsm.util.CommonUtil;
import com.zqkj.bsm.util.ExcelModel;
import com.zqkj.bsm.util.ExcelOperator;
import com.zqkj.bsm.util.PageData;

@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results({@Result(name = "MzpcList", location = "/WEB-INF/jsp/mzpc/mzpcList.jsp"),
    @Result(name = "canDfList", location = "/WEB-INF/jsp/mzpc/canDfList.jsp"),
    @Result(name = "resultDfList", location = "/WEB-INF/jsp/mzpc/resultDfList.jsp"),
    @Result(name = "error", location = "/failure.jsp")})
@ExceptionMappings({@ExceptionMapping(exception = "java.lange.RuntimeException", result = "error")})
public class MzpcAction extends BaseAction
{
    
    /**
	 * 
	 */
    private static final long serialVersionUID = -4968938517351362927L;
    
    /**
     * 查询所有的民主评测配置信息
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Action(value = "mzpc")
    public String MzpcList()
    {
        String operation = request.getParameter("operation");
        if ("init".equals(operation))
        {
            // 检索参数
            int pageNum = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagenum"), "1")) - 1;
            int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "10"));
            String title = request.getParameter("title") == null ? "" : request.getParameter("title");
            String sortname = CommonUtil.nullToDefault(request.getParameter("sortname"), "");
            String sortorder = CommonUtil.nullToDefault(request.getParameter("sortorder"), "");
            // 分页封装方法
            OraPaginatedList list_tmp = MzpcDao.getInstance().queryMzpc(title, pageNum, pageSize, sortorder, sortname);
            // 分页对象
            PageData pg =
                CommonUtil.fomateResult(list_tmp.getList(), pageNum + 1, pageSize, list_tmp.getFullListSize());
            writeJson(response, CommonUtil.formatFGMap(pg));
            return null;
        }
        else
        {
            return "MzpcList";
        }
        
    }
    
    /**
     * 可以打分的列表
     * 
     * @return
     */
    public String canDfList()
    {
        String operation = request.getParameter("operation");
        if ("init".equals(operation))
        {
            // 检索参数
            Admin admin = (Admin)session.getAttribute("admin");
            int pageNum = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagenum"), "1")) - 1;
            int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "10"));
            String title = request.getParameter("title") == null ? "" : request.getParameter("title");
            String sortname = CommonUtil.nullToDefault(request.getParameter("sortname"), "");
            String sortorder = CommonUtil.nullToDefault(request.getParameter("sortorder"), "");
            // 分页封装方法
            OraPaginatedList list_tmp =
                MzpcDao.getInstance().candfMzpc(title, pageNum, pageSize, sortorder, sortname, admin.getAdminId());
            // 分页对象
            PageData pg =
                CommonUtil.fomateResult(list_tmp.getList(), pageNum + 1, pageSize, list_tmp.getFullListSize());
            writeJson(response, CommonUtil.formatFGMap(pg));
            return null;
        }
        else
        {
            return "canDfList";
        }
        
    }
    
    /**
     * 评测表的字典数据
     */
    public void kh_dic()
    {
        writeJson(response, MzpcDao.getInstance().querykh_dic());
    }
    
    /**
     * 保存评测表配置
     * 
     * @throws Exception
     */
    public void savepfMain()
        throws Exception
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return = "";
        boolean flag = MzpcDao.getInstance().savekh_main(request);
        if (flag)
        {
            
            res_return = "{msg:\"ok\"}";
        }
        else
        {
            res_return = "{msg:\"保存评分配置失败\"}";
        }
        PrintWriter out = null;
        try
        {
            out = response.getWriter();
            out.write(res_return);
            out.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            out.close();
        }
    }
    
    public void updatefMain()
        throws Exception
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return = "";
        if (MzpcDao.getInstance().updatekh_main(request))
        {
            res_return = "{msg:\"ok\"}";
        }
        else
        {
            res_return = "{msg:\"修改评测表配置失败\"}";
        }
        PrintWriter out = null;
        try
        {
            out = response.getWriter();
            out.write(res_return);
            out.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            out.close();
        }
    }
    
    public void delfMain()
        throws Exception
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return = "";
        if (MzpcDao.getInstance().delkh_main(request))
        {
            res_return = "{msg:\"ok\"}";
        }
        else
        {
            res_return = "{msg:\"删除评测表配置失败\"}";
        }
        PrintWriter out = null;
        try
        {
            out = response.getWriter();
            out.write(res_return);
            out.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            out.close();
        }
    }
    
    /**
     * 根据评测表的字典字段来跳转到相应的页面
     */
    public void jumpHtml()
    {
        // 检索参数
        String mainid = CommonUtil.nullToDefault(request.getParameter("mainid"), "");
        String type = CommonUtil.nullToDefault(request.getParameter("type"), "view");
        String radom = CommonUtil.nullToDefault(request.getParameter("radom"), "");
        Map<String, Object> list_tmp = MzpcDao.getInstance().queryById(mainid);
        String DIC_PAHT = CommonUtil.nullToDefault(list_tmp.get("DIC_PAHT"), "");
        Map<String, Object> map = new HashMap<String, Object>();
        // 打分页面路径
        if (type.equals("edit"))
        {
            Admin admin = (Admin)session.getAttribute("admin");
            List<Map<String, Object>> list_df_oper = MzpcDao.getInstance().querykh_pf(mainid, radom);
            if (list_df_oper != null && list_df_oper.size() > 0)
            {
                map.put("error", "你已经打过分了");
            }
            else
            {
                DIC_PAHT = DIC_PAHT.replace(".html", ".jsp");
                String[] dicpahts = DIC_PAHT.split("/");
                DIC_PAHT = dicpahts[0] + "/mark_edit/" + dicpahts[1];
            }
        }
        // 预览页面路径
        else
        {
            DIC_PAHT = DIC_PAHT.replace(".html", ".jsp");
        }
        
        map.put("url", DIC_PAHT);
        map.put("mainid", mainid);
        if (type.equals("view") || type.equals("edit"))
        {
            map.put("type_view", 0);
        }
        else
        {
            map.put("type_view", 1);
        }
        writeJson(response, map);
    }
    
    /**
     * 生成评测配置的预览表
     * 
     * @return
     */
    public void cretateMzpc()
    {
        // 检索参数
        String mainid = CommonUtil.nullToDefault(request.getParameter("mainid"), "");
        Map<String, Object> list_tmp = MzpcDao.getInstance().queryById(mainid);
        String zb = CommonUtil.nullToDefault(list_tmp.get("ZB"), "");
        String max = CommonUtil.nullToDefault(list_tmp.get("MAX"), "0");
        String PARAVALUE = CommonUtil.nullToDefault(list_tmp.get("PARAVALUE"), "");
        String DIC_PAHT = CommonUtil.nullToDefault(list_tmp.get("DIC_PAHT"), "");
        String TITLE = CommonUtil.nullToDefault(list_tmp.get("TITLE"), "");
        // 总分打分的选项
        String ZT_PJ = CommonUtil.nullToDefault(list_tmp.get("ZT_PJ"), "");
        String TYPE = CommonUtil.nullToDefault(list_tmp.get("BTYPE"), "0");
        String[] ZT_PJ_S = ZT_PJ.split("~");
        String[] abcd = ZT_PJ_S[0].split(",");
        String[] value = ZT_PJ_S[1].split(",");
        List<Map<String, Object>> list_pj = new ArrayList<Map<String, Object>>();
        if (ZT_PJ_S.length > 0)
        {
            for (int i = 0, len = abcd.length; i < len; i++)
            {
                Map<String, Object> map_ = new HashMap<String, Object>();
                map_.put(abcd[i], value[i]);
                list_pj.add(map_);
            }
        }
        
        // 职务
        String POST = CommonUtil.nullToDefault(list_tmp.get("POST"), "");
        Map<String, Object> map_return = new HashMap<String, Object>();
        String[] PARAVALUES = PARAVALUE.split(",");
        String[] zbs = zb.split(",");
        String[] POSTs = POST.split(",");
        int len_zb = zbs.length;
        int len_post = 0;
        if (!POST.equals(""))
        {
            len_post = POSTs.length;
            // 有职务的表
            // map_return.put("type", 1);
        }
        // 其他一般的表格
        else
        {
            // map_return.put("type", 0);
        }
        map_return.put("type", TYPE);
        List<Map<String, Object>> list_return = new ArrayList<Map<String, Object>>();
        for (int i = 0, len = PARAVALUES.length; i < len; i++)
        {
            String name = PARAVALUES[i];
            String post = "";
            if (len_post > 0)
            {
                if (i <= len_post - 1)
                {
                    post = POSTs[i];
                }
                else
                {
                    post = "无";
                }
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("NAME", name);
            map.put("COLS", len_zb);
            map.put("POST", post);
            List<Map<String, Object>> list_ = new ArrayList<Map<String, Object>>();
            if (len_zb > 0)
            {
                for (String zbt : zbs)
                {
                    Map<String, Object> map_ = new HashMap<String, Object>();
                    map_.put("ZB", zbt);
                    list_.add(map_);
                }
            }
            map.put("DATALIST", list_);
            list_return.add(map);
        }
        
        map_return.put("url", DIC_PAHT);
        map_return.put("TITLE", TITLE);
        map_return.put("list", list_return);
        map_return.put("pj_list", list_pj);
        map_return.put("max", max);
        String json = JSONValue.toJSONString(map_return);
        // System.out.println(json);
        writeJson(response, map_return);
    }
    
    /**
     * 预览自己的打分明细
     * 
     * @return
     */
    @SuppressWarnings("rawtypes")
    public void viewMzpc()
    {
        // 检索参数
        String mainid = CommonUtil.nullToDefault(request.getParameter("mainid"), "");
        String radom = CommonUtil.nullToDefault(request.getParameter("radom"), "");
        Map<String, Object> map_main = MzpcDao.getInstance().queryById(mainid);
        String DIC_PAHT = CommonUtil.nullToDefault(map_main.get("DIC_PAHT"), "");
        // 此用户的打分结果明细
        List<Map<String, Object>> list_df_oper = MzpcDao.getInstance().querykh_pf(mainid, radom);
        // 评测表的指标项，需要他的顺序显示
        String zb = CommonUtil.nullToDefault(map_main.get("ZB"), "");
        String TITLE = CommonUtil.nullToDefault(map_main.get("TITLE"), "");
        // 总分打分的选项
        String ZT_PJ = CommonUtil.nullToDefault(map_main.get("ZT_PJ"), "");
        String TYPE = CommonUtil.nullToDefault(map_main.get("BTYPE"), "0");
        String[] ZT_PJ_S = ZT_PJ.split("~");
        String[] abcd = ZT_PJ_S[0].split(",");
        String[] value = ZT_PJ_S[1].split(",");
        List<Map<String, Object>> list_pj = new ArrayList<Map<String, Object>>();
        // 打分的结果，例如 A+,A,B,C,D~优秀,嘉奖,合格,基本合格,不合格
        Map<String, Object> map_total = new HashMap<String, Object>();
        if (ZT_PJ_S.length > 0)
        {
            for (int i = 0, len = abcd.length; i < len; i++)
            {
                map_total.put(abcd[i], value[i]);
            }
        }
        
        // 职务
        String POST = CommonUtil.nullToDefault(map_main.get("POST"), "");
        Map<String, Object> map_return = new HashMap<String, Object>();
        String[] zbs = zb.split(",");
        String[] POSTs = POST.split(",");
        int len_zb = zbs.length;
        int len_post = 0;
        if (!POST.equals(""))
        {
            len_post = POSTs.length;
            // 有职务的表
            // map_return.put("type", 1);
        }
        // 其他一般的表格
        else
        {
            // map_return.put("type", 0);
        }
        map_return.put("type", TYPE);
        List<Map<String, Object>> list_return = new ArrayList<Map<String, Object>>();
        for (int i = 0, len = list_df_oper.size(); i < len; i++)
        {
            String post = "";
            if (len_post > 0)
            {
                if (i <= len_post - 1)
                {
                    post = POSTs[i];
                }
                else
                {
                    post = "无";
                }
            }
            
            Map<String, Object> map = new HashMap<String, Object>();
            Map<String, Object> map_db = list_df_oper.get(i);
            map.put("NAME", map_db.get("NAME"));
            // 打分的明细数据json格式
            // [{"A":[0,0,0,0,0,0,0,0,0,0,0]},{"B":[1,1,1,1,1,1,1,1,1,1,1]},{"C":[0,0,0,0,0,0,0,0,0,0,0]},
            // {"D":[0,0,0,0,0,0,0,0,0,0,0]},{"E":[0,0,0,0,0,0,0,0,0,0,0]}]
            String paravalue = CommonUtil.nullToDefault(map_db.get("PARA_VALUE"), "[]");
            JSONArray df_list = JSONArray.fromObject(paravalue);
            map.put("COLS", len_zb);
            map.put("POST", post);
            map.put("TOTAL", map_total.get(map_db.get("TOTAL")));
            List<Map<String, Object>> list_ = new ArrayList<Map<String, Object>>();
            if (len_zb > 0)
            {
                for (String zbt : zbs)
                {
                    Map<String, Object> map_ = new HashMap<String, Object>();
                    map_.put("ZB", zbt);
                    if (len_post > 0)
                    {
                        map_.put("ZB_VALUE", df_list);
                    }
                    else
                    {
                        for (int j = 0, len_j = df_list.size(); j < len_j; j++)
                        {
                            JSONObject json = df_list.getJSONObject(j);
                            if (json.containsKey(zbt))
                            {
                                JSONArray zbt_value = JSONArray.fromObject(json.get(zbt));
                                map_.put("ZB_VALUE", zbt_value);
                                break;
                            }
                            
                        }
                    }
                    list_.add(map_);
                }
            }
            map.put("DATALIST", list_);
            list_return.add(map);
        }
        
        map_return.put("url", DIC_PAHT);
        map_return.put("TITLE", TITLE);
        map_return.put("list", list_return);
        map_return.put("pj_list", list_pj);
        String json = JSONValue.toJSONString(map_return);
        // System.out.println(json);
        writeJson(response, map_return);
        
    }
    
    /**
     * 获取指定评测配置
     * 
     * @param id
     */
    public void getMzpcInfo()
    {
        String id = CommonUtil.nullToDefault(request.getParameter("id"), "");
        Map<String, Object> map = MzpcDao.getInstance().queryById(id);
        
        int count = MzpcDao.getInstance().getRadomUsed(id);
        map.put("used", count);
        writeJson(response, map);
    }
    
    public String resultDfList()
    {
        String operation = request.getParameter("operation");
        if ("init".equals(operation))
        {
            // 检索参数
            int pageNum = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagenum"), "1")) - 1;
            int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "30"));
            String title = request.getParameter("title") == null ? "" : request.getParameter("title");
            String sortname = CommonUtil.nullToDefault(request.getParameter("sortname"), "");
            String sortorder = CommonUtil.nullToDefault(request.getParameter("sortorder"), "");
            // 分页封装方法
            OraPaginatedList list = MzpcDao.getInstance().queryMzpc(title, pageNum, pageSize, sortorder, sortname);
            // 分页对象
            PageData pg = CommonUtil.fomateResult(list.getList(), pageNum + 1, pageSize, list.getFullListSize());
            writeJson(response, CommonUtil.formatFGMap(pg));
            return null;
        }
        else
        {
            return "resultDfList";
        }
        
    }
    
    public void totalkh_pf()
    {
        // 检索参数
        String mainid = CommonUtil.nullToDefault(request.getParameter("mainid"), "0");
        int pageNum = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagenum"), "1")) - 1;
        int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "30"));
        String sortname = CommonUtil.nullToDefault(request.getParameter("sortname"), "");
        String sortorder = CommonUtil.nullToDefault(request.getParameter("sortorder"), "");
        // 分页封装方法
        Map<String, Integer> map_count = new HashMap<String, Integer>();
        int from = (pageNum) * pageSize;
        int to = pageSize;
        // 此方法只为获取当前测评表的类型
        Map<String, String> map_tmp = MzpcDao.getInstance().querykh_dic(mainid);
        List<Map<String, Object>> list_tmp =
            MzpcDao.getInstance().totalkh_pf(mainid, from, to, sortorder, sortname, map_count, map_tmp.get("TYPE"));
        // 分页对象
        for (Map<String, Object> map : list_tmp)
        {
            String detail = "";
            if (map_tmp.get("TYPE").equals("2"))
            {
                detail =
                    "10分(" + String.valueOf(map.get("10_TOTAL")) + "个)" + "9分(" + String.valueOf(map.get("9_TOTAL"))
                        + "个)" + "8分(" + String.valueOf(map.get("8_TOTAL")) + "个)" + "7分("
                        + String.valueOf(map.get("7_TOTAL")) + "个)" + "6分(" + String.valueOf(map.get("6_TOTAL")) + "个)"
                        + "5分(" + String.valueOf(map.get("5_TOTAL")) + "个)" + "4分("
                        + String.valueOf(map.get("4_TOTAL")) + "个)" + "3分(" + String.valueOf(map.get("3_TOTAL")) + "个)"
                        + "2分(" + String.valueOf(map.get("2_TOTAL")) + "个)" + "1分("
                        + String.valueOf(map.get("1_TOTAL")) + "个)";
            }
            else
            {
                detail =
                    "A+<" + map_tmp.get("A+") + ">(" + String.valueOf(map.get("AJ_TOTAL")) + "个)" + "A<"
                        + map_tmp.get("A") + ">(" + String.valueOf(map.get("A_TOTAL")) + "个)" + "B<" + map_tmp.get("B")
                        + ">(" + String.valueOf(map.get("B_TOTAL")) + "个)" + "C<" + map_tmp.get("C") + ">("
                        + String.valueOf(map.get("C_TOTAL")) + "个)" + "D<" + map_tmp.get("D") + ">("
                        + String.valueOf(map.get("D_TOTAL")) + "个)";
            }
            map.put("DETAIL", detail);
        }
        PageData pg = CommonUtil.fomateResult(list_tmp, pageNum + 1, pageSize, map_count.get("total"));
        writeJson(response, CommonUtil.formatFGMap(pg));
    }
    
    @SuppressWarnings("rawtypes")
    /**
     * 保存登录人的打分结果+打分明细
     * @throws Exception
     */
    public void savepdf()
        throws Exception
    {
        String json_string = CommonUtil.nullToDefault(request.getParameter("json"), "{}");
        String mainid = CommonUtil.nullToDefault(request.getParameter("mainid"), "");
        String radom = CommonUtil.nullToDefault(request.getParameter("radom"), "");
        List<Map<String, Object>> list_tmp = new ArrayList<Map<String, Object>>();
        JSONArray items = JSONArray.fromObject(json_string);
        for (int i = 0, len = items.size(); i < len; i++)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            JSONObject json = items.getJSONObject(i);
            Iterator it = json.keySet().iterator();
            while (it.hasNext())
            {
                String key = String.valueOf(it.next());
                if (key.equals("NAME"))
                {
                    map.put(key, json.getString(key));
                }
                if (key.equals("PJ"))
                {
                    map.put(key, json.getString(key));
                }
                if (key.equals("ROW"))
                {
                    JSONArray df_list = JSONArray.fromObject(json.get(key));
                    map.put("para_value", df_list.toString());
                }
            }
            list_tmp.add(map);
        }
        String res_return = "";
        List<Map<String, Object>> list = MzpcDao.getInstance().getRadomStatusTb(radom, mainid);
        if (list.get(0).get("KR_STATUS").toString().equals("1"))
        {
            res_return = "{msg:\"该随机码已失效\"}";
        }
        else
        {
            Map<String, Object> map_main = MzpcDao.getInstance().queryById(mainid);
            String zb = CommonUtil.nullToDefault(map_main.get("ZB"), "");
            if (MzpcDao.getInstance().savepdf(mainid, radom, zb, list_tmp))
            {
                MzpcDao.getInstance().updateRadom(radom, mainid);
                res_return = "{msg:\"ok\"}";
            }
            else
            {
                res_return = "{msg:\"保存打分数据出错，数据库写入失败\"}";
            }
        }
        PrintWriter out = null;
        try
        {
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();
            out.write(res_return);
            out.flush();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            out.close();
        }
        
    }
    
    public void saveType3pdf()
        throws Exception
    {
        String json_string = CommonUtil.nullToDefault(request.getParameter("json"), "{}");
        String mainid = CommonUtil.nullToDefault(request.getParameter("mainid"), "");
        String radom = CommonUtil.nullToDefault(request.getParameter("radom"), "");
        String msg = CommonUtil.nullToDefault(request.getParameter("msg"), "");
        String total = CommonUtil.nullToDefault(request.getParameter("total"), "");
        if (!msg.equals(""))
        {
            msg = URLDecoder.decode(msg, "utf-8");
        }
        String res_return = "";
        List<Map<String, Object>> list = MzpcDao.getInstance().getRadomStatusTb(radom, mainid);
        if (list.get(0).get("KR_STATUS").toString().equals("1"))
        {
            res_return = "{msg:\"该随机码已失效\"}";
        }
        else
        {
            if (MzpcDao.getInstance().savepdf3(mainid, radom, json_string, msg, total))
            {
                MzpcDao.getInstance().updateRadom(radom, mainid);
                res_return = "{msg:\"ok\"}";
            }
            else
            {
                res_return = "{msg:\"保存打分数据出错，数据库写入失败\"}";
            }
        }
        PrintWriter out = null;
        try
        {
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();
            out.write(res_return);
            out.flush();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            out.close();
        }
        
    }
    
    public void showRadom()
    {
        String id = CommonUtil.nullToDefault(request.getParameter("id"), "0");
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        list = MzpcDao.getInstance().getRadom(id, null);
        writeGson(response, list);
    }
    
    public void upRadom()
        throws IOException
    {
        String id = CommonUtil.nullToDefault(request.getParameter("mainid"), "0");
        String radom = CommonUtil.nullToDefault(request.getParameter("radom"), "0");
        String radoms[] = radom.replace("\n", "").split(",");
        MzpcDao.getInstance().delRodam(Long.parseLong(id));
        ArrayList<Object[]> list = new ArrayList<Object[]>();
        for (int i = 0; i < radoms.length; i++)
        {
            Object[] obj = new Object[] {radoms[i], id};
            list.add(obj);
        }
        MzpcDao.getInstance().updateRadomList(list);
        response.getWriter().write("success");
    }
    
    public void getKHPF()
    {
        String mainid = CommonUtil.nullToDefault(request.getParameter("mainid"), "");
        String radom = CommonUtil.nullToDefault(request.getParameter("radom"), "");
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        list = MzpcDao.getInstance().querykh_pf(mainid, radom);
        if (list.size() == 0)
        {
            writeGson(response, null);
        }
        else
        {
            writeGson(response, list.get(0));
        }
        
    }
    
    /**
     * @throws ParseException
     * @Title: outExcel
     * @Description: TODO(到处明主评测)
     * @param @throws IOException 设定文件
     * @return void 返回类型
     * @throws
     */
    public void outExcel()
        throws IOException, ParseException
    {
        
        String id = CommonUtil.nullToDefault(request.getParameter("mainid"), "0");
        int status = Integer.parseInt(MzpcDao.getInstance().getMzpcStatus(id).get("TYPE").toString());
        if (status == 0)
        {
            responseTypeZero(id);
        }
        else if (status == 1)
        {
            responseTypeOne(id);
        }
        else if (status == 2)
        {
            responseTypeTwo(id);
        }
        else if (status == 3)
        {
            responseTypeThree(id);
        }
        else if (status == 4)
        {
            responseTypeFour(id);
        }
        else if (status == 5)
        {
            responseTypeFive(id);
        }
        
    }
    
    /**
     * @Title: sumPoint
     * @Description: TODO(第一种类型处理)
     * @param @param mainid
     * @param @param listResult
     * @param @return 设定文件
     * @return ArrayList<Map<String,Object>> 返回类型
     * @throws
     */
    private ArrayList<Map<String, Object>> sumPoint(String mainid, ArrayList<Map<String, Object>> listResult)
    {
        int status = 1;
        BigDecimal size = new BigDecimal(MzpcDao.getInstance().getRadom(mainid, "1").size());
        if (size.compareTo(BigDecimal.ZERO) != 0)
        {
            for (Map<String, Object> map : listResult)
            {
                int sum = 0;
                sum += Integer.parseInt(map.get("A+").toString()) * 95;
                sum += Integer.parseInt(map.get("A").toString()) * 90;
                sum += Integer.parseInt(map.get("B").toString()) * 80;
                sum += Integer.parseInt(map.get("C").toString()) * 70;
                sum += Integer.parseInt(map.get("D").toString()) * 60;
                map.put("POINT", new BigDecimal(sum).divide(size, 1, BigDecimal.ROUND_HALF_EVEN));
            }
            Collections.sort(listResult, new Comparator<Map<String, Object>>()
            {
                public int compare(Map<String, Object> o1, Map<String, Object> o2)
                {
                    BigDecimal map1value = new BigDecimal(o1.get("POINT").toString());
                    BigDecimal map2value = new BigDecimal(o2.get("POINT").toString());
                    return map2value.compareTo(map1value);
                }
            });
            for (Map<String, Object> map : listResult)
            {
                map.put("ID", status);
                status++;
            }
        }
        return listResult;
    }
    
    /**
     * @Title: sumPoint
     * @Description: TODO(第一种类型处理)
     * @param @param mainid
     * @param @param listResult
     * @param @return 设定文件
     * @return ArrayList<Map<String,Object>> 返回类型
     * @throws
     */
    private ArrayList<Map<String, Object>> sumPointTwo(String mainid, ArrayList<Map<String, Object>> listResult)
    {
        int status = 1;
        BigDecimal size = new BigDecimal(MzpcDao.getInstance().getRadom(mainid, "1").size());
        if (size.compareTo(BigDecimal.ZERO) != 0)
        {
            for (Map<String, Object> map : listResult)
            {
                int sum = 0;
                sum += Integer.parseInt(map.get("10分").toString()) * 10;
                sum += Integer.parseInt(map.get("9分").toString()) * 9;
                sum += Integer.parseInt(map.get("8分").toString()) * 8;
                sum += Integer.parseInt(map.get("7分").toString()) * 7;
                sum += Integer.parseInt(map.get("6分").toString()) * 6;
                sum += Integer.parseInt(map.get("5分").toString()) * 5;
                sum += Integer.parseInt(map.get("4分").toString()) * 4;
                sum += Integer.parseInt(map.get("3分").toString()) * 3;
                sum += Integer.parseInt(map.get("2分").toString()) * 2;
                sum += Integer.parseInt(map.get("1分").toString()) * 1;
                map.put("POINT", new BigDecimal(sum).divide(size, 1, BigDecimal.ROUND_HALF_EVEN));
            }
            Collections.sort(listResult, new Comparator<Map<String, Object>>()
            {
                public int compare(Map<String, Object> o1, Map<String, Object> o2)
                {
                    BigDecimal map1value = new BigDecimal(o1.get("POINT").toString());
                    BigDecimal map2value = new BigDecimal(o2.get("POINT").toString());
                    return map2value.compareTo(map1value);
                }
            });
            for (Map<String, Object> map : listResult)
            {
                map.put("ID", status);
                status++;
            }
        }
        return listResult;
    }
    
    /**
     * @throws IOException
     * @Title: responseType0
     * @Description: TODO(输出type为0类的excel)
     * @param @param id 设定文件
     * @return void 返回类型
     * @throws
     */
    @SuppressWarnings("unchecked")
    private void responseTypeThree(String id)
        throws IOException
    {
        ExcelModel downExcel = new ExcelModel();
        ArrayList<Map<String, Object>> list = MzpcDao.getInstance().getMzpfResultThree(id);
        Map<String, Object> map = MzpcDao.getInstance().getMainTitle(id);
        Map<String, Object> mapResult = MzpcDao.getInstance().getResultRadom(id);
        // list = sumPointTwo(id, list);
        //
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        // 意见内容List
        List<String> msgList = new ArrayList<String>();
        // 第二栏其他内容
        List<String> ThreeList = new ArrayList<String>();
        // 第一栏内容
        Map<Integer, Pj> mapOT = new HashMap<Integer, Pj>();
        mapOT.put(1, new Pj("1、对本地区（本单位）选人用人工作的总体评价"));
        mapOT.put(2, new Pj("2、对本地区（本单位）执行选人用人工作政策法规情况的看法"));
        mapOT.put(3, new Pj("3、对本地区（本单位）整治用人上不正之风工作的看法"));
        mapOT.put(4, new Pj("4、对本地区（本单位）深化干部人事制度改革的看法"));
        mapOT.put(5, new Pj("5、对本地区（本单位）推动干部担当作为、防治为官不为的看法"));
        mapOT.put(6, new Pj("6、你认为本地区（本单位）选人用人工作存在的突出问题是什么（可多选）"));
        mapOT.put(7, new Pj("7、你对本地区（本单位）选人用人工作有何意见和建议？"));
        
        for (Map<String, Object> map1 : list)
        {
            // 添加内容
            if (map1.get("MSG") != null && !map1.get("MSG").toString().trim().equals(""))
            {
                msgList.add(map1.get("MSG").toString());
            }
            JSONObject obj = JSONObject.fromObject(map1.get("PARA_VALUE").toString());
            // 天假其他内容
            if (obj.get("three") != null && !obj.get("three").toString().trim().equals(""))
            {
                ThreeList.add(URLDecoder.decode(obj.get("three").toString()));
            }
            // 遍历第一栏和第二栏
            JSONArray oneArray = (JSONArray)obj.get("one");
            JSONArray twoArray = (JSONArray)obj.get("two");
            mapOT.get(1).add(oneArray.getInt(0));
            mapOT.get(2).add(oneArray.getInt(1));
            mapOT.get(3).add(oneArray.getInt(2));
            mapOT.get(4).add(oneArray.getInt(3));
            mapOT.get(5).add(oneArray.getInt(4));
            for (int i = 0; i < twoArray.size(); i++)
            {
                mapOT.get(6).add(twoArray.getInt(i));
            }
        }
        resultMap.put("msg", msgList);
        resultMap.put("three", ThreeList);
        resultMap.put("ot", mapOT);
        List<String> headList2 = new ArrayList<String>();
        headList2.add("（1）执行《干部任用条例》规定的资格、条件和程序不严格");
        headList2.add("（2）任人唯亲");
        headList2.add("（3）领导干部用人上个人说了算");
        headList2.add("（4）跑官要官、说情打招呼");
        headList2.add("（5）买官卖官");
        headList2.add("（6）拉票");
        headList2.add("（7）其他 (如选此项，请填写具体内容）");
        headList2.add("（8）不存在突出问题 ");
        resultMap.put("two", headList2);
        downExcel.setResultMap(resultMap);
        downExcel.setMap(mapResult);
        // downExcel.setData(list);
        downExcel.setSheetName(map.get("TITLE") != null ? map.get("TITLE").toString() : "1");
        ArrayList<String> headerList = new ArrayList<String>();
        headerList.add("满 意");
        headerList.add("");
        headerList.add("基本满意");
        headerList.add("");
        headerList.add("不满意");
        headerList.add("");
        headerList.add("不了解");
        headerList.add("");
        downExcel.setHeader(headerList);
        ExcelOperator excelOperator = new ExcelOperator();
        OutputStream out = response.getOutputStream();
        // 清空输出流
        response.reset();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        // 设置响应头和下载保存的文件名
        response.setHeader("content-disposition", "attachment;filename="
            + new String(("民主评测-" + df.format(new Date()) + ".xls").getBytes("GB2312"), "ISO8859-1"));
        // 定义输出类型
        response.setContentType("APPLICATION/msexcel");
        response.setCharacterEncoding("UTF-8");
        try
        {
            excelOperator.WriteMzpcExcel(downExcel, out, 3);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * @throws IOException
     * @Title: responseType0
     * @Description: TODO(输出type为0类的excel)
     * @param @param id 设定文件
     * @return void 返回类型
     * @throws
     */
    @SuppressWarnings("unchecked")
    private void responseTypeFour(String id)
        throws IOException
    {
        ExcelModel downExcel = new ExcelModel();
        ArrayList<Map<String, Object>> list = MzpcDao.getInstance().getMzpfResultThree(id);
        Map<String, Object> map = MzpcDao.getInstance().getMainTitle(id);
        Map<String, Object> mapResult = MzpcDao.getInstance().getResultRadom(id);
        // list = sumPointTwo(id, list);
        //
        
        ArrayList<Map<String, Object>> all = new ArrayList<Map<String, Object>>();
        int status = 0;// 循环停止标志位
        for (Map<String, Object> map2 : list)
        {
            // {MSG=, DF_TIME_SHOW_DATE=上午09:55, NAME=null, ID=1064, TOTAL=, PARA_VALUE=
            // [{"userName":"陈娟","work":"局机关党委","list":["1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"]},{"userName":"糜国才","work":"局机关党委副书记","list":["1","2","2","2","2","2","2","2","1","2","2","2","2","2","2","2"]}],
            // RADOM=002106753, ZB=null, DF_TIME_FORMAT_DATE=2017-01-10, DF_TIME=2017-01-10 09:55:41, MAIN_ID=312}
            JSONArray jsonArray = JSONArray.fromObject(map2.get("PARA_VALUE"));
            
            for (int i = 0; i < jsonArray.size(); i++)
            {
                HashMap<String, Object> resultMap = new HashMap<String, Object>();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Map<Integer, DePj> mapOT = new HashMap<Integer, DePj>();
                if (status > 0)
                {
                    for (Map<String, Object> mapAll : all)
                    {
                        if (mapAll.get("userName").toString().equals(jsonObject.get("userName").toString()))
                        {
                            mapOT = (Map<Integer, DePj>)mapAll.get("df");
                            JSONArray array = jsonObject.getJSONArray("list");
                            for (int j = 0; j < array.size(); j++)
                            {
                                mapOT.get(j + 1).sum(array.getInt(j));
                            }
                        }
                    }
                }
                else
                {
                    mapOT.put(1,
                        new DePj(Integer.parseInt(mapResult.get("ALLRADOM").toString()),
                            Integer.parseInt(mapResult.get("OLDRADOM").toString()), "理想信念", "政治不够坚定，信念动摇"));
                    mapOT.put(2,
                        new DePj(Integer.parseInt(mapResult.get("ALLRADOM").toString()),
                            Integer.parseInt(mapResult.get("OLDRADOM").toString()), "理想信念", "在公开场合发表不当言论"));
                    mapOT.put(3,
                        new DePj(Integer.parseInt(mapResult.get("ALLRADOM").toString()),
                            Integer.parseInt(mapResult.get("OLDRADOM").toString()), "政治纪律", "对上级决策阳奉阴违"));
                    mapOT.put(4,
                        new DePj(Integer.parseInt(mapResult.get("ALLRADOM").toString()),
                            Integer.parseInt(mapResult.get("OLDRADOM").toString()), "政治纪律", "在干部选拔任用中跑官要官、拉票贿选"));
                    mapOT.put(5,
                        new DePj(Integer.parseInt(mapResult.get("ALLRADOM").toString()),
                            Integer.parseInt(mapResult.get("OLDRADOM").toString()), "大局意识", "在班子中闹不团结搞小团体、小圈子，拉帮结派"));
                    mapOT.put(6,
                        new DePj(Integer.parseInt(mapResult.get("ALLRADOM").toString()),
                            Integer.parseInt(mapResult.get("OLDRADOM").toString()), "大局意识", "考虑个人利益较多，不服从组织决定"));
                    mapOT.put(7,
                        new DePj(Integer.parseInt(mapResult.get("ALLRADOM").toString()),
                            Integer.parseInt(mapResult.get("OLDRADOM").toString()), "坚持原则", "回避矛盾，和稀泥，当老好人"));
                    mapOT.put(8,
                        new DePj(Integer.parseInt(mapResult.get("ALLRADOM").toString()),
                            Integer.parseInt(mapResult.get("OLDRADOM").toString()), "坚持原则", "凭个人好恶推荐使用干部，任人唯亲"));
                    mapOT.put(9,
                        new DePj(Integer.parseInt(mapResult.get("ALLRADOM").toString()),
                            Integer.parseInt(mapResult.get("OLDRADOM").toString()), "履职尽责", "工作的主动性不够积极性不高，执行力不强"));
                    mapOT.put(10,
                        new DePj(Integer.parseInt(mapResult.get("ALLRADOM").toString()),
                            Integer.parseInt(mapResult.get("OLDRADOM").toString()), "履职尽责", "在重大任务中退缩不前、推诿塞责，不敢担当"));
                    mapOT.put(11,
                        new DePj(Integer.parseInt(mapResult.get("ALLRADOM").toString()),
                            Integer.parseInt(mapResult.get("OLDRADOM").toString()), "工作作风", "工作浮躁，搞形式主义"));
                    mapOT.put(12,
                        new DePj(Integer.parseInt(mapResult.get("ALLRADOM").toString()),
                            Integer.parseInt(mapResult.get("OLDRADOM").toString()), "工作作风", "作风不实，弄虚作假"));
                    mapOT.put(13,
                        new DePj(Integer.parseInt(mapResult.get("ALLRADOM").toString()),
                            Integer.parseInt(mapResult.get("OLDRADOM").toString()), "个人品行", "以权谋私，吃拿卡要"));
                    mapOT.put(14,
                        new DePj(Integer.parseInt(mapResult.get("ALLRADOM").toString()),
                            Integer.parseInt(mapResult.get("OLDRADOM").toString()), "个人品行", "推卸赡养抚养义务"));
                    mapOT.put(15,
                        new DePj(Integer.parseInt(mapResult.get("ALLRADOM").toString()),
                            Integer.parseInt(mapResult.get("OLDRADOM").toString()), "个人品行", "家庭不和谐，生活作风有问题"));
                    mapOT.put(16,
                        new DePj(Integer.parseInt(mapResult.get("ALLRADOM").toString()),
                            Integer.parseInt(mapResult.get("OLDRADOM").toString()), "德的总体评价", "德的总体评价"));
                    resultMap.put("userName", jsonObject.get("userName"));
                    resultMap.put("work", jsonObject.get("work"));
                    JSONArray array = jsonObject.getJSONArray("list");
                    for (int j = 0; j < array.size(); j++)
                    {
                        mapOT.get(j + 1).sum(array.getInt(j));
                    }
                    resultMap.put("df", mapOT);
                    all.add(resultMap);
                }
            }
            
            status++;
        }
        
        downExcel.setData(all);
        downExcel.setMap(mapResult);
        // downExcel.setData(list);
        downExcel.setSheetName(map.get("TITLE") != null ? map.get("TITLE").toString() : "1");
        
        // downExcel.setHeader(headerList);
        ExcelOperator excelOperator = new ExcelOperator();
        OutputStream out = response.getOutputStream();
        // 清空输出流
        response.reset();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        // 设置响应头和下载保存的文件名
        response.setHeader("content-disposition", "attachment;filename="
            + new String(("民主评测-" + df.format(new Date()) + ".xls").getBytes("GB2312"), "ISO8859-1"));
        // 定义输出类型
        response.setContentType("APPLICATION/msexcel");
        response.setCharacterEncoding("UTF-8");
        try
        {
            excelOperator.WriteMzpcExcel(downExcel, out, 4);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * @throws IOException
     * @Title: responseType0
     * @Description: TODO(输出type为0类的excel)
     * @param @param id 设定文件
     * @return void 返回类型
     * @throws
     */
    @SuppressWarnings("unchecked")
    private void responseTypeFive(String id)
        throws IOException
    {
        ExcelModel downExcel = new ExcelModel();
        ArrayList<Map<String, Object>> list = MzpcDao.getInstance().getMzpfResultThree(id);
        Map<String, Object> map = MzpcDao.getInstance().getMainTitle(id);
        Map<String, Object> mapResult = MzpcDao.getInstance().getResultRadom(id);
        // list = sumPointTwo(id, list);
        //
        ArrayList<Map<Integer, Object>> all = new ArrayList<Map<Integer, Object>>();
        JSONArray name = JSONArray.fromObject(map.get("PARAVALUE"));
        JSONArray sex = JSONArray.fromObject(map.get("SEX"));
        JSONArray birthday = JSONArray.fromObject(map.get("BIRTHDAY"));
        JSONArray oldjob = JSONArray.fromObject(map.get("OLDJOB"));
        JSONArray newjob = JSONArray.fromObject(map.get("NEWJOB"));
        JSONArray jobtime = JSONArray.fromObject(map.get("JOBTIME"));
        for (int i = 0; i < name.size(); i++)
        {
            Map<Integer, Object> map1 = new HashMap<Integer, Object>();
            map1.put(0, name.get(i));
            map1.put(1, sex.get(i));
            map1.put(2, birthday.get(i));
            map1.put(3, oldjob.get(i));
            map1.put(4, newjob.get(i));
            map1.put(5, jobtime.get(i));
            map1.put(6, 0);
            map1.put(7, 0);
            map1.put(8, 0);
            map1.put(9, 0);
            map1.put(10, 0);
            map1.put(11, 0);
            map1.put(12, 0);
            for (Map<String, Object> map2 : list)
            {
                JSONArray js = JSONArray.fromObject(map2.get("PARA_VALUE"));
                for (int j = 0; j < js.size(); j++)
                {
                    JSONObject jo = JSONObject.fromObject(js.get(j));
                    if (jo.get("NAME").toString().equals(name.get(i).toString()))
                    {
                        JSONArray js1 = JSONArray.fromObject(jo.get("list"));
                        for (int k = 0; k < js1.size(); k++)
                        {
                            if (Integer.parseInt(js1.get(k).toString()) == 1)
                            {
                                map1.put(k + 6, Integer.parseInt(map1.get(k + 6).toString()) + 1);
                            }
                        }
                        break;
                    }
                }
            }
            all.add(map1);
        }
        
        downExcel.setData(all);
        downExcel.setMap(mapResult);
        // downExcel.setData(list);
        downExcel.setSheetName(map.get("TITLE") != null ? map.get("TITLE").toString() : "1");
        
        // downExcel.setHeader(headerList);
        ExcelOperator excelOperator = new ExcelOperator();
        OutputStream out = response.getOutputStream();
        // 清空输出流
        response.reset();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        // 设置响应头和下载保存的文件名
        response.setHeader("content-disposition", "attachment;filename="
            + new String(("民主评测-" + df.format(new Date()) + ".xls").getBytes("GB2312"), "ISO8859-1"));
        // 定义输出类型
        response.setContentType("APPLICATION/msexcel");
        response.setCharacterEncoding("UTF-8");
        try
        {
            excelOperator.WriteMzpcExcel(downExcel, out, 5);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * @throws IOException
     * @Title: responseType0
     * @Description: TODO(输出type为0类的excel)
     * @param @param id 设定文件
     * @return void 返回类型
     * @throws
     */
    private void responseTypeTwo(String id)
        throws IOException
    {
        ExcelModel downExcel = new ExcelModel();
        ArrayList<Map<String, Object>> list = MzpcDao.getInstance().getMzpfResultTwo(id);
        Map<String, Object> map = MzpcDao.getInstance().getMainTitle(id);
        Map<String, Object> mapResult = MzpcDao.getInstance().getResultRadom(id);
        list = sumPointTwo(id, list);
        downExcel.setMap(mapResult);
        downExcel.setData(list);
        downExcel.setSheetName(map.get("TITLE") != null ? map.get("TITLE").toString() : "1");
        ArrayList<String> headerList = new ArrayList<String>();
        for (int i = 0; i < 3; i++)
        {
            headerList.add("排名");
            headerList.add("姓名");
            headerList.add("二级指标");
            headerList.add("总体评价");
            headerList.add("得分");
            headerList.add("备注");
        }
        downExcel.setHeader(headerList);
        ExcelOperator excelOperator = new ExcelOperator();
        OutputStream out = response.getOutputStream();
        // 清空输出流
        response.reset();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        // 设置响应头和下载保存的文件名
        response.setHeader("content-disposition", "attachment;filename="
            + new String(("民主评测-" + df.format(new Date()) + ".xls").getBytes("GB2312"), "ISO8859-1"));
        // 定义输出类型
        response.setContentType("APPLICATION/msexcel");
        response.setCharacterEncoding("UTF-8");
        try
        {
            excelOperator.WriteMzpcExcel(downExcel, out, 2);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * @throws IOException
     * @Title: responseType0
     * @Description: TODO(输出type为0类的excel)
     * @param @param id 设定文件
     * @return void 返回类型
     * @throws
     */
    private void responseTypeZero(String id)
        throws IOException
    {
        ExcelModel downExcel = new ExcelModel();
        ArrayList<Map<String, Object>> list = MzpcDao.getInstance().getMzpfResult(id);
        Map<String, Object> map = MzpcDao.getInstance().getMainTitle(id);
        Map<String, Object> mapResult = MzpcDao.getInstance().getResultRadom(id);
        list = sumPoint(id, list);
        downExcel.setMap(mapResult);
        downExcel.setData(list);
        downExcel.setSheetName(map.get("TITLE") != null ? map.get("TITLE").toString() : "1");
        ArrayList<String> headerList = new ArrayList<String>();
        for (int i = 0; i < 3; i++)
        {
            headerList.add("排名");
            headerList.add("姓名");
            headerList.add("二级指标");
            headerList.add("总体评价");
            headerList.add("得分");
            headerList.add("备注");
        }
        downExcel.setHeader(headerList);
        ExcelOperator excelOperator = new ExcelOperator();
        OutputStream out = response.getOutputStream();
        // 清空输出流
        response.reset();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        // 设置响应头和下载保存的文件名
        response.setHeader("content-disposition", "attachment;filename="
            + new String(("民主评测-" + df.format(new Date()) + ".xls").getBytes("GB2312"), "ISO8859-1"));
        // 定义输出类型
        response.setContentType("APPLICATION/msexcel");
        response.setCharacterEncoding("UTF-8");
        try
        {
            excelOperator.WriteMzpcExcel(downExcel, out, 0);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * @throws ParseException
     * @throws IOException
     * @Title: responseType0
     * @Description: TODO(输出type为0类的excel)
     * @param @param id 设定文件
     * @return void 返回类型
     * @throws
     */
    private void responseTypeOne(String id)
        throws IOException, ParseException
    {
        ExcelModel downExcel = new ExcelModel();
        ArrayList<Map<String, Object>> list = MzpcDao.getInstance().getTypeOne(id);
        list = getTypeOneDeal(list);
        Map<String, Object> map = MzpcDao.getInstance().getMainTitle(id);
        String begtime = map.get("BEGTIME").toString();
        String endtime = map.get("ENDTIME").toString();
        String km_status = map.get("STATUS").toString();
        // 判断当前测聘是否过期
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        // 开始时间
        Date beginTime = df.parse(begtime);
        // 结束时间
        Date endTime = df.parse(endtime);
        int begin = beginTime.compareTo(df.parse(df.format(new Date())));
        int end = endTime.compareTo(df.parse(df.format(new Date())));
        if (begin != 1 && end != -1 && km_status.equals("0"))
        {
            downExcel.setStatus(0);
        }
        else
        {
            downExcel.setStatus(1);
        }
        Map<String, Object> mapResult = MzpcDao.getInstance().getResultRadom(id);
        downExcel.setMap(mapResult);
        downExcel.setData(list);
        downExcel.setSheetName(map.get("TITLE") != null ? map.get("TITLE").toString() : "1");
        ExcelOperator excelOperator = new ExcelOperator();
        OutputStream out = response.getOutputStream();
        // 清空输出流
        response.reset();
        SimpleDateFormat df_title = new SimpleDateFormat("yyyyMMdd");
        // 设置响应头和下载保存的文件名
        response.setHeader("content-disposition",
            "attachment;filename="
                + new String(("民主评测-" + df_title.format(new Date()) + ".xls").getBytes("GB2312"), "ISO8859-1"));
        // 定义输出类型
        response.setContentType("APPLICATION/msexcel");
        response.setCharacterEncoding("UTF-8");
        try
        {
            excelOperator.WriteMzpcExcel(downExcel, out, 1);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * @Title: getTypeOneDeal
     * @Description: TODO(第二种类型数据处理)
     * @param @return 设定文件
     * @return List<Map<String,Object>> 返回类型
     * @throws
     */
    private ArrayList<Map<String, Object>> getTypeOneDeal(ArrayList<Map<String, Object>> list)
    {
        ArrayList<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        boolean flag = true;
        Map<String, Object> middle = null;
        for (Map<String, Object> map : list)
        {
            flag = true;
            for (Map<String, Object> mapResult : result)
            {
                if (map.get("NAME").toString().equals(mapResult.get("NAME").toString()))
                {
                    String value = map.get("PARA_VALUE").toString();
                    JSONArray array = JSONArray.fromObject(value);
                    for (int i = 0; i < array.size(); i++)
                    {
                        mapResult.put("A" + i,
                            Integer.parseInt(mapResult.get("A" + i).toString())
                                + Integer.parseInt(array.get(i).toString()));
                    }
                    flag = false;
                }
            }
            if (flag)
            {
                middle = new HashMap<String, Object>();
                middle.put("NAME", map.get("NAME"));
                middle.put("ORG", map.get("ORG_INFOR"));
                String value = map.get("PARA_VALUE").toString();
                JSONArray array = JSONArray.fromObject(value);
                for (int i = 0; i < array.size(); i++)
                {
                    middle.put("A" + i, Integer.parseInt(array.get(i).toString()));
                }
                result.add(middle);
            }
        }
        return result;
    }
}
