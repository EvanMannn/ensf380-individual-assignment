package edu.ucalgary.oop.InquirerGUIComponents;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

public class ExistingOrNewInquirer extends JFrame implements ActionListener {

    // Components of the Form
    private Container c;

    private JButton existingInquirer;
    private JButton newInquirer;
    private JButton back;

    ExistingOrNewInquirer() {

        setTitle("Existing Or New Inquirer");
        setBounds(300, 50, 700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        // Navigation Footer
        existingInquirer = new JButton("Existing Inquirer");
        existingInquirer.addActionListener(this);
        existingInquirer.setFocusable(false);
        existingInquirer.setSize(200, 40);
        existingInquirer.setLocation(100, 350);
        c.add(existingInquirer);

        newInquirer = new JButton("New Inquirer");
        newInquirer.addActionListener(this);
        newInquirer.setFocusable(false);
        newInquirer.setSize(200, 40);
        newInquirer.setLocation(400, 350);
        c.add(newInquirer);

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
        if (e.getSource() == existingInquirer) {
            dispose();
            new SelectInquirer();
        } else if (e.getSource() == newInquirer) {
            dispose();
            new LogInquiry();
        } else if (e.getSource() == back) {
            dispose();
            new MainMenu();
        }
    }
}
