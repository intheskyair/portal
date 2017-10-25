package com.zqkj.bsm.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.zqkj.bsm.downmodel.beanfactory.ModelFactory;

public class ExcelWriter {
	private final String SUFFIX_2003 = "XLS";
	private final String SUFFIX_2007 = "XLSX";
	
	public boolean write(String suffix,OutputStream out,String tableName){
		if(suffix.toUpperCase().equals(SUFFIX_2003))
			return writeExcel2003(out,tableName);
		else if(suffix.toUpperCase().equals(SUFFIX_2007))
			return writeExcel2007(out,tableName);
		else
			return false;
	}
	
	private boolean writeExcel2003(OutputStream out,String tableName){
		HSSFWorkbook workbook = null;
		Map<String,String> m = ModelFactory.getModelBean(tableName);
		String table = m.get(ModelFactory.stringFirstUpper(tableName)); 
		try {
			workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet(m.get(table));
			//sheet.setColumnHidden(0, true);
			HSSFRow row1 = sheet.createRow(0);
			row1.setZeroHeight(true);
			HSSFRow row2 = sheet.createRow(1);
			/*List<Map.Entry<String, String>> entrys = new ArrayList<Map.Entry<String, String>>(m.entrySet());
			for (int i = 0; i < entrys.size(); i++) {
				Map.Entry<String, String> entry = entrys.get(i);
				String key = entry.getKey();
				String value = entry.getValue();
				System.out.println(entry.toString());
				if(!ModelFactory.stringFirstUpper(tableName).equals(key) && !table.equals(key)){
					HSSFCell cell1 = row1.createCell(i);
					HSSFCell cell2 = row2.createCell(i);
					cell1.setCellValue(key);
					cell2.setCellValue(value);
				}
			}*/
			
			Iterator<String> keys = m.keySet().iterator();
			int col = 0;
			while(keys.hasNext()){
				String key = keys.next();
				if(!ModelFactory.stringFirstUpper(tableName).equals(key) && !table.equals(key)){
					HSSFCell cell1 = row1.createCell(col);
					HSSFCell cell2 = row2.createCell(col);
					cell1.setCellValue(key);
					cell2.setCellValue(m.get(key));
					sheet.autoSizeColumn(col); 
					col++;
				}
			}
			workbook.write(out);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean writeExcel2007(OutputStream out,String tableName){
		XSSFWorkbook workbook = null;
		Map<String,String> m = ModelFactory.getModelBean(tableName);
		String table = m.get(ModelFactory.stringFirstUpper(tableName)); 
		try {
			workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet(m.get(table));
			//sheet.setColumnHidden(0, true);
			XSSFRow row1 = sheet.createRow(0);
			row1.setZeroHeight(true);
			XSSFRow row2 = sheet.createRow(1);
			Iterator<String> keys = m.keySet().iterator();
			int col = 0;
			while(keys.hasNext()){
				String key = keys.next();
				if(!ModelFactory.stringFirstUpper(tableName).equals(key) && !table.equals(key)){
					XSSFCell cell1 = row1.createCell(col);
					XSSFCell cell2 = row2.createCell(col);
					cell1.setCellValue(key);
					cell2.setCellValue(m.get(key));
					sheet.autoSizeColumn(col); 
					col++;
				}
			}
			workbook.write(out);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
	}
}
