#### 面试题：两个线程，一个输出字母，一个输出数字，交替输出1A2B3C4D...26Z

1.LockSupport

```java
public class LockSupportTest {
    static Thread t1 = null,t2 = null;

    public static void main(String[] args) throws Exception {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        t1 = new Thread(() -> {
            for (char c:aI) {
                LockSupport.park(); // 当前线程阻塞
                System.out.print(c);
                LockSupport.unpark(t2); // t2运行
            }
        },"t1");

        t2 = new Thread(() -> {
            for (char c:aC) {
                System.out.print(c);
                LockSupport.unpark(t1); // t1运行
                LockSupport.park(); // 当前线程阻塞
            }
        },"t2");

        t1.start();
        t2.start();

    }
}
```

2.CAS

```java
/**
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

```

3.wait_notify

```java
/**
 * 使用wait notify 之前要加锁
 * 为了确保某个线程先执行，可以使用cas或者CountDownLatch来实现
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
```

4.lock_condition

```java
public class LockConditionTest {
    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        Lock lock = new ReentrantLock();
        Condition conditionT1 = lock.newCondition();
        Condition conditionT2 = lock.newCondition();
        new Thread(() -> {
            try {
                lock.lock();    // 锁住

                for (char c:aI) {
                    System.out.print(c);
                    conditionT2.signal();   // 通知等着T2条件锁的线程启动
                    conditionT1.await();
                }
                conditionT2.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"t1").start();

        new Thread(() -> {
            try {
                lock.lock();

                for (char c:aC) {
                    System.out.print(c);
                    conditionT1.signal();
                    conditionT2.await();
                }
                conditionT1.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"t2").start();
    }
}

```

