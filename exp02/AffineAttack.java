package CryptoAttacks;

import Ciphers.Utils;

import java.util.Scanner;
import java.util.List;

import static Ciphers.Utils.gcd;

public class AffineAttack {
    public static final String ALPHABETS = "abcdefghijklmnopqrstuvwxyz";
    public static final List<Integer> POSSIBLE_A = List.of(1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select an attack method:");
        System.out.println("1. Brute Force Attack");
        System.out.println("2. Known Cipher and Plain Text Attack");
        System.out.println("3. Exit");

        boolean flag =  true;

        while(flag){
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    bruteForceAttack(scanner);
                    break;
                case 2:
                    knownCipherPlainAttack(scanner);
                    break;
                case 3:
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }

        scanner.close();
    }

    public static void bruteForceAttack(Scanner scanner) {
        System.out.print("Enter cipher text to be brute forced: ");
        String cipherText = scanner.nextLine();

        for (int a : POSSIBLE_A) {
            for (int b = 0; b < 26; b++) {
                int inv = Utils.modInverse(a, 26);
                StringBuilder plain = new StringBuilder();

                for (char k : cipherText.toCharArray()) {
                    int index = (((((ALPHABETS.indexOf(k) - b) * inv) % 26) + 26) % 26);
                    plain.append(ALPHABETS.charAt(index));
                }

                System.out.println("A: " + a + " B: " + b + " Plain Text: " + plain);
            }
            System.out.print("Check for valid plain text. If not present, press ENTER to continue. If present enter 'q' to exit: ");
            String input = scanner.nextLine();
            if (input.equals("q")) {
                break;
            }
        }
    }

    public static void knownCipherPlainAttack(Scanner scanner) {
        System.out.println("Enter atleast two letters:");
        System.out.print("Enter known cipher letters: ");
        String knownCipher = scanner.nextLine();
        System.out.print("Enter corresponding plain letters: ");
        String knownPlain = scanner.nextLine();
        
        int a = 0, b = 0;
        boolean flag = true;

        for (int i = 1; i < 26; i++) {
            if(!flag)
                break;
            for (int j = 0; j < 26; j++) {
                if (gcd(i, j) == 1) {
                    StringBuilder temp = new StringBuilder();
                    int inv = Utils.modInverse(i, 26);

                    for (char ch : knownCipher.toCharArray()) {
                        int num = (((ALPHABETS.indexOf(ch) - j) * inv) % 26 + 26) % 26;
                        temp.append(ALPHABETS.charAt(num));
                    }

                    if (temp.toString().equals(knownPlain)) {
                        a = i;
                        b = j;
                        flag = false;
                        break;
                    }

                }
            }
        }

        System.out.println(a + " " +  b);

        System.out.print("Enter cipher to be attacked: ");
        String cipherText = scanner.nextLine();

        int inv = Utils.modInverse(a, 26);
        StringBuilder plainText = new StringBuilder();

        for (char c : cipherText.toCharArray()) {
            int index = (((((ALPHABETS.indexOf(c) - b) * inv) % 26) + 26) % 26);
            plainText.append(ALPHABETS.charAt(index));
        }

        System.out.println("Decrypted Plain Text: " + plainText);
    }

}
