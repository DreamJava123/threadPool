package threadPool;

import java.util.concurrent.LinkedBlockingQueue;
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

  private LinkedBlockingQueue<InternalTask> aliveThreadQueue = new LinkedBlockingQueue<>();
  // private final static DenyPolicy DENY_POLICY

  private final static ThreadFactory DEFAULT_THREAD_FACTORY = new DefaultThreadFactory();

  public BaseThreadPoolImpl(int coreNum, int maxNum) {
    this.coreNum = coreNum;
    this.maxNum = maxNum;
    this.threadFactory = new DefaultThreadFactory();
    this.denyPolicy = null;
    this.keepAliveTime = 0L;
    this.timeUnit = TimeUnit.MILLISECONDS;
    this.queue = new LinkedQueue(maxNum, null, this);
    this.init();
  }

  public BaseThreadPoolImpl(int coreNum, int maxNum, ThreadFactory threadFactory, DenyPolicy denyPolicy, long keepAliveTime,
      TimeUnit timeUnit) {
    this.coreNum = coreNum;
    this.maxNum = maxNum;
    this.threadFactory = threadFactory;
    this.denyPolicy = denyPolicy;
    this.keepAliveTime = keepAliveTime;
    this.timeUnit = timeUnit;
    this.init();
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
    this.init();
  }

  private void init() {
    //类似守护线程
    ThreadPoolManager threadPoolManager = new ThreadPoolManager(this);
    threadPoolManager.start();
    for (int i = 0; i < coreNum; i++) {
      creatThread();
    }
  }

  private static class ThreadPoolManager extends Thread {

    private final BaseThreadPoolImpl baseThreadPool;

    ThreadPoolManager(BaseThreadPoolImpl baseThreadPool) {
      this.baseThreadPool = baseThreadPool;
    }

    @Override
    public void run() {
      while (!baseThreadPool.isShutdown() && !isInterrupted()) {
        try {
          baseThreadPool.timeUnit.sleep(baseThreadPool.keepAliveTime);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        synchronized (this) {
          //todo
        }
      }
    }
  }

  @Override
  public void execute(Runnable runnable) {
    if (this.isShutdown()) {
      throw new IllegalStateException("thread is shutDown");
    }
    this.queue.offer(runnable);
  }

  /**
   * 内部创建线程方法
   */
  private void creatThread() {
    InternalTask internalTask = new InternalTask(this.queue);
    Thread thread = this.threadFactory.creatThread(internalTask);
    aliveThreadQueue.offer(internalTask);
    thread.start();
    aliveNum++;
  }

  private void closeThread() {
    InternalTask remove = aliveThreadQueue.remove();
    remove.stop();
    aliveNum--;
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
    return isShutDown;
  }
}
