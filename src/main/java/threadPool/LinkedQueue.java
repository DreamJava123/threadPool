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

  //其实可以用juc中的工具类直接替换掉的 算了 直接这样吧
  private final LinkedList<Runnable> runnableLinkedList = new LinkedList<>();

  LinkedQueue(int limit, DenyPolicy denyPolicy) {
    this.limit = limit;
    this.denyPolicy = denyPolicy;
  }

  @Override
  public void offer(Runnable runnable) {
    synchronized (runnableLinkedList) {
      if (runnableLinkedList.size() >= limit) {
        //todo 超过限制，启动拒绝策略
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
    synchronized (runnableLinkedList) {
      while (runnableLinkedList.isEmpty()) {
        try {
          //挂起当前线程
          runnableLinkedList.wait();
        } catch (InterruptedException e) {
          System.out.println(ExceptionUtils.getStackTrace(e));
        }
      }
      //拿到第一个
      return runnableLinkedList.removeFirst();
    }
  }

  @Override
  public int size() {
    return runnableLinkedList.size();
  }
}