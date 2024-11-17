package CaesarCipher;

import java.util.Scanner;

public class MenuManager {
    private Database database;
    private Scanner scanner;

    public MenuManager() {
        database = new Database();
        scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            clearScreen();
            System.out.println("\n----------------------------");
            System.out.println("       DATA MANAGER         ");
            System.out.println("----------------------------");
            System.out.println("1. Input Data");
            System.out.println("2. View Encrypted Database");
            System.out.println("3. Exit");
            System.out.println("----------------------------");
            System.out.print("Choose an option: ");

            int choice = getValidIntegerInput();
            switch (choice) {
                case 1:
                    handleInputData();
                    break;
                case 2:
                    handleViewDatabase();
                    break;
                case 3:
                    System.out.println("Exiting program. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private void handleInputData() {
        clearScreen();
        if (database.isFull()) {
            System.out.println("Database is full. Cannot add more data.");
            pause();
            return;
        }
        System.out.print("Enter data to input: ");
        String data = scanner.nextLine();
        System.out.print("Enter a single character key for encryption: ");
        char key = scanner.nextLine().charAt(0);
        boolean success = database.addEntry(data, key);
        if (success) {
            System.out.println("Data has been encrypted and stored.");
        } else {
            System.out.println("Failed to add data.");
        }
        pause();
    }

    private void handleViewDatabase() {
        if (database.isEmpty()) {
            clearScreen();
            System.out.println("Database is empty.");
            pause();
            return;
        }
        while (true) {
            clearScreen();
            System.out.println("\n----------------------------");
            System.out.println("   ENCRYPTED DATABASE       ");
            System.out.println("----------------------------");
            System.out.print(database.getEncryptedData());
            System.out.println("----------------------------");
            System.out.println("Enter entry number and decryption key to view data or type 'exit' to go back.");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            try {
                int entryNumber = Integer.parseInt(input);
                System.out.print("Decryption key: ");
                char key = scanner.nextLine().charAt(0);
                String result = database.decryptEntry(entryNumber - 1, key);
                System.out.println(result);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
            pause();
        }
    }

    private int getValidIntegerInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a number: ");
            }
        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void pause() {
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }
}