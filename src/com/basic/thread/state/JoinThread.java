package com.basic.thread.state;

public class JoinThread extends Thread {
    // 提供一个有参数的构造器，用于设置该线程的名字
    public JoinThread(String name){
        super(name);
    } // end constructor

    // 重写run方法，定义线程执行体
    public void run(){
        for(int i = 0; i < 50; i++){
            System.out.println(getName() + " " + i);
        } // end for
    } // end run
    public static void main(String[] args) throws InterruptedException {
        // 启动子线程
        new JoinThread("新线程").start();
        for(int i = 0; i < 100; i++){
            if(i == 20){
                JoinThread jt = new JoinThread("被Join的线程");
                jt.start();
                // main线程调用了jt线程的join方法，main线程必须
                // 等jt执行结束才会向下执行
                jt.join();
            } // end if
            System.out.println(Thread.currentThread().getName() + " " + i);
        } // end for

    } // end main
}

