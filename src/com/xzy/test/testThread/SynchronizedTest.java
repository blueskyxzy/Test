package com.xzy.test.testThread;

/**
 * Created by xzy on 18/11/16  .
 */

public class SynchronizedTest {

    static Object lock = new Object();

    // 作用域 静态对象 全局锁

    // 实例对象

    public synchronized void test1() throws InterruptedException {
//        synchronized (lock){
//
//        }
        lock.wait();
    }

    public synchronized void test2(){
        synchronized (this){

        }
    }

    public void test3(){

    }

    public static void main(String[] args){
        SynchronizedTest synchronizedTest = new SynchronizedTest();
        for (int i = 0; i < 10; i++){
            new Thread(() -> {
                synchronizedTest.test2();
            }).start();
        }
    }
}
