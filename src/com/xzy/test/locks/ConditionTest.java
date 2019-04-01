package com.xzy.test.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xzy on 2019/4/1  .
 */

public class ConditionTest {
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void await() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"睡觉");
            Thread.sleep(1000);
            condition.await();
            System.out.println(Thread.currentThread().getName()+"醒了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // 唤醒
    public void singnal(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"快起来");
            Thread.sleep(1000);
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public static void main(String[] args){
        ConditionTest conditionTest = new ConditionTest();
        new Thread(() -> {conditionTest.await();}).start();
        new Thread(() -> {conditionTest.singnal();}).start();
    }
}
