package threadPool;

/**
 * 线程池队列接口
 * Created by TOM
 * On 2019/10/7 16:04
 */
public interface Queue {

  void offer(Runnable runnable);

  Runnable take();

  //获取队列大小
  int size();
}
