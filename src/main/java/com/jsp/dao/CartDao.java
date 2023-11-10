package com.jsp.dao;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.jsp.entity.Cart;
import com.jsp.entity.Product;

public class CartDao {

	Configuration cfg = new Configuration().configure().addAnnotatedClass(Cart.class).addAnnotatedClass(Product.class);;

	SessionFactory sf = cfg.buildSessionFactory();

	public String createCart(Cart cart) {
		Session session = sf.openSession();
		Transaction tranc = session.beginTransaction();
		session.save(cart);
		tranc.commit();
		session.close();
		return "Cart Created Successfully...!";
	}
	public Cart viewCarts(Cart cart) {
		Session session = sf.openSession();
		Transaction tranc = session.beginTransaction();
		org.hibernate.Query<Cart> cartList=session.createQuery("from Cart");
		List<Cart> list=cartList.list();
		for(Cart carts:list) {
			System.out.println(carts.getCartId());
		}
		return cart;
		
	}
	public Cart viewProductsByCartId(int cartId) {
		Session session = sf.openSession();
		Transaction tranc = session.beginTransaction();
		Cart cart = session.get(Cart.class, cartId);
		List<Product> productsList=cart.getProducts();
		for(Product product:productsList) {
			System.out.println(product.getProductId()+" "+product.getProductName()+" "+product.getProductPrice()+" "+product.getProductQuantity());
		}

		return cart;
		
	}
	public String addProductToCart(Cart cart, int cartId, int productId) {
		Session session = sf.openSession();
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
		Session session = sf.openSession();
		Transaction tranc = session.beginTransaction();
		Cart cart = session.get(Cart.class, CartId);
		List<Product> productsList=cart.getProducts();
		for(Product product:productsList) {
			session.remove(product.getProductId());
		}
		session.update(cart);
		tranc.commit();
		session.close();
		return "Product removed Successfully...from the cart!" ;
		
	}
	
}
