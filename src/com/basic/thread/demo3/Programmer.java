package com.basic.thread.demo3;

/**
 * 推荐使用Runnable创建线程
 * 好处：
 *    1）避免单继承的局限性
 *    2）便于共用资源
 *
 * 使用Runnable创建线程
 * 1、类 实现Runnable接口 + 重写run()   → 真实角色类
 * 2、启用多线程  使用静态代理
 *     创建真实角色
 *     创建代理角色 + 真实角色的引用
 *     调用.start()  启动线程
 * @author Administrator
 */
public class Programmer implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++){
            System.out.println("coding buging debug!");
        }
    } // end run
} // end Programmer

