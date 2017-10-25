package com.zqkj.bsm.zcgl.dao;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import com.zqkj.bsm.dal.dboper.DbOperator;
import com.zqkj.bsm.system.action.page.OraPaginatedList;
import com.zqkj.bsm.system.action.util.StringHelper;
import com.zqkj.bsm.util.BHUtil;

import common.Logger;

/**
 * 资产管理DAO
 * @author xj
 *
 */
public class AssetApplyDao extends DbOperator{
	private Logger log = Logger.getLogger(AssetApplyDao.class);
	private static AssetApplyDao instance = null;
	
	private AssetApplyDao(){
		
	}
	
	public static AssetApplyDao getInstance(){
		if(instance == null){
			synchronized (AssetApplyDao.class) {
				if(instance == null)
					instance = new AssetApplyDao();
			}
		}
		return instance;
	}
	
	/**
	 * 根据Id修改资产状态
	 * @param id
	 * @param status
	 * @return
	 */
	public boolean updateUserAndStatusById(String user,String status,String id)
	{
		String sql="update zcgl_assetdetailinfo set status='"+status+"',user='"+user+"' where id='"+id+"'";
		return this.executeUpdate(sql);
	}
	
	/**
	 * 根据Id获取资产详细信息
	 * @param Id
	 * @return
	 */
	public List<Map<String, Object>> getAssetDetailInfoById(String Id) 
	{
//		String condition=this.getDesSql(map);
		
		String sql = "select a.assetname, b.* from zcgl_assetinfo a,zcgl_assetdetailinfo b"
                +" where a.id=b.code and b.id='"+Id+"' ";
        return this.getList_Pre(sql);
	}
	
	
	public OraPaginatedList getMyAssetList(int pageNum,int pageSize,String user) 
	{
//		String condition=this.getDesSql(map);
		
		String sql = "select a.assetname,b.* from zcgl_assetinfo a,zcgl_assetdetailinfo b "
				+ "where a.id=b.code and b.user ='"+user+"' and b.status='"+3+"' ";
		OraPaginatedList list = new OraPaginatedList(sql);
       list.setCurrentPage(pageNum);
       list.setObjectsPerPage(pageSize);
       return list;
	}
	


	
	
	 
		
		
	
}
