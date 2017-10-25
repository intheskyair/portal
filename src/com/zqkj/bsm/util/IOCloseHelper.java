package com.zqkj.bsm.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOCloseHelper {
	public static void closeIO(InputStream in,OutputStream out){
		if(in != null){
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(out != null){
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
