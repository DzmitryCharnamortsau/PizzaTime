package com.pluralsight;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Pizza> pizzas = new ArrayList<>();
    private static final List<Drink> drinks = new ArrayList<>();
    private static final List<GarlicKnots> knots = new ArrayList<>();
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
        boolean ordering = true;
        while(ordering){
            System.out.println("""
            ----------------------------------
                 🍕 Pizza Time Order Menu 🍕
            ----------------------------------
            1 - Add Pizza🍕
            2 - Add Drink🧃
            3 - Add Garlic Knots🧄
            4 - Checkout🛒
            0 - Cancel Order❌
            """);
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();
            switch(choice){
                case "1" -> addPizza();
                case "2" -> addDrink();
                case "3" -> addKnots();
                case "4" -> {
                    checkOut();
                    ordering = false;
                }
                case "0" -> {
                    System.out.println("Order canceled. Returning to the main menu...");
                    ordering = false;
                }
                default -> System.out.println("Invalid character! Please select an option from the menu.");
            }
        }
        return;
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
        pizzas.add(pizza);
    }
    public static void addDrink(){
        DrinkSize size = null;
        DrinkFlavor flavor = null;
        while(size == null){
            System.out.println("Would you like small, medium or large drink?");
            System.out.println("Press 0 to go back");
            String input = scanner.nextLine().toUpperCase();
            if (input.equals("0")) {
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
        drinks.add(drink);
    }
    public static void addKnots(){
        while(true){
            System.out.println("How many garlic knots would you like to add?");
            System.out.println("Press 0 to go back");
            try {
                int quantity = scanner.nextInt();
                scanner.nextLine();
                if (quantity == 0) {
                    return;
                }
                if (quantity < 0) {
                    System.out.println("Please enter a positive number or press 0 to go back");
                    continue;
                }
                GarlicKnots garlicKnots = new GarlicKnots(1.50, quantity);
                System.out.println("You added " + quantity + " garlic knots. " + garlicKnots);
                knots.add(garlicKnots);
                break;
            } catch (InputMismatchException e){
                System.out.println("Invalid input! Please enter a valid number");
                scanner.nextLine();
            }
        }
    }
    public static void checkOut(){
        if (pizzas.isEmpty() && drinks.isEmpty() && knots.isEmpty()) {
            System.out.println("You have no items in your order.");
            return;
        }
        boolean running = true;
        while (running) {
            System.out.println("\nYour current order is: ");
            for (Pizza p : pizzas) {
                System.out.printf("Pizza: %s (%s crust%s) - $%.2f%n",
                        p.getSize().getName(),
                        p.getCrust().getName(),
                        p.isCrustStuffed() ? ", stuffed" : "",
                        p.getFullPrice());
                System.out.println("  Toppings:");
                for (Toppings t : p.getToppings()) {
                    System.out.printf("    - %s%s%n",
                            t.getName(),
                            t.isExtra() ? " (extra)" : "");
                }
                System.out.println("  Sauces: " +
                        String.join(", ", p.getSauces().stream()
                                .map(Sauces::toString)
                                .toList()));
                System.out.println("  Sides: " +
                        String.join(", ", p.getSides().stream()
                                .map(Sides::toString)
                                .toList()));
            }
            for (Drink d : drinks) {
                System.out.printf("Drink: %s %s - $%.2f%n",
                        d.getSize().getName(), d.getFlavor().getName(), d.getPrice());
            }
            for (GarlicKnots g : knots) {
                System.out.printf("Garlic Knots x%d - $%.2f%n",
                        g.getQuantity(), g.getPrice());
            }
            Order order = new Order(pizzas, drinks, knots, 0);
            System.out.printf("%nTotal Price: $%.2f%n", order.getTotalPrice());
            System.out.println("Press 1 to confirm your order");
            System.out.println("Press 2 to cancel");
            String choice = scanner.nextLine();
            switch(choice){
                case "1" -> {
                    saveReceipt(order);
                    System.out.println("✔Order confirmed! Your receipt has been saved.");
                    pizzas.clear();
                    drinks.clear();
                    knots.clear();
                    running = false;
                }
                case "2" ->{
                    System.out.println("Order canceled. Returning to the main menu...");
                    pizzas.clear();
                    drinks.clear();
                    knots.clear();
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please enter 1 or 2");
            }
        }
    }
    private static void saveReceipt(Order order){
        try {
            File file = new File("Receipts");
            if (!file.exists()) {
                file.mkdirs();
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
            String fileName = "Receipts/" + LocalDateTime.now().format(formatter) + ".txt";
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
                writer.write("🍕PizzaTime Receipt🍕\n");
                writer.write("____________________\n");
                if (!pizzas.isEmpty()) {
                    writer.write("Pizza:\n");
                    for (Pizza p : pizzas) {
                        writer.write(String.format("- %s (%s crust%s) $%.2f\n",
                                p.getSize().getName(),
                                p.getCrust().getName(),
                                p.isCrustStuffed() ? ", stuffed" : "",
                                p.getFullPrice()));
                        writer.write("Toppings:\n");
                        for (Toppings t : p.getToppings()) {
                            writer.write(String.format("   - %s%s%n",
                                    t.getName(),
                                    t.isExtra() ? " (extra)" : ""));
                        }
                        writer.write("Sauces: " + String.join(", ",
                                p.getSauces().stream()
                                        .map(Sauces::toString)
                                        .toList()) + "\n");
                        writer.write("Sides: " + String.join(", ",
                                p.getSides().stream()
                                        .map(Sides::toString)
                                        .toList()) + "\n");
                    }
                    writer.write("\n");
                }
                if (!drinks.isEmpty()) {
                    writer.write("Drinks:\n");
                    for (Drink d : drinks) {
                        writer.write(String.format("- %s %s $%.2f\n",
                                d.getSize().getName(),
                                d.getFlavor().getName(),
                                d.getPrice()));
                    }
                    writer.write("\n");
                }
                if (!knots.isEmpty()) {
                    writer.write("Garlic Knots:\n");
                    for (GarlicKnots g : knots) {
                        writer.write(String.format("- %d knots $%.2f\n",
                                g.getQuantity(), g.getPrice()));
                    }
                    writer.write("\n");
                }
                writer.write(String.format("Total Price: $%.2f\n", order.getTotalPrice()));
                writer.write("Thank you for your order! See you next time🍕\n");
            }
        }
        catch(IOException e){
            System.out.println("Error saving receipt");
        }
    }
}
