package com.xzy.test.testHashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TestHashMapClient {

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "11111111");
        System.out.println(map.get(1));

//        Map<Integer, String> myMap = new MyHashMap<>();
//        for(int i = 0; i<=20; i++){
//            myMap.put(i, "1111111111111111");
//        }
        Map<String, String> stringMap = new MyHashMap<>();
        for(int i = 0;i <= 20; i++){
            stringMap.put(String.valueOf(i), "2222222222222");
        }
//        for (int i =0; i <= 20; i++){
//            myMap.put(1, String.valueOf(i));
//        }
//        System.out.println(myMap.get(1));

        // 测试迭代
        Set<Integer> keys = map.keySet();

        for(Map.Entry<Integer, String> entity: map.entrySet()){
            Integer key = entity.getKey();
            String value = entity.getValue();
            System.out.println("key:" + key + ", value:" + value);
        }
    }

}