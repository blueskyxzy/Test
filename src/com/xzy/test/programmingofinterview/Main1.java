package com.xzy.test.programmingofinterview;

import java.util.Calendar;
import java.util.Scanner;

// 网易编程题
//[编程题] 优雅的点
//        时间限制：1秒
//
//        空间限制：32768K
//
//        小易有一个圆心在坐标原点的圆，小易知道圆的半径的平方。小易认为在圆上的点而且横纵坐标都是整数的点是优雅的，小易现在想寻找一个算法计算出优雅的点的个数，请你来帮帮他。
//        例如：半径的平方如果为25
//        优雅的点就有：(+/-3, +/-4), (+/-4, +/-3), (0, +/-5) (+/-5, 0)，一共12个点。
//
//        输入描述:
//        输入为一个整数，即为圆半径的平方,范围在32位int范围内。
//
//        输出描述:
//        输出为一个整数，即为优雅的点的个数
//
//        输入例子1:
//        25
//
//        输出例子1:
//        12

public class Main1{

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        int x = Integer.parseInt(str);
        long startTime = System.nanoTime();
        int y = calcuatePoint(x);
        System.out.println(y);
        long endTime = System.nanoTime();
        System.out.println("程序运行时间： "+(endTime - startTime)+"ns");
    }

    public static int calcuatePoint(int x){
        int a = (int) Math.sqrt(x);
        int count = 0;
        for (int i = -a; i <= a; i++){
            for (int j = -a; j <= a; j++){
                if (Math.pow(i, 2) + Math.pow(j, 2) == x){
                    count++;
                }
            }
        }
        return count;
    }
}
