package com.basic.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {
    public static void main(String[] args) {
        // 将一个字符串编译成Pattern对象
        Pattern pattern = Pattern.compile("a*b");
        // 使用Pattern对象创建Matcher对象
        Matcher matcher = pattern.matcher("nmahjkbbbb");
        boolean b = matcher.find(); // 包含
        System.out.println(b);
        System.out.println(matcher.group());

        // 只用一次
        boolean b2 = Pattern.matches("a*b", "aaab"); // 整个字符串匹配
        System.out.println(b2);

        Matcher m = Pattern.compile("\\w+").matcher("java is very easy!");
        while (m.find()) {
            System.out.println(m.group() + "字符串起始位置：" + m.start() + "字符串结束位置：" + m.end());
        }
        // find方法可传入int型参数，表示从该int参数向下匹配
        int index = 0;
        while (m.find(index)) {
            System.out.println(m.group());
            index++;
        }
    }
}
