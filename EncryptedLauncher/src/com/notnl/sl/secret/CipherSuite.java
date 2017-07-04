package com.notnl.sl.secret;

/**
 *
 * @author noti0na1
 */
public interface CipherSuite {

    public byte[] decrypt(byte[] data) throws Exception;
}
