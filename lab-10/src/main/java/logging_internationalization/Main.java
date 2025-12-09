package logging_internationalization;

import java.util.*;
import java.util.logging.*;
import java.io.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    static ResourceBundle messages;

    public static void main(String[] args) {
        int languageChoice;
        while (true) {
            System.out.print("1. Українська\n2. English\nОберіть мову (Select language): ");
            if (scanner.hasNextInt()) {
                languageChoice = scanner.nextInt();
                scanner.nextLine();

                if (languageChoice == 1 || languageChoice == 2) {
                    break;
                } else {
                    System.out.println("Невірний вибір. Спробуйте ще раз. (Invalid choice. Please try again.)");
                }
            } else {
                System.out.println("Будь ласка, введіть число 1 або 2. (Please enter a number 1 or 2.)");
                scanner.nextLine();
            }
        }

        String languageCode = (languageChoice == 1) ? "uk" : "en";
        messages = ResourceBundle.getBundle("location/messages", Locale.forLanguageTag(languageCode));

        // Налаштування логера
        try {
            logger.setLevel(Level.ALL);
            logger.setUseParentHandlers(false);

            // Обробник для виведення в консоль (тільки SEVERE)
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.SEVERE);
            logger.addHandler(consoleHandler);

            // Обробник для запису в файл (всі рівні)
            FileHandler fileHandler = new FileHandler("app.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.ALL);
            logger.addHandler(fileHandler);

        } catch (IOException e) {
            System.out.println("Помилка при налаштуванні логування: " + e.getMessage());
        }

        while (true) {
            System.out.println("\n" + messages.getString("lab_5"));
            System.out.println("1. " + messages.getString("option1"));
            System.out.println("2. " + messages.getString("option2"));
            System.out.println("3. " + messages.getString("option3"));
            System.out.println("4. " + messages.getString("option4"));

            System.out.println(messages.getString("enterOption"));
            int option = scanner.nextInt();
            scanner.nextLine();

            logger.info("Користувач вибрав опцію: " + option);

            switch (option) {
                case 1:
                    getMaxWordsLine();
                    break;
                case 2:
                    handleCipherDecipher();
                    break;
                case 3:
                    handleUrlAnalysis();
                    break;
                case 4:
                    System.out.println(messages.getString("exitMessage"));
                    logger.info("Програма завершила роботу.");
                    System.exit(0);
                    break;
                default:
                    System.out.println(messages.getString("errorOption"));
                    logger.warning("Користувач ввів некоректну опцію: " + option);
            }
        }
    }

    private static void getMaxWordsLine() {
        FileProcessor fileProcessor = new FileProcessor();

        System.out.print(messages.getString("promptFileOption1"));
        String fileName = scanner.nextLine();

        List<String> data = new ArrayList<>();
        System.out.println(messages.getString("promptLinesOption1"));
        String input;
        while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
            data.add(input);
        }

        fileProcessor.writeToFile(data, fileName);
        logger.info("Дані успішно збережено в файл: " + fileName);

        List<String> fileData = fileProcessor.readFromFile(fileName);

        String result = fileProcessor.findMaxWordCountString(fileData);
        System.out.println(messages.getString("resultOption1") + result);
        logger.info("Знайдено рядок з найбільшою кількістю слів: " + result);
    }

    private static void handleCipherDecipher() {
        FileProcessor fileProcessor = new FileProcessor();
        CipherProcessor cipherProcessor = new CipherProcessor();

        System.out.print(messages.getString("promptFileOption1"));
        String fileName = scanner.nextLine();

        System.out.println(messages.getString("promptLineOption2"));
        String encrypt = scanner.nextLine();

        System.out.println(messages.getString("keyOption2"));
        char key = scanner.next().charAt(0);

        String encryptedLineSer = cipherProcessor.encrypt(encrypt, key);
        List<String> data = new ArrayList<>();
        data.add(encryptedLineSer);

        fileProcessor.writeToFile(data, fileName);
        logger.info("Дані шифрування успішно збережені в файл: " + fileName);

        String encryptedLine = fileProcessor.readFromFile(fileName).get(0);
        System.out.println(messages.getString("cipheredLineOption2") + encryptedLine);

        String decryptedLineSer = cipherProcessor.decrypt(encryptedLine, key);
        data.add(decryptedLineSer);
        fileProcessor.writeToFile(data, fileName);
        logger.info("Дані дешифрування успішно збережені в файл: " + fileName);

        String decryptedLine = fileProcessor.readFromFile(fileName).get(1);
        System.out.println(messages.getString("decipheredLineOption2") + decryptedLine);
    }

    public static void handleUrlAnalysis() {
        TagCounter tagCounter = new TagCounter();

        System.out.println(messages.getString("promptLinkOption3"));
        String url = scanner.nextLine();

        String htmlContent = tagCounter.readHtml(url);
        Map<String, Integer> tagsFreq = tagCounter.getTagsFrequencies(htmlContent);

        System.out.println(messages.getString("lexOrderOption3"));
        tagCounter.displayTagsFrequenciesInAlphabetOrder(tagsFreq);

        System.out.println("\n" + messages.getString("descOrderOption3"));
        tagCounter.displayTagsFrequenciesInDescendingOrder(tagsFreq);
        logger.info("Аналіз тегів завершено для URL: " + url);
    }
}