package com.xzy.test.jvm;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author: xzy
 * @create: 2021-01-22
 **/

// 模拟死循环、阻塞等待、死锁现象
public class DeadLock {

    public static void main(String[] args) throws IOException {
        System.in.read();
        System.out.println("开启死循环线程");
        whileTureThread();

        System.in.read();
        System.out.println("开启等待线程");
        waitThread();

        System.in.read();
        System.out.println("开启死锁线程");
        deadLock();

    }

    /**
     * 死循环线程
     */
    private static void whileTureThread() {
        new Thread(() -> {
            while (true) {

            }
        }, "whileTrueThread").start();

    }

    /**
     * 等待线程
     */
    private static void waitThread() {
        new Thread(() -> {
            synchronized (DeadLock.class) {
                try {
                    DeadLock.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"waitThread").start();
    }

    /**
     * 模拟死锁现象
     */
    private static void deadLock() {
        String A = "A";
        String B = "B";
        new Thread(() -> {
            synchronized (A) {
                try {
                    //睡2秒
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B) {
                    System.out.println("拿到B锁");
                }
            }
        }, "A").start();

        new Thread(() -> {
            synchronized (B) {
                try {
                    //睡2秒
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (A) {
                    System.out.println("拿到A锁");
                }
            }
        }, "B").start();
    }
}
