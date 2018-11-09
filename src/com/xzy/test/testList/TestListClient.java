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
        strList2.remove(2);
        strList2.remove("222222");
    }
}
