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
import com.zqkj.bsm.sms.dao.AddressBookDao;
import com.zqkj.bsm.sms.util.BookExport;
import com.zqkj.bsm.system.action.util.StringHelper;
import com.zqkj.bsm.util.CommonUtil;
import com.zqkj.bsm.util.ExcelModel;
import com.zqkj.bsm.util.ExcelOperator;
import com.zqkj.bsm.util.PageData;

public class AddressBookAction extends BaseAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 查询通讯录
	 * @return
	 */	
	public String queryAddressBookList() throws Exception
    {       
        String operation = request.getParameter("operation");
        if("init".equals(operation))
        {
            // 检索参数
            int pageNum = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagenum"), "1"));
            int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "15"));
            String phone = request.getParameter("phone") == null ? "" : request.getParameter("phone");
            String user_name = request.getParameter("user_name") == null ? "" : request.getParameter("user_name");
            AddressBookDao smd = AddressBookDao.getInstance();       
            List<Map<String, Object>> list = smd.queryBook(phone,user_name,pageNum,pageSize);
      		int count = smd.getCountOfFeature(phone,user_name);
    		// 分页对象
    		PageData pg = null;
    		pg = CommonUtil.fomateResult(list, pageNum, pageSize, count);
    		writeJson(response, CommonUtil.formatFGMap(pg));
            return null;
        } 
        else
        {
        	return "addressBooklist";
        }     
    }
	
	/**
	 * 更新通讯录
	 *@throws Exception 
     *  
	 */
    public void updateAddressBook() throws Exception
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return="";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", CommonUtil.nullToDefault(request.getParameter("id"), "0"));
        param.put("user_name", CommonUtil.nullToDefault(request.getParameter("user_name"), ""));
        param.put("phone", CommonUtil.nullToDefault(request.getParameter("phone"), ""));
        param.put("email", CommonUtil.nullToDefault(request.getParameter("email"), ""));
        String status =  CommonUtil.nullToDefault(request.getParameter("status"), "");
        if(status.equals("有效")){
        	status="0";
        }
        if(status.equals("无效")){
        	status="1";
        }
        param.put("status", status);
        AddressBookDao abd = AddressBookDao.getInstance();
    	if(abd.updateBook(param))
    	{
    		res_return= "{msg:\"ok\"}";
    	}
    	else
    	{
    		res_return="{msg:\"更新该通信录出错，数据库更新失败\"}";
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
	 * 新建通讯录
	 *@throws Exception 
     *  
	 */
    public void addAddressBook() throws Exception
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return="";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", CommonUtil.nullToDefault(request.getParameter("id"), "0"));
        param.put("user_name", CommonUtil.nullToDefault(request.getParameter("user_name"), ""));
        param.put("phone", CommonUtil.nullToDefault(request.getParameter("phone"), ""));
        param.put("email", CommonUtil.nullToDefault(request.getParameter("email"), ""));
        param.put("status", "0");
        AddressBookDao abd = AddressBookDao.getInstance();
    	if(abd.addBook(param))
    	{
    		res_return= "{msg:\"ok\"}";
    	}
    	else
    	{
    		res_return="{msg:\"新建该通信录出错，数据库更新失败\"}";
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
        param.put("user_name", request.getParameter("user_name"));
        AddressBookDao smd = AddressBookDao.getInstance();     
        JSONArray items = formatData(smd.getExportDara(param));

        OutputStream out = response.getOutputStream();
        // 清空输出流
        response.reset();
        // 设置响应头和下载保存的文件名
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        response.setHeader(
                "content-disposition",
                "attachment;filename=" + StringHelper.processFileName(request, "通讯录-"+ df.format(new Date()) + ".xls"));
        // 定义输出类型
        response.setContentType("APPLICATION/msexcel");

        ExcelModel downExcel = new ExcelModel();
        downExcel.setSheetName("通讯录数据");
        ArrayList<String> headerList = new ArrayList<String>();
        for (int i = 0; i < columns.size(); i++) {
            String key = columns.getString(i);
            headerList.add(BookExport.getColName(key));
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
            row.put("user_name", item.get("USER_NAME"));
            row.put("phone", item.get("PHONE"));
            row.put("email", item.get("EMAIL"));
            String value = CommonUtil.nullToDefault(item.get("STATUS"), "");
            String status = null;
            if ("0".equals(value)) {
                status = "有效";
            } else {
                status = "无效";
            } 
            row.put("status", status);
            rows.add(row);
        }
        return rows;
    }
    
    /**
     * 删除通讯录
     * @throws IOException 
     * 
     */
    public void deleteBook() throws Exception
    {
        response.setContentType("text/html; charset=UTF-8");
        String res_return="";
        long id = Long.parseLong(CommonUtil.nullToDefault(request.getParameter("id"), "0"));
        AddressBookDao smd =  AddressBookDao.getInstance();
            
        if(smd.deleteBook(id))
        {
          res_return= "{msg:\"ok\"}";
        }
        else
        {
            res_return="{msg:\"删除失败，数据库失败\"}";
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
}

