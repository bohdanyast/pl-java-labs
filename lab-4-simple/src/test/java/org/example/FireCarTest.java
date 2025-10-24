package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FireCarTest {
    private FireCar fireCar;

    @BeforeEach
    public void setUp() {
        fireCar = new FireCar(6);
    }

    @Test
    public void testMaxCapacitySetup() {
        assertEquals(6, fireCar.getMaxCapacity());
    }

    @Test
    public void testFirePassengersBoarding() throws Exception {
        fireCar.boardPassenger(new Firefighter("FireCar Passenger 7"));
        fireCar.boardPassenger(new Firefighter("FireCar Passenger 8"));
        fireCar.boardPassenger(new Firefighter("FireCar Passenger 9"));
        assertEquals(3, fireCar.getTakenPlaces());
    }

    @Test
    public void testFirePassengersDeboarding() throws Exception {
        Firefighter person1 = new Firefighter("FireCar Passenger 1");
        Firefighter person2 = new Firefighter("FireCar Passenger 2");

        fireCar.boardPassenger(person1);
        fireCar.boardPassenger(person2);
        fireCar.deboardPassenger(person1);
        fireCar.deboardPassenger(person2);
        assertEquals(0, fireCar.getTakenPlaces());
    }

    @Test
    public void testOverBoardingException() throws Exception {
        for (int i = 0; i < 6; i++) {
            fireCar.boardPassenger(new Firefighter("FireCar Passenger " + i));
        }

        Exception thrown = assertThrows(Exception.class,
                () -> fireCar.boardPassenger(new Firefighter("FireCar Passenger 0")));

        String expectedMessage = "All " + fireCar.getMaxCapacity() + " seats are taken! Wait for another vehicle!";

        assertEquals(expectedMessage, thrown.getMessage());
    }

    @Test
    public void testDeboardingNonExistentPassengerException() throws Exception {
        Firefighter person1 = new Firefighter("FireCar Passenger 1");
        Firefighter person2 = new Firefighter("FireCar Passenger 2");
        Firefighter person3 = new Firefighter("FireCar Passenger 3");

        fireCar.boardPassenger(person1);
        fireCar.boardPassenger(person2);

        Exception thrown = assertThrows(Exception.class,
                () -> fireCar.deboardPassenger(person3));

        String expectedMessage = "Passenger is not on the vehicle!";

        assertEquals(expectedMessage, thrown.getMessage());
    }

}