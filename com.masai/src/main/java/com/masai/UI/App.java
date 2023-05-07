package com.masai.UI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.masai.DAO.CustomerDAO;
import com.masai.DAO.Utils;
import com.masai.Entities.Customer;
import com.masai.Entities.Stock;
import com.masai.Entities.Transaction;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class App 
{

	public static void displayBrokerMenu() {
		System.out.println("1. View All Customers");
		System.out.println("2. Add new Stock");
		System.out.println("3. View All Stock");
		System.out.println("4. View consolidated report of a stock");
		System.out.println("5   Delete Customer");
		System.out.println("6.  Delete Stock");
		System.out.println("8. log Out");
	}
	 

	
    public static void main( String[] args ) throws Exception
    {
    	Scanner sc = new Scanner(System.in);
    	customerUI customer = new customerUI();
    	customer.customerSignUp(sc);

     
    }


}