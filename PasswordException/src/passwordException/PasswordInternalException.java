package passwordException;

public final class PasswordInternalException extends PasswordException {
  public PasswordInternalException(String msg) {
    super(msg);
  }
  
  public String usage() {
    return new String("Internal error in the collection " + "conaining the password history. This never should occur.");
  }
}
