package com.zqkj.bsm.Lock;

import java.util.Map;
import java.util.concurrent.locks.Lock;

public interface LockManager {

	public abstract Map<String, Lock> getLockMap();

	public abstract int getCount();

	public abstract void addlock(String user_id, Lock lock);

	public abstract void removelock(String user_id);

}