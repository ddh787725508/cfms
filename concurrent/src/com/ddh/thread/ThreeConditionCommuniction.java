package com.ddh.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ddh on 2018/4/12.
 */
@SuppressWarnings("all")
public class ThreeConditionCommuniction {
    public static void main(String[] args) {
       final Business business=new Business();
        ExecutorService pool= Executors.newFixedThreadPool(2);
        pool.execute(()->{
            for(int i=1;i<=50;i++){  //子线程1执行50次
                business.sub1(i);
            }
        });
        pool.execute(()->{
            for(int i=1;i<=50;i++){  //子线程2执行50次
                business.sub2(i);
            }
        });
        for(int i=1;i<=50;i++){//主线程执行50次
            business.main(i);
        }
    }
    static class Business{
        Lock lock=new ReentrantLock();
        Condition condition1=lock.newCondition();
        Condition condition2=lock.newCondition();
        Condition condition3=lock.newCondition();
        private int  bShould=1;

        public void sub1(int i){
            lock.lock();
            try {
                if(bShould!=1){
                    try {
                        condition1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for(int j=1;j<=10;j++){
                    System.out.println("sub1 thread sequese of "+j+" loop of "+ i);
                }
                bShould=2;
                condition2.signal();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        }
        public void sub2(int i){
            lock.lock();
            try {
                if(bShould!=2){
                    try {
                        condition2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for(int j=1;j<=20;j++){
                    System.out.println("sub2 thread sequese of "+j+" loop of "+ i);
                }
                bShould=3;
                condition3.signal();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        }
        public void main(int i){
            lock.lock();
            try {
                if(bShould!=3){
                    try {
                        condition3.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for(int j=1;j<=100;j++){
                    System.out.println("main thread sequence of "+j+" loop of "+i);
                }
                bShould=1;
                condition1.signal();
            } catch ( Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }
}
