package com.basic.synchronize;

/**
 * 死锁   --> 解决：生产者消费者模式
 * @author Administrator
 *
 */
public class SynchronizedDemo03 {
    public static void main(String[] args){
        Object goods = new Object();
        Object money = new Object();
        Test t1 = new Test(goods, money);
        Test2 t2 = new Test2(goods, money);
        Thread thread = new Thread(t1);
        Thread thread2 = new Thread(t2);
        thread.start();
        thread2.start();
    } // end main
} // end SynchronizedDemo03
class Test implements Runnable {
    Object goods;
    Object money;
    public Test(Object goods, Object money) {
        this.goods = goods;
        this.money = money;
    }
    @Override
    public void run() {
        while (true){
            test();
        } // end while
    } // end run
    public void test(){
        synchronized(goods){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } // end try
            synchronized(money){

            } // end synchronized
        } // end synchronized
        System.out.println("give money");
    } // end test
} // end Test
class Test2 implements Runnable {
    Object goods;
    Object money;
    public Test2(Object goods, Object money) {
        this.goods = goods;
        this.money = money;
    }
    @Override
    public void run() {
        while (true){
            test();
        } // end while
    } // end run
    public void test(){
        synchronized(money){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } // end try
            synchronized(goods){

            } // end synchronized
        } // end synchronized
        System.out.println("give good");
    } // end test
} // end Test2

