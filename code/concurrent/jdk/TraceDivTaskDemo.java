package com.java.code.jdk;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TraceDivTaskDemo {
    public static void main(String args[]) {
        ThreadPoolExecutor threadPoolExecutor = new TraceThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        for (int i = 0; i < 5; i++) {
            threadPoolExecutor.execute(new NoTraceDivTaskDemo.DivTask(100, i));
        }
    }
}
