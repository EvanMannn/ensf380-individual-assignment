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

public class ReliefServiceTest {/*
                                 * private ReliefService reliefService;
                                 * private Inquirer inquirer1;
                                 * private Inquirer inquirer2;
                                 * private ArrayList<Inquirer> inquirers = new ArrayList<Inquirer>();
                                 * private DisasterVictim missingPerson;
                                 * private Location lastKnownLocation;
                                 * private String validDate = "2024-02-10";
                                 * private String invalidDate = "2024/02/10";
                                 * private String expectedInfoProvided = "Looking for family member";
                                 * private String expectedLogDetails = "Log Details: \nTestLog1\nTestLog2\n";
                                 * 
                                 * @Before
                                 * public void setUp() {
                                 * // Assuming Inquirer, DisasterVictim, and Location have constructors as
                                 * implied
                                 * inquirer1 = new Inquirer("John", "Jared", "(403) 111-1111",
                                 * "Looking for family member");
                                 * Inquiry inquiry1 = new Inquiry("2020-01-01");
                                 * inquiry1.setInfo("TestLog1");
                                 * ArrayList<Inquiry> inquiryLog1 = new ArrayList<Inquiry>();
                                 * inquiryLog1.add(inquiry1);
                                 * inquirer1.setInquiryLog(inquiryLog1);
                                 * 
                                 * inquirer2 = new Inquirer("Jody", "Jared", "(403) 111-1111",
                                 * "Looking for Husband");
                                 * Inquiry inquiry2 = new Inquiry("2020-01-01");
                                 * inquiry2.setInfo("TestLog2");
                                 * ArrayList<Inquiry> inquiryLog2 = new ArrayList<Inquiry>();
                                 * inquiryLog2.add(inquiry2);
                                 * inquirer2.setInquiryLog(inquiryLog2);
                                 * 
                                 * inquirers.add(inquirer1);
                                 * inquirers.add(inquirer2);
                                 * missingPerson = new DisasterVictim("Jane", "Jared",
                                 * "Got lost post earthquake", "2024-01-25");
                                 * lastKnownLocation = new Location("University of Calgary",
                                 * "2500 University Dr NW");
                                 * reliefService = new ReliefService(inquirers, missingPerson, validDate,
                                 * expectedInfoProvided, lastKnownLocation);
                                 * }
                                 * 
                                 * @Test
                                 * public void testObjectCreation() {
                                 * assertNotNull("ReliefService object should not be null", reliefService);
                                 * }
                                 * 
                                 * @Test(expected = IllegalArgumentException.class)
                                 * public void testConstructorWithNullInquirers() {
                                 * ReliefService reliefServiceTest = new ReliefService(null, missingPerson,
                                 * validDate, expectedInfoProvided,
                                 * lastKnownLocation);
                                 * }
                                 * 
                                 * @Test(expected = IllegalArgumentException.class)
                                 * public void testConstructorWithNullMissingPerson() {
                                 * ReliefService reliefServiceTest = new ReliefService(inquirers, null,
                                 * validDate, expectedInfoProvided,
                                 * lastKnownLocation);
                                 * }
                                 * 
                                 * @Test(expected = IllegalArgumentException.class)
                                 * public void testConstructorWithNullDateOfInitialInquiry() {
                                 * ReliefService reliefServiceTest = new ReliefService(inquirers, missingPerson,
                                 * null, expectedInfoProvided,
                                 * lastKnownLocation);
                                 * }
                                 * 
                                 * @Test(expected = IllegalArgumentException.class)
                                 * public void testConstructorWithNullInfoProvided() {
                                 * ReliefService reliefServiceTest = new ReliefService(inquirers, missingPerson,
                                 * validDate, null,
                                 * lastKnownLocation);
                                 * }
                                 * 
                                 * @Test(expected = IllegalArgumentException.class)
                                 * public void testConstructorWithNullLastKnownLocation() {
                                 * ReliefService reliefServiceTest = new ReliefService(inquirers, missingPerson,
                                 * validDate, expectedInfoProvided,
                                 * null);
                                 * }
                                 * 
                                 * @Test(expected = IllegalArgumentException.class)
                                 * public void testConstructorWithInvalidDate() {
                                 * ReliefService reliefServiceTest = new ReliefService(inquirers, missingPerson,
                                 * invalidDate, expectedInfoProvided,
                                 * lastKnownLocation);
                                 * }
                                 * 
                                 * @Test
                                 * public void testGetAndSetInquirers() {
                                 * ArrayList<Inquirer> newInquirers = new ArrayList<Inquirer>();
                                 * Inquirer testInquirer = new Inquirer("test", "test", "(403) 111-1111",
                                 * "Looking for family member");
                                 * 
                                 * reliefService.setInquirers(newInquirers);
                                 * assertEquals("Inquirer should match the one set in setup", newInquirers,
                                 * reliefService.getInquirers());
                                 * }
                                 * 
                                 * @Test
                                 * public void testGetAndSetMissingPerson() {
                                 * DisasterVictim newVictim = new DisasterVictim("test", "test", "test info",
                                 * "2024-01-01");
                                 * reliefService.setMissingPerson(newVictim);
                                 * assertEquals("Missing person should match the one set in setup", newVictim,
                                 * reliefService.getMissingPerson());
                                 * }
                                 * 
                                 * @Test
                                 * public void testSetDateOfInitialInquiryWithValidInput() {
                                 * reliefService.setDateOfInitialInquiry("2020-02-02");
                                 * assertEquals("Date of inquiry should match the one set in setup",
                                 * "2020-02-02",
                                 * reliefService.getDateOfInitialInquiry());
                                 * }
                                 * 
                                 * @Test(expected = IllegalArgumentException.class)
                                 * public void testSetDateOfInitialInquiryWithInvalidInput() {
                                 * reliefService.setDateOfInitialInquiry(invalidDate);
                                 * }
                                 * 
                                 * @Test
                                 * public void testGetAndSetInfoProvided() {
                                 * reliefService.setInfoProvided("This is new info for the test to check");
                                 * assertEquals("Info provided should match the one set in setup",
                                 * "This is new info for the test to check",
                                 * reliefService.getInfoProvided());
                                 * }
                                 * 
                                 * @Test
                                 * public void testGetAndSetLastKnownLocation() {
                                 * Location newLocation = new Location("test name", "test address");
                                 * reliefService.setLastKnownLocation(newLocation);
                                 * assertEquals("Last known location should match the one set in setup",
                                 * newLocation,
                                 * reliefService.getLastKnownLocation());
                                 * }
                                 * 
                                 * @Test
                                 * public void testGetLogDetails() {
                                 * assertEquals("Log details should match the expected format",
                                 * expectedLogDetails, reliefService.getLogDetails());
                                 * }
                                 */
}
