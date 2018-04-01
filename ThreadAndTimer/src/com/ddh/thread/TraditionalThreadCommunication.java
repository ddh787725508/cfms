package com.ddh.thread;

public class TraditionalThreadCommunication {
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
}

class Business{
	private boolean bShouldSub=true;
	public synchronized void sub(int i){
		if(!bShouldSub)
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		for(int  j=1;j<=10;j++){
			System.out.println("sub thread sequese of"+j+"loop of"+i);
		}
		bShouldSub=false;
		this.notify();
	}
	public synchronized void main(int i){
		if(bShouldSub){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		for(int j=1;j<=100;j++){
			System.out.println("main thread sequese of"+j+"loop of"+i);
 	}
		this.bShouldSub=true;
		this.notify();
	}
}
