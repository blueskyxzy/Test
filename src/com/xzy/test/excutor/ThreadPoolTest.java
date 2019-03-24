package com.xzy.test.excutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xzy on 2019/3/24  .
 */

public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        final Random random = new Random();
        final List<Integer> list = new ArrayList<>();

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        for (int i=0; i<10000;i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    list.add(random.nextInt());
                }
            });
        }
//        for (int i=0; i<10000;i++){
////            Thread thread = new Thread(()-> {list.add(random.nextInt());});
////            thread.start();
////            thread.join();
////        }
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(list.size());
        System.out.println("-----------------------------");
    }
}
