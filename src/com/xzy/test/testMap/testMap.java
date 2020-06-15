package com.xzy.test.testMap;

import com.xzy.test.testHashMap.MyHashMap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class testMap {

    public static void main(String[] args) {
        Map<Long,String> map = new MyHashMap<>();
        Long key1 = Long.valueOf(1);
        Long key2 = Long.valueOf(2);
        map.put(key1,"test1");
        String v1 = map.get(key2);
        if (v1 == null){
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        String a = "";
        System.out.println("a.length="+a.length());

        Map<Long,String> m = new ConcurrentHashMap<>();
        m.put(1L, "esfs");
        System.out.printf("11");
    }

}
