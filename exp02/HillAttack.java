package CryptoAttacks;

import java.util.Scanner;

public class HillAttack {
    private static int[][] keyMatrix;
    private static int keySize;
    private static String cipherTextTry = "";

    public static final String ALPHABETS = "abcdefghijklmnopqrstuvwxyz";
    private static void encrypt(String plaintext) {
        int[] plainTextVector = new int[keySize];

        for (int i = 0; i < keySize; ++i)
            plainTextVector[i] = plaintext.charAt(i) - 'a';

        int[]cipherTextVector = new int[keySize];

        for (int i = 0; i < keySize; ++i){
            for (int k = 0; k < keySize; ++k)
                cipherTextVector[i] += keyMatrix[i][k] * plainTextVector[k];
            cipherTextVector[i] %= 26;
        }

        for(int i = 0; i < keySize; ++i)
            cipherTextTry += (char) (cipherTextVector[i] + 'a');
    }

    private static void getCoFactor(int[][] matrix, int[][] tmp, int r, int c, int sz){
        int i = 0, j = 0;

        for(int row = 0; row < sz; ++row)
            for(int col = 0; col < sz; ++col)
                if(row != r && col != c){
                    tmp[i][j++] = matrix[row][col];
                    if(j == sz - 1){
                        j = 0;
                        i++;
                    }
                }
    }

    private static int determinant(int matrix[][], int sz){
        int det;

        if(sz == 1)
            det = matrix[0][0];
        else if(sz == 2)
            det = matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1];
        else{
            det = 0;

            int[][] tmp = new int[sz][sz];

            int sign = 1;

            for(int c = 0; c < sz; ++c){
                getCoFactor(matrix, tmp, 0, c, sz);
                det += sign * matrix[0][c] * determinant(tmp, sz - 1);
                sign *= -1;
            }
        }

        return det % 26;
    }

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter plain text: ");
        String plainText = scanner.nextLine();

        System.out.print("Enter cipher text: ");
        String cipherText = scanner.nextLine();

        System.out.print("Enter the key size: ");
        keySize = scanner.nextInt();

        scanner.close();

        int totalCombinations = (int) Math.pow(26, keySize * keySize);

        for (int l = 0; l < totalCombinations; l++) {
            StringBuilder sb = new StringBuilder();
            int index = l;

            for (int j = 0; j < keySize * keySize; j++) {
                sb.append(ALPHABETS.charAt(index % 26));
                index /= 26;
            }

            String key = sb.toString();
            System.out.println(key);

            keyMatrix = new int[keySize][keySize];

            keySize = (int) Math.sqrt(key.length());

            keyMatrix = new int[keySize][keySize];

            int k = 0;
            for (int i = 0; i < keySize; i++)
                for (int j = 0; j < keySize; j++)
                    keyMatrix[i][j] = key.charAt(k++) - 'a';

            int det = determinant(keyMatrix, keySize);

            if(!(det == 0) && !(det % 2 == 0 || det % 13 == 0)){

                String plainTextTmp = plainText;
                while (plainTextTmp.length() > keySize){
                    String subPlain = plainTextTmp.substring(0, keySize);
                    plainTextTmp = plainTextTmp.substring(keySize, plainTextTmp.length());
                    encrypt(subPlain);
                }

                if (plainTextTmp.length() == keySize)
                    encrypt(plainTextTmp);
                else {
                    for (int i = plainTextTmp.length(); i < keySize; ++i)
                        plainTextTmp = plainTextTmp + 'x';
                    encrypt(plainTextTmp);
                }


                if(cipherTextTry.equals(cipherText)){
                    System.out.println("Key is: " + key);
                    break;
                }

                cipherTextTry = "";
            }
        }
    }
}
