package com.xzy.test.programmingofinterview;


import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by xzy on 18/12/28  .
 */

// 两个巨大素数（质数）的乘积（10 分）
//        得到两个巨大素数（质数）的乘积是简单的事，但想从该乘积分解出这两个巨大素数却是国际数学界公认的质因数分解难题。
//        这种单向的数学关系，是不对称加密RSA算法的基本原理。
//        本题给出两个大素数（128bit位）的乘积和其中一个素数，请你编程求出另一个素数。
//
//        输入格式:
//        44022510695404470886511586569647292146578314354528108825807522926455663589709 （大素数的乘积） 189193782774204832019945226750213439577 （其中一个大素数）
//
//        输出格式:
//        232684764001698545563067004009755869717 （另一个素数）
//
//        输入样例:
//        60883665878129858935918958333091530420746054622405737630613777684610994823161
//        271963475875372143777333694041058521413

//        输出样例:
//        223867067745633357281812540202957589797

public class promgramme2 {

    public static  void main(String[] args){
        Scanner s = new Scanner(System.in);
        // 依次输入两个数字
        BigInteger x = s.nextBigInteger();
        BigInteger y = s.nextBigInteger();

        System.out.println(x.divide(y));
    }
}
