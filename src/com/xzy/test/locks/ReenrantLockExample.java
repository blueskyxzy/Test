package com.xzy.test.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xzy on 2019/4/1  .
 */

public class ReenrantLockExample implements Runnable{
    Lock lock = new ReentrantLock();
    static int i = 0;
    @Override
    public void run() {
        lock.lock();
        try {
            for (int j=0; j<10000;j++){
                i++;
            }
        } finally {
            lock.unlock();
        }
    }

    public void a(){
        lock.lock();
        b();
        lock.unlock();
    }

    public void b(){
        lock.lock();
        lock.lock();
        lock.lock();
        System.out.println("bbbbbb");
        lock.unlock();
        lock.unlock();
        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new ReenrantLockExample());
        Thread thread2 = new Thread(new ReenrantLockExample());
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(i);
        ReenrantLockExample reenrantLockExample = new ReenrantLockExample();
        reenrantLockExample.a();
    }
}
