package com.basic.synchronize;

public class SynchronizedDemo {
    public static void main(String[] args) {
        // 真实角色
        Web12306 tickets = new Web12306();
        // 代理
        Thread th1 = new Thread(tickets, "黄牛1号");
        Thread th2 = new Thread(tickets, "黄牛2号");
        Thread th3 = new Thread(tickets, "黄牛3号");
        // 启动线程
        th1.start();
        th2.start();
        th3.start();
    } // end main
} // end SynchronizedDemo
class Web12306 implements Runnable {
    private int num = 10;
    private boolean flag = true;
    @Override
    public void run() {
        while (flag){
            //	test1();    // 不正确    重复、负数
            test2();    // 正确
            //	test3();    // 正确
            //	test4();    // 不正确    负数
            //	test5();    // 不正确    重复、负数
            //	test6();    // 不正确    负数
        } // end while
    } // end run
    // 线程不安全 锁定资源不正确
    public void test6(){
        if (num <= 0){
            flag = false;
            return ;
        } // end if
        // 加延时
        synchronized(this){
            try {
                Thread.sleep(500);  // 模拟延时
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "got a ticket, and leave "+ --num);
        } // end synchronized
    } // end test6
    // 锁定num   线程不安全 锁定资源不正确
    public void test5(){
        synchronized((Integer)num){
            if (num <= 0){
                flag = false;
                return ;
            } // end if
        } // end synchronized
        // 加延时
        try {
            Thread.sleep(500);  // 模拟延时
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "got a ticket, and leave "+ --num);
    } // end test5
    // 锁定范围不正确   输出和改写没有包括
    public void test4(){
        synchronized(this){
            if (num <= 0){
                flag = false;
                return ;
            } // end if
        } // end synchronized
        // 加延时
        try {
            Thread.sleep(500);  // 模拟延时
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "got a ticket, and leave "+ --num);
    } // end test4
    // 同步块  线程安全 锁定正确
    public void test3(){
        synchronized(this){
            if (num <= 0){
                flag = false;
                return ;
            } // end if
            // 加延时
            try {
                Thread.sleep(500);  // 模拟延时
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "got a ticket, and leave "+ --num);
        } // end synchronized
    } // end test3
    // 同步方法  线程安全的 锁定正确
    public synchronized void test2(){
        if (num <= 0){
            flag = false;
            return ;
        } // end if
        // 加延时
        try {
            Thread.sleep(500);  // 模拟延时
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "got a ticket, and leave "+ --num);
    } // end test2
    // 线程不安全
    public void test1(){
        if (num <= 0){
            flag = false;
            return ;
        } // end if
        // 加延时
        try {
            Thread.sleep(500);  // 模拟延时
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "got a ticket, and leave "+ --num);
    } // end test1
} // end Web12306

