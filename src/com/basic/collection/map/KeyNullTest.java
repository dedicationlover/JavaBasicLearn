package com.collection.map;

import java.util.HashMap;
import java.util.Hashtable;

/**
 *  hashtable hashmap 关于key=null测测试
 */
public class KeyNullTest {
    public static void main(String[] args) {
        Hashtable hashtable = new Hashtable();
//        hashtable.put(null, null);  // NullPointerException
        HashMap hashMap = new HashMap();
        hashMap.put(null, null);
        hashMap.put(null, "null");
        System.out.println(hashMap);   // {null=null}

    }
}
