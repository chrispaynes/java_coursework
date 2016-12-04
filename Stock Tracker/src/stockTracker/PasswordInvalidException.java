package stockTracker;

public class PasswordInvalidException extends PasswordException {
  private static int count;
  
  public PasswordInvalidException() {
    super("Invalid password.");
    ++count;
  }
  
  public PasswordInvalidException(String msg) {
    super(msg);
    ++count;
  }
  
  public String usage() {
    return new String("This password does not match any\n" + "value in the password history.");
  }
  
  public final void resetCount() {
    count = 0;
  }
  
  public final int getCount() {
    return count;
  }
}
