package jdk;

import java.util.concurrent.locks.ReentrantLock;

public class FairLock implements Runnable {

    public static ReentrantLock fairLock = new ReentrantLock(true);//����trueָ�����ǹ�ƽ��,Ҳ���Բ�����,�ֱ����й۲칫ƽ����ǹ�ƽ���������
    //public static ReentrantLock unfairLock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {
                fairLock.lock();
                // unfairLock.lock();
                System.out.println(Thread.currentThread().getName() + "获得锁");
            } finally {
                fairLock.unlock();
                // unfairLock.unlock();
            }
        }
    }

    /**
     * 公平锁的一个特点是:不会产生饥饿现象,只要排队最终都会得到资源.
     * <p/>
     * 但是实现公平锁要求系统维护一个有序队列,因此公平锁的实现成本较高,性能相对低下.
     *
     * @param args
     */
    public static void main(String args[]) {
        FairLock r1 = new FairLock();
        Thread thread1 = new Thread(r1, "Thread_t1");
        Thread thread2 = new Thread(r1, "Thread_t2");
        Thread thread3 = new Thread(r1, "Thread_t3");

        thread1.start();
        thread2.start();
        thread3.start();
    }

}
