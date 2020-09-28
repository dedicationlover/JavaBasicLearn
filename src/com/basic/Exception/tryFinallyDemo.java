package com.basic.Exception;

/**
 *  测试try-finally块中的执行顺序 -- 特例
 */
public class tryFinallyDemo {
    public static void main(String[] args) {
        try {
            System.out.println("try...");  // try...
            System.exit(1);
        } finally {
            System.out.println("finally");
        }
    }
}
