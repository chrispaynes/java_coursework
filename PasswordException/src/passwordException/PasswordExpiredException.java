package passwordException;

public class PasswordExpiredException extends PasswordException {
  public PasswordExpiredException() {
    super("Password has expired");
  }
  
  public PasswordExpiredException(String msg) {
    super(msg);
  }
  
  public String usage() {
    return new String(
        "This password is set to expire automatically, \n" + "and its number of remaining uses has reached zero");
  };
  
}
