package com.zqkj.bsm.system.action.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JsonHelper {
	
	/**
	 * 将List<Map>对象转换成JSON格式的字符串
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String list2json(List list){
		if(list==null || !(list.size()>0)){
			return "{result:[],total:0}";
		}
		
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<list.size();i++){
			Map map=(Map)list.get(i);
			Iterator it=map.keySet().iterator();
			StringBuilder sb2=new StringBuilder();
			sb.append("{");
			while(it.hasNext()){
				Object tmp=it.next();
				String key=(tmp==null?"":tmp.toString());
				String value=(map.get(key)==null?"":map.get(key).toString()).replace("\\", "\\\\").replace("\"", "\\\"");
				sb2.append(key+":\""+value+"\",");
			}
			String tmpResult=sb2.toString();
			if(tmpResult.endsWith(",")){
				tmpResult=tmpResult.substring(0,tmpResult.length()-1);
			}
			sb.append(tmpResult);
			sb.append("},");
		}
		String tmpResult=sb.toString();
		if(tmpResult.endsWith(",")){
			tmpResult=tmpResult.substring(0,tmpResult.length()-1);
		}
		return "{result:["+tmpResult+"],total:"+list.size()+"}";
	}
	
	
	
	
}
