package com.basic.runtime;

import java.io.IOException;

public class RuntimeDemo {
    public static void main(String[] args) throws IOException {
        // 获取关联的运行时对象
        Runtime rt = Runtime.getRuntime();
        System.out.println("处理器数量：" + rt.availableProcessors());   // 12
        System.out.println("空闲内存数：" + rt.freeMemory());
        System.out.println("总内存数：" + rt.totalMemory());
        System.out.println("可用最大内存数：" + rt.maxMemory());

        //  单独启动一条进程运行操作系统命令
        rt.exec("notepad.exe");
    }
}
