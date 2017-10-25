package com.zqkj.bsm.system.action.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.struts2.ServletActionContext;

public class PropertiesUtil{
	/**
	 * 获得指定key的�??
	 * @param key
	 * @return
	 */
	public static String getValue(String fileName, String key) {
		ResourceBundle cache = ResourceBundle.getBundle(fileName);
		return cache.getString(key);
	}

	/**
	 * 读取properties的全部信�?
	 * @param filePath 文件路径
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> readProperties(String filePath) {
		Properties props = new Properties();
		Map<String, String> map = new HashMap<String, String>();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(filePath));
			props.load(in);
			Enumeration en = props.propertyNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				String Property = props.getProperty(key);
				map.put(key, Property);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 写入properties信息
	 * @param filePath 文件路径
	 * @param parameterName 属�?�名
	 * @param parameterValue 属�?��??
	 */
	public static void writeProperties(String filePath, String parameterName, String parameterValue) {
		Properties prop = new Properties();
		try {
			InputStream fis = new FileInputStream(filePath);
			// 从输入流中读取属性列表（键和元素对）
			prop.load(fis);
			// 调用 Hashtable 的方�? put。使�? getProperty 方法提供并行性�??
			// 强制要求为属性的键和值使用字符串。返回�?�是 Hashtable 调用 put 的结果�??
			OutputStream fos = new FileOutputStream(filePath);
			prop.setProperty(parameterName, parameterValue);
			// 以�?�合使用 load 方法加载�? Properties 表中的格式，
			// 将此 Properties 表中的属性列表（键和元素对）写入输出�?
			prop.store(fos, "");
		} catch (IOException e) {
			System.err.println("Visit " + filePath + " for updating " + parameterName + " value error");
		}
	}

	/**
	 * 通过去匹配Map的键取出对应的�??
	 * @param key
	 * @param map
	 * @return
	 */
	public static String equalsKeyGetValue(String key, Map<String, String> map) {
		String value = "";
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (key.equals(entry.getKey())) {
				value = entry.getValue();
			}
		}
		return value;
	}

	public static String isExist(String wordFileName) {
		Map<String, String> map = readProperties(ServletActionContext.getServletContext().getRealPath("") + "/WEB-INF/classes/file.Properties");
		if (map.containsValue(wordFileName)) {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				if (wordFileName.equals(entry.getValue())) return (String) entry.getKey() + ".html";
			}
		}
		return null;
	}
}
