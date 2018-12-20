package com.xzy.test.testThread.test1;

/**
 * Created by xzy on 18/12/19  .
 */

// 消费者
public class Watcher implements Runnable{

    public Watcher(Movie movie) {
        this.movie = movie;
    }

    private Movie movie;

    @Override
    public void run() {
        for(int i = 0; i < 20; i++){
            if(i%2==0){
                movie.consume();
            } else {
                movie.consume();
            }
        }
    }
}
