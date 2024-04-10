package edu.ucalgary.oop.InquirerGUIComponents;

import edu.ucalgary.oop.InquirerDatabaseInterfacer;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SeeInquires extends JFrame implements ActionListener {
    private String inquirer;
    private String searchTerm;

    private InquirerDatabaseInterfacer db = new InquirerDatabaseInterfacer();

    private Container c;

    private JLabel title;

    private JPanel results;
    private JScrollPane jsp;

    private JLabel inquiryDate;
    private JLabel info;
    private JButton linkToDisasterVictim;

    private JButton back;

    SeeInquires(String inquirerArg, String searchTermArg) {
        searchTerm = searchTermArg;
        inquirer = inquirerArg;
        ArrayList<String> inquiries = db.selectInquiriesByInquirer(inquirer);

        System.out.println(inquiries);
        int numInquiries = inquiries.size();

        setTitle("Inquiries");
        setBounds(300, 50, 700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);
        // Title
        title = new JLabel("Inquiries");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 40);
        title.setLocation(250, 30);
        c.add(title);

        results = new JPanel();
        GridLayout gl = new GridLayout(numInquiries * 2, 1);

        results.setLayout(gl);
        for (int i = 0; i < numInquiries; i++) {
            JPanel result = new JPanel();

            ArrayList<String> inquiryElements = new ArrayList<String>();
            for (String s : inquiries.get(i).split(","))
                inquiryElements.add(s);

            inquiryDate = new JLabel(inquiryElements.get(0));
            info = new JLabel(convertStringToHTML(inquiryElements.get(1)));
            linkToDisasterVictim = new JButton("Add to a missing person's file");

            linkToDisasterVictim.setPreferredSize(new Dimension(60, 30));
            linkToDisasterVictim.addActionListener(this);

            GridLayout oneByTwo = new GridLayout(1, 2);
            result.setLayout(oneByTwo);
            result.add(inquiryDate);
            result.add(info);

            results.add(result);
            results.add(linkToDisasterVictim);
        }
        results.setVisible(true);

        jsp = new JScrollPane(
                results,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setSize(350, 450);
        jsp.setVisible(true);
        jsp.getVerticalScrollBar().setUnitIncrement(12);
        jsp.setLocation(50, 100);
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

        if (source.getText() == linkToDisasterVictim.getText()) {
            dispose();
            new LinkToDisasterVictim(inquirer, searchTerm);
        } else if (e.getSource() == back) {
            dispose();
            new SearchInquirersResults(searchTerm);
        }
    }

    private String convertStringToHTML(String s) {
        StringBuffer htmlString = new StringBuffer();
        htmlString.append("<HTML>");
        for (char c : s.toCharArray()) {
            switch (c) {
                case '<':
                    htmlString.append("&lt;");
                    break;
                case '>':
                    htmlString.append("&gt;");
                    break;
                case '&':
                    htmlString.append("&amp;");
                    break;
                case '"':
                    htmlString.append("&quot;");
                    break;
                case '\n':
                    htmlString.append("<br>");
                    break;
                case '\t':
                    htmlString.append("&nbsp; &nbsp; &nbsp;");
                    break;
                default:
                    if (c < 128) {
                        htmlString.append(c);
                    } else {
                        htmlString.append("&#").append((int) c).append(";");
                    }
            }
        }
        htmlString.append("</HTML>");
        return htmlString.toString();
    }
}
