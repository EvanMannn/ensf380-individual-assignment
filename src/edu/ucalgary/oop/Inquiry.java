package edu.ucalgary.oop;

import java.util.regex.Pattern;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Inquiry {
    private String dateOfInquiry;
    private String info;
    private final Integer id;

    private static final Pattern VALID_DATE_PATTERN = Pattern
            .compile("(19|20)\\d{2}[-](0[1-9]|1[1,2])[-](0[1-9]|[12][0-9]|3[01])");

    public Inquiry(String dateOfInquiry, String info, Integer id)
            throws IllegalArgumentException, NullPointerException {
        setDateOfInquiry(dateOfInquiry);
        setInfo(info);
        if (id.equals(null)) {
            throw new NullPointerException("id cannot be null");
        }
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getDateOfInquiry() {
        return dateOfInquiry;
    }

    public void setDateOfInquiry(String dateOfInquiry) throws IllegalArgumentException, NullPointerException {
        isValidDate(dateOfInquiry);
        this.dateOfInquiry = dateOfInquiry;

    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        isValidInfo(info);
        this.info = info;
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

    private void isValidInfo(String info) throws IllegalArgumentException, NullPointerException {
        if (info == null) {
            throw new NullPointerException("info cannot be null");
        } else if (info.length() > 400 || info.length() < 3) {
            throw new IllegalArgumentException("info must have a length between 3 and 400 characters");
        }
    }
}
