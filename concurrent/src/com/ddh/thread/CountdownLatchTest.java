package com.ddh.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ddh on 2018/4/12.
 */
public class CountdownLatchTest {
    public static void main(String[] args) {
     ExecutorService service= Executors.newFixedThreadPool(3);
        final CountDownLatch cOrder=new CountDownLatch(1);
        final CountDownLatch cAnswer=new CountDownLatch(3);
        for(int i=0;i<3;i++){
            service.execute(()->{
                try {
                    System.out.println("线程"+Thread.currentThread().getName()+"正在接受命令");
                    cOrder.await();
                    System.out.println("线程"+Thread.currentThread().getName()+"以接受命令");
                    Thread.sleep((long)(Math.random()*1000));
                    System.out.println("线程"+Thread.currentThread().getName()+"回应处理结果");
                    cAnswer.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        }
        try{
            Thread.sleep((long)(Math.random()*1000));
            System.out.println("线程"+Thread.currentThread().getName()+"即将发出命令");
            cOrder.countDown();
            System.out.println("线程"+Thread.currentThread().getName()+"已发出命令");
            cAnswer.await();
            System.out.println("线程"+Thread.currentThread().getName()+"回应命令处理结果");
        }catch (Exception e){
            e.printStackTrace();
        }
        service.shutdown();
    }
}
