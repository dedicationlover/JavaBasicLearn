package com.collection.queue;

import java.util.PriorityQueue;

public class TestPriorityQueue {
    public static void main(String[] args) {
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.add("kiji");
        priorityQueue.add("lkjh");
        priorityQueue.add("wuzetian");
        priorityQueue.add("aji");
//        priorityQueue.add(null);     // 不能插入null  汇报NullPointerException
        System.out.println(priorityQueue);  // [aji, kiji, wuzetian, lkjh]
        System.out.println(priorityQueue.peek());  // aji
        System.out.println(priorityQueue);   // [aji, kiji, wuzetian, lkjh]
        System.out.println(priorityQueue.poll());  // aji
        System.out.println(priorityQueue);   // [kiji, lkjh, wuzetian]
    }
}
