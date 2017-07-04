package com.notnl.scl.secret;

import com.notnl.sl.secret.IOSuite;
import com.notnl.scl.util.FileUtil;
import java.io.IOException;

/**
 *
 * @author noti0na1
 */
public class FileReadSuite implements IOSuite {

    private static final String CLASS_FILE_SUFFIX= "clazz";

    @Override
    public byte[] readFile(String cls) throws IOException {
        String filename = cls.replace('.', '/') + '.' + CLASS_FILE_SUFFIX;
        return FileUtil.readFile(filename);
    }

}
