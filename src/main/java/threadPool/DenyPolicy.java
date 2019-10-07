package threadPool;

/**
 * 线程池拒绝策略上层接口
 * Created by TOM
 * On 2019/10/7 16:08
 */
@FunctionalInterface
public interface DenyPolicy {

  //todo
  void reject();
}
