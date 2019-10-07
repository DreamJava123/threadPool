package threadPool;

/**
 * Created by TOM
 * On 2019/10/7 16:11
 */
public class DenyException extends RuntimeException {

  public DenyException(String message) {
    super(message);
  }
}
