package edu.ucalgary.oop.tests;

import org.junit.*;

import edu.ucalgary.oop.Supply;

import static org.junit.Assert.*;

public class SupplyTest {
    // Test setType and getType
    @Test
    public void testSetGetType() {
        Supply supply = new Supply("Type", 100);
        supply.setType("New Type");
        assertEquals("New Type", supply.getType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetTypeTooShort() {
        new Supply("Ty", 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetTypeTooLong() {
        new Supply("ThisTypeIsDefinitelyWayTooLong", 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetTypeInvalidFormat() {
        new Supply("invalid_type", 100);
    }

    // Test setQuantity and getQuantity
    @Test
    public void testSetGetQuantity() {
        Supply supply = new Supply("Type", 100);
        supply.setQuantity(50);
        assertEquals(Integer.valueOf(50), supply.getQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetQuantityTooLow() {
        new Supply("Type", -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetQuantityTooHigh() {
        new Supply("Type", 201);
    }

    // Test constructor
    @Test
    public void testConstructorValid() {
        Supply supply = new Supply("Type", 100);
        assertEquals("Type", supply.getType());
        assertEquals(Integer.valueOf(100), supply.getQuantity());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullType() {
        new Supply(null, 100);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullQuantity() {
        new Supply("Type", null);
    }
}
