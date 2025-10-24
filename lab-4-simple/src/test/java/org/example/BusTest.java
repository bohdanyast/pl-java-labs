package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusTest {
    private Bus<Person> bus;

    @BeforeEach
    public void setUp() {
        bus = new Bus<>(35);
    }

    @Test
    public void testMaxCapacitySetup() {
        assertEquals(35, bus.getMaxCapacity());
    }

    @Test
    public void testUsualPassengersBoarding() throws Exception {
        bus.boardPassenger(new Person("Bus Passenger 1"));
        bus.boardPassenger(new Person("Bus Passenger 2"));
        bus.boardPassenger(new Person("Bus Passenger 3"));
        assertEquals(3, bus.getTakenPlaces());
    }

    @Test
    public void testPolicePassengersBoarding() throws Exception {
        bus.boardPassenger(new Policeman("Bus Passenger 4"));
        bus.boardPassenger(new Policeman("Bus Passenger 5"));
        bus.boardPassenger(new Policeman("Bus Passenger 6"));
        assertEquals(3, bus.getTakenPlaces());
    }

    @Test
    public void testFirePassengersBoarding() throws Exception {
        bus.boardPassenger(new Firefighter("Bus Passenger 7"));
        bus.boardPassenger(new Firefighter("Bus Passenger 8"));
        bus.boardPassenger(new Firefighter("Bus Passenger 9"));
        assertEquals(3, bus.getTakenPlaces());
    }

    @Test
    public void testUsualPassengersDeboarding() throws Exception {
        Person person1 = new Person("Bus Passenger 1");
        Person person2 = new Person("Bus Passenger 2");

        bus.boardPassenger(person1);
        bus.boardPassenger(person2);
        bus.deboardPassenger(person1);
        assertEquals(1, bus.getTakenPlaces());
    }

    @Test
    public void testPolicePassengersDeboarding() throws Exception {
        Policeman person1 = new Policeman("Bus Passenger 1");
        Policeman person2 = new Policeman("Bus Passenger 2");

        bus.boardPassenger(person1);
        bus.boardPassenger(person2);
        bus.deboardPassenger(person1);
        assertEquals(1, bus.getTakenPlaces());
    }

    @Test
    public void testFirePassengersDeboarding() throws Exception {
        Firefighter person1 = new Firefighter("Bus Passenger 1");
        Firefighter person2 = new Firefighter("Bus Passenger 2");

        bus.boardPassenger(person1);
        bus.boardPassenger(person2);
        bus.deboardPassenger(person1);
        bus.deboardPassenger(person2);
        assertEquals(0, bus.getTakenPlaces());
    }

    @Test
    public void testOverBoardingException() throws Exception {
        for (int i = 0; i < 35 ; i++) {
            bus.boardPassenger(new Person("Bus Passenger " + i));
        }

        Exception thrown = assertThrows(Exception.class,
                () -> bus.boardPassenger(new Person("Bus Passenger 0")));

        String expectedMessage = "All " + bus.getMaxCapacity() + " seats are taken! Wait for another vehicle!";

        assertEquals(expectedMessage, thrown.getMessage());
    }

    @Test
    public void testDeboardingNonExistentPassengerException() throws Exception {
        Policeman person1 = new Policeman("Bus Passenger 1");
        Firefighter person2 = new Firefighter("Bus Passenger 2");
        Person person3 = new Person("Bus Passenger 3");

        bus.boardPassenger(person1);
        bus.boardPassenger(person2);

        Exception thrown = assertThrows(Exception.class,
                () -> bus.deboardPassenger(person3));

        String expectedMessage = "Passenger is not on the vehicle!";

        assertEquals(expectedMessage, thrown.getMessage());
    }

}