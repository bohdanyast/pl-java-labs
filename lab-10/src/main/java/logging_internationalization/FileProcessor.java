package logging_internationalization;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;


class FileProcessor {
    private static final Logger logger = Logger.getLogger(FileProcessor.class.getName());

    static {
        logger.setLevel(Level.SEVERE);
    }

    public void writeToFile(List<String> data, String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(data);
            logger.info("Дані успішно записано в файл: " + fileName);
        } catch (IOException e) {
            logger.severe("Помилка при записі в файл: " + e.getMessage());
        }
    }

    public List<String> readFromFile(String fileName) {
        List<String> data = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            data = (List<String>) in.readObject();
            logger.info("Дані успішно зчитано з файлу: " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            logger.severe("Помилка при зчитуванні з файлу: " + e.getMessage());
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

        logger.info("Знайдено рядок з найбільшою кількістю слів: " + maxLine);
        return maxLine;
    }
}