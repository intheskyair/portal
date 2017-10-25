package com.cudatec.render.util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

/**
 * 文件流输出支持类，实现跳过某些字节的下载
 * 由于要支持断点下载，不能直接给客户端返回 File 对象
 */
public class FileStreamingOutput implements StreamingOutput {
	private RandomAccessFile raf = null;

	public FileStreamingOutput(File file, long start) {
		try {
			raf = new RandomAccessFile(file, "r");
			raf.seek(start);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void write(OutputStream out) throws IOException, WebApplicationException {
		int nRead = 0;
		byte[] buf = new byte[4096];
		while ((nRead = raf.read(buf)) != -1) {
			out.write(buf, 0, nRead);
		}
		
		if (raf != null) {
			raf.close();
		}
	}
}
