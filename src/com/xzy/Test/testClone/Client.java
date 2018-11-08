package com.xzy.test.testClone;

/**
 * Created by xzy on 18/8/21  .
 */

// 测试浅拷贝和对象赋值
public class Client {
    public static void main(String[] args){
        User userA = new User("张三", 15L);
        User userB = new User("王五", 27L);
        User userC = userA;
        userA = userB;       // 对象赋值是浅拷贝，地址相同
        userA.setAge(16L);
        userA.setAge(17L);
        System.out.println(userA.toString());
        System.out.println(userB.toString());
        System.out.println(userC.toString());

        User principleA = new User("王校长", 50L);
        User principleB = new User("张校长", 20L);
        School schoolA = new School("天津大学", principleA);
        School schoolB = schoolA;
        schoolB.setName("南开大学");
        schoolB.setPrinciple(principleB);

        System.out.println(schoolA.toString());
        System.out.println(schoolB.toString());
    }
}
