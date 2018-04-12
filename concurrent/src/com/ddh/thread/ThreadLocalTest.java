package com.ddh.thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ddh on 2018/4/12.
 */
public class ThreadLocalTest {

    public static void main(String[] args) {
      ExecutorService service= Executors.newFixedThreadPool(2);
     for(int i=0;i<2;i++){
     service.execute(()->{
         int data=new Random().nextInt(10);
         System.out.println(Thread.currentThread().getName()+"has put data:"+data);
          MyThreadScopeData.getInstance().setAge(data);
         MyThreadScopeData.getInstance().setName("name"+data);
         new A().get();
         new B().get();
     });
     }
    }
 static class A{
     public void get(){
         MyThreadScopeData mydata=MyThreadScopeData.getInstance();
         System.out.println("A from "+Thread.currentThread().getName()+" getMyData "+mydata.getName()+"  "+mydata.getAge());
     }
 }
 static class B{
     public void get(){
         MyThreadScopeData myThreadScopeData=MyThreadScopeData.getInstance();
         System.out.println("B from"+Thread.currentThread().getName()+" getMdata "+myThreadScopeData.getName()+"  "+myThreadScopeData.getAge());

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
