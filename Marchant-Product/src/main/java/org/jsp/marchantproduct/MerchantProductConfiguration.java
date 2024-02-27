package org.jsp.marchantproduct;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"org.jsp"})
public class MerchantProductConfiguration {
		@Bean
	       public EntityManager getManager() {
	    	   return Persistence.createEntityManagerFactory("dev").createEntityManager();
	       
	}

}
