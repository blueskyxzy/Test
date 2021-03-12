package com.xzy.test.xzy;

import java.io.*;
import java.util.*;

/**
 * @author: xzy
 * @create: 2021-03-04
 *
 * 感觉要用分词算法，利用词汇之前相似的特征
 **/

//        字典查找的算法。
//        input:
//        1. input_file
//        每一行有一个词汇，如“浙江”, “浙江大学”, “美国”, “美国政府”。该文件可能有100万词
//        2. a document，字符串。一般有2000字左右。如 “美国规划协会中国办公室揭牌仪式及美国规划领域合作研讨会在浙江大学城乡规划设计研究院208会议室举行。美国规划协会CEO James Drinan，国际项目及外联主任Jeffrey Soule先生，浙江大学党委副书记任少波，浙江大学控股集团领导杨其和，西湖区政府代表应权英副主任....”
//
//        output:
//        输出document中出现的词汇,以及其位置列表。如
//        {
//        “美国” : [ 0,16, ....],
//        “浙江”: [28, ...]
//        “浙江大学”: [28, ...]
//        }

class DictionarySearcher{

    static Map<String, Integer> dic = new HashMap<>();

    static int max = 0;

    /**
    * 读文件词汇放入map,并计算最大词汇长度
    */
    public DictionarySearcher(String filename) {
        File file = new File(filename);
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));//构造一个BufferedReader类来读取文件
            String s;
            while((s = br.readLine())!=null){
                int len = s.length();
                dic.put(s, len);
                if (len > max) max = len;
            }
            br.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static HashMap<String, ArrayList<Integer>> search(String document) {
        int len = document.length();
        if (document == null || len < 2)
            return null;
        HashMap<String, ArrayList<Integer>> result = new HashMap<>();
        // 词汇长度从2到max
        for(int i = 2; i <=max && i < len; i++)
            for(int j = 0; j < len - i + 1; j++){
                String s = document.substring(j, j + i);
                if(dic.containsKey(s)){
                    ArrayList<Integer> out = result.get(s);
                    if (out != null){
                        out.add(j);
                    } else {
                        ArrayList<Integer> list = new ArrayList<>();
                        list.add(j);
                        result.put(s, list);
                    }
                }
            }
        return result;
    }

    public static void main(String [] args) {
        DictionarySearcher dictionary = new DictionarySearcher("/Users/zhiyuanxi/Desktop/dictionary.txt");
        String document = "美国规划协会中国办公室揭牌仪式及美国规划领域合作研讨会在浙江大学城乡规划设计研究院208会议室举行。美国规划协会CEO James Drinan，国际项目及外联主任Jeffrey Soule先生，浙江大学党委副书记任少波，浙江大学控股集团领导杨其和，西湖区政府代表应权英副主任";
        HashMap<String, ArrayList<Integer>> result = dictionary.search(document);
    }
}
