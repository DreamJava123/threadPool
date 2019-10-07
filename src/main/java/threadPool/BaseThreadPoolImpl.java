package threadPool;

import java.util.concurrent.TimeUnit;

/**
 * Created by TOM
 * On 2019/10/7 17:52
 */
public class BaseThreadPoolImpl implements BaseThreadPool {

  private final int coreNum;

  private final int maxNum;

  private int aliveNum;

  private final ThreadFactory threadFactory;

  private final DenyPolicy denyPolicy;

  private Queue queue;

  private volatile boolean isShutDown = false;

  private final long keepAliveTime;

  private final TimeUnit timeUnit;

  // private final static DenyPolicy DENY_POLICY

  private final static ThreadFactory DEFAULT_THREAD_FACTORY = new DefaultThreadFactory();

  public BaseThreadPoolImpl(int coreNum, int maxNum, ThreadFactory threadFactory, DenyPolicy denyPolicy, long keepAliveTime,
      TimeUnit timeUnit) {
    this.coreNum = coreNum;
    this.maxNum = maxNum;
    this.threadFactory = threadFactory;
    this.denyPolicy = denyPolicy;
    this.keepAliveTime = keepAliveTime;
    this.timeUnit = timeUnit;
  }

  public BaseThreadPoolImpl(int coreNum, int maxNum, int aliveNum, ThreadFactory threadFactory,
      DenyPolicy denyPolicy, Queue queue, boolean isShutDown, long keepAliveTime, TimeUnit timeUnit) {
    this.coreNum = coreNum;
    this.maxNum = maxNum;
    this.aliveNum = aliveNum;
    this.threadFactory = threadFactory;
    this.denyPolicy = denyPolicy;
    this.queue = queue;
    this.isShutDown = isShutDown;
    this.keepAliveTime = keepAliveTime;
    this.timeUnit = timeUnit;
  }

  private void init() {
    interThreadPool.currentThread().start();
  }

  private static class interThreadPool extends Thread {

  }

  @Override
  public void execute(Runnable runnable) {

  }

  @Override
  public void shutdown() {

  }

  @Override
  public int getMaxSize() {
    return 0;
  }

  @Override
  public int getCoreSize() {
    return 0;
  }

  @Override
  public int getQueueSize() {
    return 0;
  }

  @Override
  public int getAliveSize() {
    return 0;
  }

  @Override
  public boolean isShutdown() {
    return false;
  }
}
