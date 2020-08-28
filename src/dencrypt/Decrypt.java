package dencrypt;

abstract class DecryptMethod {

    protected char[] chain;
    protected char[] decryptedArray;
    protected int key;
    protected int value;

    protected DecryptMethod(char[] chain, int key) {
        this.chain = chain.clone();
        this.key = key;
        decryptedArray = new char[chain.length];
    }

    abstract String decrypt();
}

public class Decrypt {
    private DecryptMethod method;

    public void setMethod(DecryptMethod method) {
        this.method = method;
    }

    public String decrypt() {
        return this.method.decrypt();
    }
}

class DecryptCaesar extends DecryptMethod {

    public DecryptCaesar(char[] chain, int key) {
        super(chain, key);
    }

    @Override
    public String decrypt() {

        for (int i = 0; i < chain.length; i++) {
            if (chain[i] >= 'A' && chain[i] <= 'Z') {
                if (chain[i] - key < 'A') {
                    value = (((chain[i] - key) + 'Z') - 'A') + 1;
                } else {
                    value = chain[i] - key;
                }
                decryptedArray[i] = (char) value;
            } else if (chain[i] >= 'a' && chain[i] <= 'z') {
                if (chain[i] - key < 'a') {
                    value = (((chain[i] - key) + 'z') - 'a') + 1;
                } else {
                    value = chain[i] - key;
                }
                decryptedArray[i] = (char) value;
            } else {
                decryptedArray[i] = chain[i];
            }
        }

        return String.valueOf(decryptedArray);
    }
}

class DecryptUnicode extends DecryptMethod {

    public DecryptUnicode(char[] chain, int key) {
        super(chain, key);
    }

    @Override
    public String decrypt() {

        for (int i = 0; i < chain.length; i++) {
            value = chain[i] - key;
            decryptedArray[i] = (char) value;
        }

        return String.valueOf(decryptedArray);
    }
}