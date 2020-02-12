package com.java.mr.lock;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

public class TestLock {
    static L l = new L();   // 对象存在堆

    @Test
    public void  LockTest(){
        System.out.println(Integer.toHexString(l.hashCode()));
        System.out.println(ClassLayout.parseInstance(l).toPrintable());
    }
}
