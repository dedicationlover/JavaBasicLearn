package com.basic.thread.state;

public class YieldDemo extends Thread{
    public static void main(String[] args) {
        YieldDemo jd = new YieldDemo();
        Thread t = new Thread(jd);  // 新增
        t.start();  //就绪
        // cpu 调度 --> 运行
        for (int i = 0; i < 1000; i++){
            if (i % 20 == 0){
                // 暂停本线程 main
                Thread.yield();
            }
            System.out.println("main....." + i);
        } // end for
    } // end main
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++){
            System.out.println("Yield....." + i);
        } // end for
    } // end run
} // end YieldDemo

