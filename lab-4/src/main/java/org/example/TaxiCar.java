package org.example;

public class TaxiCar<T extends Person> extends Car<T> {
    public TaxiCar(int maxCapacity) {
        super(maxCapacity);
    }
}
