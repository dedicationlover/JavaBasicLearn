package com.basic.thread.demo3;

public class ProgrammerApply {
    public static void main(String[] args) {
        // 创建真实角色
        Programmer p = new Programmer();
        // 创建代理角色+真实角色引用
        Thread thread = new Thread(p);
        thread.start();    // 一条路径

        // 一条路径
        for (int i = 0; i < 100; i++){
            System.out.println("speaking listening drinking!");
        }
    } // end main
} // end ProgrammerApply

