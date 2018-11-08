package com.xzy.test.testStaticCode;

/**
 * Created by xzy on 18/7/17  .
 */

// 测试静态代码块

public class TestStatic {
    private static Long x;
    static {
        try{
            x = 3L;
        }catch (Exception e) {

        }
    }

    public static Long upload(){
        System.out.println(x);
        return x;
    }
}

