package com.collection.map;

import java.util.WeakHashMap;

/**
 * WeakHashMap 存储的是key的弱引用，gc运行会回收，map中也会删除这些key-value
 */
public class WeakHashMapTest {
    public static void main(String[] args) {
        WeakHashMap weakHashMap = new WeakHashMap();
        // 三个匿名key对象（无其他引用）
        weakHashMap.put(new String("you"), new String("python"));
        weakHashMap.put(new String("it"), new String("leaf"));
        weakHashMap.put(new String("me"), new String("java"));
        // key --- 系统缓存的字符串对象（系统保留了该字符串的强引用）
        weakHashMap.put("she", "c");
        System.out.println(weakHashMap); // {me=java, you=python, she=c, it=leaf}
        System.gc();
        System.runFinalization();
        System.out.println(weakHashMap); // {she=c}
    }
}
