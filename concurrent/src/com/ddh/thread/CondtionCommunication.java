package com.ddh.thread;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ddh on 2018/4/12.
 */
public class CondtionCommunication {
    public static void main(String[] args) {
        final Business business=new Business();
        ExecutorService pool= Executors.newFixedThreadPool(1);
        pool.execute(()->{
            for(int i=1;i<=50;i++){  //子线程执行50次
             business.sub(i);
            }
        });
        for(int i=1;i<=50;i++){//主线程执行50次
          business.main(i);
        }
    }
    static class Business{
         Lock  lock=new ReentrantLock();
         Condition condition=lock.newCondition();
         private Boolean  bShould=true;
         public void sub(int i){
         lock.lock();
             try {
                 if(!bShould){
                     try {
                         condition.await();
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
                 for(int j=1;j<=10;j++){
                     System.out.println("sub thread sequese of "+j+" loop of "+ i);
                 }
                 bShould=false;
                 condition.signal();
             } catch (Exception e) {
                 e.printStackTrace();
             }finally {
                 lock.unlock();
             }

         }
        public void main(int i){
         lock.lock();
            try {
                if(bShould){
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                 for(int j=1;j<=100;j++){
                     System.out.println("main thread sequence of "+j+" loop of "+i);
                 }
                 bShould=true;
                condition.signal();
            } catch ( Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

}
