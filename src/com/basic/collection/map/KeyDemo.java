package com.collection.map;

import java.util.Hashtable;

/**
 *  使用自己类作为key
 *  重写equals() hashcode()
 */
public class KeyDemo {
    public static void main(String[] args) {
        // Hashtable
        Hashtable hashtable = new Hashtable<>();
        A a1 = new A(1);
        A a2 = new A(2);
        hashtable.put(a1, "a1");
        hashtable.put(a2, "a2");
        hashtable.put(new A(3), new B());
        System.out.println(hashtable);
        System.out.println(hashtable.containsValue(new Object()));  // true   调用的是value.equals(object)
        hashtable.remove(new A(1));
        for (Object key : hashtable.keySet()) {
            System.out.println(key + "--->" + hashtable.get(key) );
        }
        // com.collection.map.A@3--->com.collection.map.B@1b6d3586
        // com.collection.map.A@2--->a2
    }
}

/**
 * 作为key
 */
class A {
    int count;
    public A(int count) {
        this.count = count;
    }
    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object != null && object.getClass() == A.class) {
            A a = (A)object;
            if (a.count == this.count) {
                return true;
            }
        }
        return false;
    }
    @Override
    public int hashCode() {
        return count;
    }
}

/**
 *  只改写equals方法，且永真
 */
class B {
    @Override
    public boolean equals(Object object) {
        return true;
    }
}