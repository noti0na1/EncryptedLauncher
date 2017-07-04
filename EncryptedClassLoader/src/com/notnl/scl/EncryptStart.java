package com.notnl.scl;

import com.notnl.scl.util.DesUtil;
import com.notnl.scl.util.FileUtil;
import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author noti0na1
 */
public class EncryptStart {
    
    private static final String KEY_FILE = "ClassKey.ck";

    public static void main(String[] args) throws Exception {
        //创建密钥并储存于文件
        byte[] key = DesUtil.initKey();
        FileUtil.writeFile(KEY_FILE, key);
        File dir = new File("");
        File[] classes = dir.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".class");
            }

        });
        for (File cls : classes) {
            System.out.println(cls.toString());
            byte[] data = FileUtil.readFile(cls);
            byte[] encrypt = DesUtil.encrypt(data, key);
            File newCls = new File(cls.getPath().replaceAll(".class", ".clazz"));
            FileUtil.writeFile(newCls, encrypt);
        }
    }
}
