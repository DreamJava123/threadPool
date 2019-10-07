package threadPool;

/**
 * 线程池上层接口
 * Created by TOM
 * On 2019/10/7 16:00
 */
public interface BaseThreadPool {

  void execute(Runnable runnable);

  void shutdown();

  int getMaxSize();

  int getCoreSize();

  int getQueueSize();

  int getAliveSize();

  boolean isShutdown();

}
