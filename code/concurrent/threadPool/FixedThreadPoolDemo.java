package com.java.code.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 线程池
 * @author: Mr.
 * @create: 2020-03-17 17:04
 **/
public class FixedThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i<20;i++) {
            executorService.execute(new Task());
        }
        executorService.shutdown();
    }
}
