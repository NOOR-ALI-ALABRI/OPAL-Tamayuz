package CustomerFeedback;

import java.io.*;

public class FeedbackPortal {

    // Step 1: three arrays (size 100) and a counter
    static String[] names = new String[100];
    static int[] ratings = new int[100];
    static String[] comments = new String[100];
    static int count = 0;

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static final String FILE_NAME = "feedback.txt";

    // ================= MAIN + SWITCH =================
    public static void main(String[] args) {
        loadFeedback();

        boolean running = true;
        while (running) {
            showMenu();
            int choice = readNumber();

            switch (choice) {
                case 1:
                    addFeedback();
                    break;
                case 2:
                    showAll();
                    break;
                case 3:
                    showAverage();
                    break;
                case 4:
                    searchByRating();
                    break;
                case 5:
                    deleteFeedback();
                    break;
                case 6:
                    saveFeedback();
                    break;
                case 7:
                    saveFeedback();
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Enter a number from 1 to 7.");
            }
        }
    }

    // ================= METHODS =================

    // Read one line from the keyboard
    static String readLine() {
        try {
            String line = input.readLine();
            return (line == null) ? "" : line.trim();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return "";
        }
    }

    // Read a number safely (try-catch NumberFormatException)
    static int readNumber() {
        try {
            return Integer.parseInt(readLine());
        } catch (NumberFormatException e) {
            return -1; // -1 means invalid input
        }
    }

    // Step 2: menu
    static void showMenu() {
        System.out.println("\n===== CUSTOMER FEEDBACK PORTAL =====");
        System.out.println("1. Add feedback");
        System.out.println("2. Show all feedback");
        System.out.println("3. Show average rating");
        System.out.println("4. Search by rating");
        System.out.println("5. Delete feedback");
        System.out.println("6. Save to file");
        System.out.println("7. Save and exit");
        System.out.print("Choose: ");
    }

    // Step 4: add feedback
    static void addFeedback() {
        if (count >= 100) {
            System.out.println("The list is full.");
            return;
        }

        String name;
        do {
            System.out.print("Name: ");
            name = readLine();
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty.");
            }
        } while (name.isEmpty());

        int rating;
        while (true) {
            System.out.print("Rating (1 to 5): ");
            rating = readNumber();
            if (rating >= 1 && rating <= 5) {
                break;
            } else {
                System.out.println("Invalid rating. Enter a number from 1 to 5.");
            }
        }

        String comment;
        do {
            System.out.print("Comment: ");
            comment = readLine();
            if (comment.isEmpty()) {
                System.out.println("Comment cannot be empty.");
            }
        } while (comment.isEmpty());

        names[count] = name;
        ratings[count] = rating;
        comments[count] = comment;
        count++;
        System.out.println("Feedback added.");
    }

    // Step 5: show all
    static void showAll() {
        if (count == 0) {
            System.out.println("No feedback yet.");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + names[i] + " | " + ratings[i] + " | " + comments[i]);
        }
        System.out.println("Total: " + count);
    }

    // Step 6: average rating
    static void showAverage() {
        if (count == 0) {
            System.out.println("No feedback yet.");
            return;
        }
        int sum = 0;
        for (int i = 0; i < count; i++) {
            sum += ratings[i];
        }
        double average = (double) sum / count;
        System.out.printf("Average rating: %.2f%n", average);
    }

    // Step 7: search by rating
    static void searchByRating() {
        System.out.print("Enter rating to search (1 to 5): ");
        int rating = readNumber();
        if (rating < 1 || rating > 5) {
            System.out.println("Invalid rating.");
            return;
        }
        int found = 0;
        for (int i = 0; i < count; i++) {
            if (ratings[i] == rating) {
                System.out.println((i + 1) + ". " + names[i] + " | " + ratings[i] + " | " + comments[i]);
                found++;
            }
        }
        if (found == 0) {
            System.out.println("No feedback with rating " + rating + ".");
        }
    }

    // Step 8: delete (shift arrays left)
    static void deleteFeedback() {
        System.out.print("Enter feedback number to delete: ");
        int number = readNumber();
        if (number < 1 || number > count) {
            System.out.println("Invalid number.");
            return;
        }
        int index = number - 1;
        for (int i = index; i < count - 1; i++) {
            names[i] = names[i + 1];
            ratings[i] = ratings[i + 1];
            comments[i] = comments[i + 1];
        }
        count--;
        System.out.println("Feedback deleted.");
    }

    // Step 9: save to file
    static void saveFeedback() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (int i = 0; i < count; i++) {
                writer.write(names[i] + "|" + ratings[i] + "|" + comments[i]);
                writer.newLine();
            }
            System.out.println("Saved to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Step 9: load from file
    static void loadFeedback() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return; // first run, no file yet
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null && count < 100) {
                String[] parts = line.split("\\|", 3);
                if (parts.length < 3) {
                    continue; // skip bad line
                }
                try {
                    names[count] = parts[0];
                    ratings[count] = Integer.parseInt(parts[1]);
                    comments[count] = parts[2];
                    count++;
                } catch (NumberFormatException e) {
                    System.out.println("Skipped a bad line: " + line);
                }
            }
            System.out.println(count + " feedback loaded.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

