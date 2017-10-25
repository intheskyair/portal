package com.zqkj.bsm.carmanager.dao;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zqkj.bsm.dal.dboper.DbOperator;
import com.zqkj.bsm.system.action.page.OraPaginatedList;
import com.zqkj.bsm.util.CommonUtil;

import common.Logger;

/**
 * 
 * @ClassName: CarManagerDao
 * @Description: TODO(车辆信息管理数据库交互)
 * @author wangwj
 * @date 2016年4月21日 下午2:07:44
 * 
 */
public class CarManagerDao extends DbOperator {
	private Logger log = Logger.getLogger(CarManagerDao.class);
	private static CarManagerDao instance = null;

	private CarManagerDao() {

	}

	public static CarManagerDao getInstance() {
		if (instance == null) {
			synchronized (CarManagerDao.class) {
				if (instance == null)
					instance = new CarManagerDao();
			}
		}
		return instance;
	}

	public OraPaginatedList queryCarInfo(Map<String, Object> map, int pageNum,
			int pageSize) {
		String condition = this.getDesSql(map);
		StringBuffer conditionP = new StringBuffer();
		StringBuffer sb = new StringBuffer();
		String status = CommonUtil.nullToDefault(map.get("status"), "").trim();
		String carno = CommonUtil.nullToDefault(map.get("carno"), "").trim();
		String orgid = CommonUtil.nullToDefault(map.get("orgid"), "").trim();
		sb.append("select * from t_car_info t where 1=1 ");
		if (!"".equals(status) && Integer.valueOf(status) != -1) {
			conditionP.append(" and t.car_status = '").append(status)
					.append("'");
		}
		if (!"".equals(carno)) {
			conditionP.append(" and t.car_no like '%").append(carno)
					.append("%'");
		}
		if (!"".equals(orgid)) {
			conditionP.append(" and t.org_name like '%").append(orgid)
					.append("%'");
		}
		Object[] oArray_count = null;
		/*
		 * if (conditionP != null && !"".equals(conditionP)) { conditionP =
		 * conditionP.substring(0, conditionP.length() - 1); oArray_count =
		 * conditionP.split(","); }
		 */
		sb.append(conditionP).append(" ").append(condition);
		OraPaginatedList list = new OraPaginatedList(sb.toString(),
				oArray_count);
		list.setSortCriterion(condition);
		list.setCurrentPage(pageNum);
		list.setObjectsPerPage(pageSize);
		return list;
	}

	public boolean saveCarInfo(Map<String, Object> params) {
		StringBuffer sb = new StringBuffer();
		sb.append("insert into t_car_info(car_no,car_attribute,car_category,car_seat_num,car_brand,car_type,car_status,org_name) values(");
		sb.append("'")
				.append(CommonUtil.nullToDefault(params.get("carno"), ""))
				.append("',");
		sb.append("'")
				.append(CommonUtil.nullToDefault(params.get("carAttr"), ""))
				.append("',");
		sb.append("'")
				.append(CommonUtil.nullToDefault(params.get("carCategory"), ""))
				.append("',");
		sb.append("'")
				.append(CommonUtil.nullToDefault(params.get("carSeatNum"), "0"))
				.append("',");
		sb.append("'")
				.append(CommonUtil.nullToDefault(params.get("carBrand"), ""))
				.append("',");
		sb.append("'")
				.append(CommonUtil.nullToDefault(params.get("carType"), ""))
				.append("',");
		sb.append("'")
				.append(CommonUtil.nullToDefault(params.get("carStatus"), "-1"))
				.append("',");

		sb.append("'")
				.append(CommonUtil.nullToDefault(params.get("departmentId"), ""))
				.append("')");
		log.debug(CarManagerDao.class + "--->saveCarInfo--->" + sb.toString());
		return this.executeUpdate(sb.toString());
	}

	public Map<String, Object> queryCarInfoById(String carId) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from t_car_info t where id= '").append(carId)
				.append("'");
		return this.getHashMap_Pre(sb.toString());
	}

	public List<Map<String, Object>> queryCarInfoByCarStatus() {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from t_car_info t where car_status= '1'");
		return this.getList_Pre(sb.toString());
	}

	public boolean updateCarInfo(Map<String, Object> params) {
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE t_car_info t SET ");
		sb.append(" t.car_no='")
				.append(CommonUtil.nullToDefault(params.get("carno"), ""))
				.append("',");
		sb.append(" t.car_attribute='")
				.append(CommonUtil.nullToDefault(params.get("carAttr"), ""))
				.append("',");
		sb.append(" t.car_category='")
				.append(CommonUtil.nullToDefault(params.get("carCategory"), ""))
				.append("',");
		sb.append(" t.car_seat_num='")
				.append(CommonUtil.nullToDefault(params.get("carSeatNum"), "0"))
				.append("',");
		sb.append(" t.car_brand='")
				.append(CommonUtil.nullToDefault(params.get("carBrand"), ""))
				.append("',");
		sb.append(" t.car_type='")
				.append(CommonUtil.nullToDefault(params.get("carType"), ""))
				.append("',");
		sb.append(" t.car_status='")
				.append(CommonUtil.nullToDefault(params.get("carStatus"), "-1"))
				.append("',");

		sb.append(" t.org_name='")
				.append(CommonUtil.nullToDefault(params.get("departmentId"), ""))
				.append("'");
		sb.append(" where t.id = '")
				.append(CommonUtil.nullToDefault(params.get("carid"), "-1"))
				.append("'");
		log.debug(CarManagerDao.class + "--->updateCarInfo--->" + sb.toString());
		return this.executeUpdate(sb.toString());
	}

	public boolean updateCarInfo(String carNo, String status) {
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE t_car_info t SET t.car_status='" + status
				+ "' where car_no ='" + carNo + "'");

		return this.executeUpdate(sb.toString());
	}

	public boolean deleteCarInfo(String carId) {
		ArrayList<String> sqls = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		sb.append("insert into t_car_info_his(car_no,car_attribute,car_category,car_seat_num,car_brand,car_type,car_status,org_name,recode_time) ");
		sb.append(
				"select car_no,car_attribute,car_category,car_seat_num,car_brand,car_type,car_status,org_name,now() from t_car_info where id = '")
				.append(carId).append("'");
		sqls.add(sb.toString());
		sb.setLength(0);
		sb.append("DELETE FROM t_car_info where id = '").append(carId)
				.append("'");
		sqls.add(sb.toString());
		return this.executeBatch(sqls);
	}

	public List<Map<String, Object>> queryCars(Map<String, Object> map)
			throws UnsupportedEncodingException {
		String condition = this.getDesSql(map);
		StringBuffer conditionP = new StringBuffer();
		StringBuffer sb = new StringBuffer();
		String status = CommonUtil.nullToDefault(map.get("status"), "").trim();
		String carno = CommonUtil.nullToDefault(map.get("carno"), "").trim();
		String orgid = URLDecoder.decode(
				(CommonUtil.nullToDefault(map.get("orgid"), "").trim()),
				"utf-8");
		sb.append("select t.*, CASE t.car_status WHEN '1' THEN '空闲' when '2' then '已出' when '3' then '维修' end AS 'status'  from t_car_info t where 1=1 ");
		if (!"".equals(status) && Integer.valueOf(status) != -1) {
			conditionP.append(" and t.car_status = '").append(status)
					.append("'");
		}
		if (!"".equals(carno)) {
			conditionP.append(" and t.car_no like '%").append(carno)
					.append("%'");
		}
		if (!"".equals(orgid)) {
			conditionP.append(" and t.org_name like '%").append(orgid)
					.append("%'");
		}
		/*
		 * if (conditionP != null && !"".equals(conditionP)) { conditionP =
		 * conditionP.substring(0, conditionP.length() - 1); oArray_count =
		 * conditionP.split(","); }
		 */
		sb.append(conditionP).append(" ").append(condition);
		return this.getList_Pre(sb.toString());
	}

	/**
	 * 数据库字段和导出标题栏的对应关系
	 */
	public  Map<String, String> getList_Column()
	{
		
			StringBuilder sb=new StringBuilder();
			sb.append("SELECT ");
			sb.append(" car_no as '车牌号',");
			sb.append(" car_attribute as '车辆属性',");
			sb.append(" car_category as '车辆类别',");
			sb.append(" car_seat_num as '座位数',");
			sb.append(" car_brand as '车辆品牌',");
			sb.append(" car_type as '车辆型号',");
			sb.append(" org_name as '所属部门',");
			sb.append(" status as '车辆状态'");
			
			sb.append(" FROM (select t.*, CASE t.car_status WHEN '1' THEN '空闲' when '2' then '已出' when '3' then '维修' end AS 'status'  from t_car_info t)c");
			return this.getList_Column(sb.toString());
		}
	
	public boolean judgeCarNo(String carNo,long id) {
		String sql = "select  * from t_car_info where car_no=? and id!=? and car_no is not null and car_no!=''";
		Object[] object = new Object[2];
		object[0] = carNo;
		object[1] = id;
		boolean exist =false;
		Map<String, Object> map = this.getHashMap_Pre(sql, object);
		if(map != null && map.size() > 0) {
			exist = true;
		}
		return exist;
	}
	
}
