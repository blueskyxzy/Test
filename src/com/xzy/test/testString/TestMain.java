package com.xzy.test.testString;


import java.math.BigDecimal;

public class TestMain {

    public static void main(String[] args) {
        Short typeJw = (short)0;
        if (typeJw == null || typeJw == 0){
            System.out.println("-----");
        }
        String itemId = "QYs24144-142444";
        Long commodityBillId;
        if (itemId.contains("#")){
            commodityBillId = Long.valueOf(itemId.substring(itemId.indexOf("-") + 1, itemId.indexOf("#")));
        } else {
            commodityBillId = Long.valueOf(itemId.substring(itemId.indexOf("-") + 1));
        }
        System.out.println("------");

        String s = "内蒙古自治区锡林郭勒盟东乌珠穆沁旗宝格达乌拉总场测试空间";
        String receiveAddr = s.replace(" ","");
        String province = "";
        String city = "";
        String are = "";
        if (receiveAddr.length() > 0) {
            if (receiveAddr.contains("省")){
                province = receiveAddr.substring(0, receiveAddr.indexOf("省")+1);
                if (receiveAddr.contains("市")){
                    city = receiveAddr.substring(receiveAddr.indexOf("省")+1, receiveAddr.indexOf("市")+1);
                    if (receiveAddr.contains("区")){
                        are = receiveAddr.substring(receiveAddr.indexOf("市")+1, receiveAddr.indexOf("区")+1);
                    } else if (receiveAddr.contains("县")){
                        are = receiveAddr.substring(receiveAddr.indexOf("市")+1, receiveAddr.indexOf("县")+1);
                    }
                } else if (receiveAddr.contains("县")){
                    city = receiveAddr.substring(receiveAddr.indexOf("省")+1, receiveAddr.indexOf("县")+1);
                    if (receiveAddr.contains("区")){
                        are = receiveAddr.substring(receiveAddr.indexOf("省")+1, receiveAddr.indexOf("区")+1);
                    } else {
                        are = city;
                    }
                }
            } else if (receiveAddr.contains("市")){
                province = receiveAddr.substring(0, receiveAddr.indexOf("市")+1);
                city = province;
                if (receiveAddr.contains("区")){
                    are = receiveAddr.substring(receiveAddr.indexOf("市")+1, receiveAddr.indexOf("区")+1);
                } else if (receiveAddr.contains("县")){
                    are = receiveAddr.substring(receiveAddr.indexOf("市")+1, receiveAddr.indexOf("县")+1);
                }
            }
        }
//        String receiveAddr = s.replace(" ","");
//        String province = receiveAddr;
//        String city = receiveAddr;
//        String are = receiveAddr;
//        if (!receiveAddr.isEmpty()) {
//            if (receiveAddr.contains("省")){
//                province = receiveAddr.substring(0, receiveAddr.indexOf("省")+1);
//                if (receiveAddr.contains("市")){
//                    city = receiveAddr.substring(receiveAddr.indexOf("省")+1, receiveAddr.indexOf("市")+1);
//                    if (receiveAddr.contains("区")){
//                        are = receiveAddr.substring(receiveAddr.indexOf("市")+1, receiveAddr.indexOf("区")+1);
//                    }
//                } else if (receiveAddr.contains("县")){
//                    city = receiveAddr.substring(receiveAddr.indexOf("省")+1, receiveAddr.indexOf("县")+1);
//                    if (receiveAddr.contains("区")){
//                        are = receiveAddr.substring(receiveAddr.indexOf("省")+1, receiveAddr.indexOf("区")+1);
//                    } else {
//                        are = city;
//                    }
//                }
//            } else if (receiveAddr.contains("市")){
//                province = receiveAddr.substring(0, receiveAddr.indexOf("市")+1);
//                city = province;
//                if (receiveAddr.contains("区")){
//                    are = receiveAddr.substring(receiveAddr.indexOf("市")+1, receiveAddr.indexOf("区")+1);
//                } else if (receiveAddr.contains("县")){
//                    are = receiveAddr.substring(receiveAddr.indexOf("市")+1, receiveAddr.indexOf("县")+1);
//                }
//            }
//        }
        System.out.println("-----------");
//        String str = "xiyou" + "3g" + "backend";
//        String str2 = "xiyou3gbackend";
//        String str3 = str + "test";
//        String str4 = "a" + "b";
//        System.out.println(str2 == str);
//        System.out.println(str3 == "xiyou3gbackendtest");
//        System.out.println(str4 == "ab");
//        String s = "aBc12D93111ffg";
//        synchrStr(s);
    }

//
//    分析字符串
//    写字符串的分析函数，分析一个由字母和数字组成的字符串，例如aBc12D93.2、
//    相连的数字不能分为2个，即12是作为一个数字统计，输出字符串中字符和数字的个数。
    public static void synchrStr(String s){
        if (s== null || s.length() == 0){
            System.out.println("字符串个数0，数字个数0");
        }
        int size = s.length();
        char[] chars = s.toCharArray();
        Integer charCount = 0;
        Integer numCount = 0;
        Boolean numFlag = false;
        for (int i= 0; i<size; i++){
            char c = chars[i];
            if ((c > '0' && c < '9')){
                if (!numFlag){
                    numCount ++;
                }
                numFlag = true;
            } else {
                numFlag = false;
            }
        }
        System.out.println("字符个数:" + size +",数字个数:" + numCount);
    }

}