package com.basic.synchronize.producerConsumer;

/**
 * 一个场景，共同的资源
 * 生产者消费者模式   信号灯法
 * wait：阻塞，等待，释放锁    sleep：不释放锁
 * notify()|notifyAll() ：唤醒
 * @author Administrator
 *
 */
public class Movie {
    private String name;
    // flag = true:生产者生产，消费者等待，生产完成后通知消费者消费
    // flag =false:消费者消费，生产者等待，消费完后通知生产者生产
    private boolean flag = true;
    /**
     * 播放
     * @param name
     */
    public synchronized void play(String name){
        if (!flag){  // 生产者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } // end if
        // 开始生产
        System.out.println("creating....");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 生产完毕，通知消费
        this.name = name;
        System.out.println("created --->" + this.name);
        this.notify();
        // 生产者停下
        this.flag = false;
    } // end play
    public synchronized void watch(){
        if (flag){  // 消费者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 消费者开始消费
        System.out.println("xiaofeiing...");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 消费完毕
        System.out.println("播放play " + name);
        // 通知生产
        this.notify();
        // 消费者阻塞
        this.flag = true;
    } // end watch
} // end Movie

