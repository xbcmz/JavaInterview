package interviewA1B2C3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 面试题：两个线程，一个输出字母，一个输出数字，交替输出1A2B3C4D...26Z
 *
 */
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
