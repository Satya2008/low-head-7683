package com.masai.UI;

import java.util.List;
import java.util.Scanner;

import com.masai.DAO.CustomerDAO;
import com.masai.DAO.Utils;
import com.masai.Entities.Customer;
import com.masai.Entities.Stock;
import com.masai.Entities.Transaction;
import com.masai.Entities.TransactionType;
import com.masai.Exception.CustomerNotFoundException;
import com.masai.Exception.DuplicateEmailException;
import com.masai.Exception.InsufficientBalanceException;
import com.masai.Exception.InsufficientStockException;
import com.masai.Exception.SomethingWentWrong;
import com.masai.Exception.StockNotFoundException;
import com.masai.Services.CustomerServices;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class customerUI {

	public static void displayCustomerMenu() throws InsufficientBalanceException {
		CustomerDAO customer = new CustomerDAO();
		Stock st = new Stock();
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		do {
		System.out.println("1. View All Stock");
		System.out.println("2. Buy Stock");
//		we have to add sell method also
		System.out.println("3. Wallet Balance");
		System.out.println("4. Transaction History");
		System.out.println("5  Add Money in Wallet");
		System.out.println("6. Withdraw money from Wallet");
		System.out.println("7. Delete Account");
		System.out.println("8.- log Out");
		System.out.println("0. Exit");
		System.out.println("Enter your preference");
		choice = sc.nextInt();
		switch(choice) {
		case 1: 
			System.out.println("***********************************************************************************");
			 customer.viewAllStocks();
			 System.out.println("***********************************************************************************");
			break;
		case 2:
			System.out.println("Enter customer ID");
			int idForBuy = sc.nextInt();
			System.out.println("Enter Stock ID");
			int StockId =sc.nextInt();
			System.out.println("Enter Quantity");
			int QunatityForBuy = sc.nextInt();
			System.out.println("***********************************************************************************");
			try {
				customer.buyStock(idForBuy, StockId, QunatityForBuy);
			} catch (StockNotFoundException | InsufficientStockException | InsufficientBalanceException e) {
				
				e.printStackTrace();
			}
			System.out.println("***********************************************************************************");
			break;
			
			
			
		case 3: System.out.println("Enter customer ID");
		int idForBalance = sc.nextInt();
		System.out.println("***********************************************************************************");
			 customer.WalletBalance(idForBalance);
			 System.out.println("***********************************************************************************");
			break;
		case 4:
		
		    System.out.println("Enter customer id:");
		    int customerId = sc.nextInt();
		    System.out.println("***********************************************************************************");
		    List<Transaction> transactions = customer.getTransactionHistory(customerId);
		    for (Transaction transaction : transactions) {
		        if (transaction.getTransactionType() == TransactionType.BUY) {
		            transaction.printStockDetails();
		        }
		    }
		    
		    System.out.println("***********************************************************************************");
		    break;
			
			
			
		case 5: 
			System.out.println("Enter customer ID");
			int id = sc.nextInt();
			System.out.println("Enter amount");
			double amount = sc.nextDouble();
			System.out.println("***********************************************************************************");
			customer.addMoneyToWallet(id, amount);
			System.out.println("amount added successfully");
			System.out.println("***********************************************************************************");
			break;
			
			
		case 6: 
			System.out.println("Enter customer ID");
			int idForwith = sc.nextInt();
			System.out.println("Enter amount");
			double amountforWith = sc.nextDouble();
			System.out.println("***********************************************************************************");
			customer.withdrawMoneyFromWallet(idForwith, amountforWith);
			System.out.println("amount withdrwa successfully");
			System.out.println("***********************************************************************************");
			break;
			
			
		case 7: 
			System.out.println("Enter customer ID");
			int idFordel = sc.nextInt();
			System.out.println("***********************************************************************************");
		    customer.deleteCustomer(idFordel);
		    System.out.println("***********************************************************************************");;
		    break;
		    
		    
		    
		case 8:
			System.out.println("***********************************************************************************");
			break;
			
			
		 case 0:
			 System.out.println("***********************************************************************************");
	          System.out.println("Thanks for using the services");
	          System.out.println("***********************************************************************************");
	          break;
	        default:
	        	System.out.println("***********************************************************************************");
	          System.out.println("Invalid Selection, try again");
	          System.out.println("***********************************************************************************");
		}
		} while(choice!=0);
	}

	public static void customerLogIn(Scanner sc) {
	    System.out.print("Enter your User Name : ");
	    String username = sc.next();
	    System.out.print("Enter your password : ");
	    String password = sc.next();

	   
	    EntityManager em = Utils.getEntityManager();

	    try {
	        Query query = em.createQuery("SELECT c FROM Customer c WHERE c.userName = :username AND c.password = :password");
	        query.setParameter("username", username);
	        query.setParameter("password", password);
	        List<Customer> customers = query.getResultList();

	        if (customers.isEmpty()) {
	            System.out.println("Invalid Credentials");
	        } else {
	        	System.out.println("Log in Successfully");
	            Customer customer = customers.get(0);
	            try {
					displayCustomerMenu();
				} catch (InsufficientBalanceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    } finally {
	        em.close();
	       
	    }
	}
	
	public void customerSignUp(Scanner sc) {
		Customer customer = new Customer();
		CustomerDAO cust= new CustomerDAO();
	    System.out.print("Enter Your name : ");
	    String name = sc.next();
	    System.out.print("Enter Your User name : ");
	    String username = sc.next();
	    EntityManager em = Utils.getEntityManager();
	    try {
	        Query query = em.createQuery("SELECT COUNT(c) FROM Customer c WHERE c.userName = :username");
	        query.setParameter("username", username);
	        long count = (long) query.getSingleResult();
	        if (count > 0) {
	            System.out.println("Username already exists. Please choose a different one.");
	            return;
	        }
	        System.out.print("Enter your Password : ");
	        String password = sc.next();
	        System.out.print("Enter your Mobile No : ");
	        String mobile = sc.next();
	        System.out.print("Enter your Email : ");
	        String email = sc.next();
	        System.out.print("Enter your Address : ");
	        String address = sc.next();
	        customer.setName(name);
	        customer.setUserName(username);
	        customer.setPassword(password);
	        customer.setMobileNo(mobile);
	        customer.setEmail(email);
	        customer.setAddress(address);
	        customer.setWalletBalance(0.0);
	        cust.signUpCustomer(customer);
	        
	    } catch (Exception e) {
	        System.out.println(e.getMessage() + " " + " unable to SingUP");
	    } finally {
	        em.close();
	    }
	}


}
