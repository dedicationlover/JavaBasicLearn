package com.basic.Date;

import com.basic.Enum.SeasonEnum;

import java.util.Date;

/**
 *  简单使用Date
 */
public class DateDemo {
    public static void main(String[] args){
        Date date = new Date();
        Date date1 = new Date();
        Date date2 = (Date) date.clone();

        date1.setTime(1277856754);
        System.out.println(date.getTime());
        System.out.println(date1.getTime());
        System.out.println(date2.getTime());

        // 比较
        System.out.println(date.after(date1));
        System.out.println(date.before(date1));
        System.out.println(date.equals(date2));
        System.out.println(date.compareTo(date1));
        System.out.println(date == date2);
    }
}
