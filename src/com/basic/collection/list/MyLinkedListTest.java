package com.basic.collection.list;

import sun.plugin.javascript.navig.Link;

import java.util.LinkedList;

public class MyLinkedListTest {
    public static void main(String[] args) {
        MyLinkedList  myLinkedList = new MyLinkedList();
        myLinkedList.add("123456789");
        myLinkedList.add(new Object());
//        myLinkedList.add(null);
        myLinkedList.add(new Integer(15));
        System.out.println(myLinkedList.size());  // 3

        // linkedlist添加元素是从head or tail添加
        // add()方法默认添加到对尾
        // 双向队列
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("first");
        linkedList.add("second");
        linkedList.add("third");
        System.out.println(linkedList);   // [first, second, third]
    }
}
