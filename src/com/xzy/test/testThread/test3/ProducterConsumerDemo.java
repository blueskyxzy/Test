package com.xzy.test.testThread.test3;

import java.util.concurrent.locks.*;

/**
 * Created by xzy on 2018/12/21.
 */

 /*
   
   本程序致力于解决多出现多个生产者，多个消费者的时候，依然能够达到生产一次，消费一次的功能
   ：
   解决的方法就是：)在被唤醒之后仍然进行条件判断，去检查要改的数字是否满足条件，如果不满足条件就继续睡眠。把两个方法中的if改为while即可。
   当然，此时仍会出现问题，就是所以线程都等待，失去资格
                   )需要将notify()改成notifyAll()
   
   升级版：
  使用Lock来替换synchronized，wait,notify,nonifyAll语法和语句的使用
  不需要同步，不需要notify
  */

class ProducterConsumerDemo {
    public static void main(String[] args) {
        Resources r = new Resources();
        Productor pro = new Productor(r);
        Consumer con = new Consumer(r);

        Thread t1 = new Thread(pro);
        Thread t2 = new Thread(pro);//多个生产者
        Thread t3 = new Thread(con);
        Thread t4 = new Thread(con);//多个消费者
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        System.out.println("Hello World!");
    }
}

class Resources {
    private String name;
    private int count = 1;
    private boolean flag = false;
    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void set(String name) throws InterruptedException {
        lock.lock();
        try {
            //)循环判断
            while (flag)
                //如果为真，放弃资格
                condition.await(); //会抛出异常
            this.name = name + "--" + count++;

            System.out.println(Thread.currentThread().getName() + "生产者" + this.name);
            flag = true;
            //)使用condition唤醒所有进程
            condition.signalAll(); //如果使用condition.signal()会出现相互等待状况，都失去资格
        } finally {
            lock.unlock();
        }


    }

    public void out() throws InterruptedException {
        lock.lock();
        try {
            //)循环判断
            while (!flag)
                condition.await();

            System.out.println(Thread.currentThread().getName() + " ....消费者...." + this.name);
            flag = false;
            //)使用condition唤醒所有进程
            condition.signalAll();
        } finally    //防止当前线程拿到锁后抛异常一直不释放锁
        {
            lock.unlock();
        }


    }
}

class Productor implements Runnable {
    private Resources res;

    Productor(Resources res) {
        this.res = res;
    }

    public void run() {
        while (true) {
            try {
                res.set("++商品++"); //需要抛出异常
            } catch (InterruptedException e) {
            }

        }
    }

}

class Consumer implements Runnable {
    private Resources res;

    Consumer(Resources res) {
        this.res = res;
    }

    public void run() {
        while (true) {
            try {
                res.out(); //需要抛出异常
            } catch (InterruptedException e) {
            }

        }
    }

}
