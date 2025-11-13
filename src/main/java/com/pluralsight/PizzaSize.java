package com.pluralsight;

public enum PizzaSize {
    PERSONAL(8.50, "Personal"),
    MEDIUM(12.00, "Medium"),
    LARGE(16.50, "Large");

    private final double price;
    private final String name;

    PizzaSize(double price, String name) {
        this.price = price;
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}

