package com.xzy.test.testAnonymousInnerClass;

/**
 * Created by xzy on 18/11/14  .
 */

abstract class Bird {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract int fly();
}

class WildGoose extends Bird{

    public int fly() {
        return 20000;
    }

    public String getName() {
        return "雁";
    }

    public String getTest() {
        return "test";
    }
}

public class Test {

    public void test(Bird bird){
        System.out.println(bird.getName() + "能够飞 " + bird.fly() + "米");
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.test(new Bird() {

            public int fly() {
                return 10000;
            }

            public String getName() {
                return "大雁";
            }
        });

        Test test1 = new Test();
        test1.test(new WildGoose(){
        });

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("-----");
            }
        });
        thread1.start();

        Thread thread2 = new Thread( () -> System.out.println("In Java8, Lambda expression") );
        thread2.start();

    }
}
