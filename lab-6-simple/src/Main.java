import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static EngUkrTranslator engUkrTranslator = new EngUkrTranslator();

    public static void main(String[] args) {

        while (true) {
            System.out.println("Вас вітає перекладач з англійської мови! Наявні опції: ");
            System.out.println("1. Додати слово і переклад");
            System.out.println("2. Перекласти з англійської слово");
            System.out.println("3. Переглянути словник");
            System.out.println("4. Вийти з програми");

            System.out.println("Введіть номер опції: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addWordAndTranslation();
                    break;
                case 2:
                    translateEnglishWord();
                    break;
                case 3:
                    engUkrTranslator.getAllWordsAndTranslations();
                    break;
                case 4:
                    System.out.println("До зустрічі!");
                    System.exit(0);
                default:
                    System.out.println("Опції не існує!");
            }
        }
    }

    private static void addWordAndTranslation() {
        System.out.println("Введіть українське слово: ");
        String ukrWord = scanner.nextLine();

        System.out.println("Введіть англійське слово: ");
        String engWord = scanner.nextLine();

        engUkrTranslator.addWord(ukrWord, engWord);
        System.out.println("Успішно додано!");
    }

    private static void translateEnglishWord() {
        System.out.println("Введіть англійське слово: ");
        String engWord = scanner.nextLine();
        String ukrWord = engUkrTranslator.translateFromEnglish(engWord);

        if (ukrWord != null) {
            System.out.println("Переклад: " + ukrWord);
        } else {
            System.out.println("Слова немає в словнику");
        }

    }
}