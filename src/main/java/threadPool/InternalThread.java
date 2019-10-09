package threadPool;


import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * Created by TOM
 * On 2019/10/7 16:13
 */
public class InternalThread implements Runnable {

  private final Queue queue;

  private volatile boolean running = true;

  InternalThread(Queue queue) {
    this.queue = queue;
  }

  /**
   * 取
   */
  @Override
  public void run() {
    while (running && !Thread.currentThread().isInterrupted()) {
      try {
        Runnable take = queue.take();
        take.run();
      } catch (Exception e) {
        System.out.println(ExceptionUtils.getStackTrace(e));
        running = false;
        break;
      }
    }
  }

  void stop() {
    running = false;
  }
}
