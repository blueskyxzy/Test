package com.xzy.test.interview;

/**
 * @author: xzy
 * @create: 2021-01-22
 **/

//评测题目: 无
//手写一个线程池

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedThreadPoolIV implements Executor {
    private volatile int corePoolSize;   // 核心线程数
    private final BlockingQueue<Runnable> workQueue; // 工作队列
    private final AtomicInteger ctl = new AtomicInteger(0); //线程数为0

    public FixedThreadPoolIV(int nThreads) {
        // 1.threadPoolExecutor写法
        // Thread new threadPoolExecutor(nThreads, nThreads, 0, As..Strategy)
        if (nThreads < 0)
            throw new IllegalArgumentException();
        this.corePoolSize = nThreads;
        LinkedBlockingQueue<Runnable> workerQueue = new LinkedBlockingQueue<>();
        workQueue = workerQueue;
    }

    @Override
    public void execute(Runnable command) {
        if(command == null)
            throw new NullPointerException();

    }



}
