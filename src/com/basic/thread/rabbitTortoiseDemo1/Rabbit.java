package com.basic.thread.rabbitTortoiseDemo1;

/**
 * 模拟龟兔赛跑
 * 1、创建多线程 继承 Thread  重写run():线程体
 * 2、应用多线程
 *    创建子类对象
 *    调用对象的start():内部由cpu管控  线程启动（不代表线程运行）
 * @author Administrator
 *
 */
public class Rabbit extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++){
            System.out.println("the rabbit had run " + i + "foots.");
        }
    } // end run

} // end Rabbit
class Tortoise extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++){
            System.out.println("the Tortoise had run " + i + "foots.");
        }
    } // end run
} // end Tortoise

