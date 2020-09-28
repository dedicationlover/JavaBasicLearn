package com.basic.thread.rabbitTortoiseDemo1;

public class RabbitApply {
    public static void main(String[] args) {
        // 创建子类对象
        Rabbit rabbit = new Rabbit();
        Tortoise tortoise = new Tortoise();
        // 调用start() 加到线程簇里面  就绪，什么时候运行由cpu决定
        rabbit.start();  // 不要调用run()
        tortoise.start();
        /*
         * 创建了两条路径，另外三条：main，gc, exception
         */
        for(int i = 0; i < 100; i++){
            System.out.println("mian=====" + i);
        }
    } // end main
} // end RabbitApply

