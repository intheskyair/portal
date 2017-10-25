package com.zqkj.bsm.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zqkj.bsm.dal.dboper.DbOperator;

import common.Logger;

public class TableHelpUtil extends DbOperator{
	private Logger log = Logger.getLogger(TableHelpUtil.class);
	private static TableHelpUtil instance = null;
	
	private TableHelpUtil(){
		
	}
	
	public static TableHelpUtil getInstance(){
		if(instance == null){
			synchronized (TableHelpUtil.class) {
				if(instance == null)
					instance = new TableHelpUtil();
			}
		}
		return instance;
	}
	
	public List<Map<String,Object>> getMysqlTableColumnInfo(String tableName){
		StringBuffer sb = new StringBuffer();
		sb.append("select * from information_schema.COLUMNS t where t.table_name='").append(tableName).append("'");
		return this.getList_Pre(sb.toString());
	}
	
	public boolean batchSqls(ArrayList<String> sqls){
		if(sqls.size() == 0){
			return true;
		}
		return this.executeBatch(sqls);
	}
}
