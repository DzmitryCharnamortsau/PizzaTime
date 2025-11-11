package com.pluralsight;

public enum Sauces {
    MARINARA("Marinara"),
    ALFREDO("Alfredo"),
    PESTO("Pesto"),
    BBQ("BBQ"),
    BUFFALO("Buffalo"),
    OLIVE_OIL("Olive oil");
    private final String name;

    Sauces(String name) {
        this.name = name;
    }
    @Override
    public String toString(){
        return name;
    }
}
