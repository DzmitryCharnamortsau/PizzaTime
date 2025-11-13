package com.pluralsight;

public enum DrinkSize {
    SMALL(2.00, "Small"),
    MEDIUM(2.50, "Medium"),
    LARGE(3.00, "Large");

    private final double price;
    private final String name;

    DrinkSize(double price, String name) {
        this.price = price;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
