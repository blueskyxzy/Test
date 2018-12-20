package com.xzy.test.testThread.test1;

/**
 * Created by xzy on 18/12/19  .
 */

// 加标志位，信号灯
public class Client {

    public static void main(String[] args){
        Movie movie = new Movie();
        Thread t = new Thread(new Producer(movie));
        Thread t2 = new Thread(new Watcher(movie));
        t.start();
        t2.start();
    }
}
