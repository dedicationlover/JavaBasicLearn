package com.basic.thread.state;

/**
 * 优先级 ： 概率，没有绝对的优先级，没有先后顺序
 * MAX_PRIORITY   10
 * NORM_PRIORITY  5
 * MIN_PRIORITY   1
 * setPriority(int newPriority)
 * @author Administrator
 *
 */
public class ThreadInfo02 {

    public static void main(String[] args) throws InterruptedException {
        MyThread mt1 = new MyThread();
        MyThread mt2 = new MyThread();
        Thread thread1 = new Thread(mt1, "first");
        Thread thread2 = new Thread(mt2, "second");

        thread1.setPriority(Thread.MIN_PRIORITY);  // 执行时间最少（分配的少）
        thread2.setPriority(Thread.MAX_PRIORITY);  // 执行的多（优先，时间多）
        thread1.start();
        thread2.start();

        Thread.sleep(10);
        mt1.stop();
        mt2.stop();
    } // end main
} // end ThreadInfo02

