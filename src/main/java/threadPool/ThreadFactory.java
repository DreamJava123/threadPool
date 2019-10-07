package threadPool;

/**
 * 线程池工厂类
 * Created by TOM
 * On 2019/10/7 16:06
 */
@FunctionalInterface
public interface ThreadFactory {

  Thread creatThread(Runnable runnable);
}
