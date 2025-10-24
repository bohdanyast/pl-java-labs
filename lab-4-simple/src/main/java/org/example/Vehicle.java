package org.example;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle<T extends Person> {
    private final int maxCapacity;
    private final List<T> passengers;

    public Vehicle(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        passengers = new ArrayList<>();
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getTakenPlaces() {
        return passengers.size();
    }

    public void boardPassenger(T person) throws Exception {
        if (passengers.size() < maxCapacity) {
            passengers.add(person);
        } else {
            throw new Exception("All " + maxCapacity + " seats are taken! Wait for another vehicle!");
        }
    }

    public void deboardPassenger(T person) throws Exception {
        if (passengers.contains(person)) {
            passengers.remove(person);
        } else {
            throw new Exception("Passenger is not on the vehicle!");
        }
    }


}
