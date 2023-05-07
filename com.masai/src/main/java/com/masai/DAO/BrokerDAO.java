package com.masai.DAO;

import java.util.List;



import com.masai.Entities.Customer;
import com.masai.Entities.Stock;
import com.masai.Services.BrokerService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class BrokerDAO implements BrokerService{



  @Override
  public void login(String username, String password) {
 
  }

  @Override
  public List<Customer> getAllCustomers() {
	  EntityManager em = Utils.getEntityManager();
    Query query = em.createQuery("SELECT c FROM Customer c");
    List<Customer> customers = query.getResultList();
    return customers;
  }

  @Override
  public void addStock(String stockName, int quantity, double price) {
	  EntityManager em = Utils.getEntityManager();
	  EntityTransaction et = em.getTransaction();
	  

    Stock stock = new Stock();
    stock.setName(stockName);
    stock.setPrice(price);
    stock.setQuantity(quantity);

    et.begin();
    em.persist(stock);
    et.commit();
    System.out.println("Successfully added");
  }

  @Override
  public List<Stock> getAllStocks() {
	  EntityManager em = Utils.getEntityManager();
    Query query = em.createQuery("SELECT s FROM Stock s");
    List<Stock> stocks = query.getResultList();
    return stocks;
  }

  @Override
  public Stock getStock(String stockName) {
	  EntityManager em = Utils.getEntityManager();
    Query query = em.createQuery("SELECT s FROM Stock s WHERE s.name = :name");
    query.setParameter("name", stockName);
    Stock stock = (Stock) query.getSingleResult();
    return stock;
  }

  @Override
  public void deleteCustomer(int customerId) {
	  EntityManager em = Utils.getEntityManager();
    EntityTransaction et = em.getTransaction();

    et.begin();
    Customer customer = em.find(Customer.class, customerId);
    em.remove(customer);
    et.commit();
    System.out.println("Customer deleted successfully");
  }

  @Override
  public void deleteStock(Stock stock) {
	  EntityManager em = Utils.getEntityManager();
      EntityTransaction et = em.getTransaction();

    et.begin();
    em.remove(stock);
    et.commit();
    System.out.println("Stock deleted successfully");
  }

  @Override
  public void logout() {
    // Broker logout is not implemented in this DAO.
    // TODO Auto-generated method stub
  }

@Override
public Customer getCustomer(int customerID) {
	EntityManager em = Utils.getEntityManager();
	Customer customer = em.find(Customer.class, customerID);
	return customer;
}

}