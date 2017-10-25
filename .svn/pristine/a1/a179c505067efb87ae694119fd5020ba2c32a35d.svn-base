package com.zqkj.bsm.chart.dao;

import java.util.List;
import java.util.Map;

import com.zqkj.bsm.dal.dboper.DbOperator;

public class Peak_countInfoDao extends DbOperator
{
	
	private static volatile Peak_countInfoDao instance = null;
	
	private Peak_countInfoDao()
	{
		
	}
	public static Peak_countInfoDao getInstance()
	{
		if(instance==null)
		{
			synchronized (Peak_countInfoDao.class)
			{
			instance = new Peak_countInfoDao();
			}
		}
		return instance;
	}
	public List<Map<String, Object>> queryPeakCount(String send_time1,String send_time2, int cycle,int peak_type) {
		String request_time1 = getTimeRequest(cycle,"pd.time");
		String request_time2 = getTimeRequest(cycle,"pda.time");
		String peakType = "";
		if(peak_type == 1){
			peakType = "max_online_num";
		}else if(peak_type == 2){
			peakType = "max_mission_submit";
		}else if(peak_type == 3){
			peakType = "max_mission_run";
		}
		String sql = "SELECT A.time,A.peak_num,pda."+peakType+"_time as peak_num_time FROM peakcount_day pda LEFT JOIN ("
				+ "SELECT "+request_time1+" as time,max(pd."+peakType+") AS peak_num FROM peakcount_day pd "
				+ "WHERE pd.time >='"+send_time1+"' and pd.time <='"+send_time2+"' GROUP BY "+ request_time1 +")A ON"
				+ "  pda."+peakType+" = A.peak_num and  "+request_time2+" =A.time where A.time is NOT NULL GROUP BY A.time";
		log.info("queryPeakCount="+sql);
		List<Map<String, Object>> list = getList_Pre(sql.toString(),null);
		return list;
	}
	public String getTimeRequest( int cycle ,String statisticalTime){
		String request_time = "";
		if(cycle==1){//按天
			request_time = "date_format("+statisticalTime+", '%Y-%m-%d')";
		}else if(cycle==2){//按周
			request_time = "date_format("+statisticalTime+", '%Y-%u')";
		}else if(cycle==3){//按月
			request_time = "date_format("+statisticalTime+", '%Y-%m')";
		}
		return request_time;
	}
}