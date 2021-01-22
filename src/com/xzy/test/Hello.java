package com.xzy.test;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

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
//        Date sendTimeReq = DateUtil.parseDate("2021/01/11");
//        Calendar cd = Calendar.getInstance();//可以对每个时间域单独修改   对时间进行加减操作等
//        int hour = cd.get(Calendar.HOUR_OF_DAY);
//        Date timeSendNow = new Date();
//        DateTime date = DateUtil.beginOfDay(timeSendNow);
//        if (sendTimeReq != null && sendTimeReq.before(date)){
//            System.out.println("-----");
//        } else if (sendTimeReq != null && hour >= 20) {
//            if (timeSendNow.compareTo(sendTimeReq) >= 0) {
//                System.out.println("-----");
//            }
//        }
        System.out.println("------");
//        Hello hello = new Hello();
//        for (int  i = 0; i < 50_000; i++) {
////            if (i >= 45000){
////                System.out.printf("---------");
////            }
//            hello.test();
//
//        }
    }
}
