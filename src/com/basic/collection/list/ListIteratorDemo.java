package com.basic.collection.list;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 *  ListIterator增加了向前迭代的功能（Iterator只能向后迭代），
 *  而且ListIterator还可以通过add方法向List集合中添加元素（Iterator只能删除元素）
 */
public class ListIteratorDemo {
    public static void main(String[] args){
        List list = new ArrayList();
        list.add("first");
        list.add("second");
        list.add("third");
        System.out.println(list);  // [first, second, third]
        // 获取list的ListIterator
        ListIterator listIterator = list.listIterator();
        System.out.println(listIterator.hasNext());      // true
        System.out.println(listIterator.hasPrevious());  // false
        // 向ListIterator 增加一个元素
        listIterator.add("new");
        System.out.println(listIterator.hasPrevious());  // true
        System.out.println(list);       // [new, first, second, third]
        // 使用next遍历
        while(listIterator.hasNext()){
            System.out.print(listIterator.next());
        }
        System.out.println(listIterator.previous());   // third
        // 重新获取向ListIterator
        listIterator = list.listIterator();
        while(listIterator.hasNext()){
            System.out.print(listIterator.next());  // new first second third
        }

        System.out.println("------反向迭代-----");

        while(listIterator.hasPrevious()){
            System.out.print(listIterator.previous() + " ");  // third second first new
        }
    }
}
