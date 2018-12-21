package com.xzy.test.testThread;

import com.xzy.test.testThread.test2.Consumer;
import com.xzy.test.testThread.test2.Producer;
import com.xzy.test.testThread.test2.Repertory;

/**
 * Created by xzy on 18/12/21  .
 */

public class TestDemo {
    public static void main(String[] args) {
        //定义一个仓库,消费者和生产者都使用这一个仓库
        Repertory repertory=new Repertory();
        //定义三个生产者(p1,p2,p3)
        Producer p1=new Producer(repertory);
        Producer p2=new Producer(repertory);
        Producer p3=new Producer(repertory);
        //定义两个消费者(c1,c2)
        Consumer c1=new Consumer(repertory);
        Consumer c2=new Consumer(repertory);
        //定义5个线程(t1,t2,t3,t4,t5)
        Thread t1=new Thread(p1,"张飞");
        Thread t2=new Thread(p2,"赵云");
        Thread t3=new Thread(p3,"关羽");
        Thread t4=new Thread(c1,"刘备");
        Thread t5=new Thread(c2,"曹操");
        //因为关羽跟赵云的生产积极性高,所以把他们的线程优先级调高一点
        t2.setPriority(10);
        t3.setPriority(10);
        //启动线程
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
