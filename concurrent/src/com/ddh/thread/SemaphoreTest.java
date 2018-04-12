package com.ddh.thread;

/**
 * Created by ddh on 2018/4/12.
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量机制
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        ExecutorService threadPool= Executors.newCachedThreadPool();
        final Semaphore sp=new Semaphore(3,false);
        for(int i=0;i<10;i++){
            Runnable runnable=()->{
                try {
                    sp.acquire();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程"+Thread.currentThread().getName()+"进入，当前已有"+(3-sp.availablePermits())+"个并发");
                try {
                    Thread.sleep((long)(Math.random()*1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程"+Thread.currentThread().getName()+"即将离开");
                sp.release();
                System.out.println("线程"+Thread.currentThread().getName()+"已离开，当前有"+(3-sp.availablePermits())+"个并发");
            };
            threadPool.execute(runnable);
        }
    }
}
