package com.xzy.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xzy on 18/8/13  .
 */
// removeAll equals hashCode测试
public class User {

    private int id;
    private String name;

    public User() {}
    public User(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + id;
//        result = prime * result + ((name == null) ? 0 : name.hashCode());
//        return result;
//    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
    public static void main(String[] args) {
        List<User> list = new ArrayList<User>();
        list.add(new User(1, "1"));
        list.add(new User(2, "2"));
        list.add(new User(3, "3"));
        list.add(new User(4, "4"));
        List<User> list1 = new ArrayList<User>();
        list1.add(new User(1, "1"));
        list1.add(new User(2, "2"));
        list1.add(new User(3, "3"));
        list1.add(new User(5, "5"));
        list.removeAll(list1);
        for (User user:list){
            System.out.println("id:"+ user.getId() + ",name:" + user.getName());
        }
    }

}