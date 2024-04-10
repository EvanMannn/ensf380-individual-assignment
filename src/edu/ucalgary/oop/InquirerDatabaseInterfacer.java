/*
Copyright Ann Barcomb and Emily Marasco, 2021-2024
Licensed under GPL v3
See LICENSE.txt for more information.
*/

package edu.ucalgary.oop;

import java.sql.*;
import java.util.ArrayList;

public class InquirerDatabaseInterfacer {

    public int maxInquirerID;
    public int maxInquiryID;

    private Connection dbConnect;
    private ResultSet queryResults;

    public InquirerDatabaseInterfacer() {
        try {
            dbConnect = DriverManager.getConnection("jdbc:postgresql://localhost/ensf380project", "oop", "ucalgary");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> selectAllInquirers() {

        ArrayList<String> searchResults = new ArrayList<String>();

        try {
            Statement myStmt = dbConnect.createStatement();
            queryResults = myStmt.executeQuery("SELECT * FROM INQUIRER");
            while (queryResults.next()) {

                System.out.println(queryResults.getString("firstName") + ", "
                        + queryResults.getString("lastName") + ", " + queryResults.getString("phoneNumber") + ", "
                        + queryResults.getString("info"));

                searchResults.add(queryResults.getString("firstName") + ", " + queryResults.getString("lastName")
                        + ", " + queryResults.getString("phoneNumber") + ", " + queryResults.getString("info"));

            }

            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return searchResults;
    }

    public ArrayList<String> selectInquirersBySearchTerm(String searchTerm) {
        ArrayList<String> searchResults = new ArrayList<String>();

        try {
            String query = "SELECT * FROM INQUIRER WHERE firstName LIKE ?";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);

            myStmt.setString(1, "%" + searchTerm + "%");
            queryResults = myStmt.executeQuery();
            System.out.println("\nInquirers");
            while (queryResults.next()) {

                System.out.println(queryResults.getString("firstName") + ", "
                        + queryResults.getString("lastName") + ", " + queryResults.getString("phoneNumber") + ", "
                        + queryResults.getString("info"));

                searchResults.add(queryResults.getString("firstName") + ", " + queryResults.getString("lastName")
                        + ", " + queryResults.getString("phoneNumber") + ", " + queryResults.getString("info"));

            }

            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return searchResults;
    }

    public ArrayList<String> selectInquiriesByInquirer(String fName) {
        ArrayList<String> inquiries = new ArrayList<String>();

        try {
            String query = "SELECT * FROM INQUIRY_LOG WHERE inquirer IN (SELECT id FROM INQUIRER WHERE firstName = ?)";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);

            myStmt.setString(1, fName);
            queryResults = myStmt.executeQuery();
            System.out.println("\nInquiries");
            while (queryResults.next()) {

                System.out.println(queryResults.getString("callDate") + ", " + queryResults.getString("details"));

                inquiries.add(queryResults.getString("callDate") + ", " + queryResults.getString("details"));

            }

            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return inquiries;
    }

    public int insertNewInquirer(String fName, String lName, String phoneNum, String info) {
        int id = -1;
        try {
            PreparedStatement myStmt;

            String query = "INSERT INTO INQUIRER (firstName, lastName, phoneNumber, info) VALUES (?,?,?,?) RETURNING id";
            myStmt = dbConnect.prepareStatement(query);

            myStmt.setString(1, fName);
            myStmt.setString(2, lName);
            myStmt.setString(3, phoneNum);
            myStmt.setString(4, info);

            queryResults = myStmt.executeQuery();
            if (queryResults.next()) {
                id = queryResults.getInt(1);
                System.out.println("The ID of the new inquirer is: " + id);
            }
            myStmt.close();
            return id;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return id;
        }
    }

    public void insertNewInquiry(int inquirer, String callDate,
            String details) {
        try {
            java.sql.Date date = java.sql.Date.valueOf(callDate);

            String query = "INSERT INTO INQUIRY_LOG (inquirer, callDate, details) VALUES (?,?,?)";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);

            myStmt.setInt(1, inquirer);
            myStmt.setDate(2, date);
            myStmt.setString(3, details);

            myStmt.executeUpdate();
            System.out.println("New Inquiry Added");

            myStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<String> selectInquirerIds() {
        ArrayList<String> searchResults = new ArrayList<String>();

        try {
            Statement myStmt = dbConnect.createStatement();
            queryResults = myStmt.executeQuery("SELECT id FROM INQUIRER");
            while (queryResults.next()) {
                System.out.println(queryResults.getString("id"));

                searchResults.add(queryResults.getString("id"));
            }

            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return searchResults;
    }

    public void close() {
        try {
            // results.close();
            dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}