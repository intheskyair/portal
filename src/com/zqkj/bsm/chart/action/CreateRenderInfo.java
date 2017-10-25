package com.zqkj.bsm.chart.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.chart.dao.CreateRenderInfoDao;
import com.zqkj.bsm.util.CommonUtil;
import com.zqkj.bsm.util.PageData;

public class CreateRenderInfo extends BaseAction{

	/**
	 * 生成渲染数据
	 */
	private static final long serialVersionUID = 1L;
	
	public String init(){
		return "initPage";
	}
	/**
	 * 获得渲染数据
	 * @return
	 */
	public String renderInfoList() throws Exception{
		int pageNum = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagenum"), "1"));
        int pageSize = Integer.parseInt(CommonUtil.nullToDefault(request.getParameter("pagesize"), "15"));
        CreateRenderInfoDao crld = CreateRenderInfoDao.getInstance();
        //按用户汇总
        List<Map<String, Object>> list = crld.queryRenderInfo(pageNum, pageSize);
        int count = crld.queryRenderInfoCount();
        // 分页对象
        PageData pg = null;
		pg = CommonUtil.fomateResult(list, pageNum, pageSize, count);
		writeJson(response, CommonUtil.formatFGMap(pg));
        return null;
	}
	
	/**
	 * 生成渲染数据
	 */
	public void createRenderInfo() throws Exception{
		  String res_return="";
		 String date = CommonUtil.nullToDefault(request.getParameter("date"), "");
		 CreateRenderInfoDao crld = CreateRenderInfoDao.getInstance();
		 if(crld.createRenderInfo(date)){
			 res_return= "{msg:\"ok\"}";
		 }else
	    	{
	    		res_return="{msg:\"生成渲染数据出错，数据库更新失败\"}";
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
