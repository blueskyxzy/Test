package com.xzy.test.testList;

import java.util.*;

/**
 * Created by xzy on 18/11/8  .
 */

public class TestListClient {

    public static void main(String[] args) {

        List<String> strList = new ArrayList<>();
        strList.add("1111");
        strList.add("222222");
        strList.size();
        strList.add(2,"33333");
        strList.remove(1);
        strList.remove("111111");
        strList.get(1);
        System.out.println(strList);

        List<String> strList2 = new LinkedList<>();
        strList2.add("1111111");
        strList2.add(1,"22222222");
        strList2.remove(1);
        strList2.remove("222222");


        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("4");
        list1.add("5");
        list1.add("6");
        list1.add("7");
        list1.add("8");
        list1.add("9");
        list1.add("10");
        list1.add("11");
        list1.add("12");
        list1.add("13");


        list1.forEach(l-> System.out.println(l));
    }
}
