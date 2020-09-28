package com.basic.Exception;

import java.util.ArrayList;
import java.util.List;

public class FinalImmutable {
    public static void main(String[] args){
        final List<String> list = new ArrayList<>();
        list.add("jigk");
        list.add("kjik");
//        List<String> list1 = List.of("kil", "fdg");   // JDK9
//        list1.add("ao");
    }
}
