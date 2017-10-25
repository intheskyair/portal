package com.zqkj.bsm.client.tcpservice;

import java.util.Hashtable;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//已经发送成功的消息队列
public class ReceiveQueue {
	private static Hashtable<String, Map<String, Object>> ht = new Hashtable<String, Map<String, Object>>();

	private static int j = 1;
	protected final Log logger = LogFactory.getLog(getClass());

	public ReceiveQueue() {
	}

	public static boolean isEmpty() {
		return ht.isEmpty();
	}

	public static synchronized void addMessage(Map<String, Object> dmsg) {
		try {
			ht.put(String.valueOf(dmsg.get("sn")), dmsg);
		} catch (Throwable t) {
			System.err.println("ReceiveQueue Error");
			t.printStackTrace(System.err);
		}

	}

	public static synchronized Map<String, Object> removeMessage(int SequenceID) {
		Map<String, Object> dmsg = null;
		dmsg = (Map<String, Object>) ht.remove(String.valueOf(SequenceID));
		return dmsg;
	}
    
	public static Map<String, Object> findMessage(int SequenceID)
	{
		Map<String, Object> dmsg = null;
		dmsg = (Map<String, Object>) ht.get(String.valueOf(SequenceID));
		return dmsg;		
	}
	
	public static int size() 
	{
		return ht.size();
	}

	public static synchronized int getSequenceID() 
	{
		if(j<99999999)
		{
		     return j++;
		}
		else
		{
			j=0;
			return j++;
		}
		
	}

}
