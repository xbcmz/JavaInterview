package com.java.mr;

/**
 * hashmap 转化2的幂次方
 *
 */
public class HighestOneBit {
    public static int highestOneBit(int i){
        i |= (i >> 1);
        i |= (i >> 2);
        i |= (i >> 4);
        i |= (i >> 8);
        i |= (i >> 16);
        return i - (i>>>1);
    }
    public static void main(String[] args) {
        int i = highestOneBit(17);
        System.out.println(i);
    }
}
