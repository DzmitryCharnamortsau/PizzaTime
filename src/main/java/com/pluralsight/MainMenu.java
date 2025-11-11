package com.pluralsight;

import java.util.InputMismatchException;
import java.util.Scanner;

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

    }
    public static void addDrink(){
        DrinkSize size = null;
        DrinkFlavor flavor = null;
        while(size == null){
            System.out.println("Would you like small, medium or large drink?");
            String input = scanner.nextLine().toUpperCase();
            try {
                size = DrinkSize.valueOf(input);
                System.out.println("You selected: " + size);
            } catch (IllegalArgumentException e) {
                System.out.println("Please enter a valid size.");
            }
        }
        while(flavor == null){
            System.out.println("What flavor would you like to choose: " +
                    "\nCherry" +
                    "\nWatermelon" +
                    "\nOrange" +
                    "\nStrawberry" +
                    "\nApple");
            String userInput = scanner.nextLine().toUpperCase();
            try {
                flavor = DrinkFlavor.valueOf(userInput);
                System.out.println("You selected: " + flavor);
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
            System.out.println("How many garlic knots would you like to add? Press 0 to go back");
            try {
                int quantity = scanner.nextInt();
                scanner.nextLine();
                if (quantity == 0) {
                    orderScreen();
                    return;
                }
                if (quantity < 0) {
                    System.out.println("Please enter a positive number or press 0 to cancel");
                    continue;
                }
                GarlicKnots garlicKnots = new GarlicKnots(1.50, quantity);
                System.out.println("You added " + quantity + " garlic knots " + garlicKnots);
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
