package com.zqkj.bsm.util;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import com.zqkj.bsm.mzpc.entity.DePj;
import com.zqkj.bsm.mzpc.entity.Pj;

/**
 *实现生成Excel文件的操作
 */
public class ExcelOperator{
    
   /**
      * 将数据信息写入到Excel表文件 ，采取传入输出流的方式。
      * @param excel Excel表的模型对象 
      * @param out  OutputStream 输出流
      * @throws Exception
      */
     public  void WriteExcel(ExcelModel excel,OutputStream out)throws Exception{
         try{
             HSSFWorkbook workbook =this.getInitWorkbook(excel);
             workbook.write(out);          
                out.close();
            // System.out.println("Done!");
         }catch(Exception e){
             //System.out.println("Failed!");
             e.printStackTrace();
         }
         
         
     }
    
    /**
     * 取得填充了数据的工作簿
     * @param excel ExcelModel Excel表的模型对象
     * @return HSSFWorkbook 工作簿对象
     */
    @SuppressWarnings("rawtypes")
	private  HSSFWorkbook getInitWorkbook(ExcelModel excel){
            

            // 创建新的Excel 工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();

             //在Excel工作簿中建一工作表
             HSSFSheet sheet = null;
             String sheetName = excel.getSheetName();
             
             if(sheetName!=null)sheet=workbook.createSheet(sheetName);
             else sheet=workbook.createSheet();
             
             sheet.setColumnWidth(0, 3766);
             
             /*
              * 设置合并单元格
             //sheet.addMergedRegion(new Region(1,(short)0,1,(short)2));*/
             
             //设置表头字体
             HSSFFont font_h = workbook.createFont();
             font_h.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

             //设置格式
             HSSFCellStyle cellStyle= workbook.createCellStyle();
             cellStyle.setFont(font_h);            


              //在索引0的位置创建行（最顶端的行）
               HSSFRow row = sheet.createRow((short)0);

               ArrayList header = excel.getHeader();
               if(header!=null){
                   for(int i=0;i<header.size();i++){

                  //在索引0的位置创建单元格（左上端）
                  HSSFCell cell = row.createCell(i);
                  // 定义单元格为字符串类型
                 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
  /*               //设置解码方式
                 cell.setEncoding((short)1);*/
                 //设置单元格的格式
                 cell.setCellStyle(cellStyle);
                 // 在单元格中写入表头信息
                 cell.setCellValue((String)header.get(i));
                 sheet.setColumnWidth(i, 5000);
                   }
               }

               ArrayList cdata = excel.getData();
               for (int i=0;i<cdata.size();i++){
                //从第二行开始
                HSSFRow row1 = sheet.createRow(i+1);
                ArrayList rdata =(ArrayList)cdata.get(i);
               //打印一行数据
                for (int j=0;j<rdata.size();j++){

                    HSSFCell cell = row1.createCell( j);
                   
   /*                 //设置字符编码方式
                    cell.setEncoding((short)1);*/
                    
                   Object o = rdata.get(j);
                    //造型,使写入到表中的数值型对象恢复为数值型，
                   //这样就可以进行运算了
                    if(o instanceof BigDecimal){
                    	/*cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                        BigDecimal b=(BigDecimal)o;
                        cell.setCellValue(b.doubleValue());*/
                    	cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
                    	cell.setCellStyle(cellStyle);
                    	BigDecimal b=(BigDecimal)o;
                    	cell.setCellValue(b.doubleValue());
                    }
                    else if(o instanceof Integer){
                    	cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                           Integer it =(Integer)o;
                           cell.setCellValue(it.intValue());
                           
                    }
                    else if(o instanceof Long){
                    	cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                        Long l =(Long)o;
                        cell.setCellValue(l.intValue());
                        
                    }
                    else if(o instanceof Double){
                    	cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                        Double d =(Double)o;
                        cell.setCellValue(d.doubleValue());
                    }
                    else if(o instanceof Float){
                    	cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                        Float f = (Float)o;
                        cell.setCellValue(f.floatValue());
                    }
                    else
                    {
                        cell.setCellValue(o+"");
                        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    }             
            
                    
                }

               }
           return workbook;
            
        }


	/**
	 * 将数据信息写入到Excel表文件 ，采取传入输出流的方式。
	 * 
	 * @param excel
	 *            导出第一类excel
	 * @param out
	 *            OutputStream 输出流
	 * @throws Exception
	 */
	public void WriteMzpcExcel(ExcelModel excel, OutputStream out, int type) throws Exception {
		try {
			HSSFWorkbook workbook = null;
			if(type == 0){
				workbook = this.getInitMzpcWorkbook(excel);
			}else if(type == 1){
				workbook = this.getInitMzpcWorkbook1(excel);
			}else if(type == 2){
				workbook = this.getInitMzpcWorkbook2(excel);
			}else if(type == 3){
				workbook = this.getInitMzpcWorkbook3(excel);
			}else if(type == 4){
				workbook = this.getInitMzpcWorkbook4(excel);
			}else if(type == 5){
				workbook = this.getInitMzpcWorkbook5(excel);
			}
			workbook.write(out);
			out.close();
			// System.out.println("Done!");
		} catch (Exception e) {
			// System.out.println("Failed!");
			e.printStackTrace();
		}

	}
	
	

	
	
	
	/**
	 * 取得填充了数据的工作簿
	 * 
	 * @param excel
	 *            ExcelModel Excel表的模型对象
	 * @return HSSFWorkbook 工作簿对象
	 */
	private HSSFWorkbook getInitMzpcWorkbook(ExcelModel excel) {
		String[] names = new String[]{"ID","NAME","","A+","POINT"};
		
		// 创建新的Excel 工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 在Excel工作簿中建一工作表
		HSSFSheet sheet = null;
		String sheetName = excel.getSheetName();
		
		if (sheetName != null)
			sheet = workbook.createSheet(sheetName);
		else
			sheet = workbook.createSheet();
		//设定列宽
		sheet.setDefaultColumnWidth((short) 8);
		/*
		 * 设置合并单元格 //sheet.addMergedRegion(new Region(1,(short)0,1,(short)2));
		 */

		// 设置表头字体
		HSSFFont font_h = workbook.createFont();
		font_h.setFontName("宋体");
		font_h.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font_h.setFontHeightInPoints((short) 20);
		
		
		// 二级标题字体
		HSSFFont font_h1 = workbook.createFont();
		font_h1.setFontName("宋体");
		font_h1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font_h1.setFontHeightInPoints((short) 10);
		
		// 二级标题字体
		HSSFFont font_h2 = workbook.createFont();
		font_h2.setFontName("宋体");
		font_h2.setFontHeightInPoints((short) 10);
		
		// 标题样式
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直   
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
		cellStyle.setFont(font_h);
		//二级标题样式
		  HSSFCellStyle cellStyle1 = workbook.createCellStyle();
	      cellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直   
	      cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水
	      cellStyle1.setFont(font_h1);
	      //三级标题样式
		  HSSFCellStyle cellStyle2 = workbook.createCellStyle();
	      cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直   
	      cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水
	      cellStyle2.setFont(font_h2);
	      
		// 在索引0的位置创建行（最顶端的行）
		HSSFRow row = sheet.createRow((short) 0);
		sheet.addMergedRegion(new Region(0, (short) 0, 1, (short) (excel.getHeader().size()-1)));
		HSSFCell ce = row.createCell((short) 0);
		ce.setCellValue(sheetName + "("+BHUtil.getTime_year()+")"); // 表格的第一行显示的数据
		cellStyle.setFont(font_h);
        ce.setCellStyle(cellStyle); // 样式，居中   
        
      
		//---------------------------------------上面为标题-----------------------------------------
        HSSFRow row1 = sheet.createRow((short)2);
		ArrayList<String> header = excel.getHeader();
		if (header != null) {
			for (int i = 0; i < header.size(); i++) {
				HSSFCell cell = row1.createCell((short)i);
				// 定义单元格为字符串类型
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 //设置解码方式 cell.setEncoding((short)1);
				// 设置单元格的格式
				cell.setCellStyle(cellStyle1);
				// 在单元格中写入表头信息
				cell.setCellValue((String) header.get(i));
			}
		}
		int begin_row = 3; //单元格合并开始行
		int begin_status = 0; //数组初始下标
		int size = excel.getData().size()%3 == 0 ? (int) Math.floor(excel.getData().size() / 3) : (int) Math.floor(excel.getData().size() / 3) + 1 ;
		//获取最大列数
		for (int i = 0; i < size ; i++) {
			//生成每一列中的5行row
			for (int j = 0; j < 5; j++) {
				sheet.createRow((short)(begin_row + j));
			}
			//合并一大行中的指定单元格
			for (int k = 0; k < excel.getHeader().size(); k++) {
				if(k != 2 && k != 3 && k != 8 && k != 9 && k != 14 && k != 15){
					sheet.addMergedRegion(new Region(begin_row, (short) k, begin_row + 4, (short) k));
				}
			}
			
			Map<String, Object> map = null;
			Map<String, Object> map2 = null;
			Map<String, Object> map3 = null;
				if(begin_status < excel.getData().size()){
					 map = excel.getData().get(begin_status) != null ? (Map<String, Object>) excel.getData().get(begin_status) : null ;
				}
				if(begin_status + 1 < excel.getData().size()){
					 map2 = excel.getData().get(begin_status+1) != null ? (Map<String, Object>) excel.getData().get(begin_status+1) : null ;
				}
				if(begin_status + 2 < excel.getData().size()){
					 map3= excel.getData().get(begin_status+2) != null ? (Map<String, Object>) excel.getData().get(begin_status+2) : null ;
				}
				
					
			
			
			//赋值第一大行的5行
			for (int l = 0; l < 5; l++) {
					HSSFRow hssfRow = sheet.createRow((short) (begin_row + l));
					if(l == 0){ //第一行
						for (int j = 0; j < excel.getHeader().size(); j++) {
							//17列
							HSSFCell hssfCell = hssfRow.createCell((short)j);
							// 定义单元格为字符串类型
							hssfCell.setCellType(HSSFCell.CELL_TYPE_STRING);
							 //设置解码方式 cell.setEncoding((short)1);
							// 设置单元格的格式
							hssfCell.setCellStyle(cellStyle2);
							// 在单元格中写入表头信息
							if(j == 0 || j == 1 || j == 3 || j == 4){
								if(map != null){
									hssfCell.setCellValue(map.get(names[j]).toString());
								}
							}else if(j == 6 || j == 7 || j == 9 || j == 10){
								if(map2 != null){
									hssfCell.setCellValue(map2.get(names[j-6]).toString());
								}
							}else if(j == 12 || j == 13 || j == 15 || j == 16){
								if(map3 != null){
									hssfCell.setCellValue(map3.get(names[j-12]).toString());
								}
							}
							else if( j == 2 || j == 8 || j == 14){
								hssfCell.setCellValue("A+");
							}
							
						}
					}else{
						String key = null;
						if(l == 1){
							key = "A";
						}else if(l == 2){
							key = "B";
						}else if(l == 3){
							key = "C";
						}else if(l == 4){
							key = "D";
						}
						for (int j = 0; j < excel.getHeader().size(); j++) {
							//17列
							HSSFCell hssfCell = hssfRow.createCell((short)j);
							// 定义单元格为字符串类型
							hssfCell.setCellType(HSSFCell.CELL_TYPE_STRING);
							 //设置解码方式 cell.setEncoding((short)1);
							// 设置单元格的格式
							hssfCell.setCellStyle(cellStyle2);
							
							if(j == 2 || j == 8 || j == 14){
								hssfCell.setCellValue(key);
							}
							if(j == 3){
								if(map != null){
									hssfCell.setCellValue(map.get(key).toString());
								}
							}else if(j == 9){
								if(map2 != null){
									hssfCell.setCellValue(map2.get(key).toString());
								}
							}else if(j == 15){
								if(map3 != null){
									hssfCell.setCellValue(map3.get(key).toString());	
								}
							}
						}
					}
			}
			
			begin_status += 3;
			begin_row += 5;
		}
		//设定最后一行备注
		sheet.addMergedRegion(new Region(begin_row, (short) 0, begin_row + 1, (short) (excel.getHeader().size()-1)));
		HSSFRow hssfRowDown = sheet.createRow((short) begin_row);
		HSSFCell hssfCell = hssfRowDown.createCell((short) 0);
		hssfCell.setCellValue("注：按A+计95分，A计90分，B计80分，C计70分，D计60分，计算得分。"); // 表格的第一行显示的数据
		cellStyle1.setFont(font_h1);
		hssfCell.setCellStyle(cellStyle1); // 样式，居中   
		  
		//最后一刚
				sheet.addMergedRegion(new Region(begin_row+2, (short) 0, begin_row + 3, (short) (excel.getHeader().size()-1)));
				HSSFRow hssfRadomRow = sheet.createRow((short) begin_row+2);
				HSSFCell hssfRadomCell = hssfRadomRow.createCell((short) 0);
				hssfRadomCell.setCellValue("当前总票数："+excel.getMap().get("ALLRADOM")+",已投票数:"+excel.getMap().get("OLDRADOM")+",未投票数："+excel.getMap().get("NEWRADOM")); // 表格的第一行显示的数据
				cellStyle1.setFont(font_h1);
				hssfRadomCell.setCellStyle(cellStyle1); // 样式，居中   
	//	
		/*int j = 3; //从第三行开始
		 * sheet.addMergedRegion(new Region(3, (short)7, 1, (short) 1));
		for (int i = 0; i < 1; i++) {
			sheet.addMergedRegion(new Region(j, (short)(j+4), 1, (short) 1));
			HSSFRow hssfRow = sheet.createRow((short)3);
		}*/
		return workbook;

	}
	
	
	
	/**
	 * 取得填充了数据的工作簿
	 * 
	 * @param excel
	 *            ExcelModel Excel表的模型对象
	 * @return HSSFWorkbook 工作簿对象
	 */
	private HSSFWorkbook getInitMzpcWorkbook1(ExcelModel excel) {
		// 创建新的Excel 工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 在Excel工作簿中建一工作表
		HSSFSheet sheet = null;
		String sheetName = excel.getSheetName();
		
		if (sheetName != null)
			sheet = workbook.createSheet(sheetName);
		else
			sheet = workbook.createSheet();
		//设定列宽
		sheet.setDefaultColumnWidth((short) 10);
		/*
		 * 设置合并单元格 //sheet.addMergedRegion(new Region(1,(short)0,1,(short)2));
		 */
		// 设置表头字体
		HSSFFont font_h = workbook.createFont();
		font_h.setFontName("宋体");
		font_h.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font_h.setFontHeightInPoints((short) 20);
		
		// 二级标题字体
		HSSFFont font_h1 = workbook.createFont();
		font_h1.setFontName("宋体");
		font_h1.setFontHeightInPoints((short) 10);
		
		// 标题样式
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直   
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
		cellStyle.setFont(font_h);
		//二级标题样式
		HSSFCellStyle cellStyle1 = workbook.createCellStyle();
	    cellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直   
	    cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水
	    cellStyle1.setFont(font_h1);
	      
		// 在索引0的位置创建行（最顶端的行）
		HSSFRow row = sheet.createRow((short) 0);
		HSSFRow hssfRow1 = sheet.createRow((short) 1);
        HSSFRow hssfRow2 = sheet.createRow((short) 2);
		sheet.addMergedRegion(new Region(0, (short) 0, 1, (short) 7));
		HSSFCell ce = row.createCell((short) 0);
		ce.setCellValue(sheetName + "("+BHUtil.getTime_year()+")"); // 表格的第一行显示的数据
		cellStyle.setFont(font_h);
        ce.setCellStyle(cellStyle); // 样式，居中   
        
        HSSFRow hssfRow3 = sheet.createRow((short) 3);
        HSSFRow hssfRow4 = sheet.createRow((short) 4);
        sheet.addMergedRegion(new Region(3, (short) 0, 4, (short) 0));
        sheet.addMergedRegion(new Region(3, (short) 1, 4, (short) 1));
        sheet.addMergedRegion(new Region(3, (short) 2, 3, (short) 4));
        sheet.addMergedRegion(new Region(3, (short) 5, 3, (short) 7));
        HSSFCell hssfCell = hssfRow3.createCell((short) 0);
        hssfCell.setCellValue("姓名"); // 表格的第一行显示的数据
        hssfCell.setCellStyle(cellStyle1); // 样式，居中
        HSSFCell hssfCel2 = hssfRow3.createCell((short) 1);
        hssfCel2.setCellValue("职务"); // 表格的第一行显示的数据
        hssfCel2.setCellStyle(cellStyle1); // 样式，居中
        HSSFCell hssfCel3 = hssfRow3.createCell((short) 2);
        hssfCel3.setCellValue("综合评价"); // 表格的第一行显示的数据
        hssfCel3.setCellStyle(cellStyle1); // 样式，居中   
        HSSFCell hssfCel4 = hssfRow3.createCell((short) 5);
        hssfCel4.setCellValue("任用建议"); // 表格的第一行显示的数据
        hssfCel4.setCellStyle(cellStyle1); // 样式，居中   
        HSSFCell hssfCel5 = hssfRow4.createCell((short) 2);
        hssfCel5.setCellValue("胜任"); // 表格的第一行显示的数据
        hssfCel5.setCellStyle(cellStyle1); // 样式，居中   
        HSSFCell hssfCel6 = hssfRow4.createCell((short) 3);
        hssfCel6.setCellValue("基本胜任"); // 表格的第一行显示的数据
        hssfCel6.setCellStyle(cellStyle1); // 样式，居中
        HSSFCell hssfCel7 = hssfRow4.createCell((short) 4);
        hssfCel7.setCellValue("不胜任"); // 表格的第一行显示的数据
        hssfCel7.setCellStyle(cellStyle1); // 样式，居中
        HSSFCell hssfCel8 = hssfRow4.createCell((short) 5);
        hssfCel8.setCellValue("任用"); // 表格的第一行显示的数据
        hssfCel8.setCellStyle(cellStyle1); // 样式，居中
        HSSFCell hssfCel9 = hssfRow4.createCell((short) 6);
        hssfCel9.setCellValue("不任用"); // 表格的第一行显示的数据
        hssfCel9.setCellStyle(cellStyle1); // 样式，居中
        HSSFCell hssfCel10 = hssfRow4.createCell((short) 7);
        hssfCel10.setCellValue("弃权"); // 表格的第一行显示的数据
        hssfCel10.setCellStyle(cellStyle1); // 样式，居中
        
        int bengin_row = 5; //开始行数
        for (int i = 0; i < excel.getData().size(); i++) {
        	HSSFRow hssfRow = sheet.createRow((short) bengin_row + i);
        	Map<String, Object> map = (Map<String, Object>) excel.getData().get(i);
        	HSSFCell hssfCell_value = hssfRow.createCell((short) 0);
        	hssfCell_value.setCellValue(map.get("NAME") != null ? map.get("NAME").toString() : ""); // 表格的第一行显示的数据
        	hssfCell_value.setCellStyle(cellStyle1); // 样式，居中
        	HSSFCell hssfCell_value1 = hssfRow.createCell((short) 1);
        	hssfCell_value1.setCellValue(map.get("ORG") != null ? map.get("ORG").toString() : ""); // 表格的第一行显示的数据
        	hssfCell_value1.setCellStyle(cellStyle1); // 样式，居中
        	HSSFCell hssfCell_value2 = hssfRow.createCell((short) 2);
        	hssfCell_value2.setCellValue(map.get("A0") != null ? map.get("A0").toString() : "0"); // 表格的第一行显示的数据
        	hssfCell_value2.setCellStyle(cellStyle1); // 样式，居中
        	HSSFCell hssfCell_value3 = hssfRow.createCell((short) 3);
        	hssfCell_value3.setCellValue(map.get("A1") != null ? map.get("A1").toString() : "0"); // 表格的第一行显示的数据
        	hssfCell_value3.setCellStyle(cellStyle1); // 样式，居中
        	HSSFCell hssfCell_value4 = hssfRow.createCell((short) 4);
        	hssfCell_value4.setCellValue(map.get("A2") != null ? map.get("A2").toString() : "0"); // 表格的第一行显示的数据
        	hssfCell_value4.setCellStyle(cellStyle1); // 样式，居中
        	HSSFCell hssfCell_value5 = hssfRow.createCell((short) 5);
        	hssfCell_value5.setCellValue(map.get("A3") != null ? map.get("A3").toString() : "0"); // 表格的第一行显示的数据
        	hssfCell_value5.setCellStyle(cellStyle1); // 样式，居中
        	HSSFCell hssfCell_value6 = hssfRow.createCell((short) 6);
        	hssfCell_value6.setCellValue(map.get("A4") != null ? map.get("A4").toString() : "0"); // 表格的第一行显示的数据
        	hssfCell_value6.setCellStyle(cellStyle1); // 样式，居中
        	HSSFCell hssfCell_value7 = hssfRow.createCell((short) 7);
        	if(excel.getStatus() == 1){
        		hssfCell_value7.setCellValue(excel.getMap().get("NEWRADOM").toString());
        	}else{
        		hssfCell_value7.setCellValue(0); // 表格的第一行显示的数据
        	}
        	hssfCell_value7.setCellStyle(cellStyle1); // 样式，居中
        	//设定最后一行备注
        	HSSFRow hssfRowDown = sheet.createRow((short) bengin_row + excel.getData().size());
    		sheet.addMergedRegion(new Region(bengin_row + excel.getData().size(), (short) 0, bengin_row + excel.getData().size() + 1, (short) 7));
    		HSSFCell hssfCellDown = hssfRowDown.createCell((short) 0);
    		hssfCellDown.setCellValue("当前总票数："+excel.getMap().get("ALLRADOM")+",已投票数:"+excel.getMap().get("OLDRADOM")+",未投票数："+excel.getMap().get("NEWRADOM")); // 表格的第一行显示的数据
    		cellStyle1.setFont(font_h1);
    		hssfCellDown.setCellStyle(cellStyle1); // 样式，居中   
    		
		}
        return workbook;
        
	}
		
		
	/**
	 * 取得填充了数据的工作簿
	 * 
	 * @param excel
	 *            ExcelModel Excel表的模型对象
	 * @return HSSFWorkbook 工作簿对象
	 */
	private HSSFWorkbook getInitMzpcWorkbook2(ExcelModel excel) {
		String[] names = new String[]{"ID","NAME","","10分","POINT"};
		
		// 创建新的Excel 工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 在Excel工作簿中建一工作表
		HSSFSheet sheet = null;
		String sheetName = excel.getSheetName();
		
		if (sheetName != null)
			sheet = workbook.createSheet(sheetName);
		else
			sheet = workbook.createSheet();
		//设定列宽
		sheet.setDefaultColumnWidth((short) 8);
		/*
		 * 设置合并单元格 //sheet.addMergedRegion(new Region(1,(short)0,1,(short)2));
		 */

		// 设置表头字体
		HSSFFont font_h = workbook.createFont();
		font_h.setFontName("宋体");
		font_h.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font_h.setFontHeightInPoints((short) 20);
		
		
		// 二级标题字体
		HSSFFont font_h1 = workbook.createFont();
		font_h1.setFontName("宋体");
		font_h1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font_h1.setFontHeightInPoints((short) 10);
		
		// 二级标题字体
		HSSFFont font_h2 = workbook.createFont();
		font_h2.setFontName("宋体");
		font_h2.setFontHeightInPoints((short) 10);
		
		// 标题样式
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直   
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
		cellStyle.setFont(font_h);
		//二级标题样式
		  HSSFCellStyle cellStyle1 = workbook.createCellStyle();
	      cellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直   
	      cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水
	      cellStyle1.setFont(font_h1);
	      //三级标题样式
		  HSSFCellStyle cellStyle2 = workbook.createCellStyle();
	      cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直   
	      cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水
	      cellStyle2.setFont(font_h2);
	      
		// 在索引0的位置创建行（最顶端的行）
		HSSFRow row = sheet.createRow((short) 0);
		sheet.addMergedRegion(new Region(0, (short) 0, 1, (short) (excel.getHeader().size()-1)));
		HSSFCell ce = row.createCell((short) 0);
		ce.setCellValue(sheetName + "("+BHUtil.getTime_year()+")"); // 表格的第一行显示的数据
		cellStyle.setFont(font_h);
        ce.setCellStyle(cellStyle); // 样式，居中   
        
      
		//---------------------------------------上面为标题-----------------------------------------
        HSSFRow row1 = sheet.createRow((short)2);
		ArrayList<String> header = excel.getHeader();
		if (header != null) {
			for (int i = 0; i < header.size(); i++) {
				HSSFCell cell = row1.createCell((short)i);
				// 定义单元格为字符串类型
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 //设置解码方式 cell.setEncoding((short)1);
				// 设置单元格的格式
				cell.setCellStyle(cellStyle1);
				// 在单元格中写入表头信息
				cell.setCellValue((String) header.get(i));
			}
		}
		int begin_row = 3; //单元格合并开始行
		int begin_status = 0; //数组初始下标
		int size = excel.getData().size()%3 == 0 ? (int) Math.floor(excel.getData().size() / 3) : (int) Math.floor(excel.getData().size() / 3) + 1 ;
		//获取最大列数
		for (int i = 0; i < size ; i++) {
			//生成每一列中的5行row
			for (int j = 0; j < 10; j++) {
				sheet.createRow((short)(begin_row + j));
			}
			//合并一大行中的指定单元格
			for (int k = 0; k < excel.getHeader().size(); k++) {
				if(k != 2 && k != 3 && k != 8 && k != 9 && k != 14 && k != 15){
					sheet.addMergedRegion(new Region(begin_row, (short) k, begin_row + 9, (short) k));
				}
			}
			
			Map<String, Object> map = null;
			Map<String, Object> map2 = null;
			Map<String, Object> map3 = null;
				if(begin_status < excel.getData().size()){
					 map = excel.getData().get(begin_status) != null ? (Map<String, Object>) excel.getData().get(begin_status) : null ;
				}
				if(begin_status + 1 < excel.getData().size()){
					 map2 = excel.getData().get(begin_status+1) != null ? (Map<String, Object>) excel.getData().get(begin_status+1) : null ;
				}
				if(begin_status + 2 < excel.getData().size()){
					 map3= excel.getData().get(begin_status+2) != null ? (Map<String, Object>) excel.getData().get(begin_status+2) : null ;
				}
				
					
			
			
			//赋值第一大行的5行
			for (int l = 0; l < 10; l++) {
					HSSFRow hssfRow = sheet.createRow((short) (begin_row + l));
					if(l == 0){ //第一行
						for (int j = 0; j < excel.getHeader().size(); j++) {
							//17列
							HSSFCell hssfCell = hssfRow.createCell((short)j);
							// 定义单元格为字符串类型
							hssfCell.setCellType(HSSFCell.CELL_TYPE_STRING);
							 //设置解码方式 cell.setEncoding((short)1);
							// 设置单元格的格式
							hssfCell.setCellStyle(cellStyle2);	
							// 在单元格中写入表头信息
							if(j == 0 || j == 1 || j == 3 || j == 4){
								if(map != null){
									hssfCell.setCellValue(map.get(names[j]).toString());
								}
							}else if(j == 6 || j == 7 || j == 9 || j == 10){
								if(map2 != null){
									hssfCell.setCellValue(map2.get(names[j-6]).toString());
								}
							}else if(j == 12 || j == 13 || j == 15 || j == 16){
								if(map3 != null){
									hssfCell.setCellValue(map3.get(names[j-12]).toString());
								}
							}
							else if( j == 2 || j == 8 || j == 14){
								hssfCell.setCellValue("10分");
							}
							
						}
					}else{
						String key = null;
						if(l == 1){
							key = "9分";
						}else if(l == 2){
							key = "8分";
						}else if(l == 3){
							key = "7分";
						}else if(l == 4){
							key = "6分";
						}else if(l == 5){
							key = "5分";
						}else if(l == 6){
							key = "4分";
						}else if(l == 7){
							key = "3分";
						}else if(l == 8){
							key = "2分";
						}else if(l == 9){
							key = "1分";
						}
						for (int j = 0; j < excel.getHeader().size(); j++) {
							//17列
							HSSFCell hssfCell = hssfRow.createCell((short)j);
							// 定义单元格为字符串类型
							hssfCell.setCellType(HSSFCell.CELL_TYPE_STRING);
							 //设置解码方式 cell.setEncoding((short)1);
							// 设置单元格的格式
							hssfCell.setCellStyle(cellStyle2);
							
							if(j == 2 || j == 8 || j == 14){
								hssfCell.setCellValue(key);
							}
							if(j == 3){
								if(map != null){
									hssfCell.setCellValue(map.get(key).toString());
								}
							}else if(j == 9){
								if(map2 != null){
									hssfCell.setCellValue(map2.get(key).toString());
								}
							}else if(j == 15){
								if(map3 != null){
									hssfCell.setCellValue(map3.get(key).toString());	
								}
							}
						}
					}
			}
			
			begin_status += 3;
			begin_row += 10;
		}
		//设定最后一行备注
		sheet.addMergedRegion(new Region(begin_row, (short) 0, begin_row + 1, (short) (excel.getHeader().size()-1)));
		HSSFRow hssfRowDown = sheet.createRow((short) begin_row);
		HSSFCell hssfCell = hssfRowDown.createCell((short) 0);
		hssfCell.setCellValue("注：按总分除以投票数计算得分。"); // 表格的第一行显示的数据
		cellStyle1.setFont(font_h1);
		hssfCell.setCellStyle(cellStyle1); // 样式，居中   
		  
		//最后一刚
				sheet.addMergedRegion(new Region(begin_row+2, (short) 0, begin_row + 3, (short) (excel.getHeader().size()-1)));
				HSSFRow hssfRadomRow = sheet.createRow((short) begin_row+2);
				HSSFCell hssfRadomCell = hssfRadomRow.createCell((short) 0);
				hssfRadomCell.setCellValue("当前总票数："+excel.getMap().get("ALLRADOM")+",已投票数:"+excel.getMap().get("OLDRADOM")+",未投票数："+excel.getMap().get("NEWRADOM")); // 表格的第一行显示的数据
				cellStyle1.setFont(font_h1);
				hssfRadomCell.setCellStyle(cellStyle1); // 样式，居中   
	//	
		/*int j = 3; //从第三行开始
		 * sheet.addMergedRegion(new Region(3, (short)7, 1, (short) 1));
		for (int i = 0; i < 1; i++) {
			sheet.addMergedRegion(new Region(j, (short)(j+4), 1, (short) 1));
			HSSFRow hssfRow = sheet.createRow((short)3);
		}*/
		return workbook;

	}

	
	/**
	 * 取得填充了数据的工作簿
	 * 
	 * @param excel
	 *            ExcelModel Excel表的模型对象
	 * @return HSSFWorkbook 工作簿对象
	 */
	private HSSFWorkbook getInitMzpcWorkbook3(ExcelModel excel) {
		// 创建新的Excel 工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 在Excel工作簿中建一工作表
		HSSFSheet sheet = null;
		String sheetName = excel.getSheetName();
		
		if (sheetName != null)
			sheet = workbook.createSheet(sheetName);
		else
			sheet = workbook.createSheet();
		//设定列宽
		sheet.setDefaultColumnWidth((short) 12);
		/*
		 * 设置合并单元格 //sheet.addMergedRegion(new Region(1,(short)0,1,(short)2));
		 */

		// 设置表头字体
		HSSFFont font_h = workbook.createFont();
		font_h.setFontName("宋体");
		font_h.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font_h.setFontHeightInPoints((short) 20);
		
		
		// 二级标题字体
		HSSFFont font_h1 = workbook.createFont();
		font_h1.setFontName("宋体");
		font_h1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font_h1.setFontHeightInPoints((short) 10);
		
		// 二级标题字体
		HSSFFont font_h2 = workbook.createFont();
		font_h2.setFontName("宋体");
		font_h2.setFontHeightInPoints((short) 10);
		
		// 标题样式
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直   
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
		cellStyle.setFont(font_h);
		//二级标题样式
		  HSSFCellStyle cellStyle1 = workbook.createCellStyle();
	      cellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直   
	      cellStyle1.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 水
	      cellStyle1.setFont(font_h1);
	      //三级标题样式
		  HSSFCellStyle cellStyle2 = workbook.createCellStyle();
	      cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直   
	      cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水
	      cellStyle2.setFont(font_h2);
	      
	    //三级标题样式
		  HSSFCellStyle cellStyle4 = workbook.createCellStyle();
		  cellStyle4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直   
		  cellStyle4.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 水
		  cellStyle4.setFont(font_h2);
		  
		  
		//三级标题样式
		  HSSFCellStyle cellStyle5 = workbook.createCellStyle();
		  cellStyle5.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直   
		  cellStyle5.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水
		  cellStyle5.setFont(font_h2);
	      ArrayList<String> header = excel.getHeader();
		// 在索引0的位置创建行（最顶端的行）
		HSSFRow row = sheet.createRow((short) 0);
		sheet.addMergedRegion(new Region(0, (short) 0, 1, (short) (header.size() - 1)));
		HSSFCell ce = row.createCell((short) 0);
		ce.setCellValue(sheetName + "("+BHUtil.getTime_year()+")"); // 表格的第一行显示的数据
		cellStyle.setFont(font_h);
        ce.setCellStyle(cellStyle); // 样式，居中   
        
        int status = 2;
        for(int i = 1 ; i < 6 ; i ++ ){
        	 HSSFRow row1 = sheet.createRow((short)status);
        	 sheet.addMergedRegion(new Region(status, (short) 0, status, (short) (header.size() - 1)));
        	 HSSFCell ce_ = row1.createCell((short) 0);
        	 Map<Integer, Pj> map =  (Map<Integer, Pj>) excel.getResultMap().get("ot");
        	 ce_.setCellValue(map.get(i).getTitle()); // 表格的第一行显示的数据
     		 cellStyle1.setFont(font_h1);
     		 ce_.setCellStyle(cellStyle1); // 样式，居中   
             
             
             HSSFRow row2 = sheet.createRow((short)status + 1);
             for (int j = 0; j < header.size(); j++) {
            	 sheet.addMergedRegion(new Region(status + 1, (short) j, status + 1, (short) (j)));
            	 HSSFCell ce_1 = row2.createCell((short) j);
            	 //奇数列填判断值
            	 if(j%2 == 0){
            		 ce_1.setCellValue(header.get(j)); // 表格的第一行显示的数据
            		 cellStyle2.setFont(font_h2);
             		 ce_1.setCellStyle(cellStyle2); // 样式，左对齐
             	 //偶数列填数组
            	 }else{
            		if(j == 1){
            			 ce_1.setCellValue(map.get(i).getA()+"票"); // 满意
            		}
            		if(j == 3){
            			 ce_1.setCellValue(map.get(i).getB()+"票"); // 基本满意
            		}
            		if(j == 5){
            			ce_1.setCellValue(map.get(i).getC()+"票"); // 不满意
            		}
            		if(j == 7){
            			ce_1.setCellValue(map.get(i).getD()+"票"); // 不了解
            		}
            		 cellStyle2.setFont(font_h2);
             		 ce_1.setCellStyle(cellStyle2); // 样式，居中
            	 }
			}
           status += 2;  
        }
        
        
        
        HSSFRow row_2 = sheet.createRow((short) status);
		sheet.addMergedRegion(new Region(status, (short) 0, status, (short) (header.size() - 1)));
		HSSFCell ce_2 = row_2.createCell((short) 0);
		 Map<Integer, Pj> map =  (Map<Integer, Pj>) excel.getResultMap().get("ot");
		ce_2.setCellValue(map.get(6).getTitle()); // 表格的第一行显示的数据
		cellStyle1.setFont(font_h1);
		ce_2.setCellStyle(cellStyle1); // 样式，居中   
        status +=1;
        ArrayList<String> headList2 = (ArrayList<String>) excel.getResultMap().get("two");
        
        for (int i = 0; i < headList2.size(); i++) {
        	HSSFRow row_3 = sheet.createRow((short) status);
    		sheet.addMergedRegion(new Region(status, (short) 0, status, (short) (header.size() - 2)));
    		HSSFCell ce_3 = row_3.createCell((short) 0);
    		ce_3.setCellValue(headList2.get(i)); // 表格的第一行显示的数据
    		ce_3.setCellStyle(cellStyle4); // 样式，左对齐   
    		
    		sheet.addMergedRegion(new Region(status, (short) (header.size() - 1), status, (short) (header.size() - 1)));
    		
    		HSSFCell ce_4 = row_3.createCell((short) (header.size() - 1));
    		Map<Integer, Pj> map_3 =  (Map<Integer, Pj>) excel.getResultMap().get("ot");
    		if(i == 0){
    			ce_4.setCellValue(map_3.get(6).getA()+"票"); // 表格的第一行显示的数据
    		}
    		if(i == 1){
    			ce_4.setCellValue(map_3.get(6).getB()+"票"); // 表格的第一行显示的数据
    		}
    		if(i == 2){
    			ce_4.setCellValue(map_3.get(6).getC()+"票"); // 表格的第一行显示的数据
    		}
    		if(i == 3){
    			ce_4.setCellValue(map_3.get(6).getD()+"票"); // 表格的第一行显示的数据
    		}
    		if(i == 4){
    			ce_4.setCellValue(map_3.get(6).getE()+"票"); // 表格的第一行显示的数据
    		}
    		if(i == 5){
    			ce_4.setCellValue(map_3.get(6).getF()+"票"); // 表格的第一行显示的数据
    		}
    		if(i == 6){
    			ce_4.setCellValue(map_3.get(6).getG()+"票"); // 表格的第一行显示的数据
    			ArrayList<String> ThreeList = (ArrayList<String>) excel.getResultMap().get("three");
    			for (int j = 0; j < ThreeList.size(); j++) {
					status ++;
					HSSFRow row_5 = sheet.createRow((short) status);
		    		sheet.addMergedRegion(new Region(status, (short) 0, status, (short) (header.size() - 1)));
		    		HSSFCell ce_5 = row_5.createCell((short) 0);
		    		ce_5.setCellValue(ThreeList.get(j)); // 表格的第一行显示的数据
		    		cellStyle2.setFont(font_h2);
		    		ce_5.setCellStyle(cellStyle2); // 样式，左对齐   
				}
    		}
    		if(i == 7){
    			ce_4.setCellValue(map_3.get(6).getH()+"票"); // 表格的第一行显示的数据
    		}
    		cellStyle2.setFont(font_h2);
    		ce_4.setCellStyle(cellStyle2); // 样式，左对齐   
    		status ++;
		}
        
        
        HSSFRow row_6 = sheet.createRow((short) status);
		sheet.addMergedRegion(new Region(status, (short) 0, status, (short) (header.size() - 1)));
		HSSFCell ce_6 = row_6.createCell((short) 0);
		Map<Integer, Pj> map_3 =  (Map<Integer, Pj>) excel.getResultMap().get("ot");
		ce_6.setCellValue(map_3.get(7).getTitle()); // 表格的第一行显示的数据
		ce_6.setCellStyle(cellStyle1); // 样式，左对齐   
        
		status ++ ;
		ArrayList<String> msgList = (ArrayList<String>) excel.getResultMap().get("msg");
		for (int j = 0; j < msgList.size(); j++) {
			HSSFRow row_7 = sheet.createRow((short) status);
			sheet.addMergedRegion(new Region(status, (short) 0, status, (short) (header.size() - 1)));
			HSSFCell ce_7 = row_7.createCell((short) 0);
			Map<Integer, Pj> map_7 =  (Map<Integer, Pj>) excel.getResultMap().get("ot");
			ce_7.setCellValue(msgList.get(j)); // 表格的第一行显示的数据
			ce_7.setCellStyle(cellStyle2); // 样式，左对齐   
			status ++;
		}
		//---------------------------------------上面为标题-----------------------------------------
       
		//设定最后一行备注
		sheet.addMergedRegion(new Region(status, (short) 0, status+1, (short) (excel.getHeader().size()-1)));
		HSSFRow hssfRowDown = sheet.createRow((short) status);
		HSSFCell hssfCell = hssfRowDown.createCell((short) 0);
		hssfCell.setCellValue("注：按当前投票数进行统计。"); // 表格的第一行显示的数据
		hssfCell.setCellStyle(cellStyle5); // 样式，居中   
		  status +=2;
		//最后一刚
				sheet.addMergedRegion(new Region(status, (short) 0, status + 3, (short) (excel.getHeader().size()-1)));
				HSSFRow hssfRadomRow = sheet.createRow((short) status);
				HSSFCell hssfRadomCell = hssfRadomRow.createCell((short) 0);
				hssfRadomCell.setCellValue("当前总票数："+excel.getMap().get("ALLRADOM")+",已投票数:"+excel.getMap().get("OLDRADOM")+",未投票数："+excel.getMap().get("NEWRADOM")); // 表格的第一行显示的数据
				hssfRadomCell.setCellStyle(cellStyle5); // 样式，居中   
				
	//	
		/*int j = 3; //从第三行开始
		 * sheet.addMergedRegion(new Region(3, (short)7, 1, (short) 1));
		for (int i = 0; i < 1; i++) {
			sheet.addMergedRegion(new Region(j, (short)(j+4), 1, (short) 1));
			HSSFRow hssfRow = sheet.createRow((short)3);
		}*/
		return workbook;

	}
	
	/**
	 * 取得填充了数据的工作簿
	 * 
	 * @param excel
	 *            ExcelModel Excel表的模型对象
	 * @return HSSFWorkbook 工作簿对象
	 */
	private HSSFWorkbook getInitMzpcWorkbook4(ExcelModel excel) {
		// 创建新的Excel 工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 在Excel工作簿中建一工作表
		HSSFSheet sheet = null;
		String sheetName = excel.getSheetName();
		
		if (sheetName != null)
			sheet = workbook.createSheet(sheetName);
		else
			sheet = workbook.createSheet();
		//设定列宽
		sheet.setDefaultColumnWidth((short) 12);
		/*
		 * 设置合并单元格 //sheet.addMergedRegion(new Region(1,(short)0,1,(short)2));
		 */

		// 设置表头字体
		HSSFFont font_h = workbook.createFont();
		font_h.setFontName("宋体");
		font_h.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font_h.setFontHeightInPoints((short) 20);
		
		
		// 二级标题字体
		HSSFFont font_h1 = workbook.createFont();
		font_h1.setFontName("宋体");
		font_h1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font_h1.setFontHeightInPoints((short) 10);
		
		// 二级标题字体
		HSSFFont font_h2 = workbook.createFont();
		font_h2.setFontName("宋体");
		font_h2.setFontHeightInPoints((short) 10);
		
		// 标题样式
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直   
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
		cellStyle.setFont(font_h);
		//二级标题样式
		  HSSFCellStyle cellStyle1 = workbook.createCellStyle();
	      cellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直   
	      cellStyle1.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 水
	      cellStyle1.setFont(font_h1);
	      //三级标题样式
		  HSSFCellStyle cellStyle2 = workbook.createCellStyle();
	      cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直   
	      cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水
	      cellStyle2.setFont(font_h2);
	      
	    //三级标题样式
		  HSSFCellStyle cellStyle4 = workbook.createCellStyle();
		  cellStyle4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直   
		  cellStyle4.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 水
		  cellStyle4.setFont(font_h2);
		  
		  
		//三级标题样式
		  HSSFCellStyle cellStyle5 = workbook.createCellStyle();
		  cellStyle5.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直   
		  cellStyle5.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水
		  cellStyle5.setFont(font_h2);
		  
		  
		  HSSFCellStyle cellStyle6 = workbook.createCellStyle();
		  cellStyle6.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直   
		  cellStyle6.setAlignment(HSSFCellStyle.ALIGN_RIGHT);// 水
		  cellStyle6.setFont(font_h2);
	      ArrayList<String> header = excel.getHeader();
		// 在索引0的位置创建行（最顶端的行）
	      int status = 0;
	      ArrayList<Map<String, Object>> list = excel.getData();
	 	 HSSFRow row = sheet.createRow((short) status);
	  		sheet.addMergedRegion(new Region(status, (short) 0, status+1, (short) (13)));
	  		HSSFCell ce = row.createCell((short) 0);
	  		ce.setCellValue(sheetName + "("+BHUtil.getTime_year()+")"); // 表格的第一行显示的数据
	  		cellStyle.setFont(font_h);
	          ce.setCellStyle(cellStyle); // 样式，居中   
	          status +=2;
	      for (Map<String, Object> mapResult : list) {
	          HSSFRow row1 = sheet.createRow((short) status);
	  		sheet.addMergedRegion(new Region(status, (short) 0, status, (short) (13)));
	  		HSSFCell ce1 = row1.createCell((short) 0);
	  		ce1.setCellValue("评测日期:" + new SimpleDateFormat("yyyy年MM月dd日").format(new Date())); // 表格的第一行显示的数据
	          ce1.setCellStyle(cellStyle6); // 样式，居中   
	          
	          status+=1;
	          HSSFRow row2 = sheet.createRow((short) status);
	  		sheet.addMergedRegion(new Region(status, (short) 0, status, (short) (6)));
	  		HSSFCell ce2 = row2.createCell((short) 0);
	  		ce2.setCellValue("姓名：" + mapResult.get("userName").toString()); // 表格的第一行显示的数据
	          ce2.setCellStyle(cellStyle4); // 样式，居中  
	          sheet.addMergedRegion(new Region(status, (short) 7, status, (short) (13)));
	          HSSFCell ce3 = row2.createCell((short) 7);
	  		ce3.setCellValue("职务：" + mapResult.get("work").toString()); // 表格的第一行显示的数据
	          ce3.setCellStyle(cellStyle4); // 样式，居中  
	          
	          status+=1;
	          int beginrow = status;
	          HSSFRow row4= sheet.createRow((short) status);
	          HSSFRow row5= sheet.createRow((short) status+1);	 
	          HSSFRow row6= sheet.createRow((short) status+2);
	          status+=3;
	          
	          sheet.addMergedRegion(new Region(beginrow, (short) 0, beginrow+2, (short) (0)));
	          HSSFCell ce4 = row4.createCell((short) 0);
	  		ce4.setCellValue("类别"); // 表格的第一行显示的数据
	          ce4.setCellStyle(cellStyle2); // 样式，居中  
	          sheet.addMergedRegion(new Region(beginrow, (short) 1, beginrow+2, (short) (3)));
	          HSSFCell ce5 = row4.createCell((short) 1);
	          ce5.setCellValue("评价内容"); // 表格的第一行显示的数据
	          ce5.setCellStyle(cellStyle2); // 样式，居中  
	          
	          sheet.addMergedRegion(new Region(beginrow, (short) 4, beginrow, (short) (13)));
	          HSSFCell ce6 = row4.createCell((short) 4);
	  		ce6.setCellValue("评价类别"); // 表格的第一行显示的数据
	          ce6.setCellStyle(cellStyle2); // 样式，居中  
	          
	          int beginCell = 4;
	          int biginCell1 = 4;
	          String[] val = new String[]{"没问题","有些问题","问题较严重","不了解","未填"};
	          for(int i = 0 ; i < val.length ; i ++ ){
	          	sheet.addMergedRegion(new Region(beginrow+1, (short) beginCell, beginrow+1, (short) (beginCell+1)));
	          	HSSFCell ce7 = row5.createCell((short) beginCell);
	          	ce7.setCellValue(val[i]); // 表格的第一行显示的数据
	          	ce7.setCellStyle(cellStyle2); // 样式，居中  
	              beginCell += 2;
	          }
	          String[] val1 = new String[]{"票数","百分比","票数","百分比","票数","百分比","票数","百分比","票数","百分比",};
	          
	          for (int i = 0; i < val1.length; i++) {
	          	sheet.addMergedRegion(new Region(beginrow+2, (short) biginCell1, beginrow+2, (short) (biginCell1)));
	          	HSSFCell ce7 = row6.createCell((short) biginCell1);
	          	ce7.setCellValue(val1[i]); // 表格的第一行显示的数据
	          	ce7.setCellStyle(cellStyle2); // 样式，居中  
	          	biginCell1 += 1;
	  		}
	          
	          Map<Integer, DePj> map = (Map<Integer, DePj>) mapResult.get("df");
	          for(int i = 0; i < 7 ; i ++){
	          	int l = 0;
	          	HSSFRow row7= sheet.createRow((short) status);
	              HSSFRow row8= sheet.createRow((short) status+1);
	              HSSFRow row9 = null;
	              if(i == 6){
	              	 row9= sheet.createRow((short) status+2);
	              	sheet.addMergedRegion(new Region(status, (short) 0, status+2, (short) (0)));
	              }else{
	              	sheet.addMergedRegion(new Region(status, (short) 0, status+1, (short) (0)));
	              }
	              HSSFCell ce7 = row7.createCell((short) l);
	              int a = i *2 + 1;
	          	ce7.setCellValue(map.get(a).getType()); // 表格的第一行显示的数据
	          	ce7.setCellStyle(cellStyle2); // 样式，居中  
	              l+=1;
	              
	          	 sheet.addMergedRegion(new Region(status, (short) l, status, (short) (l+2)));
	               HSSFCell ce8 = row7.createCell((short) l);
	               ce8.setCellValue(map.get(a).getMsg()); // 表格的第一行显示的数据
	           	 ce8.setCellStyle(cellStyle2); // 样式，居中  
	           	
	           	sheet.addMergedRegion(new Region(status+1, (short) l, status+1, (short) (l+2)));
	              HSSFCell ce9 = row8.createCell((short) l);
	              ce9.setCellValue(map.get(a+1).getMsg()); // 表格的第一行显示的数据
	          	ce9.setCellStyle(cellStyle2); // 样式，居中
	          	
	          	if(i == 6){
	          		sheet.addMergedRegion(new Region(status+2, (short) l, status+2, (short) (l+2)));
	          		HSSFCell ce10 = row9.createCell((short) l);
	                  ce10.setCellValue(map.get(a+2).getMsg()); // 表格的第一行显示的数据
	              	ce10.setCellStyle(cellStyle2); // 样式，居中
	          	}
	          	l+=3;
	          	
	          	
	          	
	          	
	          	
	          	for(int j = 0; j < 10 ; j ++){
	          		sheet.addMergedRegion(new Region(status, (short) l, status, (short) (l)));
	                  HSSFCell ce10 = row7.createCell((short) l);
	                  if(j == 0){
	                  	ce10.setCellValue(map.get(a).getP1()); // 表格的第一行显示的数据
	                  }
	                  if(j == 1){
	                 	 ce10.setCellValue(map.get(a).getP1Bfb()+"%"); // 表格的第一行显示的数据
	                 }
	                  if(j == 2){
	                 	 ce10.setCellValue(map.get(a).getP2()); // 表格的第一行显示的数据
	                 }
	                  if(j == 3){
	                 	 ce10.setCellValue(map.get(a).getP2Bfb()+"%"); // 表格的第一行显示的数据
	                 }
	                  if(j == 4){
	                 	 ce10.setCellValue(map.get(a).getP3()); // 表格的第一行显示的数据
	                 }
	                  if(j == 5){
	                 	 ce10.setCellValue(map.get(a).getP3Bfb()+"%"); // 表格的第一行显示的数据
	                 }
	                  if(j == 6){
	                 	 ce10.setCellValue(map.get(a).getP4()); // 表格的第一行显示的数据
	                 }
	                  if(j == 7){
	                 	 ce10.setCellValue(map.get(a).getP4Bfb()+"%"); // 表格的第一行显示的数据
	                 }
	                  if(j == 8){
	                 	 ce10.setCellValue(map.get(a).getP5()); // 表格的第一行显示的数据
	                 }
	                  if(j == 9){
	                 	 ce10.setCellValue(map.get(a).getP5BFb()+"%"); // 表格的第一行显示的数据
	                 }
	              	ce10.setCellStyle(cellStyle2); // 样式，居中
	              	
	              	
	              	sheet.addMergedRegion(new Region(status+1, (short) l, status+1, (short) (l)));
	                  HSSFCell ce11 = row8.createCell((short) l);
	                  if(j == 0){
	                  	ce11.setCellValue(map.get(a+1).getP1()); // 表格的第一行显示的数据
	                  }
	                  if(j == 1){
	                  	ce11.setCellValue(map.get(a+1).getP1Bfb()+"%"); // 表格的第一行显示的数据
	                 }
	                  if(j == 2){
	                  	ce11.setCellValue(map.get(a+1).getP2()); // 表格的第一行显示的数据
	                 }
	                  if(j == 3){
	                  	ce11.setCellValue(map.get(a+1).getP2Bfb()+"%"); // 表格的第一行显示的数据
	                 }
	                  if(j == 4){
	                  	ce11.setCellValue(map.get(a+1).getP3()); // 表格的第一行显示的数据
	                 }
	                  if(j == 5){
	                  	ce11.setCellValue(map.get(a+1).getP3Bfb()+"%"); // 表格的第一行显示的数据
	                 }
	                  if(j == 6){
	                  	ce11.setCellValue(map.get(a+1).getP4()); // 表格的第一行显示的数据
	                 }
	                  if(j == 7){
	                  	ce11.setCellValue(map.get(a+1).getP4Bfb()+"%"); // 表格的第一行显示的数据
	                 }
	                  if(j == 8){
	                  	ce11.setCellValue(map.get(a+1).getP5()); // 表格的第一行显示的数据
	                 }
	                  if(j == 9){
	                  	ce11.setCellValue(map.get(a+1).getP5BFb()+"%"); // 表格的第一行显示的数据
	                 }
	                  ce11.setCellStyle(cellStyle2); // 样式，居中
	              	
	                  if(i == 6){
	                  	sheet.addMergedRegion(new Region(status+2, (short) l, status+2, (short) (l)));
	                      HSSFCell ce12 = row9.createCell((short) l);
	                      if(j == 0){
	                      	ce12.setCellValue(map.get(a+2).getP1()); // 表格的第一行显示的数据
	                      }
	                      if(j == 1){
	                      	ce12.setCellValue(map.get(a+2).getP1Bfb()+"%"); // 表格的第一行显示的数据
	                     }
	                      if(j == 2){
	                      	ce12.setCellValue(map.get(a+2).getP2()); // 表格的第一行显示的数据
	                     }
	                      if(j == 3){
	                      	ce12.setCellValue(map.get(a+2).getP2Bfb()+"%"); // 表格的第一行显示的数据
	                     }
	                      if(j == 4){
	                      	ce12.setCellValue(map.get(a+2).getP3()); // 表格的第一行显示的数据
	                     }
	                      if(j == 5){
	                      	ce12.setCellValue(map.get(a+2).getP3Bfb()+"%"); // 表格的第一行显示的数据
	                     }
	                      if(j == 6){
	                      	ce12.setCellValue(map.get(a+2).getP4()); // 表格的第一行显示的数据
	                     }
	                      if(j == 7){
	                      	ce12.setCellValue(map.get(a+2).getP4Bfb()+"%"); // 表格的第一行显示的数据
	                     }
	                      if(j == 8){
	                      	ce12.setCellValue(map.get(a+2).getP5()); // 表格的第一行显示的数据
	                     }
	                      if(j == 9){
	                      	ce12.setCellValue(map.get(a+2).getP5BFb()+"%"); // 表格的第一行显示的数据
	                     }
	                      ce12.setCellStyle(cellStyle2); // 样式，居中
	              	}
	              	
	              	
	              	
	              	
	              	
	              	l+=1;
	          	}
	          	
	          	
	           	 
	              status +=2;
	          }
	          
	          
	          
	          
	         
	          HSSFRow row21= sheet.createRow((short) status+1);
	          HSSFRow row22= sheet.createRow((short) status+2);
	          sheet.addMergedRegion(new Region(status+1, (short) 0, status+2, (short) (3)));
	          HSSFCell ce1l11 = row21.createCell((short) 0);
	          ce1l11.setCellValue(map.get(16).getType());
	          ce1l11.setCellStyle(cellStyle2);
	          
	          int l1 = 4;
	          
	          String val2[] = new String[]{"好","较好","一般","差","未填"};
	          for(int i = 0; i < val2.length ; i++){
	          	sheet.addMergedRegion(new Region(status+1, (short) l1, status+1, (short) (l1+1)));
	              HSSFCell ce21 = row21.createCell((short) l1);
	              ce21.setCellValue(val2[i]);
	              ce21.setCellStyle(cellStyle2);
	              l1+=2;
	          }
	          int l2 = 4;
	          for(int j = 0 ; j< 10 ; j ++ ){
	        		sheet.addMergedRegion(new Region(status+2, (short) l2, status+2, (short) (l2)));
		              HSSFCell ce22 = row22.createCell((short) l2);
		              if(j == 0){
		            	  ce22.setCellValue(map.get(16).getP1());
		              }
		              if(j == 1){
		            	  ce22.setCellValue(map.get(16).getP1Bfb());
		              }
		              if(j == 2){
		            	  ce22.setCellValue(map.get(16).getP2());
		              }
		              if(j == 3){
		            	  ce22.setCellValue(map.get(16).getP2Bfb());
		              }
		              if(j == 4){
		            	  ce22.setCellValue(map.get(16).getP3());
		              }
		              if(j == 5){
		            	  ce22.setCellValue(map.get(16).getP3Bfb());
		              }
		              if(j == 6){
		            	  ce22.setCellValue(map.get(16).getP4());
		              }
		              if(j == 7){
		            	  ce22.setCellValue(map.get(16).getP4Bfb());
		              }
		              if(j == 8){
		            	  ce22.setCellValue(map.get(16).getP5());
		              }
		              if(j == 9){
		            	  ce22.setCellValue(map.get(16).getP5BFb());
		              }
		              ce22.setCellStyle(cellStyle2);
		              l2+=1;
	          }
	          
	          
	          status +=3;
	          /* l1 = 4;
	          for(int i = 0 ; i < 10 ; i ++){
	          	sheet.addMergedRegion(new Region(status+2, (short) l1, status+2, (short) (l1)));
	              HSSFCell ce22= row22.createCell((short) l1);
	              ce22.setCellValue(val2[i]);
	              ce22.setCellStyle(cellStyle2);
	              l1+=1;
	          }*/
	          
	          
	          
	        
	   /*   
	  		//设定最后一行备注
	  		sheet.addMergedRegion(new Region(status, (short) 0, status+1, (short) (excel.getHeader().size()-1)));
	  		HSSFRow hssfRowDown = sheet.createRow((short) status);
	  		HSSFCell hssfCell = hssfRowDown.createCell((short) 0);
	  		hssfCell.setCellValue("注：按当前投票数进行统计。"); // 表格的第一行显示的数据
	  		hssfCell.setCellStyle(cellStyle5); // 样式，居中   
	  		  status +=2;
	  		//最后一刚
	  				
	  			*/	
	      }
	      sheet.addMergedRegion(new Region(status, (short) 0, status + 3, (short) (13)));
			HSSFRow hssfRadomRow = sheet.createRow((short) status);
			HSSFCell hssfRadomCell = hssfRadomRow.createCell((short) 0);
			hssfRadomCell.setCellValue("当前总票数："+excel.getMap().get("ALLRADOM")+",已投票数:"+excel.getMap().get("OLDRADOM")+",未投票数："+excel.getMap().get("NEWRADOM")); // 表格的第一行显示的数据
			hssfRadomCell.setCellStyle(cellStyle5); // 样式，居中   
	
		return workbook;

	}
	
	/**
	 * 取得填充了数据的工作簿
	 * 
	 * @param excel
	 *            ExcelModel Excel表的模型对象
	 * @return HSSFWorkbook 工作簿对象
	 */
	private HSSFWorkbook getInitMzpcWorkbook5(ExcelModel excel) {
		// 创建新的Excel 工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 在Excel工作簿中建一工作表
		HSSFSheet sheet = null;
		String sheetName = excel.getSheetName();
		
		if (sheetName != null)
			sheet = workbook.createSheet(sheetName);
		else
			sheet = workbook.createSheet();
		//设定列宽
		sheet.setDefaultColumnWidth((short) 12);
		/*
		 * 设置合并单元格 //sheet.addMergedRegion(new Region(1,(short)0,1,(short)2));
		 */

		// 设置表头字体
		HSSFFont font_h = workbook.createFont();
		font_h.setFontName("宋体");
		font_h.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font_h.setFontHeightInPoints((short) 20);
		
		
		// 二级标题字体
		HSSFFont font_h1 = workbook.createFont();
		font_h1.setFontName("宋体");
		font_h1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font_h1.setFontHeightInPoints((short) 10);
		
		// 二级标题字体
		HSSFFont font_h2 = workbook.createFont();
		font_h2.setFontName("宋体");
		font_h2.setFontHeightInPoints((short) 10);
		
		// 标题样式
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直   
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
		cellStyle.setFont(font_h);
		//二级标题样式
		  HSSFCellStyle cellStyle1 = workbook.createCellStyle();
	      cellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直   
	      cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水
	      cellStyle1.setFont(font_h1);
	      //三级标题样式
		  HSSFCellStyle cellStyle2 = workbook.createCellStyle();
	      cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直   
	      cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水
	      cellStyle2.setFont(font_h2);
	      
	    //三级标题样式
		  HSSFCellStyle cellStyle4 = workbook.createCellStyle();
		  cellStyle4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直   
		  cellStyle4.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 水
		  cellStyle4.setFont(font_h2);
		  
		  
		//三级标题样式
		  HSSFCellStyle cellStyle5 = workbook.createCellStyle();
		  cellStyle5.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直   
		  cellStyle5.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水
		  cellStyle5.setFont(font_h2);
		  
		
	      ArrayList<String> header = excel.getHeader();
		// 在索引0的位置创建行（最顶端的行）
	      int status = 0;
	      ArrayList<Map<Integer, Object>> list = excel.getData();
	 	 HSSFRow row = sheet.createRow((short) status);
	  		sheet.addMergedRegion(new Region(status, (short) 0, status+1, (short) (12)));
	  		HSSFCell ce = row.createCell((short) 0);
	  		ce.setCellValue(sheetName + "("+BHUtil.getTime_year()+")"); // 表格的第一行显示的数据
	  		cellStyle.setFont(font_h);
	          ce.setCellStyle(cellStyle); // 样式，居中   
	          status +=2;
	          
	          HSSFRow row1 = sheet.createRow((short) status);
		  		
	          sheet.addMergedRegion(new Region(status, (short) 0, status, (short) (5)));
		  		HSSFCell ce1 = row1.createCell((short) 0);
		  		ce1.setCellValue("被测评干部的基本情况"); // 表格的第一行显示的数据
		  		ce1.setCellStyle(cellStyle1); // 样式，居中   
		  		
		  		 sheet.addMergedRegion(new Region(status, (short) 6, status, (short) (9)));
			  		HSSFCell ce2 = row1.createCell((short) 6);
			  		ce2.setCellValue("对任用该干部的看法"); // 表格的第一行显示的数据
			  		ce2.setCellStyle(cellStyle1); // 样式，居中   
			  		 sheet.addMergedRegion(new Region(status, (short) 10, status, (short) (12)));
				  		HSSFCell ce3 = row1.createCell((short) 10);
				  		ce3.setCellValue("是否存在拉票、跑官、买官行为"); // 表格的第一行显示的数据
				  		ce3.setCellStyle(cellStyle1); // 样式，居中
				  		
				  	status ++;
				  	String[] values = new String[]{"姓名","性别","出生年月","原任职务","现任职务","任职时间","满意","基本满意","不满意","不了解","存在","不存在","不了解"};
				  	HSSFRow row2 = sheet.createRow((short) status);
				  	for(int i = 0 ;i < values.length ; i ++){
					  		HSSFCell ce4 = row2.createCell((short) i);
					  		ce4.setCellValue(values[i]); // 表格的第一行显示的数据
					  		ce4.setCellStyle(cellStyle2); // 样式，居中  
				  	}
					status ++;
					
					for(Map<Integer, Object> map : list){
						HSSFRow row3 = sheet.createRow((short) status);
						for(int i = 0 ; i < 13 ; i ++){
							HSSFCell ce4 = row3.createCell((short) i);
					  		if(i > 5){
					  			ce4.setCellValue(map.get(i).toString()+"票"); // 表格的第一行显示的数据
					  		}else{
					  			ce4.setCellValue(map.get(i).toString()); // 表格的第一行显示的数据
					  		}
					  		ce4.setCellStyle(cellStyle2); // 样式，居中  
						}
						
						status++;
						
					}
					
	   /*   	
	    * 
	  		//设定最后一行备注
	  		sheet.addMergedRegion(new Region(status, (short) 0, status+1, (short) (excel.getHeader().size()-1)));
	  		HSSFRow hssfRowDown = sheet.createRow((short) status);
	  		HSSFCell hssfCell = hssfRowDown.createCell((short) 0);
	  		hssfCell.setCellValue("注：按当前投票数进行统计。"); // 表格的第一行显示的数据
	  		hssfCell.setCellStyle(cellStyle5); // 样式，居中   
	  		  status +=2;
	  		//最后一刚
	  				
	  			*/	
	      
	      sheet.addMergedRegion(new Region(status, (short) 0, status + 3, (short) (12)));
			HSSFRow hssfRadomRow = sheet.createRow((short) status);
			HSSFCell hssfRadomCell = hssfRadomRow.createCell((short) 0);
			hssfRadomCell.setCellValue("当前总票数："+excel.getMap().get("ALLRADOM")+",已投票数:"+excel.getMap().get("OLDRADOM")+",未投票数："+excel.getMap().get("NEWRADOM")); // 表格的第一行显示的数据
			hssfRadomCell.setCellStyle(cellStyle5); // 样式，居中   
	
		return workbook;

	}
}

