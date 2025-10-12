import java.util.HashMap;
import java.util.Map;

/**
 * Class for turnstile.
 * It creates card, validates it, shows statistics by type and overall.
 */
public class Turnstile {
    private Map<String, Card> cards = new HashMap<>();
    private int successesCounter;
    private int failsCounter;

    /**
     * Creates a Card in Turnstile
     * @param card Card
     */
    public void createCard(Card card) {
        if (!cardIdExists(card.id)) {
            cards.put(card.getId(), card);
        }
    }

    /**
     * Checks Card existence
     * @param id Card id
     * @return true if exists, false - if no
     */
    public boolean cardIdExists(String id) {
        return cards.containsKey(id);
    }

    /**
     * Uses a Card
     * @param cardId Card id
     * @return true if used, false if denied
     */
    public boolean validateCard(String cardId) {
        Card card = cards.get(cardId);
        if (card != null && card.useCard()) {
            successesCounter++;
            System.out.println("Проходьте! " + card);
            return true;
        }
        failsCounter++;
        System.out.println("Відхилено! " + card);
        return false;
    }

    public void getTotalData() {
        System.out.println("Проходжень: " + successesCounter);
        System.out.println("Відхилень: " + failsCounter);
    }

    public void getStatisticsByType() {
        Map<String, Integer> typeStats = new HashMap<>();
        for (Card card : cards.values()) {
            typeStats.put(card.getType(), typeStats.getOrDefault(card.getType(), 0) + 1);
        }
        System.out.println("Статистика за типами карток:");
        for (Map.Entry<String, Integer> entry : typeStats.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public int getCardCount() {
        return cards.size();
    }

    public int getSuccessesCounter() {
        return successesCounter;
    }

    public int getFailsCounter() {
        return failsCounter;
    }
}
