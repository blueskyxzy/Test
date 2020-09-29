package com.xzy.test.threadPoolExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by xzy on 20/10/28  .
 */


public class Client {

    public static void main(String[] args) throws InterruptedException {
        asynTask3();
    }

    public static void asynTask1() {
        List<Integer> orderBillIds = new ArrayList<>();
        for (int i = 0;i< 100; i++){
            orderBillIds.add(i);
        }
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 3, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadPoolExecutor.CallerRunsPolicy());
        List<Future<Integer>> futures = new ArrayList<>();
        for (Integer orderBillId: orderBillIds){
            Future<Integer> futureTask = threadPoolExecutor.submit(() -> {
//                Thread.sleep(1000);
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
    public static void asynTask2() {
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

    public static void asynTask3() throws InterruptedException {
        for (int i = 0;i< 10; i++) {
            new Thread(() ->{
                try {
                    ThreadTest.SemaphoreTest();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        }
    }

    public static void asynTask4() throws InterruptedException {
        for (int i = 0;i< 10; i++) {
            new Thread(() ->{
                try {
                    ThreadTest.ThreadLocalTest();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        }
    }
}
