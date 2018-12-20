package com.xzy.test.testThread.test1;

/**
 * Created by xzy on 18/12/18  .
 */

public class Producer {

    private String pic;

    public void consumer(String pic){
       this.pic = pic;
        System.out.println("pic:" + pic);
    }

    public void watch(){
        System.out.println("watch:" + pic);
    }
}
