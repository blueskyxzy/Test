package com.xzy.test.interview2;

class Person {
    String name;
    String gender;
    
    public Person() {
        
    }
    public Person(String n, String g) {
        name = n;
        gender = g;
    }

    
    //test:this作返回值
    Person reThis1() {
        Person per = new Person("lu","female");
        System.out.println("reThis1 per:" + per.name);
        return this;
    }
    Person reThis2() {
        Person per = reThis1();
        System.out.println("reThis2 per:" + per.name);
        return this;
    }
    Person reThis3() {
        name = "ma";
        return this;
    }
    static void equRefer(Person per1, Person per2) {
        if(per1 == per2)
            System.out.println("per1指向的对象没有改变，仍与per2指向同一个对象");
        else
            System.out.println("per1指向的对象已改变，与per2指向不同的对象");
        System.out.println("per1:" + per1.name);
        System.out.println("per2:" + per2.name);
    }
    
}

class Student extends Person {
    String stuNumber;
    String score;
    
    public Student(String n, String g) {
        super(n,g);
    }
    
    Person reThis1() {
        Person per = new Person("luPS","female");
        System.out.println("reThis1 per S:" + per.name);
        return this;
    }

    Student reThis2() {
        Person per = reThis1();
        System.out.println("reThis2 per S:" + per.name);
        return this;
    }

}


public class TestMain {
    public static void main(String[] args) {
        
        Person per1 = new Person("liuP","female");
        Person per2 = per1;
        Person per3;
        Person per4;
        Student stu1 = new Student("liuS","female");
        Student stu2 = stu1;
        
        //追加2017.11.25
        per1 = stu1.reThis3();
        if( per1 == stu1 )
            System.out.println("per1与stu1指向同一个子类对象");
        else
            System.out.println("per1与stu1指向不同的对象");
            
        per1 = stu1.reThis1();
        if( per1 == stu1 )
            System.out.println("per1与stu1指向同个子类对象");
        else
            System.out.println("per1与stu1指向不同的对象");

        per3 = stu1;
        per4 = per3.reThis3();   //
        if( per4 == per3 )
            System.out.println("per4与per3指向同一个子类对象");
        else
            System.out.println("per4与per3指向不同的对象");
        
        per3 = stu1;
        per4 = per3.reThis1();   //
        if( per4 == per3 )
            System.out.println("per4与per3指向同一个子类对象");
        else
            System.out.println("per4与per3指向不同的对象");
        
        stu1 = (Student) per3.reThis2(); //向下转型 
        if( stu1 == per3 )
            System.out.println("stu1与per3指向同一个子类对象");
        else
            System.out.println("stu1与per3指向不同的对象");
    }

}