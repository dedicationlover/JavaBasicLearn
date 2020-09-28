package com.java8.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 *  函数式接口，@FunctionalInterface 只告诉编译器，该接口是函数式接口，检查是否只有一个方法
 *  使用Java8提供的Predicate 断言 test（T）
 */
public class FunInterDemo {
    public static void main(String[] args){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Predicate<Integer> odd = a -> a % 2 == 0;
        Predicate<Integer> more = a -> a > 5;
        eval(list,odd);
        System.out.println("-------------------");
        eval(list, more);
    }

    private static void eval(List<Integer> list, Predicate<Integer> predicate){
        for(Integer integer : list){
            if(predicate.test(integer)){
                System.out.println(integer);
            }
        }
    }
}
