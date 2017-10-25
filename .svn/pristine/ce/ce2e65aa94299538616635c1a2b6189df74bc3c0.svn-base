package com.zqkj.bsm.Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;



public class LockFactory 
{
	public Logger log = Logger.getLogger(this.getClass());
    public static LockManager getLock()
    {     
        return new LockManImpl();   
    }   


    
    /**
     *执行用户的抢锁事务操作
     * @param thread
     * @param user_id
     */
    public void processTrans(Thread thread,String user_id)
    {
    	Lock lock=null; 
    	if(LockFactory.getLock().getLockMap().get(user_id)!=null)
    	{
    		lock = LockFactory.getLock().getLockMap().get(user_id);
    		System.out.println(thread.getName()+"从内存中得到了用户"+user_id+"的锁");
    	}
    	else
    	{
    		lock = new ReentrantLock();
    		LockFactory.getLock().getLockMap().put(user_id, lock);
    		System.out.println(thread.getName()+"主动创建了用户"+user_id+"锁");
    		
    	}
            try 
            {
            	lock.lock();
                System.out.println(thread.getName()+"得到了用户"+user_id+"锁");
                long startTime = System.currentTimeMillis();
                for(    ;     ;) 
                {
                    if(System.currentTimeMillis() - startTime >= 20000)
                        break;
                    //插入数据
                }

            } 
            catch (Exception e) 
            {
                // TODO: handle exception
            }
            finally 
            {
                System.out.println(thread.getName()+"释放了用户"+user_id+"锁");
                lock.unlock();
            }
    }
    
    
    public  class MyThread extends Thread 
    {
        private String user_id;
        public MyThread(String user_id) {
            this.user_id = user_id;
        }
        @Override
        public void run() 
        {
             
            try 
            {
                processTrans(Thread.currentThread(),user_id);
            } catch (Exception e) 
            {
                log.info(Thread.currentThread().getName()+"被中断");
            }
        }
    }
    
    
    public static void main(String[] args)  
    {
    	LockFactory lockfactory = new LockFactory();
    	MyThread thread1 =lockfactory.new MyThread("1");
        MyThread thread2 = lockfactory.new MyThread("4");
        MyThread thread3 = lockfactory.new MyThread("2");
        MyThread thread4 = lockfactory.new MyThread("2");
        MyThread thread5 = lockfactory.new MyThread("5");
        MyThread thread6 = lockfactory.new MyThread("3");
        thread1.start();
        thread2.start();
        thread3.start(); 
        thread4.start();
        thread5.start();
        thread6.start();
    }  
    
}
