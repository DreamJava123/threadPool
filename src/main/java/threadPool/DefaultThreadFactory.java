package threadPool;

/**
 * Created by TOM
 * On 2019/10/7 18:07
 */
public class DefaultThreadFactory implements ThreadFactory {

  @Override
  public Thread creatThread(Runnable runnable) {
    return new Thread(runnable, "TOMThread");
  }
}
