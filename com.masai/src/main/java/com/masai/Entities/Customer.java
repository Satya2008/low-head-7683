package com.masai.Entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="customers")
public class Customer {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int custId;

private String name;

@Column(name = "username", unique = true, nullable = false )
private String userName;

@Column(name = "password", nullable = false)
 private String password;

private String Address;

@Column(name = "mobile_no", unique = true, nullable = false )
private String MobileNo;

@Column(name = "Email", unique = true, nullable = false )
private String Email;

@Column(name = "wallet_balance")
private double walletBalance;

@OneToMany(mappedBy = "customer", cascade=CascadeType.ALL)
private List<Transaction> transaction;

@ManyToMany(cascade = CascadeType.ALL)
@JoinTable(name = "customer_stock", 
joinColumns = @JoinColumn(name="custome_id"), 
inverseJoinColumns = @JoinColumn(name="stock_id"))
	private List<Stock> stocks;

public Customer() {
	super();
	// TODO Auto-generated constructor stub
}

public Customer(String name, String userName, String password, String address, String mobileNo,
		String email, double walletBalance, List<Transaction> transaction, List<Stock> stocks) {
	super();
	
	this.name = name;
	this.userName = userName;
	this.password = password;
	Address = address;
	MobileNo = mobileNo;
	Email = email;
	this.walletBalance = walletBalance;
	this.transaction = transaction;
	this.stocks = stocks;
}

@Override
public String toString() {
	return "Customer [custId=" + custId + ", name=" + name + ", userName=" + userName + ", password=" + password
			+ ", Address=" + Address + ", MobileNo=" + MobileNo + ", Email=" + Email + ", walletBalance="
			+ walletBalance + ", transaction=" + transaction + ", stocks=" + stocks + "]";
}

public int getCustId() {
	return custId;
}

public void setCustId(int custId) {
	this.custId = custId;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getAddress() {
	return Address;
}

public void setAddress(String address) {
	Address = address;
}

public String getMobileNo() {
	return MobileNo;
}

public void setMobileNo(String mobileNo) {
	MobileNo = mobileNo;
}

public String getEmail() {
	return Email;
}

public void setEmail(String email) {
	Email = email;
}

public double getWalletBalance() {
	return walletBalance;
}

public void setWalletBalance(double walletBalance) {
	this.walletBalance = walletBalance;
}

public List<Transaction> getTransaction() {
	return transaction;
}

public void setTransaction(List<Transaction> transaction) {
	this.transaction = transaction;
}

public List<Stock> getStocks() {
	return stocks;
}

public void setStocks(List<Stock> stocks) {
	this.stocks = stocks;
}



}
