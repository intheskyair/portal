package com.zqkj.bsm.cluster.inter;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/**
 * 错误码返回
 * @author admin
 *
 */
public class ErrorConfig {
	public static void returnCode(ClusterResponse clusterResult,HttpServletResponse response) throws IOException
	{
		if(null!=clusterResult&&5001==clusterResult.getSubResponseCode())
		{//数据库连接失败
			response.sendError(5001);
		}
	}
}
