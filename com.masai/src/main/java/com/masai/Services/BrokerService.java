package com.masai.Services;

import java.util.List;

import com.masai.Entities.Customer;
import com.masai.Entities.Stock;

public interface BrokerService {
	
	   public void login(String username, String password);
	   
	   public List<Customer> getAllCustomers();
	   
	   public void addStock(String stockName, int quantity, double price);
	   
	   public List<Stock> getAllStocks();
	   
	   public Stock getStock(String stockName); 
	   
	   public void deleteCustomer(int customerID);
	   
	   public void deleteStock(Stock stock);
	   
	   public void logout();
	   
	   Customer getCustomer(int customerID); 
	}

