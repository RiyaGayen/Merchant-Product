package org.jsp.marchantproduct.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import org.hibernate.query.Query;
import org.jsp.marchantproduct.dto.Merchant;
import org.jsp.marchantproduct.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class ProductDao {
	  @Autowired
		private EntityManager manager;
	    public static Product pdao;
	    
	    public Product AddProduct(Product p,int id) {
	    	Merchant m=manager.find(Merchant.class, id);
	    	if(m!=null) {
	    		m.getProduct().add(p);
	    		p.setMerchant(m);
	    		
	    		EntityTransaction t= manager.getTransaction();
	    		t.begin();
	    		manager.persist(p);
	    		t.commit();
	    		return p;
	    	}
	    	return null;
	    }
	    public Product UpdateProduct(Product p) {
	    	pdao=manager.find(Product.class, p.getId());
	    	if(pdao!=null) {
	    		pdao.setBrand(p.getBrand());
	    		pdao.setCategory(p.getCategory());
	    		pdao.setCost(p.getCost());
	    		pdao.setName(p.getName());
	    	 EntityTransaction t= manager.getTransaction();
	    	 t.begin();
	    	 
	    	 t.commit();
	    	 return pdao;
	    			 
	    	}
	    	return null;
	    	
	    	
	    }
	    public List<Product> FindProductByMerchantId(int id){
	    	Query q=(Query) manager.createQuery("select m.product from Merchant m where m.id=?1");
	    	q.setParameter(1, id);
	    	try {
				return q.getResultList();
			} catch (NoResultException e) {
				return null;
			}
			
	    }
	    public List<Product> FindProductByBrand(String brand){
	    	Query q=(Query) manager.createQuery("select p from Product p where p.brand=?1");
	    	q.setParameter(1, brand);
	    	try {
				return q.getResultList();
			} catch (NoResultException e) {
				return null;
			}
	    }
	    public List<Product> FindProductByCatergory(String category){
	    	Query q=(Query) manager.createQuery("select p from Product p where p.category=?1");
	    	q.setParameter(1, category);
	    	try {
				return q.getResultList();
			} catch (NoResultException e) {
				return null;
			}
	    }
}


