package threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * Created by TOM
 * On 2019/10/7 18:12
 */
public class testThread implements Runnable {

  @Override
  public void run() {
    System.out.println("111111");
  }

  public static void main(String[] args) {
    TomThreadPool baseThreadPool = new TomThreadPool(5, 10);
    ExecutorService executorService = Executors.newFixedThreadPool(1);
    for (int i = 0; i < 20000; i++) {
/*      executorService.execute(() -> {
        System.out.println(Thread.currentThread().getName());
        System.out.println("ddddd");
      });*/
      System.out.println("start" + baseThreadPool.getAliveSize());
      baseThreadPool.execute(() -> {
        try {
          System.out.println(Thread.currentThread().getName() + Thread.currentThread().getId());
        } catch (Exception e) {
          System.out.println(ExceptionUtils.getStackTrace(e));
        }
      });
      System.out.println("queueSize" + baseThreadPool.getQueueSize());
      System.out.println("end" + baseThreadPool.getAliveSize());
    }
    try {
      Thread.sleep(1000L);
      System.out.println("=====================");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("queueSizeend" + baseThreadPool.getQueueSize());
    System.out.println("endend" + baseThreadPool.getAliveSize());
    System.out.println("core" + baseThreadPool.getCoreSize());
  }
}
