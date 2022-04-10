package br.upe.ppsw.jabberpoint.base;

import java.util.Base64;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class JabberPointUtil {

	private JabberPointUtil() {
	}

	public static final byte[] decode(String coded) {
		try {
			return Base64.getDecoder().decode(coded);
		} catch (Exception e) {
			log.error("Occured a error when decoding image", e);
			throw new JabberPointException("Occured a error when decoding image", e);
		}
	}

	public static final String encode(byte[] decoded) {
		try {
			return Base64.getEncoder().encodeToString(decoded);
		} catch (Exception e) {
			log.error("Occured a error when encoding image", e);
			throw new JabberPointException("Occured a error when encoding image", e);
		}
	}
}
