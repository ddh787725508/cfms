package com.ddh.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ddh on 2018/4/12.
 */
@SuppressWarnings("all")
public class BlockingQueueTest {
    public static void main(String[] args) {
      ExecutorService threadPool= Executors.newFixedThreadPool(3);
        BlockingQueue queue=new ArrayBlockingQueue(3);
        for(int i=0;i<2;i++){

            threadPool.execute(()->{
                while (true){
            try {
                Thread.sleep((long)(Math.random()*100));
                System.out.println(Thread.currentThread().getName()+"准别放数据");
                queue.put(1);
                System.out.println(Thread.currentThread().getName()+"已经放了数据,"+"队列目前有"+queue.size()+"个数据");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                }
        });
        }
        threadPool.execute(()->{
            while (true){
            try {
                    Thread.sleep((long)(Math.random()*1000));
                    System.out.println(Thread.currentThread().getName()+"准备取数据");
                    queue.take();
                    System.out.println(Thread.currentThread().getName()+"已经取了数据,"+"队列目前有"+queue.size()+"个数据");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            }
        });
    }
}
