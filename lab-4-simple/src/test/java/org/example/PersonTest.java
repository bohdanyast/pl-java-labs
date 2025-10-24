package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    @Test
    public void getPersonName() {
        Person person = new Person("Passenger 1");
        assertEquals("Passenger 1", person.getName());
    }

    @Test
    public void getPolicemanName() {
        Policeman person = new Policeman("Policeman 1");
        assertEquals("Policeman 1", person.getName());
    }

    @Test
    public void getFirefighterName() {
        Firefighter person = new Firefighter("Firefighter 1");
        assertEquals("Firefighter 1", person.getName());
    }

}