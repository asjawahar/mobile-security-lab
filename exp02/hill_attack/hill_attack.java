// hill attack
import java.util.*;
import java.io.*;
import java.lang.*;

class hill_attack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the cipher text");
        String cipher = sc.nextLine();
        System.out.println("Enter the key");
        String key = sc.nextLine();
        int n = key.length();
        int[][] key_matrix = new int[n][n];
        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < key_matrix[i].length; j++) {
                key_matrix[i][j] = key.charAt(k++) - 97;
            }
        }
        int[][] cipher_matrix = new int[n][1];
        k = 0;
        for (int i = 0; i < n; i++) {
            cipher_matrix[i][0] = cipher.charAt(k++) - 97;
        }
        int[][] plain_matrix = new int[n][1];
        for (int i = 0; i < n; i++) {
            plain_matrix[i][0] = 0;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < key_matrix[i].length; j++) {
                plain_matrix[i][0] += key_matrix[i][j] * cipher_matrix[j][0];
            }
            plain_matrix[i][0] = plain_matrix[i][0] % 26;
        }
        String plain = "";
        for (int i = 0; i < n; i++) {
            plain += (char) (plain_matrix[i][0] + 97);
        }
        System.out.println("The plain text is " + plain);
    }
}