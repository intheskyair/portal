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
public class AssetManagerDao extends DbOperator{
	private Logger log = Logger.getLogger(AssetManagerDao.class);
	private static AssetManagerDao instance = null;
	
	private AssetManagerDao(){
		
	}
	
	public static AssetManagerDao getInstance(){
		if(instance == null){
			synchronized (AssetManagerDao.class) {
				if(instance == null)
					instance = new AssetManagerDao();
			}
		}
		return instance;
	}
	/**
	 * 根据资产编号和品牌获取资产详情
	 * @param code
	 * @param brand
	 * @return
	 */
	public OraPaginatedList getAssetDetailInfoByCodeAndBrand(int pageNum,int pageSize,String code,String brand) 
	{
//		String condition=this.getDesSql(map);
		
		String sql = "select a.assetname, b.* from zcgl_assetinfo a,zcgl_assetdetailinfo b"
                +" where a.id=b.code and b.code='"+code+"' and b.brand='"+brand+"'";
		OraPaginatedList list = new OraPaginatedList(sql);
       list.setCurrentPage(pageNum);
       list.setObjectsPerPage(pageSize);
       return list;
	}
	
	public List<Map<String, Object>> getAssetDetailInfoByorderId(String orderId) 
	{
//		String condition=this.getDesSql(map);
		
		String sql = "select a.assetname, b.* from zcgl_assetinfo a,zcgl_assetdetailinfo b"
                +" where a.id=b.code and  b.orderid='"+orderId+"'";
		
       
        return this.getList_Pre(sql);
	}
	
	
	/**
	 * 根据orderId和状态获取资产详情
	 * @param orderId
	 * @param status
	 * @return
	 */
	public List<Map<String, Object>> getEveryAssetDetailInfo(String orderId,String status) 
	{
//		String condition=this.getDesSql(map);
		
		String sql = "select a.assetname, b.* from zcgl_assetinfo a,zcgl_assetdetailinfo b"
                +" where a.id=b.code and b.status='"+status+"' and b.orderid='"+orderId+"'";
		
       
        return this.getList_Pre(sql);
	}
	
	
	/**
	 * 根据orderId和状态更新资产状态
	 * @param orderId
	 * @param status
	 * @return
	 */
	public boolean updateStatus(String orderId,String status)
	{
		String sql="update zcgl_assetdetailinfo set status=? where orderid=?";
		Object[] obj=new Object[2];
		obj[0]=StringHelper.noEmpty2(status).trim();
		obj[1]=StringHelper.noEmpty2(orderId).trim();
		return this.executeUpdate(sql, obj);
		
	}
	
	/**
	 * 根据状态删除资产详情
	 * @param orderId
	 * @return
	 */
	public boolean deleteAssetByStatus(String orderId)
	{	
		
		String sql="delete from zcgl_assetdetailinfo where orderid =? and status=\"0\" ";
		return this.executeUpdate(sql,orderId);
	}

	
	/**
	 * 根据编号获得资产名称
	 * @param code
	 * @return
	 */
	public List<Map<String, Object>> getAssetNameByCode(String code)
	{
		String sql = "select assetname from zcgl_assetinfo where id='" + code+"'";
		return this.getList_Pre(sql);
	}
	
	/**
	 * 根据分类获取资产名称
	 * @return
	 */
	public List<Map<String, Object>> getAssetNameByType(String assettype)
	{
		String sql = "select assetname,id from zcgl_assetinfo where assettype='" + assettype+"'";
		return this.getList_Pre(sql);
	}
	/**
	 * 根据名称获取资产编号
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public List<Map<String, Object>> getAssetCodeByAssetName(String assetname) throws UnsupportedEncodingException
	{
		String sql = "select id from zcgl_assetinfo where assetname='" + URLDecoder.decode(assetname.trim(),"UTF-8")+"'";
		return this.getList_Pre(sql);
	}


/**
 * 获取资产总览列表
 * @param map
 * @param pageNum
 * @param pageSize
 * @return
 */
	public OraPaginatedList getAssetOverView(Map<String, Object> map,int pageNum,int pageSize) 
	{
//		String condition=this.getDesSql(map);
		String conditionP = "";
		String param = "";
		if(map!= null && !StringHelper.noEmpty2(map.get("assetname")).trim().equals(""))
		{
			param += " and a.assetname like concat('%',?,'%') ";
			conditionP += StringHelper.noEmpty2(map.get("assetname")).trim() + ",";
		}
		if(map!= null && !StringHelper.noEmpty2(map.get("brand")).trim().equals(""))
		{
			param += " and b.brand like concat('%',?,'%') ";
			conditionP += StringHelper.noEmpty2(map.get("brand")).trim() + ",";
		}
		String sql = "select a.assetname,a.id,b.brand,count(b.brand)as total from zcgl_assetinfo a,zcgl_assetdetailinfo b"
                +" where a.id=b.code and b.status in('"+1+"','"+2+"','"+3+"') "+param +"group by b.code,b.brand";
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
	 * 数据库字段和导出标题栏的对应关系
	 * 
	 */
	public  Map<String, String> getList_Column()
	{
		StringBuilder sb=new StringBuilder();
		sb.append("SELECT ");
		sb.append(" a.assetname as '资产名称',");
		sb.append(" a.id as '编号',");
		sb.append(" b.brand as '品牌',");
		sb.append(" count(b.brand) as '数量'");
		sb.append(" FROM zcgl_assetinfo a,zcgl_assetdetailinfo b");
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
//			String condition=this.getDesSql(map);
			String conditionP = "";
			String param = "";
			if(map!= null && !StringHelper.noEmpty2(map.get("assetname")).trim().equals(""))
			{
				param += " and a.assetname like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("assetname")).trim()) + ",";
			}
			
			if(map!= null && !StringHelper.noEmpty2(map.get("brand")).trim().equals(""))
			{
				param += " and b.brand like concat('%',?,'%') ";
				conditionP += URLDecoder.decode(StringHelper.noEmpty2(map.get("brand")).trim()) + ",";
			}
			String sql = "select a.assetname,a.id,b.brand,count(b.brand) total from zcgl_assetinfo a,zcgl_assetdetailinfo b "
	                +"where a.id=b.code and b.status='"+1+"' "+param +"group by b.code,b.brand";
			Object[] oArray_count = null;
			if (conditionP != null && !"".equals(conditionP)) 
			{
				conditionP = conditionP.substring(0, conditionP.length() - 1);
				oArray_count = conditionP.split(",");
			}	
			return this.getList_Pre(sql, oArray_count);
		 
	 }
	 
	 //往资产详细信息表添加数据
		public boolean addAssetDetailInfo(Map<String, Object> map)
		{
			
			StringBuilder sb=new StringBuilder();
			String head="";
			switch (map.get("asset_type").toString().trim()) {
			case "1":
				head="FWJGZW";
				break;
			case "2":
				head="ZYSB";
				break;
			case "3":
				head="TYSB";
				break;
			case "4":
				head="WWHCLP";
				break;
			case "5":
				head="TSDA";
				break;
			case "6":
				head="JJYJ";
				break;
			}
			Object[] obj=new Object[12];
			head=head+BHUtil.getAssetID(head);
			obj[0]=StringHelper.noEmpty2(head).trim();
			obj[1]=StringHelper.noEmpty2(map.get("asset_code")).trim();
			obj[2]=StringHelper.noEmpty2(map.get("asset_type")).trim();
			obj[3]=StringHelper.noEmpty2("1").trim();
			obj[4]=StringHelper.noEmpty2(map.get("asset_price")).trim();
			obj[5]=StringHelper.noEmpty2(map.get("asset_purchasetime")).trim();
			obj[6]=StringHelper.noEmpty2(map.get("asset_purchaser")).trim();
			obj[7]=StringHelper.noEmpty2(map.get("asset_brand")).trim();
			obj[8]=StringHelper.noEmpty2("null").trim();
			obj[9]=StringHelper.noEmpty2(map.get("asset_remark")).trim();
			obj[10]=StringHelper.noEmpty2(map.get("orderId")).trim();
			obj[11]=StringHelper.noEmpty2(map.get("status")).trim();
			sb.append("insert into zcgl_assetdetailinfo(id,code,assettype,total,price,purchasetime,purchaser,brand,user,remark,orderId,status) values(?,?,?,?,?,?,?,?,?,?,?,?)");
			return this.executeUpdate(sb.toString(), obj);
			
		}
		
		
	
}
