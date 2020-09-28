package com.basic.synchronize.producerConsumer;

import com.basic.synchronize.producerConsumer.Movie;

/**
 * 生产者
 * @author Administrator
 *
 */
public class Player implements Runnable {
    private Movie m;
    public Player(Movie m) {
        super();
        this.m = m;
    } // end constructor
    @Override
    public void run() {
        for (int i = 0; i < 20; i++){
            if (0 == i%2){
                m.play("天鹅湖----");
            }else{
                m.play("话剧----");
            } // end if
        } // end for
    } // end run
} // end Player

