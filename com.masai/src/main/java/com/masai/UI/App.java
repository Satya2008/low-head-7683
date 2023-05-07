package com.masai.UI;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int choice = 0;
        boolean isLoggedInAsBroker = false;

        do {
            if (isLoggedInAsBroker) {
                BrokerUI br = new BrokerUI();
                br.displayBrokerMenu();
                break;
            }

            System.out.println("1. Broker");
            System.out.println("2. Customer");         
            System.out.println("0. Exit");
            System.out.print("Enter Selection: ");
            choice = sc.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter Broker User Name: ");
                    String user = sc.next();
                    System.out.print("Enter Broker Password: ");
                    String password = sc.next();
                    if (user.equals("admin") && password.equals("admin")) {
                        System.out.println("Successfully logIn as Broker");
                        isLoggedInAsBroker = true;
                    } else {
                        System.out.println("Wrong Credentials, try again");
                    }
                    break;
                case 2:
                    customerUI cust = new customerUI();
                    int ch = 0;
                    do {
                        System.out.println("1. LogIn");
                        System.out.println("2. SingUp");         
                        System.out.println("0. Exit");
                        System.out.print("Enter Selection: ");
                        ch = sc.nextInt();
                        switch(ch) {
                            case 1: 
                                cust.customerLogIn(sc);
                                break;
                            case 2:
                                cust.customerSignUp(sc);
                                cust.customerLogIn(sc);
                                break;
                        }
                    } while (ch != 0);
                    break;
                case 0:
                    System.out.println("Thanks for using the services");
                    break;
                default:
                    System.out.println("Invalid Selection, try again");
            }
        } while (choice != 0);

        sc.close();
    }
}