package edu.ucalgary.oop.InquirerGUIComponents;

import java.util.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import edu.ucalgary.oop.InquirerDatabaseInterfacer;

public class SelectInquirer extends JFrame implements ActionListener {
    public final InquirerDatabaseInterfacer db = new InquirerDatabaseInterfacer();

    private Container c;

    private JLabel title;

    private JPanel results;
    private JScrollPane jsp;

    private JLabel fName;
    private JLabel lName;
    private JLabel phoneNum;
    private JButton addInquiry;

    private JButton back;

    SelectInquirer() {
        ArrayList<String> allInquirers = db.selectAllInquirers();
        ArrayList<String> allIds = db.selectInquirerIds();
        int numResults = allInquirers.size();

        setTitle("Search Results");
        setBounds(300, 50, 700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);
        // Title
        title = new JLabel("Search Results");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 40);
        title.setLocation(250, 30);
        c.add(title);

        results = new JPanel();
        GridLayout gl = new GridLayout(numResults, 1);

        results.setLayout(gl);
        for (int i = 0; i < numResults; i++) {
            JPanel result = new JPanel();

            ArrayList<String> resultsElements = new ArrayList<String>();
            for (String s : allInquirers.get(i).split(","))
                resultsElements.add(s);

            fName = new JLabel(resultsElements.get(1));
            lName = new JLabel(resultsElements.get(2));
            phoneNum = new JLabel(resultsElements.get(3));
            addInquiry = new JButton("Add Inquiry");
            addInquiry.setName(allIds.get(i));
            addInquiry.setPreferredSize(new Dimension(60, 30));
            addInquiry.addActionListener(this);

            GridLayout fourByOne = new GridLayout(4, 1);
            result.setLayout(fourByOne);
            result.add(fName);
            result.add(lName);
            result.add(phoneNum);
            result.add(addInquiry);
            result.add(new JLabel(""));
            results.add(result);
        }

        results.setVisible(true);

        jsp = new JScrollPane(
                results,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setSize(300, 550);
        jsp.setVisible(true);
        jsp.getVerticalScrollBar().setUnitIncrement(12);
        jsp.setLocation(200, 100);
        c.add(jsp);

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
        JButton source = (JButton) e.getSource();

        if (source.getText() == addInquiry.getText()) {
            dispose();
            new ExistingInquirer(Integer.parseInt(source.getName()));
        } else if (e.getSource() == back) {
            dispose();
            new ExistingOrNewInquirer();
        }
    }
}
