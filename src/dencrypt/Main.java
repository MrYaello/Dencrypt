package dencrypt;

import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;

public class Main extends Checker {

    public static void main(String[] args) {

        argsChecker(args);

        char[] dataArray = data.toCharArray();
        File outFile = new File(outPath);

        switch (mode) {
            case "enc":
                doEnc(outFile, dataArray);
                break;
            case "dec":
                doDec(outFile, dataArray);
                break;
            default:
                System.out.println("Error! Invalid mode parameter:" + mode);
                break;
        }
    }

    public static void doEnc(File outFile, char[] dataArray) {
        Encrypt encryptMethod = new Encrypt();
        if ("shift".equals(alg)) {
            encryptMethod.setMethod(new EncryptCaesar(dataArray, key));
        } else if ("unicode".equals(alg)) {
            encryptMethod.setMethod(new EncryptUnicode(dataArray, key));
        } else {
            System.out.println("Error! Invalid alg parameter:" + alg);
        }

        if ("./".equals(outPath)) {
            System.out.println(encryptMethod.encrypt());
        } else {
            try (PrintWriter printWriter = new PrintWriter(outFile)) {
                printWriter.println(encryptMethod.encrypt());
            } catch (IOException e) {
                System.out.println("Error! An exception occurs. Contact to developer: " + e.getMessage());
            }
        }
    }

    public static void doDec(File outFile, char[] dataArray) {
        Decrypt decryptMethod = new Decrypt();
        if ("shift".equals(alg)) {
            decryptMethod.setMethod(new DecryptCaesar(dataArray, key));
        } else if ("unicode".equals(alg)) {
            decryptMethod.setMethod(new DecryptUnicode(dataArray, key));
        } else {
            System.out.println("Error! Invalid alg parameter:" + alg);
        }

        if ("./".equals(outPath)) {
            System.out.println(decryptMethod.decrypt());
        } else {
            try (PrintWriter printWriter = new PrintWriter(outFile)) {
                printWriter.println(decryptMethod.decrypt());
            } catch (IOException e) {
                System.out.println("Error! An exception occurs. Contact to developer: " + e.getMessage());
            }
        }
    }
}