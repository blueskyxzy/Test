package com.xzy.test.threadPoolExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: xzy
 * @create: 2020-09-29
 **/

public class ThreadTest {
    private static Semaphore semp = new Semaphore(2);

    private static int i = 0;

    private static ThreadLocal<Integer> local = ThreadLocal.withInitial(() -> 2);

    private static AtomicInteger atomicInteger = new AtomicInteger(2);



//    private static ThreadLocal<Integer> local = new ThreadLocal<Integer>() {
//        @Override public Integer initialValue() {
//            return 2;
//        }
//    };

    /**
     * @Description: 信号量测试
     * @Author: xzy
     * @Date: 2020/09/29
     */
    public static void SemaphoreTest() throws InterruptedException {
        // 获取许可
        semp.acquire();
        System.out.println("thead name:" + Thread.currentThread().getName());
        List<Integer> lists = new ArrayList<>();
        for (int i = 0;i< 20; i++){
            lists.add(i);
        }
//        CountDownLatch countDownLatch = new CountDownLatch(1);
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2, 3, 10, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new ThreadPoolExecutor.CallerRunsPolicy());
//        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
//                2, 3, 10, TimeUnit.SECONDS,
//                new LinkedBlockingQueue<Runnable>(),
//                new ThreadPoolExecutor.DiscardPolicy());
//        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
//                4, 4, 10, TimeUnit.SECONDS,
//                new ArrayBlockingQueue<>(10),
//                new ThreadPoolExecutor.CallerRunsPolicy());
        //CallerRunsPolicy,AbortPolicy,DiscardOledestPolicy,DiscardPolicy
        try {
            if (lists != null) {
                lists.forEach(data -> threadPool.execute(() -> System.out.println(Thread.currentThread().getName() + " is running" + data)));
            }
        } finally {
            threadPool.shutdown();
        }
        // 访问完后，释放
        // 这里不能保证任务执行完就释放信号，释放信号了但线程任务还在跑
        // 1.join() 让主线程等待，到执行完 2.原子类计算器 3.CountDownLatch计数器 4.threadPool.isTerminated()
        //  CountDownLatch: 一个线程(或者多个)， 等待另外N个线程完成某个事情之后才能执行。
        //  CyclicBrrier: N个线程相互等待，任何一个线程完成之前，所有的线程都必须等待。

//        countDownLatch.await();
        while(true) {
            if(threadPool.isTerminated()) {
                break;
            }else {
                Thread.sleep(100);
            }
        }
        semp.release();
    }

    public static void ThreadLocalTest() throws InterruptedException {
        Integer a= local.get();
        if (a <=0){
            System.out.println("不处理");
        } else {
            local.set(--a);
        }
        List<Integer> lists = new ArrayList<>();
        for (int i = 0;i< 20; i++){
            lists.add(i);
        }
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2, 3, 10, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new ThreadPoolExecutor.CallerRunsPolicy());
//        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
//                2, 3, 10, TimeUnit.SECONDS,
//                new LinkedBlockingQueue<Runnable>(),
//                new ThreadPoolExecutor.DiscardPolicy());
//        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
//                4, 4, 10, TimeUnit.SECONDS,
//                new ArrayBlockingQueue<>(10),
//                new ThreadPoolExecutor.CallerRunsPolicy());
        //CallerRunsPolicy,AbortPolicy,DiscardOledestPolicy,DiscardPolicy
        try {
            if (lists != null) {
                lists.forEach(data -> threadPool.execute(() -> System.out.println(Thread.currentThread().getName() + " is running" + data)));
            }
        } finally {
            threadPool.shutdown();
        }
        // 执行完
        local.set(local.get() + 1);
    }

}
