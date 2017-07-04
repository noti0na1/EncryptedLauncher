/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notnl.sl.util;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import org.bouncycastle.util.encoders.Hex;

/**
 *
 * @author noti0na1
 */
public class SHAUtil {

    public static String encodeSHAHex(byte[] data) throws GeneralSecurityException {
        MessageDigest md = MessageDigest.getInstance("SHA");
        return new String(Hex.encode(md.digest(data)));
    }
}
