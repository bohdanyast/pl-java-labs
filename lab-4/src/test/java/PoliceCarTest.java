import org.example.PoliceCar;
import org.example.Policeman;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PoliceCarTest {
    private PoliceCar policeCar;

    @BeforeEach
    public void setUp() {
        policeCar = new PoliceCar(8);
    }

    @Test
    public void testMaxCapacitySetup() {
        assertEquals(8, policeCar.getMaxCapacity());
    }

    @Test
    public void testPolicePassengersBoarding() throws Exception {
        policeCar.boardPassenger(new Policeman("PoliceCar Passenger 4"));
        policeCar.boardPassenger(new Policeman("PoliceCar Passenger 5"));
        policeCar.boardPassenger(new Policeman("PoliceCar Passenger 6"));
        assertEquals(3, policeCar.getTakenPlaces());
    }

    @Test
    public void testPolicePassengersDeboarding() throws Exception {
        Policeman person1 = new Policeman("PoliceCar Passenger 1");
        Policeman person2 = new Policeman("PoliceCar Passenger 2");

        policeCar.boardPassenger(person1);
        policeCar.boardPassenger(person2);
        policeCar.deboardPassenger(person1);
        assertEquals(1, policeCar.getTakenPlaces());
    }

    @Test
    public void testOverBoardingException() throws Exception {
        for (int i = 0; i < 8 ; i++) {
            policeCar.boardPassenger(new Policeman("PoliceCar Passenger " + i));
        }

        Exception thrown = assertThrows(Exception.class,
                () -> policeCar.boardPassenger(new Policeman("PoliceCar Passenger 0")));

        String expectedMessage = "All " + policeCar.getMaxCapacity() + " seats are taken! Wait for another vehicle!";

        assertEquals(expectedMessage, thrown.getMessage());
    }

    @Test
    public void testDeboardingNonExistentPassengerException() throws Exception {
        Policeman person1 = new Policeman("PoliceCar Passenger 1");
        Policeman person2 = new Policeman("PoliceCar Passenger 2");
        Policeman person3 = new Policeman("PoliceCar Passenger 3");

        policeCar.boardPassenger(person1);
        policeCar.boardPassenger(person2);

        Exception thrown = assertThrows(Exception.class,
                () -> policeCar.deboardPassenger(person3));

        String expectedMessage = "Passenger is not on the vehicle!";

        assertEquals(expectedMessage, thrown.getMessage());
    }

}