package com.basic.thread;

/**
 * ThreadLocal的作用
 */
class Account1 {
    /**
     * 定义一个ThreadLocal类型的变量，该变量将是一个线程局部变量
     * 每个线程都会保留该变量的一个副本
     */
    private ThreadLocal<String> name = new ThreadLocal<>();
    public Account1(String str) {
        this.name.set(str);
        // 下面代码看到输出“初始名”
        System.out.println("-----" + this.name.get());
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }
}

class MyTest extends Thread {
    // 定义一个Account属性
    private Account1 account;
    public MyTest(Account1 account, String name) {
        super(name);
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (i == 6) {
                account.setName(getName());
            }
            System.out.println(account.getName() + "账户的 i = " + i);
        }
    }
}

public class ThreadLocalTest {
    public static void main(String[] args) {
        // 启动两条线程，两条线程共享同一个Account
        Account1 at = new Account1("初始名");
        new MyTest(at, "线程甲").start();
        new MyTest(at, "线程乙").start();
    }
}
