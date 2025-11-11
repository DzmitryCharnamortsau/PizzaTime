package com.pluralsight;

public enum ToppingType {
    //meats
    PEPPERONI("Pepperoni", "meat", 1.00, 2.00, 3.00, 0.50, 1.00, 1.50),
    SAUSAGE("Sausage", "meat", 1.00, 2.00, 3.00, 0.50, 1.00, 1.50),
    HAM("Ham", "meat", 1.00, 2.00, 3.00, 0.50, 1.00, 1.50),
    BACON("Bacon", "meat", 1.00, 2.00, 3.00, 0.50, 1.00, 1.50),
    CHICKEN("Chicken", "meat", 1.00, 2.00, 3.00, 0.50, 1.00, 1.50),
    MEATBALL("Meatball", "meat", 1.00, 2.00, 3.00, 0.50, 1.00, 1.50),
    //cheeses
    MOZZARELLA("Mozzarella", "cheese", 0.75, 1.50, 1.25, 0.30, 0.60, 0.90),
    BUFFALO("Buffalo", "cheese", 0.75, 1.50, 1.25, 0.30, 0.60, 0.90),
    PARMESAN("Parmesan", "cheese", 0.75, 1.50, 1.25, 0.30, 0.60, 0.90),
    RICOTTA("Ricotta", "cheese", 0.75, 1.50, 1.25, 0.30, 0.60, 0.90),
    GOAT_CHEESE("Goat cheese", "cheese", 0.75, 1.50, 1.25, 0.30, 0.60, 0.90),
    //regular
    ONIONS("Onions", "regular"),
    MUSHROOMS("Onions", "regular"),
    BELL_PEPPERS("Onions", "regular"),
    OLIVES("Onions", "regular"),
    TOMATOES("Onions", "regular"),
    SPINACH("Onions", "regular"),
    BASIL("Onions", "regular"),
    PINEAPPLE("Onions", "regular"),
    ANCHOVIES("Onions", "regular");


    private final String name;
    private final String type;
    private final double personal8, medium12, large16;
    private final double extra8, extra12, extra16;

    ToppingType(String name, String type, double personal8, double medium12,
                double large16, double extra8, double extra12, double extra16) {
        this.name = name;
        this.type = type;
        this.personal8 = personal8;
        this.medium12 = medium12;
        this.large16 = large16;
        this.extra8 = extra8;
        this.extra12 = extra12;
        this.extra16 = extra16;
    }

    ToppingType(String name, String type) {
        this(name, type, 0, 0, 0, 0, 0,0);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getPrice(String size){
        return switch (size.toLowerCase()) {
            case "personal", "8" -> personal8;
            case "medium", "12" -> medium12;
            case "large", "16" -> large16;
            default -> 0;
        };
    }
    public double getExtraPrice(String size){
        return switch (size.toLowerCase()) {
            case "personal", "8" -> extra8;
            case "medium", "12" -> extra12;
            case "large", "16" -> extra16;
            default -> 0;
        };
    }
    @Override
    public String toString(){
        return name;
    }
}
