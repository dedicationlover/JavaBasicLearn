package com.basic.thread.state;

/**
 * static Thread currentThread()
 * String getName()   void setName(String name)
 * boolean isAlive()
 * @author Administrator
 *
 */
public class ThreadInfo01 {
    public static void main(String[] args) throws InterruptedException {
        MyThread mt = new MyThread();
        // Thread(Runnable target, String name)
        Thread thread = new Thread(mt, "first");   //不写名字默认Thread-0
        System.out.println(thread.getName());   // first
        //	thread.setName("rename first");
        System.out.println(thread.getName());   // rename first
        System.out.println(Thread.currentThread().getName());   // main copy时粘贴为：ThreadInfo01.java
        thread.start();
        System.out.println("start status: ----->" + thread.isAlive());    // true
        Thread.sleep(10);
        mt.stop();
        System.out.println("stop status: ----->" + thread.isAlive());     // true
        Thread.sleep(20);
        System.out.println("stop sleep status: ----->" + thread.isAlive());     // false
    } // end main
} // end ThreadInfo01

class MyThread extends Thread {
    public void run() {

    }
}