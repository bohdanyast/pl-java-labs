import java.time.LocalDate;

/**
 * Class for all Trip Cards.
 * We need check only remaining trips. In the task it's 5 or 10.
 */
public class TripCard extends Card {
    public TripCard(String id, String type, LocalDate expiryDate, int remainingTrips) {
        super(id, type, expiryDate, remainingTrips);
    }

    @Override
    public boolean useCard() {
        if (isValid()) {
            remainingTrips--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "TripCard [id=" + id + ", type=" + type + ", remainingTrips=" + remainingTrips + "]";

    }
}
