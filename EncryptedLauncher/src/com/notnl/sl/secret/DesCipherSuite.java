package com.notnl.sl.secret;

import com.notnl.sl.util.DesUtil;
import java.security.GeneralSecurityException;

/**
 *
 * @author noti0na1
 */
public class DesCipherSuite implements CipherSuite {

    private final byte[] key;

    public DesCipherSuite(byte[] key) {
        this.key = key;
    }

    @Override
    public byte[] decrypt(byte[] data) throws GeneralSecurityException {
        return DesUtil.decrypt(data, key);
    }

    public byte[] getKey() {
        return key;
    }

}
