package com.zqkj.bsm.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	private final String SUFFIX_2003 = "XLS";
	private final String SUFFIX_2007 = "XLSX";
	
	public boolean read(String suffix,InputStream in,String tableName){
		if(suffix.toUpperCase().equals(SUFFIX_2003))
			return readExcel2003(in,tableName);
		else if(suffix.toUpperCase().equals(SUFFIX_2007))
			return readExcel2007(in,tableName);
		else
			return false;
	}
	
	private boolean readExcel2003(InputStream in,String tableName){
		HSSFWorkbook workbook = null;
		StringBuffer sb = new StringBuffer();
		ArrayList<String> sqls = new ArrayList<String>();
		try {
			workbook = new HSSFWorkbook(in);
			HSSFSheet sheet = workbook.getSheetAt(0);
			int rowNum = sheet.getLastRowNum();
			int finalCellsNum = 0;
			for(int i = 0; i <= rowNum; i++){
				HSSFRow rows = sheet.getRow(i);
				int col = rows.getPhysicalNumberOfCells();
				if(i == 0){
					sb.append("insert into ").append(tableName).append("(");
					finalCellsNum = col;
				}
				StringBuffer sb1 = new StringBuffer();
				for(int j = 0; j < finalCellsNum; j++){
					//System.out.println("(第"+i+"行,第"+j+"列)的值为："+getCellFormatValue(rows.getCell(j)));
					if(i == 0 && (j+1) < col){
						sb.append(getCellFormatValue(rows.getCell(j))).append(",");
					}else if(i == 0 && (j+1) == col){
						sb.append(getCellFormatValue(rows.getCell(j)));
					}else if(i > 1){
						if((j+1) < finalCellsNum)
							sb1.append("'").append(getCellFormatValue(rows.getCell(j))).append("',");
						else
							sb1.append("'").append(getCellFormatValue(rows.getCell(j))).append("'");
					}
				}
				if(i > 1 && !sb1.equals(""))
					sqls.add(sb.toString()+") values("+sb1.toString()+")");
			}
			//System.out.println(sqls);
			return TableHelpUtil.getInstance().batchSqls(sqls);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean readExcel2007(InputStream in,String tableName){
		XSSFWorkbook workbook = null;
		StringBuffer sb = new StringBuffer();
		ArrayList<String> sqls = new ArrayList<String>();
		try {
			workbook = new XSSFWorkbook(in);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int rowNum = sheet.getLastRowNum();
			int finalCellsNum = 0;
			for(int i = 0; i <= rowNum; i++){
				XSSFRow rows = sheet.getRow(i);
				int col = rows.getPhysicalNumberOfCells();
				if(i == 0){
					sb.append("insert into ").append(tableName).append("(");
					finalCellsNum = col;
				}
				StringBuffer sb1 = new StringBuffer();
				for(int j = 0; j < finalCellsNum; j++){
					if(i == 0 && (j+1) < col){
						sb.append(getCellFormatValue(rows.getCell(j))).append(",");
					}else if(i == 0 && (j+1) == col){
						sb.append(getCellFormatValue(rows.getCell(j)));
					}else if(i>1){
//						System.out.println("(第"+i+"行,第"+j+"列)的值为："+getCellFormatValue(rows.getCell(j)));
						if((j+1) < finalCellsNum)
							sb1.append("'").append(getCellFormatValue(rows.getCell(j))).append("',");
						else
							sb1.append("'").append(getCellFormatValue(rows.getCell(j))).append("'");
					}
				}
				if(i > 1 && !sb1.equals(""))
					sqls.add(sb.toString()+") values("+sb1.toString()+")");
			}
//			System.out.println(sqls);
			return TableHelpUtil.getInstance().batchSqls(sqls);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
    /**
     * 根据HSSFCell类型设置数据
     * @param cell
     * @return
     */
    private String getCellFormatValue(HSSFCell cell) {
        String cellvalue = "";
        if (cell != null && !"null".equals(cell)) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
            // 如果当前Cell的Type为NUMERIC
            case HSSFCell.CELL_TYPE_NUMERIC:
            case HSSFCell.CELL_TYPE_FORMULA: {
                // 判断当前的cell是否为Date
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // 如果是Date类型则，转化为Data格式
                    
                    //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                    //cellvalue = cell.getDateCellValue().toLocaleString();
                    
                    //方法2：这样子的data格式是不带带时分秒的：2011-10-12
                    Date date = cell.getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    cellvalue = sdf.format(date);
                    
                }
                // 如果是纯数字
                else {
                	// 取得当前Cell的数值
                	DecimalFormat df = new DecimalFormat("#.##"); 
                    cellvalue = df.format(cell.getNumericCellValue());
                }
                break;
            }
            // 如果当前Cell的Type为STRIN
            case HSSFCell.CELL_TYPE_STRING:
                // 取得当前的Cell字符串
                cellvalue = cell.getRichStringCellValue().getString();
                break;
            // 默认的Cell值
            default:
                cellvalue = "";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;

    }
    
    /**
     * 根据XSSFCell类型设置数据
     * @param cell
     * @return
     */
    private String getCellFormatValue(XSSFCell cell) {
        String cellvalue = "";
        if (cell != null && !"null".equals(cell)) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
            // 如果当前Cell的Type为NUMERIC
            case HSSFCell.CELL_TYPE_NUMERIC:
            case HSSFCell.CELL_TYPE_FORMULA: {
                // 判断当前的cell是否为Date
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // 如果是Date类型则，转化为Data格式
                    
                    //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                    //cellvalue = cell.getDateCellValue().toLocaleString();
                    
                    //方法2：这样子的data格式是不带带时分秒的：2011-10-12
                    Date date = cell.getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    cellvalue = sdf.format(date);
                    
                }
                // 如果是纯数字
                else {
                    // 取得当前Cell的数值
                	DecimalFormat df = new DecimalFormat("#.##"); 
                    cellvalue = df.format(cell.getNumericCellValue());
                }
                break;
            }
            // 如果当前Cell的Type为STRIN
            case HSSFCell.CELL_TYPE_STRING:
                // 取得当前的Cell字符串
                cellvalue = cell.getRichStringCellValue().getString();
                break;
            // 默认的Cell值
            default:
                cellvalue = "";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }
    
    /*private StringBuffer pjInsertSqlHead(String tableName,StringBuffer sb){
    	List<Map<String,Object>> tableInfo = TableHelpUtil.getInstance().getMysqlTableColumnInfo(tableName);
    	if(sb != null){
    		for(Map<String,Object> table : tableInfo){
    			if(table.get("EXTRA") != null && "auto_increment".equals(table.get("EXTRA").toString()))
    				continue;
    			else{
    				sb.append(table.get("COLUMN_NAME").toString()).append(",");
    			}
    		}
    	}
    	return sb;
    }*/
}
