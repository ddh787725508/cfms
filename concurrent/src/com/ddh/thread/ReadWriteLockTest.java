package com.ddh.thread;

/**
 * Created by ddh on 2018/4/12.
 */
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {

    public static void main(String[] args) {
        final   Queue3 q3=new Queue3();
        for(int i=0;i<3;i++){
            new Thread(){
                @Override
                public void run() {
                    while(true){
                        q3.get();
                    }
                }
            }.start();
            new Thread(){
                @Override
                public void run() {
                    q3.put(new Random().nextInt(10000));
                }
            }.start();
        }


    }
}
class Queue3{
    private Object data=null;//只能有一个线程写该数据，可以有多个线程来读此数据
    ReadWriteLock lock=new ReentrantReadWriteLock();
    public void get(){
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+" be ready to read data");
            Thread.sleep((long)Math.random()*1000);
            System.out.println(Thread.currentThread().getName()+" have read data:"+data);
        } catch (InterruptedException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }finally{
            lock.readLock().unlock();
        }
    }

    public void put(Object data){
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"be ready to write data");
            Thread.sleep((long)Math.random()*1000);
            this.data=data;
            System.out.println(Thread.currentThread().getName()+"have write data:"+data);
        } catch (InterruptedException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }finally{
            lock.writeLock().unlock();
        }
    }
}
