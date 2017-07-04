package com.notnl.sl.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author noti0na1
 */
public class FileUtil {

    /**
     * 读取单个文件
     *
     * @param filename
     * @return
     * @throws java.io.IOException
     */
    public static byte[] readFile(String filename) throws IOException {
        return readFile(new File(filename));
    }

    /**
     * 读取单个文件
     *
     * @param file
     * @return
     * @throws java.io.IOException
     */
    public static byte[] readFile(File file) throws IOException {
        InputStream ips = null;
        byte[] buffer = null;
        try {
            ips = new FileInputStream(file);
            buffer = new byte[(int) file.length()];
            for (int i = 0; i < buffer.length; i++) {
                buffer[i] = (byte) ips.read();
            }
        } finally {
            if (ips != null) {
                ips.close();
                ips = null;
            }
        }
        return buffer;
    }

    /**
     * 读取文件夹内所有文件
     *
     * @param dir
     * @return
     * @throws java.io.IOException
     */
    public static Map<File, byte[]> readFiles(File dir) throws IOException {
        return readFiles(dir, null);
    }

    /**
     * 读取文件夹内所有文件
     *
     * @param dir
     * @param filter
     * @return
     * @throws java.io.IOException
     */
    public static Map<File, byte[]> readFiles(File dir, FilenameFilter filter) throws IOException {
        if (!dir.isDirectory()) {
            return new HashMap<File, byte[]>(0);
        }
        File[] files = filter == null ? dir.listFiles() : dir.listFiles(filter);
        Map<File, byte[]> fileSet = new HashMap<File, byte[]>();
        for (File file : files) {
            if (file.isFile()) {
                fileSet.put(file, readFile(file));
            } else if (file.isDirectory()) {
                fileSet.putAll(readFiles(file));
            }
        }
        return fileSet;
    }

    /**
     * 输出文件
     *
     * @param filename
     * @param sub
     * @throws java.io.IOException
     */
    public static void writeFile(String filename, byte[] sub) throws IOException {
        writeFile(new File(filename), sub);
    }

    /**
     * 输出文件
     *
     * @param file
     * @param sub
     * @throws java.io.IOException
     */
    public static void writeFile(File file, byte[] sub) throws IOException {
        OutputStream ops = null;
        try {
            makeFile(file);
            ops = new FileOutputStream(file);
            ops.write(sub);
        } finally {
            if (ops != null) {
                ops.close();
                ops = null;
            }
        }
    }

    /**
     * 输出多个文件
     *
     * @param fileSet
     * @throws java.io.IOException
     */
    public static void writeFiles(Map<File, byte[]> fileSet) throws IOException {
        for (Entry<File, byte[]> file : fileSet.entrySet()) {
            writeFile(file.getKey(), file.getValue());
        }
    }

    /**
     * 创建文件
     * 
     * @param file
     * @throws IOException 
     */
    public static void makeFile(File file) throws IOException {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * 私有化构造器
     */
    private FileUtil() {
    }
}
