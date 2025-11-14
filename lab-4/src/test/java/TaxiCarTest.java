import org.example.Firefighter;
import org.example.Person;
import org.example.Policeman;
import org.example.TaxiCar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TaxiCarTest {
    private TaxiCar<Person> taxiCar;

    @BeforeEach
    public void setUp() {
        taxiCar = new TaxiCar<>(8);
    }

    @Test
    public void testMaxCapacitySetup() {
        assertEquals(8, taxiCar.getMaxCapacity());
    }

    @Test
    public void testUsualPassengersBoarding() throws Exception {
        taxiCar.boardPassenger(new Person("TaxiCar Passenger 1"));
        taxiCar.boardPassenger(new Person("TaxiCar Passenger 2"));
        taxiCar.boardPassenger(new Person("TaxiCar Passenger 3"));
        assertEquals(3, taxiCar.getTakenPlaces());
    }

    @Test
    public void testPolicePassengersBoarding() throws Exception {
        taxiCar.boardPassenger(new Policeman("TaxiCar Passenger 4"));
        taxiCar.boardPassenger(new Policeman("TaxiCar Passenger 5"));
        taxiCar.boardPassenger(new Policeman("TaxiCar Passenger 6"));
        assertEquals(3, taxiCar.getTakenPlaces());
    }

    @Test
    public void testFirePassengersBoarding() throws Exception {
        taxiCar.boardPassenger(new Firefighter("TaxiCar Passenger 7"));
        taxiCar.boardPassenger(new Firefighter("TaxiCar Passenger 8"));
        taxiCar.boardPassenger(new Firefighter("TaxiCar Passenger 9"));
        assertEquals(3, taxiCar.getTakenPlaces());
    }

    @Test
    public void testUsualPassengersDeboarding() throws Exception {
        Person person1 = new Person("TaxiCar Passenger 1");
        Person person2 = new Person("TaxiCar Passenger 2");

        taxiCar.boardPassenger(person1);
        taxiCar.boardPassenger(person2);
        taxiCar.deboardPassenger(person1);
        assertEquals(1, taxiCar.getTakenPlaces());
    }

    @Test
    public void testPolicePassengersDeboarding() throws Exception {
        Policeman person1 = new Policeman("TaxiCar Passenger 1");
        Policeman person2 = new Policeman("TaxiCar Passenger 2");

        taxiCar.boardPassenger(person1);
        taxiCar.boardPassenger(person2);
        taxiCar.deboardPassenger(person1);
        assertEquals(1, taxiCar.getTakenPlaces());
    }

    @Test
    public void testFirePassengersDeboarding() throws Exception {
        Firefighter person1 = new Firefighter("TaxiCar Passenger 1");
        Firefighter person2 = new Firefighter("TaxiCar Passenger 2");

        taxiCar.boardPassenger(person1);
        taxiCar.boardPassenger(person2);
        taxiCar.deboardPassenger(person1);
        taxiCar.deboardPassenger(person2);
        assertEquals(0, taxiCar.getTakenPlaces());
    }

    @Test
    public void testOverBoardingException() throws Exception {
        for (int i = 0; i < 8 ; i++) {
            taxiCar.boardPassenger(new Person("TaxiCar Passenger " + i));
        }

        Exception thrown = assertThrows(Exception.class,
                () -> taxiCar.boardPassenger(new Person("TaxiCar Passenger 0")));

        String expectedMessage = "All " + taxiCar.getMaxCapacity() + " seats are taken! Wait for another vehicle!";

        assertEquals(expectedMessage, thrown.getMessage());
    }

    @Test
    public void testDeboardingNonExistentPassengerException() throws Exception {
        Policeman person1 = new Policeman("TaxiCar Passenger 1");
        Firefighter person2 = new Firefighter("TaxiCar Passenger 2");
        Person person3 = new Person("TaxiCar Passenger 3");

        taxiCar.boardPassenger(person1);
        taxiCar.boardPassenger(person2);

        Exception thrown = assertThrows(Exception.class,
                () -> taxiCar.deboardPassenger(person3));

        String expectedMessage = "Passenger is not on the vehicle!";

        assertEquals(expectedMessage, thrown.getMessage());
    }

}