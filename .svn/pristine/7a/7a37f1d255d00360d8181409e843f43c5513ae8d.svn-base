package com.cudatec.render.dao;
import java.util.Map;

import com.zqkj.bsm.dal.dboper.DbOperator;

/**
 * 根据客户端传来的哈希值，检查是否已有相同的文件在服务器
 */
public class CheckDao extends DbOperator{
	
	private static volatile CheckDao instance = null;
	
	private CheckDao() {}
	
	public static CheckDao getInstance() {
		if(instance == null) {
			synchronized (CheckDao.class) {
				instance = new CheckDao();
			}
		}
		return instance;
	}
	
	/**
	 * 根据哈希值，判断文件是否存在
	 * @param sha1 SHA-1计算得到的40个字符（20个字节）值
	 * @return 是否存在对应的哈希记录，如果有记录，则表示服务器有这个文件
	 */
	public boolean check(String sha1)  {
		String sql = "select reference from storage_mapping where hash=? limit 1";
		Object[] object = new Object[1];
		object[0] = sha1;
		boolean exist = false;
		Map<String, Object> map = this.getHashMap_Pre(sql, object);
		if(map != null && map.size() > 0) {
			exist = true;
		}
		return exist;
	}
}
