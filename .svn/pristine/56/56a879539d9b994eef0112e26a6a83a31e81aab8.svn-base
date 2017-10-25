package com.zqkj.bsm.chart.action;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.chart.dao.ChartExport;
import com.zqkj.bsm.chart.dao.UserInfoDao;
import com.zqkj.bsm.system.action.util.StringHelper;
import com.zqkj.bsm.util.BHUtil;
import com.zqkj.bsm.util.CommonUtil;
import com.zqkj.bsm.util.ExcelModel;
import com.zqkj.bsm.util.ExcelOperator;
import com.zqkj.bsm.util.PageData;

public class UserInfo extends BaseAction{

	/**
	 *用户信息统计 
	 */
	private static final long serialVersionUID = 1L;

	
	public String init(){
		return "initPage";
	}
	
	public String userinfo_login_Init(){
		return "userinfo_login";
	}
	
	public String userinfo_total_Init(){
		return "userinfo_total";
	}
	
	
	/**
	 * 按时间---注册数
	 */
	public String queryRegisterNum_trend() throws Exception{
		// 查询时间段
		String send_time1 = CommonUtil.nullToDefault(request.getParameter("send_time1"), "");
		String send_time2 = CommonUtil.nullToDefault(request.getParameter("send_time2"), "");
		int cycle = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("cycle"), "1")) ;
		UserInfoDao dao = UserInfoDao.getInstance();
		List<Map<String, Object>> pg = dao.queryRegisterNum_trend( send_time1,send_time2,cycle);
		if(cycle == 2){
			pg = BHUtil.weekToDate(pg);
		}
		writeJson(response, pg);
		return null;
	}
	/**
	 * 按地区---注册数
	 */
	public String queryRegisterNum_place() throws Exception{
		// 查询时间段
		String send_time1 = CommonUtil.nullToDefault(request.getParameter("send_time1"), "");
		String send_time2 = CommonUtil.nullToDefault(request.getParameter("send_time2"), "");
		UserInfoDao dao = UserInfoDao.getInstance();
		List<Map<String, Object>> pg = dao.queryRegisterNum_place( send_time1,send_time2);
		writeJson(response, pg);
		return null;
	}
	
	/**
	 *  登录信息 
	 */
	public String queryLoginInfor() throws Exception{
		 int pageNum = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagenum"), "1"));
         int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "15"));
         Date t1 = new Date();
         String s1 = new SimpleDateFormat("yyyy-MM-dd").format(t1);
         Calendar calendar = Calendar.getInstance();
         String s2 = calendar.get(Calendar.YEAR) + "-01-01";
         String send_time1 = CommonUtil.nullToDefault(request.getParameter("send_time1"),s2);
         String send_time2 = CommonUtil.nullToDefault(request.getParameter("send_time2"),s1);
         String username = request.getParameter("username") == null ? "" : request.getParameter("username");
         String sortName = CommonUtil.nullToDefault(request.getParameter("sortname"), "");
         String sortOrder = CommonUtil.nullToDefault(request.getParameter("sortorder"), "");
         UserInfoDao uid = UserInfoDao.getInstance();
         //按用户汇总
         List<Map<String, Object>> list = uid.queryLoginInfor(send_time1,send_time2, username, pageNum, pageSize, sortName, sortOrder);
         int count = uid.queryLoginInforCount(send_time1,send_time2, username);
         // 分页对象
         PageData pg = null;
 		pg = CommonUtil.fomateResult(list, pageNum, pageSize, count);
 		writeJson(response, CommonUtil.formatFGMap(pg));
         return null;
	}
	 /**
     * 导出一定条件之下的数据
     * @throws IOException 
     * 
     */
    public void exportJobs() throws IOException {

    	request.setCharacterEncoding("utf-8");
        JSONArray columns = JSONArray.fromObject(request.getParameter("columns"));
        String username = request.getParameter("username") == null ? "" : request.getParameter("username");
        Date t1 = new Date();
        String s1 = new SimpleDateFormat("yyyy-MM-dd").format(t1);
        Calendar calendar = Calendar.getInstance();
        String s2 = calendar.get(Calendar.YEAR) + "-01-01";
        String send_time1 = CommonUtil.nullToDefault(request.getParameter("send_time1"),s2);
        String send_time2 = CommonUtil.nullToDefault(request.getParameter("send_time2"),s1);
        UserInfoDao uid = UserInfoDao.getInstance();
        List<Map<String, Object>> items = uid.getLoginInfor(username,send_time1,send_time2);
        response.reset();
        OutputStream out = response.getOutputStream();
        // 清空输出流
        response.reset();
        // 设置响应头和下载保存的文件名
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        response.setHeader("content-disposition", "attachment;filename=" +  StringHelper.processFileName(request, "用户登录信息-"+ df.format(new Date()) + ".xls"));
        // 定义输出类型
        response.setContentType("APPLICATION/msexcel");

        ExcelModel downExcel = new ExcelModel();
        downExcel.setSheetName("用户登录信息数据");
        ArrayList<String> headerList = new ArrayList<String>();
        for (int i = 0; i < columns.size(); i++) {
            String key = columns.getString(i);
            headerList.add(ChartExport.getLoginInforName(key));
        }
        downExcel.setHeader(headerList);
        ArrayList<ArrayList<Object>> dataList = new ArrayList<ArrayList<Object>>();
        for (int i = 0,len=items.size(); i < len; i++) 
        {
        	Map<String, Object> row = items.get(i);
            ArrayList<Object> dataline = new ArrayList<Object>();
            for (int j = 0; j < columns.size(); j++) 
            {
                String key = columns.getString(j);
                    dataline.add(row.get(key.toUpperCase())==null?"":row.get(key.toUpperCase()));
            }
            dataList.add(dataline);
        }
        downExcel.setData(dataList);
        ExcelOperator op = new ExcelOperator();
        try {
            op.WriteExcel(downExcel, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.close();
        response.flushBuffer();// 强行将响应缓存中的内容发送到目的地
    }
}
