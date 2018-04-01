package com.ddh.thread;

public class TraditionalThreadSyn {
        public static void main(String[] args) {
        	new TraditionalThreadSyn().init();
       }
        private void init(){
        	final OutPuter outputer=new OutPuter();
        	new Thread(new Runnable() {
				
				@Override
				public void run() {
				while(true){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}	
				    outputer.output("zhangxiaoxiao");
				}
				}
		        
			}).start();
         new Thread(new Runnable() {
				
				@Override
				public void run() {
				while(true){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}	
				    outputer.output2("lihouming");
				}
				}
		        
			}).start();
        }
        
       static class OutPuter{
        	public void output(String name){
        		int len=name.length();
        		synchronized (OutPuter.class) {
					for (int i = 0; i < len; i++) {
						System.out.print(name.charAt(i));
					}
					System.out.println();
				}
        	}
        	public synchronized void output1(String name){
        		int len=name.length();
        		synchronized (this) {
					for (int i = 0; i < len; i++) {
						System.out.print(name.charAt(i));
					}
					System.out.println();
				}
        	}
        	public static synchronized void output2(String name){
        		int len=name.length();
					for (int i = 0; i < len; i++) {
						System.out.print(name.charAt(i));
					}
					System.out.println();
        	}
        }
}
