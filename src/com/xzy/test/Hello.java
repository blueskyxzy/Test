package com.xzy.test;

/**
 * Created by xzy on 2020/2/27  .
 */

// bug, i打印出来前面的是-1，后面的是/
public class Hello {
    public void test() {
        int  i = 8;
        while  ((i -= 3) > 0);
//        System.out.println("i = " + i);
        if(i != -1){
            System.out.println("--------------i" + i);
        }
    }

    public static void main(String[] args) {
        Hello hello = new Hello();
        for (int  i = 0; i < 50_000; i++) {
//            if (i >= 45000){
//                System.out.printf("---------");
//            }
            hello.test();

        }
    }
}
