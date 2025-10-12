import java.time.LocalDate;

/**
 * Class for all Cards based on expire. In the task it's 10 or 30 days.
 */
public class ExpireCard extends Card {
    public ExpireCard(String id, String type, LocalDate expiryDate, int remainingTrips) {
        super(id, type, expiryDate, remainingTrips);
    }

    @Override
    public boolean useCard() {
        return isValid();
    }

    @Override
    public String toString() {
        return "ExpireCard [id=" + id + ", type=" + type + ", expiryDate=" + expiryDate + "]";
    }
}
