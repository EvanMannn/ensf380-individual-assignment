package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.regex.*;

public class Location {
    // Member Variables
    private String name;
    private String address;
    private ArrayList<DisasterVictim> occupants = new ArrayList<DisasterVictim>();
    private ArrayList<Supply> supplies = new ArrayList<Supply>();

    private static final Integer MAX_NAME_LENGTH = 30;
    private static final Integer MIN_NAME_LENGTH = 3;

    private static final Pattern VALID_NAME_PATTERN = Pattern
            .compile("^[\\p{Lu}][\\p{Ll}]*((\\.| |-|')[\\p{Lu}][\\p{Ll}]*)*$");

    // Constructor
    public Location(String name, String address, ArrayList<DisasterVictim> occupants, ArrayList<Supply> supplies)
            throws IllegalArgumentException, NullPointerException {
        setName(name);
        setAddress(address);
        setOccupants(occupants);
        setSupplies(supplies);
    }

    public Location(String name, String address) throws IllegalArgumentException, NullPointerException {
        setName(name);
        setAddress(address);
    }

    // name Methods
    public void setName(String name) throws IllegalArgumentException {
        isValidName(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // address Methods
    public void setAddress(String address) throws IllegalArgumentException {
        isValidAddress(address);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    // occupants Methods
    public void setOccupants(ArrayList<DisasterVictim> occupants) throws IllegalArgumentException {
        isValidOccupantsArray(occupants);
        this.occupants = occupants;
    }

    public ArrayList<DisasterVictim> getOccupants() {
        return occupants;
    }

    public void addOccupant(DisasterVictim occupant) throws IllegalArgumentException {
        isValidOccupantToAdd(occupant);
        occupants.add(occupant);
    }

    public void removeOccupant(DisasterVictim occupant) throws IllegalArgumentException {
        if (occupants.contains(occupant) == false) {
            throw new IllegalArgumentException("Cannot remove an occupant that is not currently in occupants");
        }
        occupants.remove(occupant);
    }

    // supplies Methods
    public void setSupplies(ArrayList<Supply> supplies) throws IllegalArgumentException {
        isValidSuppliesArray(supplies);
        this.supplies = supplies;
    }

    public ArrayList<Supply> getSupplies() {
        return supplies;
    }

    public void incrementSupply(Supply supply) {
        isValidSupplyToAdd(supply);
        for (int i = 0; i < supplies.size(); i++) {
            Supply cur = supplies.get(i);
            if (cur.getType() == supply.getType()) {
                cur.setQuantity(cur.getQuantity() + supply.getQuantity());
                return;
            } else {
                supplies.add(supply);
            }
        }
    }

    public void decrementSupply(Supply supply) throws IllegalArgumentException, NullPointerException {
        // Decrease the quantity of the supply passed as an argument by the quantity
        // given
        if (supply.equals(null)) {
            throw new NullPointerException("Cannot decrement supplies with a null value");
        }
        for (int i = 0; i < supplies.size(); i++) {
            if (supply.getType() == supplies.get(i).getType()) {
                supplies.get(i).setQuantity((supplies.get(i).getQuantity() - supply.getQuantity()));
                if (supplies.get(i).getQuantity() == 0) {
                    supplies.remove(i);
                } else if (supplies.get(i).getQuantity() < 0) {
                    throw new IllegalArgumentException(
                            "Cannot remove a higher quantity of supplies than are currently available in the current supplies");
                }
                return;
            }
        }
        throw new IllegalArgumentException("Cannot remove a supply that is not at this location");
    }

    public void assignSuppliesToOccupant(DisasterVictim occupant, ArrayList<Supply> supplies)
            throws IllegalArgumentException, NullPointerException {
        if (occupant.equals(null)) {
            throw new NullPointerException("Cannot assign supplies to null");
        }
        if (this.occupants.contains(occupant) == false) {
            throw new IllegalArgumentException("The occupant to assign supplies to must be present at this location");
        }
        for (int i = 0; i < supplies.size(); i++) {
            decrementSupply(supplies.get(i));
            occupant.incrementPersonalBelonging(supplies.get(i));
        }
    }

    private void isValidName(String str) throws IllegalArgumentException, NullPointerException {
        if (str.equals(null)) {
            throw new NullPointerException("Name cannot be null");
        } else if (str.length() < MIN_NAME_LENGTH || str.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Name must have a length between 3 and 20");
        } else if (!VALID_NAME_PATTERN.matcher(str).matches()) {
            throw new IllegalArgumentException(
                    "Name does not follow the correct format. Names must be made collections of capitalized words that are only seperated by a single special character ('. -)");
        }
    }

    private void isValidAddress(String str) throws IllegalArgumentException, NullPointerException {
        if (str.equals(null)) {
            throw new NullPointerException("Address cannot be null");
        } else if (str.length() < MIN_NAME_LENGTH || str.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Address must have a length between 3 and 20");
        }
    }

    private void isValidOccupantsArray(ArrayList<DisasterVictim> occupants)
            throws IllegalArgumentException, NullPointerException {
        if (occupants.equals(null)) {
            throw new NullPointerException("Arraylist of occupants cannot be null");
        }
        for (int i = 0; i < occupants.size(); i++) {
            if (occupants.get(i).equals(null)) {
                throw new NullPointerException("Elements of the occupants cannot be null");
            }
            for (int j = 0; j < occupants.size(); j++) {
                if (i == j) {
                    continue;
                }
                if (occupants.get(i).getAssignedSocialID() == occupants.get(j).getAssignedSocialID()) {
                    throw new IllegalArgumentException(
                            "Invalid input when setting the occupants: list of occupants cannot have occupants with the same ID values, these are the primary key of the occupant");
                }
            }
        }
    }

    private void isValidOccupantToAdd(DisasterVictim occupant) throws IllegalArgumentException, NullPointerException {
        if (occupant.equals(null)) {
            throw new NullPointerException("Cannot add a null occupant");
        }
        for (int i = 0; i < occupants.size(); i++) {
            if (occupants.get(i).getAssignedSocialID() == occupant.getAssignedSocialID()) {
                throw new IllegalArgumentException(
                        "Invalid input when adding an occupant: list of occupants cannot have occupants with the same ID values, these are the primary key of the occupant");
            }
        }
    }

    private void isValidSuppliesArray(ArrayList<Supply> supplies)
            throws IllegalArgumentException, NullPointerException {
        if (supplies.equals(null)) {
            throw new NullPointerException("Arraylist of supplies cannot be null");
        }
        for (int i = 0; i < supplies.size(); i++) {
            if (supplies.get(i).equals(null)) {
                throw new NullPointerException("Elements of the supplies cannot be null");
            }
            for (int j = 0; j < supplies.size(); j++) {
                if (i == j) {
                    continue;
                }
                if (supplies.get(i).getType() == supplies.get(j).getType()) {
                    throw new IllegalArgumentException(
                            "Invalid input when setting supplies: list of supplies cannot have supplies with the same name, if supplies have the same name they should be be the same element with a combined quantity");
                }
            }
        }
    }

    private void isValidSupplyToAdd(Supply supply) throws NullPointerException {
        if (supply.equals(null)) {
            throw new NullPointerException("Cannot add a null supply");
        }
    }
}
