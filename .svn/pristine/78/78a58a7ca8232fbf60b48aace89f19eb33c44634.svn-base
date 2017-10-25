package com.zqkj.bsm.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * 保存分页记录的数据结构
 *
 * @version 1.0 2010-10-10
 * @author  0188
 * @since   JDK 1.6
 */
public class PageData {
    /**
     * 当前页号
     */
    private int currPage;

    /**
     * 总页号
     */
    private int totalPage;

    /**
     * 每页记录数
     */
    private int pageSize;

    /**
     * 总记录数
     */
    private int totalDataCount;

    /**
     * 当前页记录对象的List
     */
    List<Map<String, Object>> list;

    /**
     * 构造器
     *
     */
    public PageData(){
        this.list = new ArrayList<Map<String, Object>>();
    }

    /**
     * 取当前页号
     *
     * @author  0188  2010-10-25
     * @return
     */
    public int getCurrPage() {
        return currPage;
    }

    /**
     * 设置当前页号
     *
     * @author  0188  2010-10-25
     * @param currPage
     */
    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    /**
     * 取当前页数据
     *
     * @author  0188  2010-10-25
     * @return
     */
    public List<Map<String, Object>> getList() {
        return list;
    }

    /**
     * 设置当前页数据
     *
     * @author  0188  2010-10-25
     * @param list
     */
    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }

    /**
     * 取总页号
     *
     * @author  0188  2010-10-25
     * @return
     */
    public int getTotalPage() {
        return totalPage;
    }

    /**
     * 设置总页号
     *
     * @author  0188  2010-10-25
     * @param totalPage
     */
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    /**
     * 取每页记录数
     *
     * @author  0188  2010-10-25
     * @return
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置每页记录数
     *
     * @author  0188  2010-10-25
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 取总记录数
     *
     * @author  0188  2010-10-31
     * @return
     */
    public int getTotalDataCount() {
        return totalDataCount;
    }

    /**
     * 设置总记录数
     *
     * @author  0188  2010-10-31
     * @param totalDataCount
     */
    public void setTotalDataCount(int totalDataCount) {
        this.totalDataCount = totalDataCount;
    }
}
