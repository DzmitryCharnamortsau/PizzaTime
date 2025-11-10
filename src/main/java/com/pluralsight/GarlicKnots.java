package com.pluralsight;

public class GarlicKnots {

    private double price;
    private int quantity;

    public GarlicKnots(Double price, int quantity){
        this.price = 1.50;
        this.quantity = quantity;
    }

    public double getPrice() {
        return price*quantity;
    }
}
