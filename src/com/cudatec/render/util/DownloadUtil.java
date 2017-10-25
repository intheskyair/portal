package com.cudatec.render.util;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;

/**
 * 文件断点下载工具类
 */
public class DownloadUtil {
	/**
	 * 获得 HttpHeader 中的断点位置
	 * @param hh 客户端下载请求的头部信息
	 * @return 文件开始下载的字节位置
	 */
	public static long getStart(HttpHeaders hh) {
		MultivaluedMap<String, String> values = hh.getRequestHeaders();
		if (values.containsKey("Range")) {
			String range = values.get("Range").get(0);
			// eg. "Range: bytes=12345-"
			// get 12345
			String start = range.substring(range.indexOf("=") + 1, range.length()-1);
			return Long.parseLong(start);
		} else {
			return 0;
		}
	}
}
