package com.xzy.test.interview;

/**
 * @author: xzy
 * @create: 2021-02-05
 **/

public class RetryTest {
    public static void main(String[] args) {
        retry:
        for (int i=0; i< 3; i++) {
            for (int j=0; j< 5; j++) {
                System.out.println(i + "," +j);
                if (j == 3)
                    break retry;
            }
        }
        System.out.println("------");
    }
}
