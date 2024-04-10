/*
Copyright Ann Barcomb and Khawla Shnaikat, 2024
Licensed under GPL v3
See LICENSE.txt for more information.
*/

package edu.ucalgary.oop.tests;

import org.junit.Before;
import org.junit.Test;

import edu.ucalgary.oop.DietaryRestriction;
import edu.ucalgary.oop.DisasterVictim;
import edu.ucalgary.oop.FamilyRelation;
import edu.ucalgary.oop.GenderTextFileReader;
import edu.ucalgary.oop.Location;
import edu.ucalgary.oop.MedicalRecord;
import edu.ucalgary.oop.RelationshipType;
import edu.ucalgary.oop.Supply;

import static org.junit.Assert.*;
import java.util.ArrayList;

public class DisasterVictimTest {
        private DisasterVictim victim1, victim2, victim3, victim4;

        private ArrayList<Supply> validSupplies = new ArrayList<Supply>();
        private ArrayList<Supply> invalidSuppliesWithNull = new ArrayList<Supply>();
        private ArrayList<Supply> invalidSuppliesWithDuplicates = new ArrayList<Supply>();

        private ArrayList<FamilyRelation> validFamilyRelations = new ArrayList<FamilyRelation>();
        private ArrayList<FamilyRelation> invalidFamilyRelationsWithNull = new ArrayList<FamilyRelation>();
        private ArrayList<FamilyRelation> invalidFamilyRelationsWithDuplicates = new ArrayList<FamilyRelation>();

        private ArrayList<MedicalRecord> validMedicalRecords = new ArrayList<MedicalRecord>();
        private ArrayList<MedicalRecord> invalidMedicalRecordsWithNull = new ArrayList<MedicalRecord>();
        private ArrayList<MedicalRecord> invalidMedicalRecordsWithDuplicates = new ArrayList<MedicalRecord>();

        private String expectedFirstName = "Tim";
        private String expectedLastName = "Freddy";
        private String validEntryDate = "2024-01-15";
        private String validDateOfBirth = "2003-04-14";
        private Integer validApproximateAge = 23;
        private DietaryRestriction expectedDietaryRestriction = DietaryRestriction.AVML;
        private GenderTextFileReader gtr;
        private String expectedInfo = "Needs medical attention and speaks 2 languages";

        @Before
        public void setUp() {
                gtr = new GenderTextFileReader("..\\data\\GenderOptions.txt");
                ArrayList<String> validGenders = gtr.getGenders();

                victim1 = new DisasterVictim(expectedFirstName, expectedLastName, expectedInfo, validDateOfBirth,
                                validEntryDate, validGenders.get(0), expectedDietaryRestriction);
                victim2 = new DisasterVictim(expectedFirstName, expectedLastName, expectedInfo, validApproximateAge,
                                validEntryDate, validGenders.get(0), expectedDietaryRestriction);

                // Supplies
                validSupplies.add(new Supply("Water Bottle", 10));

                invalidSuppliesWithNull.add(null);

                invalidSuppliesWithDuplicates.add(new Supply("Blanket", 5));
                invalidSuppliesWithDuplicates.add(new Supply("Blanket", 5));

                // Family Relations
                validFamilyRelations.add(new FamilyRelation(victim2, RelationshipType.SI, victim1));

                invalidFamilyRelationsWithNull.add(null);

                invalidFamilyRelationsWithDuplicates.add(new FamilyRelation(victim2, RelationshipType.SI, victim1));
                invalidFamilyRelationsWithDuplicates.add(new FamilyRelation(victim2, RelationshipType.SI, victim1));

                // Medical Records

                Location testLocation = new Location("Shelter Z", "1234 Shelter Ave");
                validMedicalRecords.add(new MedicalRecord(testLocation, "test for strep", "2024-02-09"));

                invalidMedicalRecordsWithNull.add(null);

                invalidMedicalRecordsWithDuplicates
                                .add(new MedicalRecord(testLocation, "test for strep", "2024-02-09"));
                invalidMedicalRecordsWithDuplicates
                                .add(new MedicalRecord(testLocation, "test for strep", "2024-02-09"));

                victim3 = new DisasterVictim(expectedFirstName, expectedLastName, expectedInfo, validDateOfBirth,
                                validEntryDate, validGenders.get(0), expectedDietaryRestriction, validMedicalRecords,
                                validFamilyRelations, validSupplies);
                victim4 = new DisasterVictim(expectedFirstName, expectedLastName, expectedInfo, validApproximateAge,
                                validEntryDate, validGenders.get(0), expectedDietaryRestriction, validMedicalRecords,
                                validFamilyRelations, validSupplies);
        }

        /*
         * Due to the constructor using the setters, predominant input testing is done
         * through the setters, constructors are tested for proper operation when given
         * valid inputs
         */
        /*
         * @Test
         * public void testConstructorWithValidEntryDate() {
         * victim = new DisasterVictim(expectedFirstName, expectedLastName,
         * expectedInfo, validDate);
         * assertNotNull("Constructor should successfully create an instance with a valid entry date"
         * , victim);
         * assertEquals("Constructor should set the entry date correctly", validDate,
         * victim.getEntryDate());
         * }
         * 
         * @Test(expected = IllegalArgumentException.class)
         * public void testConstructorWithInvalidEntryDateFormat() {
         * victim = new DisasterVictim(expectedFirstName, expectedLastName,
         * expectedInfo, invalidDate);
         * }
         * 
         * @Test(expected = IllegalArgumentException.class)
         * public void testConstructorWithNullEntryDate() {
         * victim = new DisasterVictim(expectedFirstName, expectedLastName,
         * expectedInfo, null);
         * }
         * 
         * @Test
         * public void testSetDateOfBirthWithValidDate() {
         * String newDateOfBirth = "1987-05-21";
         * victim.setDateOfBirth(newDateOfBirth);
         * assertEquals("setDateOfBirth should correctly update the date of birth",
         * newDateOfBirth,
         * victim.getDateOfBirth());
         * assertEquals("The approximate age value should be set to null when the birthday is set"
         * ,
         * victim.getApproximateAge(), null);
         * }
         * 
         * @Test(expected = IllegalArgumentException.class)
         * public void testSetDateOfBirthWithInvalidFormat() {
         * 
         * victim.setDateOfBirth(invalidDate); // This format should cause an exception
         * }
         * 
         * @Test
         * public void testSetApproximateAgeWithValidValue() {
         * 
         * victim.setApproximateAge(12);
         * assertTrue("setApproximateAge should set the approximate age of victim",
         * victim.getApproximateAge() == 12);
         * assertEquals("setApproximateAge should set the birthday to null",
         * victim.getDateOfBirth(), null);
         * }
         * 
         * @Test(expected = IllegalArgumentException.class)
         * public void testSetApproximateAgeWithNegativeAge() {
         * 
         * victim.setApproximateAge(-1);
         * }
         * 
         * @Test
         * public void testGetAssignedSocialID() {
         * // Since the victim is created first, before the two victims in setup, it
         * should
         * // have a
         * // social id of 0
         * 
         * assertEquals("getAssignedSocialID should return the expected social ID", 0,
         * victim.getAssignedSocialID());
         * }
         * 
         * @Test
         * public void testGetEntryDate() {
         * String EXPECTED_ENTRY_DATE = "2024-01-02";
         * victim = new DisasterVictim(expectedFirstName, expectedLastName,
         * expectedInfo, EXPECTED_ENTRY_DATE);
         * assertEquals("getEntryDate should return the expected entry date",
         * EXPECTED_ENTRY_DATE, victim.getEntryDate());
         * }
         * 
         * @Test
         * public void testSetAndGetGenderWithValidInput() {
         * String newGender = "male";
         * victim.setGender(newGender);
         * assertEquals("setGender should update and getGender should return the new gender"
         * , newGender.toLowerCase(),
         * victim.getGender());
         * }
         * 
         * @Test(expected = IllegalArgumentException.class)
         * public void testSetGenderWithInvalidInput() {
         * String newGender = "massive sand worm";
         * victim.setGender(newGender);
         * }
         * 
         * @Test
         * public void testAddFamilyConnection() {
         * FamilyRelation relation = familyRelations.get(0);
         * FamilyRelation siblingsRelation = new FamilyRelation(victim1,
         * relation.getRelationshipTo(), victim);
         * victim.addFamilyConnection(relation, true);
         * assertTrue("addFamilyConnection should add a family relationship to victim",
         * victim.getFamilyConnections().contains(relation));
         * assertTrue(
         * "addFamilyConnection should add the family connection to the second person within the relation as well"
         * ,
         * victim1.getFamilyConnections().contains(siblingsRelation));
         * }
         * 
         * @Test
         * public void testAddPersonalBelonging() {
         * Supply newSupply = new Supply("Emergency Kit", 1);
         * victim.addPersonalBelonging(newSupply);
         * assertTrue("addPersonalBelonging should add the supply to personal belongings"
         * ,
         * victim.getPersonalBelongings().contains(newSupply));
         * }
         * 
         * @Test
         * public void testRemoveFamilyConnection() {
         * victim.setFamilyConnections(familyRelations);
         * victim.removeFamilyConnection(familyRelations.get(0));
         * assertFalse("removeFamilyConnection should remove the family connection",
         * victim.getFamilyConnections().contains(familyRelations.get(0)));
         * }
         * 
         * @Test
         * public void testRemovePersonalBelonging() {
         * 
         * Supply supplyToRemove = suppliesToSet.get(0);
         * victim.addPersonalBelonging(supplyToRemove);
         * victim.removePersonalBelonging(supplyToRemove);
         * assertFalse("removePersonalBelonging should remove the supply from personal belongings"
         * ,
         * victim.getPersonalBelongings().contains(supplyToRemove));
         * }
         * 
         * @Test
         * public void testSetFamilyConnectionWithValidInput() {
         * victim.setFamilyConnections(familyRelations);
         * assertEquals("Family relation should be set", familyRelations,
         * victim.getFamilyConnections());
         * }
         * 
         * @Test(expected = IllegalArgumentException.class)
         * public void testSetFamilyConnectionWithDuplicates() {
         * ArrayList<FamilyRelation> testRelations = new ArrayList<FamilyRelation>();
         * testRelations.add(new FamilyRelation(victim, "sibling", victim1));
         * testRelations.add(new FamilyRelation(victim, "sibling", victim1));
         * victim.setFamilyConnections(testRelations);
         * }
         * 
         * @Test
         * public void testGetAndSetMedicalRecords() {
         * 
         * victim.setMedicalRecords(medicalRecords);
         * assertEquals("setMedicalRecords should correctly update medical records",
         * victim.getMedicalRecords(),
         * medicalRecords);
         * }
         * 
         * @Test
         * public void testGetAndSetPersonalBelongings() {
         * victim.setPersonalBelongings(suppliesToSet);
         * 
         * assertEquals("setPersonalBelongings should correctly update personal belongings"
         * ,
         * victim.getPersonalBelongings(), suppliesToSet);
         * }
         * 
         * @Test
         * public void testGetAndSetDietaryRestrictions() {
         * victim.setDietaryRestriction(DietaryRestriction.DBML);
         * assertEquals("setDietaryRestrictions should set the value of dietaryRestriction in victim"
         * ,
         * victim.getDietaryRestriction(), DietaryRestriction.DBML);
         * }
         */
}
