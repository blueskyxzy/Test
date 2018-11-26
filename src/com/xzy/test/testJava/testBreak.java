package com.xzy.test.testJava;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xzy on 18/11/26  .
 */

public class testBreak {
    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++){
//            for (int j = 0; j < 20; j++){
//                if (j>10)
//                    break;
//                System.out.println("i="+ i + ",j="+j);
//            }
//        }
        List<Long> xList= new ArrayList<>();
        List<Long> yList = new ArrayList<>();
        List<XYOBJ> xyList = new ArrayList<>();

        xList.add(1L);
        xList.add(2L);
        yList.add(1L);
        yList.add(2L);
        yList.add(3L);

        XYOBJ xyobj1 = new XYOBJ(1L, 2L);
        xyList.add(xyobj1);
        for (Long x: xList){
            for (Long y : yList){
                boolean needAdd = true;
                for (XYOBJ xyobj :xyList){
                    Long xInDB = xyobj.getX();
                    Long yInDB = xyobj.getY();
                    if (xInDB.equals(x) && yInDB.equals(y)) {
                        needAdd = false;
                        break;
                    }
                }
                if (needAdd){
                    System.out.println("need create x:" + x + ",y:" + y);
                }
            }
        }
    }

}
