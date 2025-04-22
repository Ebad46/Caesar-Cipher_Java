package CaesarCipher;
import java.util.ArrayList;
public class Database {
    private static final int MAX_ENTRIES = 2;
    private ArrayList<String> encryptedData;
    private ArrayList<EncryptionDecryption> keys;

    public Database() {
        encryptedData = new ArrayList<>();
        keys = new ArrayList<>();
    }

    public boolean addEntry(String data, char key) {
        if (encryptedData.size() >= MAX_ENTRIES) {
            return false;
        }
        EncryptionDecryption encryptor = new EncryptionDecryption(key);
        encryptedData.add(encryptor.encrypt(data));
        keys.add(encryptor);
        return true;
    }

    public String getEncryptedData() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < encryptedData.size(); i++) {
            result.append((i + 1)).append(". ").append(encryptedData.get(i)).append("\n");
        }
        return result.toString();
    }

    public String decryptEntry(int index, char key) {
        if (index < 0 || index >= encryptedData.size()) {
            return "Invalid entry number!";
        }
        EncryptionDecryption encryptor = keys.get(index);
        if (encryptor.getKey() == key) {
            return encryptor.decrypt(encryptedData.get(index));
        }
        return "Incorrect key!";
    }

    public boolean isFull() {
        return encryptedData.size() >= MAX_ENTRIES;
    }

    public boolean isEmpty() {
        return encryptedData.isEmpty();
    }
}
