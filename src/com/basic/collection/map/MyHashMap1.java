package com.basic.collection.map;

/**
 * 自写hashmap第一版 效率低
 */
public class MyHashMap1 {
    // 存储 K-V 对的数组,直接给100的量
    private Entry[] elementData = new Entry[100];
    // 记录元素个数
    private int size;
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }

    /**
     * 添加一对 key-value，若key已经存在，则更新value
     * 不扩容，不判空
     * @param key 需要插入的key
     * @param value 需要插入或更新的value
     */
    public void put(Object key, Object value) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].key.equals(key)) {
                elementData[i].value = value;
                return ;
            }
        }
        Entry entry = new Entry(key, value);
        elementData[size++] = entry;
    }
    public Object get(Object key) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].key.equals(key)) {
                return elementData[i].value;
            }
        }
        return null;
    }
    public boolean containKey(Object key) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].key.equals(key)) {
                return true;
            }
        }
        return false;
    }
    public boolean  containValue(Object value) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].value.equals(value)) {
                return true;
            }
        }
        return false;
    }
}

/**
 *  用于存储的 key-value对，构成一个class--entry
 */
class Entry {
    Object key;
    Object value;
    public Entry(Object key, Object value) {
        this.key = key;
        this.value = value;
    }
}