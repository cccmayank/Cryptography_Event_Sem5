// affine.java

import java.util.Scanner;

public class AffineCipher {
    private static final int ALPHABET_SIZE = 26;

    // Function to encrypt the plaintext using the Affine Cipherhello 
    public static String encrypt(String plaintext, int a, int b) {
        StringBuilder ciphertext = new StringBuilder();

        for (char character : plaintext.toCharArray()) {
            if (Character.isLetter(character)) {
                // Convert character to 0-25 range
                int x = Character.toLowerCase(character) - 'a';
                // Apply the affine transformation
                int encryptedChar = (a * x + b) % ALPHABET_SIZE;
                // Convert back to character
                ciphertext.append((char) (encryptedChar + 'a'));
            } else {
                ciphertext.append(character); // Non-letter characters remain unchanged
            }
        }
        return ciphertext.toString();
    }

    // Function to decrypt the ciphertext using the Affine Cipher
    public static String decrypt(String ciphertext, int a, int b) {
        StringBuilder plaintext = new StringBuilder();
        int aInverse = modInverse(a, ALPHABET_SIZE);

        for (char character : ciphertext.toCharArray()) {
            if (Character.isLetter(character)) {
                int y = Character.toLowerCase(character) - 'a';
                // Apply the inverse affine transformation
                int decryptedChar = (aInverse * (y - b + ALPHABET_SIZE)) % ALPHABET_SIZE;
                plaintext.append((char) (decryptedChar + 'a'));
            } else {
                plaintext.append(character); // Non-letter characters remain unchanged
            }
        }
        return plaintext.toString();
    }

    // Function to find modular inverse of a under modulo m
    private static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return 1; // If no modular inverse exists, return 1 (not ideal)
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String plaintext = scanner.nextLine();

        System.out.print("Enter value for a (must be coprime to 26): ");
        int a = scanner.nextInt();

        System.out.print("Enter value for b: ");
        int b = scanner.nextInt();

        String encrypted = encrypt(plaintext, a, b);
        System.out.println("Encrypted: " + encrypted);

        String decrypted = decrypt(encrypted, a, b);
        System.out.println("Decrypted: " + decrypted);

        scanner.close();
    }
}
