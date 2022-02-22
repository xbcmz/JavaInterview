package singleton;

/**
 * <p>
 * 懒汉模式
 *
 * </p>
 *
 * @author Mr.
 * @since 2022/2/22
 */
public class LanHanSingleton {
    /**
     * 优点：在需要被使用的时候才被创建，不会造成资源浪费
     * 缺点：线程不安全，getInstance方法没有同步，在多线程的情况下会造成创建的实例不同
     * 线程安全的修改方法：
     * 使用synchronized关键字同步getInstance方法
     * 使用synchronized关键字同步代码块
     */
    private LanHanSingleton() {
    }

    private static LanHanSingleton lanHanSingleton = null;

    public static LanHanSingleton getInstance() {
        if (null == lanHanSingleton) {
            return new LanHanSingleton();
        }
        return lanHanSingleton;
    }

}
