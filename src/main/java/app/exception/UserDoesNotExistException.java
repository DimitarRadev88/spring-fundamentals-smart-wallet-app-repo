package app.exception;

public class UserDoesNotExistException extends RuntimeException {
  public UserDoesNotExistException(String message) {
    super(message);
  }
}
