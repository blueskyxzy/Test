package com.xzy.test.testThread;

public class TestThreadClient {

    public static void main(String[] args){
        for(int i = 0; i < 20; i ++){
            Thread t = new Thread(() -> System.out.println(Thread.currentThread().getName() + ":start"));
            t.start();
        }
        System.out.println("end");
    }

}