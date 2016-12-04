package passwordException;

public class PasswordSizeException extends PasswordInvalidFormatException {
  private int pswdSize;
  private int minSize;
  private int maxSize;
  
  public PasswordSizeException(String msg, int pswdSize, int minSize, int maxSize) {
    super(msg);
    this.pswdSize = pswdSize;
    this.minSize = minSize;
    this.maxSize = maxSize;
  }
  
  public int getPswdSize() {
    return pswdSize;
  }
  
  public int getMinSize() {
    return minSize;
  }
  
  public int getMaxSize() {
    return maxSize;
  }
  
}
