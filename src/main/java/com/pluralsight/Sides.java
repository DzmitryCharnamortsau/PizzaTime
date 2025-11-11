package com.pluralsight;

public enum Sides {
    RED_PEPPER("Red pepper"),
    PARMESAN("Parmesan");
    private final String name;

    Sides(String name) {
        this.name = name;
    }
    @Override
    public String toString(){
        return name;
    }
}
