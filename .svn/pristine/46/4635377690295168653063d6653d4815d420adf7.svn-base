package com.zqkj.bsm.system.action.util;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.struts2.ServletActionContext;

public class FileUtil{
	private static final int BUFFER_SIZE = 16 * 1024;

	public static void uploadFile(File src, File dst) throws IOException {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			while (in.read(buffer) > 0) {
				out.write(buffer);
			}
		} finally {
			if (null != in) {
				in.close();
			}
			if (null != out) {
				out.close();
			}
		}
	}

	public static String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}

	public static void makeDir(String directory) {
		File dir = new File(directory);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
	}

	/**
	 * 图片缩放处理
	 * @param imageFile �?要缩放的图片
	 * @param fileName 缩放后保存的文件�?
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
    public static synchronized void zoom(File imageFile, String fileName) throws Exception {
		try {
			// 缩略图存放路�?
			File todir = new File(ServletActionContext.getServletContext().getRealPath("/upload/thumbs"));
			if (!todir.exists()) {
				todir.mkdir();
			}
			File sImg = new File(todir, fileName);
			BufferedImage Bi = ImageIO.read(imageFile);
			// 假设图片�? �? �?大为130 80,使用默认缩略算法
			Image Itemp = Bi.getScaledInstance(130, 80, Bi.SCALE_DEFAULT);
			double Ratio = 0.0;
			if ((Bi.getHeight() > 130) || (Bi.getWidth() > 80)) {
				if (Bi.getHeight() > Bi.getWidth()) {
					Ratio = 80.0 / Bi.getHeight();
				} else {
					Ratio = 130.0 / Bi.getWidth();
				}
				AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(Ratio, Ratio), null);
				Itemp = op.filter(Bi, null);
			}
			ImageIO.write((BufferedImage) Itemp, "jpg", sImg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取随机文件�?
	 * @param fileName
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String generateFileName(String fileName) throws UnsupportedEncodingException {
		DateFormat format = new SimpleDateFormat("yyMMddHHmmss");
		String formatDate = format.format(new Date());
		String extension = fileName.substring(fileName.lastIndexOf("."));
		fileName = new String(fileName.getBytes("ISO8859-1"), "UTF-8");
		return fileName + "_" + formatDate + new Random().nextInt(10000) + extension;
	}
}
