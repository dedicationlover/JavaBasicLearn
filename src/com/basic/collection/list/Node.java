package com.basic.collection.list;

/**
 * MyLinkedList中的元素
 */
public class Node {
    Node previous;
    Object object;
    Node next;

    public Node() {}

    public Node(Node previous, Object o, Node next) {
        this.previous = previous;
        this.object = o;
        this.next = next;
    }
}
