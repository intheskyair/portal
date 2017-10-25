package com.cudatec.render.util;

import java.io.File;

/**
 * 渲染完成后，清理分布式存储的订单文件<\br> 目前采用通过Linux原生任务命令进行清除：
 * #crontab -e 0 4 * * * /bin/find /render/ -mtime +7
 * -name "*.*" -exec rm -rf {} \;
 */
public class RenderUtil {
	public static void clean(File dir) {
		// for (File f : dir.listFiles()) {
		// if (f.isDirectory()) {
		// clean(f);
		// } else {
		// f.delete();
		// }
		// }
		// dir.delete();
	}

	public static void clean(String uid, String transid) {
		// File renderDir = new File(Configuration.RENDER + uid, transid);
		// if (renderDir.exists()) {
		// RenderUtil.clean(renderDir);
		// }
	}
}
