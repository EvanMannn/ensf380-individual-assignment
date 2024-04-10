package edu.ucalgary.oop.InquirerGUIComponents;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

public class MainMenu extends JFrame implements ActionListener {

    // Components of the Form
    private Container c;
    private JLabel title;
    private JButton logInquiry;
    private JButton searchInquiries;

    MainMenu() {

        setTitle("Main Menu");
        setBounds(300, 50, 700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);
        // Title
        title = new JLabel("   Main Menu   ");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 40);
        title.setLocation(250, 30);
        c.add(title);

        // Navigation Footer
        logInquiry = new JButton("Log Inquiry");
        logInquiry.addActionListener(this);
        logInquiry.setFocusable(false);
        logInquiry.setSize(200, 40);
        logInquiry.setLocation(100, 350);
        c.add(logInquiry);

        searchInquiries = new JButton("Search Inquiries");
        searchInquiries.addActionListener(this);
        searchInquiries.setFocusable(false);
        searchInquiries.setSize(200, 40);
        searchInquiries.setLocation(400, 350);
        c.add(searchInquiries);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logInquiry) {
            dispose();
            new ExistingOrNewInquirer();
        } else if (e.getSource() == searchInquiries) {
            dispose();
            new SearchInquirers();
        }
    }
}
