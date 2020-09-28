package com.basic.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用线程池来执行指定Runnable对象所代表的任务
 * 创建了Runnable实现类之后程序没有直接创建线程、启动线程来执行该Runnable任务，
 * 而是通过线程池来执行该任务
 */

// 实现Runnable接口来定义一个简单的线程
class TestThread1 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "的 i = " + i);
        }
    }
}
public class ThreadPoolTest {
    public static void main(String[] args) {
        // 创建一个具有固定线程数（6）的线程池
        ExecutorService pool = Executors.newFixedThreadPool(6);
        // 向线程池中提交两个线程
        pool.submit(new TestThread1());
        pool.submit(new TestThread1());
        // 关闭线程池
        pool.shutdown();
    }
}
