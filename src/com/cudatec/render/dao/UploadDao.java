package com.cudatec.render.dao;
import java.util.Map;
import com.cudatec.render.util.PathUtil;
import com.zqkj.bsm.dal.dboper.DbOperator;

/**
 * 处理客户端文件上传
 */
public class UploadDao extends DbOperator {
	
	private static volatile UploadDao instance = null;

	private UploadDao() {}

	public static UploadDao getInstance() {
		if (instance == null) {
			synchronized (UploadDao.class) {
				instance = new UploadDao();
			}
		}
		return instance;
	}
	
	
	
	public String queryTransId(String userID, String pid) 
	{
		String sql = "select `name` from storage_directory where uid=? and did=? ";
		Object[] object = new Object[2];
		object[0] = userID;
		object[1] = pid;
		Map<String, Object> map = this.getHashMap_Pre(sql, object);
		return map==null?"":String.valueOf(map.get("NAME"));
	}
	
	
	/**
	 * 更新哈希映射
	 * @param sha1 文件的哈希值
	 * @param size 文件大小
	 * @param type 文件类型
	 */
	public void updateMapping(String sha1, long size, String type) 
	{
	    String sql = null;
		if (CheckDao.getInstance().check(sha1)) 
		{
			// 哈希记录已经存在，把引用数增加1
			sql = "UPDATE `storage_mapping` SET `reference` = `reference` + 1 WHERE `hash` = ?";
			Object[] object = new Object[1];
			object[0] = sha1;
			this.executeInsert(sql, object);
		} else 
		{
			// 哈希记录不存在，插入哈希记录
			sql = "INSERT INTO `storage_mapping`(`hash`,`size`,`type`) VALUES(?,?,?)";
			Object[] object = new Object[3];
			object[0] = sha1;
			object[1] = size;
			object[2] = type;
			this.executeInsert(sql, object);
		}
	}
	
	/**
	 * 插入文件上传的记录，不管文件是否已经在服务器中存在
	 * 只要客户上传一个文件，就会在 storage_file 表中添加一条记录
	 * @param uid 用户ID
	 * @param pid 父文件ID
	 * @param sha1 文件哈希值
	 * @param name 文件名称
	 * @param absolutepath 文件在客户端的绝对路径
	 * @param cachepath 文件在客户端的缓存路径
	 */
	public void insertRecord(String uid, String pid, String sha1, String name, String absolutepath, String option,long size) {
		String trans_id = null;
		String targetpath = null;			// 文件在分布式存储的目标路径
		absolutepath = absolutepath.replace('\\', '/').toLowerCase();
		String getTransName = "select `name` from storage_directory where uid=? and did=? limit 1";
		Object[] object = new Object[2];
		object[0] = uid;
		object[1] = pid;
		Map<String, Object> map =this.getHashMap_Pre(getTransName, object);
//		String getMaxfid = "SELECT MAX(`fid`) as FID FROM `storage_file` WHERE `uid`="+uid;
		String sql = "INSERT INTO `storage_file`(`uid`,`pid`,`hash`,`name`,absolutepath,targetpath,size) VALUES(?,?,?,?,?,?,?)";
//		long id = 1;
		if (map!=null && map.size()>0) 
		{
			trans_id = String.valueOf(map.get("NAME"));
			targetpath = PathUtil.getTargetPath(uid, trans_id, absolutepath);
			if ("1".equals(option)) {								// 如果 option 为1，则修改 targetpath
				String sqlGetPath = "select input_path from storage_directory where uid=? and did=? limit 1";
				Object[] object_getpath = new Object[2];
				object_getpath[0] = uid;
				object_getpath[1] = pid;
				Map<String, Object> map_getpath = this.getHashMap_Pre(sqlGetPath, object_getpath);
				if (map_getpath != null && map_getpath.size() > 0) 
				{
					String renderPath = (String)map_getpath.get("INPUT_PATH");
					if (renderPath != null) {
						targetpath = PathUtil.getParentPath(renderPath) + PathUtil.getFileName(absolutepath);
					}
				}
				
//				Connection conn = DbUtil.getConnection();
//				PreparedStatement stmt = null;
//				ResultSet rs = null;
//				try {
//					stmt = conn.prepareStatement(sqlGetPath);
//					stmt.setString(1, uid);
//					stmt.setString(2, pid);
//					rs = stmt.executeQuery();
//					if (rs.next()) {
//						String renderPath = rs.getString(1);		// 把当前文件放在和渲染文件放在同个文件夹中
//						if (renderPath != null) {
//							targetpath = PathUtil.getParentPath(renderPath) + PathUtil.getFileName(absolutepath);
//						}
//					}
//					rs.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				} finally {
//					DbUtil.closeStatement(stmt);
//					DbUtil.closeConnection(conn);
//				}
			}
		}
		Object[] object_insert = new Object[7];
		object_insert[0] = uid;
		object_insert[1] = pid;
		object_insert[2] = sha1;
		object_insert[3] = name;
		object_insert[4] = absolutepath;
		object_insert[5] = targetpath;
		object_insert[6] = size;
		this.executeInsert(sql, object_insert);
	}
	
	public void updateUsage(String uid, long usage) 
	{
		String sql = "update storage_userinfo set `usage`=`usage`+? where uid=?";
		Object[] object = new Object[2];
		object[0] = usage;
		object[1] = uid;
		this.executeUpdate(sql, object);
	}
}
