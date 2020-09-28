package com.basic.Date;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalenderDemo {
    public static void main(String[] args){
        Calendar calendar = new GregorianCalendar();
        calendar.set(2020,8,10,2,34,45);
        Date date = calendar.getTime();
        System.out.println(date);   // Thu Sep 10 02:34:45 CST 2020

        calendar.set(2020,8,19);
        date = calendar.getTime();
        System.out.println(date);    // Sat Sep 19 02:34:45 CST 2020

        date = new Date();
        calendar.setTime(date);
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));

        calendar.add(Calendar.DATE, 120);
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));

        System.out.println("----------");

        // set延迟修改
        calendar.set(Calendar.DAY_OF_YEAR, 400);
        System.out.println(calendar.getTime());
        // 关闭容错性
        calendar.setLenient(false);
        calendar.set(Calendar.MONTH, 13);
        System.out.println(calendar.getTime());  // java.lang.IllegalArgumentException: MONTH
    }
}
