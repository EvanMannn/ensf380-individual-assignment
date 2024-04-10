/*
Copyright Ann Barcomb and Khawla Shnaikat, 2024
Licensed under GPL v3
See LICENSE.txt for more information.
*/
package edu.ucalgary.oop.tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class InquirerTest {

    /*
     * Define the values which will be used for tests
     */
    /*
     * private Inquirer inquirer;
     * private Inquiry expectedInquiry;
     * 
     * private String expectedPhoneNumber;
     * private String expectedInfo;
     * 
     * @Before
     * public void setUp() {
     * inquirer = new Inquirer("Joseph", "Bouillon", "(587) 222-2222",
     * "looking for my family members");
     * expectedPhoneNumber = "(587) 222-2222";
     * expectedInfo = "Looking for info on a family member";
     * expectedInquiry = new Inquiry("2024-01-01");
     * expectedInquiry.setInfo(expectedInfo);
     * }
     * 
     * @Test
     * public void testConstructor() {
     * assertNotNull("Constructor should create a non-null Location object",
     * inquirer);
     * assertEquals("Constructor should set the first name correctly", "Joseph",
     * inquirer.getFirstName());
     * assertEquals("Constructor should set the last name correctly", "Bouillon",
     * inquirer.getLastName());
     * assertEquals("Constructor should set the phone number correctly",
     * "(587) 222-2222",
     * inquirer.getServicesPhoneNum());
     * assertEquals("Constructor should set the info correctly correctly",
     * "looking for my family members",
     * inquirer.getInfo());
     * }
     * 
     * @Test(expected = IllegalArgumentException.class)
     * public void testConstructorWithNullPhoneNumber() {
     * Inquirer testInquirer = new Inquirer("test first name", "test last name",
     * null, "test info");
     * }
     * 
     * @Test(expected = IllegalArgumentException.class)
     * public void testConstructorWithInvalidPhoneNumber() {
     * Inquirer testInquirer = new Inquirer("test first name", "test last name",
     * "4033 888 8888", "test info");
     * }
     * 
     * @Test
     * public void testGetServicesPhoneNum() {
     * 
     * assertEquals("getServicesPhoneNum() should return the correct Services Number"
     * , expectedPhoneNumber,
     * inquirer.getServicesPhoneNum());
     * }
     * 
     * @Test
     * public void testAddInquiry() {
     * inquirer.addInquiry(expectedInquiry);
     * assertTrue("addInquiry should add a inquiry to the inquiryLog list",
     * inquirer.getInquiryLog().contains(expectedInquiry));
     * }
     * 
     * @Test
     * public void testRemoveInquiry() {
     * inquirer.addInquiry(expectedInquiry);
     * inquirer.removeInquiry(expectedInquiry);
     * assertFalse("removeInquiry should remove the inquiry from the inquiryLog list"
     * ,
     * inquirer.getInquiryLog().contains(expectedInquiry));
     * }
     * 
     * @Test(expected = IllegalArgumentException.class)
     * public void testRemoveInquiryThatIsNotPresent() {
     * Inquiry testInquiry = new Inquiry("2023-01-01");
     * inquirer.addInquiry(expectedInquiry);
     * inquirer.removeInquiry(testInquiry);
     * }
     * 
     * @Test
     * public void testSetAndGetOccupants() {
     * ArrayList<Inquiry> newLog = new ArrayList<>();
     * newLog.add(expectedInquiry);
     * inquirer.setInquiryLog(newLog);
     * assertTrue("setOccupants should replace the occupants list with the new list"
     * ,
     * inquirer.getInquiryLog().containsAll(newLog));
     * }
     */
}
