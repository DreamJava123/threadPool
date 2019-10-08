package threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    BaseThreadPoolImpl baseThreadPool = new BaseThreadPoolImpl(5, 10);
    ExecutorService executorService = Executors.newFixedThreadPool(1);
    for (int i = 0; i < 11; i++) {
/*      executorService.execute(() -> {
        System.out.println(Thread.currentThread().getName());
        System.out.println("ddddd");
      });*/
      baseThreadPool.execute(() -> {
        try {
          System.out.println(Thread.currentThread().getName() + Thread.currentThread().getId());
          System.out.println("ddddd");
        } catch (Exception e) {
          System.out.println("----");
        }
      });
    }
  }
}
