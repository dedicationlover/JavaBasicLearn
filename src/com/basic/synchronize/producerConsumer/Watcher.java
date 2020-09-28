package com.basic.synchronize.producerConsumer;

import com.basic.synchronize.producerConsumer.Movie;

/**
 * 消费者
 * @author Administrator
 *
 */
public class Watcher implements Runnable {
    private Movie m;
    public Watcher(Movie m) {
        super();
        this.m = m;
    } // end constructor
    @Override
    public void run() {
        for (int i = 0; i < 20; i++){
            m.watch();
        } // end for
    } // end run
} // end Watcher

