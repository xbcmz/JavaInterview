package com.java.mr;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * TreeMap:通过比较器comparable或者comparator实现Key的去重
 * HashMap:通过使用hashcode和equals实现去重
 */
public class TreeMapSortTest {

    public static void main(String[] args) {
//        TreeMap map = new TreeMap();  sieze = 2
        HashMap<Object, Object> map = new HashMap<>();//    size = 1
        map.put(new Key(), "one");
        map.put(new Key(), "two");
        System.out.println(map.size());
    }
}

class Key implements Comparable<Key> {

    @Override
    // 返回的负数，表示对象永远小于输入的other对象
    public int compareTo(Key o) {
        return -1;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }

}
