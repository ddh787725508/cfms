package com.ddh.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreeCondtionCommunication {
        public static void main(String[] args) {
        	final Business business=new Business();
        	new Thread(new Runnable() {
				
				@Override
				public void run() {
				 for(int i=1;i<=50;i++){
					 business.sub2(i);
				 }	
				}
			}).start();
				new Thread(new Runnable() {
								
								@Override
								public void run() {
								 for(int i=1;i<=50;i++){
									 business.sub3(i);
								 }	
								}
							}).start();
			  for(int i=1;i<=50;i++){
				  business.main(i);
			  }
			  
		}      
      static  class Business{
    	    Lock lock=new ReentrantLock();
        	private int ShouldSub=2;
        	Condition condition1=lock.newCondition();
        	Condition condition2=lock.newCondition();
        	Condition condition3=lock.newCondition();
        	public  void sub2(int i){
        		lock.lock();
        		try {
					if(ShouldSub!=2)
						try {
							condition2.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					for(int  j=1;j<=10;j++){
						System.out.println("sub2 thread sequese of"+j+"loop of"+i);
					}
					 ShouldSub=3;
					condition3.signal();
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}finally{
        		    lock.unlock();
				}
        	}
        	public  void sub3(int i){
        		lock.lock();
        		try {
					if(ShouldSub!=3)
						try {
							condition3.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					for(int  j=1;j<=20;j++){
						System.out.println("sub3 thread sequese of"+j+"loop of"+i);
					}
					 ShouldSub=1;
					condition1.signal();
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
					if(ShouldSub!=1){
						try {
							condition1.await();
						} catch (InterruptedException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
					}
					for(int j=1;j<=100;j++){
						System.out.println("main thread sequese of"+j+"loop of"+i);
      	}
					this.ShouldSub=2;
					 condition2.signal();
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}finally{
					lock.unlock();
				}
        	}
        }

}

