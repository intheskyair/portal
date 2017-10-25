package com.zqkj.bsm.system.action.page;
import java.util.List;
import java.util.Map;
import com.zqkj.bsm.dal.dboper.DbOperator;
import com.zqkj.bsm.system.action.SysPara;

/**
 * display的外部分页实例
 * 
 * @author gaofeng
 * 
 */
public class OraPaginatedList extends DbOperator {

	private List<Map<String, Object>> pagedList = null;

	private int objectsPerPage = SysPara.page_num; // 每页大小
	private int currentPage; // 当前页码
	private String selectSql; // 大list的sql
	private int fullSize; // 总大小
	private int fullPageNumber; // 总页数
	private String sortCriterion = null;
	private long czznum;
	private long cxznum;
	private long yxcsznum;
	private Double yxjeznum;
	private Object[] obj;

	//private String poolName; // 数据库名

	public OraPaginatedList(String sql,Object[] obj) 
	{
		selectSql = sql;
		this.currentPage = 1;
		this.obj = obj;
	}

	public OraPaginatedList(String sql) 
	{
		selectSql = sql;
		this.currentPage = 1;
	}

	/**
	 * 设置当前面，内含null值判断代码，可以简化页面上的操作。
	 * 
	 * 参数：
	 * 
	 * @param curPage
	 */
	public void setCurrentPage(int curPage) 
	{
			this.currentPage = curPage;
	}

	public void setObjectsPerPage(int objectsPerPage) {
		this.objectsPerPage = objectsPerPage;
	}

	public void setObjectsPerPage(String objectsPerPage) {
		if (objectsPerPage == null || "null".equals(objectsPerPage))
			this.objectsPerPage = 20;
		else
			this.objectsPerPage = Integer.parseInt(objectsPerPage);
	}

	public List<Map<String, Object>> getList() 
	{

		//mysql的分页
		if(SysPara.db_bz==0)
		{
			String con_Sql = null;
			if (this.sortCriterion != null)
				con_Sql = this.sortCriterion;
		    pagedList = this.getRangeList(selectSql,con_Sql, currentPage,  objectsPerPage,obj);
		}
		//oracle的分页
		else if(SysPara.db_bz==1)
		{
			String fullSql = null;
			if (this.sortCriterion != null)
				fullSql = selectSql + this.sortCriterion + " ";
			else
				fullSql = selectSql;
			pagedList = this.getRangeList_Oracle(fullSql, currentPage,  objectsPerPage,obj);
		}
		fullSize = this.getCount(selectSql, obj);

		if (fullSize % objectsPerPage == 0) 
		{
			fullPageNumber = fullSize / objectsPerPage;
		} 
		else 
		{
			fullPageNumber = fullSize / objectsPerPage + 1;
		}
		return pagedList;
	}

	public int getPageNumber() {
		return currentPage;
	}

	public int getObjectsPerPage() {
		return objectsPerPage;
	}

	public int getFullListSize() {
		return fullSize;
	}

	public void setSortCriterion(String fieldN) {
		this.sortCriterion = (fieldN == null || "null".equals(fieldN)) ? ""
				: fieldN;
	}

	public String getSortCriterion() {
		return this.sortCriterion;
	}

	public String getSearchId() {
		return Integer.toHexString(objectsPerPage * 10000 + currentPage);
	}

	public int getFullPageNumber() {
		return fullPageNumber;
	}

	public void setFullPageNumber(int fullPageNumber) {
		this.fullPageNumber = fullPageNumber;
	}


	public long getCzznum() {
		return czznum;
	}

	public long getCxznum() {
		return cxznum;
	}

	public long getYxcsznum() {
		return yxcsznum;
	}

	public double getYxjeznum() {
		return yxjeznum;
	}
}