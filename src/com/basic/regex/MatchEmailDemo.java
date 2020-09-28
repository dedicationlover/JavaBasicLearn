package com.basic.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchEmailDemo {
    public static void main(String[] args) {
        String[] emails = {
                "google@gmail.com",
                "xinlang@163.com",
                "xiaomin123@193.cn",
                "1243545@123.123"
        };
        String mailExgex = "\\w{3,20}@\\w+\\.(com|org|cn|net|gov)";
        Pattern pattern = Pattern.compile(mailExgex);
        Matcher matcher = null;
        for (String email : emails) {
            if (matcher == null) {
                matcher = pattern.matcher(email);
            } else {
                matcher.reset(email);
            }
            if (matcher.matches()) {
                System.out.println(email + "是一个有效的邮件地址");
            } else {
                System.out.println(email + "不是一个有效的邮件地址");
            }
        }
        test06();
    }
    public static void test06() {
        String[] msgs = {
                "Java has regular expressions in 1.4",
                "regular expressions now expressing in Java",
                "Java represses oracular expressions"
        };
        Pattern p = Pattern.compile("re\\w*");
        Matcher matcher = null;
        for(int i = 0; i < msgs.length; i++){
            if(matcher == null){
                matcher = p.matcher(msgs[i]);
            }
            else{
                matcher.reset(msgs[i]);
            }
            System.out.println(matcher.replaceAll("哈哈\2"));
        }
    }

}
