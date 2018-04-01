package com.ddh.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CondtionCommunication {
        public static void main(String[] args) {
        	final Business business=new Business();
        	new Thread(new Runnable() {
				
				@Override
				public void run() {
				 for(int i=1;i<=50;i++){
					 business.sub(i);
				 }	
				}
			}).start();
			  for(int i=1;i<=50;i++){
				  business.main(i);
			  }
			  
		}      
      static  class Business{
    	    Lock lock=new ReentrantLock();
        	private boolean bShouldSub=true;
        	Condition condition=lock.newCondition();
        	public  void sub(int i){
        		lock.lock();
        		try {
					if(!bShouldSub)
						try {
							condition.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					for(int  j=1;j<=10;j++){
						System.out.println("sub thread sequese of"+j+"loop of"+i);
					}
					bShouldSub=false;
					condition.signal();
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}finally{
        		    lock.unlock();
				}
        	}
        	public   void main(int i){
        		lock.lock();
        		try {
					if(bShouldSub){
						try {
							condition.await();
						} catch (InterruptedException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
					}
					for(int j=1;j<=100;j++){
						System.out.println("main thread sequese of"+j+"loop of"+i);
      	}
					this.bShouldSub=true;
					 condition.signal();
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}finally{
					lock.unlock();
				}
        	}
        }

}

			