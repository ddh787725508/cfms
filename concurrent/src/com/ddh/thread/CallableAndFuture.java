package com.ddh.thread;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by ddh on 2018/4/12.
 */
public class CallableAndFuture {
    public static void main(String[] args) {
       ExecutorService threadpool= Executors.newSingleThreadExecutor();
      Future<String> future=threadpool.submit(()->{
          Thread.sleep(1000);
          return "Hello";
      });
        System.out.println("等待结果:");
        try {
            System.out.println("拿到结果:"+future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
       ExecutorService service1= Executors.newFixedThreadPool(3);
        CompletionService<Integer> completionService=new ExecutorCompletionService<Integer>(service1);
        for(int i=0;i<10;i++){
            final int seq=i;
            completionService.submit(()->{
                Thread.sleep(new Random().nextInt(5000));
                return seq;
            });
        }
        for(int i=0;i<10;i++){
            try {
                System.out.println(completionService.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }
}
