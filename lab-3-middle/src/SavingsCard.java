import java.time.LocalDate;

/**
 * Class for all Savings Cards
 * In fact we need only its id and balance, as expiry date and remaining trips are infinite.
 */
public class SavingsCard extends Card {
    private static final double TRIP_COST = 15.0;
    private double cardBalance;

    public SavingsCard(String id, double cardBalance) {
        super(id, "Regular", LocalDate.MAX, Integer.MAX_VALUE);
        this.cardBalance = cardBalance;
    }

    public void addCardBalance(double amount) {
        this.cardBalance += amount;
    }

    public double getCardBalance() {
        return cardBalance;
    }

    @Override
    public boolean isValid() {
        return cardBalance >= TRIP_COST;
    }

    @Override
    public boolean useCard() {
        if (isValid()) {
            cardBalance -= TRIP_COST;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "SavingsCard [id=" + id + ", balance=" + cardBalance + "]";
    }
}
