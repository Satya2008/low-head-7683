package com.masai.UI;

import java.util.List;
import java.util.Scanner;

import com.masai.DAO.BrokerDAO;
import com.masai.Entities.Customer;
import com.masai.Entities.Stock;

public class BrokerUI {


	
	public static void displayBrokerMenu() {
		 Scanner sc = new Scanner(System.in);
		 BrokerDAO br = new BrokerDAO();
	    int choice = 0;
	    do {
	        System.out.println("1. View All Customers");
		    System.out.println("2. Add new Stock");
		    System.out.println("3. View All Stock");
		    System.out.println("4. View consolidated report of a stock");
		    System.out.println("5. Delete Customer");
		    System.out.println("6. Delete Stock");
		    System.out.println("7. Log Out");
		    System.out.println("Enter your preference");
		    choice = sc.nextInt();
		    switch(choice) {
		    case 1: 
		    	List<Customer> customer = br.getAllCustomers();
		    	for(Customer c : customer) {
		    		System.out.println(c);
		    	}
		    	break;
		    case 2:
		    	System.out.println("Enter stock name");
		    	String name = sc.next();
		    	System.out.println("Enter stock quantity");
		    	int qunatity=  sc.nextInt();
		    	System.out.println("Enter stock price");
		    	double price = sc.nextDouble();
		    
		    br.addStock(name, qunatity, price);
		    	
		    	
		    	break;
		    case 3: 
		    	List<Stock> stock = br.getAllStocks();
		    	for(Stock s : stock) {
		    		System.out.println(s);
		    	}
		    	break;
		    case 4: 
		    	System.out.print("Enter Stock Name: ");
                String st = sc.next();

                Stock s = br.getStock(st);
                if (s != null) {
                    System.out.println("Consolidated report for " + st);
                    System.out.println(s);
                } else {
                    System.out.println("No stock found with name " + st);
                }
                break;
		    	
		    case 5: 
		    	System.out.print("Enter customer ID to delete: ");
		          int customerId = sc.nextInt();
		          br.deleteCustomer(customerId);
		          break;
		    	
		    case 6: 
		    	System.out.print("Enter stock name to delete: ");
		          String stockNameToDelete = sc.next();
		          Stock stockToDelete = br.getStock(stockNameToDelete);
		          br.deleteStock(stockToDelete);
		          break;
		    	
		    case 7: 
//		    	System.out.println("logged out Successfully");
		    	break;
		    
		    }
		    
	    } while(choice!=0);
	    
	    
	    
	}
	  
	}


