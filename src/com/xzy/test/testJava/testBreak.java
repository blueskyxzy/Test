package com.xzy.test.testJava;

/**
 * Created by xzy on 18/11/26  .
 */

public class testBreak {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 20; j++){
                if (j>10)
                    break;
                System.out.println("i="+ i + ",j="+j);
            }
        }
    }

}
