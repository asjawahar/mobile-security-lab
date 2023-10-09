package CryptoAttacks;

import java.util.Scanner;

public class CaesarAttack {
    public static final String ALPHABETS = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select an attack method:");
        System.out.println("1. Brute Force Attack");
        System.out.println("2. Known Cipher and Plain Letter Attack");
        System.out.println("3. Exit");

        boolean flag = true;

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
        System.out.print("Enter cipher to be attacked: ");
        String cipher = scanner.nextLine();

        for (int shift = 0; shift < 26; shift++) {
            StringBuilder plainText = new StringBuilder();
            for (char c : cipher.toCharArray()) {
                int index = ((ALPHABETS.indexOf(Character.toLowerCase(c)) - shift) + 26) % 26;
                char decryptedChar = Character.isLowerCase(c) ? ALPHABETS.charAt(index) : Character.toUpperCase(ALPHABETS.charAt(index));
                plainText.append(decryptedChar);
            }
            System.out.println("Shift Value: " + shift + " Plain Text: " + plainText);
        }
    }

    public static void knownCipherPlainAttack(Scanner scanner) {
        System.out.print("Enter known cipher letter: ");
        char knownCipher = scanner.nextLine().charAt(0);

        System.out.print("Enter corresponding plain letter: ");
        char knownPlain = scanner.nextLine().charAt(0);

        int shift = ((ALPHABETS.indexOf(Character.toLowerCase(knownCipher)) - ALPHABETS.indexOf(Character.toLowerCase(knownPlain))) + 26) % 26;
        System.out.println("Calculated Shift Value: " + shift);

        System.out.print("Enter cipher to be attacked: ");
        String cipher = scanner.nextLine();

        StringBuilder plainText = new StringBuilder();
        for (char c : cipher.toCharArray()) {
            int index = ((ALPHABETS.indexOf(Character.toLowerCase(c)) - shift) + 26) % 26;
            char decryptedChar = Character.isLowerCase(c) ? ALPHABETS.charAt(index) : Character.toUpperCase(ALPHABETS.charAt(index));
            plainText.append(decryptedChar);
        }

        System.out.println("Decrypted Plain Text: " + plainText);
    }
}
