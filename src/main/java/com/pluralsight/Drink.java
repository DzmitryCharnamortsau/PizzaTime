package com.pluralsight;

public class Drink {
    private DrinkSize size;
    private DrinkFlavor flavor;
    private double price;

    public Drink(DrinkSize size, DrinkFlavor flavor, double price) {
        this.size = size;
        this.flavor = flavor;
        this.price = price;
    }
    @Override
    public String toString(){
        return size.getName() + " " + flavor.getName() + " drink. The price is $" + String.format("%.2f", price);
    }
}
