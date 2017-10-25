package com.cudatec.render.dao;

import java.util.Map;

import com.zqkj.bsm.dal.dboper.DbOperator;

/**
 * 文件下载的数据库访问类
 * 这个属于网盘的功能，在目前的客户端不需要调用这个接口
 */
public class DownloadDao extends DbOperator {
	private static volatile DownloadDao instance = null;
	
	private DownloadDao() {}
	
	public static DownloadDao getInstance() {
		if (instance == null) {
			synchronized (DownloadDao.class) {
				instance = new DownloadDao();
			}
		}
		return instance;
	}
	
	/**
	 * 根据指定的文件ID下载文件
	 * @param uid 用户ID
	 * @param fid 文件ID
	 * @return 文件的哈希值（由文件的哈希值，就可以确定在集中式存储中的路径）
	 */
	public String getHash(String uid, String fid) {
		String hash = null;
		String sql = "select `hash` from storage_file where uid=? and fid=? limit 1";
		Object[] object = new Object[2];
		object[0] = uid;
		object[1] = fid;
		Map<String, Object> map = this.getHashMap_Pre(sql, object);
		if(map != null && map.size() > 0) {
			hash = (String)map.get("HASH");
		}
		return hash;
	}
}
