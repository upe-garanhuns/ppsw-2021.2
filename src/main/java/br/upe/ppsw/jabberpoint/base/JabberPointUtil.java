package br.upe.ppsw.jabberpoint.base;

import java.util.Base64;

public final class JabberPointUtil {

  private JabberPointUtil() {}

  public static final byte[] decode(String coded) {
    try {
      return Base64.getDecoder().decode(coded);
    } catch (Exception e) {
      throw new JabberPointException("Occured a error when decoding image", e);
    }
  }

  public static final String encode(byte[] decoded) {
    try {
      return Base64.getEncoder().encodeToString(decoded);
    } catch (Exception e) {
      throw new JabberPointException("Occured a error when encoding image", e);
    }
  }
}
