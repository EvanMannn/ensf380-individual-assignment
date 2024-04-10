package edu.ucalgary.oop;

import java.util.regex.*;

public class Supply {
    private String type;
    private Integer quantity;

    private static final int MIN_TYPE_LENGTH = 3;
    private static final int MAX_TYPE_LENGTH = 20;

    private static final int MIN_QUANTITY = 0;
    private static final int MAX_QUANTITY = 200;

    private static final Pattern VALID_TYPE_PATTERN = Pattern
            .compile("^[\\p{Lu}][\\p{Ll}]*((\\.| |-|')[\\p{Lu}][\\p{Ll}]*)*$");

    // Constructor
    public Supply(String type, Integer quantity) throws IllegalArgumentException, NullPointerException {
        this.setType(type);
        this.setQuantity(quantity);
    }

    // type Methods
    public void setType(String type) throws IllegalArgumentException, NullPointerException {
        isValidType(type);
        ;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    // quantity Methods

    public void setQuantity(int quantity) throws IllegalArgumentException, NullPointerException {
        isValidQuantity(quantity);
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    private void isValidQuantity(Integer quantity) throws IllegalArgumentException, NullPointerException {
        if (quantity.equals(null)) {
            throw new NullPointerException("Quantity cannot be null");
        } else if (quantity < MIN_QUANTITY || quantity > MAX_QUANTITY) {
            throw new IllegalArgumentException("Quantity must be between 0 and 200");
        }
    }

    private void isValidType(String type) throws IllegalArgumentException, NullPointerException {
        if (type.equals(null)) {
            throw new NullPointerException("Type cannot be null");
        } else if (type.length() < MIN_TYPE_LENGTH || type.length() > MAX_TYPE_LENGTH) {
            throw new IllegalArgumentException("Type must have a length between 3 and 20");
        } else if (!VALID_TYPE_PATTERN.matcher(type).matches()) {
            throw new IllegalArgumentException(
                    "Type does not follow the correct format. Names must be made collections of capitalized words that are only seperated by a single special character ('. -)");
        }
    }
}
