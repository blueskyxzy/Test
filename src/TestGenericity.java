/**
 * Created by xzy on 18/6/12  .
 */

// 测试泛型，从Object到泛型
//    泛型就是变量类型的参数化。在使用泛型前，存入集合中的元素可以是任何类型的，
//    当从集合中取出时，所有的元素都是Object类型，需要进行向下的强制类型转换，转换到特定的类型。
//    而强制类型转换容易引起运行时错误。
//     泛型类型参数只能被类或接口类型赋值，不能被原生数据类型赋值，原生数据类型需要使用对应的包装类。
public class TestGenericity {


    public static void main(String[] args) {
        // 定义泛型类Gen的一个Integer版本
        Test<Integer> intOb = new Test<Integer>(88);
        intOb.showType();
        int i = intOb.getOb();
        System.out.println("value= " + i);

        // 定义泛型类Gen的一个String版本
        Test<String> strOb = new Test<String>("Hello Gen!");
        strOb.showType();
        String s = strOb.getOb();
        System.out.println("value= " + s);


        System.out.println("----------------------------------");
        Test2 intOb2 = new Test2(new Integer(88));
        intOb2.showTyep();
        int i2 = (Integer) intOb2.getOb();
        System.out.println("value= " + i2);
        // 定义类Gen2的一个String版本

        Test2 strOb2 = new Test2("Hello Gen!");

        strOb2.showTyep();

        String s2 = (String) strOb2.getOb();

        System.out.println("value= " + s2);
    }



    }
class Test<T> {

    private T ob; // 定义泛型成员变量

    public Test(T ob) {

        this.ob = ob;

    }


    public T getOb() {

        return ob;

    }


    public void setOb(T ob) {

        this.ob = ob;

    }

    public void showType() {

        System.out.println("T的实际类型是: " + ob.getClass().getName());

    }

}

class Test2 {

    private Object ob; // 定义一个通用类型成员

    public Test2(Object ob) {

        this.ob = ob;

    }

    public Object getOb() {

        return ob;

    }

    public void setOb(Object ob) {

        this.ob = ob;

    }

    public void showTyep() {

        System.out.println("T的实际类型是: " + ob.getClass().getName());

    }

}
