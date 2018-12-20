package com.xzy.test.testThread.test1;

/**
 * Created by xzy on 18/12/19  .
 */

public class Watcher implements Runnable{
    public Watcher(Producer producer) {
        this.producer = producer;
    }

    private Producer producer;

    @Override
    public void run() {
        for(int i = 0; i < 20; i++){
            if(i%2==0){
                producer.watch();
            } else {
                producer.watch();
            }
        }
    }
}
