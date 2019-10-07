package threadPool;


/**
 * Created by TOM
 * On 2019/10/7 16:13
 */
public class InternalTask implements Runnable {

  private final Queue queue;

  private volatile boolean running = true;

  public InternalTask(Queue queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    while (running && !Thread.currentThread().isInterrupted()) {
      try {
        Runnable take = queue.take();
        take.run();
      } catch (Exception e) {
        System.out.println(e);
        running = false;
        break;
      }
    }
  }

  public void stop() {
    running = false;
  }
}
