package com.basic.thread.callableDemo4;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/**
 * 使用Callable创建线程
 * @author Administrator
 *
 */
public class CallableDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException{
        // 创建线程  （要创建的线程数量）
        ExecutorService ser = Executors.newFixedThreadPool(2);
        Race tortoise = new Race("乌龟", 1500);
        Race rabbit = new Race("兔子", 1000);
        // 获取值
        Future<Integer> result1 = ser.submit(tortoise);
        Future<Integer> result2 = ser.submit(rabbit);
        Thread.sleep(3000);
        tortoise.setFlag(false);   // 停止线程体中的循环
        rabbit.setFlag(false);
        int value1 = result1.get();
        int value2 = result2.get();
        System.out.println("乌龟爬了-----" + value1 + "步");
        System.out.println("兔子跑了-----" + value2 + "步");
        // 停止服务
        //	ser.shutdownNow();
    } // end main
} //end CallableDemo
class Race implements Callable<Integer> {
    private String name;
    private long time;  // 延时
    private boolean flag = true;
    private int step; // 步数
    public Race() {
    }
    public Race(String name) {
        this.name = name;
    }
    public Race(String name, long time) {
        this(name);
        this.time = time;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getTime() {
        return time;
    }
    public void setTime(long time) {
        this.time = time;
    }
    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public int getStep() {
        return step;
    }
    public void setStep(int step) {
        this.step = step;
    }
    @Override
    public Integer call() throws Exception {
        while (flag){
            Thread.sleep(time); // 延时
            step++;
        }
        return step;
    } // end call
} // end Race
