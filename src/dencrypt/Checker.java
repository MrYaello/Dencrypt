package dencrypt;

import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Checker {

    protected static String mode = "enc";
    protected static String alg = "shift";
    protected static String data = "";
    protected static String inPath = "./";
    protected static String outPath = "./";
    protected static int key = 0;

    public static void argsChecker(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (args.length == 1 && args[0].equals("-help")) {
            helpMessage();
        } else if (args.length > 0) {
            for (int i = 0; i < args.length - 1; i++) {
                if ("-in".equals(args[i])) {
                    inPath += args[i + 1];
                } else if ("-out".equals(args[i])) {
                    outPath += args[i + 1];
                } else if ("-mode".equals(args[i])) {
                    mode = args[i + 1];
                } else if ("-key".equals(args[i])) {
                    key = Integer.parseInt(args[i + 1]);
                } else if ("-data".equals(args[i])) {
                    data = args[i + 1];
                } else if ("-alg".equals(args[i])) {
                    alg = args[i + 1];
                } else if (args[i].contains("-") && !args[i].contains(".")) {
                    System.out.printf("Error! Parameter %s is invalid.", args[i]);
                }
            }

            if (data.isEmpty()) {
                try {
                    data = fileToString(inPath);
                } catch (IOException e) {
                    System.out.println("Error! Cannot read file: " + e.getMessage());
                }
            }
        } else {
            String useFiles;

            System.out.println("Write the action to do: encrypt - enc, decrypt - dec");
            mode = sc.nextLine().toLowerCase().trim();
            System.out.println("enc".equals(mode)
                    ? "What encryption method would you like to use? Caesar - shift, Unicode - unicode"
                    : "What decryption method would you like to use? Caesar - shift, Unicode - unicode");
            alg = sc.nextLine().toLowerCase().trim();
            System.out.println("Do you wanna use a file as input? Yes - y, No - n");
            useFiles = sc.nextLine().trim();
            if (useFiles.equalsIgnoreCase("y")) {
                System.out.println("Type the file path: Example - \"file.txt\"");
                inPath += sc.nextLine().trim().replace("\"", "");
            } else {
                System.out.println("Type the data to process:");
                data = sc.nextLine().trim();
            }
            System.out.println("Do you wanna use a file as output? Yes - y, No (Print in Terminal) - n ");
            useFiles = sc.nextLine().trim();
            if (useFiles.equalsIgnoreCase("y")) {
                System.out.println("Type the file path: Example - \"file.txt\"");
                outPath += sc.nextLine().trim().replace("\"", "");
            }
            System.out.println("Finally, input the key to use:");
            key = sc.nextInt();
        }
    }

    private static void helpMessage() {
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("| EncryptDecrypt - 1.1 | Help Message                          - [] X |");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println(" Run file without arguments to use the GUI mode");
        System.out.println();
        System.out.println(" Displays this message: -help");
        System.out.println(" Setup encrypt or decrypt mode: -mode (encrypt - enc, decrypt - dec)");
        System.out.println(" Select the algorithm to use: -alg (Caesar - shift, Unicode - unicode)");
        System.out.printf(" Send the data to use: -data %c(Data to encrypt)%c\n", '"', '"');
        System.out.printf(" Or if you want to use a file as data: -in %c(/home/InputFilePath.txt)%c\n", '"', '"');
        System.out.printf(" Print in terminal or save in a file: -out %c(/home/SaveFilePath.txt)%c\n", '"', '"');
        System.out.println(" Config the key to use: -key (Integer Number)");
        System.out.println("-----------------------------------------------------------------------");
    }

    public static String fileToString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}

