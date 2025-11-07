package com.pluralsight;

import java.util.Scanner;

public class MainMenu {
    public static void homeScreen(){
        System.out.println("""
                    ___________________________
         _____________🍕Welcome to Pizza Time!🍕_____________
                    ___________________________
                """);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while(running){
            System.out.println("Press 1 to start a new order");
            System.out.println("Press 0 to exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();
            switch (choice){
                case "1" -> System.out.println("placeholder");
                case "0" -> {
                    running = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid character. Please press 1 to start or 0 to exit");
            }
        }
    }
}
