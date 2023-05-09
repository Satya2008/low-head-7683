package com.masai.DAO;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.masai.Entities.Customer;
import com.masai.Entities.Stock;
import com.masai.Entities.Transaction;
import com.masai.Entities.TransactionType;
import com.masai.Exception.DuplicateEmailException;
import com.masai.Exception.InsufficientBalanceException;
import com.masai.Exception.InsufficientStockException;
import com.masai.Exception.NullPointerException;
import com.masai.Exception.SomethingWentWrong;
import com.masai.Exception.StockNotFoundException;
import com.masai.Services.CustomerServices;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;



public class CustomerDAO implements CustomerServices {

	@Override
	public void signUpCustomer(Customer customer) throws DuplicateEmailException {
		EntityManager em = Utils.getEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			Query query = em.createQuery("select count(c) from Customer c where c.Email = :email");
			query.setParameter("email", customer.getEmail());
			long count = (long) query.getSingleResult();
			if (count > 0) {
				throw new DuplicateEmailException("Email already Exists - " + customer.getEmail());

			}
			em.persist(customer);
			et.commit();
			System.out.println("account successfully created");
		} catch (Exception e) {
			System.out.println("unable to SingUP");
		} finally {
			em.close();
			}
	}

	



	@Override
	public void addMoneyToWallet(int customerId, double amount) {
		// TODO Auto-generated method stub
		EntityManager em = Utils.getEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			Customer customer = em.find(Customer.class, customerId);
			if (customer == null) {
				System.out.println("Invalid Id");
				return;
			}
			customer.setWalletBalance(customer.getWalletBalance() + amount);
			em.merge(customer);
			et.commit();

		} catch (Exception e) {
			System.out.println(e.getMessage() + " " + "unable to add money in wallet");
		} finally {
			em.close();
		}
	}

	@Override
	public void withdrawMoneyFromWallet(int customerId, double amount) throws InsufficientBalanceException {
		EntityManager em = Utils.getEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			Customer customer = em.find(Customer.class, customerId);
			if (customer == null) {
				System.out.println("Invalid Id");
				return;
			}
			if (customer.getWalletBalance() < amount) {
				throw new InsufficientBalanceException("Insufficient balance in wallet");
			}
			customer.setWalletBalance(customer.getWalletBalance() - amount);
			em.merge(customer);
			et.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage() + "unable to withdraw Money");
		} finally {
			em.close();
		}

	}
	@Override
	public void viewAllStocks() {
		StockDAO st = new StockDAO();
		  List<Stock> stocks = st.getAllStocks();
		  System.out.println("Available stocks:");
		  for (Stock stock : stocks) {
		    System.out.println("Stock name: "+stock.getName()+ "Price: " + stock.getPrice() + " Quantity: " + stock.getQuantity());
		  }
		
	}
	@Override
	public void buyStock(int customerId, int stockID, int quantity)
	    throws StockNotFoundException, InsufficientStockException, InsufficientBalanceException {
		EntityManager em = Utils.getEntityManager();
	    EntityTransaction et = em.getTransaction();
	    try {
	      
	        et.begin();
	        
	        Customer customer = em.find(Customer.class, customerId);
	        Stock stock = em.find(Stock.class, stockID);
	        
	        if(customer.getWalletBalance() < stock.getPrice() * quantity) {
	            throw new InsufficientBalanceException("Insufficient balance in customer's account");
	        }
	        
	        double totalAmount = stock.getPrice() * quantity;
	        customer.setWalletBalance(customer.getWalletBalance() - totalAmount);
	        
	        Transaction tr = new Transaction();
	        tr.setCustomer(customer);
	        tr.setStock(stock);
	        tr.setQuantity(quantity);
	        tr.setTransactionType(TransactionType.BUY);
	        tr.setTransactionDate(LocalDate.now());
	        em.persist(tr);
	        stock.setQuantity(stock.getQuantity()-tr.getQuantity());
	        em.persist(stock);
	        et.commit();
	        
	        System.out.println("Successfully buy : " + stock.getName() + " stock");
	    } catch (InsufficientBalanceException e) {
	       
	        throw e;
	    } finally {
	        if (em != null) {
	            em.close();
	        }
	    }
	}

		

	@Override
	public void sellStock(int customerId, String stockName, int quantity)
			throws StockNotFoundException, InsufficientStockException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Transaction> getTransactionHistory(int customerId) {
	    EntityManager em = Utils.getEntityManager();
	    try {
	        Query query = em.createQuery(
	            "SELECT t FROM Transaction t WHERE t.customer.id = :customerId ORDER BY t.transactionDate DESC");
	            query.setParameter("customerId", customerId);
	           List<Transaction> tr =  query.getResultList();
	        return tr;
	    } finally {
	        if (em != null) {
	            em.close();
	        }
	    }
	}
    
	@Override
	public void deleteCustomer(int customerId) {
		
		    EntityManager em = Utils.getEntityManager();
		    EntityTransaction et = em.getTransaction();
		    try {
		        et.begin();
		        Customer customer = em.find(Customer.class, customerId);
		        if (customer != null) {
		            em.remove(customer);
		            et.commit();
		            System.out.println("Successfully deleted");
		        } else {
		            System.out.println("Customer not found with ID: " + customerId);
		        }
		    } catch (Exception e) {
		        System.out.println("Unable to delete account.");
		    } finally {
		        em.close();
		    }
		}

		public void WalletBalance(int customerId) {

		    EntityManager em = Utils.getEntityManager();
		    EntityTransaction et = em.getTransaction();
		    try {
		        et.begin();
		        Customer customer = em.find(Customer.class, customerId);
		       System.out.println(customer.getWalletBalance()); 
		    } catch (Exception e) {
		        System.out.println("Unable to delete account.");
		    } finally {
		        em.close();
		    }
		}





		@Override
		public void sellStock(int stockId, int quantity) throws StockNotFoundException, InsufficientStockException {
			// TODO Auto-generated method stub
			
		}
			
		}





	

	

