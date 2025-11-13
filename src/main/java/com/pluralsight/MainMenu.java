package com.pluralsight;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainMenu {
    private static final Scanner scanner = new Scanner(System.in);
    public static void homeScreen(){
        System.out.println("""
                    ___________________________
         _____________🍕Welcome to Pizza Time!🍕_____________
                    ___________________________
                """);
        boolean running = true;
        while(running){
            System.out.println("Press 1 to start a new order");
            System.out.println("Press 0 to exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();
            switch (choice){
                case "1" -> orderScreen();
                case "0" -> {
                    running = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid character. Please press 1 to start or 0 to exit");
            }
        }
    }
    public static void orderScreen(){
        boolean running = true;
        while(running){
            System.out.println("Press 1 to add pizza🍕");
            System.out.println("Press 2 to add drink🧃");
            System.out.println("Press 3 to add garlic knots🧄");
            System.out.println("Press 4 to checkout🛒");
            System.out.println("Press 0 to cancel your order❌");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();
            switch(choice){
                case "1" -> addPizza();
                case "2" -> addDrink();
                case "3" -> addKnots();
                case "4" -> checkOut();
                case "0" -> {
                    System.out.println("Order canceled. Returning to the main menu");
                    running = false;
                    homeScreen();
                }
                default -> System.out.println("Invalid character!");
            }
        }
    }
    public static void addPizza(){
        CrustType crustType = null;
        while(crustType == null){
            System.out.println("Please select the type of crust from the following or press 0 to go back: " +
                    "\n-thin" +
                    "\n-regular" +
                    "\n-thick" +
                    "\n-cauliflower");
            String input = scanner.nextLine().toUpperCase();
            if (input.equals("0")) {
                orderScreen();
                return;
            }
            try{
                crustType = CrustType.valueOf(input);
                System.out.println("You selected: " + crustType.getName() + " crust");
            } catch (IllegalArgumentException e) {
                System.out.println("Please choose one crust from the list or press 0 to go back");
            }
        }
        boolean stuffedCrust = false;
        while(true){
            System.out.println("Would you like the pizza with stuffed crust?" +
                    "\n-yes" +
                    "\n-no");
            String stuffed = scanner.nextLine();
            if(stuffed.equalsIgnoreCase("yes")){
                System.out.println("You added stuffed crust");
                stuffedCrust = true;
                break;
            } else if(stuffed.equalsIgnoreCase("no")){
                System.out.println("No stuffed crust selected");
                break;
            } else {
                System.out.println("Please enter 'yes' or 'no'");
            }
        }
        PizzaSize pizzaSize = null;
        while(pizzaSize == null){
            System.out.println("Please select the size: " +
                    "\n-personal(8')" +
                    "\n-medium(12')" +
                    "\n-large(16')");
            String input = scanner.nextLine().toUpperCase();
            try{
                pizzaSize = PizzaSize.valueOf(input);
                System.out.println("You selected: " + pizzaSize.getName() + " size");
            } catch (IllegalArgumentException e) {
                System.out.println("Please choose one size from the list");
            }
        }
        List<Toppings> toppings = new ArrayList<>();
        while(toppings.isEmpty()){
            System.out.println("""
                    Please select your toppings:
                    -pepperoni      -mozzarella     -onions         -basil
                    -sausage        -parmesan       -mushrooms      -pineapple
                    -ham            -ricotta        -bell peppers   -anchovies
                    -bacon          -goat cheese    -olives
                    -chicken        -buffalo        -tomatoes
                    -meatball                       -spinach
                    """);
            String input = scanner.nextLine().trim().toUpperCase();
            String[] choices = input.split(",");
            for (String choice : choices){
                try {
                    choice = choice.trim().replace(" ", "_");
                    ToppingType type = ToppingType.valueOf(choice);
                    boolean exists = toppings.stream()
                            .anyMatch(t -> t.getType() == type);
                    if (!exists) {
                        toppings.add(new Toppings(type.getName(), type, false));
                    }
                }
                catch (IllegalArgumentException e){
                    System.out.println("Please select a topping from the list");
                }
            }
        }
        System.out.println("Do you want extra toppings?" +
                "\n-yes" +
                "\n-no");
        String extraResponse = scanner.nextLine().trim().toLowerCase();
        if(extraResponse.equals("yes")){
            System.out.println("Which topping(s) do you want extra?");
            String extraInput = scanner.nextLine().trim().toUpperCase();
            String[] extraChoices = extraInput.split(",");
            for (String choice : extraChoices){
                try {
                    choice = choice.trim().replace(" ", "_");
                    ToppingType extraTopping = ToppingType.valueOf(choice);
                    boolean found = false;
                    for (Toppings t : toppings) {
                        if (t.getType() == extraTopping) {
                            t.setExtra(true);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        toppings.add(new Toppings(extraTopping.getName(), extraTopping, true));
                    }
                    System.out.println("You added extra: " + extraTopping.getName());
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid topping! Please select from the list.");
                }
            }
        }
        System.out.println("You selected:");
        for (Toppings t : toppings) {
            System.out.println("- " + t.getName() + (t.isExtra() ? " (extra)" : ""));
        }
        List<Sauces> sauces = new ArrayList<>();
        while(sauces.isEmpty()) {
            System.out.println("Please select your sauces: " +
                    "\n-marinara" +
                    "\n-alfredo" +
                    "\n-pesto" +
                    "\n-bbq" +
                    "\n-buffalo" +
                    "\n-olive oil");
            String input = scanner.nextLine().trim().toUpperCase();
            String[] choices = input.split(",");
            for (String choice : choices) {
                try {
                    choice = choice.trim().replace(" ", "_");
                    Sauces sauce = Sauces.valueOf(choice);
                    if (!sauces.contains(sauce)) {
                        sauces.add(sauce);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Please select a sauce from the list");
                }
            }
            if (sauces.isEmpty()) {
                System.out.println("No sauces selected. Please try again.\n");
            }
        }
        System.out.println("You selected: " + String.join(", ",
                sauces.stream().map(Sauces::toString).toList()));
        List<Sides> sides = new ArrayList<>();
        while(sides.isEmpty()){
            System.out.println("Please select your sides: " +
                    "\n-red pepper" +
                    "\n-parmesan");
            String input = scanner.nextLine().trim().toUpperCase();
            String[] choices = input.split(",");
            for (String choice : choices) {
                try {
                    choice = choice.trim().replace(" ", "_");
                    Sides side = Sides.valueOf(choice);
                    if (!sides.contains(side)) {
                        sides.add(side);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Please enter a side from the list");
                }
            }
            if (sides.isEmpty()) {
                System.out.println("No sides selected. Please try again.\n");
            }
        }
        System.out.println("You selected: " + String.join(", ",
                sides.stream().map(Sides::toString).toList()));
        double totalPrice = pizzaSize.getPrice();
        for (Toppings t : toppings) {
            totalPrice += t.totalCost(pizzaSize.getName());
        }
        Pizza pizza = new Pizza(pizzaSize, crustType, stuffedCrust, toppings, sauces, sides, totalPrice);
        System.out.println("\n--- Your Pizza Order ---");
        System.out.println("Size: " + pizza.getSize().getName());
        System.out.println("Crust: " + crustType.getName() + (stuffedCrust ? " (stuffed)" : ""));
        System.out.println("Toppings:");
        for (Toppings t : toppings) {
            System.out.println("- " + t.getName() + (t.isExtra() ? " (extra)" : ""));
        }
        System.out.println("Sauces: " + String.join(", ", sauces.stream().map(Sauces::toString).toList()));
        System.out.println("Sides: " + String.join(", ", sides.stream().map(Sides::toString).toList()));
        System.out.printf("Total Price: $%.2f%n", pizza.getFullPrice());
        orderScreen();
    }
    public static void addDrink(){
        DrinkSize size = null;
        DrinkFlavor flavor = null;
        while(size == null){
            System.out.println("Would you like small, medium or large drink?");
            System.out.println("Press 0 to go back");
            String input = scanner.nextLine().toUpperCase();
            if (input.equals("0")) {
                orderScreen();
                return;
            }
            try {
                size = DrinkSize.valueOf(input);
                System.out.println("You selected: " + size.getName());
            } catch (IllegalArgumentException e) {
                System.out.println("Please enter a valid size or press 0 to go back.");
            }
        }
        while(flavor == null){
            System.out.println("What flavor would you like to choose: " +
                    "\nCherry🍒" +
                    "\nWatermelon🍉" +
                    "\nOrange🍊" +
                    "\nStrawberry🍓" +
                    "\nApple🍏");
            String userInput = scanner.nextLine().toUpperCase();
            try {
                flavor = DrinkFlavor.valueOf(userInput);
                System.out.println("You selected: " + flavor.getName());
            } catch (IllegalArgumentException e) {
                System.out.println("Please enter a valid flavor.");
            }
        }
        Drink drink = new Drink(size, flavor, size.getPrice());
        System.out.println("Drink added: " + drink);
        orderScreen();
    }
    public static void addKnots(){
        while(true){
            System.out.println("How many garlic knots would you like to add?");
            System.out.println("Press 0 to go back");
            try {
                int quantity = scanner.nextInt();
                scanner.nextLine();
                if (quantity == 0) {
                    orderScreen();
                    return;
                }
                if (quantity < 0) {
                    System.out.println("Please enter a positive number or press 0 to go back");
                    continue;
                }
                GarlicKnots garlicKnots = new GarlicKnots(1.50, quantity);
                System.out.println("You added " + quantity + " garlic knots. " + garlicKnots);
                break;
            } catch (InputMismatchException e){
                System.out.println("Invalid input! Please enter a valid number");
                scanner.nextLine();
            }
        }
    }
    public static void checkOut(){
        boolean running = true;
        while (running) {
            System.out.println("Press 1 to confirm your order");
            System.out.println("Press 2 to cancel");
            String choice = scanner.nextLine();
            switch(choice){}
        }
    }
}
