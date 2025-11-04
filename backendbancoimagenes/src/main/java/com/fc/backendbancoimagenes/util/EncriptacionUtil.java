package com.fc.backendbancoimagenes.util;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncriptacionUtil {
	private static final String SECRET = "miClaveSecretaWA";
	private static final String ALGORITHM = "AES";

	public static String Encriptacion(String input) throws Exception {
        SecretKeySpec key = new SecretKeySpec(SECRET.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String Desencriptacion(String encrypted) throws Exception {
        SecretKeySpec key = new SecretKeySpec(SECRET.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encrypted));
        return new String(decrypted);
    }
}