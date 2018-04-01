package com.ddh.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ThreadScopeTest {
	 private static ThreadLocal<MyThreadScopeData> myThreadScopeData=new ThreadLocal<MyThreadScopeData>();
        public static void main(String[] args) {
			for(int i=0;i<2;i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					int data=new Random().nextInt();
					System.out.println(Thread.currentThread().getName()+"has put data:"+data);
					 MyThreadScopeData.getInstance().setName("name"+data);
					 MyThreadScopeData.getInstance().setAge(data);
					new A().get();
					new B().get();
				}
			}).start();
			}
			
			
		}
        static class A{
        	public void get(){
        		MyThreadScopeData myData= MyThreadScopeData.getInstance();
        		System.out.println("A from "+Thread.currentThread().getName()+"getMyData:"+myData.getName()+","+myData.getAge());
        	}
        }
        static class B{
        	public void get(){
        		 
        		MyThreadScopeData myData= MyThreadScopeData.getInstance();
        		System.out.println("B from "+Thread.currentThread().getName()+"getMyData:"+myData.getName()+","+myData.getAge());
        	}
        }
}
  class MyThreadScopeData{
	  private MyThreadScopeData(){
		  
	  }
	  public static MyThreadScopeData getInstance(){
	      MyThreadScopeData instance=map.get();  
		  if(instance==null){
			  instance=new MyThreadScopeData();
			  map.set(instance);
		  }
		  return instance;
	  }
	  private static ThreadLocal<MyThreadScopeData> map=new ThreadLocal<MyThreadScopeData>();
	  private String name;
	  private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
  }
 