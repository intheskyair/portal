package com.zqkj.bsm.sms.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.sms.dao.SendedMessageDao;
import com.zqkj.bsm.sms.util.ExportContent;
import com.zqkj.bsm.system.action.util.StringHelper;
import com.zqkj.bsm.util.CommonUtil;
import com.zqkj.bsm.util.ExcelModel;
import com.zqkj.bsm.util.ExcelOperator;
import com.zqkj.bsm.util.PageData;

public class SendedMessageAction extends BaseAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 查询已发送短信
	 * @return
	 */	
	public String querySendedSMS() throws Exception
    {       
        String operation = request.getParameter("operation");
        if("init".equals(operation))
        {
            // 检索参数
            int pageNum = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagenum"), "1"));
            int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "15"));
            String phone = request.getParameter("phone") == null ? "" : request.getParameter("phone");
            String send_time1 = request.getParameter("send_time1") == null ? "" : request.getParameter("send_time1");
            String send_time2 = request.getParameter("send_time2") == null ? "" : request.getParameter("send_time2");
            SendedMessageDao smd = SendedMessageDao.getInstance();       
            List<Map<String, Object>> list = smd.queryMessage(phone,send_time1,send_time2,pageNum,pageSize);
      		int count = smd.getCountOfFeature(phone,send_time1,send_time2);
    		// 分页对象
    		PageData pg = null;
    		pg = CommonUtil.fomateResult(list, pageNum, pageSize, count);
    		writeJson(response, CommonUtil.formatFGMap(pg));
            return null;
        } 
        else
        {
        	return "smslist";
        }     
    }
	
	/**
	 * 查询已发送邮件
	 * @return
	 */	
	public String querySendedEmail() throws Exception
    {       
        String operation = request.getParameter("operation");
        if("init".equals(operation))
        {
            // 检索参数
            int pageNum = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagenum"), "1"));
            int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "15"));
            String sender = request.getParameter("sender") == null ? "" : request.getParameter("sender");
            String receive = request.getParameter("receive") == null ? "" : request.getParameter("receive");
            String state = request.getParameter("state") == null ? "" : request.getParameter("state");
            int dateType = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("dateType"), "0"));
            String preDate = request.getParameter("preDate") == null ? "" : request.getParameter("preDate");
            String postDate = request.getParameter("postDate") == null ? "" : request.getParameter("postDate");
            String sortName = CommonUtil.nullToDefault(request.getParameter("sortname"), "");
            String sortOrder = CommonUtil.nullToDefault(request.getParameter("sortorder"), "");
            SendedMessageDao smd = SendedMessageDao.getInstance();       
            List<Map<String, Object>> list = smd.queryEmail(sender,receive,state,dateType,preDate,postDate,pageNum,pageSize,sortName,sortOrder);
            int count = smd.getCountOfEmail(sender,receive,state,dateType,preDate,postDate);
    		// 分页对象
    		PageData pg = null;
    		pg = CommonUtil.fomateResult(list, pageNum, pageSize, count);
    		writeJson(response, CommonUtil.formatFGMap(pg));
            return null;
        } 
        else
        {
        	return "emaillist";
        }     
    }
    
    /**
     * 导出一定条件之下的数据
     * @throws IOException 
     * 
     */
    public void exportJobs() throws IOException {

        request.setCharacterEncoding("utf-8");
        JSONArray columns = JSONArray.fromObject(request.getParameter("columns"));
        // 检索参数
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("phone", request.getParameter("phone"));
        param.put("send_time1", request.getParameter("send_time1"));
        param.put("send_time2", request.getParameter("send_time2"));
        SendedMessageDao smd = SendedMessageDao.getInstance();     
        JSONArray items = formatData(smd.getExportDara(param));
        
        OutputStream out = response.getOutputStream();
        // 清空输出流
        response.reset();
        // 设置响应头和下载保存的文件名
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        response.setHeader(
                "content-disposition",
                "attachment;filename=" + StringHelper.processFileName(request, "已发送短信-"+ df.format(new Date()) + ".xls"));
        // 定义输出类型
        response.setContentType("APPLICATION/msexcel");

        ExcelModel downExcel = new ExcelModel();
        downExcel.setSheetName("已发送短信数据");
        ArrayList<String> headerList = new ArrayList<String>();
        for (int i = 0; i < columns.size(); i++) {
            String key = columns.getString(i);
            headerList.add(ExportContent.getColName(key));
        }
        downExcel.setHeader(headerList);
        ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < items.size(); i++) {
            JSONObject row = items.getJSONObject(i);
            ArrayList<String> dataline = new ArrayList<String>();
            for (int j = 0; j < columns.size(); j++) {
                String key = columns.getString(j);
                if (row.containsKey(key)) {
                    dataline.add(CommonUtil.nullToDefault(row.get(key), ""));
                } else {
                    dataline.add("");
                }
            }
            dataList.add(dataline);
        }

        downExcel.setData(dataList);
        ExcelOperator op = new ExcelOperator();
        // out:传入的输出流
        try {
            op.WriteExcel(downExcel, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.close();
        response.flushBuffer();// 强行将响应缓存中的内容发送到目的地
    }
    
    /**
     * 格式化需要导出的数据.
     * 
     * @param items
     *            被格式化数据
     * @return 格式化好的数据
     */
    private JSONArray formatData(List<Map<String, Object>> list) {

        JSONArray rows = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> item = list.get(i);
            JSONObject row = new JSONObject();
            // 编号
            row.put("id", i + 1);
            // 系统名字
            row.put("sys_name", item.get("SYS_NAME"));
            // 手机号码
            row.put("phone", item.get("PHONE"));
            // 发送内容
            row.put("content", item.get("CONTENT"));
            // 发送时间
            row.put("send_time",item.get("SEND_TIME"));
            // 返回状态
            row.put("returnstatus", item.get("RETURNSTATUS"));
            // 状态描述
            row.put("message", item.get("MESSAGE"));
            rows.add(row);
        }
        return rows;
    }
    
    /**
     * 删除短信
     * @throws Exception
     */
    public void deleteSms() throws Exception
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return="";
        long id = Long.parseLong(CommonUtil.nullToDefault(request.getParameter("id"), "0"));
        SendedMessageDao smd = SendedMessageDao.getInstance();
            
        if(smd.deleteSms(id))
        {
          res_return= "{msg:\"ok\"}";
        }
        else
        {
            res_return="{msg:\"删除短信失败，数据库失败\"}";
        }
        PrintWriter out=null;
        try {
            out=response.getWriter();
            out.write(res_return);
            out.flush();
        } catch (IOException e) 
        {
            e.printStackTrace();
        }finally{
            out.close();
        }
    }
    
    /**
     * 删除邮件
     * @throws Exception
     */
    public void deleteEmail() throws Exception
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return="";
        JSONArray ids = JSONArray.fromObject(request.getParameter("ids"));
        SendedMessageDao smd = SendedMessageDao.getInstance();
        if(smd.deleteEmail(ids)){ 
        	res_return= "{msg:\"ok\"}";
        }else{
            res_return="{msg:\"删除邮件失败，数据库失败\"}";
        }
        PrintWriter out=null;
        try {
            out=response.getWriter();
            out.write(res_return);
            out.flush();
        } catch (IOException e) 
        {
            e.printStackTrace();
        }finally{
            out.close();
        }
    }
    
    /**
     * 导出邮件
     * @throws IOException 
     */
    public void exportEmail() throws IOException {
        request.setCharacterEncoding("utf-8");
        JSONArray columns = JSONArray.fromObject(request.getParameter("columns"));
        // 检索参数
        String sender = request.getParameter("sender") == null ? "" : request.getParameter("sender");
        String receive = request.getParameter("receive") == null ? "" : request.getParameter("receive");
        String state = request.getParameter("state") == null ? "" : request.getParameter("state");
        int dateType = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("dateType"), "0"));
        String preDate = request.getParameter("preDate") == null ? "" : request.getParameter("preDate");
        String postDate = request.getParameter("postDate") == null ? "" : request.getParameter("postDate");
        SendedMessageDao smd = SendedMessageDao.getInstance();       
        List<Map<String, Object>> list = smd.exportEmail(sender,receive,state,dateType,preDate,postDate);
        OutputStream out = response.getOutputStream();
        // 清空输出流
        response.reset();
        // 设置响应头和下载保存的文件名
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        response.setHeader(
                "content-disposition",
                "attachment;filename=" + StringHelper.processFileName(request, "已发送邮件-"+ df.format(new Date()) + ".xls"));
        // 定义输出类型
        response.setContentType("APPLICATION/msexcel");

        ExcelModel downExcel = new ExcelModel();
        downExcel.setSheetName("已发送邮件数据");
        ArrayList<String> headerList = new ArrayList<String>();
        for (int i = 0; i < columns.size(); i++) {
            String key = columns.getString(i);
            headerList.add(ExportContent.getEmailHead(key));
        }
        downExcel.setHeader(headerList);
        ArrayList<ArrayList<Object>> dataList = new ArrayList<ArrayList<Object>>();
        for (int i = 0,len=list.size(); i < len; i++) 
        {
        	Map<String, Object> row = list.get(i);
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

