package com.notnl.scl.util;

import com.sun.org.apache.xml.internal.security.utils.Base64;
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

    private static Key toKey(byte[] key) throws Exception {
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(KEY_ALG);
        SecretKey sk = skf.generateSecret(dks);
        return sk;
    }

    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALG);
        cipher.init(Cipher.DECRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALG);
        cipher.init(Cipher.ENCRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    public static byte[] initKey() throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALG);
        kg.init(56);
        SecretKey sk = kg.generateKey();
        return sk.getEncoded();
    }
    
    public static void main(String[] args) throws Exception {
        String inputStr = "DESTest";
        byte[] inputData = inputStr.getBytes();
        System.out.println("原文：" + inputStr);
        //初始化密钥
        byte[] key = DesUtil.initKey();
        System.out.println("密钥：" + Base64.encode(key));
        //加密
        byte[] encyptedData = DesUtil.encrypt(inputData, key);
        System.out.println("加密后：" + Base64.encode(encyptedData));
        //解密
        byte[] decyptedData = DesUtil.decrypt(encyptedData, key);
        System.out.println("解密后：" + new String(decyptedData));
    }
}
