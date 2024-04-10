package edu.ucalgary.oop;

import java.util.regex.*;

public class Person {
    private String firstName;
    private String lastName;
    private String info;

    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 25;

    private static final int MIN_INFO_LENGTH = 3;
    private static final int MAX_INFO_LENGTH = 400;

    private static final Pattern VALID_NAME_PATTERN = Pattern
            .compile("^[\\p{Lu}][\\p{Ll}]*((\\.| |-|')[\\p{Lu}][\\p{Ll}]*)*$");

    public Person(String firstName, String lastName, String info) throws IllegalArgumentException {
        setFirstName(firstName);
        setLastName(lastName);
        setInfo(info);
    }

    public void setFirstName(String firstName) throws IllegalArgumentException {
        isValidName(firstName);
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) throws IllegalArgumentException {
        isValidName(lastName);
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setInfo(String info) {
        isValidInfo(info);
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    private void isValidName(String name) throws IllegalArgumentException, NullPointerException {
        if (name == null) {
            throw new NullPointerException("Cannot have null name value.");
        } else if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Name must have a length between 2 and 25.");
        } else if (!VALID_NAME_PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException(
                    "Name does not follow the correct format. Names must be made collections of capitalized words that are only seperated by a single special character ('. -)");
        } else {
            return;
        }
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
