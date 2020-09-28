package com.basic.synchronize.lock;

import java.util.concurrent.locks.ReentrantLock;

public class Account {
    // 定义锁对象
    private final ReentrantLock lock = new ReentrantLock();
    private String accountNo;
    private double balance;
    public Account(){}
    public Account(String accountNo, double balance){
        this.accountNo = accountNo;
        this.balance = balance;
    } // end constructor
    // 此处省略了accountNo的setter和getter方法
    // 因此账户余额不允许随便修改，所以取消balance属性的setter方法
    public double getBalance(){
        return this.balance;
    } // end getBalance
    // 提供一个线程安全draw方法来完成取钱操作
    public void draw(double drawAmount){
        // 对同步锁进行加锁
        lock.lock();
        try {
            // 账户余额大于取钱数目
            if(balance >= drawAmount){
                // 吐出钞票
                System.out.println(Thread.currentThread().getName() + "取钱成功！吐出钞票：" + drawAmount);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 修改余额
                balance -= drawAmount;
                System.out.println("\t余额为：" + balance);
            } else{
                System.out.println(Thread.currentThread().getName() + "取钱失败！余额不足！");
            } // end if-else
        } finally {
            // 使用finally块来确保释放锁
            lock.unlock();
        } // end try-finally
    } // end drawAmount
    // 此处省略了hashCode和equals两个重写的方法
} // end Account

