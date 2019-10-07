package threadPool;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by TOM
 * On 2019/10/7 17:29
 */
public class LinkedQueue implements Queue {

  private final int limit;

  private final DenyPolicy denyPolicy;

  private final LinkedBlockingQueue<Runnable> runnableLinkedList = new LinkedBlockingQueue<>();
  //???
  private final BaseThreadPool baseThreadPool;

  public LinkedQueue(int limit, DenyPolicy denyPolicy, BaseThreadPool baseThreadPool) {
    this.limit = limit;
    this.denyPolicy = denyPolicy;
    this.baseThreadPool = baseThreadPool;
  }

  @Override
  public void offer(Runnable runnable) {
    if (runnableLinkedList.size() <= limit) {
      runnableLinkedList.offer(runnable);
      runnableLinkedList.notifyAll();
    } else {
      denyPolicy.reject();
    }
  }

  @Override
  public Runnable take() {
    while (runnableLinkedList.isEmpty()) {
      try {
        //挂起当前线程
        runnableLinkedList.wait();
      } catch (InterruptedException e) {
        System.out.println(e);
      }
    }
    //拿到第一个
    return runnableLinkedList.remove();
  }

  @Override
  public int size() {
    return runnableLinkedList.size();
  }
}