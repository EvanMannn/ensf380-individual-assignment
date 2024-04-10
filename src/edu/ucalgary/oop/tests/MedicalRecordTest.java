package edu.ucalgary.oop.tests;

import org.junit.Before;
import org.junit.Test;

import edu.ucalgary.oop.Location;
import edu.ucalgary.oop.MedicalRecord;

import static org.junit.Assert.*;
import java.util.ArrayList;

public class MedicalRecordTest {

    private MedicalRecord medicalRecord;
    private Location location;
    private String expectedTreatmentDetails = "Broken arm treated";
    private String expectedDateOfTreatment = "2024-01-19";

    @Before
    public void setUp() {
        location = new Location("Shelter A", "140 8 Ave NW");
        medicalRecord = new MedicalRecord(location, expectedTreatmentDetails, expectedDateOfTreatment);
    }

    @Test
    public void testConstructorWithValidInputs() {
        assertNotNull("Constructor should successfully create an instance with a valid inquiry date", medicalRecord);
        assertEquals("Constructor should successfully set the treatment date", expectedDateOfTreatment,
                medicalRecord.getDateOfTreatment());
        assertEquals("Constructor should successfully set the treatment details", expectedTreatmentDetails,
                medicalRecord.getTreatmentDetails());
        assertEquals("Constructor should successfully set the location", location,
                medicalRecord.getLocation());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidEntryDateFormat() {
        MedicalRecord testMedicalRecord = new MedicalRecord(location, expectedTreatmentDetails,
                "2020/02/02");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithFutureDate() {
        MedicalRecord testMedicalRecord = new MedicalRecord(location, expectedTreatmentDetails,
                "2025-04-11");
    }

    @Test()
    public void testConstructorWithValidLeapYearDate() {
        MedicalRecord testMedicalRecord = new MedicalRecord(location, expectedTreatmentDetails,
                "2020-02-29");
        assertEquals("Constructor should successfully set the treatement date to this leap year date", "2020-02-29",
                testMedicalRecord.getDateOfTreatment());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidLeapYearDate() {
        MedicalRecord testMedicalRecord = new MedicalRecord(location, expectedTreatmentDetails,
                "2021-02-29");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithTooOldDate() {
        MedicalRecord testMedicalRecord = new MedicalRecord(location, expectedTreatmentDetails,
                "1881-02-29");
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullDate() {
        MedicalRecord testMedicalRecord = new MedicalRecord(location, expectedTreatmentDetails,
                null);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullLocation() {
        MedicalRecord testMedicalRecord = new MedicalRecord(null, expectedTreatmentDetails,
                "2020-03-29");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithTooLongTreatmentDetails() {
        String tooLongDetails = new String(new char[201]).replace('\0', 'a');
        MedicalRecord testMedicalRecord = new MedicalRecord(location, tooLongDetails,
                "2020-03-29");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithTooShortTreatmentDetails() {
        String tooShortDetails = "No";
        MedicalRecord testMedicalRecord = new MedicalRecord(location, tooShortDetails,
                "2020-03-29");
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullTreatmentDetails() {
        MedicalRecord testMedicalRecord = new MedicalRecord(location, null,
                "2020-03-29");
    }

    @Test
    public void testSetLocation() {
        Location newExpectedLocation = new Location("Shelter B", "150 8 Ave NW ");
        medicalRecord.setLocation(newExpectedLocation);
        assertEquals("setLocation should update the Location", newExpectedLocation.getName(),
                medicalRecord.getLocation().getName());
    }

    @Test(expected = NullPointerException.class)
    public void testSetLocationAsNull() {
        medicalRecord.setLocation(null);
    }

    @Test
    public void testSetTreatmentDetails() {
        String newExpectedTreatment = "No surgery required";
        medicalRecord.setTreatmentDetails(newExpectedTreatment);
        assertEquals("setTreatmentDetails should update the treatment details", newExpectedTreatment,
                medicalRecord.getTreatmentDetails());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetTooLongTreatmentDetails() {
        String tooLongDetails = new String(new char[201]).replace('\0', 'a');
        medicalRecord.setTreatmentDetails(tooLongDetails);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetTooShortTreatmentDetails() {
        String tooShortDetails = "No";
        medicalRecord.setTreatmentDetails(tooShortDetails);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNullTreatmentDetails() {
        medicalRecord.setTreatmentDetails(null);

    }

    @Test
    public void testSetDateOfTreatment() {
        String newExpectedDateOfTreatment = "2024-02-05";
        medicalRecord.setDateOfTreatment(newExpectedDateOfTreatment);
        assertEquals("setDateOfTreatment should update date of treatment", newExpectedDateOfTreatment,
                medicalRecord.getDateOfTreatment());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfTreatmentWithInvalidFormat() {
        medicalRecord.setDateOfTreatment("2020/02/02");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfTreatmentWithFutureDate() {
        medicalRecord.setDateOfTreatment("2025-04-11");
    }

    @Test()
    public void testSetDateOfTreatmentWithValidLeapYearDate() {
        medicalRecord.setDateOfTreatment("2020-02-29");
        assertEquals("Setter should successfully set the treatement date to this leap year date", "2020-02-29",
                medicalRecord.getDateOfTreatment());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfTreatmentWithInvalidLeapYearDate() {
        medicalRecord.setDateOfTreatment("2021-02-29");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfTreatmentWithTooOldDate() {
        medicalRecord.setDateOfTreatment("1881-02-29");
    }

    @Test(expected = NullPointerException.class)
    public void testSetDateOfTreatmentWithNullDate() {
        medicalRecord.setDateOfTreatment(null);

    }

}
