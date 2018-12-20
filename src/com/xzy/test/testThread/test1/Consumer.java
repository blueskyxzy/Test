package com.xzy.test.testThread.test1;

/**
 * Created by xzy on 18/12/19  .
 */

public class Consumer implements Runnable{

    private Producer producer;

    public Consumer(Producer producer) {
        this.producer = producer;
    }

    @Override
    public void run() {
        for(int i = 0; i < 20; i++){
            if(i%2==0){
                producer.consumer("偶数");
            } else {
                producer.consumer("奇数");
            }
        }
    }
}
