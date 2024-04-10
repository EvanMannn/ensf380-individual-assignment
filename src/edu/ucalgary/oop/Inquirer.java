package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.regex.*;

public class Inquirer extends Person {
    private final Integer id;
    private String phoneNumber;
    private ArrayList<Inquiry> inquiryLog = new ArrayList<Inquiry>();
    private static final Pattern VALID_PHONE_NUMBER_PATTERN = Pattern
            .compile("^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");

    public Inquirer(String firstName, String lastName, String phoneNumber, String info, ArrayList<Inquiry> inquiryLog,
            Integer id)
            throws IllegalArgumentException, NullPointerException {
        super(firstName, lastName, info);

        setPhoneNumber(phoneNumber);
        setInquiryLog(inquiryLog);
        if (id.equals(null)) {
            throw new NullPointerException("id cannot be null");
        }
        this.id = id;
    }

    public Inquirer(String firstName, String lastName, String phoneNumber, String info, Integer id)
            throws IllegalArgumentException, NullPointerException {
        super(firstName, lastName, info);

        setPhoneNumber(phoneNumber);
        if (id.equals(null)) {
            throw new NullPointerException("id cannot be null");
        }
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setPhoneNumber(String phoneNumber) {
        isValidPhoneNum(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    public String getServicesPhoneNumber() {
        return phoneNumber;
    }

    public void setInquiryLog(ArrayList<Inquiry> inquiryLog) throws IllegalArgumentException, NullPointerException {
        isValidInquiryLog(inquiryLog);
        this.inquiryLog = inquiryLog;
    }

    public ArrayList<Inquiry> getInquiryLog() {
        return this.inquiryLog;
    }

    public void addInquiry(Inquiry inquiry) throws IllegalArgumentException, NullPointerException {
        isValidInquiryToAdd(inquiry);
        inquiryLog.add(inquiry);
    }

    public void removeInquiry(Inquiry inquiry) throws IllegalArgumentException {
        if (this.inquiryLog.contains(inquiry) == false) {
            throw new IllegalArgumentException("Cannot remove an inquiry that is not in the current log");
        }
        inquiryLog.remove(inquiry);
    }

    private void isValidPhoneNum(String PhoneNum) throws IllegalArgumentException, NullPointerException {

        if (PhoneNum == null) {
            throw new NullPointerException("Phone number cannot be null");
        } else if (!VALID_PHONE_NUMBER_PATTERN.matcher(PhoneNum).matches()) {
            throw new IllegalArgumentException("Invalid Input: Phone number has an incorrect format.");
        }
    }

    private void isValidInquiryLog(ArrayList<Inquiry> inquiries) throws IllegalArgumentException, NullPointerException {
        if (inquiries.equals(null)) {
            throw new NullPointerException("Arraylist for inquiries cannot be null");
        }
        for (int i = 0; i < inquiries.size(); i++) {
            if (inquiries.get(i).equals(null)) {
                throw new NullPointerException("Elements of the inquiry log cannot be null");
            }
            for (int j = 0; j < inquiries.size(); j++) {
                if (inquiries.get(i).getId() == inquiries.get(j).getId()) {
                    throw new IllegalArgumentException(
                            "Invalid input when setting the inquiry log: list of inquiries cannot have inquiries with the same ID values, these are the primary key of the inquiry");
                }
            }
        }
    }

    private void isValidInquiryToAdd(Inquiry inquiry) throws IllegalArgumentException, NullPointerException {
        if (inquiry.equals(null)) {
            throw new NullPointerException("Cannot add null inquiries");
        }

        for (int i = 0; i < inquiryLog.size(); i++) {
            if (inquiryLog.get(i).getId() == inquiry.getId()) {
                throw new IllegalArgumentException(
                        "Invalid input when adding a new inquiry: list of inquiries cannot have inquiries with the same ID values, these are the primary key of the inquiry");
            }
        }
    }
}