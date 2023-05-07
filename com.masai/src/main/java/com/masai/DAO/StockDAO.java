package com.masai.DAO;

import java.util.List;

import com.masai.Entities.Stock;
import com.masai.Services.StockService;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class StockDAO implements StockService {

	@Override
	public void addStock(Stock stock) {
		EntityManager em = Utils.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(stock);
		et.commit();
		em.close();
	}

	@Override
	public void deleteStock(int stockId) {
		EntityManager em = Utils.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Stock stock = em.find(Stock.class, stockId);
		if(stock!=null) {
			em.remove(stock);
		}
		et.commit();
		em.close();
	}

	@Override
	public  List<Stock> getAllStocks() {
		EntityManager em = Utils.getEntityManager();
		Query query = em.createQuery("select s from Stock s");
		return query.getResultList();
	
	}

	@Override
	public Stock getStockById(int stockId) {
		EntityManager em = Utils.getEntityManager();
        return em.find(Stock.class, stockId);
	}

	@Override
	public Stock getStockByName(String stockName) {
		EntityManager em = Utils.getEntityManager();
		Query query = em.createQuery("SELECT s FROM Stock s WHERE s.name = :name");
        query.setParameter("name", stockName);
        List<Stock> stocks = query.getResultList();
        if (stocks.isEmpty()) {
            return null;
        } else {
            return stocks.get(0);
        }
	}

	@Override
	public void updateStock(Stock stock) {
		EntityManager em = Utils.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(stock);
		et.commit();
		em.close();
		
		
	}

	@Override
	public List<Stock> getStocksByCustomerId(int customerId) {
		EntityManager em = Utils.getEntityManager();
		Query query = em.createQuery("SELECT s FROM Stock s WHERE s.customer.id = :customerID");
		query.setParameter("customerId", customerId);
		return query.getResultList();
	}

	

}
