package com.notnl.sl.util;

import java.security.GeneralSecurityException;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 *
 * @author noti0na1
 */
public final class DesUtil {

    public static final String KEY_ALG = "DES";

    public static final String CIPHER_ALG = "DES/ECB/PKCS5Padding";

    private static Key toKey(byte[] key) throws GeneralSecurityException {
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(KEY_ALG);
        SecretKey sk = skf.generateSecret(dks);
        return sk;
    }

    public static byte[] decrypt(byte[] data, byte[] key) throws GeneralSecurityException {
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALG);
        cipher.init(Cipher.DECRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    public static byte[] encrypt(byte[] data, byte[] key) throws GeneralSecurityException {
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALG);
        cipher.init(Cipher.ENCRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    public static byte[] initKey() throws GeneralSecurityException {
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALG);
        kg.init(56);
        SecretKey sk = kg.generateKey();
        return sk.getEncoded();
    }
}
