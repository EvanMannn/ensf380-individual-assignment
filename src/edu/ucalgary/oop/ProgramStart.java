package edu.ucalgary.oop;

import edu.ucalgary.oop.InquirerGUIComponents.*;

import java.util.Scanner;
import java.util.ArrayList;

public class ProgramStart {
    /*
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * The following code is the setup to demonstrate the execution of the GUIs.
     * Every static variable is an element of the setup that can be modified by the
     * GUIs during their operation.
     * This includes an array list of relief service objects, location objects, and
     * various forms of supplies, disaster victims, medical records, etc.
     * These all combine to create the initial state of which the GUIs modify.
     * This does use only valid inputs for the initial setup however all classes
     * have protection against invalid inputs inside their code that
     * throws the correct exceptions and or makes the correct modifications to solve
     * the problems.
     * Also it is noted that all of these variables, since they are static, can be
     * overwritten through assignment which could cause issues,
     * but this is just for demonstration purposes, proper use of such an
     * application would have to make use of a database to protect against
     * assignment operations. However, the GUIs do not use assignment operations
     * unless creating a new object and only make use of appropriate
     * class methods to modify the objects to demonstrate the understanding that
     * assignment operations should be avoided.
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * 
     */

    public static ArrayList<ReliefService> reliefServiceArray = new ArrayList<ReliefService>();
    public static ArrayList<Location> locationArray = new ArrayList<Location>();
    public static ArrayList<ArrayList<Supply>> supplyArrays = new ArrayList<ArrayList<Supply>>();
    public static ArrayList<DisasterVictim> disasterVictimArray = new ArrayList<DisasterVictim>();
    public static ArrayList<MedicalRecord> medicalRecordArray = new ArrayList<MedicalRecord>();
    public static ArrayList<FamilyRelation> familyRelationArray = new ArrayList<FamilyRelation>();
    public static ArrayList<Inquirer> inquirerArray = new ArrayList<Inquirer>();
    public static ArrayList<ArrayList<Inquiry>> inquiryArrays = new ArrayList<ArrayList<Inquiry>>();

    public static MedicalRecord med1;
    public static MedicalRecord med2;
    public static MedicalRecord med3;
    public static MedicalRecord med4;

    /*
     * // Create a new instance with Date of Birth
     * public static DisasterVictim victim1 = new DisasterVictim("John", "Doe",
     * "1980-01-01", "info1", med1, "2022-01-01");
     * 
     * // Create a new instance with Age
     * public static DisasterVictim victim2 = new DisasterVictim("Jane", "Doe", 42,
     * "info2", med2, "2022-01-02");
     * 
     * // Create a new instance with Date of Birth and a different medical record
     * public static DisasterVictim victim3 = new DisasterVictim("Alice", "Smith",
     * "1990-01-01", "info3", med3,
     * "2022-01-03");
     * 
     * // Create a new instance with Age and a different medical record
     * public static DisasterVictim victim4 = new DisasterVictim("Bob", "Smith", 32,
     * "info4", med4, "2022-01-04");
     */
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println(
                "Select whether you want to initialize the log inquirer GUI (1) or enter a new disaster victim (2)");
        while (true) {
            String input = inputScanner.nextLine();
            if (input.equals("1")) {
                InquirerGUI.main();
                inputScanner.close();
                break;
            } else if (input.equals("2")) {
                // DISASTER VICTIM GUI GOES HERE
                inputScanner.close();
                break;
            } else {
                System.out.println("Please type 1 or 2:");
            }
        }
    }
}
