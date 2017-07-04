package com.notnl.sl.secret;

import java.io.IOException;

/**
 *
 * @author noti0na1
 */
public interface InputSuite {

    /**
     *
     * @param cls like xxx.xxx.Xxx
     * @return
     * @throws java.io.IOException
     */
    public byte[] readFile(String cls) throws IOException;
}
