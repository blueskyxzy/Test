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
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadPoolExecutor.AbortPolicy());
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

    public static void main(String[] args) throws InterruptedException {
        for(int i =0;i<20;i++){
            test();
        }
    }
}
