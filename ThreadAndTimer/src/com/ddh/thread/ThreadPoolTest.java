package com.ddh.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
  
	public static void main(String[] args) {
		
	           //ExecutorService  threadPool= Executors.newFixedThreadPool(3);//固定线程池大小
             ExecutorService  threadPool=Executors.newSingleThreadExecutor();
	           //ExecutorService  threadPool= Executors.newCachedThreadPool();
	           for(int i=1;i<=10;i++){
	        	   final int task=i;
	           threadPool.execute(new Runnable() {
	        	   
				public void run() {
					for(int j=1;j<=10;j++){
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName()+"is looping of "+j+"for task of"+task);
					}
					
				}
			});
	           }
	           System.out.println("all of 10 tasks have committed!");
	           //threadPool.shutdown();
	          // threadPool.shutdownNow();
	           Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable() {
				
				@Override
				public void run() {
					System.out.println("bombing");
				
					
				}
			}, 6, 2, TimeUnit.SECONDS);
  }
}
