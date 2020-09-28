package com.basic.thread.state;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 倒计时
 * 1、倒数10个数，一秒打印一个
 * 2、倒计时
 * @author Administrator
 *
 */
public class SleepDemo01 {
    public static void main(String[] args) throws InterruptedException {
        // 倒计时
        Date endDate = new Date(System.currentTimeMillis() + 10 * 1000);
        long end = endDate.getTime();
        while (true){
            // 输出
            System.out.println(new SimpleDateFormat("mm:ss").format(endDate));
            // 构建下一秒的时间
            endDate = new Date(endDate.getTime() - 1000);
            // sleep 一秒打印一次
            Thread.sleep(1000);
            // 10秒以内基础，10秒以外退出
            if (end - 10*1000 > endDate.getTime()){
                break;
            } // end if
        } // end while
    } // end main
    // 倒数
    public static void test1() throws InterruptedException{
        int num = 10;
        while (num > 0){
            System.out.println(num--);
            Thread.sleep(1000);
        }
    } // end test1
} // end SleepDemo01

