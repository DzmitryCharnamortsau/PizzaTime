package com.pluralsight;

public class Toppings {
    private String name;
    private ToppingType type;
    private boolean extra;

    public Toppings(String name, ToppingType type, boolean extra) {
        this.name = name;
        this.type = type;
        this.extra = extra;
    }

    public String getName() {
        return name;
    }

    public ToppingType getType() {
        return type;
    }

    public boolean isExtra() {
        return extra;
    }

    public void setExtra(boolean extra) {
        this.extra = extra;
    }

    public double totalCost(String pizzaSize){
        if (extra){
            return type.getPrice(pizzaSize) + getType().getExtraPrice(pizzaSize);
        }
        else return type.getPrice(pizzaSize);
    }
}
