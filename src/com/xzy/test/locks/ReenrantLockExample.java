package com.xzy.test.locks;

/**
 * Created by xzy on 2019/4/1  .
 */

public class ReenrantLockExample implements Runnable{
    static int i = 0;
    @Override
    public void run() {
        for (int j=0; j<10000;j++){
            i++;
        }
    }

    public static void main(String[] args){

    }
}
