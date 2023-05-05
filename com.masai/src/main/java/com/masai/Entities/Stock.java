package com.masai.Entities;



import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stockId;
	
	@Column(name = "name", unique = true, nullable = false)
	private String name;
	
	private int quantity;
	
	private double price;
	
	@ManyToMany(mappedBy = "stocks")
	private List<Customer> customers;

	public Stock() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Stock(String name, int quantity, double price, List<Customer> customers) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.customers = customers;
	}

	@Override
	public String toString() {
		return "Stock [stockId=" + stockId + ", name=" + name + ", quantity=" + quantity + ", price=" + price
				+ ", customers=" + customers + "]";
	}

	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
	
}
