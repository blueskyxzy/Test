package com.xzy.test.consumer;


import com.xzy.test.testThread.test2.Consumer;

import javax.sound.midi.Soundbank;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class TestMain {

    private static BlockingQueue<Integer> workQueue = new ArrayBlockingQueue<>(100);

    public static void main(String[] args) {
        Thread p1 = new Thread(new Producer());
        Thread p2 = new Thread(new Producer());
        Thread c1 = new Thread(new Worker());
        p1.start();
        c1.start();
        p2.start();
    }

    public static class Producer implements Runnable{

        public Producer() {
        }

        @Override
        public void run() {
            synchronized(this){
                System.out.println("正在生产数据...");
                for(int i= 0; i< 10; i++){
                    Random random = new Random();
                    int rand = random.nextInt(10);
                    workQueue.add(rand);
                }
            }
        }
    }

    public static class Worker implements Runnable{

        //实现run()方法,并将当前的线程名称传入.
        @Override
        public  void run(){
           runWorker();
        }

        public void runWorker(){
            try{
                for (;;) {
//                    Integer task = workQueue.poll(1000, TimeUnit.MILLISECONDS);
                    Integer task = workQueue.take();
//                    Thread.sleep(1000);
                    System.out.println("消费数据:" + task);
                }
            } catch (InterruptedException e){
                System.out.println("error");
            }
        }
    }

}