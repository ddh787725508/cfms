package com.ddh.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueCommunication {
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
 static   class Business{
        	 BlockingQueue<Integer> queue1=new ArrayBlockingQueue<Integer>(1);
        	 BlockingQueue<Integer> queue2=new ArrayBlockingQueue<Integer>(1);
        	 {
        		 try {
					queue2.put(1);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
        	 }
        	public   void sub(int i){
        		 try {
					queue1.put(1);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
        		for(int  j=1;j<=10;j++){
        			System.out.println("sub thread sequese of"+j+"loop of"+i);
        		}
        		 try {
					queue2.take();
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
        	}
        	public   void main(int i)  {
        		 try {
					queue2.put(1);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
        		for(int j=1;j<=100;j++){
        			System.out.println("main thread sequese of"+j+"loop of"+i);
         	}
        		try {
					queue1.take();
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
        		 
        	}
        }

}

