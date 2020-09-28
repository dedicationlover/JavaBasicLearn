package com.basic.collection.list;

/**
 * head节点存元素
 * add方法从头插入
 */
public class MyLinkedList {
    // 链表头尾
    private Node head;
    private Node tail;
    private int size;

    public boolean set(int index, Object object) {
        checkIndex(index);
        Node node = indexOf(index);
        node.object = object;
        return true;
    }

    private Node indexOf(int index) {
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            try {
                throw new Exception("下标错误");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Object remove (int index) {
        checkIndex(index);
        Object o;
        if (index == 0) {
            o = head.object;
            head = head.next;
            head.previous = null;
        } else {
            Node node = indexOf(index);
            node.previous.next = node.next;
            node.next.previous = node.previous;
            o = node.object;
        }
       size--;
        return o;
    }

    public boolean remove(Object object) {
        int index = get(object);
        if (index >= 0) {
            remove(index);
            return true;
        }
        return false;
    }

    public int get(Object object) {
        Node node = head;
        for (int i = 0; i < size; i++) {
            if (node.object.equals(object)) {
                return i;
            }
        }
        return -1;
    }

    public Object get(int index) {
        checkIndex(index);
        return indexOf(index).object;
    }

    public void add(int index, Object object) {
        checkIndex(index);
        if (object == null) {
            throw new NullPointerException();
        }
        Node node = new Node();
        node.object = object;
        if (index == 0) {
            node.next = head;
            head.previous = node;
            head = node;
        } else {
            Node temp = indexOf(index);
            node.next = temp;
            node.previous = temp.previous;
            node.previous.next = node;
            temp.previous = node;
        }
        size++;
    }

    public void add(Object object) {
        if (object == null) {
            throw new NullPointerException();
        }
        Node node = new Node(null, object, null);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            head.previous = node;
            node.next = head;
            head = node;
        }
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
