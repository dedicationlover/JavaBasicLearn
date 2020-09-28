package com.basic.collection.list;

/**
 *  根据ArrayList规范写一个ArrayList类
 */
public class MyArrayList {
    // 默认的初始化大小
    private static final int DEFAULT_CAPACITY = 10;
    // 存储数据的数组
    private Object[] elementData;
    // 记录list的大小
    private int size;

    /**
     *  无参构造器，给默认的size大小
     */
    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     *  使用传入的参数大写构建数组
     * @param initialCapacity 数组初始大小
     */
    public MyArrayList(int initialCapacity) {
        // 对入参判断
        if (initialCapacity < 0) {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        elementData = new Object[initialCapacity];
    }

    /**
     * 返回容器元素个数
     * @return 元素个数
     */
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 向数组添加一个元素
     * @param o 需要添加的元素
     * @return 添加成功返回true
     */
    public boolean add(Object o) {
        ensureCapacity(size + 1);
        elementData[size++] = o;
        return true;
    }

    /**
     * 向数组中指定位置添加一个元素
     * @param index 指定位置
     * @param o 需要添加的元素
     */
    public void add(int index, Object o) {
        ensureCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = o;
        size++;
    }

    private void ensureCapacity(int needCapacity) {
        if (needCapacity > elementData.length) {
            // 扩容
            int oldCapacity = elementData.length;
            int newCapacity = oldCapacity + oldCapacity >> 1;
            if (needCapacity - newCapacity > 0) {
                newCapacity = needCapacity;
            }
            Object[] newData = new Object[newCapacity];
            System.arraycopy(elementData,0, newData, 0, oldCapacity);
            elementData = newData;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 移除指定索引处的元素
     * @param index 需要被移除的索引
     * @return 移除的元素
     */
    public Object remove(int index) {
        // 入参检查
        checkIndex(index);
        Object obj = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        elementData[size] = null;
        return obj;
    }

    /**
     * 移除指定元素
     * @param o 需要被移除的元素
     * @return 成功移除返回true
     */
    public boolean remove(Object o) {
        remove(indexOf(o));
        return true;
    }

    /**
     * 获取指定索引的元素
     * @param index 指定索引
     * @return 索引位置的元素
     */
    public Object get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    /**
     * 判断数组是否包含指定元素
     * @param o 需要判断的元素
     * @return 包含则返回true
     */
    public boolean contain(Object o) {
        return indexOf(o) > -1;
    }

    /**
     * 获取指定的对象的索引
     * @param o 需要获取的对象
     * @return 该对象的索引
     */
    public int indexOf(Object o) {
        // null.equals报异常，分开处理
        if (null == o) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }


}

