package stockTracker;

public abstract class PasswordException extends Exception {
  public PasswordException() {
    super("Password exception");
  }
  
  public PasswordException(String msg) {
    super(msg);
  }
  
  public abstract String usage();
}
