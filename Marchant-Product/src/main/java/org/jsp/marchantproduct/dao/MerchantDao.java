package org.jsp.marchantproduct.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import org.hibernate.query.Query;
import org.jsp.marchantproduct.dto.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MerchantDao {
    @Autowired(required = true)
	private EntityManager manager;
    public static Merchant mdao;
    public Merchant saveMerchant(Merchant m) {
    	EntityTransaction t=manager.getTransaction();
    	t.begin();
    	manager.persist(m);
    	t.commit();
    	return m;
    }
    
    public Merchant updateMerchant(Merchant m) {
    	 mdao=manager.find(Merchant.class, m.getId());
    	if(mdao!=null) {
    		mdao.setName(m.getName());
    		mdao.setEmail(m.getEmail());
    		mdao.setPhone(m.getPhone());
    		mdao.setPassword(m.getPassword());
    		EntityTransaction t= manager.getTransaction();
    		t.begin();
    		
    		t.commit();
    		return mdao;
    	}
    	return null;
    }
	public Merchant FindMerchant(int id) {
	 return manager.find(Merchant.class, id);
	}
	public Merchant VerifyMerchant(long phone,String password) {
		Query q=(Query) manager.createQuery("select m from Merchant m where m.phone=?1 and m.password=?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			return (Merchant) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		
	}
	public Merchant VerifyMerchant(String email,String password) {
		Query q=(Query) manager.createQuery("select m from Merchant m where m.email=?1 and m.password=?2");
		q.setParameter(1, email);
		q.setParameter(2, password);
		try {
			return (Merchant) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		
	}
	
	
}
