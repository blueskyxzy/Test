package com.xzy.test.testThread.test1;

/**
 * Created by xzy on 18/12/19  .
 */

public class Client {

    public static void main(String[] args){
        Producer producer = new Producer();
        Thread t = new Thread(new Consumer(producer));
        Thread t2 = new Thread(new Watcher(producer));
        t.start();
        t2.start();
    }
}
