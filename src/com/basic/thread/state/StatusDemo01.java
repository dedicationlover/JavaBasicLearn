package com.basic.thread.state;

public class StatusDemo01 {
    public static void main(String[] args) {
        Study s = new Study();
        new Thread(s).start();
        for (int i = 0; i < 10; i++){
            if (5 == i){
                s.stop();
            } // end if
            System.out.println("main-----" + i);
        } // end for
    } // end main
} // end StatusDemo01
class Study implements Runnable {
    // 1）、线程类中定义线程体使用的标识
    private boolean flag = true;
    @Override
    public void run() {
        // 2）、线程体内使用该标识
        while (flag){
            System.out.println("study thread....");
        } // end while

    } // end run
    // 对外提供方法改变标识
    public void stop(){
        flag = false;
    } // end stop
} // end Study

