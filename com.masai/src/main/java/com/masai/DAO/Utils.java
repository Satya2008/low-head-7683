package com.masai.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Utils {

static EntityManagerFactory emf;
	
	static {
		emf = Persistence.createEntityManagerFactory("stockConnect");
	}
	
	public static EntityManager getEntityManager() {
		 return emf.createEntityManager();
	}
}
