package edu.ucalgary.oop.InquirerGUIComponents;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class LinkToDisasterVictim implements ActionListener {
    private String inquirer;
    private String searchTerm;

    JFrame frame = new JFrame();
    JLabel label = new JLabel("List of Missing People");

    JButton back = new JButton("Back");
    JButton addInquiry = new JButton("Add Inquiry");

    LinkToDisasterVictim(String inquirerArg, String searchTermArg) {
        searchTerm = searchTermArg;
        inquirer = inquirerArg;
        addInquiry.setBounds(100, 160, 200, 40);
        addInquiry.setFocusable(false);
        addInquiry.addActionListener(this);

        back.setBounds(100, 80, 200, 40);
        back.setFocusable(false);
        back.addActionListener(this);

        label.setBounds(0, 0, 1000, 50);
        label.setFont(new Font(null, Font.PLAIN, 25));

        frame.add(label);
        frame.add(addInquiry);
        frame.add(back);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addInquiry) {
            System.out.println("Added");
        } else if (e.getSource() == back) {
            frame.dispose();
            new SeeInquires(inquirer, searchTerm);
        }
    }

}
