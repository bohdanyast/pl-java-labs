package org.example;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoadTest {
    private Road road;
    private Bus<Person> bus;
    private TaxiCar<Person> taxi;
    private FireCar fireTruck;
    private PoliceCar policeCar;

    @BeforeEach
    public void setUp() {
        bus = new Bus<>(35);
        taxi = new TaxiCar<>(6);
        fireTruck = new FireCar(3);
        policeCar = new PoliceCar(3);

        List<Vehicle<?>> vehicles = new ArrayList<>();
        vehicles.add(bus);
        vehicles.add(taxi);
        vehicles.add(fireTruck);
        vehicles.add(policeCar);
        road = new Road(vehicles);
    }

    @Test
    public void testAvailableSeatsAtStart() {
        assertEquals(50, 50-road.getAllTakenPlaces());
    }

    @Test
    public void testTakenSeatsAtStart() {
        assertEquals(0, road.getAllTakenPlaces());
    }

    @Test
    public void addPassengersAndTestStatistics() throws Exception{
        bus.boardPassenger(new Person("Bus Passenger 1"));
        taxi.boardPassenger(new Person("Taxi Passenger 1"));
        taxi.boardPassenger(new Person("Taxi Passenger 2"));
        taxi.boardPassenger(new Person("Taxi Passenger 3"));
        taxi.boardPassenger(new Person("Taxi Passenger 4"));
        taxi.boardPassenger(new Person("Taxi Passenger 5"));
        fireTruck.boardPassenger(new Firefighter("Firefighter 1"));
        policeCar.boardPassenger(new Policeman("Policeman 1"));

        assertEquals(8, road.getAllTakenPlaces());

        policeCar.boardPassenger(new Policeman("Police Officer 2"));
        assertEquals(9, road.getAllTakenPlaces());
    }

    @Test
    public void testAddingCar() throws Exception {
        assertEquals(0, road.getAllTakenPlaces());

        Bus<Person> bus1 = new Bus<>(35);
        assertEquals(0, road.getAllTakenPlaces());
        road.addCarToRoad(bus1);

        bus1.boardPassenger(new Person("Bus Passenger 1"));
        bus1.boardPassenger(new Person("Bus Passenger 2"));
        bus1.boardPassenger(new Person("Bus Passenger 3"));
        bus1.boardPassenger(new Person("Bus Passenger 4"));
        bus1.boardPassenger(new Person("Bus Passenger 5"));
        assertEquals(5,  road.getAllTakenPlaces());
    }
}