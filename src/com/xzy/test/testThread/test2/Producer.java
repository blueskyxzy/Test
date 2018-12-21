package com.xzy.test.testThread.test2;

/**
 * Created by xzy on 18/12/21  .
 */

public class Producer implements Runnable{
    //定义一个静态变量来记录产品号数.确保每一个产品的唯一性.
    public static  Integer count=0;
    //定义仓库
    Repertory repertory=null;
    //构造方法初始化repertory(仓库)
    public Producer(Repertory repertory) {
        this.repertory=repertory;
    }
    /* run()方法因为该方法中存在非原子性操作count++;
     * 当多个线程同时访问时会发生count++的多次操作,导致出错
     * 为该方法添加同步错做,确保每一次只能有一个生产者进入该模块。
     * 这样就能保证count++这个操作的安全性.
     */
    @Override
    public void run() {
        while (true) {
            synchronized(Producer.class){
                count++;
                Product product=new Product(count);
                repertory.push(product,Thread.currentThread().getName());
            }


        }

    }
}
