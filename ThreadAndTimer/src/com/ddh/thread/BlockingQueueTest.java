package com.ddh.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
      public static void main(String[] args) {
		BlockingQueue  queue=new ArrayBlockingQueue(3);
		for(int i=0;i<2;i++){
			new Thread(){
				@Override
				public void run() {
				 	 while(true){
				 		 try {
							Thread.sleep((long)(Math.random()*100));
							System.out.println(Thread.currentThread().getName()+"准别放数据");
							queue.put(1);
							System.out.println(Thread.currentThread().getName()+"已经放了数据,"+"队列目前有"+queue.size()+"个数据");
							
						} catch (InterruptedException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
				 	 }
				}
			}.start();
		}
		new Thread(){
			@Override
			public void run() {
			 	 while(true){
			 		 try {
						Thread.sleep((long)(Math.random()*1000));
						System.out.println(Thread.currentThread().getName()+"准别放数据");
						queue.take();
						System.out.println(Thread.currentThread().getName()+"已经放了数据,"+"队列目前有"+queue.size()+"个数据");
						
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
			 	 }
			}
		}.start();
	}
}
