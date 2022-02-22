package singleton;

/**
 * <p>
 * 静态内部类
 * </p>
 *
 * @author Mr.
 * @since 2022/2/22
 */
public class StaticSingleton {
    /**
     线程安全，延时加载
     由于内部类是隐藏的，所以只要不使用内部类，那么JVM就不会将其加载进来，这样就很好的实现了延迟加载
     缺点：遇到序列化对象的时候，如果还使用默认的运行方式，就会出现多实例的情况，解决方法是在反序列化中使用readResolve方法
     为什么是线程安全的：虚拟机会保证一个类的构造器方法在多线程环境中被正确地加载，同步，如果多个线程同时去初始化一个类，那么只有一个线程去执行这个类的
     */
    private StaticSingleton() {
    }

    private static class SingletonHolder {
        private static StaticSingleton instance = new StaticSingleton();
    }

    public static StaticSingleton getInstance() {
        System.out.println("获取实例");
        return SingletonHolder.instance;
    }
}
