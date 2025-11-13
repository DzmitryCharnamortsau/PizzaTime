package com.pluralsight;

public enum CrustType {
    THIN("Thin"),
    REGULAR("Regular"),
    THICK("Thick"),
    CAULIFLOWER("Cauliflower");

    private final String name;

    public String getName() {
        return name;
    }

    CrustType(String name){
        this.name = name;
    }
}
