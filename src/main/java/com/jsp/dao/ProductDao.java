package com.jsp.dao;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.jsp.entity.Cart;
import com.jsp.entity.Product;

public class ProductDao {

	Configuration cfg = new Configuration().configure().addAnnotatedClass(Product.class).addAnnotatedClass(Cart.class);

	SessionFactory sf = cfg.buildSessionFactory();

	public String addProduct(Product product) {

		Session session = sf.openSession();

		Transaction tranc = session.beginTransaction();

		session.save(product);

		tranc.commit();
		session.close();

		return "Product successfully saved...!";

	}

	public Product getProductById(int id) {
		Session session = sf.openSession();
		Transaction tranc = session.beginTransaction();
		Product product = session.get(Product.class, id);
		tranc.commit();
		session.close();

		return product;

	}

	public List<Product> getAllProducts() {
		Session session = sf.openSession();
		Transaction tranc = session.beginTransaction();

		Query query = session.createQuery("from Product p");

		List<Product> products = query.getResultList();

		tranc.commit();
		session.close();

		return products;

	}

	public String updateProduct(Product product) {
		Session session = sf.openSession();
		Transaction tranc = session.beginTransaction();

		session.update(product);
		tranc.commit();
		session.close();

		return "product update successfully";
	}

	public String deleteProduct(Product product) {
		Session session = sf.openSession();
		Transaction tranc = session.beginTransaction();

		session.delete(product);
		tranc.commit();
		session.close();

		return "product deleted successfully";
	}

}
