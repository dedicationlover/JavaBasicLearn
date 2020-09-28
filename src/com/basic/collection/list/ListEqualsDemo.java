package com.basic.collection.list;

import java.util.ArrayList;
import java.util.List;

/**
 *  List使用equals方法判断两个对象是否相等
 */
public class ListEqualsDemo {
    public static void main(String[] args){
        List list = new ArrayList();
        list.add("jame");
        list.add("kimi");
        list.add("jone");
        System.out.println(list);  // [jame, kimi, jone]
        /* list调用该对象的equals()依次与list集合元素比较，若返回true，则认为相对，即删除返回 */
        list.remove(new AA());
        System.out.println(list);  // [kimi, jone]
    }
}

class AA {
    @Override
    public boolean equals(Object o){
        return true;
    }
}