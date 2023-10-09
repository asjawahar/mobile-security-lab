package DES;

import java.math.BigInteger;

public class DES {

    // Initial Permutation Table
    private static final int[] initialPerm = {
            58, 50, 42, 34, 26, 18, 10, 2,
            60, 52, 44, 36, 28, 20, 12, 4,
            62, 54, 46, 38, 30, 22, 14, 6,
            64, 56, 48, 40, 32, 24, 16, 8,
            57, 49, 41, 33, 25, 17, 9, 1,
            59, 51, 43, 35, 27, 19, 11, 3,
            61, 53, 45, 37, 29, 21, 13, 5,
            63, 55, 47, 39, 31, 23, 15, 7
    };

    // Expansion D-box Table
    private static final int[] expD = {
            32, 1, 2, 3, 4, 5, 4, 5,
            6, 7, 8, 9, 8, 9, 10, 11,
            12, 13, 12, 13, 14, 15, 16, 17,
            16, 17, 18, 19, 20, 21, 20, 21,
            22, 23, 24, 25, 24, 25, 26, 27,
            28, 29, 28, 29, 30, 31, 32, 1
    };

    // Straight Permutation Table
    private static final int[] per = {
            16, 7, 20, 21, 29, 12, 28, 17,
            1, 15, 23, 26, 5, 18, 31, 10,
            2, 8, 24, 14, 32, 27, 3, 9,
            19, 13, 30, 6, 22, 11, 4, 25
    };

    // S-box Table
    private static final int[][][] sbox = {
            {
                    {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
                    {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
                    {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
                    {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
            },
            {
                    {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
                    {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
                    {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
                    {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}
            },
            {
                    {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
                    {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
                    {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
                    {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}
            },
            {
                    {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
                    {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
                    {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
                    {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}
            },
            {
                    {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
                    {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
                    {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
                    {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}
            },
            {
                    {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
                    {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
                    {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
                    {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}
            },
            {
                    {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
                    {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
                    {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
                    {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}
            },
            {
                    {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
                    {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
                    {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
                    {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}
            }
    };

    // Final Permutation Table
    private static final int[] finalPerm = {
            40, 8, 48, 16, 56, 24, 64, 32,
            39, 7, 47, 15, 55, 23, 63, 31,
            38, 6, 46, 14, 54, 22, 62, 30,
            37, 5, 45, 13, 53, 21, 61, 29,
            36, 4, 44, 12, 52, 20, 60, 28,
            35, 3, 43, 11, 51, 19, 59, 27,
            34, 2, 42, 10, 50, 18, 58, 26,
            33, 1, 41, 9, 49, 17, 57, 25
    };

    private static final int[] shiftTable = {
            1, 1, 2, 2,
            2, 2, 2, 2,
            1, 2, 2, 2,
            2, 2, 2, 1
    };

    // Hexadecimal to Binary conversion
    public static String hexToBin(String hex) {
        String bin = new BigInteger(hex, 16).toString(2);
        int len = bin.length();
        // Ensure the binary string is 64 bits long
        while (len < 64) {
            bin = "0" + bin;
            len++;
        }
        return bin;
    }

    // Binary to Hexadecimal conversion
    public static String binToHex(String bin) {
        String hex = new BigInteger(bin, 2).toString(16).toUpperCase();
        int len = hex.length();
        // Ensure the hexadecimal string is 16 characters long
        while (len < 16) {
            hex = "0" + hex;
            len++;
        }
        return hex;
    }

    // Binary to Decimal conversion
    public static int binToDec(String binary) {
        return Integer.parseInt(binary, 2);
    }

    // Decimal to Binary conversion
    public static String decToBin(int decimal) {
        String bin = Integer.toBinaryString(decimal);
        int len = bin.length();
        // Ensure the binary string is 4 bits long
        while (len < 4) {
            bin = "0" + bin;
            len++;
        }
        return bin;
    }

    // Permute function to rearrange the bits
    public static String permute(String input, int[] table, int outputSize) {
        char[] output = new char[outputSize];
        for (int i = 0; i < outputSize; i++) {
            output[i] = input.charAt(table[i] - 1);
        }
        return new String(output);
    }

    // Shift the bits towards left by n shifts
    public static String shiftLeft(String input, int numShifts) {
        int len = input.length();
        return input.substring(numShifts) + input.substring(0, numShifts);
    }

    // Calculate XOR of two binary strings
    public static String xor(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            result.append(a.charAt(i) == b.charAt(i) ? "0" : "1");
        }
        return result.toString();
    }

    public static String encrypt(String plaintext, String key) {
        // Convert plaintext and key to binary
        String binaryPlaintext = hexToBin(plaintext);
        String binaryKey = hexToBin(key);

        // Initial Permutation
        String initialPermutation = permute(binaryPlaintext, initialPerm, 64);

        // Splitting
        String left = initialPermutation.substring(0, 32);
        String right = initialPermutation.substring(32);

        // Generate 16 round keys
        String[] roundKeys = new String[16];
        String roundKey = permute(binaryKey, initialPerm, 56);
        for (int i = 0; i < 16; i++) {
            left = shiftLeft(left, shiftTable[i]);
            right = shiftLeft(right, shiftTable[i]);
            String rightExpanded = permute(right, expD, 48);
            String roundKey56 = permute(roundKey, expD, 48);
            roundKeys[i] = roundKey56;
        }

        // Encryption Rounds
        for (int i = 0; i < 16; i++) {
            String rightExpanded = permute(right, expD, 48);
            String xorResult = xor(rightExpanded, roundKeys[i]);
            String substitution = "";
            for (int j = 0; j < 8; j++) {
                String block = xorResult.substring(j * 6, (j + 1) * 6);
                int row = binToDec(block.charAt(0) + "" + block.charAt(5));
                int col = binToDec(block.substring(1, 5));
                int value = sbox[j][row][col];
                substitution += decToBin(value);
            }
            String permutation = permute(substitution, per, 32);
            String xorResult2 = xor(left, permutation);
            left = xorResult2;
            if (i != 15) {
                // Swap left and right for all rounds except the last
                String temp = left;
                left = right;
                right = temp;
            }
        }

        // Final Permutation
        String finalPermutation = right + left;
        String encryptedBinary = permute(finalPermutation, finalPerm, 64);

        // Convert binary to hexadecimal
        String encryptedHex = binToHex(encryptedBinary);
        return encryptedHex;
    }

    public static String decrypt(String ciphertext, String key) {
        // Convert ciphertext and key to binary
        String binaryCiphertext = hexToBin(ciphertext);
        String binaryKey = hexToBin(key);

        // Initial Permutation
        String initialPermutation = permute(binaryCiphertext, initialPerm, 64);

        // Splitting
        String left = initialPermutation.substring(0, 32);
        String right = initialPermutation.substring(32);

        // Generate 16 round keys
        String[] roundKeys = new String[16];
        String roundKey = permute(binaryKey, initialPerm, 56);
        for (int i = 0; i < 16; i++) {
            left = shiftLeft(left, shiftTable[i]);
            right = shiftLeft(right, shiftTable[i]);
            String rightExpanded = permute(right, expD, 48);
            String roundKey56 = permute(roundKey, expD, 48);
            roundKeys[i] = roundKey56;
        }

        // Decryption Rounds
        for (int i = 15; i >= 0; i--) {
            String rightExpanded = permute(right, expD, 48);
            String xorResult = xor(rightExpanded, roundKeys[i]);
            String substitution = "";
            for (int j = 0; j < 8; j++) {
                String block = xorResult.substring(j * 6, (j + 1) * 6);
                int row = binToDec(block.charAt(0) + "" + block.charAt(5));
                int col = binToDec(block.substring(1, 5));
                int value = sbox[j][row][col];
                substitution += decToBin(value);
            }
            String permutation = permute(substitution, per, 32);
            String xorResult2 = xor(left, permutation);
            left = xorResult2;
            if (i != 0) {
                // Swap left and right for all rounds except the first
                String temp = left;
                left = right;
                right = temp;
            }
        }

        // Final Permutation
        String finalPermutation = right + left;
        String decryptedBinary = permute(finalPermutation, finalPerm, 64);

        // Convert the final binary data to hexadecimal to get the plaintext
        String decryptedPlaintext = binToHex(decryptedBinary);
        return decryptedPlaintext;
    }

    public static void main(String[] args) {
        String plaintext = "0123456789ABCDEF"; // 16-byte plaintext in uppercase hexadecimal
        String key = "133457799BBCDFF1"; // 16-byte key in uppercase hexadecimal

        String encryptedText = encrypt(plaintext, key);
        System.out.println("Encrypted Text: " + encryptedText);

        String decryptedText = decrypt(encryptedText, key);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}

