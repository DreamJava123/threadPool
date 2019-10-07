package threadPool;

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
    DefaultThreadFactory defaultThreadFactory = new DefaultThreadFactory();

    Thread thread = defaultThreadFactory.creatThread(new testThread());
    thread.start();
    System.out.println(thread.getName());
  }
}
