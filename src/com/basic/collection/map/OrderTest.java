package com.collection.map;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.TreeMap;

public class OrderTest {
    public static void main(String[] args) {
        System.out.println("------hashtable----");
        // 顺序与添加的顺序一致
        Hashtable<String, String> hashtable = new Hashtable();
        hashtable.put("abc", "123");
        hashtable.put("aaa", "gjk");
        hashtable.put("xxxx", "wo");
        hashtable.put("fg", "lo");
        System.out.println(hashtable);  //  {fg=lo, xxxx=wo, aaa=gjk, abc=123}

        System.out.println("------hashmap----");
        // 乱序
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("abc", "123");
        hashMap.put("aaa", "gjk");
        hashMap.put("xxxx", "wo");
        hashMap.put("fg", "lo");
        System.out.println(hashMap);  // {aaa=gjk, fg=lo, abc=123, xxxx=wo}

        System.out.println("------treemap----");
        // 按key升序
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("abc", "123");
        treeMap.put("aaa", "gjk");
        treeMap.put("xxxx", "wo");
        treeMap.put("fg", "lo");
        System.out.println(treeMap); // {aaa=gjk, abc=123, fg=lo, xxxx=wo}
    }
}
