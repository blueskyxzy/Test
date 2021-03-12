package com.xzy.test.interview;


import java.math.BigDecimal;

public class TestEquals {

    public static void main(String[] args) {
        Long i = 5L;
        String s = "5";
        Long a = 5L;
        Integer b = 5;
        boolean result1 = i.equals(s);
        boolean result2 = s.equals(i);
        boolean result3 = i.equals(a);
        boolean result4 = (i == a);
        boolean result5 = i.equals(b);
        System.out.println("result:" + result1);
        System.out.println("result:" + result2);
        System.out.println("result:" + result3);
        System.out.println("result:" + result4);
        System.out.println("result:" + result5);
        String se = "\"code\" : \"123\"";
        Integer cashMoney = 114990000;
        String result = String.valueOf(new BigDecimal(cashMoney).divide(new BigDecimal(1000000)).setScale(2, BigDecimal.ROUND_HALF_UP));
        System.out.printf("result:" + result);
    }

}