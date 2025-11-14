import java.io.*;

/**
 * Boohbah Message Decryptor
 *
 * STORY: The Boohbah have intercepted a secret message from an unknown enemy.
 * Intelligence suggests it's encrypted with a Caesar cipher (shift unknown).
 * Your mission: Read the encrypted message, decrypt it, and write the
 * decrypted message to a file. Then determine who is attacking!
 */
public class BoohbahMessageDecryptor {

    public static String decrypt(String encrypted, int shift) {
        StringBuilder decrypted = new StringBuilder();

        for (char c : encrypted.toCharArray()) {
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    char shifted = (char) (c - shift);
                    if (shifted < 'A') shifted += 26;
                    decrypted.append(shifted);
                } else {
                    char shifted = (char) (c - shift);
                    if (shifted < 'a') shifted += 26;
                    decrypted.append(shifted);
                }
            } else {
                decrypted.append(c);
            }
        }

        return decrypted.toString();
    }

    public static void main(String[] args) {

        String inputFile = "C:\\Users\\jnkamienski\\IdeaProjects\\BoohbahMessageDecryptor\\src\\encrypted_message.txt";
        String outputFile = "decrypted_message.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {

            // Read encrypted message
            String encryptedMessage = reader.readLine();

            // Try all shifts
            for (int shift = 0; shift < 26; shift++) {
                String attempt = decrypt(encryptedMessage, shift);
                // System.out.println("Shift " + shift + ": " + attempt);
            }

            // TODO: set this once you manually find the correct shift
            int correctShift = 3;  // <-- replace with correct value

            // Write decrypted output
            try (FileWriter writer = new FileWriter(outputFile)) {
                String decryptedMessage = decrypt(encryptedMessage, correctShift);
                writer.write(decryptedMessage);
            }

            System.out.println("Decryption complete. Output written to " + outputFile);

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Input file not found: " + inputFile, e);
        } catch (IOException e) {
            throw new RuntimeException("I/O error occurred", e);
        }
    }
}
