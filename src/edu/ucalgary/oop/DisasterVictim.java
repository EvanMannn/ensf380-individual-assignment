package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.regex.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DisasterVictim extends Person {

    // This file path is relative to running the program from the src directory
    // If the text file has been updated change the file path to
    // ..\\data\\new_file_name.txt
    // Where new_file_name is the name of the new text file

    private static final String FILE_PATH = "..\\data\\GenderOptions.txt";

    private String dateOfBirth;
    private Integer approximateAge;

    private ArrayList<MedicalRecord> medicalRecords = new ArrayList<MedicalRecord>();
    private ArrayList<FamilyRelation> familyRelations = new ArrayList<FamilyRelation>();
    private ArrayList<Supply> personalBelongings = new ArrayList<Supply>();

    private String gender;
    private DietaryRestriction dietaryRestriction;

    private final int ASSIGNED_SOCIAL_ID;
    private final String ENTRY_DATE;

    private static int counter = 0;
    private static final Pattern VALID_DATE_PATTERN = Pattern
            .compile("(19|20)\\d{2}[-](0[1-9]|1[1,2])[-](0[1-9]|[12][0-9]|3[01])");

    // constructor
    public DisasterVictim(String firstName, String lastName, String info, String dateOfBirth, String ENTRY_DATE,
            String gender, DietaryRestriction dietaryRestriction, ArrayList<MedicalRecord> medicalRecords,
            ArrayList<FamilyRelation> familyRelations, ArrayList<Supply> personalBelongings)
            throws IllegalArgumentException, NullPointerException {
        super(firstName, firstName, info);

        isValidDate(ENTRY_DATE);
        this.ENTRY_DATE = ENTRY_DATE;

        setDateOfBirth(dateOfBirth);
        setGender(gender);
        setDietaryRestriction(dietaryRestriction);
        setMedicalRecords(medicalRecords);
        setFamilyRelations(familyRelations);
        setPersonalBelongings(personalBelongings);

        this.ASSIGNED_SOCIAL_ID = counter;
        counter += 1;

    }

    public DisasterVictim(String firstName, String lastName, String info, int approximateAge, String ENTRY_DATE,
            String gender, DietaryRestriction dietaryRestriction, ArrayList<MedicalRecord> medicalRecords,
            ArrayList<FamilyRelation> familyRelations, ArrayList<Supply> personalBelongings)
            throws IllegalArgumentException, NullPointerException {
        super(firstName, firstName, info);

        isValidDate(ENTRY_DATE);
        this.ENTRY_DATE = ENTRY_DATE;

        setApproximateAge(approximateAge);
        setGender(gender);
        setDietaryRestriction(dietaryRestriction);
        setMedicalRecords(medicalRecords);
        setFamilyRelations(familyRelations);
        setPersonalBelongings(personalBelongings);

        this.ASSIGNED_SOCIAL_ID = counter;
        counter += 1;

    }

    public DisasterVictim(String firstName, String lastName, String info, String dateOfBirth, String ENTRY_DATE,
            String gender, DietaryRestriction dietaryRestriction)
            throws IllegalArgumentException, NullPointerException {
        super(firstName, firstName, info);

        isValidDate(ENTRY_DATE);
        this.ENTRY_DATE = ENTRY_DATE;

        setDateOfBirth(dateOfBirth);
        setGender(gender);
        setDietaryRestriction(dietaryRestriction);

        this.ASSIGNED_SOCIAL_ID = counter;
        counter += 1;

    }

    public DisasterVictim(String firstName, String lastName, String info, int approximateAge, String ENTRY_DATE,
            String gender, DietaryRestriction dietaryRestriction)
            throws IllegalArgumentException, NullPointerException {
        super(firstName, firstName, info);

        isValidDate(ENTRY_DATE);
        this.ENTRY_DATE = ENTRY_DATE;

        setApproximateAge(approximateAge);
        setGender(gender);
        setDietaryRestriction(dietaryRestriction);

        this.ASSIGNED_SOCIAL_ID = counter;
        counter += 1;

    }

    // Getters
    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public Integer getApproximateAge() {
        return this.approximateAge;
    }

    public ArrayList<MedicalRecord> getMedicalRecords() {
        return this.medicalRecords;
    }

    public String getEntryDate() {
        return this.ENTRY_DATE;
    }

    public int getAssignedSocialID() {
        return this.ASSIGNED_SOCIAL_ID;
    }

    public ArrayList<Supply> getPersonalBelongings() {
        return this.personalBelongings;
    }

    public ArrayList<FamilyRelation> getFamilyRelations() {
        return this.familyRelations;
    }

    public String getGender() {
        return this.gender;
    }

    public DietaryRestriction getDietaryRestriction() {
        return this.dietaryRestriction;
    }

    // Setters
    public void setDateOfBirth(String dateOfBirth) throws IllegalArgumentException, NullPointerException {
        isValidDate(dateOfBirth);
        isValidAge(dateOfBirth);
        this.dateOfBirth = dateOfBirth;
        this.approximateAge = null;
    }

    public void setApproximateAge(int approximateAge) throws IllegalArgumentException, NullPointerException {
        isValidAge(approximateAge);
        this.approximateAge = approximateAge;
        this.dateOfBirth = null;
    }

    public void setGender(String gender) throws IllegalArgumentException, NullPointerException {
        isValidGender(gender);
        this.gender = gender;
    }

    public void setDietaryRestriction(DietaryRestriction dietaryRestriction) {
        this.dietaryRestriction = dietaryRestriction;
    }

    public void setMedicalRecords(ArrayList<MedicalRecord> medicalRecords)
            throws IllegalArgumentException, NullPointerException {
        isValidMedicalRecordArrayList(medicalRecords);
        this.medicalRecords = medicalRecords;
    }

    public void setPersonalBelongings(ArrayList<Supply> supplies)
            throws IllegalArgumentException, NullPointerException {
        isValidPersonalBelongsArrayList(supplies);
        this.personalBelongings = supplies;
    }

    public void setFamilyRelations(ArrayList<FamilyRelation> relations)
            throws IllegalArgumentException, NullPointerException {
        isValidFamilyRelationsArrayList(relations);

        for (int i = 0; i < familyRelations.size(); i++) {
            this.addFamilyRelation(familyRelations.get(i), true);
        }
    }

    public void incrementPersonalBelonging(Supply supply) throws NullPointerException {
        isValidPersonalBelongingToAdd(supply);
        for (int i = 0; i < personalBelongings.size(); i++) {
            Supply cur = personalBelongings.get(i);
            if (cur.getType() == supply.getType()) {
                cur.setQuantity(cur.getQuantity() + supply.getQuantity());
                return;
            } else {
                personalBelongings.add(supply);
            }
        }
    }

    public void decrementPersonalBelonging(Supply supply) throws IllegalArgumentException {
        if (!personalBelongings.contains(supply)) {
            throw new IllegalArgumentException("Cannot remove a personal belonging that doesn't exist");
        }
        personalBelongings.remove(supply);
    }

    public void addFamilyRelation(FamilyRelation familyRelation, boolean chainUpdate)
            throws IllegalArgumentException, NullPointerException {

        isValidfamilyRelationToAdd(familyRelation);

        familyRelations.add(familyRelation);
        if (chainUpdate == true) {
            if (familyRelation.getRelationshipTo() == RelationshipType.CTP
                    || familyRelation.getRelationshipTo() == RelationshipType.PTC) {
                FamilyRelation newFamilyRelation = new FamilyRelation(familyRelation.getPersonTwo(),
                        familyRelation.getRelationshipTo().getOppoRelationshipType(), this);
                familyRelation.getPersonTwo().addFamilyRelation(newFamilyRelation, false);
            } else {
                FamilyRelation newFamilyRelation = new FamilyRelation(familyRelation.getPersonTwo(),
                        familyRelation.getRelationshipTo(), this);
                familyRelation.getPersonTwo().addFamilyRelation(newFamilyRelation, false);
            }
        }

        updateFamilyTree();
    }

    public void removeFamilyRelation(FamilyRelation familyRelation, boolean chainUpdate)
            throws IllegalArgumentException {
        if (!familyRelations.contains(familyRelation)) {
            throw new IllegalArgumentException("Cannot remove a family relation that doesn't exist");
        }

        familyRelations.remove(familyRelation);
        if (chainUpdate == true) {
            FamilyRelation newFamilyRelationToRemove = new FamilyRelation(familyRelation.getPersonTwo(),
                    familyRelation.getRelationshipTo(), this);
            familyRelation.getPersonTwo().removeFamilyRelation(newFamilyRelationToRemove, false);
        }
    }

    public void addMedicalRecord(MedicalRecord medicalRecord) throws IllegalArgumentException, NullPointerException {
        isValidMedicalRecordToAdd(medicalRecord);
        medicalRecords.add(medicalRecord);
    }

    public void removeMedicalRecord(MedicalRecord medicalRecord) throws IllegalArgumentException {
        if (!medicalRecords.contains(medicalRecord)) {
            throw new IllegalArgumentException("Cannot remove a medical record that doesn't exist");
        }
        medicalRecords.remove(medicalRecord);
    }

    // Validation functions
    private void isValidDate(String date) throws IllegalArgumentException, NullPointerException {
        if (date == null) {
            throw new NullPointerException("Cannot have null date value.");
        } else if (!VALID_DATE_PATTERN.matcher(date).matches()) {
            throw new IllegalArgumentException("Date has an incorrect format, it must be of the format yyyy-mm-dd");
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate givenDate = LocalDate.parse(date, formatter);
            LocalDate today = LocalDate.now();
            if (givenDate.isAfter(today)) {
                throw new IllegalArgumentException("Cannot input a date in the future");
            }
        }
        // Leap year checks
        if (validateLeapYearInput(date) == false) {
            throw new IllegalArgumentException("Invalid input regarding a leap year");
        }
    }

    private boolean validateLeapYearInput(String date) {

        boolean boolValue;
        int d, m, y;
        y = Integer.parseInt(date.substring(0, 4));
        m = Integer.parseInt(date.substring(5, 7));
        d = Integer.parseInt(date.substring(8));

        boolean isLeapYear = false;
        if (y % 4 != 0) {
            isLeapYear = false;
        } else if (y % 100 != 0) {
            isLeapYear = true;
        } else if (y % 400 != 0) {
            isLeapYear = false;
        } else {
            isLeapYear = true;
        }

        ArrayList<Integer> monthsWith3Days = new ArrayList<Integer>();
        monthsWith3Days.add(4);
        monthsWith3Days.add(6);
        monthsWith3Days.add(9);
        monthsWith3Days.add(11);
        if (monthsWith3Days.contains(m) && d == 31) {
            boolValue = false;

        } else if ((m == 2 && isLeapYear && d > 29)) {
            boolValue = false;

        } else if ((m == 2 && !isLeapYear && d > 28)) {
            boolValue = false;

        } else
            boolValue = true;

        return boolValue;

    }

    private void isValidAge(String birthday) throws IllegalArgumentException, NullPointerException {
        Integer birthYear = Integer.parseInt(birthday.substring(0, 4));
        int age = 2024 - birthYear;
        if (age > 120 || age < 0) {
            throw new IllegalArgumentException(
                    "Age must be a number between 0 and 120. The age calculated from the birthday provided breaks this rule");
        } else if (birthYear.equals(null)) {
            throw new NullPointerException("Birthday cannot be null while approximate age is also null");
        }
    }

    private void isValidAge(Integer age) throws IllegalArgumentException, NullPointerException {
        if (age > 120 || age < 0) {
            throw new IllegalArgumentException(
                    "Age must be a number between 0 and 120. The supplied integer breaks this rule");
        } else if (age.equals(null)) {
            throw new NullPointerException("Approximate age cannot be null while birthday is also null");
        }
    }

    private void isValidGender(String gender) throws IllegalArgumentException, NullPointerException {
        GenderTextFileReader gtr = new GenderTextFileReader(FILE_PATH);
        if (gender == null) {
            throw new NullPointerException("Cannot have null gender value");
        } else if (gtr.getGenders().contains(gender)) {
            throw new IllegalArgumentException(
                    "Gender must be in the supplied text file that GenderTextFileReader reads from. See documentation for more information");
        } else {
            return;
        }
    }

    private void isValidFamilyRelationsArrayList(ArrayList<FamilyRelation> familyRelations)
            throws IllegalArgumentException, NullPointerException {
        if (familyRelations.equals(null)) {
            throw new NullPointerException("Cannot set family relations to null");
        }

        for (int i = 0; i < familyRelations.size(); i++) {
            for (int j = 0; j < familyRelations.size(); j++) {
                if (j != i) {
                    if (familyRelations.get(i).compareRelations(familyRelations.get(j))) {
                        throw new IllegalArgumentException(
                                "Cannot set family realtions to an array with duplicate relations in it. Relations are not directional so if the people are flipped that is still a duplicate");
                    }
                }
            }
        }
    }

    private void isValidfamilyRelationToAdd(FamilyRelation familyRelation)
            throws IllegalArgumentException, NullPointerException {
        if (familyRelation.getPersonOne().equals(this) == false) {
            throw new IllegalArgumentException(
                    "Cannot add family connections don't involve this disaster victim as person one within the connection");
        } else if (familyRelation.equals(null)) {
            throw new NullPointerException("Cannot add null family relation");
        }

        for (int i = 0; i < familyRelations.size(); i++) {
            if (familyRelations.get(i).compareRelations(familyRelation)) {
                throw new IllegalArgumentException(
                        "Cannot add family connections that already exist on this person's file");
            }
        }
    }

    private void isValidMedicalRecordArrayList(ArrayList<MedicalRecord> medicalRecords)
            throws IllegalArgumentException, NullPointerException {
        if (medicalRecords.equals(null)) {
            throw new NullPointerException("Cannot set medical records to null");
        }

        for (int i = 0; i < medicalRecords.size(); i++) {
            for (int j = 0; j < medicalRecords.size(); j++) {
                if (j != i) {
                    if (medicalRecords.get(i).equals(medicalRecords.get(j))) {
                        throw new IllegalArgumentException(
                                "Cannot set medical records to an array with duplicate records in it");
                    }
                }
            }
        }
    }

    private void isValidMedicalRecordToAdd(MedicalRecord medicalRecord)
            throws IllegalArgumentException, NullPointerException {
        if (medicalRecord.equals(null)) {
            throw new NullPointerException("Cannot add null family relation");
        }

        for (int i = 0; i < medicalRecords.size(); i++) {
            if (medicalRecords.get(i).equals(medicalRecord)) {
                throw new IllegalArgumentException(
                        "Cannot add medical record that already exist on this person's file");
            }
        }
    }

    private void isValidPersonalBelongsArrayList(ArrayList<Supply> personalBelongings)
            throws IllegalArgumentException, NullPointerException {
        if (personalBelongings.equals(null)) {
            throw new NullPointerException("Cannot add null personal belongings");
        }

        for (int i = 0; i < personalBelongings.size(); i++) {
            for (int j = 0; j < personalBelongings.size(); j++) {
                if (j != i) {
                    if (personalBelongings.get(i).getType() == personalBelongings.get(j).getType()) {
                        throw new IllegalArgumentException(
                                "Cannot set personal belongings to an array with duplicate supply types in it. These entries should be combined into one element with a single quantity");
                    }
                }
            }
        }
    }

    private void isValidPersonalBelongingToAdd(Supply personalBelonging) throws NullPointerException {
        if (personalBelonging.equals(null)) {
            throw new NullPointerException("Cannot add a personal belonging that is null");
        }
    }

    // Helper functions
    private void updateFamilyTree() {
        for (int i = 0; i < familyRelations.size(); i++) {
            FamilyRelation main = familyRelations.get(i);
            if (main.getRelationshipTo() == RelationshipType.SP) {

                for (int j = 0; j < familyRelations.size(); j++) {
                    if (i == j) {
                        continue;
                    }
                    FamilyRelation cur = familyRelations.get(i);
                    if (cur.getRelationshipTo() == RelationshipType.PTC) {
                        main.getPersonTwo().addFamilyRelation(
                                new FamilyRelation(main.getPersonTwo(), RelationshipType.PTC, cur.getPersonTwo()),
                                true);
                    }
                }

            } else if (main.getRelationshipTo() == RelationshipType.PTC) {

                for (int j = 0; j < familyRelations.size(); j++) {
                    if (i == j) {
                        continue;
                    }
                    FamilyRelation cur = familyRelations.get(i);
                    if (cur.getRelationshipTo() == RelationshipType.PTC) {
                        main.getPersonTwo().addFamilyRelation(
                                new FamilyRelation(main.getPersonTwo(), RelationshipType.SI, cur.getPersonTwo()),
                                true);
                    }
                }

            } else if (main.getRelationshipTo() == RelationshipType.CTP) {

                for (int j = 0; j < familyRelations.size(); j++) {
                    if (i == j) {
                        continue;
                    }
                    FamilyRelation cur = familyRelations.get(i);
                    if (cur.getRelationshipTo() == RelationshipType.SI) {
                        main.getPersonTwo().addFamilyRelation(
                                new FamilyRelation(main.getPersonTwo(), RelationshipType.PTC, cur.getPersonTwo()),
                                true);
                    }
                }

            } else if (main.getRelationshipTo() == RelationshipType.SI) {

                for (int j = 0; j < familyRelations.size(); j++) {
                    if (i == j) {
                        continue;
                    }
                    FamilyRelation cur = familyRelations.get(i);
                    if (cur.getRelationshipTo() == RelationshipType.SI) {
                        main.getPersonTwo().addFamilyRelation(
                                new FamilyRelation(main.getPersonTwo(), RelationshipType.SI, cur.getPersonTwo()),
                                true);
                    }
                }

            }
        }
    }
}