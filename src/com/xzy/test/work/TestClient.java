package com.xzy.test.work;

import java.util.ArrayList;
import java.util.List;

public class TestClient {

    public static void main(String[] args){
//        String s = "http://app-common.oss-cn-hangzhou.aliyuncs.com/changru/gwgx/promotion/1544510536858.jpg?Expires=1859870537&OSSAccessKeyId=LTAI23gFjKV5y5Ew&Signature=BRbV8ISrS02YvftZcd5yA0CwDV0%3D";
//        System.out.println(s.length());

        List<Long> list = new ArrayList<>();
        for (int i =0; i<10; i++){
            Long x = 13L;
            list.add(x);
        }
        for (int i =0; i<10; i++){
            Long x = 16L;
            list.add(x);
        }
        for (int i =0; i<10; i++){
            Long x = 10L;
            list.add(x);
        }
        list.removeIf(y -> y == null || !(y == 16 || y == 29 || y == 13));

        System.out.println(list);

    }

}