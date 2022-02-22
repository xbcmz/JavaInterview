package singleton;

/**
 * <p>
 * 饿汉模式
 * </p>
 *
 * @author Mr.
 * @since 2022/2/22
 */
public class EHanSingleton {
    /**
     * 优点：单例在类被加载的时候被创建，线程安全，可以用于多线程环境
     * 缺点：如果单例未被使用，那么同样会被创建，会造成内存浪费
     */
    private EHanSingleton() {
    }

    private static EHanSingleton eHanSingleton = new EHanSingleton();

    public static EHanSingleton getInstance() {
        return eHanSingleton;
    }

}
