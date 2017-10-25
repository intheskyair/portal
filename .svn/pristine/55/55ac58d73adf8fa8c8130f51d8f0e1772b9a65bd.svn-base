package com.zqkj.bsm.system.action.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取目录及子目录下指定文件名的路�?, 返回�?个List
 */

public class FileViewer{

	/**
	 * @param path 文件路径
	 * @param suffix 后缀�?, 为空则表示所有文�?
	 * @param isdepth 是否遍历子目�?
	 * @return list
	 */
	public static List<String> getListFiles(String path, String suffix, boolean isdepth) {
		List<String> lstFileNames = new ArrayList<String>();
		File file = new File(path);
		return FileViewer.listFile(lstFileNames, file, suffix, isdepth);
	}

	private static List<String> listFile(List<String> lstFileNames, File f, String suffix, boolean isdepth) {
		// 若是目录, 采用递归的方法遍历子目录
		if (f.isDirectory()) {
			File[] t = f.listFiles();
			for (int i = 0; i < t.length; i++) {
				if (isdepth || t[i].isFile()) {
					listFile(lstFileNames, t[i], suffix, isdepth);
				}
			}
		} else {
			String filePath = f.getName();
			if (!suffix.equals("")) {
				int begIndex = filePath.lastIndexOf("."); // �?后一�?.(即后�?名前面的.)的索�?
				String tempsuffix = "";
				if (begIndex != -1) {
					tempsuffix = filePath.substring(begIndex + 1, filePath.length());
					if (tempsuffix.equals(suffix)) {
						lstFileNames.add(filePath);
					}
				}
			} else {
				int begIndex = filePath.lastIndexOf("."); // �?后一�?.(即后�?名前面的.)的索�?
				String tempsuffix = "";
				if (begIndex != -1) {
					tempsuffix = filePath.substring(begIndex + 1, filePath.length());
					if (!"doc".equalsIgnoreCase(tempsuffix)) {
						lstFileNames.add(filePath);
					}
				}
			}
		}
		return lstFileNames;
	}
}
