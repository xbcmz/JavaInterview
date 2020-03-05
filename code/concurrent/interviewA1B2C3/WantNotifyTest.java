package com.java.code.interviewA1B2C3;

import java.util.concurrent.CountDownLatch;

/**
 * 面试题：两个线程，一个输出字母，一个输出数字，交替输出1A2B3C4D...26Z
 *
 * 使用wait notify 之前要加锁
 *
 */
public class WantNotifyTest {
    //private static volatile boolean t2strated = false;

    private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) {
        final Object o = new Object();

        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        new Thread(() -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (o) {  // 锁对象

//                while(!t2strated){  // 如果t2线程未启动，t1进入等待队列
//                    try {
//                        o.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
                for (char c:aI) {
                    System.out.print(c);
                    try{
                        o.notify(); // 唤醒等待队列中的某一个线程
                        o.wait();   // 当前线程进入等待队列，让出锁

                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    o.notify();
                }
            }

        },"t1").start();

        new Thread(() -> {
            synchronized (o) {  // 锁对象
                for (char c:aC) {
                    System.out.print(c);
//                    t2strated = true;   // 让t1启动
                    latch.countDown();
                    try{
                        o.notify(); // 唤醒等待队列中的某一个线程
                        o.wait();   // 当前线程进入等待队列，让出锁

                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    o.notify();
                }
            }

        },"t2").start();

    }
}
