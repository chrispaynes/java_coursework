package passwordException;

public class PasswordUsedException extends PasswordException {
  public PasswordUsedException() {
    super("Password recently used.");
  }
  
  public PasswordUsedException(String msg) {
    super(msg);
  }
  
  public String usage() {
    return new String("This password cannot be reused at this time.");
  };
}
