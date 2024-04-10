package edu.ucalgary.oop.tests;

import org.junit.*;

import edu.ucalgary.oop.Person;

import static org.junit.Assert.*;

public class PersonTest {
    // Test setFirstName and getFirstName
    @Test
    public void testSetGetFirstName() {
        Person person = new Person("John", "Doe", "Info");
        person.setFirstName("Jane");
        assertEquals("Jane", person.getFirstName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetFirstNameTooShort() {
        new Person("J", "Doe", "Info");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetFirstNameTooLong() {
        new Person("ThisFirstNameIsDefinitelyWayTooLong", "Doe", "Info");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetFirstNameInvalidFormat() {
        new Person("invalid_first_name", "Doe", "Info");
    }

    // Test setLastName and getLastName
    @Test
    public void testSetGetLastName() {
        Person person = new Person("John", "Doe", "Info");
        person.setLastName("Smith");
        assertEquals("Smith", person.getLastName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetLastNameTooShort() {
        new Person("John", "D", "Info");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetLastNameTooLong() {
        new Person("John", "ThisLastNameIsDefinitelyWayTooLong", "Info");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetLastNameInvalidFormat() {
        new Person("John", "invalid_last_name", "Info");
    }

    // Test setInfo and getInfo
    @Test
    public void testSetGetInfo() {
        Person person = new Person("John", "Doe", "Info");
        person.setInfo("New info");
        assertEquals("New info", person.getInfo());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInfoTooShort() {
        new Person("John", "Doe", "In");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInfoTooLong() {
        String longInfo = new String(new char[401]).replace("\0", "a");
        new Person("John", "Doe", longInfo);
    }

    // Test constructor
    @Test
    public void testConstructorValid() {
        Person person = new Person("John", "Doe", "Info");
        assertEquals("John", person.getFirstName());
        assertEquals("Doe", person.getLastName());
        assertEquals("Info", person.getInfo());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullFirstName() {
        new Person(null, "Doe", "Info");
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullLastName() {
        new Person("John", null, "Info");
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullInfo() {
        new Person("John", "Doe", null);
    }
}
