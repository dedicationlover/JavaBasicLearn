package com.basic.IO.file;

import java.io.File;

/**
 * 树形结构打印目录下的子目录
 */
public class FileTree {
    public static void main(String[] args) {
        File file = new File("D:\\JavaCode\\code\\JavaLearn\\src\\com\\basic");
        printFile(file, 0);
    }
    public static void printFile(File file, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("-");
        }
        System.out.print(file.getName() + "\n");
        if (file.isDirectory()) {
            File[] subFiles = file.listFiles();
            for (File file1 : subFiles) {
                printFile(file1, level + 1);
            }
        }
    }
}
