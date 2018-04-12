package com.ddh.thread;

import java.util.Map;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ddh on 2018/4/12.
 */
public class ExchangerTest {
    public static void main(String[] args) {
      ExecutorService service= Executors.newFixedThreadPool(2);
       final Exchanger<String> exchanger=new Exchanger<>();
        service.execute(()->{
            String data1="ddh";
            System.out.println("线程"+Thread.currentThread().getName()+"正在把"+data1+"换出去");
            try {
                Thread.sleep((long)(Math.random()*10000));
                  String data2=exchanger.exchange(data1);
                System.out.println("线程"+Thread.currentThread().getName()+"换回的数据为"+data2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        service.execute(()->{
            String data1="1234";
            System.out.println("线程"+Thread.currentThread().getName()+"正在把"+data1+"换出去");
            try {
                Thread.sleep((long)(Math.random()*1000));
                String data2=exchanger.exchange(data1);
                System.out.println("线程"+Thread.currentThread().getName()+"换回的数据为"+data2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
