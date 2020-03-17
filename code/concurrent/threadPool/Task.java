package com.java.code.threadPool;

/**
 * @description:
 * @author: Mr.
 * @create: 2020-03-17 17:25
 **/
public class Task implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"running");
    }
}
