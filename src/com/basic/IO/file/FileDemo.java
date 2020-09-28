package com.basic.IO.file;

import java.io.File;
import java.io.IOException;

public class FileDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        File file = new File("D:/JavaCode/code");
        File file1 = new File("");
        System.out.println(file.isFile());  // true
        System.out.println(file1.isDirectory());  // false
        System.out.println(file1.isFile());  // false

        // 创建新文件
        File newFile = new File(file, "newFile.txt");
        newFile.createNewFile();
        System.out.println(newFile.getAbsolutePath());

        // 创建目录
        File newD = new File("d:/new/add/newd");
        newD.mkdirs();
    }
}
