package com.basic.thread.timer;

import java.util.Date;
/**
 * schedule(TimerTask task, Date time)
 * schedule(TimerTask task, Date firstTime, long period)
 * 自学quartz
 */
import java.util.Timer;
import java.util.TimerTask;
public class TimerDemo01 {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){

            @Override
            public void run() {
                System.out.println("我是一个粉刷匠，粉刷本领强，我要把那小房子，刷的很漂亮。");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }}, new Date(System.currentTimeMillis() + 1000), 2000);
    } // end main
} // end TimerDemo01
