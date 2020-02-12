package com.java.mr.lock;

import java.util.concurrent.locks.LockSupport;

public class TestPark {
    public static void main(String[] args) {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                testSync();
            }
        };
        t1.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main");
        LockSupport.unpark(t1);

    }

    public static void testSync(){
        System.out.println("t1-----start");

        LockSupport.park();
        System.out.println("t1-----end");
    }
}
