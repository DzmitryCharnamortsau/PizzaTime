package com.pluralsight;

import java.util.List;

public class Order {
    private List<Pizza> pizza;
    private List<Drink> drink;
    private List<GarlicKnots> knots;
    private double totalPrice;

    public Order(List<Pizza> pizza, List<Drink> drink, List<GarlicKnots> knots, double totalPrice) {
        this.pizza = pizza;
        this.drink = drink;
        this.knots = knots;
        this.totalPrice = calculateTotal();
    }
    private double calculateTotal(){
        double total = 0;
        for (Pizza p : pizza) total += p.getFullPrice();
        for (Drink d : drink) total += d.getPrice();
        for (GarlicKnots g : knots) total += g.getPrice();
        return total;
    }
    public double getTotalPrice(){
        return totalPrice;
    }
}
