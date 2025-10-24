package org.example;

public class Bus<T extends Person> extends Vehicle<T> {
    public Bus(int maxCapacity) {
        super(maxCapacity);
    }
}
