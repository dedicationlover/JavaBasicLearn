package com.collection.map;

import java.util.IdentityHashMap;

public class TestIdentityHashMap {
    public static void main(String[] args) {
        IdentityHashMap ihm = new IdentityHashMap();
        // 添加两个
        ihm.put(new String("hanzhong"), 100);
        ihm.put(new String("hanzhong"), 70);
        // 添加一个
        ihm.put("liang", 34);
        ihm.put("liang", 45);

        System.out.println(ihm); // {liang=45, hanzhong=100, hanzhong=70}
    }
}
