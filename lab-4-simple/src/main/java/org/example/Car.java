package org.example;

public class Car<T extends Person> extends Vehicle<T> {
    public Car(int maxCapacity) {
        super(maxCapacity);
    }
}
