package com.basic.thread.threadGroup;

/**
 * 创建了几条线程，它们分别属于不同的线程组，程序还将一个线程组设置成后台线程组
 */
class TestThread extends Thread {
    // 提供指定线程名的构造器
    public TestThread(String name) {
        super(name);
    }
    public TestThread(ThreadGroup group, String name) {
        super(group, name);
    }

    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println(getName() + "线程的i变量" + i);
        }
    }
}
public class ThreadGroupTest {
    public static void main(String[] args) {
        // 获取主线程所在的线程组，这是所有线程默认的线程组
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        System.out.println("主线程组的名字：" + mainGroup.getName());
        System.out.println("主线程组是否是后台线程组：" + mainGroup.isDaemon());
        new TestThread("主线程组的线程").start();
        ThreadGroup tg = new ThreadGroup("新线程组");
        tg.setDaemon(true);
        System.out.println("tg线程组是否是后台线程组：" + tg.isDaemon());
        TestThread tt = new TestThread(tg, "tg组的线程甲");
        tt.start();
        new TestThread(tg, "tg组的线程乙").start();
    }
}
