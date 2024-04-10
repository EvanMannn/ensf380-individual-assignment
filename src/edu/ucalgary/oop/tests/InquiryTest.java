package edu.ucalgary.oop.tests;

import org.junit.*;

import edu.ucalgary.oop.Inquiry;

import static org.junit.Assert.*;

public class InquiryTest {
    // Test setDateOfInquiry and getDateOfInquiry
    @Test
    public void testSetGetDateOfInquiry() {
        Inquiry inquiry = new Inquiry("2022-01-01", "Info", 1);
        inquiry.setDateOfInquiry("2022-01-02");
        assertEquals("2022-01-02", inquiry.getDateOfInquiry());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfInquiryInvalidFormat() {
        new Inquiry("01-01-2022", "Info", 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfInquiryFutureDate() {
        new Inquiry("3000-01-01", "Info", 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfInquiryInvalidLeapYear() {
        new Inquiry("2021-02-29", "Info", 1);
    }

    // Test setInfo and getInfo
    @Test
    public void testSetGetInfo() {
        Inquiry inquiry = new Inquiry("2022-01-01", "Info", 1);
        inquiry.setInfo("New info");
        assertEquals("New info", inquiry.getInfo());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInfoTooShort() {
        new Inquiry("2022-01-01", "In", 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInfoTooLong() {
        String longInfo = new String(new char[401]).replace("\0", "a");
        new Inquiry("2022-01-01", longInfo, 1);
    }

    // Test constructor
    @Test
    public void testConstructorValid() {
        Inquiry inquiry = new Inquiry("2022-01-01", "Info", 1);
        assertEquals("2022-01-01", inquiry.getDateOfInquiry());
        assertEquals("Info", inquiry.getInfo());
        assertEquals(Integer.valueOf(1), inquiry.getId());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullDateOfInquiry() {
        new Inquiry(null, "Info", 1);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullInfo() {
        new Inquiry("2022-01-01", null, 1);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullId() {
        new Inquiry("2022-01-01", "Info", null);
    }
}
