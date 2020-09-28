package com.basic.collection.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *  比较ArrayList和LinkedList迭代读取的速度
 */
public class TestPerformance {
    public static void main(String[] args){
        // 穿入list的元素
        String[] strings = new String[9999999];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = String.valueOf(i);
        }
        // 给list添加元素
        ArrayList arrayList = new ArrayList();
        for (String string : strings) {
            arrayList.add(string);
        }
        LinkedList linkedList = new LinkedList();
        for (String string : strings) {
            linkedList.add(string);
        }
        // 迭代访问ArrayList,统计遍历时间
        long startTime = System.currentTimeMillis();
        for (Iterator it = arrayList.iterator(); it.hasNext(); ) {
            it.next();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("arraylist: " + (endTime-startTime));  // 5
        startTime = System.currentTimeMillis();
        for (Iterator it = linkedList.iterator(); it.hasNext(); ) {
            it.next();
        }
        endTime = System.currentTimeMillis();
        System.out.println("linkedlist: " + (endTime - startTime));  // 33
    }
}
