package com.xzy.test.testListFilter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

/**
 * Created by xzy on 18/8/13  .
 */


/**
 * java8的例子，使用lambda表达式
 * @author zhangchao
 *
 */
class Book {
    private String id;
    private String name;

    public Book(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Book() {
        super();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}


public class TestListFilter {

    /**
     * 准备书的列表数据
     * @return
     */
    public static List<Book> prepareData() {
        // 准备书的列表，id是从1到10
        List<Book> bookList = new ArrayList<Book>();
        for (int i = 1; i < 11; i++) {
            bookList.add(new Book(String.valueOf(i), "book"+i));
        }
        return bookList;
    }

    public static void main(String[] args) {
        List<Book> bookList = prepareData();

        // 要被找出的书的ID
        ArrayList<String> ids = new ArrayList<String>();
        ids.add("3");
        ids.add("6");
        ids.add("8");
        ids.add("9");

        // 存放过滤结果的列表
        List<Book> result = null;

        // 使用lambda表达式过滤出结果并放到result列表里
        result = bookList.stream()
                .filter((Book b) -> ids.contains(b.getId()))
                .collect(Collectors.toList());
        List<Book> result2 = bookList;
        result2.removeIf(b -> ids.contains(b.getId()));
        // 打印结果列表
        if (result != null && !result.isEmpty()) {
            result.forEach((Book b) -> System.out.println(b.getId() + "  " + b.getName()));
        }

        System.out.println("______________________");
        if (result2 != null && !result2.isEmpty()) {
            result2.forEach((Book b) -> System.out.println(b.getId() + "  " + b.getName()));
        }
    }
}
