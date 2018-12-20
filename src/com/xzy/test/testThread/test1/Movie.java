package com.xzy.test.testThread.test1;

/**
 * Created by xzy on 18/12/18  .
 */

// 资源
public class Movie {

    private String pic;

    // true 生产者生产，消费者等待，生产完成通知消费
    // false 生产者等待，消费者消费，消费完成通知生成
    private boolean flag = true;

    public synchronized void produce(String pic){
        // 生产者等待
        if(!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 生产者生产
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.pic = pic;
        System.out.println("pic:" + pic);

        // 通知并修改标志位
        this.notify();
        flag = false;
    }

    public synchronized void consume(){
        // 消费者等待
        if (flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 消费者消费
        System.out.println("watch:" + pic);

        // 通知并修改标志位
        this.notify();
        flag = true;
    }
}
