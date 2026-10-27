package FileHandling;
import java.io.*;
public class FileCopy {
    public static void main(String[] args) {
        String sourcePath = "FileHandling/source.txt";
        String destination = "destination.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(sourcePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(destination))) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("File copied successfully.");


        } catch (FileNotFoundException e) {
            System.out.println("Error: source file not found");
        } catch (IOException e) {
            System.out.println("Error: an I/O problem");
        }
    }
}