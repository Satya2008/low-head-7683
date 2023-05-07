package com.masai.UI;

import java.util.List;
import java.util.Scanner;

import com.masai.DAO.CustomerDAO;
import com.masai.DAO.Utils;
import com.masai.Entities.Customer;
import com.masai.Exception.DuplicateEmailException;
import com.masai.Services.CustomerServices;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class customerUI {

	public static void displayCustomerMenu() {
		System.out.println("1. View All Stock");
		System.out.println("2. Buy Stock");
		System.out.println("3. Sell Stock");
		System.out.println("4. Transaction History");
		System.out.println("5   Add Money in Wallet");
		System.out.println("6. Withdraw money from Wallet");
		System.out.println("7. Delete Account");
		System.out.println("8. log Out");
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
	            displayCustomerMenu();
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