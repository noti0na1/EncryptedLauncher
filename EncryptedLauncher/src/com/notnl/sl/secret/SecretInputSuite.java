/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notnl.sl.secret;

import com.notnl.sl.util.FileUtil;
import com.notnl.sl.util.SHAUtil;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author noti0na1
 */
public class SecretInputSuite implements InputSuite {

    private final String dir;

    private final Map<String, byte[]> buffer;

    public SecretInputSuite(String dir) {
        this.dir = dir;
        this.buffer = new HashMap<String, byte[]>();
    }

    @Override
    public byte[] readFile(String cls) throws IOException {
        if (this.buffer.containsKey(cls)) {
            return this.buffer.get(cls);
        }
        byte[] data;
        try {
            data = FileUtil.readFile(dir + SHAUtil.encodeSHAHex(cls.getBytes()));
        } catch (GeneralSecurityException ex) {
            throw new IOException(ex.getMessage());
        }
        this.buffer.put(cls, data);
        return data;
    }

}
