package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CarTest {
    private Car<Person> car;

    @BeforeEach
    public void setUp() {
        car = new Car<>(8);
    }

    @Test
    public void testMaxCapacitySetup() {
        assertEquals(8, car.getMaxCapacity());
    }

    @Test
    public void testUsualPassengersBoarding() throws Exception {
        car.boardPassenger(new Person("Car Passenger 1"));
        car.boardPassenger(new Person("Car Passenger 2"));
        car.boardPassenger(new Person("Car Passenger 3"));
        assertEquals(3, car.getTakenPlaces());
    }

    @Test
    public void testPolicePassengersBoarding() throws Exception {
        car.boardPassenger(new Policeman("Car Passenger 4"));
        car.boardPassenger(new Policeman("Car Passenger 5"));
        car.boardPassenger(new Policeman("Car Passenger 6"));
        assertEquals(3, car.getTakenPlaces());
    }

    @Test
    public void testFirePassengersBoarding() throws Exception {
        car.boardPassenger(new Firefighter("Car Passenger 7"));
        car.boardPassenger(new Firefighter("Car Passenger 8"));
        car.boardPassenger(new Firefighter("Car Passenger 9"));
        assertEquals(3, car.getTakenPlaces());
    }

    @Test
    public void testUsualPassengersDeboarding() throws Exception {
        Person person1 = new Person("Car Passenger 1");
        Person person2 = new Person("Car Passenger 2");

        car.boardPassenger(person1);
        car.boardPassenger(person2);
        car.deboardPassenger(person1);
        assertEquals(1, car.getTakenPlaces());
    }

    @Test
    public void testPolicePassengersDeboarding() throws Exception {
        Policeman person1 = new Policeman("Car Passenger 1");
        Policeman person2 = new Policeman("Car Passenger 2");

        car.boardPassenger(person1);
        car.boardPassenger(person2);
        car.deboardPassenger(person1);
        assertEquals(1, car.getTakenPlaces());
    }

    @Test
    public void testFirePassengersDeboarding() throws Exception {
        Firefighter person1 = new Firefighter("Car Passenger 1");
        Firefighter person2 = new Firefighter("Car Passenger 2");

        car.boardPassenger(person1);
        car.boardPassenger(person2);
        car.deboardPassenger(person1);
        car.deboardPassenger(person2);
        assertEquals(0, car.getTakenPlaces());
    }

    @Test
    public void testOverBoardingException() throws Exception {
        for (int i = 0; i < 8 ; i++) {
            car.boardPassenger(new Person("Car Passenger " + i));
        }

        Exception thrown = assertThrows(Exception.class,
                () -> car.boardPassenger(new Person("Car Passenger 0")));

        String expectedMessage = "All " + car.getMaxCapacity() + " seats are taken! Wait for another vehicle!";

        assertEquals(expectedMessage, thrown.getMessage());
    }

    @Test
    public void testDeboardingNonExistentPassengerException() throws Exception {
        Policeman person1 = new Policeman("Car Passenger 1");
        Firefighter person2 = new Firefighter("Car Passenger 2");
        Person person3 = new Person("Car Passenger 3");

        car.boardPassenger(person1);
        car.boardPassenger(person2);

        Exception thrown = assertThrows(Exception.class,
                () -> car.deboardPassenger(person3));

        String expectedMessage = "Passenger is not on the vehicle!";

        assertEquals(expectedMessage, thrown.getMessage());
    }

}