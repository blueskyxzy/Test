package testClone;

import javax.jws.soap.SOAPBinding;

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
    }
}
