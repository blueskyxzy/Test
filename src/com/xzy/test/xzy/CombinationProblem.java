package com.xzy.test.xzy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: xzy
 * @create: 2021-03-04
 **/

//  穷尽集合之间的元素组合。
//        input: list of list
//        [ [‘a’, ‘b’, ‘c’], [‘1’, ‘2’, ‘3’, ‘4’, ‘5’, ‘6’] , [‘A’, ‘B’, ‘C’, ‘D’] ]
//        output:
//        [
//        [‘a’, ‘1’, ‘A’],
//        [‘a’, ‘1’, ‘B’],
//        [‘a’, ‘1’, ‘C’],
//        [‘a’, ‘1’, ‘D’],
//        ...
//        [‘c’, ‘6’, ‘D’]
//        ]

class CombinationProblem {

    //元素不能有","
    public static ArrayList<ArrayList<String>> getSetCombination(ArrayList<ArrayList<String>> input) {
        if (input == null || input.size() <= 1)
            return input;
        ArrayList<ArrayList<String>> outList;
        ArrayList<String> combine = input.get(0);
        for(int i = 1; i < input.size(); i++){
            combine = combination(combine, input.get(i));
        }
        outList = transferResult(combine);
        return outList;
    }

    private static ArrayList<ArrayList<String>> transferResult(ArrayList<String> list) {
        ArrayList<ArrayList<String>> resultList = new ArrayList<>();
        for(String s:list){
            String[] str = s.split(",");
            ArrayList<String> result = new ArrayList<>(Arrays.asList(str));
            resultList.add(result);
        }
        return resultList;
    }

    private static ArrayList<String> combination(ArrayList<String> combine, ArrayList<String> list) {
        ArrayList<String> result = new ArrayList<>();
        for(String a:combine)
            for(String b:list) {
                String c = a + "," + b;
                result.add(c);
            }
        return result;
    }

    public static void main(String [] args) {
        ArrayList<ArrayList<String>> input = new ArrayList<>();
        initInput(input);
        ArrayList<ArrayList<String>> outList = getSetCombination(input);
        for (ArrayList<String> list:outList){
            for(String s:list)
                System.out.print(s + ",");
            System.out.println();
        }
    }

    private static void initInput(ArrayList<ArrayList<String>> input) {
        ArrayList listOne = new ArrayList();
        ArrayList listTwo = new ArrayList();
        ArrayList listThree = new ArrayList();
        listOne.add("a");
        listOne.add("b");
        listOne.add("c");
        listTwo.add("1");
        listTwo.add("2");
        listTwo.add("3");
        listTwo.add("4");
        listTwo.add("5");
        listTwo.add("6");
        listThree.add("A");
        listThree.add("B");
        listThree.add("C");
        listThree.add("D");
        input.add(listOne);
        input.add(listTwo);
        input.add(listThree);
    }

}
