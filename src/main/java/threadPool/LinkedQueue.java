package threadPool;

import java.util.LinkedList;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * Created by TOM
 * On 2019/10/7 17:29
 */
public class LinkedQueue implements Queue {

  private final int limit;

  private final DenyPolicy denyPolicy;


  private final LinkedList<Runnable> runnableLinkedList = new LinkedList<>();
  //???
  private final BaseThreadPool baseThreadPool;

  public LinkedQueue(int limit, DenyPolicy denyPolicy, BaseThreadPool baseThreadPool) {
    this.limit = limit;
    this.denyPolicy = denyPolicy;
    this.baseThreadPool = baseThreadPool;
  }

  @Override
  public void offer(Runnable runnable) {
    synchronized (runnableLinkedList) {
      if (runnableLinkedList.size() >= limit) {
        //超过限制，启动拒绝策略
        System.out.println("被拒绝啦");
      } else {
        //将入到队列
        runnableLinkedList.addLast(runnable);
        runnableLinkedList.notifyAll();
      }
    }

  }

  @Override
  public Runnable take() {
    while (runnableLinkedList.isEmpty()) {
      synchronized (runnableLinkedList) {
        try {
          //挂起当前线程
          runnableLinkedList.wait();
        } catch (InterruptedException e) {
          System.out.println(ExceptionUtils.getStackTrace(e));
        }
      }
    }
    //拿到第一个
    return runnableLinkedList.getFirst();
  }

  @Override
  public int size() {
    return runnableLinkedList.size();
  }
}