package com.notnl.sl;

import com.notnl.sl.secret.DesCipherSuite;
import com.notnl.sl.secret.SecretClassLoader;
import com.notnl.sl.secret.SecretInputSuite;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;
import java.util.logging.Logger;
import org.bouncycastle.util.encoders.Hex;

/**
 *
 * @author noti0na1
 */
public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws Exception {
        LOG.info("读取配置文件");
        Properties pro = new Properties();
        pro.load(new FileInputStream(new File(Config.RESOURCE + "properties")));
        String classPath = new String(Hex.decode(pro.getProperty("Class-Path")));
        String mainClass = new String(Hex.decode(pro.getProperty("Main-Class")));
        LOG.info("Class-Path:" + classPath);
        LOG.info("Main-Class:" + mainClass);

        LOG.info("读取密钥");
        byte[] key = Hex.decode(pro.getProperty("Key"));

        LOG.info("初始化ClassLoader");
        URLClassLoader ucl = new URLClassLoader(buildUrls(classPath));
        SecretClassLoader scl = new SecretClassLoader(
                new SecretInputSuite(Config.RESOURCE), new DesCipherSuite(key), ucl);

        LOG.info("载入主类");
        Class<?> clazz = scl.loadClass(mainClass);
        Method main = clazz.getMethod("main", (new String[0]).getClass());
        main.invoke(null, new Object[]{args});
    }

    private static URL[] buildUrls(String str) throws MalformedURLException {
        String[] sub = str.split(" ");
        if (sub == null || sub.length == 0) {
            return new URL[0];
        }
        URL[] urls = new URL[sub.length];
        for (int i = 0; i < sub.length; i++) {
            urls[i] = new URL("file:" + sub[i]);
        }
        return urls;
    }
}
