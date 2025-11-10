import java.io.*;
import java.util.*;

class FileProcessor {

    public void writeToFile(List<String> data, String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(data);
        } catch (IOException e) {
            System.out.println("Помилка при записі в файл: " + e.getMessage());
        }
    }

    public List<String> readFromFile(String fileName) {
        List<String> data = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            data = (List<String>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Помилка при зчитуванні з файлу: " + e.getMessage());
        }
        return data;
    }

    public String findMaxWordCountString(List<String> data) {
        String maxLine = "";
        int maxWords = 0;

        for (String line : data) {
            int wordCount = line.split("\\s+").length;
            if (wordCount > maxWords) {
                maxWords = wordCount;
                maxLine = line;
            }
        }

        return maxLine;
    }
}