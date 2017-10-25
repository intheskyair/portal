package com.cudatec.render.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.Arrays;

import org.apache.log4j.Logger;

/**
 * 文件上传的工具类
 */
public class UploadUtil {
	protected static Logger log = Logger.getLogger(UploadUtil.class);

	/**
	 * 合并分块的子文件
	 * 
	 * @param dir
	 *            分块文件所在的磁盘文件夹
	 * @param sha1
	 *            分块文件合并后的哈希值
	 * @return 是否合并成功
	 */
	public static boolean combine(File dir, String sha1) 
	{
		boolean return_fg = false;
		if (dir.exists()) {
			try {
				// 需要合并的文件
				File[] list = dir.listFiles();
				// 排序
				Arrays.sort(list);
				File folder = new File(PathUtil.getfolderFromHash(sha1)); // 大文件，先保存在/upload中
				if (!folder.exists()) 
				{
					folder.mkdirs();
					log.info("大文件文件夹不存在，直接创建="+folder);
				}
				File dest = new File(PathUtil.getPathFromHash(sha1));
				// FileOutputStream writer = new FileOutputStream(dest);
				// // 将分割的文件以文件流的方式读入，并写入目标文件
				// int read = 0;
				// byte[] bt = new byte[8192];
				// for (int i = 0; i < list.length; i++) {
				// FileInputStream reader = new FileInputStream(list[i]);
				// while ((read = reader.read(bt)) != -1) {
				// writer.write(bt, 0, read);
				// }
				// reader.close();
				// }
				// writer.close();

				// 用于解决多个线程同时写入一个文件的并发问题
				// 同一个用户上传多个订单，若多个订单中含有相同的素材，则及有可能出现同时写入情况。
				// By 朱川
				// 2014-07-31
				// CODE_BLOCK_OF 对文件加锁
				FileOutputStream writer = new FileOutputStream(dest);
				FileChannel fc = writer.getChannel();
				FileLock fl = fc.tryLock();
				if (fl.isValid()) {
					// 将分割的文件以文件流的方式读入，并写入目标文件
					int read = 0;
					byte[] bt = new byte[8192];
					for (int i = 0; i < list.length; i++) {
						FileInputStream reader = new FileInputStream(list[i]);
						while ((read = reader.read(bt)) != -1) {
							writer.write(bt, 0, read);
						}
						reader.close();
					}

					// delete current directory
					for (File f : list) {
						f.delete();
					}
					dir.delete();
				}
				fl.release();
				writer.close(); // END OF CODE_BLOCK_OF

				// // delete current directory
				// for (File f : list) {
				// f.delete();
				// }
				// dir.delete();
				return_fg= true;
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			catch (OverlappingFileLockException e) 
			{
				//e.printStackTrace();
				log.info("hash="+sha1+",已经被锁定，有文件正在写入生成hash,无需再次");
				return_fg= true;
			}
		}
		return return_fg;
	}
}
