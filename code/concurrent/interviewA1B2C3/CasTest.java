package com.java.code.interviewA1B2C3;

/**
 * 面试题：两个线程，一个输出字母，一个输出数字，交替输出1A2B3C4D...26Z
 *
 * 偏向锁只记录线程ID
 * 轻量级（自旋锁）占用cpu（不访问cpu）
 * 重量级锁wait放弃cpu进入等待队列不占用cpu
 */
public class CasTest {

    enum ReadyToRun {T1,T2}

    static volatile ReadyToRun r = ReadyToRun.T1;   // volatile 防止指令重排

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        new Thread(() -> {
            for (char c:aC) {
                while(r != ReadyToRun.T1) {}    // 线程是T1结束循环
                System.out.print(c);
                r = ReadyToRun.T2;
            }
        },"t1").start();

        new Thread(() -> {
            for (char c:aI) {
                while(r != ReadyToRun.T2) {}     // 线程是T2结束循环
                System.out.print(c);
                r = ReadyToRun.T1;
            }
        },"t2").start();

    }
}
