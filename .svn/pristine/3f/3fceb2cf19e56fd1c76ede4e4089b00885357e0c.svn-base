package com.zqkj.bsm.zcgl.dao;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import com.zqkj.bsm.client.dao.ClientDao;
import com.zqkj.bsm.dal.dboper.DbOperator;
import com.zqkj.bsm.system.action.page.OraPaginatedList;
import com.zqkj.bsm.system.action.util.StringHelper;

public class AssetInfoDao extends DbOperator{
	
	private static AssetInfoDao instance = null;
	public static AssetInfoDao getInstance()
	{
		if(instance==null)
		{
			synchronized (ClientDao.class)
			{
			  instance = new AssetInfoDao();
			}
		}
		return instance;
	}
	
	/**
	 * 数据库字段和导出标题栏的对应关系
	 * 
	 */
	public  Map<String, String> getList_Column()
	{
		StringBuilder sb=new StringBuilder();
		sb.append("SELECT ");
		sb.append(" assetname as '资产名称',");
		sb.append(" id as '编号',");
		sb.append(" place as '存放地点',");
		sb.append(" assettype as '资产类型',");
		sb.append(" remark as '备注'");
		sb.append(" FROM zcgl_assetinfo");
		return this.getList_Column(sb.toString());
	}
	
	/**
	 * 数据导出不分页，但是超过一定量会有性能问题
	 * @param map
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	 @SuppressWarnings("deprecation")
	public List<Map<String, Object>> getExportDara(Map<String, Object> map) throws UnsupportedEncodingException 
	 {
			String condition=this.getDesSql(map);
			String conditionP = "";
			String param = "";
			if(map!= null && !StringHelper.noEmpty2(map.get("assetname")).trim().equals(""))
			{
				param += " and a.assetname like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("assetname")).trim()) + ",";
			}
			if(map!= null && !StringHelper.noEmpty2(map.get("id")).trim().equals(""))
			{
				param += " and a.id like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("id")).trim()) + ",";
			}
			if(map!= null && !StringHelper.noEmpty2(map.get("place")).trim().equals(""))
			{
				param += " and a.place like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("place")).trim()) + ",";
			}
			String sql = "select a.* from zcgl_assetinfo a  "
	                +" where 1=1  "+param + condition;
			Object[] oArray_count = null;
			if (conditionP != null && !"".equals(conditionP)) 
			{
				conditionP = conditionP.substring(0, conditionP.length() - 1);
				oArray_count = conditionP.split(",");
			}	
			return this.getList_Pre(sql, oArray_count);
		 
	 }
	
	/**
	 * 按条件查询获取建议书列表
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public OraPaginatedList queryAssetInfo(Map<String, Object> map,int pageNum,int pageSize) 
	{
		String condition=this.getDesSql(map);
		String conditionP = "";
		String param = "";
		if(map!= null && !StringHelper.noEmpty2(map.get("assetname")).trim().equals(""))
		{
			param += " and a.assetname like concat('%',?,'%') ";
			conditionP += StringHelper.noEmpty2(map.get("assetname")).trim() + ",";
		}
		if(map!= null && !StringHelper.noEmpty2(map.get("id")).trim().equals(""))
		{
			param += " and a.id like concat('%',?,'%') ";
			conditionP += StringHelper.noEmpty2(map.get("id")).trim() + ",";
		}
		if(map!= null && !StringHelper.noEmpty2(map.get("place")).trim().equals(""))
		{
			param += " and a.place like concat('%',?,'%') ";
			conditionP += StringHelper.noEmpty2(map.get("place")).trim() + ",";
		}
		String sql = "select a.* from zcgl_assetinfo a  "
                +" where 1=1  "+param + condition;
		Object[] oArray_count = null;
		if (conditionP != null && !"".equals(conditionP)) 
		{
			conditionP = conditionP.substring(0, conditionP.length() - 1);
			oArray_count = conditionP.split(",");
		}		
		OraPaginatedList list = new OraPaginatedList(sql,oArray_count);
        list.setCurrentPage(pageNum);
        list.setObjectsPerPage(pageSize);
        return list;
	}

	/**
	 * 保存资产信息
	 * @param map
	 * @param uid
	 * @return
	 * sb.append(" assetname as '资产名称',");
		sb.append(" code as '编号',");
		sb.append(" place as '存放地点',");
		sb.append(" remark as '备注'");
		sb.append(" FROM zcgl_assetinfo");
	 */
	public boolean saveAssetInfo(Map<String, Object> map,String uid)
	{
		StringBuilder sb=new StringBuilder();
		sb.append("insert into zcgl_assetinfo(assetname,place,assettype,remark) values(?,?,?,?) ");
		Object[] object = new Object[4];
		object[0] = StringHelper.noEmpty2(map.get("assetname")).trim();
		object[1] = StringHelper.noEmpty2(map.get("place")).trim();
		object[2] = StringHelper.noEmpty2(map.get("assettype")).trim();
		object[3] = StringHelper.noEmpty2(map.get("remark")).trim();
		return this.executeUpdate(sb.toString(), object);
		
	}

	/**
	 * 更新资产信息
	 * @param map
	 * @return
	 */
	public boolean updateAssetInfo(Map<String, Object> map,String uid)
	{

		StringBuilder sb=new StringBuilder();
		sb.append("update zcgl_assetinfo set assetname=?,place=?,assettype=?,remark=? where id =? ");
		Object[] object = new Object[5];
		object[0] = StringHelper.noEmpty2(map.get("assetname")).trim();
		object[1] = StringHelper.noEmpty2(map.get("place")).trim();
		object[2] = StringHelper.noEmpty2(map.get("assettype")).trim();
		object[3] = StringHelper.noEmpty2(map.get("remark")).trim();
		object[4] = StringHelper.noEmpty2(map.get("id")).trim();
		return this.executeUpdate(sb.toString(), object);
		
	}
	/**
	 * 删除资产信息
	 * @param id
	 * @return
	 */
	public boolean deleteAssetInfo(String id)
	{	
		StringBuilder sb=new StringBuilder();
		sb.append("delete from zcgl_assetinfo where id =? ");
		return this.executeUpdate(sb.toString(), id);
	}
	
	
	
	
}
