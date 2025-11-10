import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nЛабораторна робота №5. Наявні опції для вибору:");
            System.out.println("1. Рядок з максимальною кількістю слів у файлі: ");
            System.out.println("2. Шифрування/дешифрування за ключовим символом (шифр Цезаря): ");
            System.out.println("3. Частота тегів на сторінці:");
            System.out.println("4. Вихід з програми:");

            System.out.println("Введіть номер опції: ");
            int option = scanner.nextInt();
            scanner.nextLine();

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
                    System.out.println("До зустрічі!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Опції з номером " + option + " немає!");
            }
        }
    }

    private static void getMaxWordsLine() {
        FileProcessor fileProcessor = new FileProcessor();

        System.out.print("Введіть ім'я файлу для збереження даних: ");
        String fileName = scanner.nextLine();

        List<String> data = new ArrayList<>();
        System.out.println("Введіть рядки для збереження в файл. Для завершення введіть 'end'.");
        String input;
        while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
            data.add(input);
        }

        fileProcessor.writeToFile(data, fileName);
        System.out.println("Дані успішно збережені в файл.");

        List<String> fileData = fileProcessor.readFromFile(fileName);

        String result = fileProcessor.findMaxWordCountString(fileData);
        System.out.println("Рядок з найбільшою кількістю слів: " + result);
    }

    private static void handleCipherDecipher() {
        FileProcessor fileProcessor = new FileProcessor();
        CipherProcessor cipherProcessor = new CipherProcessor();

        System.out.print("Введіть ім'я файлу для збереження даних: ");
        String fileName = scanner.nextLine();

        System.out.println("Введіть рядок, який хочете зашифрувати:");
        String encrypt = scanner.nextLine();

        System.out.println("Введіть ключовий символ:");
        char key = scanner.next().charAt(0);

        String encryptedLineSer = cipherProcessor.encrypt(encrypt, key);
        List<String> data = new ArrayList<>();
        data.add(encryptedLineSer);

        fileProcessor.writeToFile(data, fileName);
        System.out.println("Дані шифрування успішно збережені в файл.");

        String encryptedLine = fileProcessor.readFromFile(fileName).get(0);
        System.out.println("Отримане шифрування з файлу: " + encryptedLine);

        String decryptedLineSer = cipherProcessor.decrypt(encryptedLine, key);
        data.add(decryptedLineSer);
        fileProcessor.writeToFile(data, fileName);
        System.out.println("Дані дешифрування успішно збережені в файл.");

        String decryptedLine = fileProcessor.readFromFile(fileName).get(1);
        System.out.println("Отримане дешифрування з файлу: " + decryptedLine);
    }

    public static void handleUrlAnalysis() {
        TagCounter tagCounter = new TagCounter();

        System.out.println("Введіть посилання: ");
        String url = scanner.nextLine();

        String htmlContent = tagCounter.readHtml(url);
        Map<String, Integer> tagsFreq = tagCounter.getTagsFrequencies(htmlContent);

        System.out.println("Теги в лексикографічному порядку:");
        tagCounter.displayTagsFrequenciesInAlphabetOrder(tagsFreq);

        System.out.println("\nТеги в порядку спадання:");
        tagCounter.displayTagsFrequenciesInDescendingOrder(tagsFreq);
    }
}
