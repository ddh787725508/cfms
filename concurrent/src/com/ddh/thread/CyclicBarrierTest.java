package com.ddh.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ddh on 2018/4/12.
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        CyclicBarrier cb = new CyclicBarrier(3);
        for (int i = 0; i < 3; i++) {
            service.execute(() -> {
                        try {
                            Thread.sleep((long) (Math.random() * 100));
                            System.out.println("线程" + Thread.currentThread().getName() + "即将到达集合点1，当前已有" +
                                    (cb.getNumberWaiting() + 1) + "个到达" + (cb.getNumberWaiting() == 2 ? "都到齐了，继续走呀" : "正在等候"));
                            cb.await();
                            Thread.sleep((long) (Math.random() * 100));
                            System.out.println("线程" + Thread.currentThread().getName() + "即将到达集合点2，当前已有" +
                                    (cb.getNumberWaiting() + 1) + "个到达" + (cb.getNumberWaiting() == 2 ? "都到齐了，继续走呀" : "正在等候"));
                            cb.await();
                            Thread.sleep((long) (Math.random() * 100));
                            System.out.println("线程" + Thread.currentThread().getName() + "即将到达集合点3，当前已有" +
                                    (cb.getNumberWaiting() + 1) + "个到达" + (cb.getNumberWaiting() == 2 ? "都到齐了，继续走呀" : "正在等候"));
                            cb.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    }
            );
        }
        service.shutdown();
    }
}
