package com.pluralsight;

import java.util.List;

public class Pizza {
    private PizzaSize size;
    private CrustType crust;
    private boolean isCrustStuffed;
    private List<Toppings> toppings;
    private List<Sauces> sauces;
    private List<Sides> sides;
    private double price;

    public Pizza(PizzaSize size, CrustType crust, boolean isCrustStuffed, List<Toppings> toppings,
                 List<Sauces> sauces, List<Sides> sides, double price) {
        this.size = size;
        this.crust = crust;
        this.isCrustStuffed = isCrustStuffed;
        this.toppings = toppings;
        this.sauces = sauces;
        this.sides = sides;
        this.price = price;
    }

    public PizzaSize getSize() {
        return size;
    }

    public double getFullPrice() {
        return price;
    }
}
