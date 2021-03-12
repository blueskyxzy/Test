package com.xzy.test.interview;


import com.xzy.test.locks.ReenrantLockExample;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestMain {

    public static void main(String[] args) {
        List<Integer> lists = new ArrayList<>();
        for (int i = 0;i< 100; i++){
            lists.add(i);
        }
//        FixedThreadPool fixedThreadPool = new FixedThreadPool(3);
        ThreadPoolExecutor fixedThreadPool = new ThreadPoolExecutor(
                2, 3, 10, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        lists.forEach(data -> {
            fixedThreadPool.execute(() -> System.out.println(Thread.currentThread().getName() + " is running" + data));
        });
        System.out.printf("---------");

    }

}