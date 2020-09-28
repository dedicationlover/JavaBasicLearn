package com.basic.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;

/**
 *  date和string之间的转换
 */
public class DateFormatDemo {
    public static void main(String[] args){
        // Date  --->  string
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date(123455666);
        String string = dateFormat.format(date);
        System.out.println(string);

        // string ---> date
        String str = "2020/8/9 21:28:9";
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        try {
            Date date1 = dateFormat1.parse(str);
            System.out.println(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
