package com.notnl.sl.secret;

import java.io.IOException;

/**
 *
 * @author noti0na1
 */
public class SecretClassLoader extends ClassLoader {

    private InputSuite inputSuite;
    private CipherSuite cipherSuite;

    public SecretClassLoader(InputSuite inputSuite) {
        this(inputSuite, (CipherSuite) null);
    }

    public SecretClassLoader(InputSuite inputSuite, ClassLoader parent) {
        this(inputSuite, null, parent);
    }

    public SecretClassLoader(InputSuite inputSuite, CipherSuite cipherSuite) {
        super();
        this.inputSuite = inputSuite;
        this.cipherSuite = cipherSuite;
    }

    public SecretClassLoader(InputSuite inputSuite, CipherSuite cipherSuite, ClassLoader parent) {
        super(parent);
        this.inputSuite = inputSuite;
        this.cipherSuite = cipherSuite;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] original;
        try {
            original = this.inputSuite.readFile(name);
        } catch (IOException ex) {
            throw new ClassNotFoundException(ex.getMessage());
        }
        byte[] result;
        if (this.cipherSuite == null) {
            result = original;
        } else {
            try {
                result = this.cipherSuite.decrypt(original);
            } catch (Exception ex) {
                throw new ClassNotFoundException(ex.getMessage());
            }
        }
        Class clazz = defineClass(name, result, 0, result.length);
        return clazz;
    }

    public InputSuite getInputSuite() {
        return inputSuite;
    }

    public void setInputSuite(InputSuite inputSuite) {
        this.inputSuite = inputSuite;
    }

    public CipherSuite getCipherSuite() {
        return cipherSuite;
    }

    public void setCipherSuite(CipherSuite cipherSuite) {
        this.cipherSuite = cipherSuite;
    }

}
