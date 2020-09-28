package com.collection.map;

import java.util.TreeMap;

/**
 * 实现一个类作为key
 */
class KeyClass implements Comparable {
    int count;
    public KeyClass(int count) {
        this.count = count;
    }
    @Override
    public String toString() {
        return "KeyClass(count=" + count + ")";
    }
    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object != null && object.getClass() == KeyClass.class) {
            KeyClass keyClass = (KeyClass) object;
            if (this.count == keyClass.count) {
                return true;
            }
        }
        return false;
    }
    @Override
    public int compareTo(Object object) {
        KeyClass keyClass = (KeyClass) object;
        if (this.count > keyClass.count) {
            return 1;
        } else if (this.count == keyClass.count) {
            return 0;
        } else {
            return -1;
        }
    }
}
public class TreeMapKeyDemo {
    public static void main(String[] args) {
        TreeMap<KeyClass, String> treeMap = new TreeMap<>();
        treeMap.put(new KeyClass(520), new String("love"));
        treeMap.put(new KeyClass(771), "doit");
        treeMap.put(new KeyClass(20), "begin");
        treeMap.put(new KeyClass(100), "o");
        System.out.println(treeMap);
        // {KeyClass(count=20)=begin, KeyClass(count=100)=o, KeyClass(count=520)=love, KeyClass(count=771)=doit}
        System.out.println(treeMap.firstEntry());  // KeyClass(count=20)=begin
        System.out.println(treeMap.higherEntry(new KeyClass(150)));  // KeyClass(count=520)=love
        System.out.println(treeMap.lowerEntry(new KeyClass(100)));   // KeyClass(count=20)=begin
        System.out.println(treeMap.subMap(new KeyClass(100), new KeyClass(600)));
        // {KeyClass(count=100)=o, KeyClass(count=520)=love}
    }
}
