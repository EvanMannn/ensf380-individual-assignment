package edu.ucalgary.oop.InquirerGUIComponents;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import edu.ucalgary.oop.InquirerDatabaseInterfacer;

public class ExistingInquirer extends JFrame implements ActionListener {
    private final InquirerDatabaseInterfacer db = new InquirerDatabaseInterfacer();

    private int d, m, y;
    private String infoString;
    private int exisitngInquirerId;

    private String formattedDate;

    // Components of the Form
    private Container c;
    private JLabel title;

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

    ExistingInquirer(int exisitngInquirerIdArg) {
        exisitngInquirerId = exisitngInquirerIdArg;
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
                db.insertNewInquiry(exisitngInquirerId, formattedDate, infoString);
            } else {
                System.out.println("Invalid entries, no insertions made");
            }
        } else if (e.getSource() == back) {
            dispose();
            new SelectInquirer();
        }
    }

    private boolean validateInquiryInput() {

        boolean allInputValid = false;

        d = Integer.parseInt((String) date.getSelectedItem());
        m = Integer.parseInt((String) month.getSelectedItem());
        y = Integer.parseInt((String) year.getSelectedItem());
        infoString = infoText.getText();
        boolean bool1 = isValidDate(d, m, y);
        boolean bool2 = isValidInfo(infoString);
        if (bool1 && bool2)
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
}
