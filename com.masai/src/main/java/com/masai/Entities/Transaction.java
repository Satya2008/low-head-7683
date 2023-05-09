package com.masai.Entities;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tranID;
	

	private TransactionType transactionType;
	
	private int quantity;
	
	private double price;
	
	private LocalDate transactionDate;
	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "stock_id")
	private Stock stock;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction( TransactionType transactionType, int quantity, double price, 
			Customer customer, Stock stock) {
		super();
		
		this.transactionType = transactionType;
		this.quantity = quantity;
		this.price = price;
//		this.transactionDate = transactionDate;
		this.customer = customer;
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Transaction [tranID=" + tranID + ", transactionType=" + transactionType + ", quantity=" + quantity
				+ ", price=" + price + ",  customer=" + customer + ", stock="
				+ stock + "]";
	}

	public int getTranID() {
		return tranID;
	}

	public void setTranID(int tranID) {
		this.tranID = tranID;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}
	
	
	public void printStockDetails() {
	    if (this.transactionType == TransactionType.BUY) {
	        System.out.println("Stock Name = " + this.stock.getName() + " - "+"Stock Price = "+ + this.stock.getPrice() + " - " + "Quantity  = "+ this.stock.getQuantity());
	    }
	}
	
	
}
