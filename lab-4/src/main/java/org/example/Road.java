package org.example;

import java.util.List;

public class Road {
    private final List<Vehicle<?>> carsInRoad;

    public Road(List<Vehicle<?>> carsInRoad) {
        this.carsInRoad = carsInRoad;
    }

    public int getAllTakenPlaces() {
        return carsInRoad.stream().mapToInt(Vehicle::getTakenPlaces).sum();
    }

    public void addCarToRoad(Vehicle<?> vehicle){
        carsInRoad.add(vehicle);
    }
}