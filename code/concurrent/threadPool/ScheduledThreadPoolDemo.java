package com.java.code.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @description: 线程池
 * @author: Mr.
 * @create: 2020-03-17 17:04
 **/
public class ScheduledThreadPoolDemo {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        System.out.println(System.currentTimeMillis());
        scheduledExecutorService.schedule((Runnable) () -> {
            System.out.println("延迟执行三秒");
            System.out.println(System.currentTimeMillis());
        },3, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();
    }
}
