package com.xzy.test.testStaticCode;

import com.xzy.test.testStaticCode.TestStatic;

/**
 * Created by xzy on 18/7/17  .
 */

public class StaticTest {

    public static void main(String[] args){
        Long x = TestStatic.upload();
        System.out.println(x);

        Long upload = TestStatic.upload();


    }
}
