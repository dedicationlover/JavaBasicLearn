package com.basic.thread.state;

/**
 * Sleep模拟网络延时
 * @author Administrator
 *
 */
public class SleepDemo02 {
    public static void main(String[] args){
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
} // end SleepDemo02
class Web12306 implements Runnable {
    private int num = 50;
    @Override
    public void run() {
        while (true){
            if (num <= 0){
                break;
            } // end if
            // 加延时
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "got a ticket, and leave "+ --num);
        } // end while
    } // end run
} // end Web12306

