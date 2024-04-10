/*
Copyright Ann Barcomb and Khawla Shnaikat, 2024
Licensed under GPL v3
See LICENSE.txt for more information.
*/
package edu.ucalgary.oop.tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class LocationTest {
    /*
     * private Location location;
     * private DisasterVictim victim;
     * private Supply supply1;
     * private Supply supply2;
     * 
     * @Before
     * public void setUp() {
     * // Initializing test objects before each test method
     * location = new Location("Shelter A", "1234 Shelter Ave");
     * victim = new DisasterVictim("John", "Doe", "some info", "2024-01-01");
     * supply1 = new Supply("Water Bottle", 10);
     * supply2 = new Supply("Toilet Paper", 10);
     * }
     * 
     * // Helper method to check if a supply is in the list
     * private boolean containsSupply(ArrayList<Supply> supplies, Supply
     * supplyToCheck) {
     * return supplies.contains(supplyToCheck);
     * }
     * 
     * @Test
     * public void testConstructor() {
     * assertNotNull("Constructor should create a non-null Location object",
     * location);
     * assertEquals("Constructor should set the name correctly", "Shelter A",
     * location.getName());
     * assertEquals("Constructor should set the address correctly",
     * "1234 Shelter Ave", location.getAddress());
     * }
     * 
     * @Test(expected = IllegalArgumentException.class)
     * public void testConstructorWithNullName() {
     * Location testLocation = new Location(null, "test address");
     * }
     * 
     * @Test(expected = IllegalArgumentException.class)
     * public void testConstructorWithNullAddress() {
     * Location testLocation = new Location("test name", null);
     * }
     * 
     * @Test
     * public void testSetName() {
     * String newName = "Shelter B";
     * location.setName(newName);
     * assertEquals("setName should update the name of the location", newName,
     * location.getName());
     * }
     * 
     * @Test(expected = IllegalArgumentException.class)
     * public void testSetNameWithNull() {
     * location.setName(null);
     * }
     * 
     * @Test
     * public void testSetAddress() {
     * String newAddress = "4321 Shelter Blvd";
     * location.setAddress(newAddress);
     * assertEquals("setAddress should update the address of the location",
     * newAddress, location.getAddress());
     * }
     * 
     * @Test(expected = IllegalArgumentException.class)
     * public void testSetAddressWithNull() {
     * location.setAddress(null);
     * }
     * 
     * @Test
     * public void testAddOccupant() {
     * location.addOccupant(victim);
     * assertTrue("addOccupant should add a disaster victim to the occupants list",
     * location.getOccupants().contains(victim));
     * }
     * 
     * @Test(expected = IllegalArgumentException.class)
     * public void testAddDuplicateOccupant() {
     * location.addOccupant(victim);
     * location.addOccupant(victim); // Add an occupant that is already in the array
     * }
     * 
     * @Test
     * public void testRemoveOccupant() {
     * location.addOccupant(victim); // Ensure the victim is added first
     * location.removeOccupant(victim);
     * assertFalse("removeOccupant should remove the disaster victim from the occupants list"
     * ,
     * location.getOccupants().contains(victim));
     * }
     * 
     * @Test
     * public void testSetAndGetOccupants() {
     * ArrayList<DisasterVictim> newOccupants = new ArrayList<>();
     * newOccupants.add(victim);
     * location.setOccupants(newOccupants);
     * assertTrue("setOccupants should replace the occupants list with the new list"
     * ,
     * location.getOccupants().containsAll(newOccupants));
     * }
     * 
     * @Test(expected = IllegalArgumentException.class)
     * public void testSetOccupantsWithDuplicates() {
     * ArrayList<DisasterVictim> newOccupants = new ArrayList<>();
     * newOccupants.add(victim);
     * newOccupants.add(victim);
     * location.setOccupants(newOccupants);
     * }
     * 
     * @Test
     * public void testAddSupplyThatIsAlreadyInSupplies() {
     * ArrayList<Supply> newSupplies = new ArrayList<>();
     * newSupplies.add(supply1);
     * location.setSupplies(newSupplies);
     * location.addSupply(supply1);
     * assertTrue("addSupply should increase the quantity of the supply",
     * location.getSupplies().get(0).getQuantity() == 20);
     * }
     * 
     * @Test
     * public void testAddSupplyThatIsNotAreadyInSupplies() {
     * ArrayList<Supply> newSupplies = new ArrayList<>();
     * newSupplies.add(supply1);
     * location.setSupplies(newSupplies);
     * location.addSupply(supply2);
     * assertTrue("addSupply should add the new supply to the current list of supplies"
     * ,
     * containsSupply(location.getSupplies(), supply2));
     * }
     * 
     * @Test
     * public void testRemoveSupplyToAQuantityAboveZero() {
     * Supply testSupply = new Supply("Water Bottle", 5);
     * ArrayList<Supply> newSupplies = new ArrayList<>();
     * newSupplies.add(supply1);
     * location.setSupplies(newSupplies);
     * location.removeSupply(testSupply);
     * assertTrue("removeSupply should decrease the quantity of the supply in the list"
     * ,
     * location.getSupplies().get(0).getQuantity() == 5);
     * }
     * 
     * @Test
     * public void testRemoveSupplyToZero() {
     * location.addSupply(supply1);
     * location.removeSupply(supply1);
     * assertFalse("removeSupply should remove the supply from the supplies list",
     * containsSupply(location.getSupplies(), supply1));
     * }
     * 
     * @Test(expected = IllegalArgumentException.class)
     * public void testRemoveSupplyToAQuantityBelowZero() {
     * Supply testSupply = new Supply("Water Bottle", 15);
     * ArrayList<Supply> newSupplies = new ArrayList<>();
     * newSupplies.add(supply1);
     * location.setSupplies(newSupplies);
     * location.removeSupply(testSupply);
     * }
     * 
     * @Test(expected = IllegalArgumentException.class)
     * public void testRemoveASupplyThatIsNotPresent() {
     * Supply testSupply = new Supply("test", 15);
     * ArrayList<Supply> newSupplies = new ArrayList<>();
     * newSupplies.add(supply1);
     * location.setSupplies(newSupplies);
     * location.removeSupply(testSupply);
     * }
     * 
     * @Test
     * public void testSetAndGetSuppliesWithValidInput() {
     * ArrayList<Supply> newSupplies = new ArrayList<>();
     * newSupplies.add(supply1);
     * location.setSupplies(newSupplies);
     * assertTrue("setSupplies should replace the supplies list with the new list",
     * containsSupply(location.getSupplies(), supply1));
     * }
     * 
     * @Test(expected = IllegalArgumentException.class)
     * public void testSetSuppliesWithInvalidInput() {
     * ArrayList<Supply> newSupplies = new ArrayList<>();
     * newSupplies.add(supply1);
     * newSupplies.add(supply1);
     * location.setSupplies(newSupplies);
     * }
     * 
     * @Test
     * public void testAssignSuppliesToOccupantWithValidInput() {
     * location.addOccupant(victim);
     * location.addSupply(supply1);
     * Supply supplyToAssign = new Supply("Water Bottle", 5);
     * ArrayList<Supply> suppliesToAssign = new ArrayList<>();
     * suppliesToAssign.add(supplyToAssign);
     * location.assignSuppliesToOccupant(victim, suppliesToAssign);
     * assertEquals(
     * "assignSuppliesToOccupant should lower the quantity of the item at the location and add it to the victim's personal belongings"
     * ,
     * victim.getPersonalBelongings().get(0), supplyToAssign);
     * }
     * 
     * @Test(expected = IllegalArgumentException.class)
     * public void testAssignSuppliesToOccupantWithoutEnoughQuantity() {
     * location.addOccupant(victim);
     * location.addSupply(supply1);
     * Supply supplyToAssign = new Supply("Water Bottle", 15);
     * ArrayList<Supply> suppliesToAssign = new ArrayList<>();
     * suppliesToAssign.add(supplyToAssign);
     * location.assignSuppliesToOccupant(victim, suppliesToAssign);
     * }
     * 
     * @Test(expected = IllegalArgumentException.class)
     * public void testAssignSuppliesToOccupantThatIsNotPresentAtTheLocation() {
     * location.addOccupant(victim);
     * Supply supplyToAssign = new Supply("Water Bottle", 15);
     * ArrayList<Supply> suppliesToAssign = new ArrayList<>();
     * suppliesToAssign.add(supplyToAssign);
     * location.assignSuppliesToOccupant(victim, suppliesToAssign);
     * }
     */
}