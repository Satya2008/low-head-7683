package com.masai.DAO;

import java.util.List;

import com.masai.Entities.Customer;
import com.masai.Entities.Transaction;
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
	public  void signUpCustomer(Customer customer) throws DuplicateEmailException {
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
		 System.out.println("Singup successfully");
		} catch (Exception e) {
			System.out.println(e.getMessage() + " " + " unable to SingUP");
		} finally {
			em.close();
		}

	}

	@Override
	public Customer getCustomerById(int custID) throws SomethingWentWrong {
		EntityManager em = Utils.getEntityManager();

		Customer customer = null;

		try {
			customer = em.find(Customer.class, custID);
			if (customer == null) {
				throw new SomethingWentWrong("Customer not found");
			}

		} catch (Exception e) {
			throw new SomethingWentWrong("Unable to find");

		}
		return customer;

	}

	@Override
    public Customer getCustomerByUsername(String username) throws SomethingWentWrong {
        EntityManager em = Utils.getEntityManager();
        try {
            Query query = em.createQuery("SELECT c FROM Customer c WHERE c.userName = :username");
            query.setParameter("username", username);
            List<Customer> customers = query.getResultList();
            if (customers.size() == 0) {
                throw new SomethingWentWrong("Customer not found");
            }
            return customers.get(0);
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
	public void buyStock(int customerId, String stockName, int quantity)
			throws StockNotFoundException, InsufficientStockException, InsufficientBalanceException {

	}

	@Override
	public void sellStock(int customerId, String stockName, int quantity)
			throws StockNotFoundException, InsufficientStockException {

	}

	@Override
	public List<Transaction> getTransactionHistory(int customerId) {

		return null;
	}

	@Override
	public void deleteCustomer(int customerId) {

	}

}
