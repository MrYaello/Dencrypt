package dencrypt;

abstract class EncryptMethod {

    protected char[] chain;
    protected char[] encryptedArray;
    protected int key;
    protected int value;

    protected EncryptMethod(char[] chain, int key) {
        this.chain = chain.clone();
        this.key = key;
        encryptedArray = new char[chain.length];
    }

    abstract String encrypt();
}

public class Encrypt {

    private EncryptMethod method;

    public void setMethod(EncryptMethod method) {
        this.method = method;
    }

    public String encrypt() {
        return this.method.encrypt();
    }
}

class EncryptCaesar extends EncryptMethod {

    public EncryptCaesar(char[] chain, int key) {
        super(chain, key);
    }

    @Override
    public String encrypt() {

        for (int i = 0; i < chain.length; i++) {
            if (chain[i] >= 'A' && chain[i] <= 'Z') {
                if (chain[i] + key > 'Z') {
                    value = (((chain[i] + key) - 'Z') + 'A') - 1;
                } else {
                    value = chain[i] + key;
                }
                encryptedArray[i] = (char) value;
            } else if (chain[i] >= 'a' && chain[i] <= 'z') {
                if (chain[i] + key > 'z') {
                    value = (((chain[i] + key) - 'z') + 'a') - 1;
                } else {
                    value = chain[i] + key;
                }
                encryptedArray[i] = (char) value;
            } else {
                encryptedArray[i] = chain[i];
            }
        }

        return String.valueOf(encryptedArray);
    }
}

class EncryptUnicode extends EncryptMethod {

    public EncryptUnicode(char[] chain, int key) {
        super(chain, key);
    }

    @Override
    public String encrypt() {

        for (int i = 0; i < chain.length; i++) {
            value = chain[i] + key;
            encryptedArray[i] = (char) value;
        }

        return String.valueOf(encryptedArray);
    }
}