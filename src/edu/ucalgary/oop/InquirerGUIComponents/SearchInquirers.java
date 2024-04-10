package edu.ucalgary.oop.InquirerGUIComponents;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SearchInquirers extends JFrame implements ActionListener {

    private Container c;

    private JLabel title;

    private JLabel inquirerName;
    private JTextField searchField;

    private JButton back;
    private JButton search;

    SearchInquirers() {
        setTitle("Search Inquirers");
        setBounds(300, 50, 700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);
        // Title
        title = new JLabel("Search Inquirers");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 40);
        title.setLocation(250, 30);
        c.add(title);

        // Inquirer Name Header and Text Box
        inquirerName = new JLabel("Inquirer Name");
        inquirerName.setFont(new Font("Arial", Font.PLAIN, 20));
        inquirerName.setSize(200, 20);
        inquirerName.setLocation(120, 340);
        c.add(inquirerName);

        searchField = new JTextField();
        searchField.setFont(new Font("Arial", Font.PLAIN, 15));
        searchField.setSize(230, 20);
        searchField.setLocation(260, 340);
        c.add(searchField);

        // Navigation Footer
        search = new JButton("Search");
        search.addActionListener(this);
        search.setFocusable(false);
        search.setSize(100, 40);
        search.setLocation(460, 620);
        c.add(search);

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
        if (e.getSource() == search) {
            dispose();
            new SearchInquirersResults(searchField.getText());
        } else if (e.getSource() == back) {
            dispose();
            new MainMenu();
        }
    }
}
