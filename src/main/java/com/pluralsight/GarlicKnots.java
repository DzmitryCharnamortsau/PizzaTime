package com.pluralsight;

public class GarlicKnots {

    private double price;
    private int quantity;

    public GarlicKnots(Double price, int quantity){
        this.price = 1.50;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price*quantity;
    }
    @Override
    public String toString(){
        return "The price is $" + String.format("%.2f", getPrice());
    }
}
