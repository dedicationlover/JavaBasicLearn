package com.basic.Arithmetic;

/**
 *  浮点数的 infinity与NAN的比较
 *  NAN不与任何NAN相等
 */
public class InfinityCompare {
    public static void main(String[] args){
        System.out.println(Float.POSITIVE_INFINITY == Double.POSITIVE_INFINITY);   // true
        System.out.println(Float.NEGATIVE_INFINITY == Double.NEGATIVE_INFINITY);   // true
        System.out.println(Float.NEGATIVE_INFINITY == -Double.POSITIVE_INFINITY);  // true

        // NAN
        System.out.println(Float.NaN == Double.NaN);   // false
        System.out.println(Float.NaN == Float.NaN);    // false

        double d = 4.0;
        System.out.println(d);
    }
}
