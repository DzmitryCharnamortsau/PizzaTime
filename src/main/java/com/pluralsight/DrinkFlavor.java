package com.pluralsight;

public enum DrinkFlavor {
    CHERRY("Cherry"),
    WATERMELON("Watermelon"),
    ORANGE("Orange"),
    STRAWBERRY("Strawberry"),
    APPLE("Apple");
    private final String name;
    DrinkFlavor(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
