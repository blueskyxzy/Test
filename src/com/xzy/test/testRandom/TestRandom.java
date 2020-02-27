package com.xzy.test.testRandom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by xzy on 2019/10/23  .
 */

public class TestRandom {

    public static void main(String[] args) {
//        testC()
//        testA();
//        int[] ints = testB(10, 999999);
//        String[] ints = test(10);
//        for (String i:ints){
//            System.out.println(i);
//        }
//        String[] array = test2(10);
//        for (String i:array){
//            System.out.println(i);
//        }
//        System.out.println("---------");
//        String[] arr = {"000000", "000001", "000002", "000003",  "000004", "000005"};
//        String[] array2 = distinctRandomArray1(6, arr);
//        for (String i:array2){
//            System.out.println(i);
//        }

//        System.out.println("---------");
//        String[] array3 = distinctRandomArray2(5, arr);
//        for (String i:array3){
//            System.out.println(i);
//        }
//        System.out.println("---------");
//        String[] array4 = distinctRandomArray(6, 6, arr);
//        for (String i:array4){
//            System.out.println(i);
//        }
//        System.out.println("---------");
        List<String> list = new ArrayList<>();
        list.add("000000");
        list.add("000001");
        list.add("000002");
        list.add("000003");
        list.add("000004");
        list.add("000005");
        // 1006    10000       0.8s
        //1006    20000       2s
        //1006    50000       10s
        //1006    100000      34s
        //1006    200000      144s
        // 2,3万以上性能明显下降，耗时明显
        list = MyRandomUtils.distinctRandomList(10010, 6, list);
//        List<String> randomList = distinctRandomList(6, 6, list);
//        for(String i: randomList){
//            System.out.println(i);
//        }
        int numberTotal = 10010;
        if (numberTotal > 1000){
            // 大于1000的分组分批执行，每批500
            List<List<String>> insertGroupList= new ArrayList<>(); // 分组
            int i = 0;
            List<String> insertList = new ArrayList<>();
            for (int j = 0; j < numberTotal; j++){
                insertList.add(list.get(j));
                i++;
                if (i >= 500){
                    insertGroupList.add(insertList);
                    i = 0;
                    insertList = new ArrayList<>();
                }
                if (j == numberTotal - 1){
                    insertGroupList.add(insertList);
                }
            }
            System.out.printf("------------------");
        }
        System.out.printf("-----------------");
        long startTime = System.currentTimeMillis();  //开始测试时间
        List<String> randomList2 =MyRandomUtils.distinctRandomList(1000, 6, list);
        long endTime = System.currentTimeMillis();  //开始测试时间
        System.out.printf("耗时：" + (endTime - startTime) + "ms");
        System.out.printf("-----------------");
        // 生成10万可以重复的6位随机数耗时0.3s，100万耗时2s
//        long startTime1 = System.currentTimeMillis();  //开始测试时间
//        List<String> myList = new ArrayList<>();
//        for (int i=0; i < 1000000; i++){
//            String s = MyRandomUtils.MyRandom(6);
//            myList.add(s);
//        }
//        long endTime1 = System.currentTimeMillis();  //开始测试时间
//        System.out.printf("耗时：" + (endTime1 - startTime1) + "ms");
//        for(String i: randomList2){
//            System.out.println(i);
//        }
    }

    private static int[] testB(int sz, int size){
        long startTime=System.currentTimeMillis();  //开始测试时间
        Random rd = new Random();
        int[] rds = new int[sz];//随机数数组
        int n = 0;//序号
        List<Integer> lst = new ArrayList<Integer>(); //存放有序数字集合
        //获取随机数数组, 里面有重复数字
        while (n < rds.length) {
            lst.add(n);
            rds[n++] = rd.nextInt(size);
        }
        //把随机数和有序集合进行匹对, 把随机数在集合出现的数字从集合中移除掉.
        for (int i = 0; i < rds.length; i++) {
            for (int j = 0; j < lst.size(); j++) {
                if (rds[i] == lst.get(j)) {
                    lst.remove(j);
                    break;
                }
            }
        }
        //把数组中重复的第二个数字用集合的第一个数字替换掉, 并移除掉数组的第一个数字
        for (int i = 0; i < rds.length; i++) {
            for (int j = 0; j < rds.length; j++) {
                if (i != j && rds[i] == rds[j]) {
                    rds[j] = lst.get(0);
                    lst.remove(0);
                    break;
                }
            }
        }
        //得到的  rds  数组就是不重复的随机数组
        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("自定义代码运行时间： "+(endTime-startTime)+"ms");
        return rds;
    }

    /**
     * count是要取的随机数的个数，arround是随机数在哪个范围取 [0~arround）
     */
    public static int[] testA(int count, int arround){
        long time = System.currentTimeMillis();//依旧是测试时间。。。
        Random random = new Random();
        ArrayList<Integer> numList = new ArrayList<>(); //集合存放可以取的数值范围
        int[] rand = new int[count]; //随机数组
        for (int i = 1; i < arround+1; i++) {
            numList.add(i); //循环把范围内的数值顺序放入结集合
        }
        for (int i = 0; i < count; i++) {
            int n = random.nextInt(arround - i); // 随机范围内的数作为集合的下标
            rand[i] = numList.get(n); //找到这下标的数值并赋值给随机数组
            numList.remove(n);  //把该值从数组移除
        }
        long time2 = System.currentTimeMillis();
        System.out.println("testA运行时间： " + (time2 - time) + "ms");
        return rand;
    }

    public static int[] testC(){
        Random random = new Random();
        int[] randomArray=new int[10];
        int temp;
        for (int i = 0; i < 10; i++) {
//            temp=random.nextInt(999999); 随机数可能小于100000，导致转换的字符串不是6位

            temp = (int) ((Math.random() * 9 + 1) * 100000);  // 这样生成6位随机数都是大于100000，小于1000000的，但范围也小了，少了首位是0的数
            System.out.print(temp + " ");
            randomArray[i]=temp;
            for (int j=0;j<i;j++){
                if (randomArray[i]==randomArray[j]){
                    i--;
                    break;
                }
            }
            System.out.println(Arrays.toString(randomArray));
        }
        return randomArray;
    }

    /**
     * 此方法生成total个不重复的size位随机数字符串首位可以是0，生成字符串总数不能大于等于2的size次方，数量越大性能越低
     */
    public static String[] test(int total){
        String[] randomArray = new String[total];
        String temp;
        for (int i = 0; i < total; i++) {
            temp = MyRandom(6);
            randomArray[i] = temp;
            for (int j=0;j<i;j++){
                if (randomArray[i].equals(randomArray[j])){
                    i--;
                    break;
                }
            }
        }
        return randomArray;
    }

    /**
     * 此方法生成total个不重复的size位随机数字符串首位可以是0，且生成的字符串不在已有的字符串数组arr中，生成字符串总数不能大于等于2的size次方，数量越大性能越低
     */
    public static String[] distinctRandomArray1(int total, String[] arr){
        String[] randomArray = new String[total];
        String temp;
        int length = arr.length;
        for (int i=0; i<total; i++) {
            temp = MyRandom(6);
            randomArray[i] = temp;
            for (int j=0; j<i; j++){
                if (randomArray[i].equals(randomArray[j])){
                    i--;
                    break;
                }
            }
            for (int k=0; k<length; k++){
                if (temp.equals(arr[k])){
                    i--;
                    break;
                }
            }
        }
        return randomArray;
    }

    /**
     * 此方法生成total个不重复的size位随机数字符串数组，首位可以是0，且生成的字符串不在已有的字符串数组arr中，生成字符串总数不能大于等于2的size次方，数量越大性能越低
     */
    public static String[] distinctRandomArray(int total, int size, String[] arr){
        String[] randomArray = new String[total];
        String temp;
        int length = arr.length;
        for (int i=0; i<total; i++) {
            temp = MyRandom(size);
            randomArray[i] = temp;
            boolean hasRepeated = false;
            for (int j=0; j<i; j++){
                if (randomArray[i].equals(randomArray[j])){
                    i--;
                    hasRepeated = true;
                    break;
                }
            }
            if (!hasRepeated){
                for (int k=0; k<length; k++){
                    if (temp.equals(arr[k])){
                        i--;
                        break;
                    }
                }
            }

        }
        return randomArray;
    }

    /**
     * 此方法生成total个不重复的size位随机数字符串列表，首位可以是0，且生成的字符串不在已有的字符串列表list中，生成字符串总数不能大于等于2的size次方，数量越大性能越低
     */
    public static List<String> distinctRandomList(int total, int size, List list){
        List<String> randomList = new ArrayList<>();
        String temp;
        int length = list.size();
        for (int i=0; i<total; i++) {
            temp = MyRandom(size);
            boolean hasRepeated = false;
            for (int j=0; j<i; j++){
                if (temp.equals(randomList.get(j))){
                    hasRepeated = true;
                    break;
                }
            }
            if (!hasRepeated){
                for (int k=0; k<length; k++){
                    if (temp.equals(list.get(k))){
                        break;
                    }
                }
            }
            if (hasRepeated){
                i--;
            } else {
                randomList.add(temp);
            }

        }
        return randomList;
    }

    /**
     * 此方法生成一个size位随机数字符串,首位可以是0不影响范围
     */
    public static String MyRandom(int size){
        String sources = "0123456789";
        Random rand = new Random();
        StringBuffer flag = new StringBuffer();
        for (int j=0; j<size; j++){
            flag.append(sources.charAt(rand.nextInt(9)) + "");
        }
        return flag.toString();
    }


    public static String[] test2(int total){
        String[] randomArray=new String[total];
        String temp;
        for (int i = 0; i < total; i++) {
            temp = MyRandom2();
            randomArray[i]=temp;
            for (int j=0;j<i;j++){
                if (randomArray[i].equals(randomArray[j])){
                    i--;
                    break;
                }
            }
        }
        return randomArray;
    }

    public static String MyRandom2(){
        String[] sources = {"000000", "000001", "000002", "000003",  "000004", "000005", "000006", "000007", "000008", "000009", "123456", "654321"};
        Random rand = new Random();
        String flag = sources[rand.nextInt(12)];
        return flag;
    }

    public static String[] distinctRandomArray2(int total, String[] arr){
        String[] randomArray = new String[total];
        String temp;
        int length = arr.length;
        for (int i=0; i<total; i++) {
            temp = MyRandom2();
            randomArray[i] = temp;
            for (int j=0; j<i; j++){
                if (randomArray[i].equals(randomArray[j])){
                    i--;
                    break;
                }
            }
             for (int k=0; k<length; k++){
                if (temp.equals(arr[k])){
                    i--;
                    break;
                }
            }
        }
        return randomArray;
    }

    /**
     * 此方法生成total个不重复的size位随机数字符串列表，首位可以是0，且生成的字符串不在已有的字符串列表list中，生成字符串总数不能大于等于2的size次方，数量越大性能越低
     */
    public static List<String> distinctRandomList2(int total, List list){
        long startTime=System.currentTimeMillis();  //开始测试时间
        List<String> randomList = new ArrayList<>();
        String temp;
        int length = list.size();
        for (int i=0; i<total; i++) {
            temp = MyRandom2();
            boolean hasRepeated = false;
            for (int j=0; j<i; j++){
                if (temp.equals(randomList.get(j))){
                    hasRepeated = true;
                    break;
                }
            }
            if (!hasRepeated){
                for (int k=0; k<length; k++){
                    if (temp.equals(list.get(k))){
                        hasRepeated = true;
                        break;
                    }
                }
            }
            if (hasRepeated){
                i--;
            } else {
                randomList.add(temp);
            }

        }
        long endTime=System.currentTimeMillis();  //开始测试时间
        return randomList;
    }

}
