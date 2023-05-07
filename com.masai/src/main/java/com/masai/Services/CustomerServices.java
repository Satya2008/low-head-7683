package com.masai.Services;



import java.util.List;

import com.masai.Entities.Customer;
import com.masai.Entities.Transaction;
import com.masai.Exception.CustomerNotFoundException;
import com.masai.Exception.DuplicateEmailException;
import com.masai.Exception.InsufficientBalanceException;
import com.masai.Exception.InsufficientStockException;
import com.masai.Exception.NullPointerException;
import com.masai.Exception.SomethingWentWrong;
import com.masai.Exception.StockNotFoundException;



public interface CustomerServices {

    public void signUpCustomer(Customer customer) throws DuplicateEmailException;

//    public Customer getCustomerById(int custID) throws SomethingWentWrong ;

//    public Customer getCustomerByUsername(String username) throws SomethingWentWrong, Exception;
     
    public void viewAllStocks();

    public void addMoneyToWallet(int customerId, double amount) throws SomethingWentWrong;

    public void withdrawMoneyFromWallet(int customerId, double amount) throws InsufficientBalanceException;

    public void buyStock(int customerId, String stockName, int quantity) throws StockNotFoundException, InsufficientStockException, InsufficientBalanceException;

    public void sellStock(int customerId, String stockName, int quantity) throws StockNotFoundException, InsufficientStockException;

    public List<Transaction> getTransactionHistory(int customerId);

	public void deleteCustomer(int customerId);
    
   

}