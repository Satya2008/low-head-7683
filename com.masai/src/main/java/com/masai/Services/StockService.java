package com.masai.Services;

import java.util.List;

import com.masai.Entities.Stock;

public interface StockService { 
	    public void addStock(Stock stock);
	    
	    public void deleteStock(int stockId);
	    
	    public  List<Stock> getAllStocks();
	    
	    public Stock getStockById(int stockId);
	    
	    public Stock getStockByName(String stockName);
	    
	    public void updateStock(Stock stock);
	    
	    public List<Stock> getStocksByCustomerId(int customerId);
	    
}
