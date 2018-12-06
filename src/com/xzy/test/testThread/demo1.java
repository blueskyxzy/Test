package com.xzy.test.testThread;

/**
 * Created by xzy on 18/12/5  .
 */


public class demo1{
    public static void main(String args[]){
        for(int i = 0; i < 20; i ++){
            Thread t = new Thread(() -> System.out.println(Thread.currentThread().getName() + ":start"));
            t.start();
            for(int j=0;j<50;j++){
                if(i>10){
                    try{
                        t.join() ;    // 线程强制运行
//                        t.wait();
//                        t.notify();
//                        t.notifyAll();
//                        Thread.yield();
//                        Thread.sleep(100);
//                        t.setPriority(Thread.MAX_PRIORITY);
//                        t.interrupt();
//                        t.suspend();
//                        t.resume();
                    }catch(InterruptedException e){}
                }
            }
        }
    }
}
