package threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 线程池
 * @author: Mr.
 * @create: 2020-03-17 17:04
 **/
public class SingleThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i<20;i++) {
            executorService.execute(new Task());
        }
        executorService.shutdown();
    }
}
