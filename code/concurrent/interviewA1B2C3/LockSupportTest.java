package interviewA1B2C3;

import java.util.concurrent.locks.LockSupport;

/**
 * 面试题：两个线程，一个输出字母，一个输出数字，交替输出1A2B3C4D...26Z
 */
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
