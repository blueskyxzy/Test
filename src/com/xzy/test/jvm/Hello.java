package com.xzy.test.jvm;

import cn.hutool.http.HttpUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by xzy on 2020/2/27  .
 */

// bug, i打印出来前面的是-1，后面的是/
public class Hello {

    public static void test() throws InterruptedException {
        final int _128K = 128 * 1024;
        List<byte[]> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            //耗时操作，令监视器的曲线变化更加明显
            Thread.sleep(100);
            list.add(new byte[_128K]);
        }
        System.out.println("--------test-------");
    }

    public static void test2() {
        List<Integer> orderBillIds = new ArrayList<>();
        for (int i = 0;i< 100; i++){
            orderBillIds.add(i);
        }
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadPoolExecutor.CallerRunsPolicy());
        List<Future<Integer>> futures = new ArrayList<>();
        for (Integer orderBillId: orderBillIds){
            Future<Integer> futureTask = threadPoolExecutor.submit(() -> {
                Thread.sleep(5000);
                Integer a = orderBillId + 1000;
                return a;
            });
            futures.add(futureTask);
        }
        try {
            // 获取返回结果给List
            for (Future<Integer> future :futures) {
                Integer result = future.get();
                System.out.println("result: " + result);
            }
        } catch (InterruptedException | ExecutionException e){
            Thread.currentThread().interrupt();
            //e.printStackTrace();
            System.out.println("asyn error " + e);
        } finally {
            threadPoolExecutor.shutdown();
        }
        System.out.println("--------------ent------------");
    }

    //    1、AbortPolicy策略
//    该策略直接抛出异常，阻止系统工作
//    2、CallerRunsPolicy策略
//    只要线程池未关闭，该策略直接在调用者线程中运行当前被丢弃的任务。显然这样不会真的丢弃任务，但是，调用者线程性能可能急剧下降。   ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务，如主线程
//    3、DiscardOledestPolicy策略
//    丢弃最老的一个请求任务，也就是丢弃一个即将被执行的任务，并尝试再次提交当前任务。
//    4、DiscardPolicy策略
//    默默的丢弃无法处理的任务，不予任何处理。
    public static void test3() {
        List<Integer> lists = new ArrayList<>();
        for (int i = 0;i< 100; i++){
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
    }
    public static void test4() {
        List<Integer> lists = new ArrayList<>();
        for (int i = 0;i< 100; i++){
            lists.add(i);
        }
        ThreadPoolExecutor threadPool1 = new ThreadPoolExecutor(
                5, 5, 10, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        ThreadPoolExecutor threadPool2 = new ThreadPoolExecutor(
                5, 5, 10, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        ThreadPoolExecutor threadPool3 = new ThreadPoolExecutor(
                5, 5, 10, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        ThreadPoolExecutor threadPool4 = new ThreadPoolExecutor(
                5, 5, 10, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        ThreadPoolExecutor threadPool5 = new ThreadPoolExecutor(
                3, 3, 10, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        ThreadPoolExecutor threadPool6 = new ThreadPoolExecutor(
                4, 4, 10, TimeUnit.SECONDS,
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
                lists.forEach(data -> threadPool1.execute(() -> System.out.println(Thread.currentThread().getName() + " is running" + data)));
            }
            if (lists != null) {
                lists.forEach(data -> threadPool2.execute(() -> System.out.println(Thread.currentThread().getName() + " is running" + data)));
            }
            if (lists != null) {
                lists.forEach(data -> threadPool3.execute(() -> System.out.println(Thread.currentThread().getName() + " is running" + data)));
            }
            if (lists != null) {
                lists.forEach(data -> threadPool4.execute(() -> System.out.println(Thread.currentThread().getName() + " is running" + data)));
            }
            if (lists != null) {
                lists.forEach(data -> threadPool5.execute(() -> System.out.println(Thread.currentThread().getName() + " is running" + data)));
            }
            if (lists != null) {
                lists.forEach(data -> threadPool6.execute(() -> System.out.println(Thread.currentThread().getName() + " is running" + data)));
            }
        } finally {
            threadPool1.shutdown();
            threadPool2.shutdown();
            threadPool3.shutdown();
            threadPool4.shutdown();
            threadPool5.shutdown();
            threadPool6.shutdown();
        }
    }


    public static void main(String[] args) throws InterruptedException {
//        for(int i =0;i<20;i++){
//            test();
//        }
        test4();
    }
}
