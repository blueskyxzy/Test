package com.xzy.test.testHashMap;

import java.util.HashMap;
import java.util.Map;

public class TestHashMapClient {

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "11111111");
        System.out.println(map.get(1));

        Map<Integer, String> myMap = new HashMap<>();
        myMap.put(1,"11111111111111");
        System.out.println(myMap.get(1));
    }

}