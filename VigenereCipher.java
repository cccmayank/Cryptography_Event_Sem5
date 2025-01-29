
import java.util.Scanner;

public class VigenereCipher {

    // Method to encrypt the plaintext using the key
    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        int keyIndex = 0;

        for (char c : plaintext.toCharArray()) {
            if (Character.isLetter(c)) {
                char offset = Character.isUpperCase(c) ? 'A' : 'a';
                ciphertext.append((char) ((c + key.charAt(keyIndex) - 2 * offset) % 26 + offset));
                keyIndex = (keyIndex + 1) % key.length();
            } else {
                ciphertext.append(c); // Non-letter characters are added unchanged
            }
        }
        return ciphertext.toString();
    }

    // Method to decrypt the ciphertext using the key
    public static String decrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();
        int keyIndex = 0;

        for (char c : ciphertext.toCharArray()) {
            if (Character.isLetter(c)) {
                char offset = Character.isUpperCase(c) ? 'A' : 'a';
                plaintext.append((char) ((c - key.charAt(keyIndex) + 26) % 26 + offset));
                keyIndex = (keyIndex + 1) % key.length();
            } else {
                plaintext.append(c); // Non-letter characters are added unchanged
            }
        }
        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter plaintext: ");
        String plaintext = scanner.nextLine();
        
        System.out.print("Enter key: ");
        String key = scanner.nextLine();
        
        String encrypted = encrypt(plaintext, key);
        System.out.println("Encrypted: " + encrypted);
        
        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted: " + decrypted);
        
        scanner.close();
    }
}
