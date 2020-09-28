package com.basic.IO;

import java.util.Scanner;

/**
 *  联系控制台输入
 */
public class ScannerTest {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        System.out.println(a);

        scanner.nextLine();  // 把换行符捕获住

        String string = scanner.nextLine();   // 捕获到上一行的换行符
        System.out.println(string);

        scanner.close();
    }
}
