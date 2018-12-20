package com.xzy.test.testThread.test1;

/**
 * Created by xzy on 18/12/19  .
 */

// 生产者
public class Producer implements Runnable{

    private Movie movie;

    public Producer(Movie movie) {
        this.movie = movie;
    }

    @Override
    public void run() {
        for(int i = 0; i < 20; i++){
            if(i%2==0){
                movie.produce("偶数");
            } else {
                movie.produce("奇数");
            }
        }
    }
}
