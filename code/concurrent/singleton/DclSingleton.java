package singleton;

/**
 * <p>
 * 双重检验锁
 * </p>
 *
 * @author Mr.
 * @since 2022/2/22
 */
public class DclSingleton {
    private DclSingleton() {
    }

    /**
     * 这里为什么会用volatile?
     * 主要在于instance = new DoubleCheck();并非是一个原子操作
     * <p>
     * 首先需要了解JVM在类加载的时候作了哪些事情
     * 1.给 instance 分配内存
     * 2.调用 DoubleCheck 的构造函数来初始化成员变量，形成实例
     * 3.将 instance 对象指向分配的内存空间
     * <p>
     * JVM 的即时编译器中存在指令重排序的优化，
     * 也就是说上面的第二步和第三步的顺序是不能保证的，
     * 最终的执行顺序可能是 1-2-3 也可能是 1-3-2。
     */
    private static volatile DclSingleton instance = null;

    public static DclSingleton getInstance() {
        if (instance == null) {
            synchronized (DclSingleton.class) {
                if (instance == null) {
                    System.out.println("获取实例");
                    instance = new DclSingleton();
                }
            }
        }
        return instance;
    }
}
