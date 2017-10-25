package com.zqkj.bsm.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * <p>Title: ExcelModel</p>
 *
 * <p>Description: Excel表的操作模型</p>
 *
 * <p>Copyright: Copyright (c) 2013-9-03</p>
 *
 * <p>Company: *** </p>
 *
 * @author 0098
 * @version 1.0
 */

public class ExcelModel {
      /**
       * 工作表名
       */
      protected String sheetName;
      /**
       * 表内数据,保存在二维的ArrayList对象中
       */
      protected ArrayList data;
      
      /**
       * 表内数据,保存Map对象中
       */
      protected HashMap resultMap;
      /**
       * 数据表的标题内容
       */
      protected ArrayList header;
      /**
       * 用于设置列宽的整型数组
       * 这个方法在程序中暂未用到
       * 适用于固定列数的表格
       */
      protected int[] width;
      
      /**
       * 放入票数信息
       */
      protected Map map;
      
      
      /**
       * 是否过期信息
       */
      protected int status;
      
      public void setSheetName(String sheetName){
          this.sheetName=sheetName;
      }
      
      public String getSheetName(){
          return this.sheetName;
      }

      public void setData(ArrayList data){
            this.data=data;
      }

      public ArrayList getData(){
            return this.data;
      }

      public void setHeader(ArrayList header){
            this.header=header;
      }

      public ArrayList getHeader(){
            return this.header;
      }

      public void setWidth(int[] width){
            this.width=width;
      }

      public int[] getWidth(){
            return this.width;
      }

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public HashMap getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap resultMap) {
		this.resultMap = resultMap;
	}


      
}
