package com.notnl.scl;

import com.notnl.sl.secret.DesCipherSuite;
import com.notnl.scl.secret.FileReadSuite;
import com.notnl.sl.secret.SecretClassLoader;
import com.notnl.scl.util.FileUtil;
import java.lang.reflect.Method;

/**
 *
 * @author noti0na1
 */
public class Launcher {

    private static final String MAIN_CLASS = "HelloClass";

    private static final String KEY_FILE = "ClassKey.ck";

    public static void main(String[] args) throws Exception {
        byte[] key = FileUtil.readFile(KEY_FILE);
        SecretClassLoader scl = new SecretClassLoader(new FileReadSuite(), new DesCipherSuite(), key);
        Class<?> clazz = scl.loadClass(MAIN_CLASS);
        Method main = clazz.getMethod("main", (new String[0]).getClass());
        main.invoke(null, new Object[]{args});
    }
}
