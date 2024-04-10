package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.regex.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReliefService {
    private ArrayList<Inquirer> inquirers = new ArrayList<Inquirer>();
    private DisasterVictim missingPerson;
    private String dateReportedMissing;
    private String infoProvided;
    private Location lastKnownLocation;

    private static final int MIN_INFO_LENGTH = 3;
    private static final int MAX_INFO_LENGTH = 400;

    private static final Pattern VALID_DATE_PATTERN = Pattern
            .compile("(19|20)\\d{2}[-](0[1-9]|1[1,2])[-](0[1-9]|[12][0-9]|3[01])");

    public ReliefService(ArrayList<Inquirer> inquirers, DisasterVictim missingPerson, String dateOfInitialInquiry,
            String infoProvided,
            Location lastKnownLocation) throws IllegalArgumentException, NullPointerException {

        setInquirers(inquirers);
        setMissingPerson(missingPerson);
        setDateReportedMissing(dateOfInitialInquiry);
        setInfoProvided(infoProvided);
        setLastKnownLocation(lastKnownLocation);
    }

    public ReliefService(DisasterVictim missingPerson, String dateOfInitialInquiry,
            String infoProvided,
            Location lastKnownLocation) throws IllegalArgumentException, NullPointerException {

        setMissingPerson(missingPerson);
        setDateReportedMissing(dateOfInitialInquiry);
        setInfoProvided(infoProvided);
        setLastKnownLocation(lastKnownLocation);
    }

    public ArrayList<Inquirer> getInquirers() {
        return this.inquirers;
    }

    public void setInquirers(ArrayList<Inquirer> inquirers) throws IllegalArgumentException {
        isValidInquirersArrayList(inquirers);
        this.inquirers = inquirers;
    }

    public void addInquirer(Inquirer inquirer) throws IllegalArgumentException {
        isValidInquirerToAdd(inquirer);
        this.inquirers.add(inquirer);
    }

    public void removeInquirer(Inquirer inquirer) {
        isValidInquirerToRemove(inquirer);
        this.inquirers.remove(inquirer);
    }

    public DisasterVictim getMissingPerson() {
        return missingPerson;
    }

    public void setMissingPerson(DisasterVictim missingPerson) {
        isValidMissingPerson(missingPerson);
        this.missingPerson = missingPerson;
    }

    public String getDateReportedMissing() {

        return this.dateReportedMissing;
    }

    public void setDateReportedMissing(String dateReportedMissing) throws IllegalArgumentException {
        isValidDate(dateReportedMissing);
        this.dateReportedMissing = dateReportedMissing;

    }

    public String getInfoProvided() {
        return infoProvided;
    }

    public void setInfoProvided(String infoProvided) {
        isValidInfo(infoProvided);
        this.infoProvided = infoProvided;
    }

    public Location getLastKnownLocation() {
        return lastKnownLocation;
    }

    public void setLastKnownLocation(Location lastKnownLocation) {
        isValidLastKnownLocation(lastKnownLocation);
        this.lastKnownLocation = lastKnownLocation;
    }

    public String getLogDetails() {
        String returnLog = "Log Details: \n";
        for (int i = 0; i < inquirers.size(); i++) {
            for (int j = 0; j < inquirers.get(i).getInquiryLog().size(); j++) {
                returnLog += inquirers.get(i).getInquiryLog().get(j).getInfo();
                returnLog += "\n";
            }
        }
        return returnLog;
    }

    private void isValidInquirersArrayList(ArrayList<Inquirer> inquirers)
            throws IllegalArgumentException, NullPointerException {
        if (inquirers.equals(null)) {
            throw new NullPointerException("Arraylist of inquirers cannot be null");
        }
        for (int i = 0; i < inquirers.size(); i++) {
            if (inquirers.get(i).equals(null)) {
                throw new NullPointerException("Elements of inquirers cannot be null");
            }
            for (int j = 0; j < inquirers.size(); j++) {
                if (i == j) {
                    continue;
                }
                if (inquirers.get(i).getId() == inquirers.get(j).getId()) {
                    throw new IllegalArgumentException(
                            "Invalid input when setting the inquirers: list of inquirers cannot have inquirers with the same ID values, these are the primary key of the inquirer");
                }
            }
        }
    }

    private void isValidInquirerToAdd(Inquirer inquirer) throws IllegalArgumentException, NullPointerException {
        if (inquirer.equals(null)) {
            throw new NullPointerException("Cannot add a null inquirer");
        }
        for (int i = 0; i < inquirers.size(); i++) {
            if (inquirers.get(i).getId() == inquirer.getId()) {
                throw new IllegalArgumentException(
                        "Invalid input when adding an inquirer: list of inquirers cannot have inquirers with the same ID values, these are the primary key of the inquirer");
            }
        }
    }

    private void isValidInquirerToRemove(Inquirer inquirer) throws IllegalArgumentException {
        if (inquirers.contains(inquirer) == false) {
            throw new IllegalArgumentException("Cannot remove an inquirer that is not currently in inquirers");
        }
    }

    private void isValidMissingPerson(DisasterVictim missingPerson) throws NullPointerException {
        if (missingPerson.equals(null)) {
            throw new NullPointerException("Cannot assign missing person to null");
        }
    }

    private void isValidLastKnownLocation(Location lastKnownLocation) {
        if (lastKnownLocation.equals(null)) {
            throw new NullPointerException("Cannot assign last known location to null");
        }
    }

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

    private void isValidInfo(String info) throws IllegalArgumentException, NullPointerException {
        if (info == null) {
            throw new NullPointerException("Cannot have null info value.");
        } else if (info.length() < MIN_INFO_LENGTH || info.length() > MAX_INFO_LENGTH) {
            throw new IllegalArgumentException("Info must have a length between 3 and 400.");
        } else {
            return;
        }
    }
}
