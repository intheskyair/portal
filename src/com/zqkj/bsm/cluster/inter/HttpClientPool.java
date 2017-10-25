package com.zqkj.bsm.cluster.inter;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;

/**
 * 针对HttpClient创建达到最大个数而导致的IO异常增加了池连接引用.
 * 
 * @author 0188
 *
 */
public class HttpClientPool {

    // 设置请求超时
    public static final int CONNECTION_TIMEOUT = 30000;
    // 读取时间超时
    public static final int READ_TIMEOUT = 300000;
    // 最大连接数
    public static final int MAX_TOTAL_CONNECTION = 800;
    // 获取连接的最大等待时间
    public static final int WAIT_TIMEOUT = 60000;
    // 每个路由最大连接数
    public static final int MAX_ROUTE_CONNETIONS = 100;
    private static ThreadSafeClientConnManager cm = null;
    
    static {
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
        cm = new ThreadSafeClientConnManager(schemeRegistry);
        // 设置参数
        cm.setMaxTotal(MAX_TOTAL_CONNECTION);
        cm.setDefaultMaxPerRoute(MAX_ROUTE_CONNETIONS);
    }

    /**
     * 获取HttpClient对象
     * 
     * @return
     */
    public static HttpClient getHttpClient() {

        HttpParams params = new BasicHttpParams();
        params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
        params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, CONNECTION_TIMEOUT);
        params.setParameter(CoreConnectionPNames.SO_TIMEOUT, READ_TIMEOUT);

        return new DefaultHttpClient(cm, params);
    }

    /**
     * 关闭池连接
     */
    public static void release() {
        if (cm != null) {
            cm.shutdown();
        }
    }
}
