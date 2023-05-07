package com.masai.Services;

import java.util.List;

import com.masai.Entities.Transaction;

public interface TransactionService {
	 public void addTransaction(Transaction transaction);
	 
	 public List<Transaction> getTransactionsByCustomerId(int customerId);
}
