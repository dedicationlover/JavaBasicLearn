package com.basic.collection.map;

import java.util.*;

/**
 * 只读设置
 * 1、emptyXxx() 空的不可变的集合 ---> 避免空指针异常
 *    emptyList()
 *    emptySet()
 *    emptyMap()
 *  2、singletonXxx() 一个元素不可变的集合（只包含一个元素）
 *     singleton(T o)
 *     singletonList(T o)
 *     singletonMap(K key, V value)
 *  3、unmodifiableXxx()  不可变容器
 *     unmodifiableList(List<? extends T> list)
 *     unmodifiableSet(Set<? extends T> s)
 *     unmodifiableMap(Map<? extends K, ? extends V> m)
 */
public class OnlyReadDemo {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("wang", "wang");
        map.put("zhang", "zhang");
        // 只读设置
        Map<String, String> unmodMap = Collections.unmodifiableMap(map);
//        unmodMap.put("zhao", "zhao");   // java.lang.UnsupportedOperationException

        // 一个元素的容器测试
        List<String> list = Collections.singletonList(new String());
//        list.add("qian");  // java.lang.UnsupportedOperationException
        System.out.println(list.size());  // 1
    }
    public static Set<String> operator(Set<String> s) {
        if (s == null) {
            return Collections.EMPTY_SET;  // 避免外部获取时 NullPointerException
        }
        return s;
    }

}
