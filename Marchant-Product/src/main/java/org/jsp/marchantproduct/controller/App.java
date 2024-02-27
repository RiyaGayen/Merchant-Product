package org.jsp.marchantproduct.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.marchantproduct.MerchantProductConfiguration;
import org.jsp.marchantproduct.dao.MerchantDao;
import org.jsp.marchantproduct.dao.ProductDao;
import org.jsp.marchantproduct.dto.Merchant;
import org.jsp.marchantproduct.dto.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	static ProductDao pdao;
	static MerchantDao mdao;
	static ApplicationContext context;
	static Scanner sc = new Scanner(System.in);
	static {
		context = new AnnotationConfigApplicationContext(MerchantProductConfiguration.class);
		pdao = context.getBean(ProductDao.class);
		mdao = context.getBean(MerchantDao.class);

	}

	public static void main(String[] args) {
		while (true) {
			System.out.println("1. SaveMerchant");
			System.out.println("2. UpdateMerchan");
			System.out.println("3. FindMerchnatById");
			System.out.println("4. VerifyMerchantByPhone");
			System.out.println("5. VerifyMerchantByEmail");
			System.out.println("6. AddProduct");
			System.out.println("7. UpdateProduct");
			System.out.println("8. FindProductByMerchantId");

			switch (sc.nextInt()) {
			case 1:
				SaveMerchant();
				break;
			case 2:
				UpdateMerchan();
				break;
			case 3:
				FindMerchnatById();
				break;
			case 4:
				VerifyMerchantByPhone();
				break;
			case 5:
				VerifyMerchantByEmail();
				break;
			case 6:
				AddProduct();
				break;
			case 7:
				UpdateProduct();
				break;
			case 8:
				FindProductByMerchantId();
				break;
			default:
				System.out.println("Invalid Option");
			}
		}
	}

	private static void FindProductByMerchantId() {
		System.out.println("Enter Merchant id");
		int id=sc.nextInt();
		List<Product> p=pdao.FindProductByMerchantId(id);
		if(p.size()>0) {
			for(Product x:p) {
				System.out.println(x.getName());
				System.out.println(x.getBrand());
				System.out.println(x.getCategory());
			}
			
		}
		
		
	}

	private static void UpdateProduct() {
		System.out.println("Enter Id, name,brand,category,description,Cost");
		Product p= new Product();
		p.setId(sc.nextInt());
		p.setName(sc.next());
		p.setBrand(sc.next());
		p.setCategory(sc.next());
		p.setDescription(sc.next());
		p.setCost(sc.nextDouble());
		p=pdao.UpdateProduct(p);
		if(p!=null) {
			System.out.println("Product Updated");
		}else {
			System.out.println("Cannot Update Product");
		}
		

	}

	private static void AddProduct() {
		System.out.println("Enter Merchant ID");
		int id = sc.nextInt();
		System.out.println("Enter name,brand,category,description,Cost");
		Product p = new Product();
		p.setName(sc.next());
		p.setBrand(sc.next());
		p.setCategory(sc.next());
		p.setDescription(sc.next());
		p.setCost(sc.nextDouble());
		p = pdao.AddProduct(p, id);
		if (p != null) {
			System.out.println("Product Saved With id : " + p.getId());
		} else {
			System.out.println("Not Saved");
		}

	}

	private static void SaveMerchant() {
		System.out.println("Enter Username,phone,email,Password to Save");
		Merchant m = new Merchant();
		m.setName(sc.next());
		m.setPhone(sc.nextLong());
		m.setEmail(sc.next());
		m.setPassword(sc.next());

		m = mdao.saveMerchant(m);
		if (m != null) {
			System.out.println("User Saved With id : " + m.getId());
		} else {
			System.out.println("Not Saved");
		}
	}

	private static void UpdateMerchan() {
		System.out.println("Enter Id,Username,phone,email,Password to Save");
		Merchant m = new Merchant();
		m.setId(sc.nextInt());
		m.setName(sc.next());
		m.setPhone(sc.nextLong());
		m.setEmail(sc.next());
		m.setPassword(sc.next());
		m = mdao.updateMerchant(m);
		if (m != null) {
			System.out.println("User Updated ");
		} else {
			System.out.println("Not Updated");
		}

	}

	private static void FindMerchnatById() {
		System.out.println("Enter Id");
		int id = sc.nextInt();
		Merchant m = new Merchant();
		m = mdao.FindMerchant(id);
		if (m != null) {
			System.out.println(m.getName());
			System.out.println(m.getEmail());
			System.out.println(m.getPhone());
			System.out.println(m.getPassword());

		} else {
			System.out.println("Invalid Id");
		}

	}

	private static void VerifyMerchantByPhone() {
		System.out.println("Enter phone Number And Password");
		long phone = sc.nextLong();
		String password = sc.next();
		Merchant m = new Merchant();
		m = mdao.VerifyMerchant(phone, password);
		if (m != null) {
			System.out.println(m.getName());
			System.out.println(m.getEmail());
			System.out.println(m.getPhone());
			System.out.println(m.getPassword());
		} else {
			System.out.println("Invalid Number or Password");
		}

	}

	private static void VerifyMerchantByEmail() {
		System.out.println("Enter phone email And Password");
		String email = sc.next();
		String password = sc.next();
		Merchant m = new Merchant();
		m = mdao.VerifyMerchant(email, password);
		if (m != null) {
			System.out.println(m.getName());
			System.out.println(m.getEmail());
			System.out.println(m.getPhone());
			System.out.println(m.getPassword());
		} else {
			System.out.println("Invalid Number or Password");
		}

	}

}
