package com.basic.synchronize.producerConsumer;

public class Apply {
    public static void main(String[] args){
        // 共同资源
        Movie m = new Movie();
        // 多线程
        Player p = new Player(m);
        Watcher w = new Watcher(m);
        // 代理
        new Thread(p).start();
        new Thread(w).start();
    } // end main
} // end Apply

