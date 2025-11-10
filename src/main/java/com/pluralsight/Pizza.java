package com.pluralsight;

import java.util.List;

public class Pizza {
    private PizzaSize size;
    private CrustType crust;
    private boolean isCrustStuffed;
    private List<Toppings> toppings;
    private double price;

    public Pizza(PizzaSize size, CrustType crust, boolean isCrustStuffed, List<Toppings> toppings, double price) {
        this.size = size;
        this.crust = crust;
        this.isCrustStuffed = isCrustStuffed;
        this.toppings = toppings;
        this.price = price;
    }
}
