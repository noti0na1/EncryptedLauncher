package com.notnl.scl.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author noti0na1
 */
public class FileUtil {

    public static byte[] readFile(String filename) throws IOException {
        return readFile(new File(filename));
    }

    public static byte[] readFile(File file) throws IOException {
        long len = file.length();
        byte data[] = new byte[(int) len];
        FileInputStream fin = new FileInputStream(file);
        int r = fin.read(data);

        if (r != len) {
            throw new IOException("Only read " + r + " of " + len + " for " + file);
        }
        fin.close();
        return data;
    }

    public static void writeFile(String filename, byte[] data) throws IOException {
        writeFile(new File(filename), data);
    }

    public static void writeFile(File file, byte[] data) throws IOException {
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(data);
        fout.close();
    }

}
