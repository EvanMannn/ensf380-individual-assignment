package edu.ucalgary.oop.InquirerGUIComponents;

import java.util.*;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import edu.ucalgary.oop.InquirerDatabaseInterfacer;

public class LogInquiry extends JFrame implements ActionListener {
    private final InquirerDatabaseInterfacer db = new InquirerDatabaseInterfacer();

    private int d, m, y;
    private String fNameString, lNameString, descriptionString, infoString, phoneNumString;

    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 25;
    private static final Pattern VALID_NAME_PATTERN = Pattern
            .compile("^[\\p{Lu}][\\p{Ll}]*((\\.| |-|')[\\p{Lu}][\\p{Ll}]*)*$");
    private static final Pattern VALID_PHONE_NUMBER_PATTERN = Pattern
            .compile("^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");
    private String formattedDate;

    // Components of the Form
    private Container c;
    private JLabel title;
    private JLabel header1;

    private JLabel fName;
    private JTextField fNameText;

    private JLabel lName;
    private JTextField lNameText;

    private JLabel description;
    private JTextField descriptionText;

    private JLabel phoneNum;
    private JTextField phoneNumText;

    private JLabel header2;

    private JLabel dateOfInquiry;
    private JComboBox<String> date;
    private JComboBox<String> month;
    private JComboBox<String> year;

    private JLabel info;
    private JTextArea infoText;

    private JButton submit;
    private JButton back;

    private final String dates[] = { "01", "02", "03", "04", "05",
            "06", "07", "08", "09", "10",
            "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30",
            "31" };
    private final String months[] = { "01", "02", "03", "04",
            "05", "06", "07", "08",
            "09", "10", "11", "12" };
    private final String years[] = { "1995", "1996", "1997", "1998",
            "1999", "2000", "2001", "2002",
            "2003", "2004", "2005", "2006",
            "2007", "2008", "2009", "2010",
            "2011", "2012", "2013", "2014",
            "2015", "2016", "2017", "2018",
            "2019", "2020", "2021", "2022",
            "2023", "2024" };

    LogInquiry() {
        setTitle("Log Inquiry");
        setBounds(300, 50, 700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        // Title
        title = new JLabel("   Log Inquiry   ");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 40);
        title.setLocation(250, 30);
        c.add(title);

        // Header1
        header1 = new JLabel("Inquirer Details");
        header1.setFont(new Font("Arial", Font.PLAIN, 25));
        header1.setSize(300, 40);
        header1.setLocation(40, 100);
        c.add(header1);

        // First Name Header and Text Box
        fName = new JLabel("First Name");
        fName.setFont(new Font("Arial", Font.PLAIN, 20));
        fName.setSize(200, 20);
        fName.setLocation(60, 140);
        c.add(fName);

        fNameText = new JTextField();
        fNameText.setFont(new Font("Arial", Font.PLAIN, 15));
        fNameText.setSize(190, 20);
        fNameText.setLocation(260, 140);
        c.add(fNameText);

        // Last Name Header and Text Box
        lName = new JLabel("Last Name");
        lName.setFont(new Font("Arial", Font.PLAIN, 20));
        lName.setSize(200, 20);
        lName.setLocation(60, 165);
        c.add(lName);

        lNameText = new JTextField();
        lNameText.setFont(new Font("Arial", Font.PLAIN, 15));
        lNameText.setSize(190, 20);
        lNameText.setLocation(260, 165);
        c.add(lNameText);

        // Description Header and Text Box
        description = new JLabel("Description");
        description.setFont(new Font("Arial", Font.PLAIN, 20));
        description.setSize(200, 20);
        description.setLocation(60, 190);
        c.add(description);

        descriptionText = new JTextField();
        descriptionText.setFont(new Font("Arial", Font.PLAIN, 15));
        descriptionText.setSize(190, 20);
        descriptionText.setLocation(260, 190);
        c.add(descriptionText);

        // Phone Number Header and Text Box
        phoneNum = new JLabel("Phone Number");
        phoneNum.setFont(new Font("Arial", Font.PLAIN, 20));
        phoneNum.setSize(200, 20);
        phoneNum.setLocation(60, 215);
        c.add(phoneNum);

        phoneNumText = new JTextField();
        phoneNumText.setFont(new Font("Arial", Font.PLAIN, 15));
        phoneNumText.setSize(190, 20);
        phoneNumText.setLocation(260, 215);
        c.add(phoneNumText);

        // Header2
        header2 = new JLabel("Inquiry Details");
        header2.setFont(new Font("Arial", Font.PLAIN, 25));
        header2.setSize(300, 40);
        header2.setLocation(40, 300);
        c.add(header2);

        // Date Header and Text Box
        dateOfInquiry = new JLabel("Date Of Inquiry");
        dateOfInquiry.setFont(new Font("Arial", Font.PLAIN, 20));
        dateOfInquiry.setSize(200, 20);
        dateOfInquiry.setLocation(60, 340);
        c.add(dateOfInquiry);

        date = new JComboBox<String>(dates);
        date.setFont(new Font("Arial", Font.PLAIN, 15));
        date.setSize(50, 20);
        date.setLocation(260, 340);
        c.add(date);

        month = new JComboBox<String>(months);
        month.setFont(new Font("Arial", Font.PLAIN, 15));
        month.setSize(60, 20);
        month.setLocation(320, 340);
        c.add(month);

        year = new JComboBox<String>(years);
        year.setFont(new Font("Arial", Font.PLAIN, 15));
        year.setSize(60, 20);
        year.setLocation(390, 340);
        c.add(year);

        // Info Header and Text Area
        info = new JLabel("Info");
        info.setFont(new Font("Arial", Font.PLAIN, 20));
        info.setSize(200, 20);
        info.setLocation(60, 365);
        c.add(info);

        infoText = new JTextArea();
        infoText.setFont(new Font("Arial", Font.PLAIN, 15));
        infoText.setSize(350, 170);
        infoText.setLocation(60, 390);
        infoText.setLineWrap(true);
        c.add(infoText);

        // Navigation Footer
        submit = new JButton("Submit");
        submit.addActionListener(this);
        submit.setFocusable(false);
        submit.setSize(100, 40);
        submit.setLocation(460, 620);
        c.add(submit);

        back = new JButton("Back");
        back.addActionListener(this);
        back.setFocusable(false);
        back.setSize(100, 40);
        back.setLocation(580, 620);
        c.add(back);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            if (validateInquiryInput()) {
                int newInquirerID = db.insertNewInquirer(fNameString, lNameString, phoneNumString, descriptionString);
                db.insertNewInquiry(newInquirerID, formattedDate, infoString);
            } else {
                System.out.println("Invalid entries, no insertions made");
            }
        } else if (e.getSource() == back) {
            dispose();
            new ExistingOrNewInquirer();
        }
    }

    private boolean validateInquiryInput() {

        boolean allInputValid = false;

        d = Integer.parseInt((String) date.getSelectedItem());
        m = Integer.parseInt((String) month.getSelectedItem());
        y = Integer.parseInt((String) year.getSelectedItem());
        fNameString = fNameText.getText();
        lNameString = lNameText.getText();
        descriptionString = descriptionText.getText();
        infoString = infoText.getText();
        phoneNumString = phoneNumText.getText();
        boolean bool1 = isValidDate(d, m, y);
        boolean bool2 = isValidName(fNameString, "first");
        boolean bool3 = isValidName(lNameString, "last");
        boolean bool4 = isValidDescr(descriptionString);
        boolean bool5 = isValidInfo(infoString);
        boolean bool6 = isValidPhoneNum(phoneNumString);
        if (bool1 && bool2 && bool3 && bool4 && bool5 && bool6)
            allInputValid = true;
        formattedDate = year.getSelectedItem() + "-" + month.getSelectedItem() + "-" + date.getSelectedItem();

        return allInputValid;

    }

    private boolean isValidDate(int d, int m, int y) {
        boolean boolValue;

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
            JOptionPane.showMessageDialog(this, "month " + m + " does not have 31 days.");
        } else if ((m == 2 && isLeapYear && d > 29)) {
            boolValue = false;
            JOptionPane.showMessageDialog(this, "Invalid Input: February does not have more than 29 days that year");
        } else if ((m == 2 && !isLeapYear && d > 28)) {
            boolValue = false;
            JOptionPane.showMessageDialog(this, "Invalid Input: February does not have more than 28 days that year");
        } else
            boolValue = true;
        String dateStr = String.format("%04d-%02d-%02d", y, m, d);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate givenDate = LocalDate.parse(dateStr, formatter);
        LocalDate today = LocalDate.now();
        if (givenDate.isAfter(today)) {
            JOptionPane.showMessageDialog(this, "Invalid Input: Cannot input a day in the future");
            boolValue = false;
        }

        return boolValue;
    }

    private boolean isValidName(String name, String nameType) {
        boolean boolValue;

        int length = name.length();
        if (name == null || name == "") {
            boolValue = false;
            JOptionPane.showMessageDialog(this, "Invalid Input: " + nameType + " name cannot be empty ");
        } else if (length < MIN_LENGTH || length > MAX_LENGTH) {
            boolValue = false;
            JOptionPane.showMessageDialog(this,
                    "Invalid Input:  " + nameType + " name must be between 2 and 25 characters.");
        } else if (!VALID_NAME_PATTERN.matcher(name).matches()) {
            boolValue = false;
            JOptionPane.showMessageDialog(this, "Invalid Input: " + nameType + " name is not in a valid format.");
        } else {
            boolValue = true;
        }

        return boolValue;
    }

    private boolean isValidDescr(String descr) {
        boolean boolValue;

        if (descr.length() > 25 || descr.length() < 3) {
            boolValue = false;
            JOptionPane.showMessageDialog(this, "Invalid Input: The description must be between 3 and 20 characters.");
        } else if (descr == null || descr == "") {
            boolValue = false;
            JOptionPane.showMessageDialog(this, "Invalid Input: The description cannot be empty.");
        } else
            boolValue = true;

        return boolValue;
    }

    private boolean isValidInfo(String info) {
        boolean boolValue;

        if (info.length() > 400 || info.length() < 3) {
            boolValue = false;
            JOptionPane.showMessageDialog(this, "Invalid Input: Info must be between 3 and 200 characters.");
        } else if (info == null || info == "") {
            boolValue = false;
            JOptionPane.showMessageDialog(this, "Invalid Input: Info cannot be empty.");
        } else
            boolValue = true;

        return boolValue;
    }

    private boolean isValidPhoneNum(String PhoneNum) {
        boolean boolValue;

        if (!VALID_PHONE_NUMBER_PATTERN.matcher(PhoneNum).matches()) {
            boolValue = false;
            JOptionPane.showMessageDialog(this, "Invalid Input: Phone number has an incorrect format.");
        } else
            boolValue = true;

        return boolValue;
    }
}
