import java.time.LocalDate;

/**
 * Class for Cards. It holds its id, type, expiry date and remaining trips.
 * Also has useCard() for overloading for needed type of card.
 */
public abstract class Card {
    protected String id;
    protected String type; // "Student", "Pupil", "Regular"
    protected LocalDate expiryDate;
    protected int remainingTrips;

    public Card(String id, String type, LocalDate expiryDate, int remainingTrips) {
        this.id = id;
        this.type = type;
        this.expiryDate = expiryDate;
        this.remainingTrips = remainingTrips;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public boolean isValid() {
        return !expiryDate.isBefore(LocalDate.now()) && remainingTrips > 0;
    }

    public abstract boolean useCard();

}
