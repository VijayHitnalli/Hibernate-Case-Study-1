package com.jsp.dao;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.entity.Cart;
import com.jsp.entity.Product;

@Repository
public class CartDao {
	
	Configuration configuration = new Configuration().configure().addAnnotatedClass(Product.class).addAnnotatedClass(Cart.class);
	SessionFactory sessionFactory = configuration.buildSessionFactory();
	
	public String createCart(Cart cart) {
		Session session = sessionFactory.openSession();
		Transaction tranc = session.beginTransaction();
		session.save(cart);
		tranc.commit();
		session.close();
		return "Cart Created Successfully...!";
	}
	public Cart viewCarts(Cart cart) {
		Session session = sessionFactory.openSession();
		Transaction tranc = session.beginTransaction();
		org.hibernate.Query<Cart> cartList=session.createQuery("from Cart");
		List<Cart> list=cartList.list();
		for(Cart carts:list) {
			System.out.println(carts.getCartId());
		}
		return cart;
		
	}
	public Cart viewProductsByCartId(int cartId) {
		Session session = sessionFactory.openSession();
		Transaction tranc = session.beginTransaction();
		Cart cart = session.get(Cart.class, cartId);
		List<Product> productsList=cart.getProducts();
		for(Product product:productsList) {
			System.out.println(product.getProductId()+" "+product.getProductName()+" "+product.getProductPrice()+" "+product.getProductQuantity());
		}

		return cart;
		
	}
	public String addProductToCart(Cart cart, int cartId, int productId) {
		Session session =sessionFactory.openSession();
		Transaction tranc = session.beginTransaction();
		cart = session.get(Cart.class, cartId);
		cart.getProducts().add(session.get(Product.class, productId));
		session.saveOrUpdate(cart);
		System.out.println("Product added Successfully...!");
		tranc.commit();
		session.close();
		return "Product added Successfully...!";

	}
	@SuppressWarnings("deprecation")
	public String removeProductFromCart(int CartId,int productId) {
		Session session = sessionFactory.openSession();
		Transaction tranc = session.beginTransaction();
		Product product =session.get(Product.class, productId);
		Cart cart = session.get(Cart.class, CartId);
		cart.getProducts().remove(product);
		
		session.update(cart);
		tranc.commit();
		session.close();
		return "Product removed Successfully...from the cart!" ;
		
	}
	
}
