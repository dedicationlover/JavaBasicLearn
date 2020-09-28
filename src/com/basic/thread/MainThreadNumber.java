package com.basic.thread;

import java.lang.reflect.AnnotatedElement;

/**
 * hello world 运行时有多少个线程
 */
public class MainThreadNumber {
    public static void main(String[] args) {
        System.out.println("hello world");
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        ThreadGroup topGroup = group;
        while (group != null) {
            topGroup = group;
            System.out.println(topGroup.getName() + "  " + topGroup.activeCount());
            Thread[] threads = new Thread[topGroup.activeCount()];
            topGroup.enumerate(threads);
            for (int i = 0; i < threads.length; i++){
                System.out.println(threads[i].getName());
            }
            group = group.getParent();
        }
    }
}