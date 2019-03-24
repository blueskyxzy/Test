package com.xzy.test.excutor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by xzy on 2019/3/23  .
 */

// test FutureTask,Callable
public class test1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new CallabelTest() {
        });

        new Thread(futureTask).start();
//        futureTask.cancel(true);
        System.out.println(futureTask.get());
    }
}

class CallabelTest implements Callable<String> {

    @Override
    public String call() throws Exception {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 3; i++){
            sb.append(i).append(",");
        }
        return sb.toString();
    }
}
