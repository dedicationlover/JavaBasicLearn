package com.basic.collection.list;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试List中add方法.
 */
public class ListAddDemo {
    /**
     * 这是一个main方法.
     * @param args 入参
     */
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("jame");
        list.add("kimi");
        list.add("jone");
        System.out.println(list.size());  // 3
        list.add(null);
        System.out.println(list.size());  // 4
        list.add(null);
        System.out.println(list.size());  // 5

        // 集合作为参数,只是作为一个object存在，占一个位
        list.add(list);
        System.out.println(list.size());  // 6
        System.out.println(list);         // [jame, kimi, jone, null, null, (this Collection)]
        System.out.println(list.get(5));  // [jame, kimi, jone, null, null, (this Collection)]

        // 正确添加集合
        list.addAll(list);
        System.out.println(list.size());  // 12
        System.out.println(list);   // [jame, kimi, jone, null, null, (this Collection), jame, kimi, jone, null, null, (this Collection)]

        // 存储的是object,get的时候需要强制转型
        String str = (String) list.get(1);
        System.out.println(str);
    }
}
