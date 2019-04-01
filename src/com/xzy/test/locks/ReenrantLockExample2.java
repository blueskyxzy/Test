package com.xzy.test.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xzy on 2019/4/1  .
 */

public class ReenrantLockExample2 implements Runnable{
    ReentrantLock lock = new ReentrantLock();
    static int i = 0;
    @Override
    public void run() {
        try {
            if (lock.tryLock(3, TimeUnit.SECONDS)){
                Thread.sleep(6000);
                System.out.println("拿到锁");
            } else {
                System.out.println("获取锁失败");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
           if(lock.isHeldByCurrentThread())
            lock.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        ReenrantLockExample2 reenrantLockExample2 = new ReenrantLockExample2();
        new Thread(reenrantLockExample2).start();
        new Thread(reenrantLockExample2).start();
    }
}
