package com.collection.map;

import java.util.EnumMap;

/**
 *  EnumMap 的使用
 */
public class EnumMapTest {
    public static void main(String[] args) {
        EnumMap enumMap = new EnumMap(Season.class);
        enumMap.put(Season.FALL, "cool");
        enumMap.put(Season.SPRING, "worm");
        System.out.println(enumMap);  // {SPRING=worm, FALL=cool}

        enumMap.put(Season.SPRING, "2");
        System.out.println(enumMap); // {SPRING=2, FALL=cool}
    }
}
enum Season {
    SPRING, SUMMER, FALL, WINTER
}