package com.baizhi;

import java.util.HashMap;
import java.util.Map;

public class TestHashMap {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        map.put(2,"123");
        map.put(null,null);
        map.put(1,"123");
        String s = map.get(null);
        int size = map.size();
        System.out.println(size);
    }
}
