import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    static Turnstile turnstile = new Turnstile();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nВас вітає турнікет TUR-01! Наявні опції для роботи:");
            System.out.println("1. Випустити картку.");
            System.out.println("2. Зчитати картку.");
            System.out.println("3. Переглянути загальну статистику відмов та успіхів.");
            System.out.println("4. Переглянути статистику по випущених картках (за типом).");
            System.out.println("5. Вийти з системи.");

            System.out.println("Введіть номер опції: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    createCard();
                    break;
                case 2:
                    readCard();
                    break;
                case 3:
                    getAllTurnstileStats();
                    break;
                case 4:
                    getAllCardsTypesStats();
                    break;
                case 5:
                    System.out.println("До зустрічі!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Опції з номером " + option + " немає!");
            }
        }
    }

    /**
     * Creates a card
     */
    private static void createCard() {
        System.out.println("Оберіть тип картки (Pupil (p), Student (s), Regular (r)): ");
        String cardType = getCardType();

        String cardId = getCardId();

        if (cardType.equalsIgnoreCase("r")) {
            createRegularCard(cardId);
        } else if (cardType.equalsIgnoreCase("p") || cardType.equalsIgnoreCase("s")) {
            createTripOrExpireCard(cardId, cardType);
        }
    }

    /**
     * Gets card type from scanner
     * @return p, s, r if those letters were passed
     */
    private static String getCardType() {
        while (true) {
            String cardType = scanner.nextLine().trim();

            if (isValidCardType(cardType)) {
                switch (cardType) {
                    case "p":
                        return "Pupil";
                    case "s":
                        return "Student";
                    case "r":
                        return "Regular";
                }
            } else {
                System.out.println("Введіть p, s або r. (Pupil (p), Student (s), Regular (r).");
            }
        }
    }

    /**
     * Checks if string is s, p or r
     * @param cardType a string passed
     * @return true if s, p or r
     */
    private static boolean isValidCardType(String cardType) {
        return cardType.equalsIgnoreCase("r")
                || cardType.equalsIgnoreCase("s")
                || cardType.equalsIgnoreCase("p");
    }

    /**
     * Creates a regular card
     * @param cardId card id
     */
    private static void createRegularCard(String cardId) {
        System.out.println("Накопичувана (y) / не накопичувана (n)? ");
        String savingsCardChoice = scanner.nextLine().trim();

        if (savingsCardChoice.equalsIgnoreCase("y")) {
            Double cardBalance = getExpireCardBalance();
            Card card = new SavingsCard(cardId, cardBalance);
            turnstile.createCard(card);
            System.out.println("Накопичувальну карту створено! " + card);
        } else if (savingsCardChoice.equalsIgnoreCase("n")) {
            createTripOrExpireCard(cardId, "Regular");
        }
    }

    /**
     * Gets a card id from scanner
     * @return id, if it didn't exist, nothing if existed
     */
    private static String getCardId() {
        while (true) {
            System.out.print("Введіть унікальний ID картки: ");
            String id = scanner.nextLine().trim();

            if (!turnstile.cardIdExists(id)) {
                return id;
            } else {
                System.out.println("Цей ID вже використовується. Спробуйте інший.");
            }
        }
    }

    /**
     * Gets balance from scanner
     * @return balance number, if it's valid
     */
    private static Double getExpireCardBalance() {
        while (true) {
            System.out.println("Поповніть баланс карти: ");
            double cardBalance = scanner.nextDouble();

            if (cardBalance <= 0) {
                System.out.println("Баланс не може бути від'ємним, або нульовим.");
            } else {
                return cardBalance;
            }
        }
    }

    /**
     * Creates TripCard/ExpireCard
     * @param cardId card id
     * @param cardType card type
     */
    private static void createTripOrExpireCard(String cardId, String cardType) {
        System.out.println("Бажаєте створити карту за к-стю поїздок (1), чи за терміном дії (2)? ");
        int tripOrExpireChoice = scanner.nextInt();

        if (tripOrExpireChoice == 1) {
            int cardTrips = getCardTrips();
            LocalDate maxDate = LocalDate.MAX; // Date is not relevant, trips are
            Card card = new TripCard(cardId, cardType, maxDate, cardTrips);

            turnstile.createCard(card);

            System.out.println("Карту створено! " + card);
        } else if (tripOrExpireChoice == 2) {
            int daysToAdd = getDaysToAdd();
            LocalDate cardExpireDate = LocalDate.now().plusDays(daysToAdd); // Trips are not relevant, expire date is
            Card card = new ExpireCard(cardId, cardType, cardExpireDate, Integer.MAX_VALUE);

            turnstile.createCard(card);

            System.out.println("Карту створено! " + card);
        } else {
            System.out.println("Виберіть або 1, або 2.");
        }
    }

    /**
     * Gets trips number from scanner
     * @return trips number if valid
     */
    private static int getCardTrips() {
        while (true) {
            System.out.println("Можна вибрати або 5, або 10 поїздок. Введіть число: ");
            int tripsAmount = scanner.nextInt();

            if (tripsAmount < 5 || tripsAmount > 10) {
                System.out.println("Можна обрати лише 5 або 10");
            } else {
                return tripsAmount;
            }
        }
    }

    /**
     * Gets number of days to add from scanner
     * @return number of days to add if valid
     */
    private static int getDaysToAdd() {
        while (true) {
            System.out.println("Можна вибрати або місяць (30), або 10 днів (10): ");
            int daysToAdd = scanner.nextInt();

            if (daysToAdd < 10 || daysToAdd > 30) {
                System.out.println("Можна вибрати або 30 або 10. ");
            } else {
                return daysToAdd;
            }
        }
    }

    /**
     * Reads card
     */
    private static void readCard() {
        System.out.print("Введіть ID картки: ");
        String cardId = scanner.nextLine();
        turnstile.validateCard(cardId);
    }

    private static void getAllTurnstileStats() {
        turnstile.getTotalData();
    }

    private static void getAllCardsTypesStats() {
        turnstile.getStatisticsByType();
    }
}