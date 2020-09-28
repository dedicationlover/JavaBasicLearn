package com.basic.Arithmetic;

/**
 *  整数除0:    ArithmeticException
 *  整数除 0.0:  Infinity     -Infinity
 *  浮点数除0:   Infinity     -Infinity
 *  0 除 整数与浮点数     0  / 0.0
 */
public class DivisionZero {
    public static void main(String[] args){
//        System.out.println(5/0);   // java.lang.ArithmeticException: / by zero
        System.out.println(5/0.0);   // Infinity
        System.out.println(5.0/0.0); // Infinity
        System.out.println(5.0/0);   // Infinity
//        System.out.println(0/0);    // java.lang.ArithmeticException: / by zero
        System.out.println(0/0.0);    // NaN
        System.out.println(0.0/0);    // NaN
        System.out.println(0/5);      // 0
        System.out.println(0/5.0);    // 0.0
    }
}
