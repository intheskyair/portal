package com.cudatec.render.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

/**
 * 哈希计算工具类
 */
public class HashUtil {
	/**
	 * 根据输入的文件计算哈希值
	 * @param file 输入文件
	 * @return 文件的SHA-1哈希值
	 */
	public static String calculate(File file) {
		if (file.exists()) {
			int read = 0;
			byte[] bytes = new byte[8192];
			StringBuilder sb = new StringBuilder();
			MessageDigest md = null;
			
			try {
				md = MessageDigest.getInstance("SHA1");
				InputStream in = new FileInputStream(file);
				while ((read = in.read(bytes)) != -1) {
					md.update(bytes, 0, read);
				}
				in.close();
				
				byte[] mdbytes = md.digest();
				for (int i = 0; i < mdbytes.length; i++) {
					sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
				}
				return sb.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static String calculate(String file) {
		return calculate(new File(file));
	}
}
