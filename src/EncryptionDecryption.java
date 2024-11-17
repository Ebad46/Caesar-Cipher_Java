package CaesarCipher;
public class EncryptionDecryption {
    private char key;

    public EncryptionDecryption(char key) {
        this.key = key;
    }

    public String encrypt(String data) {
        return process(data);
    }

    public String decrypt(String data) {
        return process(data);
    }

    private String process(String data) {
        StringBuilder result = new StringBuilder();
        for (char c : data.toCharArray()) {
            result.append((char) (c ^ (key - 'a' + 26)));
        }
        return result.toString();
    }

    public char getKey() {
        return key;
    }
}

