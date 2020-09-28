package com.basic.thread.state;

/**
 * join ： 合并线程
 * Thread.join()  join(long millis)  join(long millis)
 * @author Administrator
 *
 */
public class JoinDemo extends Thread {
    public static void main(String[] args) throws InterruptedException {
        JoinDemo jd = new JoinDemo();
        Thread t = new Thread(jd);  // 新增
        t.start();  //就绪
        // cpu 调度 --> 运行
        for (int i = 0; i < 1000; i++){
            if (50 == i){
                t.join();  // main 阻塞
            }
            System.out.println("main....." + i);
        } // end for
    } // end main
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++){
            System.out.println("Join....." + i);
        } // end for
    } // end run
} // end JoinDemo
