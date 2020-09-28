package com.basic.collection.map;

import com.basic.collection.list.MyLinkedList;

/**
 * 使用数组+链表的方式存储
 */
public class MyHashMap2 {
    private MyLinkedList[] elementData = new MyLinkedList[100];
    private int size;
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }

    /**
     * 根据key的hashcode选择存放的list
     * @param key
     * @param value
     */
    public void put(Object key, Object value) {
        Entry entry = new Entry(key, value);
        int pos = entry.hashCode() % elementData.length;
        if (pos < 0) {
            pos += elementData.length;
        }
        if (elementData[pos] == null) {
            MyLinkedList list = new MyLinkedList();
            elementData[pos] = list;
            list.add(entry);
        } else {
            MyLinkedList list = elementData[pos];
            for (int i = 0; i < list.size(); i++) {
                Entry entry1 = (Entry) list.get(i);
                if (entry1.value.equals(entry)) {
                    entry1.value = value;
                    return;
                }
            }
            elementData[pos].add(entry);
        }
        size++;
    }
    public Object get(Object key) {
        int pos = key.hashCode() % elementData.length;
        if (pos < 0) {
            pos += elementData.length;
        }
        if (elementData[pos] != null) {
            MyLinkedList list = elementData[pos];
            for (int i = 0; i < size; i++) {
                Entry entry = (Entry) list.get(i);
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }
}
