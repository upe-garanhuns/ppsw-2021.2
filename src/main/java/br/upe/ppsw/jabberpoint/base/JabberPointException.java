package br.upe.ppsw.jabberpoint.base;

public class JabberPointException extends RuntimeException {

  private static final long serialVersionUID = -3598106776661484014L;

  public JabberPointException(String message, Exception cause) {
    super(message, cause);
  }

  public JabberPointException(String message) {
    super(message);
  }

}
